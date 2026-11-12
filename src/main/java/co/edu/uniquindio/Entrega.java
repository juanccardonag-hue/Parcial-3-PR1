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
    public Lote buscarLote(String codigoLote) {
        if (codigoLote == null || this.lotes == null) {
            return null;
        }
        for (Lote l : lotes) {
            if (l.getCodigo().trim().equalsIgnoreCase(codigoLote.trim())) {
                return l;
            }
        }
        return null;
    }

    public boolean agregarLote(Lote nuevoLote) {
        if (this.estado != EstadoEntrega.ABIERTA) {
            return false;
        }
        if (nuevoLote == null || nuevoLote.getPesoKg() <= 0) {
            return false;
        }
        if (buscarLote(nuevoLote.getCodigo()) != null) {
            return false;
        }
        return lotes.add(nuevoLote);
    }

    public boolean aceptarEntrega() {
        if (this.estado != EstadoEntrega.ABIERTA || lotes.isEmpty()) {
            return false;
        }
        this.estado = EstadoEntrega.ACEPTADA;
        int pesoTotal = calcularPesoTotal();
        double valorTotal = calcularValorTotal();
        this.comprobante = new Comprobante(this.codigo, this.aportante.cedula(), pesoTotal, valorTotal);
        return true;
    }

    public int calcularPesoTotal() {
        int total = 0;
        for (Lote l : lotes) {
            total += l.getPesoKg();
        }
        return total;
    }

    public double calcularValorTotal() {
        double total = 0.0;
        for (Lote l : lotes) {
            total += l.calcularValor();
        }
        return total;
    }

    public List<String> obtenerEtiquetas() {
        List<String> etiquetas = new ArrayList<>();
        for (Lote l : lotes) {
            if (l instanceof Etiquetable) {
                Etiquetable e = (Etiquetable) l;
                etiquetas.add(e.generarEtiqueta());
            }
        }
        return etiquetas;
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