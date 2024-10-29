import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Clase Gestion que maneja el registro y gestión de pedidos.
 * <p>
 * Esta clase permite registrar pedidos, seleccionar transporte adecuado,
 * guardar pedidos en un archivo CSV y generar reportes mensuales.
 * </p>
 * 
 * @author Luis Angel Girón Arévalo
 * @date 28/10/2024
 */
public class Gestion {
    private ArrayList<Pedido> pedidos;

    /**
     * Constructor de la clase Gestion que inicializa la lista de pedidos.
     */
    public Gestion() {
        pedidos = new ArrayList<>();
    }

    /**
     * Registra un pedido seleccionando un transporte que cumpla con los requisitos.
     *
     * @param capacidad Capacidad requerida para el transporte en kg.
     * @param distancia Distancia del pedido en km.
     * @return Costo total del pedido.
     * @throws IOException Si ocurre un error al guardar el pedido.
     * @throws EntregaInvalidaExcepcion Si no hay transporte válido.
     */
    public double registrarPedido(double capacidad, double distancia) throws IOException, EntregaInvalidaExcepcion {
        Transporte transporteElegido = seleccionarTransporte(capacidad, distancia);

        if (transporteElegido != null) {
            try {
                transporteElegido.validarEntrega();
                return transporteElegido.calcularCosto();
            } catch (EntregaInvalidaExcepcion e) {
                throw new EntregaInvalidaExcepcion("Error en la entrega: " + e.getMessage());
            }
        } else {
            throw new EntregaInvalidaExcepcion("No hay transporte disponible que cumpla con los requisitos.");
        }
    }

    /**
     * Confirma el registro de un pedido y lo guarda en un archivo CSV.
     *
     * @param transporte El transporte asociado al pedido.
     * @return true si el pedido se registró exitosamente, false en caso contrario.
     * @throws IOException Si ocurre un error al guardar el pedido.
     */
    public boolean confirmarRegistroPedido(Transporte transporte) throws IOException {
        if (transporte != null) {
            Pedido pedido = new Pedido(new Date(), transporte);
            pedidos.add(pedido);
            guardarPedidoCSV(pedido);
            return true;
        }
        return false;
    }

    /**
     * Selecciona un transporte basado en la capacidad y la distancia requeridas.
     *
     * @param capacidad Capacidad requerida para el transporte en kg.
     * @param distancia Distancia del pedido en km.
     * @return Un objeto Transporte que cumpla con los requisitos.
     */
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

    /**
     * Valida si un transporte puede realizar la entrega.
     *
     * @param transporte El transporte a validar.
     * @return true si el transporte puede realizar la entrega, false en caso contrario.
     */
    private boolean validarYSeleccionarTransporte(Transporte transporte) {
        try {
            return transporte.validarEntrega();
        } catch (EntregaInvalidaExcepcion e) {
            return false;
        }
    }

    /**
     * Guarda un pedido en un archivo CSV.
     *
     * @param pedido El pedido a guardar.
     * @throws IOException Si ocurre un error al guardar el pedido en el archivo.
     */
    public void guardarPedidoCSV(Pedido pedido) throws IOException {
        FileWriter writer = new FileWriter("pedidos.csv", true);
        BufferedWriter buffer = new BufferedWriter(writer);
        
        // Usar el formato adecuado para la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        String fechaFormateada = formatoFecha.format(pedido.getFecha());
        
        // Formato CSV: fecha,costo total,tipo de transporte,distancia,capacidad
        buffer.write(fechaFormateada + "," + pedido.getCostototal() + "," +
                pedido.getTransporte().getClass().getSimpleName() + "," +
                pedido.getTransporte().getDistancia() + "," +
                pedido.getTransporte().getCapacidad());
        buffer.newLine();
        buffer.close();
    }
    
    /**
     * Carga los pedidos desde un archivo CSV.
     *
     * @return Una lista de pedidos cargados.
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
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

    /**
     * Crea un objeto Transporte basado en el tipo especificado.
     *
     * @param tipo Tipo de transporte (Camion, Motocicleta, Drone).
     * @param capacidad Capacidad del transporte en kg.
     * @param distancia Distancia del pedido en km.
     * @return Un objeto Transporte del tipo especificado.
     */
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

    /**
     * Genera un reporte mensual del costo total de los pedidos.
     *
     * @return El costo total de los pedidos en el mes actual.
     * @throws IOException Si ocurre un error al cargar los pedidos desde el archivo.
     */
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