package org.example.school.sptech.ApiIndividual.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.example.school.sptech.ApiIndividual.dominio.Animais

@Repository
interface AnimalRepository : JpaRepository<Animais, Int> {

    @Query("SELECT a FROM Animal a WHERE a.especie = :especie")
    fun findByEspecie(especie: String): List<Animais>

    @Query("SELECT a FROM Animal a WHERE a.idade >= :idadeMin AND a.idade <= :idadeMax")
    fun findByIdadeBetween(idadeMin: Int, idadeMax: Int): List<Animais>
}