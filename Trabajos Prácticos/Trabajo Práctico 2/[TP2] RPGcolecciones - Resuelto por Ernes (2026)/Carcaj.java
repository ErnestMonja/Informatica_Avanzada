import java.util.ArrayList;
import java.util.List;

/**
 * Modela el Carcaj que lleva el personaje para tranportar sus flechas.
 * 
 * @author  Maximiliano A. Eschoyez y resuelto por Ernes.
 * @version 1.0
 *
 *                                                      [PUNTAJE xx/20]
 */
public class Carcaj extends Elemento
{
    private final Integer PESO_PROPIO = 10;
    private Integer capacidad;
    private ArrayList<Flecha> flechas;

    /**
     * Constructor para un Carcaj generico chico. Su capacidad es de 5 flechas pero se inicializa vacío.
     * Recordar que es de tipo recipiente y tiene un peso inicial.
     */
    public Carcaj()
    {
        // TODO - Implementar metodo
        super("Carcaj Chico", 10, TipoElemento.RECIPIENTE);
        super.setPeso(PESO_PROPIO);
        this.capacidad = 5;
        this.flechas = new ArrayList<>();
    }

    /**
     * Constructor para un Carcaj de nombre y capacidad indicados por parametro. Se inicializa vacío.
     * Recordar que es de tipo recipiente y tiene un peso inicial.
     * 
     * @param nombre        El nombre del carcaj.
     * @param capacidad     Cantidad máxima de flechas.
     */
    public Carcaj(String nombre, Integer capacidad)
    {
        // TODO - Implementar metodo
        super(nombre, 10, TipoElemento.RECIPIENTE);
        super.setPeso(PESO_PROPIO);
        this.capacidad = capacidad;
        this.flechas = new ArrayList<>();
    }

    /**
     * Agrega una flecha al carcaj. Debe actualizarse el peso total.
     * 
     * Si no tiene capacidad disponible, debe imprimir:
     *          "<nombre>: Capacidad completa"
     * donde <nombre> es el nombre del carcaj.
     * 
     * Si el elemento a agregar no es una flecha, debe imprimir:
     *          "<nombre>: No es una flecha"
     * donde <nombre> es el nombre del elemento entregado.
     * 
     * @param flecha    La flecha a agregar.
     */
    public void addFlecha(Elemento flecha)
    {
        // TODO - Implementar metodo
        if(this.getCantidadFlechas() < this.getCapacidad())
        {
            if(flecha instanceof Flecha)
            {
                Flecha flechaAAgregar = (Flecha) flecha;
                this.flechas.add(flechaAAgregar);
                
                // Actualizo el peso total
                Integer pesoActualizado = PESO_PROPIO;
                for(Flecha f : flechas)
                {
                    pesoActualizado += f.getPeso();
                }
                super.setPeso(pesoActualizado);
            }
            else
            {
                System.out.println(flecha.getNombre() + ": No es una flecha");
            }
        }
        else
        {
            System.out.println(super.getNombre() + ": Capacidad completa");
        }
    }

    /**
     * Quita del carcaj una flecha. Debe actualizarse el peso total.
     * 
     * Si no hay mas flechas, devuelve null y debe imprimir:
     *      "<nombre>: No quedan flechas"
     * donde <nombre> es el nombre del carcaj.
     * 
     * @return  Una flecha.
     */
    public Flecha getFlecha() 
    {
        // TODO - Implementar metodo
        if(this.getCantidadFlechas() > 0)
        {
            // Siempre itero por sobre la primera flecha:
            Flecha flechaARetornar = this.flechas.get(0);
            this.flechas.remove(this.flechas.get(0));
            
            // Actualizo el peso total:
            super.setPeso(super.getPeso() - flechaARetornar.getPeso());
            
            // Retorno la flecha
            return flechaARetornar;
        }
        else
        {
            System.out.println(super.getNombre() + ": No quedan flechas");
            return null;
        }
    }

    /**
     * Modifica el peso del carcaj. Puede sumar o restar peso.
     * 
     * @param peso  El peso a modificar.
     */
    public void addPeso(Integer peso)
    {
        // TODO - Implementar metodo
        super.setPeso(super.getPeso() + peso);
    }

    /**
     * Devuelve informacion sobre el carcaj de la forma:
     *          "<nombre>: Flechas <cant>/<max>"
     * donde <nombre> es el nombre del carcaj, <cant> es la cantidad de flechas que contiene y <max> la capacidad maxima.
     */
    @Override        
    public String toString()
    {
        // TODO - Implementar metodo
        String info = super.getNombre() + ": Flechas " + this.getCantidadFlechas() + "/" + this.getCapacidad();
        return info;
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