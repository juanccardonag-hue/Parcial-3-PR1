package co.edu.uniquindio;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
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
        entrega = new Entrega("E02", aportante);

        lotes = new ArrayList<>();
        lotes.add(new PapelCarton("L01", "Papel Clasificado", 10, true));
        lotes.add(new Metal("L11", "Aluminio", 5, TipoMetal.ALUMINIO));
        lotes.add(new ResiduoElectronico("L12", "Electrónicos", 4, 2));
    }

    //Agregar lote válido y rechazar códigos duplicados o peso menor o igual a cero
    @Test
    @DisplayName("Prueba: Validación de Lotes al Agregar")
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
}