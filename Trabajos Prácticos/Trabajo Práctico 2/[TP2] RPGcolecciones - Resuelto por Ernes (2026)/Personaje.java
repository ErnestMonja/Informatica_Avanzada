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
    
    private String nombre;                  // nombre     -> El nombre del personaje.
    private Integer vida;                   // vida       -> El valor actual de vida.
    private Elemento objeto;                // objeto     -> El elemento que tiene en sus manos. Puede tener las manos vacias (null).
    private Bolsa bolsa;                    // bolsa      -> La bolsa de elementos.
    private Habitacion habitacion;          // habitacion -> El lugar donde se encuentra el personaje.
    
    /**
     * Crea un personaje con el nombre dado y configura los campos segun vida, MAX_VIDA y PESO_MAXIMO_BOLSA.
     * 
     * El personaje se inicializa sin bolsa y con las manos vacías en la habitacion inicial del mapa (getInicio()).
     * 
     * @param nombre    El nombre del personje.
     * @param vida      El valor inicial y maximo de vida del personaje.
     * @param peso      El peso máximo que puede transportar el personaje.
     */
    public Personaje (String nombre, Integer vida, Integer peso)
    {
        // TODO - Implementar metodo
        this.nombre = nombre;
        this.vida = vida;
        this.MAX_VIDA = vida;
        this.PESO_MAXIMO_BOLSA = peso;
        
        this.bolsa = null;
        this.objeto = null;
        this.habitacion = Mapa.getInstance().getInicio();
    }

    /**
     * Imprime en pantalla la información sobre el lugar donde se encuentra.
     */
    public void mirarAlrededor()
    {
        // TODO - Implementar metodo
        System.out.println(this.habitacion.getDescripcionLarga());
    }

    /**
     * El personaje se mueve a la habitación en la dirección indicada. Si la dirección no es válida, se queda donde estaba.
     * 
     * @param direccion     Direccion por donde salir de la habitacion
     */
    public void irHacia(Salida direccion)
    {
        // TODO - Implementar metodo              
        this.setHabitacion(this.habitacion.getSalida(direccion));
    } 

    /**
     * Guarda el elemento que tiene en sus manos en la bolsa, siempre y cuando haya lugar suficiente.  Las manos quedan vacias (null).
     * Si las manos estan vacias (null), mostrar:
     *          "No hay elemento para agregar a la bolsa"
     *          
     * Si no hay bolsa (null), mostrar:
     *          "No hay bolsa"
     */
    public void guardarElemento()
    {
        // TODO - Implementar metodo
        if(this.getElemento() == null)
        {
            System.out.println("No hay elemento para agregar a la bolsa");
            return;
        }
        else if(this.getBolsa() == null)
        {
            System.out.println("No hay bolsa");
            return;
        }
        else
        {   // El "siempre y cuando haya lugar suficiente" se encarga el método addElemento().
            this.getBolsa().addElemento(this.getElemento());
            this.setElemento(null);
        }
    }

    /**
     * Toma un elemento de la bolsa (getElemento) y lo pone en las manos del personaje.
     * 
     * Si tiene un elemento en sus manos, imprimir:
     *          "Manos ocupadas".
     * 
     * Si no hay bolsa, imprimir: 
     *          "No hay bolsa"
     * 
     * Si no existe el elemento se debe imprimir:
     *          "No se cuenta con el <nombre>"
     * donde <nombre> es el nombre del elemento buscado.
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
        else if(this.getBolsa() == null)
        {
            System.out.println("No hay bolsa");
            return;
        }
        
        Elemento e = this.getBolsa().getElemento(nombre);
        if(e == null)
        {
            System.out.println("No se cuenta con el " + nombre);
            return;
        }
        this.setElemento(e);
    }
    
    /**
     * Toma de la habitación el elemento indicado con sus manos. 
     * 
     * De ser necesario, debe guardar en la bolsa lo que tenga en sus manos.
     * 
     * Si el elemento no existe, debe seguir sosteniendo el elemento que tenia en sus manos.
     * 
     * @param nombre    Nombre del objeto a recoger de la habitacion.
     */
    public void recogerElemento(String nombre)
    {
        // TODO - Implementar metodo
        Elemento e = this.habitacion.getElemento(nombre);
        if(e == null)
        {
            return;
        }
        else
        {
            if(this.getElemento() != null)
            {
                this.guardarElemento();
            }
            this.setElemento(e);
            return;
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
        }
        else
        {
            this.habitacion.addElemento(this.getElemento());
            this.setElemento(null);
        }
    }

    /**
     * Establece la nueva bolsa para el personaje.
     * 
     * @param bolsa     La nueva bolsa del personaje.
     */
    public void setBolsa(Bolsa bolsa)
    {
        if (bolsa.getPesoMaximo() > PESO_MAXIMO_BOLSA)
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
     * @return  La bolsa del personaje.
     */
    private Bolsa getBolsa()
    {
        return bolsa;
    }

    private String getNombre()
    {
        return nombre;
    }

    private void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    private Integer getVida()
    {
        return vida;
    }

    private void setVida(Integer vida)
    {
        this.vida = vida;
    }

    private void resetVida(Integer vida)
    {
        this.vida = MAX_VIDA;
    }

    private Elemento getElemento()
    {
        return objeto;
    }

    private void setElemento(Elemento objeto)
    {
        this.objeto = objeto;
    }
    
    private void setHabitacion(Habitacion habitacion)
    {
        this.habitacion = habitacion;      
    }
}