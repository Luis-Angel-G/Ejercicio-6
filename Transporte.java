public abstract class Transporte implements Interfaz {
    protected double tarifabase;
    protected double capacidad;
    protected double distancia;
    
    public Transporte(double tarifabase, double capacidad, double distancia) {
        this.tarifabase = tarifabase;
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
    
    @Override
    public double calcularCosto(){
        double costot = tarifabase * distancia;
        return costot;
    }

    @Override
    public String validarEntrega(){
        return "";
    }
}