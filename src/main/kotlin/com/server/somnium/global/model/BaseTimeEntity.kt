package com.server.somnium.global.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
/**
 * data가 저장된 시점의 시간과 마지막으로 변경이 일어난 시간을 저장하고 관리하는 전역 entity
 */
class BaseTimeEntity {
    @CreatedDate @Column(updatable = false)
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    lateinit var modifiedDate: LocalDateTime
}