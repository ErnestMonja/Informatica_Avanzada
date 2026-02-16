/**
 * Modela los recipientes para los ingredientes.
 */
public abstract class Recipiente
{
    static int CAPACIDAD_MAXIMA_DEFAULT = 2000;
    int capacidadMaxima;
    int cantidadDisponible;
    
    /**
     * Constructor sin parámetros. Setea la capacidad máxima a un valor por defecto.
     */
    public Recipiente()
    {
        capacidadMaxima = Recipiente.CAPACIDAD_MAXIMA_DEFAULT;
    }
    
    /**
     * Constructor con parámetro. Setea la capacidad maáxima al valor indicado.
     * 
     * @param  capacidadMaxima          El valor de la maxima capacidad de este recipiente.
     * @throws IllegalArgumentException Si el parámetro excede la capacidad máxima por defecto o es un valor negativo.
     */
    public Recipiente(int capacidadMaxima)
    {
        if(capacidadMaxima <=0 || capacidadMaxima > Recipiente.CAPACIDAD_MAXIMA_DEFAULT)
        {
            throw new IllegalArgumentException ("Capacidad maxima no valida");
        }
        this.capacidadMaxima = capacidadMaxima;
    }
    
    /**
     * Devuelve el tipo de ingrediente que almacena el recipiente    
     * 
     * @return el ingrediente almacenado
     */
    public abstract Ingrediente getTipoIngredienteAlmacenado();
    
    /**
     * Agrega contenido al recipiente
     * 
     * @param cantidadAgregada la cantidad de contenido a agregar
     * @throws CapacidadExcedidaException cuando intenta agregarse
     * mas contendio del que el recipiente puede alojar
     * @throws IllegalArgumentException cuando la cantidad a
     * agregar es un valor negativo
     */
    public abstract void agregar(int cantidadAgregada) throws CapacidadExcedidaException;

    /**
     * Extrae contenido del recipiente
     * 
     * @param cantidadAExtraer la cantidad a extraer
     * @throws CapacidadExcedidaException cuando intenta extraerse
     * una cantidad mayor a la diponible en el recipiente
     * @throws IllegalArgumentException cuando la cantidad a
     * extraer es un valor negativo
     */
    public abstract void extraer(int cantidadAExtraer) throws CapacidadExcedidaException;
    
    
    // Getters y toString:
    /**
     * Devuelve la capacidad maxima del recipiente
     * 
     * @return la capacidad maxima de este recipiente.
     */
    public int getCapacidadMaxima()
    {
        return capacidadMaxima;
    }
    
    /**
     * Devuelve la cantidad de ingrediente en el recipiente
     * 
     * @return la cantidad de contenido disponible en el recipiente.
     */
    public int getCantidadIngredienteDisponible()
    {
        return cantidadDisponible;
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Recipiente contiene: ");
        sb.append(this.getTipoIngredienteAlmacenado());
        sb.append(" [");
        sb.append(cantidadDisponible);
        sb.append("/");
        sb.append(this.capacidadMaxima);
        sb.append("]");
        return sb.toString();
    }
}
