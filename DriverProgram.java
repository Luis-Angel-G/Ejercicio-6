import java.io.IOException;
import java.util.Scanner;

public class DriverProgram {
    public static void main(String[] args) {
        Gestion gestion = new Gestion();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Menú de Opciones:");
            System.out.println("1. Registrar Pedido");
            System.out.println("2. Generar Reporte Mensual");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Ingrese capacidad (kg): ");
                        double capacidad = scanner.nextDouble();
                        System.out.print("Ingrese distancia (km): ");
                        double distancia = scanner.nextDouble();

                        String mensajeCosto = gestion.registrarPedido(capacidad, distancia);
                        System.out.println(mensajeCosto);

                        if (!mensajeCosto.contains("No hay transporte")) {
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
                        }
                    } catch (IOException e) {
                        System.out.println("Error al guardar el pedido.");
                    }
                    break;

                case 2:
                    // Llamada a generar reporte mensual (modificar para evitar System.out.println en Gestion)
                    break;

                case 3:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }
}