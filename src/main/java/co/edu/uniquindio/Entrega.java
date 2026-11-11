package co.edu.uniquindio;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Entrega {
    private String codigo;
    private LocalDate fecha;
    private Aportante aportante;
    private  EstadoEntrega estado;
    private List<Lote> lotes;
    private Comprobante comprobante;

    public Entrega(String codigo, LocalDate fecha, Aportante aportante, EstadoEntrega estado, List<Lote> lotes, Comprobante comprobante) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.aportante = aportante;
        this.estado = estado;
        this.lotes = lotes;
        this.comprobante = comprobante;
    }

    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}

    public LocalDate getFecha() {return fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public Aportante getAportante() {return aportante;}
    public void setAportante(Aportante aportante) {this.aportante = aportante;}

    public EstadoEntrega getEstado() {return estado;}
    public void setEstado(EstadoEntrega estado) {this.estado = estado;}

    public List<Lote> getLotes() {return lotes;}
    public void setLotes(List<Lote> lotes) {this.lotes = lotes;}

    public Comprobante getComprobante() {return comprobante;}
    public void setComprobante(Comprobante comprobante) {this.comprobante = comprobante;}
}
