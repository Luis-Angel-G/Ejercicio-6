/**
 * @author Luis Angel Giron Arevalo
 * @date Fecha de creación: 2024-10-24
 * @date Fecha de última modificación: 2024-10-28
 * Descripción: Clase que representa una motocicleta.
 */
public class Motocicleta extends Transporte {
    /**
     * Constructor para la clase Motocicleta.
     *
     * @param capacidad La capacidad de la motocicleta.
     * @param distancia La distancia a recorrer.
     */
    public Motocicleta(double capacidad, double distancia) {
        super(capacidad, distancia);
        setTarifabase(10);
        setCapacidadmax(50);
    }
}