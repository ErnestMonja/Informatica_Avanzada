/**
 * Logica de un semaforo de 2 estados: ROJO y VERDE.
 */
public class Semaforo
{
    private ESTADOS estado;

    /**
     * Inicializa el semáforo al estado inicial
     * 
     * @param estado_inicial    Rojo o verde
     */
    public Semaforo(ESTADOS estado_inicial)
    {
        this.estado = estado_inicial;
    }

    /**
     * Consulta el estado del semáforo.
     * 
     * @return  Rojo o verde.
     */
    public ESTADOS get_estado()
    {
        return estado;
    }

    /**
     * Cambia el estado del semáforo.
     * 
     * @param nuevo_estado  Rojo o verde.
     */
    public void set_estado(ESTADOS nuevo_estado)
    {
        this.estado = nuevo_estado;
    }

    /**
     * @return  true si el semáforo está en verede, false si no lo está.
     */
    public boolean esta_disponible()
    {
        return estado.equals(ESTADOS.VERDE);
    }
}