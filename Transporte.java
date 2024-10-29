public abstract class Transporte implements Interfaz {
    protected double tarifabase;
    protected double capacidad;
    protected double distancia;
    protected int capacidadmax;
    
    public Transporte(double capacidad, double distancia) {
        this.capacidad = capacidad;
        this.distancia = distancia;
    }

    public double getTarifabase() {
        return tarifabase;
    }

    public void setTarifabase(double tarifabase) {
        this.tarifabase = tarifabase;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getCapacidadmax() {
        return capacidadmax;
    }
    
    public void setCapacidadmax(int capacidadmax) {
        this.capacidadmax = capacidadmax;
    }
    
    @Override
    public double calcularCosto(){
        double costot = tarifabase * distancia;
        return costot;
    }

    @Override
    public boolean validarEntrega() throws EntregaInvalidaExcepcion {
        if (capacidad > capacidadmax) {
            throw new EntregaInvalidaExcepcion("Capacidad excede el límite permitido.");
        }
        return true; // Entrega válida
    }
}