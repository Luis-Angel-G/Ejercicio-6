/**
 * @author Luis Angel Giron Arevalo
 * @date Fecha de creación: 2024-10-24
 * @date Fecha de última modificación: 2024-10-28
 * Descripción: Interfaz para la clase Transporte.
 */
public interface Interfaz {
    /**
     * Calcula el costo del transporte.
     *
     * @return El costo calculado.
     */
    double calcularCosto();

    /**
     * Valida si la entrega es válida.
     *
     * @return true si la entrega es válida, false en caso contrario.
     * @throws EntregaInvalidaExcepcion si la entrega no es válida.
     */
    boolean validarEntrega() throws EntregaInvalidaExcepcion;
}