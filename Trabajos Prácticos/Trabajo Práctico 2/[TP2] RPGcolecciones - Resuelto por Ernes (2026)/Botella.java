/**
 * Clase Botella que extiende a Elemento.
 * 
 * @author  Maximiliano A. Eschoyez y resuelto por Ernes.
 * @version 1.1
 *
 *                                                      [PUNTAJE xx/20]
 */
public class Botella extends Elemento
{
    private static final Integer PESO_PROPIO = 4;
    private Elemento contenido;

    public Botella()
    {
        super();
        super.setPeso(PESO_PROPIO);
        super.setTipo(TipoElemento.RECIPIENTE);
        generarNombre();
    }    

    /**
     * Llena la botella con el líquido indicado, agregando el peso del nuevo líquido. Genera el nuevo nombre de la botella y actualiza el peso.
     * 
     * Solo puede rellenarse cuando esta vacía. Si tiene contenido, debe imprimir el mensaje:
     *              "<botella>: Se encuentra llena"
     * siendo <botella> el texto provisto por toString().
     * 
     * Si el elemento no es del tipo "liquido", debe imprimir el mensaje:
     *              "<botella>: No admite <elemento>"        
     * siendo <botella> el texto provisto por toString() y <elemento> la descripcion del elemento entregado (toString()).
     * 
     * 
     * @param liquido   El líquido para rellenar la botella.
     */
    public void llenarBotella(Elemento liquido)
    {
        // TODO - Implementar metodo
        if(this.isVacia())
        {
            if(liquido.getTipo() == TipoElemento.LIQUIDO)
            {
                this.setContenido(liquido);
                super.setPeso(PESO_PROPIO + liquido.getPeso());
                this.generarNombre();
            }
            else
            {
                System.out.println(super.toString() + ": No admite " + liquido.getTipo());
            }
        }
        else
        {
            System.out.println(super.toString() + ": Se encuentra llena");
        }
    }

    /**
     * Vacia el contenido de la botella y actualiza el peso. La botella queda lista para recargar. Genera el nuevo nombre de la botella.
     * 
     * @return  El contenido de la botella.
     */
    public Elemento vaciarBotella()
    {
        // TODO - Implementar metodo
        Elemento contenido = this.getContenido();
        super.setPeso(PESO_PROPIO);
        this.setContenido(null);
        this.generarNombre();
        return contenido;
    }

    /**
     * Establece la descripción de la botella con su contenido correspondiente, por ej. "Botella con Agua". En caso de que no tenga contenido, 
     * utiliza el texto: "Botella vacia".
     */
    public void generarNombre()
    {
        // TODO - Implementar metodo
        if(this.isVacia())
        {
            super.setNombre("Botella vacia");
            return;
        }
        else
        {
            super.setNombre("Botella con " + this.getContenido().getNombre());
        }
    }

    /**
     * Devuelve verdadero en caso de contener algún tipo de pocima y falso sí esta vacía o contiene otro tipo de líquido.
     * 
     * @return  true en caso de contener algún tipo de pocima.
     */
    public Boolean hasPocima()
    {
        // TODO - Implementar metodo
        if(this.getContenido() instanceof Pocima)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Devuelve verdadero en caso de contener agua y falso sí esta vacía o contiene otro tipo de líquido.
     * 
     * @return  true en caso de contener agua.
     */
    public Boolean hasAgua()
    {
        // TODO - Implementar metodo
        if(this.getContenido() instanceof Agua)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Devuelve verdadero en caso de contener vino y falso sí esta vacía o contiene otro tipo de líquido.
     * 
     * @return  true en caso de contener vino.
     */
    public Boolean hasVino()
    {
        // TODO - Implementar metodo
        if(this.getContenido() instanceof Vino)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
  
    /**
     * Devuelve verdadero o falso segun tenga o no contenido.
     * 
     * @return  true en caso de estar vacia.
     */
    public Boolean isVacia()
    { 
        if(this.getContenido() == null)
        {
            return true;
        }
        else
        {
            return false;    
        }
    }

    public Elemento getContenido()
    {
        return contenido;
    }

    public void setContenido(Elemento contenido)
    {
        this.contenido = contenido;
    }
}