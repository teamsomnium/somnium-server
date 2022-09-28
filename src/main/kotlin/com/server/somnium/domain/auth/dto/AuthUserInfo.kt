package com.server.somnium.domain.auth.dto

data class AuthUserInfo(
    val id: Long,
    val properties: Properties
) {
    data class Properties(
        val nickname: String
    )
}