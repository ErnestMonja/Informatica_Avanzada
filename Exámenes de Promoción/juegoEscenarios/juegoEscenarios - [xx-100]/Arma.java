import java.util.ArrayList;
import java.util.List;

/**
 * La clase Arma a completar.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.1
 *                                              PUNTAJE: xx/20
 */
public class Arma extends Elemento
{
    private List<Municion> municiones;
    private TipoMunicion tipoMunicion;

    /**
     * Constructor de Arma.
     * 
     * @param   nombre
     * @param   tipoMunicion
     */
    public Arma(String nombre, TipoMunicion tipoMunicion)
    {
        // TODO - Implementar el constructor
        super(nombre);
        this.municiones = new ArrayList<>();
        this.tipoMunicion = tipoMunicion;
    }

    /**
     * Agrega la munición a la lista de municiones.
     * 
     * Lanza MunicionNoValidaException si la munición no coincide con el tipo de munición del arma.
     * 
     * @param  m                            La munición a cargar.
     * @throws MunicionNoValidaException    Si la munición no coincide con el tipo de munición del arma.
     */
    public void cargar(Municion m) throws MunicionNoValidaException
    {
        // TODO - Implementar el metodo
        if(m.getTipo() != this.tipoMunicion)
        {
            throw new MunicionNoValidaException("");
        }
        municiones.add(m);
    }

    /**
     * Remueve la primera munición de la lista y retorna el daño de la misma.
     * 
     * Arroja ArmaDescargadaException si no hay municiones.
     * 
     * @return                          El valor de daño de la munición removida.
     * @throws ArmaDescargadaException  Si no hay municiones disponibles.
     */
    public Integer disparar() throws ArmaDescargadaException
    {
        // TODO - Implementar el metodo
        if(this.municiones.isEmpty())
        {
            throw new ArmaDescargadaException("");
        }
        Municion municionDisparada = this.municiones.remove(0);
        return municionDisparada.getDanio();
    }

    /**
     * Verifica si existen municiones cargadas en la lista.
     * 
     * @return  true si hay municiones cargadas, false de lo contrario.
     */
    public Boolean isCargada()
    {
        // TODO - Implementar el metodo
        if(this.municiones.isEmpty())
        {
            return false;
        }
        return true;
    }
}
