public class Camion extends Transporte {
    public double descuento;

    public Camion(double capacidad, double distancia, double descuento) {
        super(capacidad, distancia);
        this.descuento = descuento;
        setTarifabase(20);
        setCapacidadmax(1000);
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public double calcularCosto() {
        double costot = super.calcularCosto() - (super.calcularCosto() * (descuento / 100));
        return costot;
    }
}