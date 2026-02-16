import java.util.ArrayList;
import java.util.List;

/**
 * Modelo simplificado de una calle de 2 vías.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 20/20
 */
public class Calle
{
    private String  nombre;
    private Integer longitud;                               // Longitud [mts]
    private List<InterseccionInterface> intersecciones;

    /**
     * Inicialización:
     * 
     * @param nombre    Nombre de la calle.
     * @param longitud  longitud máxima disponible.
     */
    public Calle(String nombre, Integer longitud)
    {
        //Completar
        this.nombre = nombre;
        this.longitud = longitud;
        this.intersecciones = new ArrayList<>();
    }

    /**
     * Agrega una nueva interseccion a esta calle no puede contener intersecciones repetidas.
     * 
     * @param inte  Intersección a ser agregada.
     */
    public void agregar_interseccion(InterseccionInterface inte)
    {
        //Completar
        if(!this.intersecciones.contains(inte))
        {
            this.intersecciones.add(inte);
        }
    }

    /**
     * @return  Longitud de la calle.
     */
    public Integer get_longitud()
    {
        return longitud;
    }

    /**
     * @return  Lista con todas las intersecciones por las que es afectada esta calle.
     */
    public List<InterseccionInterface> get_intersecciones()
    {
        //Completar
        return this.intersecciones;
    }

    /**
     * @return  Devuelve el nombre de la calle.
     */
    @Override
    public String toString()
    {
        //Completar
        return nombre;
    }
}