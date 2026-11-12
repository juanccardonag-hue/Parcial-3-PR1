package co.edu.uniquindio;

import javax.swing.JOptionPane;
import java.util.List;

public class Main {
    private static Empresa ecoRuta = new Empresa("124n","EcoRuta");

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
                    + "Digite el número de la opción:";

            String opcion = JOptionPane.showInputDialog(null, menu, "EcoRuta", JOptionPane.QUESTION_MESSAGE);

            if (opcion == null) {
                break;
            }

            switch (opcion.trim()) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "¡Programa finalizado!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Ingrese un número del 1 al 8.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }


}
