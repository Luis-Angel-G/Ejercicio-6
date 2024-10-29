public class Motocicleta extends Transporte {
    public Motocicleta(double capacidad, double distancia) {
        super(capacidad, distancia);
        setTarifabase(10);
        setCapacidadmax(50);
    }
}