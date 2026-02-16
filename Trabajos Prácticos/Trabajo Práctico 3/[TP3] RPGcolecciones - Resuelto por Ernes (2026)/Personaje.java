/**
 * Esta clase modela un personaje de un juego de rol.
 * 
 * @author  Maximiliano A. Eschoyez y resuelto por Ernes.
 * @version 1.0
 *
 *                                                      [PUNTAJE xx/30]
 */
public class Personaje
{
    private final Integer MAX_VIDA;
    private final Integer PESO_MAXIMO_BOLSA;
    
    private String nombre;                          // nombre     -> El nombre del personaje.
    private Integer vida;                           // vida       -> El valor actual de vida.
    private Elemento objeto;                        // objeto     -> El elemento que tiene en sus manos, puede tener las manos vacias (null).
    private Bolsa bolsa;                            // bolsa      -> La bolsa de elementos.
    private Habitacion habitacion;                  // habitacion -> El lugar donde se encuentra el personaje.

    /**
     * Crea un personaje con el nombre dado y configura los campos según vida, MAX_VIDA y PESO_MAXIMO_BOLSA.
     * 
     * El personaje se inicializa sin bolsa y con las manos vacías en la habitación inicial del mapa (getInicio()).
     * 
     * @param nombre    El nombre del personje.
     * @param vida      El valor inicial y maximo de vida del personaje.
     * @param peso      El peso maximo que puede transportar el personaje.
     */
    public Personaje(String nombre, Integer vida, Integer peso)
    {
        // TODO - Implementar constructor
        this.nombre = nombre;
        this.vida = vida;
        this.MAX_VIDA = vida;
        this.PESO_MAXIMO_BOLSA = peso;
        
        this.objeto = null;
        this.bolsa = null;
        this.habitacion = Mapa.getInstance().getInicio();
    }

    /**
     * Imprime en pantalla la informacion sobre el lugar donde se encuentra.
     */
    public void mirarAlrededor()
    {
        System.out.println(habitacion.getDescripcionLarga());
    }

    /**
     * El personaje se mueve a la habitación en la dirección indicada. Si la dirección no es válida, se queda donde estaba
     * y lo indica imprimiendo el mensaje que trae la excepción lanzada por habitación.
     * 
     * @param direccion     Dirección por donde salir de la habitación.
     */
    public void irHacia(Salida direccion)
    {
        // TODO - Implementar metodo
        try
        {
            this.setHabitacion(this.getHabitacion().getSalida(direccion));
        }
        catch(AccionNoPermitidaException e)
        {
            System.out.println(e);
        }
    } 

    /**
     * Guarda el elemento que tiene en sus manos en la bolsa, siempre y cuando haya lugar suficiente. Las manos quedan vacías (null).
     * 
     * Si las manos estan vacías (null), mostrar el mensaje:
     *          "No hay elemento para agregar a la bolsa"
     * 
     * Si no hay bolsa, mostrar:
     *          "<nombre>: No tiene bolsa"
     * donde <nombre> es el nombre del personaje.
     * 
     * En caso de no poder guardarse el elemento, mostrar el mensaje que trae la excepción.
     */
    public void guardarElemento()
    {
        // TODO - Implementar metodo
        if(this.getElemento() == null)
        {
            System.out.println("No hay elemento para agregar a la bolsa");
            return;
        }
        if(this.getBolsa() == null)
        {
            System.out.println(this.getNombre() + ": No tiene bolsa");
            return;
        }
        
        // Intento añadir el elemento a la bolsa considerando las posibles excepciones que lanzan el método addElemento
        try
        {
            this.getBolsa().addElemento(this.getElemento());
            this.setElemento(null);
        }
        catch(ContenedorLlenoException e)
        {
            System.out.println(e);
        }
        catch(AccionNoPermitidaException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Toma un elemento de la bolsa (getElemento) y lo pone en las manos del personaje.
     * 
     * Si tiene un elemento en sus manos, imprimir:
     *          "Manos ocupadas".
     * 
     * Si no hay bolsa, mostrar:
     *          "<nombre>: No tiene bolsa"
     * donde <nombre> es el nombre del personaje.
     * 
     * Si no existe el elemento se debe imprimir
     *          "No se cuenta con el <nombre>"
     * donde <nombre> es el nombre del elemento buscado.
     * 
     * Si la bolsa no tiene elementos (vacia), imprime el mensaje que trae la excepcion.
     * 
     * @param nombre    El elemento a tomar de la bolsa.
     */
    public void tomarElemento(String nombre)
    {
        // TODO - Implementar metodo
        if(this.getElemento() != null)
        {
            System.out.println("Manos ocupadas");
            return;
        }
        if(this.getBolsa() == null)
        {
            System.out.println(this.getNombre() + ": No tiene bolsa");
            return;
        }
        
        try
        {
            this.setElemento(this.getBolsa().getElemento(nombre));
        }
        catch(ContenedorVacioException e)
        {
            System.out.println(e);
        }
        catch(AccionNoPermitidaException e)
        {
            System.out.println("No se cuenta con el " + nombre);
        }
    }
    
    /**
     * Toma de la habitación el elemento indicado con sus manos. De ser necesario, debe guardar en la bolsa lo que tenga en sus manos.
     *
     * Si el elemento no existe o no se puede tomar, debe seguir sosteniendo el elemento que tenía en sus manos.
     * 
     * En caso de no poder tomar el objeto, mostrar el mensaje que trae la excepción.
     * 
     * @param nombre    Nombre del objeto a recoger de la habitación.
     */
    public void recogerElemento(String nombre)
    {
        // TODO - Implementar metodo
        try
        {
            Elemento e = this.getHabitacion().getElemento(nombre);
            if(this.getElemento() != null)
            {
                this.guardarElemento();
            }
            this.setElemento(e);
        }
        catch(ContenedorVacioException e)
        {
            System.out.println(e);
        }
        catch(AccionNoPermitidaException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Deja en la habitación el elemento que tiene en sus manos. Las manos quedan vacías (null).
     * 
     * Si las manos estan vacías, imprime el mensaje:
     *          "No hay objeto para dejar"
     */
    public void dejarElemento()
    {
        // TODO - Implementar metodo
        if(this.getElemento() == null)
        {
            System.out.println("No hay objeto para dejar");
            return;
        }
        
        try
        {
            this.getHabitacion().addElemento(this.getElemento());
            this.setElemento(null);
        }
        catch(ContenedorLlenoException e)
        {
            System.out.println(e);
        }
        catch(AccionNoPermitidaException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Establece la nueva bolsa para el personaje.
     * 
     * @param bolsa La nueva bolsa del personaje.
     */
    public void setBolsa(Bolsa bolsa)
    {
        if(bolsa.getPesoMaximo() > PESO_MAXIMO_BOLSA)
        {
            System.out.println("Bolsa inapropiada");
        }
        else
        {
            this.bolsa = bolsa;
        }
    }

    /**
     * Devuelve la bolsa del personaje.
     * 
     * @return La bolsa del personaje.
     */
    public Bolsa getBolsa()
    {
        return bolsa;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public Integer getVida() 
    {
        return vida;
    }

    public void setVida(Integer vida)
    {
        this.vida = vida;
    }

    public void resetVida(Integer vida)
    {
        this.vida = MAX_VIDA;
    }

    public Elemento getElemento()
    {
        return objeto;
    }

    public void setElemento(Elemento objeto)
    {
        this.objeto = objeto;
    }
    
    public void setHabitacion(Habitacion habitacion)
    {
        this.habitacion = habitacion;      
    }
    
    public Habitacion getHabitacion()
    {
        return habitacion;      
    }
}