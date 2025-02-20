import org.exercicio.model.Tarefa;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TarefaTest {

    @Test
    void testGettersAndSetters() {
        Tarefa tarefa = new Tarefa();
        LocalDateTime now = LocalDateTime.now();

        tarefa.setId(1);
        tarefa.setTitulo("Estudar JUnit");
        tarefa.setDescricao("Criar testes unitários");
        tarefa.setDataCriacao(now);
        tarefa.setStatus("PENDENTE");

        assertEquals(1, tarefa.getId());
        assertEquals("Estudar JUnit", tarefa.getTitulo());
        assertEquals("Criar testes unitários", tarefa.getDescricao());
        assertEquals(now, tarefa.getDataCriacao());
        assertEquals("PENDENTE", tarefa.getStatus());
    }
}