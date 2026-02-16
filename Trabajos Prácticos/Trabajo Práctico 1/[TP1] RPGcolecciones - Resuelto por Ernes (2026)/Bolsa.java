import java.util.ArrayList;
import java.util.TreeMap;

/** 
 * Modela la bolsa que utiliza el Personaje para transportar elementos. La capacidad se determina por el peso
 * maximo que soporta.Todos los objetos se incluyen en un mapa.
 * 
 * @author  Maximiliano A. Eschoyez y resuelto por Ernes.
 * @version 1.0
 *
 *                                                      [PUNTAJE xx/20]
 */

public class Bolsa
{
    private final int PESO_MAXIMO;
    private int pesoActual;
    private String nombre;
    private TreeMap<String, Elemento> porNombre;

    /**
     * Constructor con parámetros. Inicializa las variables de instancia.
     * 
     * No olvidar construir el mapa de elementos (TreeMap), ni setear el peso inicial de la bolsa.
     * 
     * @param nombre    El nombre de la bolsa.
     * @param peso      El peso máximo que puede contener la bolsa.
     */
    public Bolsa (String nombre, int peso)
    {
        // TODO - Implementar metodo
        this.nombre = nombre;
        this.PESO_MAXIMO = peso;
        this.pesoActual = 0;
        this.porNombre = new TreeMap<>();
    }

    /**
     * Agrega un elemento a la bolsa y actualiza el peso total.
     * 
     * Si el elemento no puede agregarse, se debe imprimir el mensaje:
     *      "<nombre>: No se puede agregar <elemento>"
     * 
     * Donde <nombre> es el nombre de la bolsa y <elemento> es el nombre del elemento a agregar.
     * 
     * No se puede agregar un elemento sí:
     *         - Su peso excede el límite de la bolsa.
     *         - Existe un elemento con el mismo nombre.
     * 
     * @param obj   El elemento a agregar en la bolsa.
     */
    public void addElemento(Elemento obj)
    {
        // TODO - Implementar metodo
        if((obj.getPeso() > this.getPesoMaximo())||(obj.getPeso() > this.getPesoLibre())||(porNombre.containsValue(obj)))
        {
            System.out.println(this.getNombre() + ": No se puede agregar " + obj.getNombre());
        }
        else
        {
            this.porNombre.put(obj.getNombre(), obj);
            this.pesoActual += obj.getPeso();
        }
    }

    /**
     * Quita un objeto de la bolsa por su nombre y actualiza el peso total.
     * 
     * @param nombre    El nombre del elemento a quitar de la bolsa.
     * @return          El elemento eliminado, o null si no existe ese elemento a remover.
     */
    public Elemento delElemento(String nombre)
    {
        // TODO - Implementar metodo
        if(this.porNombre.containsKey(nombre))
        {
            Elemento e = this.porNombre.get(nombre);
            this.pesoActual -= porNombre.get(nombre).getPeso();
            this.porNombre.remove(nombre);
            return e;
        }
        return null;
    }

    /**
     * Devuelve una lista con los elementos almacenados en la bolsa.
     * 
     * @return ArrayList<Elemento>      Lista con los elementos de la bolsa.
     */
    public ArrayList<Elemento> getElementosEnLaBolsa()
    {
        // TODO - Implementar metodo
        ArrayList<Elemento> elementosDevolver = new ArrayList<>();
        for(Elemento e : porNombre.values())
        {
            elementosDevolver.add(e);
        }
        return elementosDevolver;
    }

    /**
     * Devuelve la lista de elementos almacenados en la bolsa cuyo nombre comienza con el prefijo indicado.
     * 
     * @param pre                       El prefijo a buscar
     * @return ArrayList<Elemento>      Lista con los elementos de la bolsa que cumplen con el criterio.
     */
    public ArrayList<Elemento> getElementosConPrefijo(String pre)
    {
        // TODO - Implementar metodo
        ArrayList<String> listaTest = new ArrayList<>();
        ArrayList<Elemento> elementosDevolver = new ArrayList<>();
        for(String s : porNombre.keySet())
        {
            if(s.trim().toLowerCase().startsWith(pre))
            {
                listaTest.add(s);
            }
        }
        for(String s : listaTest)
        {
            elementosDevolver.add(porNombre.get(s));
        }
        return elementosDevolver;
    }

    /**
     * Devuelve el peso disponible que aún puede almacenarse en la bolsa en un momento dado. Cambia según se 
     * agregan o quitan objetos.
     * 
     * @return      El peso máximo a agregar.
     */
    public int getPesoLibre()
    {
        // TODO - Implementar metodo
        return (PESO_MAXIMO - pesoActual);
    }

    /**
     * Incrementa el peso total almacenado en la bolsa. Se utiliza para agregar como para quitar objetos.
     *
     * @param peso  El peso a quitar/agregar.
     */
    public void addPeso(int peso)
    {
        // TODO - Implementar metodo
        if(this.getPesoLibre() >= peso)
        {
            this.pesoActual += peso;
        }
    }

    public int getPesoActual()
    {
        return pesoActual;
    }

    public void setPesoActual(int pesoActual)
    {
        this.pesoActual = pesoActual;
    }

    public String getKeySet()
    {
        return porNombre.keySet().toString();
    }

    public int getPesoMaximo()
    {
        return PESO_MAXIMO;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public TreeMap<String,Elemento> getMapaDeElementos()
    {
        return porNombre;
    }
}