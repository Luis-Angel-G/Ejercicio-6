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

    public void setDescuento() {
        this.descuento = 5;
    }

    @Override
    public double calcularCosto() {
        double costo = super.calcularCosto();
        if (distancia > 100) {
            costo -= costo * (descuento / 100);
        }
        return costo;
    }
}