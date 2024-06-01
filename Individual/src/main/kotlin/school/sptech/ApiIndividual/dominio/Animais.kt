package org.example.school.sptech.ApiIndividual.dominio

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
data class Animais(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @field:NotEmpty
    @field:Size(min = 2, max = 50)
    var nome: String,

    @field:NotEmpty
    @field:Size(min = 2, max = 50)
    var especie: String,

    @field:Min(0)
    var idade: Int
)