package co.edu.uniquindio;

public class Metal extends Lote implements Etiquetable {
    private TipoMetal tipoMetal;

    public Metal(String codigo, String descripcion, int pesoKg, TipoMetal tipoMetal) {
        super(codigo, descripcion, pesoKg);
        this.tipoMetal = tipoMetal;
    }

    public TipoMetal getTipoMetal() {return tipoMetal;}
    public void setTipoMetal(TipoMetal tipoMetal) {this.tipoMetal = tipoMetal;}

    @Override
    public double calcularValor(){
        double tarifa;
        if (tipoMetal == TipoMetal.ALUMINIO) {
            tarifa = 2200.0;
        } else {
            tarifa = 900.0;
        }
        return pesoKg * tarifa;
    }
    @Override
    public String generarEtiqueta(){
        return "Lote Metal [Código: " + codigo + ", Tipo: " + tipoMetal + "]";
    }
}
