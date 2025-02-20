package org.exercicio.dao;

import org.exercicio.model.Tarefa;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    // Conecta ao banco de dados
    private Connection getConnection() throws SQLException {
    String url = "jdbc:postgresql://localhost:5432/todo_list";
    String user = "postgres";
    String password = "1234";
    return DriverManager.getConnection(url,user,password);
    }


    //Cria e insere uma nova tarefa no banco de dados
    public void criar(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, status) VALUES (?, ?, ?)";


        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        )
        {

            ps.setString(1, tarefa.getTitulo());
            ps.setString(2, tarefa.getDescricao());
            ps.setString(3,"PENDENTE");

            int linhasModificadas = ps.executeUpdate();

            if (linhasModificadas == 0) {
                throw new SQLException("Falha na criação da tarefa");

            }

            // Recupera o ID gerado pelo banco
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    tarefa.setId(rs.getInt(1));  // Atualiza o ID no objeto
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tarefa> listarTodas() {
        List<Tarefa> tarefaList = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setDataCriacao(rs.getObject("data_criacao", LocalDateTime.class));
                tarefa.setStatus(rs.getString("status"));

                tarefaList.add(tarefa);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tarefaList;
    }


    //Exclui uma tarefa do banco de dados
    public boolean deletar(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Atualiza o status de uma tarefa
    public boolean atualizarStatus(int id, String novoStatus) {
        String sql = "UPDATE tarefas SET status = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, novoStatus);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




}
