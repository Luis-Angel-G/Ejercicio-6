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
            // Manejo de la excepción al validar entrega
            try {
                transporteElegido.validarEntrega(); // Llama al método que lanza la excepción
                double costo = transporteElegido.calcularCosto();
                // Confirmar registro del pedido
                confirmarRegistroPedido(transporteElegido);
                return "Costo total: $" + costo;
            } catch (EntregaInvalidaExcepcion e) {
                return "Error en la entrega: " + e.getMessage();
            }
        } else {
            return "No hay transporte disponible que cumpla con los requisitos.";
        }
    }

    public boolean confirmarRegistroPedido(Transporte transporte) throws IOException {
        if (transporte != null) {
            Pedido pedido = new Pedido(new Date(), transporte);
            pedidos.add(pedido);
            guardarPedidoCSV(pedido);
            return true; // Pedido registrado con éxito
        }
        return false; // Pedido no registrado
    }

    private Transporte seleccionarTransporte(double capacidad, double distancia) {
        // Crear instancias de cada tipo de transporte
        Camion camion = new Camion(capacidad, distancia, 5);
        Motocicleta moto = new Motocicleta(capacidad, distancia);
        Drone drone = new Drone(capacidad, distancia, 10);

        // Validar cada transporte y seleccionar el más barato que cumpla los requisitos
        if (validarYSeleccionarTransporte(drone)) {
            return drone;
        } else if (validarYSeleccionarTransporte(moto)) {
            return moto;
        } else if (validarYSeleccionarTransporte(camion)) {
            return camion;
        }

        return null; // Ningún transporte cumple los requisitos
    }

    // Método para validar el transporte y manejar excepciones
    private boolean validarYSeleccionarTransporte(Transporte transporte) {
        try {
            return transporte.validarEntrega(); // Llama al método que lanza la excepción
        } catch (EntregaInvalidaExcepcion e) {
            // Manejo de la excepción si es necesario
            return false; // No se puede usar este transporte
        }
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
            Date fecha = new Date(datos[0]); // Ajusta este método si es necesario
            double costo = Double.parseDouble(datos[1]);
            String tipo = datos[2];
            double distancia = Double.parseDouble(datos[3]);
            double capacidad = Double.parseDouble(datos[4]);

            Transporte transporte = crearTransporte(tipo, capacidad, distancia);
            Pedido pedido = new Pedido(fecha, transporte);
            pedidosCargados.add(pedido);
        }
        reader.close();
        return pedidosCargados;
    }

    // Método auxiliar para crear transporte según el tipo
    private Transporte crearTransporte(String tipo, double capacidad, double distancia) {
        switch (tipo) {
            case "Camion":
                return new Camion(capacidad, distancia, 5);
            case "Motocicleta":
                return new Motocicleta(capacidad, distancia);
            case "Drone":
                return new Drone(capacidad, distancia, 10);
            default:
                return null; // Tipo de transporte no reconocido
        }
    }

    public double generarReporteMensual() throws IOException {
        List<Pedido> pedidosMensuales = cargarPedidosCSV();
        double totalCosto = 0;

        Date fechaActual = new Date();
        for (Pedido pedido : pedidosMensuales) {
            if (pedido.getFecha().getMonth() == fechaActual.getMonth() &&
                pedido.getFecha().getYear() == fechaActual.getYear()) {
                totalCosto += pedido.getCostototal();
            }
        }

        return totalCosto; // Retorna el total de costos
    }
}