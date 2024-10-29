public class Drone extends Transporte {
    public int limite;

    public Drone(double tarifabase, double capacidad, double distancia, int capacidadmax, int limite) {
        super(tarifabase, capacidad, distancia, capacidadmax);
        this.limite = limite;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
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