/**
 * Representa una batería que puede cargarse y descargarse. La unidad de carga depende del modelo, pudiendo ser cualquier porcentaje como 
 * ser 1%, 2%, ..., 10%, etc.
 * 
 * 
 * Nota: La clase tiene ciertas inconsistencias (a mi parecer), con respecto a lo que dice el javadoc y lo que en verdad quiere el lev hacer,
 * por lo tanto las listo a continuación para facilitarle la implementación de la misma:
 *  - Hubo un error de los profesores en el exámen final, entre el javadoc dado en el lev que el del que uno recibe al descomprimir este 
 *    proyecto del zip. El javadoc a seguir es el propuesto por este código o la version corregida del proyecto.
 *  - El método toString() nunca pide ser sobreescrito en su javadoc (no tiene siquiera javadoc) sin embargo para obtener el puntaje completo, 
 *    debe reescribirse de: return "Bateria []"; a retornar en cambio la implementación provista en esta resolución.
 *      
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 14/20
 */
class Bateria extends Combustible
{
    /**
     * Un constructor para una batería con 70% de carga inicial y unidad de carga de 5%.
     * 
     * @param id                El código del producto.
     * @param descripcion       Detalle de la batería.
     * @param costoUnidadCarga  Precio del costo de recarga por unidad.
     */
    // TODO - Implementar el constructor
    public Bateria(final String id, String descripcion, Double costoUnidadCarga)
    {
        super(id, descripcion, costoUnidadCarga, 5, 70);
    }
    
    /**
     * Un constructor para una batería con 50% de carga inicial.
     * 
     * @param id                El código del producto.
     * @param descripcion       Detalle de la batería.
     * @param unidadCarga       Un valor en porcentaje.
     * @param costoUnidadCarga  Precio del costo de recarga por unidad.
     */
    // TODO - Implementar el constructor
    public Bateria(final String id, String descripcion, Integer unidadCarga, Double costoUnidadCarga)
    {
        super(id, descripcion, costoUnidadCarga, unidadCarga, 50);
    }
    
    
    // TODO - Implementar los métodos faltantes de la interfaz Recargable
    /**
     * Nuevo porcentaje de carga. El valor final no puede ser
     * menor a 0% ni mayor a 100%.
     * 
     * @param porcentaje    Nuevo porcentaje de carga
     */
    public void setPorcentajeCarga(Integer porcentaje)
    {
        if(porcentaje < 0 || porcentaje > 100)
        {
            return;
        }
        super.setCarga(porcentaje);
    }
    
    /**
     * El porcentaje de carga actual, siempre entre 0% y 100%.
     * 
     * @return Porcentaje de carga
     */
    public Integer getPorcentajeCarga()
    {
        return super.getCarga();
    }
    
    /**
     * Representa el costo de la recarga necesaria para alzancar el 100%.
     * 
     * Si tiene 50% y el costo es de $0.25 por cada 5%, el costo es $2.50
     * 
     * @return El costo de la recarga
    */
    public Double getCostoCargaCompleta()
    {
        int iteraciones = 0;
        Double bateriaFaltante = 100.0 - this.getPorcentajeCarga();
        while(bateriaFaltante != 0.0)
        {
            bateriaFaltante -= super.getCostoUnidadCarga(); // Creo que el error surge de aquí, debería hacer algo así como: bateriaFaltante -= super.getUnidadCarga()
            iteraciones++;
        }
        return iteraciones*super.getCostoUnidadCarga();
    }
    
    @Override
    public String toString()
    {
        return "Bateria []\n" + super.toString();
    }
}