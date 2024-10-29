public class Drone extends Transporte {
    public int limite;

    public Drone(double capacidad, double distancia, int limite) {
        super(capacidad, distancia);
        this.limite = limite;
        setTarifabase(5);
        setCapacidadmax(5);
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite() {
        this.limite = 10;
    }

    @Override
    public boolean validarEntrega(){
        boolean valido;
        if (capacidad <= capacidadmax && distancia <= limite) {
            valido = true;
        }
        else {
            valido = false;
        }
        return valido;
    }
}