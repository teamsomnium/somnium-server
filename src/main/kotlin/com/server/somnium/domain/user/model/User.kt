package com.server.somnium.domain.user.model

import com.server.somnium.domain.auth.dto.AuthUserInfo
import javax.persistence.*

@Entity(name = "user")
class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "auth_id", nullable = false, unique = true)
    val authId: Long,

    @Column(name = "name", nullable = false)
    var name: String,

    @Enumerated(EnumType.STRING) @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = [JoinColumn(name = "user_id")])
    val roles: List<Role>? = ArrayList()
) {
    fun updateUserInfo(authUserInfo: AuthUserInfo) {
        this.name = authUserInfo.properties.nickname
    }
}