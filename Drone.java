/**
 * @author Luis Angel Giron Arevalo
 * @date Fecha de creación: 2024-10-24
 * @date Fecha de última modificación: 2024-10-28
 * Descripción: Clase que representa un dron.
 */
public class Drone extends Transporte {
    public int limite; // Límite de distancia para el dron

    /**
     * Constructor para la clase Drone.
     *
     * @param capacidad La capacidad del dron.
     * @param distancia La distancia a recorrer.
     * @param limite El límite de distancia permitido.
     */
    public Drone(double capacidad, double distancia, int limite) {
        super(capacidad, distancia);
        this.limite = limite;
        setTarifabase(5);
        setCapacidadmax(5);
    }

    /**
     * Obtiene el límite de distancia permitido.
     *
     * @return Límite de distancia permitido.
     */
    public int getLimite() {
        return limite;
    }

    /**
     * Establece el límite de distancia permitido.
     *
     * @param limite Límite a establecer.
     */
    public void setLimite(int limite) {
        this.limite = limite;
    }

    /**
     * Valida si la entrega del dron es válida.
     *
     * @return true si la entrega es válida, false en caso contrario.
     * @throws EntregaInvalidaExcepcion si la entrega no es válida.
     */
    @Override
    public boolean validarEntrega() throws EntregaInvalidaExcepcion {
        if (capacidad > capacidadmax || distancia > limite) {
            throw new EntregaInvalidaExcepcion("Capacidad o distancia excede el límite permitido.");
        }
        return true; // Entrega válida
    }
}