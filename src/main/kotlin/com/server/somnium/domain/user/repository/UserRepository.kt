package com.server.somnium.domain.user.repository

import com.server.somnium.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findUserByAuthId(authId: Long): User?
}