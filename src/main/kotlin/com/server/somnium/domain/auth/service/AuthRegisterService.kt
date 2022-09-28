package com.server.somnium.domain.auth.facade

import com.server.somnium.domain.auth.dto.AuthTokenResponse
import com.server.somnium.domain.auth.dto.AuthUserInfo
import com.server.somnium.domain.auth.property.AuthProperties
import com.server.somnium.domain.user.repository.UserRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class AuthRegisterService(
    private val authProperties: AuthProperties,
    private val userRepository: UserRepository
) {

    fun register(code: String) {
        val userInfo = getUserInfo(getToken(code).access_token)

        val boolean = isNewUser(userInfo)
    }

    private fun getToken(code: String): AuthTokenResponse {
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

    private fun getUserInfo(accessToken: String): AuthUserInfo {
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

    /**
     * token을 받아오는 http요청에 필요한 body
     */
    private fun getTokenRequestBody(
        code: String,
    ): MultiValueMap<String, String> {
        val formData: MultiValueMap<String, String> = LinkedMultiValueMap()
        formData.add("grant_type", "authorization_code")
        formData.add("client_id", authProperties.clientId)
        formData.add("redirect_uri", authProperties.redirectUri)
        formData.add("code", code)
        formData.add("client_secret", authProperties.clientSecret)

        return formData
    }

    /**
     * 신규 유저인지 아닌지를 검증하는 메서드
     */
    private fun isNewUser(authUserInfo: AuthUserInfo): Boolean {
        userRepository.findUserByAuthId(authUserInfo.id)
                ?: return false
        return true
    }
}
