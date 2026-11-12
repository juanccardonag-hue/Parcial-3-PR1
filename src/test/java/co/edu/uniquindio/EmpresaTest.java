package co.edu.uniquindio;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.logging.Logger;

class EmpresaTest {

    private Empresa empresa;
    private Aportante aportante;

    private static final Logger LOG = Logger.getLogger(EmpresaTest.class.getName());

    @BeforeEach
    void crearObjetosClases() {
        empresa = new Empresa("12N-1", "EcoRuta");
        aportante = empresa.getAportantes().get(0);
    }

    // Registrar entrega válida y rechazar códigos duplicados o aportantes desconocidos
    @Test
    @DisplayName("Prueba: Registro de Entrega y Validaciones")
    void testRegistrarEntregaYValidaciones() {
        LOG.info("Iniciando prueba: Registro de Entrega y Validaciones");

        boolean creada = empresa.registrarEntrega("E01", LocalDate.of(2026, 11, 12), aportante.cedula());
        Entrega entrega = empresa.buscarEntrega("E01");

        boolean repiteCodigo = empresa.registrarEntrega("E01", LocalDate.of(2026, 11, 12), aportante.cedula());
        boolean aportanteInvalido = empresa.registrarEntrega("E20", LocalDate.of(2026, 11, 12), "999999");

        assertTrue(creada);
        assertNotNull(entrega);
        assertEquals(LocalDate.of(2026, 11, 12), entrega.getFecha());
        assertEquals(EstadoEntrega.ABIERTA, entrega.getEstado());
        assertEquals(0, entrega.getLotes().size());
        assertNull(entrega.getComprobante());
        assertFalse(repiteCodigo);
        assertFalse(aportanteInvalido);

        LOG.info("Finalizando prueba: Registro de Entrega y Validaciones");
    }
}