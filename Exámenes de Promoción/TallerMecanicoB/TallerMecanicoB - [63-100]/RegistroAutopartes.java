import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.ArrayList;

/**
 * La clase RegistroAutopartes.
 * 
 * Nota: Si usted descomprime el zip de este proyecto, verá que esta clase no existe y se espera que usted genere la clase, y copie el 
 * código provisto por el lev para comenzar a resolver los métodos.
 * 
 * Recomiendo revisar la implementación de estos métodos debido al bajo puntaje que obtuvo en las pruebas para ser una clase relativamente
 * facil (solo es manejo de un mapa básicamente).
 *      
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 17/30
 */
public class RegistroAutopartes
{
    private static RegistroAutopartes singleton = null; 
    private Map<String, Item> catalogo;                     // La key es el ID del Item

    // Constructor Privado
    private RegistroAutopartes()
    {
        this.catalogo = new HashMap<>();
    }

    public Map<String, Item> getCatalogo()
    {
        return catalogo;
    }

    /**
     * Este metodo asegura que siempre se devuelva el mismo objeto registro desde cualquier lado que se llame. Ej:
     * 
     * RegistroAutopartes miRegistro = RegistroAutopartes.getRegistro();
     * 
     * @return  El registro existente.
     */
    public static RegistroAutopartes getRegistro()
    {
        if(singleton == null)
        {
            singleton =  new RegistroAutopartes();
        }
        return singleton;
    }

    /**
     * Limpia el registro, dejandolo vacío.
     */
    public void limpiarRegistro()
    {
        this.catalogo.clear();
    }
    
    /**
     * Agrega una autoparte al registro. 
     * 
     * @param  parte                    La autoparte a agregar.
     * @throws IllegalArgumentException Si la autoparte es null.
    */
    public void agregarAutoParte(Item parte)
    {
        // TODO implementar metodo
        if(parte == null)
        {
            throw new IllegalArgumentException();
        }
        this.catalogo.put(parte.getId(), parte);
    }

    /**
     * Remueve una autoparte del registro.
     * 
     * @param  id                           El ID de la autoparte a quitar del registro.
     * @throws IllegalArgumentException     Si el ID es null o cadena vacía.
     */
    public void removerAutoParte(String id)
    {
        // TODO implementar metodo
        if((id == null)||(id.equals("")))
        {
            throw new IllegalArgumentException();
        }
        
        Iterator<String> it = this.catalogo.keySet().iterator();
        while(it.hasNext())
        {
            String idIterator = it.next();
            if(idIterator.equals(id))
            {
                it.remove();
            }
        }
    }

    /**
     * Verifica si una autoparte existe en el registro.
     * 
     * @param  id                           El ID de la autoparte a buscar.
     * @throws IllegalArgumentException     Si el ID es null o cadena vacía.
     */
    public Boolean contains(String id)
    {
        // TODO implementar metodo
        if((id == null)||(id.equals("")))
        {
            throw new IllegalArgumentException();
        }
        
        if(this.catalogo.containsKey(id))
        {
            return true;
        }
        return false;
    }

    // Los metodos de listas son para hacer distintas preguntas
    /**
     * Genera una lista de las autopartes registradas
     * por orden alfabético de la descripción.
     * 
     * El formato de cada fila es el siguiente:
     * "<ID> | <descripcion> | <precio>"
     * 
     * por ej. : "bat60a12v | Bateria auto | $56000.0"
     * (considerar espacios en blanco)
     * 
     * @return Una lista ordenada por descripción
     */
    public List<String> getListadoPorDescripcion()
    {
        // TODO implementar metodo                                              // Creo que en este método, se deberían recorer los valores en vez de las llaves, tal que:
        TreeSet<String> lista = new TreeSet<>();                                // TreeSet<String> lista = new TreeSet<>();
        for(String s : this.catalogo.keySet())                                  // for(Item i : this.catalogo.values())
        {                                                                       // {
            String info = this.catalogo.get(s).getId() + " | "                  //     String info = i.getId() + " | " 
                        + this.catalogo.get(s).getDescripcion() + " | $"        //                 + i.getDescripcion() + " | $"
                        + this.catalogo.get(s).getPrecio();                     //                 + i.getPrecio();
            lista.add(info);                                                    //     lista.add(info);
        }                                                                       // }
        return new ArrayList<String>(lista);                                    // return new ArrayList<String>(lista);
    }

    public Integer cantidadRegistros()
    {
        return catalogo.size();
    }

    @Override
    public String toString()
    {
        return "RegistroAutopartes [size=" + cantidadRegistros() + ", catalogo=" + catalogo + "]";
    }    
}