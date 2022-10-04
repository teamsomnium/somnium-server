package com.server.somnium.domain.user.model

import org.springframework.security.core.GrantedAuthority

enum class Role: GrantedAuthority {
    ROLE_USER;

    override fun getAuthority(): String {
        return name
    }
}
