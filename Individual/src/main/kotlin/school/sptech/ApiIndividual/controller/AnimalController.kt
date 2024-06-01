package org.example.school.sptech.ApiIndividual.controller

import jakarta.validation.Valid
import org.example.school.sptech.ApiIndividual.dominio.Animais
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.example.school.sptech.ApiIndividual.dto.AnimalDto
import org.example.school.sptech.ApiIndividual.service.AnimalService

@RestController
@RequestMapping("/animais")
class AnimalController(
    private val animalService: AnimalService
) {

    @GetMapping
    fun listar(): ResponseEntity<List<Animais>> {
        val animais = animalService.getAllAnimals()
        return if (animais.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(animais)
        }
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<Animais> {
        val animal = animalService.getAnimalById(id)
        return ResponseEntity.ok(animal)
    }

    @PostMapping
    fun criar(@RequestBody @Valid animalDTO: AnimalDto): ResponseEntity<Animais> {
        val animalSalvo = animalService.createAnimal(animalDTO)
        return ResponseEntity.status(201).body(animalSalvo)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody @Valid animalDTO: AnimalDto): ResponseEntity<Animais> {
        val animalAtualizado = animalService.updateAnimal(id, animalDTO)
        return ResponseEntity.ok(animalAtualizado)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Int): ResponseEntity<Void> {
        animalService.deleteAnimal(id)
        return ResponseEntity.noContent().build()
    }
}