/**
 * Implementación de un Auto que transita por una determinada calle.
 * 
 * El modelo tiene en cuenta las siguientes simplificaciones:
 *      1. El auto siempre transita por la misma calle (no puede doblar)
 *      2. La direcciones posibles son adelante y atras. El auto no cambia de dirección en ningún momento
 *      
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 40/40
 */
public class Auto
{
    // Campos estáticos:
    private static final Integer velocidad = 1;     // Cantidad de lugares que se mueve el auto.
    private static final String nombre = "Auto";
    private static Integer numero_auto = 0;

    public enum DIRECCION
    {
        ADELANTE, ATRAS
    }

    private Calle calle_actual;
    private Integer posicion;                       // Altura ubicada en la calle actual
    private DIRECCION direccion;
    private Integer id;

    /**
     * Inicializacion del auto.
     * 
     * @param calle_inicial  Calle por la cual el auto va a transitar.
     * @param direccion      Dirección en la cual puede transitar (@see DIRECCION).
     * @param lugar_inicial  Lugar inicial dentro de la calle en la cual inicia su recorrido.
     */
    public Auto(Calle calle_inicial, DIRECCION direccion, Integer lugar_inicial)
    {
        this.calle_actual = calle_inicial;
        this.direccion = direccion;
        this.posicion = lugar_inicial;

        id = numero_auto;
        numero_auto += 1;
    }

    /**
     * Devuelve la nueva posición que tendría el auto.
     * 
     * La nueva posición depende de la DIRECCION que tiene el auto y su velocidad, que en este caso es la misma para todos los autos.
     *      - Si la dirección es hacia ADELANTE, la posición del auto aumenta.
     *      - Si la dirección es hacia ATRAS, la posición del auto decrementa.
     *      
     * @return  La nueva posición que va a ocupar el auto.
     */
    public Integer get_siguiente_posicion()
    {
        // Completar
        if(this.get_direccion() == DIRECCION.ADELANTE)
        {
            return (this.get_posicion() + velocidad);
        }
        else // Si no va adelante, va para atras.
        {
            return (this.get_posicion() - velocidad);
        }
    }

    /**
     * Se fija si la posición a validar está entre la posición actual del auto y su siguiente posición ( get_siguiente_posicion() )
     * 
     * @param posicion_a_validar    Posición por la cual queremos saber si estará en la posición a validar en su próximo movimiento.
     * @return                      true si pasa por la posición, false si no pasa.
     */
    public boolean auto_pasa_por(Integer posicion_a_validar)
    {
        // Completar
        Integer actual    = this.get_posicion();
        Integer siguiente = this.get_siguiente_posicion();
        if(this.get_direccion() == DIRECCION.ADELANTE)
        {
            return ((posicion_a_validar >= actual)&&(posicion_a_validar <= siguiente));
        }
        else
        {
            return ((posicion_a_validar <= actual)&&(posicion_a_validar >= siguiente));
        }
    }

    /**
     * Lógica de movimiento del auto:
     * 
     * Si el próximo movimiento del auto esta dentro de los límites de la calles entonces:
     *      - Si el auto puede avanzar, es decir, no choca con otro auto ni se encuentra un semaforo en rojo (Ayuda: ver clase Mapa), avanzar
     *      - Si no puede avanzar lanza excepción.
     * Si el auto esta por salirse de los límites no avanza pero tampoco tira excepción.
     */
    public void mover_auto_una_posicion(Mapa mapa) throws IllegalStateException 
    {
        // Completar
        Integer siguiente = this.get_siguiente_posicion();
        if((siguiente < 0)||(siguiente > this.get_calle().get_longitud()))
        {
            return;
        }
        
        if(mapa.comprobar_movimiento(this))
        {
            posicion = this.get_siguiente_posicion(); 
        }
        else
        {
            throw new IllegalStateException();
        }
    }

    /**
     * @return  Dirección del auto.
     */
    public DIRECCION get_direccion()
    {
        return direccion;
    }

    /**
     * @return  Calle actual.
     */
    public Calle get_calle()
    {
        return calle_actual;
    }

    /**
     * @return  Posición actual del auto.
     */
    public Integer get_posicion()
    {
        return posicion;
    }
    
    public String toString()
    {
        return String.format("%s %d", nombre, id);
    }
}