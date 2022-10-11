package com.server.somnium.global.jwt.repository

import com.server.somnium.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetails(
    private val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepository.findUserByAuthId(username!!.toLong())
                ?: throw IllegalArgumentException("해당 username을 가진 user가 존재하지 않습니다. username: $username")
    }
}