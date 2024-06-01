package school.sptech.projetoestoque.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.example.school.sptech.ApiIndividual.dominio.Funcionarios

@Repository
interface FuncionarioRepository : JpaRepository<Funcionarios, Int> {

    @Query("SELECT f FROM Funcionario f WHERE f.cargo = :cargo")
    fun findByCargo(cargo: String): List<Funcionarios>

    @Query("SELECT f FROM Funcionario f WHERE f.salario >= :salarioMin AND f.salario <= :salarioMax")
    fun findBySalarioBetween(salarioMin: Double, salarioMax: Double): List<Funcionarios>
}
