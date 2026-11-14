# Sistema de Controle de Biblioteca

Projeto desenvolvido como entrega final da formação, consolidando os conceitos de **Programação Orientada a Objetos (Java)** e **Banco de Dados Relacional (PostgreSQL)**.

---

## 🛠️ Tecnologias Utilizadas

* **Java (JDK 17+):** Abstração, Herança, Encapsulamento e Polimorfismo.
* **PostgreSQL 17:** Modelagem relacional, restrições de integridade (`CHECK`, `FOREIGN KEY`), agrupamentos e junções (`JOIN`).

---

## 📂 Estrutura do Projeto

### 1. Aplicação em Java (POO)
* `ItemBiblioteca.java`: Classe abstrata base para os materiais do acervo, definindo contratos de prazo e multa diária.
* `Livro.java`: Subclasse com prazo de 14 dias e multa de R$ 0,50/dia.
* `Revista.java`: Subclasse com prazo de 7 dias e multa de R$ 1,00/dia.
* `DVD.java`: Subclasse adicionada para validar a extensibilidade do sistema sem alterar a lógica existente (prazo de 3 dias e multa de R$ 2,00/dia).
* `Usuario.java`: Classe abstrata base para leitores, controlando a quantidade de itens emprestados.
* `Aluno.java`: Subclasse com limite máximo de 3 itens simultâneos.
* `Professor.java`: Subclasse com limite máximo de 5 itens simultâneos.
* `Biblioteca.java`: Classe gerenciadora que controla o acervo e os empréstimos usando arrays de tamanho fixo e polimorfismo.
* `Main.java`: Classe principal com o cenário de testes (cadastros, empréstimos aprovados, bloqueio por limite atingido e devolução).

### 2. Scripts em PostgreSQL
* `Biblioteca.sql`: Script de criação das tabelas (`item`, `usuario`, `emprestimo`) com `GENERATED ALWAYS AS IDENTITY`, restrições `CHECK` e inserção dos dados de teste.
* `ConsultaBiblioteca.sql`: Consultas SQL para listagem do acervo, empréstimos em aberto, soma de multas acumuladas por usuário (`GROUP BY`) e itens nunca emprestados (`LEFT JOIN`).

---

## 🚀 Como Executar

### Parte 1: Java
1. Clone este repositório ou abra a pasta no **VS Code** (com o *Extension Pack for Java* instalado).
2. Abra o arquivo `Main.java` e clique em **Run**.

### Parte 2: PostgreSQL
1. Abra o **pgAdmin** e crie um banco de dados (ex: `Biblioteca`).
2. Abra a *Query Tool*, execute primeiro o conteúdo de `Biblioteca.sql` para criar e popular as tabelas.
3. Em seguida, execute as consultas do arquivo `ConsultaBiblioteca.sql`.
