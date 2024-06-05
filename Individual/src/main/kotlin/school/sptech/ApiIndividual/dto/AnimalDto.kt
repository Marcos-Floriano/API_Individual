package school.sptech.ApiIndividual.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class AnimalDto(

    @field:NotEmpty
    @field:Size(min = 2, max = 50)
    var nome: String,

    @field:NotEmpty
    @field:Size(min = 2, max = 50)
    var especie: String,

    @field:Min(0)
    var idade: Int

)
