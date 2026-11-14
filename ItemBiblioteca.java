public abstract class ItemBiblioteca {
    private String codigo;
    private String titulo;
    private boolean disponivel;

    public ItemBiblioteca(String codigo, String titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponivel = true; // Todo item nasce disponível
    }

    public abstract int getPrazoDias();
    public abstract double getMultaDiaria();

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    // Sem modificador 'public': protege o encapsulamento contra chamadas externas
    void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        String status = disponivel ? "Disponível" : "Emprestado";
        return String.format("[%s] %s | Status: %s | Prazo: %d dias | Multa: R$ %.2f/dia",
                codigo, titulo, status, getPrazoDias(), getMultaDiaria());
    }
}
