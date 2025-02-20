package org.exercicio.services;

import org.exercicio.dao.TarefaDAO;
import org.exercicio.model.Tarefa;
import java.util.List;

public class TarefaService {
    private TarefaDAO tarefaDAO = new TarefaDAO();

    public void criarTarefa(Tarefa tarefa) {
        tarefaDAO.criar(tarefa);
    }

    public List<Tarefa> listarTodasTarefas() {
        return tarefaDAO.listarTodas();
    }

    public boolean atualizarStatusTarefa(int id, String novoStatus) {
        if (!novoStatus.matches("PENDENTE|EM_ANDAMENTO|CONCLUÍDA")) {
            throw new IllegalArgumentException("Status inválido!");
        }
        return tarefaDAO.atualizarStatus(id, novoStatus);
    }

    public boolean deletarTarefa(int id) {
        return tarefaDAO.deletar(id);
    }
}
