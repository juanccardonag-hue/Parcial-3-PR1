package co.edu.uniquindio;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class EntregaTest {

    private Aportante aportante;
    private Entrega entrega;
    private List<Lote> lotes;

    private static final Logger LOG = Logger.getLogger(EntregaTest.class.getName());

    @BeforeEach
    void crearObjetosClases() {
        aportante = new Aportante("109555", "Juan Cardona", "juan@mail.com");
        entrega = new Entrega("E02", LocalDate.of(2026, 11, 12), aportante);

        lotes = new ArrayList<>();
        lotes.add(new PapelCarton("L01", "Papel Clasificado", 5, true));
        lotes.add(new Metal("L11", "Aluminio", 5, TipoMetal.ALUMINIO));
        lotes.add(new ResiduoElectronico("L12", "Electrónicos", 4, 2));
        lotes.add(new PapelCarton("L20", "Papel Clasificado", 10, true));
    }

    // Validación de lotes sin repetir codigo ni peso <=0
    @Test
    @DisplayName("Prueba : Validación de Lotes al Agregar")
    void testValidacionLotes() {
        LOG.info("Iniciando prueba: Validación de Lotes al Agregar");

        boolean agregadoValido = entrega.agregarLote(lotes.get(0));
        boolean agregadoDuplicado = entrega.agregarLote(new PapelCarton("L01", "Otro papel", 3, false));
        boolean agregadoPesoCero = entrega.agregarLote(new PapelCarton("L99", "Papel cero", 0, false));

        assertTrue(agregadoValido);
        assertFalse(agregadoDuplicado);
        assertFalse(agregadoPesoCero);
        assertEquals(1, entrega.getLotes().size());

        LOG.info("Finalizando prueba: Validación de Lotes al Agregar");
    }

    // Rechazo de una entrega vacía
    @Test
    @DisplayName("Prueba : Rechazo de Entrega Vacía")
    void testRechazoEntregaVacia() {
        LOG.info("Iniciando prueba: Rechazo de Entrega Vacía");

        boolean aceptada = entrega.aceptarEntrega();

        assertFalse(aceptada);
        assertEquals(EstadoEntrega.ABIERTA, entrega.getEstado());
        assertNull(entrega.getComprobante());

        LOG.info("Finalizando prueba: Rechazo de Entrega Vacía");
    }

    // Aceptación y cálculo con los tres tipos de lote
    @Test
    @DisplayName("Prueba : Aceptación y Cálculo de Tres Lotes")
    void testAceptacionYCalculoTresLotes() {
        LOG.info("Iniciando prueba: Aceptación y Cálculo de Tres Lotes");

        entrega.agregarLote(lotes.get(3));
        entrega.agregarLote(lotes.get(1));
        entrega.agregarLote(lotes.get(2));

        assertEquals(6000.0, lotes.get(3).calcularValor());
        assertEquals(11000.0, lotes.get(1).calcularValor());
        assertEquals(7200.0, lotes.get(2).calcularValor());

        boolean aceptada = entrega.aceptarEntrega();

        assertTrue(aceptada);
        assertEquals(EstadoEntrega.ACEPTADA, entrega.getEstado());
        assertNotNull(entrega.getComprobante());
        assertEquals(19, entrega.getComprobante().pesoTotal());
        assertEquals(24200.0, entrega.getComprobante().valorTotal());

        LOG.info("Finalizando prueba: Aceptación y Cálculo de Tres Lotes");
    }

    // Consulta de etiquetas de la entrega aceptada
    @Test
    @DisplayName("Prueba 5: Consulta de Etiquetas")
    void testConsultaEtiquetas() {
        LOG.info("Iniciando prueba: Consulta de Etiquetas");

        entrega.agregarLote(lotes.get(3));
        entrega.agregarLote(lotes.get(1));
        entrega.agregarLote(lotes.get(2));
        entrega.aceptarEntrega();

        List<String> etiquetas = entrega.obtenerEtiquetas();

        assertEquals(2, etiquetas.size());
        assertTrue(etiquetas.get(0).contains("L11") && etiquetas.get(0).contains("ALUMINIO"));
        assertTrue(etiquetas.get(1).contains("L12") && etiquetas.get(1).contains("2"));

        LOG.info("Finalizando prueba: Consulta de Etiquetas");
    }
}