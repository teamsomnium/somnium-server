package com.server.somnium.global.jwt

import com.server.somnium.domain.user.model.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.Date

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret-key}")
    private val secretKey: String
) {

    private val key: Key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

    companion object {
        private const val ACCESS_TOKEN_EXPIRED_IN: Long = 1000L * 60 * 60 * 3
    }

    fun createAccessToken(authId: String, roles: MutableList<Role>): String {
        val claims: Claims = Jwts.claims().setSubject(authId)
        claims["auth"] = roles
                .map { it.authority }
                .toList()

        val now = Date()
        val accessTokenExpiresIn = Date(now.time + ACCESS_TOKEN_EXPIRED_IN)

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact()
    }

}