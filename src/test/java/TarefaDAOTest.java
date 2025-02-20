import org.exercicio.dao.TarefaDAO;
import org.exercicio.model.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class TarefaDAOTest {
    private TarefaDAO dao;
    private Tarefa tarefa;

    @BeforeEach
    void setUp() throws SQLException {
        // Usando banco H2 em memória para testes
        criarTabelaTeste();
        dao = new TarefaDAO();
        tarefa = new Tarefa();
        tarefa.setTitulo("Teste DAO");
        tarefa.setDescricao("Descrição teste");
    }

    private void criarTabelaTeste() throws SQLException {
        try (var conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
             var stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE tarefas (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    titulo VARCHAR(100) NOT NULL,
                    descricao TEXT,
                    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    status VARCHAR(20) DEFAULT 'PENDENTE'
                )
            """);
        }
    }

    @Test
    void testCriarTarefa() {
        dao.criar(tarefa);
        assertTrue(tarefa.getId() > 0);
    }

    @Test
    void testListarTodas() {
        dao.criar(tarefa);
        assertEquals(1, dao.listarTodas().size());
    }

    @Test
    void testAtualizarStatus() {
        dao.criar(tarefa);
        boolean sucesso = dao.atualizarStatus(tarefa.getId(), "CONCLUÍDA");
        assertTrue(sucesso);
    }

    @Test
    void testDeletarTarefa() {
        dao.criar(tarefa);
        boolean sucesso = dao.deletar(tarefa.getId());
        assertTrue(sucesso);
        assertEquals(0, dao.listarTodas().size());
    }
}