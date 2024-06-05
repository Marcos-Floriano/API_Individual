package school.sptech.ApiIndividual.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

class FuncionarioDto (
    @field:NotEmpty
    @field:Size(min = 2, max = 100)
    var nome: String,

    @field:NotEmpty
    @field:Size(min = 2, max = 100)
    var cargo: String,

    @field:NotNull
    @field:Min(0)
    var salario: Double
)