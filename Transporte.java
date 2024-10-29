/**
 * @author Luis Angel Giron Arevalo
 * @date Fecha de creación: 2024-10-24
 * @date Fecha de última modificación: 2024-10-28
 * Descripción: Clase abstracta que representa un transporte.
 */
public abstract class Transporte implements Interfaz {
    protected double tarifabase; // Tarifa base del transporte
    protected double capacidad;   // Capacidad del transporte
    protected double distancia;   // Distancia a recorrer
    protected int capacidadmax;   // Capacidad máxima permitida

    /**
     * Constructor para la clase Transporte.
     *
     * @param capacidad La capacidad del transporte.
     * @param distancia La distancia a recorrer.
     */
    public Transporte(double capacidad, double distancia) {
        this.capacidad = capacidad;
        this.distancia = distancia;
    }

    /**
     * Obtiene la tarifa base del transporte.
     *
     * @return Tarifa base del transporte.
     */
    public double getTarifabase() {
        return tarifabase;
    }

    /**
     * Establece la tarifa base del transporte.
     *
     * @param tarifabase Tarifa base a establecer.
     */
    public void setTarifabase(double tarifabase) {
        this.tarifabase = tarifabase;
    }

    /**
     * Obtiene la capacidad del transporte.
     *
     * @return Capacidad del transporte.
     */
    public double getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad del transporte.
     *
     * @param capacidad Capacidad a establecer.
     */
    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene la distancia a recorrer.
     *
     * @return Distancia a recorrer.
     */
    public double getDistancia() {
        return distancia;
    }

    /**
     * Establece la distancia a recorrer.
     *
     * @param distancia Distancia a establecer.
     */
    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    /**
     * Obtiene la capacidad máxima del transporte.
     *
     * @return Capacidad máxima del transporte.
     */
    public int getCapacidadmax() {
        return capacidadmax;
    }

    /**
     * Establece la capacidad máxima del transporte.
     *
     * @param capacidadmax Capacidad máxima a establecer.
     */
    public void setCapacidadmax(int capacidadmax) {
        this.capacidadmax = capacidadmax;
    }

    /**
     * Calcula el costo del transporte.
     *
     * @return El costo total.
     */
    @Override
    public double calcularCosto() {
        double costot = tarifabase * distancia;
        return costot;
    }

    /**
     * Valida si la entrega es válida.
     *
     * @return true si la entrega es válida, false en caso contrario.
     * @throws EntregaInvalidaExcepcion si la entrega no es válida.
     */
    @Override
    public boolean validarEntrega() throws EntregaInvalidaExcepcion {
        if (capacidad > capacidadmax) {
            throw new EntregaInvalidaExcepcion("Capacidad excede el límite permitido.");
        }
        return true; // Entrega válida
    }
}