package school.sptech.ApiIndividual.service

import org.springframework.stereotype.Service
import school.sptech.ApiIndividual.dominio.Funcionarios
import school.sptech.ApiIndividual.dto.FuncionarioDto
import school.sptech.ApiIndividual.repository.FuncionariosRepository

@Service
class FuncionariosService(
    private val funcionarioRepository: FuncionariosRepository
) {

    fun getAllFuncionarios(): List<Funcionarios> = funcionarioRepository.findAll()

    fun getFuncionarioById(id: Int): Funcionarios = funcionarioRepository.findById(id).orElseThrow {
        IllegalArgumentException("Funcionário não encontrado com ID: $id")
    }

    fun createFuncionario(funcionarioDTO: FuncionarioDto): Funcionarios {
        val funcionario = Funcionarios(
            nome = funcionarioDTO.nome,
            cargo = funcionarioDTO.cargo,
            salario = funcionarioDTO.salario
        )
        return funcionarioRepository.save(funcionario)
    }

    fun updateFuncionario(id: Int, funcionarioDTO: FuncionarioDto): Funcionarios {
        val funcionarioExistente = getFuncionarioById(id)
        funcionarioExistente.nome = funcionarioDTO.nome
        funcionarioExistente.cargo = funcionarioDTO.cargo
        funcionarioExistente.salario = funcionarioDTO.salario
        return funcionarioRepository.save(funcionarioExistente)
    }

    fun deleteFuncionario(id: Int) {
        val funcionario = getFuncionarioById(id)
        funcionarioRepository.delete(funcionario)
    }
}