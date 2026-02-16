import java.util.*;

/**
 * Clase Mapa a completar.
 * 
 * Nota: Según las pruebas que se pueden ver, estas indican que: "realizarMovimiento desplaza al personaje a una posicion ocupada por 
 * otro personaje" donde se refiere a que el método .moverPersonajeEnDireccion() no funciona adecuadamente. Queda a su disposición intentar
 * corregir este bug.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 37/40
 */
public class Mapa
{
    private final static int DISTANCIA_VISUALIZACION = 5;
    private static final int TAMANO_DEFAULT = 100;
    private final int tamano;
    private List<Personaje> personajes;
    
    /**
     * Inicializacion de mapa. Guarda el tamanio e inicializa la lista de personajes.
     *
     * @param tamano    Se asume un tablero cuadrado del valor recibido.
     */
    public Mapa(int tamano)
    {
        if(tamano < 1)
        {
            this.tamano = TAMANO_DEFAULT;
        }
        else
        {
            this.tamano = tamano;
        }
        this.personajes = new ArrayList<Personaje>();
    }

    /**
     * Agrega un personaje a la lista en caso de que no se encuentre en ella.
     * 
     * Si el nombre del personaje ya se encuentra en la lista arroja una excepcion
     *
     * @param p     Personaje a ser agregado.
     */
    public boolean agregarPersonaje(Personaje p) throws NombreDuplicadoException
    {
         // Implementar
         for(Personaje per : personajes)
         {
             if(per.getNombre().equals(p.getNombre()))
             {
                 throw new NombreDuplicadoException("");
             }
         }
         personajes.add(p);
         return true;
    }

    /**
     * Elimina el personaje de la lista en caso de que exista, si el personaje no existe en la lista devuelve False, 
     * de lo contrario retorna True.
     *
     * @param p     Personaje a ser eliminado
     */
    public boolean eliminarPersonaje(Personaje p)
    {
        // Implementar
        if(this.personajes.contains(p))
        {
            personajes.remove(p);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Elimina el personaje de la lista en base a su nombre si el personaje no existe en la lista devuelve False, 
     * de lo contrario retorna True
     * 
     * @param nombre    Nombre del personaje a ser eliminado 
     */ 
    public boolean eliminarPersonaje(String nombre)
    {
        // Implementar
        Iterator<Personaje> it = personajes.iterator();
        while(it.hasNext())
        {
            Personaje p = it.next();
            if(p.getNombre().equals(nombre))
            {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * @return Devuelve la lista de personajes
     */
    List<Personaje> getPersonajes()
    {
        return personajes;
    }

    /**
     * Mueve el personaje especificado en la dirección indicada. Si es posible, setea una nueva posición al personaje.
     * 
     * Debe cumplirse que:
     *      1. La nueva posición este dentro de los limites del tablero
     *      2. La nueva posición este disponible (-> no este ocupada por otro personaje)
     * 
     * Ayuda: Utilizar metodos privados de esta misma clase (posicionDisponible, nuevaPosicion -> clase Posicion)
     * 
     * @param p     El Personaje
     * @param d     La Direccion
     * @return      true si se pudo efectuar el movimiento, false si no se pudo.
     */
    public boolean moverPersonajeEnDireccion(Personaje p, Direccion d)
    {
        // Implementar
        Posicion nuevaPos = p.getPosicion().nuevaPosicion(d);
            
        // ¿Se encuentra esta posición dentro de los límites del tablero?
        if( (nuevaPos.getX() < 0)||(nuevaPos.getX() >= tamano)||
            (nuevaPos.getY() < 0)||(nuevaPos.getY() >= tamano)  )
        {
            return false;
        }
        if(!this.posicionDisponible(nuevaPos))
        {
            return false;
        }
        return p.setPosicion(nuevaPos);
    }


    /**
     * @param nuevapos  Posición tentativa del personaje. No debe haber ningún personaje en esa posición
     * @return          true si la posición esta disponible, false si no lo está.
     */
    public boolean posicionDisponible(Posicion nuevapos)
    {
        // Implementar
        for(Personaje p : personajes)
        {
            if(p.getPosicion().equals(nuevapos))
            {
                return false;
            }
        }
        return true; 
    }

    /**
     * Retorna una lista con los personajes que se encuentran cercanos a un personaje.
     * 
     * El personaje se considera cercano si la distancia entre los dos personajes, tanto en x como en y, es menor a la distancia de visualización.
     * 
     * AYUDA: Ningún personaje puede estar cerca de sí mismo (la lista no debe incluir una referencia a sí mismo)
     *  
     * @param  personaje    el Personaje
     * @return              Una List<Personaje> con los personajes cercanos
     */
    List<Personaje> obtenerPersonajesCercanos(Personaje personaje)
    {
        // Implementar
        List<Personaje> personajesCercanos = new ArrayList<>();
        for(Personaje p : personajes)
        {
            if(p.equals(personaje))  // Ningún personaje puede estar cerca de sí mismo
            {
                continue;
            }
        
            int dx = Math.abs(personaje.getPosicion().getX() - p.getPosicion().getX());
            int dy = Math.abs(personaje.getPosicion().getY() - p.getPosicion().getY());
            if((dx < DISTANCIA_VISUALIZACION)&&(dy < DISTANCIA_VISUALIZACION))
            {
                personajesCercanos.add(p);
            }
        }
        return personajesCercanos;
    }

     /**
     * Retorna la cantidad de Heroes de una facción determinada
     * 
     * @param  f    La facción.
     * @return      El número de heroes de esa facción.
     */
    int obtenerNroHeroesDeFaccion(Faccion f)
    {
        // Implementar
        int nroHeroesEnFaccion = 0;
        for(Personaje p : personajes)
        {
            if(p instanceof Heroe)
            {
                Heroe h = (Heroe) p;
                if(h.getFaccion().equals(f))
                {
                    nroHeroesEnFaccion++;
                }
            }
        }
        return nroHeroesEnFaccion;
    }
}