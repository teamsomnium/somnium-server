package com.server.somnium.domain.auth.service.facade

import com.server.somnium.domain.auth.dto.AuthTokenResponse
import com.server.somnium.domain.auth.dto.AuthUserInfo

interface AuthRegistrationFacade {
    fun getToken(code: String): AuthTokenResponse
    fun getUserInfo(accessToken: String): AuthUserInfo
}