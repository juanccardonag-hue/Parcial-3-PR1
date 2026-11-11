package co.edu.uniquindio;

public class ResiduoElectronico extends Lote implements Etiquetable {
    private int cantidadEquipos;

    public ResiduoElectronico(String codigo, String descripcion, int pesoKg, int cantidadEquipos) {
        super(codigo, descripcion, pesoKg);
        this.cantidadEquipos = cantidadEquipos;
    }

    public int getCantidadEquipos() {return cantidadEquipos;}
    public void setCantidadEquipos(int cantidadEquipos) {this.cantidadEquipos = cantidadEquipos;}

    @Override
    public double calcularValor(){
        return (pesoKg * 1400.0) + (cantidadEquipos * 800.0);
    }

    @Override
    public String generarEtiqueta() {
        return "Lote Electrónico [Código: " + codigo + ", Equipos completos: " + cantidadEquipos + "]";
    }
}



