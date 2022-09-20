package com.server.somnium.domain.user.domain

import javax.persistence.*

@Entity(name = "user")
class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "oauth_id", nullable = false, unique = true)
    val oauthId: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING) @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    val roles: List<Role>? = ArrayList()
) {

}