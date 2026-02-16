import java.util.ArrayList;
import java.util.List;

/**
 * Logica que centraliza la informacion de los movimientos de los autos.
 * 
 * Evita que los autos choquen entre sí o que intenten cruzar un semáforo en rojo.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 40/40
 */
public class Mapa
{
    private List<Auto> autos;

    public Mapa()
    {
        this.autos = new ArrayList<>();
        // Completar
    }

    /**
     * Agrego un nuevo auto para ser controlado.
     * 
     * @param auto
     */
    public void agregar_auto(Auto auto)
    {
        // Completar
        this.autos.add(auto);
    }

    /**
     * Devuelve la lista de autos en mapa.
     */
    public List<Auto> get_autos()
    {
        // Completar
        return this.autos;
    }

    /**
     * Elimina la lista de autos registrados en el mapa
     */
    public void borrar_autos() 
    {
        // Completar
        this.autos.clear();
    }

    /**
     * Se fija en la lista de autos disponible si hay alguno que cumpla las siguientes condiciones:
     *      1. Va en la misma direccion que el auto consultado.
     *      2. Está en la misma calle.
     *      3. Está en alguna posición por lo(/a) cual el auto pasaría en su próximo movimiento.
     *      
     * Recomendación: utilizar métodos de la clase Auto
     *      
     * @param  auto
     * @return      true si algun auto cumple la(s) condicion(es), false si no la(s) cumple.
     */
    private boolean auto_en_el_camino(Auto auto)
    {
        // Completar
        for(Auto a : autos)
        {
            if((a.get_direccion() == auto.get_direccion())&&
               (a.get_calle().equals(auto.get_calle()))&&
               (auto.auto_pasa_por(a.get_posicion())) )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Se fija si dentro de las intersecciones que hay en la calle actual del auto:
     * 
     * Si hay alguna intersección en la trayectoria del auto:
     *      Verifica que el semáforo este en verde para la calle en la cual transita el auto (true si esta en verde, falso si no)
     * De lo contrario: devuelve verdadero.
     *      
     * @param  auto
     * @return      true si no hay ninguna intersección con semáforo en rojo en el camino, de lo contrario devuelve false.
     */
    private boolean interseccion_disponible(Auto auto)
    {
        // Completar
        for(InterseccionInterface interseccion : auto.get_calle().get_intersecciones())
        {
            try
            {
                Integer altura = interseccion.get_altura_calle(auto.get_calle());
                if((auto.auto_pasa_por(altura))&&
                   (!interseccion.get_paso(auto.get_calle())) )
                {
                        return false;
                }
            }
            catch(CalleIncorrectaException e)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Lógica de comprobación de movimientos de los autos.
     * 
     * Evalua:
     *     1. Que no haya ningun auto en el camino.
     *     2. Que no tenga semaforo en rojo en ninguna interesección.
     *     
     * Recomendacion: Utilizar métodos privados de la misma clase.
     * 
     * @param auto
     * @return      true si el movimiento a realizar está permitido, false si no lo está.
     */
    public boolean comprobar_movimiento(Auto auto)
    {
        // Completar
        return ((!this.auto_en_el_camino(auto))&&(this.interseccion_disponible(auto)));
    }
}