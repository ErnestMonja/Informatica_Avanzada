import java.util.TreeMap;

/**
 * Clase Habitacion - Una habitacion en un juego de aventuras.  Esta clase es parte de la apliciacion "World of Zuul".
 *
 * Un objeto "Habitacion" representa una ubicación en el juego. Las habitaciones tienen salidas que conducen a otras habitaciones, 
 * indicadas como norte, sur, este y oeste. Para cada dirección, una habitación mantiene una referencia a la habitacion vecina, o 
 * null si no existe una en es dirección.
 * 
 * El código ha sido adaptado de la clase Habitacion del proyecto "Mundo de Zuul" para el TP3 de Informática Avanzada.
 *
 * @author  Maximiliano A. Eschoyez y resuelto por Ernes.
 * @version 1.0
 *
 *                                                      [PUNTAJE xx/30]
 */
public class Habitacion implements Contenedor
{
    protected TreeMap<Salida, Habitacion> salidas;      // Almacena las salidas de esta habitación
    protected TreeMap<String, Elemento> elementos;      // Almacena los elementos portables y decorado de la habitación
    private Liquido derramado;
    private EstadoContenedor estado;
    
    /* **************************   Modificacion para el TP   ************************** */
    /* ************************** Informatica Avanzada - 2021 ************************** */
    // Esta version utiliza la información del enumerado LugaresMapa
    protected LugaresMapa lugar;
    
    /**
     * Crea una habitación descrita por "descripcion". Inicialmente, la habitación no tiene salidas, "descripcion"
     * es algo así como "una cocina" o "un patio".
     * 
     * Se incorpora el String nombre y el TreeMap de elementos, el liquido derramado y el estado para adaptarlo al TP
     * de Informática Avanzada.
     * 
     * Se inicializa en estado vacio (ver Arroyo y Barrica a modo de ejemplo de los estados)
     * 
     * @param lugar     El nombre y la descripción de la habitación.
     */
    public Habitacion(LugaresMapa lugar) 
    {
        // TODO - Modificar metodo
        this.salidas = new TreeMap<>();
        this.elementos = new TreeMap<>();
        this.lugar = lugar;
        
        this.derramado = null;
        this.estado = new Vacia();
    }

    /**
     * Devuelve una descripción de la habitación en la forma:
     *          El camino que te lleva al pueblo.
     *          Se ha derramado Agua en el piso.
     *          Salidas: norte oeste
     *          Elementos: Pan Pluma Flecha
     * 
     * MODIFICAR el método para que la descripción:
     *  1) Incluya la lista de elementos,
     *  2) Si el campo derramado contiene un líquido, que agrege la línea: "Se ha derramado <liquido> en el piso".
     * 
     * @return  Una descripcion larga de esta habitacion
     */
    public String getDescripcionLarga()
    {
         // TODO - Modificar metodo
         String info = this.getDescripcionCorta() + "\n"; 
         if(this.getDerramado() != null)
         {
             info += "Se ha derramado " + this.getDerramado().getNombre() + " en el piso\n";
         }
         info += this.getStringDeSalidas() + "\n" 
               + this.getStringDeElementos();
         return info;
    }

    /**
     * Devuelve una cadena describiendo las salidas de la habitacion, por ejemplo:
     *          "Elementos: Pan Pluma Flecha".
     *          
     * En caso de no haber elementos, devuelve la cadena:
     *          "No hay elementos en la habitacion"
     * 
     * El texto apropiado se obtiene del metodo toString() sobreescrito en las clases privadas Vacia y ConElementos (estados).
     * 
     * @return Detalles de las salidas de la habitacion
     */
    protected String getStringDeElementos()
    {
        // TODO - Implementar funcionalidad en las clases privadas
        return estado.toString();
    }

    /**
     * Devuelve la habitación a la que se llega sí vamos desde esta habitación en la dirección indicada. Si no hay habitación en
     * esa dirección, lanza excepción con el mensaje:
     *          "No hay salida en direccion <direccion>"
     * donde <direccion> es la dirección indicada.
     * 
     * @param direccion     La dirección de salida
     * @return              La habitación en la dirección indicada
     */
    public Habitacion getSalida(Salida direccion) throws AccionNoPermitidaException
    {
        // TODO - Modificar metodo
        if(this.salidas.containsKey(direccion)) 
        {
            return salidas.get(direccion);
        }
        else
        {
            throw new AccionNoPermitidaException("No hay salida en direccion " + direccion.toString());
        }
    }

/* ***************************************************************** Patron State ***************************************************************** */
    /**
     * Delega metodos para acceder a los métodos apropiados en base al estado del objeto.
     * 
     * Agrega un elemento a la habitación. Si el elemento es de la familia de los líquidos, no se agrega al mapa, sino al campo "derramado", y se 
     * debe cambiar la descripción de la habitación.
     * 
     * @param elemento                      El elemento a agregar.
     * @throws ContenedorLlenoException     No aplica.
     * @throws AccionNoPermitidaException   No aplica.
     */
    @Override
    public void addElemento(Elemento elemento) throws ContenedorLlenoException, AccionNoPermitidaException
    {
        estado.addElemento(elemento);
    }

