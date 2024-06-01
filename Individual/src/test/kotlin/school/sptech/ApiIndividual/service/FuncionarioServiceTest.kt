package school.sptech.ApiIndividual.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.example.school.sptech.ApiIndividual.dominio.Funcionarios
import org.example.school.sptech.ApiIndividual.dto.FuncionarioDto
import org.example.school.sptech.ApiIndividual.service.FuncionarioService
import school.sptech.projetoestoque.repository.FuncionarioRepository
import java.util.*

@SpringBootTest
class FuncionarioServiceTest {

    val service = FuncionarioService(mock(FuncionarioRepository::class.java))
    private lateinit var funcionarioRepository: FuncionarioRepository

    @BeforeEach
    fun setup() {
        funcionarioRepository = mock(FuncionarioRepository::class.java)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/funcionarios.csv"], numLinesToSkip = 1)
    fun `deve listar todos os funcionarios`(id: Int, nome: String, cargo: String, salario: Double) {
        val funcionario = Funcionarios(id = id, nome = nome, cargo = cargo, salario = salario)
        `when`(funcionarioRepository.findAll()).thenReturn(listOf(funcionario))

        val result = service.getAllFuncionarios()
        assertFalse(result.isEmpty())
        assertEquals(1, result.size)
        assertEquals(nome, result[0].nome)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/funcionarios.csv"], numLinesToSkip = 1)
    fun `deve buscar funcionario por id`(id: Int, nome: String, cargo: String, salario: Double) {
        val funcionario = Funcionarios(id = id, nome = nome, cargo = cargo, salario = salario)
        `when`(funcionarioRepository.findById(id.toLong())).thenReturn(Optional.of(funcionario))

        val encontrado = service.getFuncionarioById(id)
        assertEquals(nome, encontrado.nome)
    }

    @Test
    fun `deve criar um novo funcionario`() {
        val funcionarioDTO = FuncionarioDto(nome = "Carlos Lima", cargo = "Tratador de Animais", salario = 3200.0)
        val funcionario = Funcionarios(nome = funcionarioDTO.nome, cargo = funcionarioDTO.cargo, salario = funcionarioDTO.salario)
        `when`(funcionarioRepository.save(any(Funcionarios::class.java))).thenReturn(funcionario)

        val salvo = service.createFuncionario(funcionarioDTO)
        assertEquals("Carlos Lima", salvo.nome)
    }
}