import java.util.*;

/**
 * Completar los métodos de GestorSalas para:
 *  - Agregar salas (agregarSala).
 *  - Eliminar salas por nombre (eliminarSala).
 *  - Modificar capacidad y ubicación (modificarSala).
 *  - Buscar una sala por nombre (buscarSala).
 *  - Obtener la lista completa (getTodas).
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: xx/20
 */
public class GestorSalas
{
    private List<Sala> salas = new ArrayList<>();

    /**
     * Agrega una Sala a la lista de salas del gestor
     */
    public void agregarSala(Sala sala)
    {
        //TODO Implementar
        if(sala != null)
        {
            salas.add(sala);
        }
    }

    /**
     * Elimina de la lista la Sala cuyo nombre coincide con el nombre pasado como argumento.
     */
    public void eliminarSala(String nombre)
    {
        //TODO Implementar
        Iterator<Sala> it = salas.iterator();
        while(it.hasNext())
        {
            Sala s = it.next();
            if(s.getNombre().equals(nombre))
            {
                it.remove();
                return;
            }
        }
    }

    /**
     * Actualiza la capacidad y ubicación de la Sala cuyo nombre coincide con el nombre pasado como argumento.
     */
    public void modificarSala(String nombre, int nuevaCapacidad, Ubicacion nuevaUbicacion)
    {
        //TODO Implementar
        for(Sala s : salas)
        {
            if(s.getNombre().equals(nombre))
            {
                s.setCapacidad(nuevaCapacidad);
                s.setUbicacion(nuevaUbicacion);
            }
        }
    }

    /**
     * Devuelve la Sala cuyo nombre coincide con el nombre pasado como argumento.
     */
    public Sala buscarSala(String nombre)
    {
        //TODO Implementar
        for(Sala s : salas)
        {
            if(s.getNombre().equals(nombre))
            {
                return s;
            }
        }
        return null;
    }

    /**
     * Devuelve una lista con todas las Salas del gestor de Salas
     */
    public List<Sala> getTodas()
    {
        //TODO Implementar
        return this.salas;
    }
}