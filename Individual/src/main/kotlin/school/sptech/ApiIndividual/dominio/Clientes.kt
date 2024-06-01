package org.example.school.sptech.ApiIndividual.dominio

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
data class Clientes(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @field:NotEmpty
    @field:Size(min = 2, max = 100)
    var nome: String,

    @field:NotEmpty
    @field:Email
    var email: String
)
