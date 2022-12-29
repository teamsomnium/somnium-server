package com.server.somnium.domain.auth.service.facade.impl

import com.server.somnium.domain.auth.dto.AuthTokenResponse
import com.server.somnium.domain.auth.dto.AuthUserInfo
import com.server.somnium.domain.auth.property.AuthProperties
import com.server.somnium.domain.auth.service.facade.AuthRegistrationFacade
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class AuthRegistrationFacadeImpl(
    private val authProperties: AuthProperties
): AuthRegistrationFacade {

    override fun getToken(code: String): AuthTokenResponse {
        return WebClient.create()
                .post()
                .uri("https://kauth.kakao.com/oauth/token")
                .headers {
                    it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                    it.acceptCharset = Collections.singletonList(StandardCharsets.UTF_8)
                }
                .bodyValue(getTokenRequestBody(code))
                .retrieve()
                .bodyToMono(AuthTokenResponse::class.java)
                .block()!!
    }

    override fun getUserInfo(accessToken: String): AuthUserInfo {
        return WebClient.create()
                .get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .headers {
                    it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                    it.acceptCharset = Collections.singletonList(StandardCharsets.UTF_8)
                    it.add("Authorization", "Bearer $accessToken")
                }
                .retrieve()
                .bodyToMono(AuthUserInfo::class.java)
                .block()!!
    }

    private fun getTokenRequestBody(code: String): MultiValueMap<String, String> {
        val formData: MultiValueMap<String, String> = LinkedMultiValueMap()
        formData.add("grant_type", "authorization_code")
        formData.add("client_id", authProperties.clientId)
        formData.add("redirect_uri", authProperties.redirectUri)
        formData.add("code", code)
        formData.add("client_secret", authProperties.clientSecret)

        return formData
    }
}