public class DVD extends ItemBiblioteca {
    public DVD(String codigo, String titulo) {
        super(codigo, titulo);
    }

    @Override
    public int getPrazoDias() {
        return 3;
    }

    @Override
    public double getMultaDiaria() {
        return 2.00;
    }
}
