package school.sptech.ApiIndividual.repository

import org.apache.commons.lang3.ClassUtils.Interfaces
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import school.sptech.ApiIndividual.dominio.Funcionarios

interface FuncionariosRepository:JpaRepository<Funcionarios,Int> {

    @Query("SELECT f FROM Funcionarios f WHERE f.cargo = :cargo")
    fun findByCargo(cargo: String): List<Funcionarios>

    @Query("SELECT f FROM Funcionarios f WHERE f.salario >= :salarioMin AND f.salario <= :salarioMax")
    fun findBySalarioBetween(salarioMin: Double, salarioMax: Double): List<Funcionarios>

}