    /**
     * Delegate methods para acceder a los metodos apropiados en base al estado del objeto.
     * 
     * Brinda acceso a un elemento en la habitación. Si el objeto es portable, lo quita del mapa. Si es decorado, devuelve la referencia sin quitarlo.
     * 
     * @param  nombre                       El nombre del elemento a acceder.
     * @return                              El elemento a acceder.
     * @throws ContenedorVacioException     En caso que la habitación este vacía con el mensaje: "No hay elementos en la habitacion".
     * @throws AccionNoPermitidaException   En caso que el elemento no exista con el mensaje: "No existe el elemento <nombre>"
     *                                      (<nombre> == nombre del elemento).
     */
    public Elemento getElemento(String nombre) throws ContenedorVacioException, AccionNoPermitidaException
    {
        return estado.getElemento(nombre);
    }
    
    /**
     * Se utiliza la version implementada en EstadoContenedor, que siempre lanza la excepcion AccionNoPermitidaException.
     */    
    public Elemento getElemento() throws ContenedorVacioException, AccionNoPermitidaException
    {
        return estado.getElemento();
    }

    /*
     * Implementar los métodos de las clases privadas que sean necesarios para cada estado.
     * 
     * No olvidar:
     *  1) contemplar las situaciones que producen el cambio a otro estado,
     *  2) sobreescribir el metodo toString() para reportar los elementos de la habitacion.
     * 
     * Ver lo implementado en Arroyo y Barrica. Considerar las pautas en Botella.
     */
    /**
     * La clase VACIA indica el estado de la Habitacion sin elementos. Debe cambiar al estado CONELEMENTOS al agregarse un elemento no líquido.
     */
    private class Vacia extends EstadoContenedor
    {
        // TODO - Implementar la clase privada
        @Override
        public void addElemento(Elemento elemento) throws ContenedorLlenoException, AccionNoPermitidaException
        {
            if(elemento instanceof Liquido)
            {
                Liquido l = (Liquido) elemento;
                derramado = l;
            }
            else
            {
                elementos.put(elemento.getNombre(), elemento);
                estado = new ConElementos();   
            }
        }
        
        public Elemento getElemento(String nombre) throws ContenedorVacioException, AccionNoPermitidaException
        {
            throw new ContenedorVacioException("No hay elementos en la habitacion");
        }
        
        @Override
        public String toString()
        {
            String info = "No hay elementos en la habitacion";           
            return info;
        }
    }

    /**
     * La clase CONELEMENTOS indica el estado de la Habitacion con al menos un (1) elemento. Debe cambiar al estado VACIA al quitarse el último elemento.
     */
    private class ConElementos extends EstadoContenedor
    {
        // TODO - Implementar la clase privada
        @Override
        public void addElemento(Elemento elemento) throws ContenedorLlenoException, AccionNoPermitidaException
        {
            if(elemento instanceof Liquido)
            {
                Liquido l = (Liquido) elemento;
                derramado = l;
            }
            else
            {
                elementos.put(elemento.getNombre(), elemento);
            }
        }
        
        public Elemento getElemento(String nombre) throws ContenedorVacioException, AccionNoPermitidaException
        {
            if(elementos.containsKey(nombre))
            {
                Elemento e = elementos.get(nombre);
                if(e instanceof Portable)
                {
                    elementos.remove(e.getNombre());
                }
                if(elementos.size() == 0)
                {
                    estado = new Vacia();
                }
                return e;
            }
            else
            {
                throw new AccionNoPermitidaException("No existe el elemento " + nombre);
            }
        }
        
        @Override
        public String toString()
        {
            String info = "Elementos:";
            for(Elemento e : elementos.values())
            {
                info += " " + e.getNombre(); 
            }
            return info;
        }
    }
/* *************************************************************** FIN Patron State **************************************************************** */

    /**
     * Devuelve el nombre de la habitacion.
     * 
     * @return  El nombre de la habitacion.
     */
    public String getNombre()
    {
        return lugar.getNombre();
    }

    /**
     * @return  La descripción corta de esta habitación (la que se definió en el constructor).
     */
    public String getDescripcionCorta()
    {
        return lugar.getDescripcion();
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
     * @return  Detalles de las salidas de la habitación.
     */
    private String getStringDeSalidas()
    {
        String texto = "Salidas:";
        for(Salida salida : salidas.keySet())
        {
            texto += " " + salida;
        }
        return texto;
    }

    public EstadoContenedor getEstado()
    {
        return estado;
    }
    
    public Liquido getDerramado()
    {
        return derramado;
    }
}