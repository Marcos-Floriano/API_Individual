package school.sptech.ApiIndividual.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import school.sptech.ApiIndividual.dominio.Animal
import school.sptech.ApiIndividual.dto.AnimalDto
import school.sptech.ApiIndividual.repository.AnimalRepository
import school.sptech.ApiIndividual.service.AnimalService
import java.util.*

@SpringBootTest
class AnimalServiceTest {

    val service = AnimalService(mock(AnimalRepository::class.java))
    private lateinit var animalRepository: AnimalRepository

    @BeforeEach
    fun setup() {
        animalRepository = mock(AnimalRepository::class.java)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/animais.csv"], numLinesToSkip = 1)
    fun `deve listar todos os animais`(id: Int, nome: String, especie: String, idade: Int) {
        val animal = Animal(id = id, nome = nome, especie = especie, idade = idade)
        `when`(animalRepository.findAll()).thenReturn(listOf(animal))

        val result = service.getAllAnimals()
        assertFalse(result.isEmpty())
        assertEquals(1, result.size)
        assertEquals(nome, result[0].nome)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/animais.csv"], numLinesToSkip = 1)
    fun `deve buscar animal por id`(id: Int, nome: String, especie: String, idade: Int) {
        val animal = Animal(id = id, nome = nome, especie = especie, idade = idade)
        `when`(animalRepository.findById(id)).thenReturn(Optional.of(animal))

        val encontrado = service.getAnimalById(id)
        assertEquals(nome, encontrado.nome)
    }

    @Test
    fun `deve criar um novo animal`() {
        val animalDTO = AnimalDto(nome = "Tigre", especie = "Panthera tigris", idade = 4)
        val animal = Animal(nome = animalDTO.nome, especie = animalDTO.especie, idade = animalDTO.idade)
        `when`(animalRepository.save(any(Animal::class.java))).thenReturn(animal)

        val salvo = service.createAnimal(animalDTO)
        assertEquals("Tigre", salvo.nome)
    }
}