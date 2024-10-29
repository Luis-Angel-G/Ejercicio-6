import java.util.Date;

public class Pedido {
    public Date fecha;
    public double costototal;
    
    public Pedido(Date fecha) {
        this.fecha = fecha;
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

    public void setCostototal(calcularCosto()) {
        this.costototal = calcularCosto();
    }
}