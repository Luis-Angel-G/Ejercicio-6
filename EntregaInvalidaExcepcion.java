/**
 * @author Luis Angel Girón Arévalo
 * @date Fecha de creación: 2024-10-24
 * @date Fecha de última modificación: 2024-10-28
 * Descripción: Clase que representa una excepción para entregas inválidas.
 */
public class EntregaInvalidaExcepcion extends Exception {
    /**
     * Constructor para la excepción de entrega inválida.
     *
     * @param mensaje Mensaje de error.
     */
    public EntregaInvalidaExcepcion(String mensaje) {
        super(mensaje);
    }
}