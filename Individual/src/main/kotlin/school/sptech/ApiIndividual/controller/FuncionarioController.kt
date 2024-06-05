package school.sptech.ApiIndividual.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.ApiIndividual.dominio.Funcionarios
import school.sptech.ApiIndividual.dto.FuncionarioDto
import school.sptech.ApiIndividual.service.FuncionariosService

@RestController
@RequestMapping("/funcionarios")
class FuncionarioController(
    private val funcionariosService: FuncionariosService
) {

    @GetMapping
    fun listar(): ResponseEntity<List<Funcionarios>> {
        val funcionarios = funcionariosService.getAllFuncionarios()
        return if (funcionarios.isEmpty()) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(funcionarios)
        }
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<Funcionarios> {
        val funcionario = funcionariosService.getFuncionarioById(id)
        return ResponseEntity.ok(funcionario)
    }

    @PostMapping
    fun criar(@RequestBody @Valid funcionarioDTO: FuncionarioDto): ResponseEntity<Funcionarios> {
        val funcionarioSalvo = funcionariosService.createFuncionario(funcionarioDTO)
        return ResponseEntity.status(201).body(funcionarioSalvo)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody @Valid funcionarioDTO: FuncionarioDto): ResponseEntity<Funcionarios> {
        val funcionarioAtualizado = funcionariosService.updateFuncionario(id, funcionarioDTO)
        return ResponseEntity.ok(funcionarioAtualizado)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Int): ResponseEntity<Void> {
        funcionariosService.deleteFuncionario(id)
        return ResponseEntity.noContent().build()
    }
}