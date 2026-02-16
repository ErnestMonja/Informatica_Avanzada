/**
 * Clase Item.
 * 
 * Nota: La clase tiene ciertas inconsistencias (a mi parecer), con respecto a lo que dice el javadoc y lo que en verdad quiere el lev hacer,
 * por lo tanto las listo a continuación para facilitarle la implementación de la misma:
 *      - En todos los constructores, si bien no lo especifica, hay que llamar al método generarId() de esta clase.
 *      - En el método generarId() no hace falta recortar el String ni hacerlo todo mayúscula a pesar de que los ejemplos parecen indicar que 
 *        si es el caso.
 *      - En el método getPrecioConDescuento(), si bien se indica que se pasa un porcentaje, el valor pasado como parámetro no es un valor de 
 *        0 a 100, sino mas bien un número Double que va del 0 al 1 (por ejemplo un 0,5 representa un 50%).
 *      
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 20/20
 */
abstract class Item
{
    private static Integer contador = 0;
    
    private final String id;
    private String descripcion;
    private Double precio;

    /**
     * Constructor simple que genera el id y setea la descripción en el texto "Sin descripcion"
     * 
     * @param id            El id del item.
     */
    public Item(final String id)
    {
        // TODO - Implementar constructor
        this.id = this.generarId(id);
        this.descripcion = "Sin descripcion";
    }
    
    /**
     * Constructor que genera el id y setea la descripción
     * 
     * @param id            El id del item.
     * @param descripcion   La descripción del elemento.
     */
    public Item(final String id, String descripcion)
    {
        // TODO - Implementar constructor
        this.id = this.generarId(id);
        this.descripcion = descripcion;
    }
    
    /**
     * Constructor que genera el id y setea la descripción y el precio.
     * 
     * @param id            El id del item.
     * @param descripcion   La descripción del elemento.
     * @param precio        El precio del producto.
     */
    public Item(final String id, String descripcion, Double precio)
    {
        // TODO - Implementar constructor
        this.id = this.generarId(id);
        this.descripcion = descripcion;
        this.precio = precio;
    }

    /**
     * Genera el ID del producto uniendo el ID provisto con el valor del contador de elementos separados por un guion bajo "_".
     * 
     * Ejemplo:
     *   Agrego una Bateria  --> ID es "BAT_1"
     *   Agrego otra Bateria --> ID es "BAT_2"
     *   Agrego un Tanque    --> ID es "TAN_3"
     * El contador siempre se incrementa.
     * 
     * @param id    El ID a generar
     * @return      
     */
    private String generarId(final String id)
    {
        // TODO - Implementar metodo
        contador++;
        String info = id + "_" + this.contador;
        return info;
    }

    /**
     * El precio de lista del producto con descuento
     *  -  5% de descuento es 0.05
     *  - 25% de descuento es 0.25
     *  
     * @param porcentaje    El descuento a realizar.
     * @return              El precio de lista.
     */
    public Double getPrecioConDescuento(final Double porcentaje)
    {
        // TODO - Implementar metodo
        Double precioConDescuento = this.getPrecio()*(1 - porcentaje);
        return precioConDescuento;
    }

    /**
     * El precio de lista del producto.
     * 
     * @return  El precio de lista.
     */
    public Double getPrecio()
    {
        return precio;
    }

    public String getId()
    {
        return id;
    }    
    
    public String getDescripcion()
    {
        return descripcion;
    }    

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }    
    
    public void setPrecio(Double precio)
    {
        this.precio = precio;
    }

    @Override
    public String toString()
    {
        return "Item [id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + "]";
    }  
}