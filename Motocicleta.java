public class Motocicleta extends Transporte {
    public Motocicleta(double capacidad, double distancia) {
        super(tarifabase, capacidad, distancia, capacidadmax);
        setTarifabase(10);
        setCapacidadmax(50);
    }
}