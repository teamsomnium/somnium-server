package com.server.somnium.domain.user.model

import com.server.somnium.domain.auth.dto.AuthUserInfo
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity(name = "user")
class User(
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
        val roles: MutableList<Role> = mutableListOf()
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val role = this.roles.map{it.name}.toString()
        return role.map{SimpleGrantedAuthority(role)}.toMutableList()
    }

    override fun getPassword() = ""

    override fun getUsername() = authId.toString()

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

    fun updateUserInfo(authUserInfo: AuthUserInfo) {
        this.name = authUserInfo.properties.nickname
    }
}