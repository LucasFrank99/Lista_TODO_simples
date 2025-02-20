import org.exercicio.Main;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void testFluxoCompleto() {
        // Simula entrada do usuário
        String input = "1\nTítulo Teste\nDescrição Teste\n2\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Captura saída do console
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Tarefa criada com ID"));
        assertTrue(output.contains("Título: Título Teste"));
    }
}