package school.sptech.ApiIndividual.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import school.sptech.ApiIndividual.dominio.Animal

@Repository
interface AnimalRepository : JpaRepository<Animal, Int> {

    @Query("SELECT a FROM Animal a WHERE a.especie = :especie")
    fun findByEspecie(especie: String): List<Animal>

    @Query("SELECT a FROM Animal a WHERE a.idade >= :idadeMin AND a.idade <= :idadeMax")
    fun findByIdadeBetween(idadeMin: Int, idadeMax: Int): List<Animal>
}