package co.edu.uniquindio;
import java.util.List;
import java.util.ArrayList;

public class Empresa {
    private  String nit;
    private String nombre;
    private List<Aportante> aportantes;
    private List<Entrega> entregas;

    public Empresa(String nit, String nombre) {
        this.nit = nit;
        this.nombre = nombre;
        this.aportantes = new ArrayList<>();
        this.entregas = new ArrayList<>();

        // Datos quemados para realizar pruebas desde el menú interactivo
        this.aportantes.add(new Aportante("109555", "Juan Cardona", "juan@mail.com"));
        this.aportantes.add(new Aportante("109245", "Mariana Mendez", "mariana@mail.com"));
    }

    public String getNit() {return nit;}
    public void setNit(String nit) {this.nit = nit;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public List<Aportante> getAportantes() {return aportantes;}
    public void setAportantes(List<Aportante> aportantes) {this.aportantes = aportantes;}

    public List<Entrega> getEntregas() {return entregas;}
    public void setEntregas(List<Entrega> entregas) {this.entregas = entregas;}

    public Aportante buscarAportante(String id) {
        if (id == null) return null;
        for (Aportante a : aportantes) {
            if (a.cedula().trim().equalsIgnoreCase(id.trim())) {
                return a;
            }
        }
        return null;
    }

    public boolean registrarAportante(String id, String nombre, String correo) {
        if (id == null || id.trim().isEmpty() || buscarAportante(id) != null) {
            return false;
        }
        aportantes.add(new Aportante(id.trim(), nombre.trim(), correo.trim()));
        return true;
    }

    public Entrega buscarEntrega(String codigo) {
        if (codigo == null) return null;
        for (Entrega e : entregas) {
            if (e.getCodigo().trim().equalsIgnoreCase(codigo.trim())) {
                return e;
            }
        }
        return null;
    }

    public boolean registrarEntrega(String codigo, String idAportante) {
        if (codigo == null || codigo.trim().isEmpty() || buscarEntrega(codigo) != null) {
            return false;
        }
        Aportante ap = buscarAportante(idAportante);
        if (ap == null) {
            return false;
        }
        entregas.add(new Entrega(codigo.trim(), ap));
        return true;
    }

    public double consultarValorAcumuladoAportante(String idAportante) {
        double acumulado = 0.0;
        if (idAportante == null) return acumulado;
        for (Entrega e : entregas) {
            if (e.getAportante().cedula().trim().equalsIgnoreCase(idAportante.trim())
                    && e.getEstado() == EstadoEntrega.ACEPTADA) {
                if (e.getComprobante() != null) {
                    acumulado += e.getComprobante().valorTotal();
                }
            }
        }
        return acumulado;
    }
}