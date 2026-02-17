public abstract class Mision
{
    private String nombre;
    private Integer nivelRequerido;

    /**
     * Crea una nueva misión con su nombre y el requerimiento de nivel necesario para realizarla.
     *
     * @param nombre         El nombre de la misión.
     * @param nivelRequerido El nivel requerido para participar de la misión.
     */
    public Mision(String nombre, Integer nivelRequerido)
    {
        this.nombre = nombre;
        this.nivelRequerido = nivelRequerido;
    }

    public abstract Boolean validarCumplimiento(Personaje p);

    public String getNombre()
    {
        return nombre;
    }

    public Integer getNivelRequerido()
    {
        return nivelRequerido;
    }
}
