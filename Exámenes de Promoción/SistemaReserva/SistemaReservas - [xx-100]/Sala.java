/**
 * Completar la clase Sala:
 *  - Implementar el constructor.
 *  - Definir los métodos getNombre(), getCapacidad() y getUbicacion()
 *  - Implementar los métodos setCapacidad(...) y setUbicacion(...).
 *  - Sobrescribir el método toString() para mostrar una descripción legible de la sala.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: xx/20
 */
public class Sala
{
    private String nombre;
    private int capacidad;
    private Ubicacion ubicacion;

    public Sala(String nombre, int capacidad, Ubicacion ubicacion)
    {
        //TODO Implementar método
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
    }

    public String getNombre()
    {
        //TODO Implementar
        return this.nombre;
    }
    
    public int getCapacidad()
    { 
        //TODO Implementar
        return this.capacidad;
    }
    
    public Ubicacion getUbicacion()
    { 
        //TODO Implementar
        return this.ubicacion;
    }
    
    /**
     * Setea la capacidad de la sala solo si la capacidad es mayor a 0
     */
    public void setCapacidad(int capacidad)
    { 
        //TODO Implementar
        if(capacidad > 0)
        {
            this.capacidad = capacidad;
        }
    }
    
    public void setUbicacion(Ubicacion ubicacion)
    { 
        //TODO Implementar
        this.ubicacion = ubicacion;
    }
    
    /**
     * Retorna una representación de la Sala como String con el formato
     *      "Sala <nombre> (capacidad: <capacidad>, ubicacion: <ubicacion>)"
     * 
     * Ejemplo: 
     *      Sala Uritorco (capacidad: 6, ubicacion: Norte)
     */
    @Override
    public String toString()
    { 
        //TODO Implementar
        String info = "Sala " + this.getNombre() 
                    + "(capacidad: " + this.getCapacidad()
                    + ", ubicacion: " + this.getUbicacion() + ")";
        return info;
    }
}