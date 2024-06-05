package school.sptech.ApiIndividual.service

import org.springframework.stereotype.Service
import school.sptech.ApiIndividual.dominio.Animal
import school.sptech.ApiIndividual.dto.AnimalDto
import school.sptech.ApiIndividual.repository.AnimalRepository

@Service
class AnimalService(
    private val animalRepository: AnimalRepository
) {

    fun getAllAnimals(): List<Animal> = animalRepository.findAll()

    fun getAnimalById(id: Int): Animal = animalRepository.findById(id).orElseThrow {
        IllegalArgumentException("Animal não encontrado com ID: $id")
    }

    fun createAnimal(animalDTO: AnimalDto): Animal {
        val animal = Animal(
            nome = animalDTO.nome,
            especie = animalDTO.especie,
            idade = animalDTO.idade
        )
        return animalRepository.save(animal)
    }

    fun updateAnimal(id: Int, animalDTO: AnimalDto): Animal {
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