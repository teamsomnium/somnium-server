package com.server.somnium.global.jwt

import com.server.somnium.domain.user.model.Role
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.Collections

@SpringBootTest
class JwtTokenProviderTest(
    @Autowired val jwtTokenProvider: JwtTokenProvider
){

    @Test
    @DisplayName("accessToken이 잘 생성되나요?")
    fun createAccessTokenTest() {
        //given //when
        val accessToken = jwtTokenProvider.createAccessToken("1234", Collections.singletonList(Role.ROLE_USER))

        //then
        Assertions.assertNotNull(accessToken)
    }

    @Test
    @DisplayName("token의 expired 여부가 잘 검증 되나요?")
    fun validateTokenTest() {
        //given
        val accessToken = createAccessToken()

        //when
        val validateResult = jwtTokenProvider.validateToken(accessToken)

        //then
        Assertions.assertTrue(validateResult)

    }

    @DisplayName("Test를 위한 AccessToken 발급 메소드")
    private fun createAccessToken(): String {
        return jwtTokenProvider.createAccessToken("1234", Collections.singletonList(Role.ROLE_USER))
    }
}