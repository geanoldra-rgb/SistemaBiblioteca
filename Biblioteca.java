public class Biblioteca {
    private ItemBiblioteca[] acervo;
    private Usuario[] usuarios;
    private int totalItens;
    private int totalUsuarios;

    public Biblioteca(int capacidadeItens, int capacidadeUsuarios) {
        this.acervo = new ItemBiblioteca[capacidadeItens];
        this.usuarios = new Usuario[capacidadeUsuarios];
        this.totalItens = 0;
        this.totalUsuarios = 0;
    }

    public boolean cadastrarItem(ItemBiblioteca item) {
        if (totalItens < acervo.length) {
            acervo[totalItens++] = item;
            return true;
        }
        System.out.println("Erro: Capacidade máxima do acervo atingida.");
        return false;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (totalUsuarios < usuarios.length) {
            usuarios[totalUsuarios++] = usuario;
            return true;
        }
        System.out.println("Erro: Capacidade máxima de usuários atingida.");
        return false;
    }

    public boolean emprestar(Usuario usuario, ItemBiblioteca item) {
        if (!item.isDisponivel()) {
            System.out.printf("Recusado: O item '%s' já está emprestado.%n", item.getTitulo());
            return false;
        }
        if (!usuario.podeEmprestar()) {
            System.out.printf("Recusado: %s atingiu o limite de %d item(ns) simultâneo(s).%n",
                    usuario.getNome(), usuario.getLimiteItens());
            return false;
        }

        item.setDisponivel(false);
        usuario.registrarEmprestimo();
        System.out.printf("Sucesso: '%s' emprestado para %s. Devolução em %d dias.%n",
                item.getTitulo(), usuario.getNome(), item.getPrazoDias());
        return true;
    }

    public boolean devolver(Usuario usuario, ItemBiblioteca item) {
        if (item.isDisponivel()) {
            System.out.printf("Aviso: O item '%s' já consta como disponível no acervo.%n", item.getTitulo());
            return false;
        }

        item.setDisponivel(true);
        usuario.registrarDevolucao();
        System.out.printf("Devolvido: '%s' devolvido por %s.%n", item.getTitulo(), usuario.getNome());
        return true;
    }

    public void listarAcervo() {
        System.out.println("\n=== ACERVO DA BIBLIOTECA ===");
        for (int i = 0; i < totalItens; i++) {
            // Polimorfismo puro: nenhum 'if' ou 'instanceof' verificando o tipo
            System.out.println(acervo[i]);
        }
        System.out.println("============================\n");
    }
}
