package co.edu.uniquindio;

public class PapelCarton extends Lote {
    private boolean clasificado;

    public PapelCarton(String codigo, String descripcion, int pesoKg, boolean clasificado) {
        super(codigo, descripcion, pesoKg);
        this.clasificado = clasificado;
    }

    public boolean isClasificado() {
        return clasificado;
    }

    public void setClasificado(boolean clasificado) {
        this.clasificado = clasificado;
    }

    @Override
    public double calcularValor() {
        double tarifaBase = 500.0;
        double tarifaAdicional = 0.0;

        if (clasificado) {
            tarifaAdicional = 100.0;
        }
        return pesoKg * (tarifaBase + tarifaAdicional);
    }
}
