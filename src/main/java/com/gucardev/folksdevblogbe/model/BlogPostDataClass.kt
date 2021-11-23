package com.gucardev.folksdevblogbe.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class BlogPostDataClass(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val name: @NotNull @NotBlank String?,
    val imageUrl: String?,
    val details: @Size(max = 2000) String?,
    val videoUrl: String?,
    @JsonIgnore
    val deleted: Boolean = false,
    @CreationTimestamp
    val created: Timestamp?,
    @UpdateTimestamp
    val modified: Timestamp?,

    )