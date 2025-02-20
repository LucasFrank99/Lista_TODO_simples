import org.exercicio.dao.TarefaDAO;
import org.exercicio.model.Tarefa;
import org.exercicio.services.TarefaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaDAO dao;

    @InjectMocks
    private TarefaService service;

    @Test
    void testCriarTarefa() {
        Tarefa tarefa = new Tarefa();
        service.criarTarefa(tarefa);
        verify(dao, times(1)).criar(tarefa);
    }

    @Test
     void testAtualizarStatusValido() {
        when(dao.atualizarStatus(1, "CONCLUÍDA")).thenReturn(true);

        boolean sucesso = service.atualizarStatusTarefa(1, "CONCLUÍDA");
        assertTrue(sucesso);
    }

    @Test
    void testAtualizarStatusInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.atualizarStatusTarefa(1, "INVALIDO");
        });
    }
}