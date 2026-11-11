package co.edu.uniquindio;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Entrega {
    private String codigo;
    private LocalDate fecha;
    private Aportante aportante;
    private  EstadoEntrega estado;
    private List<Lote> lotes = new ArrayList<>();
    private Comprobante comprobante;

    public Entrega(String codigo, Aportante aportante) {
        this.codigo = codigo;
        this.fecha = LocalDate.now();
        this.aportante = aportante;
        this.estado = EstadoEntrega.ABIERTA;
        this.lotes = new ArrayList<>();
        this.comprobante = null;
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