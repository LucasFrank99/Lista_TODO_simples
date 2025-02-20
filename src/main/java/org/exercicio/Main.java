package org.exercicio;

import org.exercicio.model.Tarefa;
import org.exercicio.services.TarefaService;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static TarefaService service = new TarefaService();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- TODO LIST ---");
        System.out.println("1. Nova Tarefa");
        System.out.println("2. Listar Tarefas");
        System.out.println("3. Atualizar Status");
        System.out.println("4. Deletar Tarefa");
        System.out.println("5. Sair");
        System.out.print("Escolha: ");
    }

    private static int lerOpcao() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        } finally {
            scanner.nextLine(); // Limpa o buffer
        }
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> criarTarefa();
            case 2 -> listarTarefas();
            case 3 -> atualizarStatus();
            case 4 -> deletarTarefa();
            case 5 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private static void criarTarefa() {
        Tarefa tarefa = new Tarefa();

        System.out.print("\nTítulo: ");
        tarefa.setTitulo(scanner.nextLine());

        System.out.print("Descrição: ");
        tarefa.setDescricao(scanner.nextLine());

        service.criarTarefa(tarefa);
        System.out.println(" Tarefa criada com ID: " + tarefa.getId());
    }

    private static void listarTarefas() {
        List<Tarefa> tarefas = service.listarTodasTarefas();

        if (tarefas.isEmpty()) {
            System.out.println("\nNenhuma tarefa encontrada.");
            return;
        }

        System.out.println("\n--- LISTA DE TAREFAS ---");
        tarefas.forEach(t -> {
            String dataFormatada = t.getDataCriacao().format(formatter);
            System.out.printf(
                    "ID: %d | Título: %s | Status: %s | Criada em: %s%n",
                    t.getId(), t.getTitulo(), t.getStatus(), dataFormatada
            );
        });
    }

    private static void atualizarStatus() {
        try {
            System.out.print("\nID da tarefa: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Novo status (PENDENTE, EM_ANDAMENTO, CONCLUÍDA): ");
            String novoStatus = scanner.nextLine().toUpperCase();

            if (!List.of("PENDENTE", "EM_ANDAMENTO", "CONCLUÍDA").contains(novoStatus)) {
                System.out.println(" Status inválido!");
                return;
            }

            boolean sucesso = service.atualizarStatusTarefa(id, novoStatus);
            System.out.println(sucesso ? " Status atualizado!" : " Tarefa não encontrada.");

        } catch (NumberFormatException e) {
            System.out.println(" ID deve ser um número!");
        }
    }

    private static void deletarTarefa() {
        try {
            System.out.print("\nID da tarefa para deletar: ");
            int id = Integer.parseInt(scanner.nextLine());

            boolean sucesso = service.deletarTarefa(id);
            System.out.println(sucesso ? " Tarefa deletada!" : " Tarefa não encontrada.");

        } catch (NumberFormatException e) {
            System.out.println(" ID deve ser um número!");
        }
    }
}