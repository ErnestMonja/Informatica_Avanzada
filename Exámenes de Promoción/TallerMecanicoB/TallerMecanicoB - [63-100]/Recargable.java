interface Recargable
{
    /**
     * Nuevo porcentaje de carga. El valor final no puede ser
     * menor a 0% ni mayor a 100%.
     * 
     * @param porcentaje Nuevo porcentaje de carga
     */
    public void setPorcentajeCarga(Integer porcentaje);
    
    /**
     * El porcentaje de carga actual, siempre entre 0% y 100%.
     * 
     * @return Porcentaje de carga
     */
    public Integer getPorcentajeCarga();
    
    /**
     * Representa el costo de la recarga necesaria para alzancar el 100%.
     * Si tiene 50% y el costo es de $0.25 por cada 5%, el costo es $2.50
     * 
     * @return El costo de la recarga
    */
    public Double  getCostoCargaCompleta();
}