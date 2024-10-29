import java.util.Date;

public class Pedido {
    private Date fecha;
    private double costototal;
    private Transporte transporte;

    public Pedido(Date fecha, Transporte transporte) {
        this.fecha = fecha;
        this.transporte = transporte;
        this.costototal = transporte.calcularCosto(); // Asumiendo que el método calcularCosto está definido en Transporte
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCostototal() {
        return costototal;
    }

    public void setCostototal(double costototal) {
        this.costototal = costototal;
    }

    public Transporte getTransporte() { // Método agregado
        return transporte;
    }
}