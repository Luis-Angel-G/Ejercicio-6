import java.io.IOException;
import java.util.Scanner;

/**
 * Clase DriverProgram que contiene el método main para ejecutar el sistema.
 * <p>
 * Esta clase proporciona una interfaz de menú para registrar pedidos y generar reportes mensuales.
 * Los usuarios pueden ingresar la capacidad y la distancia de un pedido,
 * confirmar el registro del mismo, y ver un reporte de los costos mensuales.
 * </p>
 * 
 * @author Luis Angel Girón Arévalo
 * @date 28/10/2024
 */
public class DriverProgram {
    /**
     * Método principal que inicia la ejecución del programa.
     * 
     * @param args Argumentos de línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        Gestion gestion = new Gestion();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menu de Opciones:");
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Generar Reporte Mensual");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Ingrese capacidad (kg): ");
                        double capacidad = scanner.nextDouble();
                        System.out.print("Ingrese distancia (km): ");
                        double distancia = scanner.nextDouble();

                        double costoTotal = gestion.registrarPedido(capacidad, distancia);
                        System.out.println("Costo total: $" + costoTotal);

                        System.out.print("¿Desea confirmar el pedido? (s/n): ");
                        String confirmacion = scanner.next();

                        if (confirmacion.equalsIgnoreCase("s")) {
                            Transporte transporte = gestion.seleccionarTransporte(capacidad, distancia);
                            boolean exito = gestion.confirmarRegistroPedido(transporte);

                            if (exito) {
                                System.out.println("Pedido registrado exitosamente.");
                            } else {
                                System.out.println("Error al registrar el pedido.");
                            }
                        } else {
                            System.out.println("Pedido cancelado.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error al guardar el pedido.");
                    } catch (EntregaInvalidaExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        double totalCosto = gestion.generarReporteMensual();
                        System.out.println("Total de costos de pedidos en el mes actual: $" + totalCosto);
                    } catch (IOException e) {
                        System.out.println("Error al generar el reporte.");
                    }
                    break;

                case 3:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}