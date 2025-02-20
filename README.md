# Sistema de Gerenciamento de Tarefas (TODO List) em Java

Um projeto simples para gerenciar tarefas via console, com integração a banco de dados e foco em práticas de desenvolvimento Java.

---

## 🚀 Visão Geral

O projeto permite:
- **Cadastrar** novas tarefas com título, descrição e status.
- **Listar** todas as tarefas armazenadas.
- **Atualizar** o status das tarefas (Pendente, Em Andamento, Concluída).
- **Excluir** tarefas existentes.
- Persistência de dados em banco relacional (PostgreSQL ou H2).

---

## 🛠️ Ferramentas Utilizadas

- **Linguagem:** Java 21
- **Banco de Dados:** 
  - PostgreSQL (produção) 
  - H2 (testes em memória)
- **Frameworks:**
  - Spring JDBC (para conexão com banco)
  - JUnit 5 + Mockito (testes unitários)
- **Gerenciamento de Dependências:** Maven
- **Ferramentas Adicionais:** Git, IntelliJ/Eclipse

---

## ⚙️ Configuração do Ambiente

### Pré-requisitos
- JDK 21 instalado
- Maven 3.8+
- PostgreSQL (opcional para testes locais)
- IDE de sua preferência


