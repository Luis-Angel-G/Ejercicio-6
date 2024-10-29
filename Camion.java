/**
 * @author Luis Ángel Girón Arévalo
 * @date Fecha de creación: 2024-10-24
 * @date Fecha de última modificación: 2024-10-28
 * Descripción: Clase que representa un camión.
 */
public class Camion extends Transporte {
    public double descuento; // Descuento aplicado al costo

    /**
     * Constructor para la clase Camion.
     *
     * @param capacidad La capacidad del camión.
     * @param distancia La distancia a recorrer.
     * @param descuento El descuento a aplicar.
     */
    public Camion(double capacidad, double distancia, double descuento) {
        super(capacidad, distancia);
        this.descuento = descuento;
        setTarifabase(20);
        setCapacidadmax(1000);
    }

    /**
     * Obtiene el descuento aplicado al costo.
     *
     * @return Descuento aplicado.
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Establece el descuento aplicado al costo.
     *
     * @param descuento Descuento a establecer.
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    /**
     * Calcula el costo del camión considerando el descuento.
     *
     * @return El costo total del camión.
     */
    @Override
    public double calcularCosto() {
        double costo = super.calcularCosto();
        if (distancia > 100) {
            costo -= costo * (descuento / 100);
        }
        return costo;
    }
}