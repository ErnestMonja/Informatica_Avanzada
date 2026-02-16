import java.util.ArrayList;
import java.util.TreeMap;

/**
 * La clase Caldero modela un recipiente para preparar pocimas. Se la inicializa con un nombre a elección, la 
 * cantidad máxima de ingredientes que admite y sin receta y pocima asociada.
 * 
 * Cuando se tienen todos los ingredientes de la receta, se puede proceder a preparar la pocima.  Solo se admiten
 * recetas que no superen la cantidad máxima de ingredientes del caldero (capacidad).
 * 
 * @author  Maximiliano A. Eschoyez y resuelto por Ernes.
 * @version 1.0
 *
 *                                                      [PUNTAJE xx/30]
 */
public class Caldero
{
    private final Integer MAX_INGREDIENTES;
    private String nombre;
    private Receta receta;
    private Elemento pocima;
    private TreeMap<String, Elemento> elementos;

    /**
     * El constructor debe inicializar el objeto con un nombre a elección, la capacidad maxima de ingredientes y sin
     * receta y pocima asociadas (null).
     * 
     * No olvidar construir el mapa de elementos (TreeMap).
     * 
     * @param nombre        El nombre del caldero.
     * @param capacidad     La capacidad máxima de ingredientes.
     */
    public Caldero (String nombre, Integer capacidad)
    {
        // TODO - Implementar metodo
        this.MAX_INGREDIENTES = capacidad;
        this.nombre = nombre;
        this.receta = null;
        this.pocima = null;
        this.elementos = new TreeMap<>();
    }

    /**
     * Asocia una receta al caldero para poder preparar una pocima.
     * 
     * La receta se incorpora solo sí:
     *   1) La cantidad de ingredientes no supera la capacidad maxima del caldero,
     *   2) No hay una receta asociada,
     *   3) No hay pocima preparada.
     * Si la receta no puede agregarse, se debe imprimir el mensaje:
     *      "<nombre>: No se puede agregar la receta"
     * 
     * donde <nombre> es el nombre del caldero.
     * 
     * Al agregar la receta, se deben generar las claves en el mapa de elementos asociadas a ningun objeto (null).
     * 
     * @param receta    La receta a incorporar.
     */
    public void setReceta(Receta receta)
    {
        // TODO - Implementar metodo
        if((receta.getCantidadIngredientes() <= this.getCapacidad())&&(this.receta == null)&&(this.pocima == null))
        {
            this.receta = receta;
            for(String s : receta.getIngredientes())
            {
                elementos.put(s, null);
            }
        }
        else
        {
            System.out.println(this.getNombre() + ": No se puede agregar la receta");
        }
    }

    /**
     * Incorpora al caldero un ingrediente siempre y cuando pertenezca a la receta.
     *
     * Se incorpora el ingrediente al mapa de elementos, siendo su nombre (getNombre()) la clave (key) y el valor (value) el
     * objeto elemento.
     * 
     * @param ingrediente   El ingrediente a incorporar al caldero.
     */
    public void addIngrediente(Elemento ingrediente)
    {
        // TODO - Implementar metodo
        if(this.receta.getIngredientes().contains(ingrediente.getNombre()))
        {
            elementos.put(ingrediente.getNombre(), ingrediente);
        }
    }

    /**
     * Genera una lista con los nombres de los ingredientes (key) faltantes.
     * 
     * Recordar que cada entrada del mapa (key) debe tener asociado un elemento (value). En caso contrario, no hay elemento 
     * asociado (null).
     * 
     * Ayuda: La lista (ArrayList) debe crearse localmente. Lista vacia significa que se cuenta con todos los ingredientes.
     * 
     * @return  La lista con los nombres de los ingredientes faltantes.
     */
    public ArrayList<String> getIngredientesFaltantes()
    {
        // TODO - Implementar metodo
        ArrayList<String> ingredientesFaltantes = new ArrayList<>();
        for(String s : elementos.keySet())
        {
            if(this.elementos.get(s) == null)
            {
                ingredientesFaltantes.add(s);
            }
        }
        return ingredientesFaltantes;
    }

    /**
     * Verifica que se hayan incorporado todos los ingredientes.
     * 
     * @return  Devuelve true si se tienen todos los ingredientes y false si falta al menos uno.
     */
    public Boolean verificarIngredientes()
    {
        // TODO - Implementar metodo
        if(this.getIngredientesFaltantes().size() == 0)
        {
            return true;
        }
        else
        {
            return false;    
        }
    }

    /**
     * Prepara la pocima previa verificación que exista una receta y que se hayan incorporado todos los ingredientes.
     * 
     * En caso de realizar la pocima se debe:
     *  1) Crear el objeto de tipo Elemento cuyo nombre es la concatenación de "Pocima de" con 
     *     el nombre de la receta y su peso es la suma de los pesos de cada ingrediente.
     *  2) Desvincular la receta del caldero.
     *  3) Limpiar el mapa de ingredientes.
     * 
     * Si no se puede prepara la pocima, se debe imprimir el mensaje:
     *      "<nombre>: No se puede preparar la pocima"
     * donde <nombre> es el nombre del caldero.
     */
    public void prepararPocima()
    {
        // TODO - Implementar metodo
        if((this.receta != null)&&(this.verificarIngredientes()))
        {
            Integer pesoPocion = 0;
            for(Elemento e : elementos.values())
            {
                pesoPocion += e.getPeso();
            }
            Elemento Pocima = new Elemento("Pocima de " + this.receta.getNombre(), pesoPocion);
            this.receta = null;
            this.elementos.clear();
        }
        else
        {
            System.out.println(this.getNombre() + ": No se puede preparar la pocima");
        }
    }

    /**
     * Retorna la pocima preparada y restablece a null el campo.
     * 
     * @return La pocima preparada.
     */
    public Elemento getPocima()
    {
        // TODO - Implementar metodo
        Elemento pocimaDevolver = this.pocima;
        this.pocima = null;
        return pocimaDevolver;
    }

    /**
     * Genera una cadena con la informacion sobre el caldero.
     * 
     * Debe generarse usando el nombre y la receta asociada o la pocima según:
     *    "<nombre>: ["vacio" | <receta> | <pocima>]"
     * 
     * Ejemplo sin receta ni pocima:
     *    "Caldero mediano: vacio"
     * 
     * Ejemplo con receta:
     *    "Caldero mediano: Receta voladora"
     * 
     * Ejemplo con pocima:
     *    "Caldero mediano: Pocima de Receta voladora"
     * 
     * @return El texto indicado en el ejemplo.
     */
    @Override
    public String toString()
    {
        // TODO - Implementar metodo
        String info = this.getNombre() + ": ";
        if((this.pocima == null)&&(this.receta == null))
        {
            info += "vacio";
        }
        else if((this.pocima == null)&&(this.receta != null))
        {
            info += this.receta.getNombre();
        }
        else if((this.pocima != null)&&(this.receta == null))
        {
            info += this.pocima.getNombre();
        }
        return info;
    }

    /**
     * Devuelve el mapa de ingredientes.
     * 
     * @return El mapa de ingredientes.
     */
    public TreeMap<String, Elemento> getIngredientes()
    {
        return elementos;
    }

    /**
     * Devuelve la receta asociada.
     * 
     * @return La receta asociada.
     */
    public Receta getReceta()
    {
        return receta;
    }

    /**
     * Devuelve el nombre del caldero.
     *
     * @return El nombre de caldero.
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Devuelve la capacidad max. del caldero.
     *
     * @return capacidad max.
     */
    public int getCapacidad()
    {
        return MAX_INGREDIENTES;
    }
}