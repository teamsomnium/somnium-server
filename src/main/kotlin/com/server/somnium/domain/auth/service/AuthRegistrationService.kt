package com.server.somnium.domain.auth.service

import com.server.somnium.domain.auth.dto.AuthUserInfo
import com.server.somnium.domain.auth.service.facade.AuthRegistrationFacade
import com.server.somnium.domain.user.model.Role
import com.server.somnium.domain.user.model.User
import com.server.somnium.domain.user.repository.UserRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

private val logger = KotlinLogging.logger {}

@Service
class AuthRegistrationService(
    private val userRepository: UserRepository,
    private val authRegistrationFacade: AuthRegistrationFacade
) {

    @Transactional
    fun register(code: String) {
        val token = authRegistrationFacade.getToken(code)
        val userInfo = authRegistrationFacade.getUserInfo(token.access_token)

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
