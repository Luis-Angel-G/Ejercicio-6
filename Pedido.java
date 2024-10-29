/**
 * @author Luis Angel Giron Arevalo
 * @date Fecha de creación: 2024-10-24
 * @date Fecha de última modificación: 2024-10-28
 * Descripción: Clase que representa un pedido.
 */
import java.util.Date;

public class Pedido {
    private Date fecha; // Fecha del pedido
    private double costototal; // Costo total del pedido
    private Transporte transporte; // Transporte asociado al pedido

    /**
     * Constructor para la clase Pedido.
     *
     * @param fecha La fecha del pedido.
     * @param transporte El transporte asociado al pedido.
     */
    public Pedido(Date fecha, Transporte transporte) {
        this.fecha = fecha;
        this.transporte = transporte;
        this.costototal = transporte.calcularCosto();
    }

    /**
     * Obtiene la fecha del pedido.
     *
     * @return Fecha del pedido.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del pedido.
     *
     * @param fecha Fecha a establecer.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el costo total del pedido.
     *
     * @return Costo total del pedido.
     */
    public double getCostototal() {
        return costototal;
    }

    /**
     * Establece el costo total del pedido.
     *
     * @param costototal Costo total a establecer.
     */
    public void setCostototal(double costototal) {
        this.costototal = costototal;
    }

    /**
     * Obtiene el transporte asociado al pedido.
     *
     * @return Transporte asociado al pedido.
     */
    public Transporte getTransporte() {
        return transporte;
    }
}