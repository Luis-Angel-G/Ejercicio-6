public abstract class Transporte implements Interfaz {
    protected double tarifabase;
    protected double capacidad;
    protected double distancia;
    protected int capacidadmax;
    
    public Transporte(double tarifabase, double capacidad, double distancia, int capacidadmax) {
        this.tarifabase = tarifabase;
        this.capacidad = capacidad;
        this.distancia = distancia;
        this.capacidadmax = capacidadmax;
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
    public boolean validarEntrega(){
        boolean valido;
        if (capacidad <= capacidadmax) {
            valido = true;
        }
        else {
            valido = false;
        }
    }
}