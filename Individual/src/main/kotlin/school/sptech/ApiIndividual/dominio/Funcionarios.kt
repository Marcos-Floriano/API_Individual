package school.sptech.ApiIndividual.dominio

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
class Funcionarios(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

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