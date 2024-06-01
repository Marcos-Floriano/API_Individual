package org.example.school.sptech.ApiIndividual.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.example.school.sptech.ApiIndividual.dominio.Funcionarios
import org.example.school.sptech.ApiIndividual.dto.FuncionarioDto
import org.example.school.sptech.ApiIndividual.service.FuncionarioService

@RestController
@RequestMapping("/funcionarios")
class FuncionarioController(
    private val funcionarioService: FuncionarioService
) {

    @GetMapping
    fun listar(): ResponseEntity<List<Funcionarios>> {
        val funcionarios = funcionarioService.getAllFuncionarios()
        return if (funcionarios.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(funcionarios)
        }
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<Funcionarios> {
        val funcionario = funcionarioService.getFuncionarioById(id)
        return ResponseEntity.ok(funcionario)
    }

    @PostMapping
    fun criar(@RequestBody @Valid funcionarioDTO: FuncionarioDto): ResponseEntity<Funcionarios> {
        val funcionarioSalvo = funcionarioService.createFuncionario(funcionarioDTO)
        return ResponseEntity.status(201).body(funcionarioSalvo)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody @Valid funcionarioDTO: FuncionarioDto): ResponseEntity<Funcionarios> {
        val funcionarioAtualizado = funcionarioService.updateFuncionario(id, funcionarioDTO)
        return ResponseEntity.ok(funcionarioAtualizado)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Int): ResponseEntity<Void> {
        funcionarioService.deleteFuncionario(id)
        return ResponseEntity.noContent().build()
    }
}