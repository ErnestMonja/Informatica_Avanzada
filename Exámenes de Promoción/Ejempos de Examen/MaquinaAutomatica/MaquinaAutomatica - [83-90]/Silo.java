/**
 * Implementar la clase Silo que extiende a Recipiente. Considere los casos cuando no hay ingrediente o capacidad es mayor a capacidad máxima o negativa.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 16/16
 */
public class Silo extends Recipiente
{
    private Ingrediente ingrediente;

    /**
     * Constructor con dos parámetros, de objectos de la clase Silo.
     * 
     * @param ingredienteAlmacenado     El ingrediente a almacenar.
     * @param capacidadMaxima           La capacidad máxima del Silo.
     */
    public Silo(Ingrediente ingredienteAlmacenado, int capacidadMaxima)
    {
        //TODO: Implementar metodo
        super(capacidadMaxima);
        this.ingrediente = ingredienteAlmacenado;
        super.cantidadDisponible = 0;
    }
    
    /**
     * Constructor con un parámetro, de objectos de la clase Silo.
     * 
     * @param ingredienteAlmacenado     El ingrediente a almacenar.
     */
    public Silo(Ingrediente ingredienteAlmacenado)
    {
        //TODO: Implementar metodo
        super();
        this.ingrediente = ingredienteAlmacenado;
        super.cantidadDisponible = 0;
    }
    
    /**
     * Devuelve el tipo de ingrediente que almacena el recipiente. 
     * 
     * @return  El ingrediente almacenado.
     */
    public Ingrediente getTipoIngredienteAlmacenado()
    {
        //TODO: Implementar metodo
        return ingrediente;
    }
    
    /**
     * Agrega contenido al recipiente.
     * 
     * @param  cantidadAgregada             La cantidad de contenido a agregar.
     * @throws CapacidadExcedidaException   Cuando intenta agregarse más contenido del que el recipiente puede alojar.
     * @throws IllegalArgumentException     Cuando la cantidad a agregar es un valor negativo.
     */
    public void agregar(int cantidadAgregada) throws CapacidadExcedidaException
    {
        //TODO: Implementar metodo
        if(cantidadAgregada + super.getCantidadIngredienteDisponible() > super.getCapacidadMaxima())
        {
            throw new CapacidadExcedidaException();
        }
        if(cantidadAgregada < 0)
        {
            throw new IllegalArgumentException();
        }
        cantidadDisponible += cantidadAgregada;
    }
        
    /**
     * Extrae contenido del recipiente.
     * 
     * @param  cantidadAExtraer             La cantidad a extraer.
     * @throws CapacidadExcedidaException   Cuando intenta extraerse una cantidad mayor a la disponible en el recipiente.
     * @throws IllegalArgumentException     Cuando la cantidad a extraer es un valor negativo.
     */
    public void extraer(int cantidadAExtraer) throws CapacidadExcedidaException
    {
        //TODO: Implementar metodo
        if(cantidadAExtraer > super.getCantidadIngredienteDisponible())
        {
            throw new CapacidadExcedidaException();
        }
        if(cantidadAExtraer < 0)
        {
            throw new IllegalArgumentException();
        }
        cantidadDisponible -= cantidadAExtraer;
    }
}