package com.server.somnium.domain.somnium.model

import com.server.somnium.domain.user.model.User
import javax.persistence.*

@Entity(name = "somnium")
class Somnium(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    val status: Status,

    @Column(name = "description", nullable = false)
    val description: String,

    @Column(name = "image_url", nullable = true, length = 500)
    val imageUrl: String

) {

}