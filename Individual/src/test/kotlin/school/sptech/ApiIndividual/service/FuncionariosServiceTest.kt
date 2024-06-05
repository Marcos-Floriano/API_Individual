package school.sptech.ApiIndividual.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import school.sptech.ApiIndividual.dominio.Funcionarios
import school.sptech.ApiIndividual.dto.FuncionarioDto
import school.sptech.ApiIndividual.repository.FuncionariosRepository
import java.util.*

@SpringBootTest
class FuncionariosServiceTest {

    val service = FuncionariosService(mock(FuncionariosRepository::class.java))
    private lateinit var funcionarioRepository: FuncionariosRepository

    @BeforeEach
    fun setup() {
        funcionarioRepository = mock(FuncionariosRepository::class.java)
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
        `when`(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario))

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