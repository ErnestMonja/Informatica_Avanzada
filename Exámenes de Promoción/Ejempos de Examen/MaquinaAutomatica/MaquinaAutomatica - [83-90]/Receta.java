import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

/**
 * La clase Receta.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 14/15
 */
public class Receta
{
    protected Map<Ingrediente, Integer> ingredientes;
    
    public Receta()
    {
        ingredientes = new TreeMap<Ingrediente,Integer>();
    }
    
    /**
     * Devuelve el mapa de ingredientes de la receta.
     *
     * @return  El mapa de ingredientes de la receta.
     */
    public Map<Ingrediente, Integer> getMapaDeIngredientes()
    {
        return ingredientes;
    }
    
    /**
     * Agrega un ingrediente a la receta.
     *
     * @param ing                       El ingrediente a agregar a la receta.
     * @param cantidad                  La cantidad de ingrediente a agregar a la receta.
     * @throws IllegalArgumentException Cuando el ingrediente o la cantidad de ingrediente a agregar no es válida (ej. negativa).
     */
    public void agregarIngrediente(Ingrediente ing, Integer cantidad) throws IllegalArgumentException
    {
        //TODO: Implementar metodo
        if((ing == null)||(cantidad <= 0))
        {
            throw new IllegalArgumentException();
        }
        else
        {
            if(this.getMapaDeIngredientes().containsKey(ing))
            {
                this.getMapaDeIngredientes().put(ing, ingredientes.get(ing) + cantidad);
                return;
            }
            else
            {
                this.getMapaDeIngredientes().put(ing, cantidad);
            }
        }
    }
    
    /**
     * Obtiene la lista de ingredientes de esta receta.
     *
     * @return  La lista de ingredientes de la receta.
     */
    public List<Ingrediente> getIngredientes()
    {
        //TODO: Implementar metodo
        List<Ingrediente> ingredReceta = new ArrayList<>();
        for(Ingrediente i : this.getMapaDeIngredientes().keySet())
        {
            ingredReceta.add(i);
        }
        return ingredReceta;
    }
    
    /**
     * Retorna la cantidad del ingrediente solicitado para esta receta. 
     * 
     * Si la receta no requiere este ingrediente, retorna 0.
     *
     * @param ingrediente   El ingrediente solicitado.
     * @return              La cantidad de ingrediente requerida por la receta.
     */
    public int getCantidadDeIngredienteRequerida(Ingrediente ingrediente)
    {
        //TODO: Implementar metodo
        if(this.getMapaDeIngredientes().containsKey(ingrediente))
        {
            return this.getMapaDeIngredientes().get(ingrediente);
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * Retorna el peso total de la receta (sumatoria de las cantidades de todos los ingredientes de la receta).
     *
     * @return  El peso total de la receta.
     */
    public int volumenTotalReceta()
    {
        //TODO: Implementar metodo
        int pesoTotal = 0;
        for(Integer pesoIngrediente : this.getMapaDeIngredientes().values())
        {
            pesoTotal += pesoIngrediente;
        }
        return pesoTotal;
    }
    
    /**
     * Retorna un String con el siguiente formato:
     *          "Receta: <Ingrediente1>[<Cantidad1>];<Ingrediente2>[<Cantidad2>];...;<<IngredienteN>[<CantidadN>];"
     *  
     *  Ej.: "Receta: AZUCAR[5];CAFE[15];AGUA[150];"    
     */
    public String toString()
    {
        //TODO: Implementar metodo
        String info = "Receta: ";
        for(Ingrediente i : this.getIngredientes())
        {
            info += i + "[" + this.getMapaDeIngredientes().get(i) + "];";
        }
        return info;
    }
}