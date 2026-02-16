public class Billetera {
    Double pesos;

    /**
     * Constructor por defecto
     */
    public Billetera() {
        pesos = 0.0;
    }

    /**
     * Constructor con dinero incial.
     * @param pesos El dinero en la billetera
     */
    public Billetera(Double pesos) {
        this.pesos = pesos;
    }

    /**
     * Agrega dinero a la billetera.
     * En caso de recibir un valor menor o igual a cero
     * lanza IllegalArgumentException.
     * 
     * @param pesos La cantidad de pesos a agregar.
     * @throws IllegalArgumentException Cuando la cantidad es menor igual a cero.
     */
    public void agregarPesos(final Double pesos) {
        if (pesos > 0) {
            this.pesos += pesos;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Intenta gastar una cantidad de pesos. Si la cantidad a gastar
     * es mayor a la disponible, lanza una excepción del tipo 
     * DineroInsuficienteException.
     * En cambio, si la cantidad de pesos a gastar es negativa
     * lanza una IllegalArgumentException.
     * 
     * @param pesos La cantidad de pesos a gastar.
     * @throws DineroInsuficienteException Cuando el valor es mayor al dinero disponible.
     * @throws IllegalArgumentException Cuando la cantidad es menor igual a cero.
     */
    public void gastar(final Double pesos) throws DineroInsuficienteException {
        if (pesos <= 0) {
            throw new IllegalArgumentException();
        } else if (this.pesos < pesos) {
            throw new DineroInsuficienteException();
        } else {
            this.pesos -= pesos;
        }
    }

    /**
     * Imprime lo siguiente:
     * 
     * "Billetera -> Fondos: ${pesos}" 
     * 
     * Donde se remplaza los corchetes por el valor de la variable.
     * Ej:
     * 
     * "Billetera -> Fondos: $10.0"
     */
    public String toString() {
        return "Billetera -> Fondos: $" + this.pesos;
    }

    public Double getPesos() {
        return pesos;
    }

}
