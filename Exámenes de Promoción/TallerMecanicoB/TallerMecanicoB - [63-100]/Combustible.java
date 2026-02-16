abstract class Combustible extends Item implements Recargable
{
    // La carga es adimensional y depende de la implementación. Puede ser porcentaje, litros, cm3, ...
    private final Integer unidadCarga;
    private Double        costoUnidadCarga;
    private Integer       carga;

    public Combustible (final String id, Double costoUnidadCarga)
    {
        super(id);
        this.unidadCarga = 1;
        this.costoUnidadCarga = costoUnidadCarga;
        this.carga = 0;
    }

    public Combustible (final String id, Double costoUnidadCarga, Integer carga)
    {
        super(id);
        this.unidadCarga = 1;
        this.costoUnidadCarga = costoUnidadCarga;
        this.carga = carga;
    }

    public Combustible (final String id, String descripcion, Double costoUnidadCarga, Integer unidadCarga)
    {
        super(id, descripcion);
        this.unidadCarga = unidadCarga;
        this.costoUnidadCarga = costoUnidadCarga;
        this.carga = 0;
    }

    public Combustible (final String id, String descripcion, Double costoUnidadCarga, Integer unidadCarga, Integer carga)
    {
        super(id, descripcion);
        this.unidadCarga = unidadCarga;
        this.costoUnidadCarga = costoUnidadCarga;
        this.carga = carga;
    }

    public Combustible(final String id, String descripcion, Double precio, Double costoUnidadCarga)
    {
        super(id, descripcion, precio);
        this.unidadCarga = 1;
        this.costoUnidadCarga = costoUnidadCarga;
        this.carga = 0;
    }

    public Combustible(final String id, String descripcion, Double precio, Double costoUnidadCarga, Integer unidadCarga)
    {
        super(id, descripcion, precio);
        this.unidadCarga = unidadCarga;
        this.costoUnidadCarga = costoUnidadCarga;
        this.carga = 0;
    }

    public Combustible(final String id, String descripcion, Double precio, Double costoUnidadCarga, Integer unidadCarga, Integer carga)
    {
        super(id, descripcion, precio);
        this.unidadCarga = unidadCarga;
        this.costoUnidadCarga = costoUnidadCarga;
        this.carga = carga;
    }

    public Integer getCarga()
    {
        return carga;
    }

    public void setCarga(Integer carga)
    {
        this.carga = carga;
    }
    
    public Double getCostoUnidadCarga()
    {
        return costoUnidadCarga;
    }

    public void setCostoUnidadCarga(Double costoUnidadCarga)
    {
        this.costoUnidadCarga = costoUnidadCarga;
    }

    public Integer getUnidadCarga()
    {
        return unidadCarga;
    }

    @Override
    public String toString()
    {
        return "Combustible [carga=" + carga + "]\n" + super.toString();
    }
}