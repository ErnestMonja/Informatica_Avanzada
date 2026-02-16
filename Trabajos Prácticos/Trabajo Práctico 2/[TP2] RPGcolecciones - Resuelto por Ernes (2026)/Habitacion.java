import java.util.TreeMap;
import java.util.Iterator;

/**
 * Clase Habitacion - Una habitacion en un juego de aventuras.  Esta clase es parte de la apliciacion "World of Zuul".
 *
 * Un objeto "Habitacion" representa una ubicación en el juego. Las habitaciones tienen salidas que conducen a otras habitaciones, 
 * indicadas como norte, sur, este y oeste. Para cada dirección, una habitación mantiene una referencia a la habitacion vecina, o 
 * null si no existe una en es dirección.
 * 
 * El código ha sido adaptado de la clase Habitacion del proyecto "Mundo de Zuul" para el TP2 de Informática Avanzada.
 *
 * @author  Maximiliano A. Eschoyez y resuelto por Ernes.
 * @version 1.0
 *
 *                                                      [PUNTAJE xx/30]
 */
public class Habitacion 
{
    protected TreeMap<Salida, Habitacion> salidas;      // Almacena las salidas de esta habitación
    protected TreeMap<String, Elemento> elementos;      // Almacena los elementos de la habitación
    
    /* **************************   Modificacion para el TP   ************************** */
    /* ************************** Informatica Avanzada - 2021 ************************** */
    // Esta version utiliza la informacion del enumerado LugaresMapa
    protected LugaresMapa lugar;
    
    /**
     * Crea una habitación descrita por "descripcion". Inicialmente, la habitación no tiene salidas, "descripcion"
     * es algo así como "una cocina" o "un patio".
     * 
     * Se incorpora el String nombre y el TreeMap de elementos para adaptarlo al TP de Informatica Avanzada.
     * 
     * @param lugar     El lugar del mapa al que corresponde esta habitación.
     */
    public Habitacion(LugaresMapa lugar) 
    {
        // TODO - Modificar metodo
        this.salidas = new TreeMap<>();
        this.elementos = new TreeMap<>();
        this.lugar = lugar;
    }

    /**
     * Devuelve el nombre de la habitación.
     * 
     * @return  El nombre de la habitación.
     */
    public String getNombre()
    {
        // TODO - Implementar metodo
        return this.lugar.getNombre();
    }

    /**
     * @return  La descripción corta de esta habitación (la que se definió en el constructor).
     */
    public String getDescripcionCorta()
    {
        // TODO - Implementar metodo
        return this.lugar.getDescripcion();
    }

    /**
     * Devuelve una descripcion de la habitacion en la forma:
     *         "Usted esta en la cocina
     *          Salidas: norte oeste
     *          Elementos: Pan Pluma Flecha"
     * 
     * MODIFICAR el método para que la descripción incluya la lista de elementos.
     * 
     * @return  Una descripcion larga de esta habitación.
     */
    public String getDescripcionLarga()
    {
        // TODO - Modificar metodo
        String info = "Usted esta en " + this.getNombre() + "\n"
                    + this.getStringDeSalidas() + "\n"
                    + this.getStringDeElementos();
        return info;
    }

    /**
     * Agrega un elemento a la habitación. Si el elemento es de tipo líquido, no se agrega al mapa y se debe imprimir:
     *          "<nombre>: se acaba de derramar en el piso"
     * donde <nombre> es el nombre del líquido derramado.
     * 
     * @param elemento  El elemento a agregar.
     */
    public void addElemento(Elemento elemento)
    {
        // TODO - Implementar metodo
        if(elemento.getTipo() == TipoElemento.LIQUIDO)
        {
            System.out.println(elemento.getNombre() + ": se acaba de derramar en el piso");
        }
        else
        {
            elementos.put(elemento.getNombre(), elemento);
        }
    }

    /**
     * Quita el elemento de la habitacion.
     * 
     * Si el elemento es de tipo fijo (parte del escenario) no debe quitarlo, devuelve null e imprime el mensaje:
     *          "<nombre>: No se puede tomar"
     * donde <nombre> es el elemento solicitado.
     * 
     * Si el elemento no existe, devuelve null e imprime el mensaje:
     *          "<nombre>: No existe"
     * donde <nombre> es el elemento solicitado.
     * 
     * @param nombre    El nombre del elemento a tomar.
     * @return          El elemento a tomar.
     */
    public Elemento getElemento(String nombre)
    {
        // TODO - Implementar metodo
        if(elementos.containsKey(nombre))
        {
            Elemento e = elementos.get(nombre);
            if(e.getTipo() == TipoElemento.FIJO)
            {
                System.out.println(nombre + ": No se puede tomar");
                return null;
            }
            else
            {
                elementos.remove(nombre);
                return e;
            }
        }
        else
        {
            System.out.println(nombre + ": No existe");
            return null;
        }
    }

    /**
     * Devuelve una cadena describiendo las salidas de la habitación, por ejemplo:
     *          "Elementos: Pan Pluma Flecha"
     *          
     * En caso de no haber elementos, devuelve la cadena:
     *          "No hay elementos en este lugar"
     * 
     * @return Detalles de las salidas de la habitación.
     */
    protected String getStringDeElementos()
    {
        // TODO - Implementar metodo
        String cadena = "";
        if(elementos.size() <= 0)
        {
            cadena += "No hay elementos en este lugar";
        }
        else
        {
            cadena += "Elementos:";
            for(Elemento e : elementos.values())
            {
                cadena += " " + e.getNombre();
            }
        }
        return cadena;
    }

    /**
     * Devuelve la habitación a la que se llega sí vamos desde esta habitación en la dirección indicada. Si no hay habitaciones en esa dirección,
     * se devuelve a sí misma e imprime el mensaje:
     *          "No hay salida en direccion <direccion>"
     * donde <direccion> es la direccion indicada.
     * 
     * @param direccion     La dirección de salida.
     * @return              La habitación en la dirección indicada.
     */
    public Habitacion getSalida(Salida direccion) 
    {
        // TODO - Modificar metodo
        if(salidas.containsKey(direccion))
        {
            return salidas.get(direccion);
        }
        else
        {
            System.out.println("No hay salida en direccion " + direccion);
            return this;
        }
    }

    /**
     * Define las salidas de esta habitacion.
     * 
     * @param direccion     La dirección de la salida.
     * @param vecina        La habitación a la cual esta salida conduce.
     */
    public void establecerSalida(Salida direccion, Habitacion vecina) 
    {
        salidas.put(direccion, vecina);
    }

    /**
     * Devuelve una cadena describiendo las salidas de la habitacion, por ejemplo:
     *          "Salidas: norte oeste"
     * 
     * @return      Detalles de las salidas de la habitación.
     */
    private String getStringDeSalidas()
    {
        String cadena = "Salidas:";
        for(Salida salida : salidas.keySet())
        {
            cadena += " " + salida;
        }
        return cadena;
    }
}