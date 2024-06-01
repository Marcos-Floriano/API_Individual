package org.example.school.sptech.ApiIndividual.service

import org.springframework.stereotype.Service
import org.example.school.sptech.ApiIndividual.dominio.Animais
import org.example.school.sptech.ApiIndividual.dto.AnimalDto
import org.example.school.sptech.ApiIndividual.repository.AnimalRepository

@Service
class AnimalService(
    private val animalRepository: AnimalRepository
) {

    fun getAllAnimals(): List<Animais> = animalRepository.findAll()

    fun getAnimalById(id: Int): Animais = animalRepository.findById(id).orElseThrow {
        IllegalArgumentException("Animal não encontrado com ID: $id")
    }

    fun createAnimal(animalDTO: AnimalDto): Animais {
        val animal = Animais(
            nome = animalDTO.nome,
            especie = animalDTO.especie,
            idade = animalDTO.idade
        )
        return animalRepository.save(animal)
    }

    fun updateAnimal(id: Int, animalDTO: AnimalDto): Animais {
        val animalExistente = getAnimalById(id)
        animalExistente.nome = animalDTO.nome
        animalExistente.especie = animalDTO.especie
        animalExistente.idade = animalDTO.idade
        return animalRepository.save(animalExistente)
    }

    fun deleteAnimal(id: Int) {
        val animal = getAnimalById(id)
        animalRepository.delete(animal)
    }
}