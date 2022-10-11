package com.server.somnium.domain.auth.property

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthPropertiesTest(
    @Autowired val authProperties: AuthProperties
) {

    @Test
    @DisplayName("AuthProperties의 property들이 잘 불러와 지나요?")
    fun authPropertiesTest() {
        Assertions.assertNotNull(authProperties.clientId)
        Assertions.assertNotNull(authProperties.redirectUri)
        Assertions.assertNotNull(authProperties.clientSecret)
    }

}