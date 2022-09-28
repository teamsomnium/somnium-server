package com.server.somnium.domain.auth.service

import com.server.somnium.domain.auth.dto.AuthUserInfo
import com.server.somnium.domain.auth.service.facade.AuthRegisterFacade
import com.server.somnium.domain.user.model.Role
import com.server.somnium.domain.user.model.User
import com.server.somnium.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AuthRegisterService(
    private val userRepository: UserRepository,
    private val authRegisterFacade: AuthRegisterFacade
) {

    @Transactional
    fun register(code: String) {
        val token = authRegisterFacade.getToken(code)
        val userInfo = authRegisterFacade.getUserInfo(token.access_token)

        if (isNewUser(userInfo))
            registerNewUser(userInfo)
        else
            updateUserInfo(userInfo)
    }


    private fun isNewUser(authUserInfo: AuthUserInfo): Boolean {
        userRepository.findUserByAuthId(authUserInfo.id) ?: return true
        return false
    }

    private fun registerNewUser(authUserInfo: AuthUserInfo) {
        userRepository.save(User(
                authId = authUserInfo.id,
                name = authUserInfo.properties.nickname,
                roles = Collections.singletonList(Role.ROLE_USER)
        ))
    }

    private fun updateUserInfo(authUserInfo: AuthUserInfo) {
        val user = userRepository.findUserByAuthId(authUserInfo.id)!!
        user.updateUserInfo(authUserInfo)
    }

}
