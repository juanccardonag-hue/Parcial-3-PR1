package co.edu.uniquindio;
import java.util.List;
import java.util.ArrayList;

public class Empresa {
    private  String nit;
    private String nombre;
    private List<Aportante> aportantes;
    private List<Entrega> entregas;

    public Empresa(String nit, String nombre, List<Aportante> aportantes, List<Entrega> entregas) {
        this.nit = nit;
        this.nombre = nombre;
        this.aportantes = new ArrayList<>();
        this.entregas = new ArrayList<>();
    }

    public String getNit() {return nit;}
    public void setNit(String nit) {this.nit = nit;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public List<Aportante> getAportantes() {return aportantes;}
    public void setAportantes(List<Aportante> aportantes) {this.aportantes = aportantes;}

    public List<Entrega> getEntregas() {return entregas;}
    public void setEntregas(List<Entrega> entregas) {this.entregas = entregas;}
}
