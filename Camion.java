public class Camion extends Transporte {
    public double descuento;

    public Camion(double capacidad, double distancia, double descuento) {
        super(capacidad, distancia);
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void setTarifabase(20);
}