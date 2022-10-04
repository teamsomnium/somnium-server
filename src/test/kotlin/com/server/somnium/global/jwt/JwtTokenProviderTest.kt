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

}