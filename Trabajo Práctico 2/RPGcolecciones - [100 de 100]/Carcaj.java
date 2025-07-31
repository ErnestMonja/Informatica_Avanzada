import java.util.ArrayList;
import java.util.List;

/**
 * Modela el Carcaj que lleva el personaje para
 * tranportar sus flechas.
 */
public class Carcaj extends Elemento
{
    private final Integer PESO_PROPIO = 10;
    private Integer capacidad;
    private ArrayList<Flecha> flechas;


    /**
     * Constructor para un Carcaj generico chico. Su capacidad
     * es de 5 flechas pero se inicializa vacio.
     * Recordar que es de tipo recipiente y tiene un peso inicial.
     */
    public Carcaj()
    {
        super("Carcaj chico", 10, TipoElemento.RECIPIENTE);
        this.capacidad = 5;
        this.setPeso(PESO_PROPIO);
        flechas = new ArrayList<Flecha>();
    }


    /**
     * Constructor para un Carcaj de nombre y capacidad indicados
     * por parametro.  Se inicializa vacio.
     * Recordar que es de tipo recipiente y tiene un peso inicial.
     *
     * @param nombre El nombre del carcaj.
     * @param capacidad Cantidad maxima de flechas.
     */
    public Carcaj(String nombre, Integer capacidad)
    {
        super(nombre, 10, TipoElemento.RECIPIENTE);
        this.capacidad = capacidad;
        this.setPeso(PESO_PROPIO);
        flechas = new ArrayList<Flecha>();
    }


    /**
     * Agrega una flecha al carcaj.
     *
     * Debe actualizarse el peso total.
     *
     * Si no tiene capacidad disponible, debe imprimir
     *         "<nombre>: Capacidad completa"
     * donde <nombre> es el nombre del carcaj.
     *
     * Si el elemento a agregar no es una flecha, debe imprimir:
     *         "<nombre>: No es una flecha"
     * donde <nombre> es el nombre del elemento entregado.
     *
     * @param flecha La flecha a agregar.
     */
    public void addFlecha (Elemento flecha)
    {
        if(flecha.getTipo() != TipoElemento.MUNICION) 
        {
            System.out.println(flecha.toString() + ": No es una flecha");
        }
        else 
        {
            if (flechas.size() >= capacidad)
            {
                System.out.println(getNombre() + ": Capacidad completa");
            }
            else
            {
                flechas.add(new Flecha());
                addPeso(flecha.getPeso());
            }
        }
    }
        
        
    /**
     * Quita del carcaj una flecha.
     *
     * Debe actualizarse el peso total.
     *
     * Si no hay mas flechas, devuelve null y debe imprimir:
     *          "<nombre>: No quedan flechas"
     * donde <nombre> es el nombre del carcaj.
     *
     * @return Una flecha.
     */
    public Flecha getFlecha()
    {
        if(flechas.size()==0)
        {
            System.out.println(this.getNombre() + ": No quedan flechas");
            return null;
        }
        else
        {
            flechas.remove(flechas.size()-1);
            this.setPeso(this.getPeso() - 3);
            return new Flecha();
        }
    }


    /**
     * Modifica el peso del carcaj.
     * Puede sumar o restar peso.
     *
     * @param peso El peso a modificar.
     */
    public void addPeso (Integer peso)
    {
        this.setPeso(this.getPeso() + peso);
    }


    /**
     * Devuelve informacion sobre el carcaj de la forma:
     *         "<nombre>: Flechas <cant>/<max>"
     * donde <nombre> es el nombre del carcaj,
     * <cant> es la cantidad de flechas que contiene y
     * <max> la capacidad maxima.
     */
    @Override            
    public String toString()
    {
        String cadena = this.getNombre() + ": Flechas " + flechas.size() + "/" + capacidad;
        return cadena;
    }

    public Integer getCantidadFlechas() 
    {
        return flechas.size();
    }

    public Integer getCapacidad() 
    {
        return capacidad;
    }
}