package co.edu.uniquindio;

import javax.swing.JOptionPane;
import java.util.List;

public class Main {
    private static Empresa ecoRuta = new Empresa("123n", "EcoRuta");

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            String menu = " . ݁₊ ⊹ . ݁ Menu ecoruta ݁ . ⊹ ₊ ݁.\n"
                    + "1. Registrar nuevo Aportante\n"
                    + "2. Registrar nueva Entrega\n"
                    + "3. Agregar lote a una Entrega\n"
                    + "4. Aceptar Entrega (Cerrar y Generar Comprobante)\n"
                    + "5. Consultar Etiquetas de una Entrega\n"
                    + "6. Consultar Valor Acumulado por Aportante\n"
                    + "7. Listar Aportantes y Entregas Registradas\n"
                    + "8. Salir\n\n"
                    + "Digite el número de la opción a realizar:";

            String opcion = JOptionPane.showInputDialog(null, menu, "EcoRuta", JOptionPane.QUESTION_MESSAGE);

            if (opcion == null) {
                break;
            }

            switch (opcion.trim()) {
                case "1":
                    registrarAportanteMenu();
                    break;
                case "2":
                    registrarEntregaMenu();
                    break;
                case "3":
                    agregarLoteMenu();
                    break;
                case "4":
                    aceptarEntregaMenu();
                    break;
                case "5":
                    consultarEtiquetasMenu();
                    break;
                case "6":
                    consultarAcumuladoMenu();
                    break;
                case "7":
                    listarRegistrosMenu();
                    break;
                case "8":
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "°¡Programa finalizado!°");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "⚠ Opción no válida. Ingrese un número del 1 al 8 ⚠", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    private static void registrarAportanteMenu() {
        String id = JOptionPane.showInputDialog("Ingrese la cedula del aportante:");
        if (id == null || id.trim().isEmpty()) return;

        String nombre = JOptionPane.showInputDialog("Ingresa el nombre del aportante:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String correo = JOptionPane.showInputDialog("Ingresa el correo del aportante:");
        if (correo == null || correo.trim().isEmpty()) return;

        if (ecoRuta.registrarAportante(id, nombre, correo)) {
            JOptionPane.showMessageDialog(null, "Aportante registrado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "⚠ Error: La cédula ya existe o es inválida ⚠", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void registrarEntregaMenu() {
        String id = JOptionPane.showInputDialog("Ingresa cédula del aportante:");
        if (id == null || id.trim().isEmpty()) return;

        if (ecoRuta.buscarAportante(id) == null) {
            JOptionPane.showMessageDialog(null, "⚠ Error: El aportante '" + id + "' no existe. Regístrelo en la opción 1 ⚠", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String codigo = JOptionPane.showInputDialog("Ingrese el código de la nueva entrega");
        if (codigo == null || codigo.trim().isEmpty()) return;

        if (ecoRuta.registrarEntrega(codigo, id)) {
            JOptionPane.showMessageDialog(null, "Entrega '" + codigo.trim() + "' creada en estado ABIERTA.");
        } else {
            JOptionPane.showMessageDialog(null, "⚠ Error: El código de entrega '" + codigo + "' ya existe ⚠", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private static void agregarLoteMenu() {
        String codEntrega = JOptionPane.showInputDialog("Ingrese código de la entrega");
        if (codEntrega == null || codEntrega.trim().isEmpty()) return;

        Entrega entrega = ecoRuta.buscarEntrega(codEntrega);

        if (entrega == null) {
            JOptionPane.showMessageDialog(null, "⚠ Error: La entrega '" + codEntrega + "' no existe ⚠", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (entrega.getEstado() == EstadoEntrega.ACEPTADA) {
            JOptionPane.showMessageDialog(null, "⚠ Error: La entrega '" + codEntrega + "' ya fue ACEPTADA. No se pueden agregar más lotes ⚠", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String menuTipo = "Seleccione Tipo de Lote:\n"
                + "1. Papel y Cartón\n"
                + "2. Metal\n"
                + "3. Residuo Electrónico\n\n"
                + "Digite 1, 2 o 3:";

        String tipo = JOptionPane.showInputDialog(null, menuTipo, "Tipo Lote", JOptionPane.QUESTION_MESSAGE);
        if (tipo == null || tipo.trim().isEmpty()) return;

        String codLote = JOptionPane.showInputDialog("Ingrese Código del lote ");
        if (codLote == null || codLote.trim().isEmpty()) return;

        if (entrega.buscarLote(codLote) != null) {
            JOptionPane.showMessageDialog(null, "Error: El lote '" + codLote + "' ya existe en la entrega '" + codEntrega + "'. Use otro código.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String desc = JOptionPane.showInputDialog("Ingrese la descripción del lote:");
        if (desc == null || desc.trim().isEmpty()) return;

        String pesoStr = JOptionPane.showInputDialog("Ingrese el peso en Kg (entero mayor a 0):");
        if (pesoStr == null || pesoStr.trim().isEmpty()) return;

        int peso = 0;
        try {
            peso = Integer.parseInt(pesoStr.trim());
            if (peso <= 0) {
                JOptionPane.showMessageDialog(null, "⚠ Error: El peso debe ser mayor a 0 ⚠", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "⚠ Error: Debe ingresar un número entero ⚠", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Lote lote = null;

        switch (tipo.trim()) {
            case "1":
                int clas = JOptionPane.showConfirmDialog(null, "¿Está clasificado el papel/cartón?", "Clasificación", JOptionPane.YES_NO_OPTION);
                boolean estaClasificado = (clas == JOptionPane.YES_OPTION);
                lote = new PapelCarton(codLote, desc, peso, estaClasificado);
                break;
            case "2":
                String menuMetal = "Tipo de Metal:\n1. Aluminio\n2. Acero\n\nDigite 1 o 2:";
                String tmInput = JOptionPane.showInputDialog(null, menuMetal, "Metal", JOptionPane.QUESTION_MESSAGE);
                if (tmInput == null || tmInput.trim().isEmpty()) return;

                TipoMetal tm = null;
                if (tmInput.trim().equals("1")) {
                    tm = TipoMetal.ALUMINIO;
                } else if (tmInput.trim().equals("2")) {
                    tm = TipoMetal.ACERO;
                } else {
                    JOptionPane.showMessageDialog(null, "⚠ Error: Opción de metal inválida ⚠", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                lote = new Metal(codLote, desc, peso, tm);
                break;
            case "3":
                String eqStr = JOptionPane.showInputDialog("Cantidad de equipos completos:");
                if (eqStr == null || eqStr.trim().isEmpty()) return;
                int eq = 0;
                try {
                    eq = Integer.parseInt(eqStr.trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "⚠ Error: Cantidad de equipos inválida ⚠", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                lote = new ResiduoElectronico(codLote, desc, peso, eq);
                break;
            default:
                JOptionPane.showMessageDialog(null, "⚠ Tipo de lote inválido ⚠", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        if (lote != null && entrega.agregarLote(lote)) {
            JOptionPane.showMessageDialog(null, "¡Lote '" + codLote + "' agregado con éxito a la entrega '" + codEntrega + "'!\nValor del lote: $" + lote.calcularValor());
        } else {
            JOptionPane.showMessageDialog(null, "⚠ Error al agregar el lote ⚠", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void aceptarEntregaMenu() {
        String codEntrega = JOptionPane.showInputDialog("Ingrese el código de la entrega a aceptar:");
        if (codEntrega == null || codEntrega.trim().isEmpty()) return;

        Entrega entrega = ecoRuta.buscarEntrega(codEntrega);

        if (entrega == null) {
            JOptionPane.showMessageDialog(null, "⚠ Error: La entrega '" + codEntrega + "' no existe ⚠", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (entrega.getEstado() == EstadoEntrega.ACEPTADA) {
            JOptionPane.showMessageDialog(null, "⚠ Error: La entrega '" + codEntrega + "' ya está ACEPTADA ⚠", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (entrega.getLotes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠ Error: La entrega no tiene lotes. Agregue al menos un lote (Opción 3) antes de aceptarla ⚠", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (entrega.aceptarEntrega()) {
            Comprobante c = entrega.getComprobante();
            JOptionPane.showMessageDialog(null, "¡ENTREGA ACEPTADA CON ÉXITO!\n\n"
                    + "Comprobante Generado:\n"
                    + "❤ Código Entrega: " + c.codigoEntrega() + "\n"
                    + "❤ ID Aportante: " + c.idAportante() + "\n"
                    + "❤ Peso Total: " + c.pesoTotal() + " kg\n"
                    + "❤ Valor Total: $" + c.valorTotal());
        }
    }

    private static void consultarEtiquetasMenu() {
        String codEntrega = JOptionPane.showInputDialog("Ingrese el código de la entrega:");
        if (codEntrega == null || codEntrega.trim().isEmpty()) return;

        Entrega entrega = ecoRuta.buscarEntrega(codEntrega);

        if (entrega == null) {
            JOptionPane.showMessageDialog(null, "Error: Entrega no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<String> ets = entrega.obtenerEtiquetas();
        if (ets.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay lotes con etiquetas (Metales/Electrónicos) en esta entrega.");
        } else {
            String msg = "Etiquetas en la entrega '" + codEntrega + "':\n\n";
            for (String e : ets) {
                msg = msg + "• " + e + "\n";
            }
            JOptionPane.showMessageDialog(null, msg);
        }
    }

    private static void consultarAcumuladoMenu() {
        String id = JOptionPane.showInputDialog("Ingrese la cédula del aportante:");
        if (id == null || id.trim().isEmpty()) return;

        if (ecoRuta.buscarAportante(id) == null) {
            JOptionPane.showMessageDialog(null, "Error: Aportante con cédula '" + id + "' no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double total = ecoRuta.consultarValorAcumuladoAportante(id);
        JOptionPane.showMessageDialog(null, "Valor acumulado en entregas ACEPTADAS para la cédula" + id + ": $" + total);
    }

    private static void listarRegistrosMenu() {
        String mensage = "°°°APORTANTES REGISTRADOS °°°\n";
        for (Aportante a : ecoRuta.getAportantes()) {
            mensage = mensage + "• ID: " + a.cedula() + " | Nombre: " + a.nombre() + "\n";
        }

        mensage = mensage + "\n°°° ENTREGAS REGISTRADAS °°°\n";
        if (ecoRuta.getEntregas().isEmpty()) {
            mensage = mensage + "(Sin entregas)\n";
        } else {
            for (Entrega e : ecoRuta.getEntregas()) {
                mensage = mensage + "• Código: " + e.getCodigo() + " • Aportante: " + e.getAportante().cedula()
                        + " • Estado: " + e.getEstado() + " • Cant. Lotes: " + e.getLotes().size() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, mensage);
    }
}