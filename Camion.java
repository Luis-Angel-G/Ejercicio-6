public class Camion extends Transporte {
    public double descuento;

    public Camion(double tarifabase, double capacidad, double distancia, int capacidadmax, double descuento) {
        super(tarifabase, capacidad, distancia, capacidadmax);
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}