import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gestion {
    private ArrayList<Pedido> pedidos;

    public Gestion() {
        pedidos = new ArrayList<>();
    }

    public String registrarPedido(double capacidad, double distancia) throws IOException, EntregaInvalidaExcepcion {
        Transporte transporteElegido = seleccionarTransporte(capacidad, distancia);
        
        if (transporteElegido != null) {
            transporteElegido.validarEntrega(); // Llama al método que lanza la excepción
            double costo = transporteElegido.calcularCosto();
            return "Costo total: $" + costo;
        } else {
            return "No hay transporte disponible que cumpla con los requisitos.";
        }
    }

    public boolean confirmarRegistroPedido(Transporte transporte) throws IOException {
        if (transporte != null) {
            Pedido pedido = new Pedido(new Date(), transporte);
            pedidos.add(pedido);
            guardarPedidoCSV(pedido);
            return true;  // Pedido registrado con éxito
        }
        return false; // Pedido no registrado
    }

    private Transporte seleccionarTransporte(double capacidad, double distancia) {
        // Crear instancias de cada tipo de transporte
        Camion camion = new Camion(capacidad, distancia, 5);
        Motocicleta moto = new Motocicleta(capacidad, distancia);
        Drone drone = new Drone(capacidad, distancia, 10);

        // Validar cada transporte y seleccionar el más barato que cumpla los requisitos
        if (drone.validarEntrega()) {
            return drone;
        } else if (moto.validarEntrega()) {
            return moto;
        } else if (camion.validarEntrega()) {
            return camion;
        }

        return null; // Ningún transporte cumple los requisitos
    }

    public void guardarPedidoCSV(Pedido pedido) throws IOException {
        FileWriter writer = new FileWriter("pedidos.csv", true);
        BufferedWriter buffer = new BufferedWriter(writer);

        // Formato CSV: fecha,costo total,tipo de transporte,distancia,capacidad
        buffer.write(pedido.getFecha() + "," + pedido.getCostototal() + "," +
                pedido.getTransporte().getClass().getSimpleName() + "," +
                pedido.getTransporte().getDistancia() + "," +
                pedido.getTransporte().getCapacidad());
        buffer.newLine();
        buffer.close();
    }

    public List<Pedido> cargarPedidosCSV() throws IOException {
        List<Pedido> pedidosCargados = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("pedidos.csv"));
        String linea;

        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(",");
            Date fecha = new Date(datos[0]);
            double costo = Double.parseDouble(datos[1]);
            String tipo = datos[2];
            double distancia = Double.parseDouble(datos[3]);
            double capacidad = Double.parseDouble(datos[4]);

            Transporte transporte = null;
            if (tipo.equals("Camion")) {
                transporte = new Camion(capacidad, distancia, 5);
            } else if (tipo.equals("Motocicleta")) {
                transporte = new Motocicleta(capacidad, distancia);
            } else if (tipo.equals("Drone")) {
                transporte = new Drone(capacidad, distancia, 10);
            }

            Pedido pedido = new Pedido(fecha, transporte);
            pedidosCargados.add(pedido);
        }
        reader.close();
        return pedidosCargados;
    }

    public void generarReporteMensual() throws IOException {
        List<Pedido> pedidosMensuales = cargarPedidosCSV();
        double totalCosto = 0;

        Date fechaActual = new Date();
        for (Pedido pedido : pedidosMensuales) {
            if (pedido.getFecha().getMonth() == fechaActual.getMonth() &&
                pedido.getFecha().getYear() == fechaActual.getYear()) {
                totalCosto += pedido.getCostototal();
            }
        }

        System.out.println("Total de costos de pedidos en el mes actual: $" + totalCosto);
    }
}