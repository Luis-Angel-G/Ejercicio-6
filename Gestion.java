import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
            try {
                transporteElegido.validarEntrega();
                double costo = transporteElegido.calcularCosto();
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
            return true;
        }
        return false;
    }

    public Transporte seleccionarTransporte(double capacidad, double distancia) {
        Camion camion = new Camion(capacidad, distancia, 5);
        Motocicleta moto = new Motocicleta(capacidad, distancia);
        Drone drone = new Drone(capacidad, distancia, 10);

        if (validarYSeleccionarTransporte(drone)) {
            return drone;
        } else if (validarYSeleccionarTransporte(moto)) {
            return moto;
        } else if (validarYSeleccionarTransporte(camion)) {
            return camion;
        }

        return null;
    }

    private boolean validarYSeleccionarTransporte(Transporte transporte) {
        try {
            return transporte.validarEntrega();
        } catch (EntregaInvalidaExcepcion e) {
            return false;
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
        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        while ((linea = reader.readLine()) != null) {
            String[] datos = linea.split(",");
            Date fecha = null;
            try {
                fecha = formatoFecha.parse(datos[0]);
            } catch (ParseException e) {
                e.printStackTrace();
                continue; // Si hay un error, continúa con la siguiente línea
            }
            double costo = Double.parseDouble(datos[1]);
            String tipo = datos[2];
            double distancia = Double.parseDouble(datos[3]);
            double capacidad = Double.parseDouble(datos[4]);

            Transporte transporte = crearTransporte(tipo, capacidad, distancia);
            Pedido pedido = new Pedido(fecha, transporte);
            pedido.setCostototal(costo);
            pedidosCargados.add(pedido);
        }
        reader.close();
        return pedidosCargados;
    }

    private Transporte crearTransporte(String tipo, double capacidad, double distancia) {
        switch (tipo) {
            case "Camion":
                return new Camion(capacidad, distancia, 5);
            case "Motocicleta":
                return new Motocicleta(capacidad, distancia);
            case "Drone":
                return new Drone(capacidad, distancia, 10);
            default:
                return null;
        }
    }

    public double generarReporteMensual() throws IOException {
        List<Pedido> pedidosMensuales = cargarPedidosCSV();
        double totalCosto = 0;

        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        int mesActual = calendar.get(Calendar.MONTH);
        int anioActual = calendar.get(Calendar.YEAR);

        for (Pedido pedido : pedidosMensuales) {
            calendar.setTime(pedido.getFecha());
            int mesPedido = calendar.get(Calendar.MONTH);
            int anioPedido = calendar.get(Calendar.YEAR);

            if (mesPedido == mesActual && anioPedido == anioActual) {
                totalCosto += pedido.getCostototal();
            }
        }

        return totalCosto; 
    }
}