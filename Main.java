public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(10, 5);

        // Cadastro de itens (incluindo o novo tipo DVD sem alterar a Biblioteca)
        ItemBiblioteca l1 = new Livro("L01", "Clean Code");
        ItemBiblioteca l2 = new Livro("L02", "O Senhor dos Aneis");
        ItemBiblioteca r1 = new Revista("R01", "National Geographic");
        ItemBiblioteca d1 = new DVD("D01", "Interestelar");

        biblioteca.cadastrarItem(l1);
        biblioteca.cadastrarItem(l2);
        biblioteca.cadastrarItem(r1);
        biblioteca.cadastrarItem(d1);

        // Cadastro de usuários
        Usuario aluno = new Aluno("Lucas (Aluno)");
        Usuario prof = new Professor("Dra. Helena (Professora)");

        biblioteca.cadastrarUsuario(aluno);
        biblioteca.cadastrarUsuario(prof);

        biblioteca.listarAcervo();

        // Empréstimos bem-sucedidos (Aluno pode até 3)
        System.out.println("--- Realizando Emprestimos ---");
        biblioteca.emprestar(aluno, l1); // 1º item
        biblioteca.emprestar(aluno, r1); // 2º item
        biblioteca.emprestar(aluno, d1); // 3º item (limite atingido)

        // Empréstimo recusado por limite atingido
        biblioteca.emprestar(aluno, l2); // 4º item -> Deve falhar

        // Empréstimo bem-sucedido para professora
        biblioteca.emprestar(prof, l2);

        biblioteca.listarAcervo();

        // Teste de devolução e novo empréstimo
        System.out.println("--- Realizando Devolução ---");
        biblioteca.devolver(aluno, r1);
        biblioteca.emprestar(aluno, r1); // Agora funciona pois liberou 1 vaga
    }
}
