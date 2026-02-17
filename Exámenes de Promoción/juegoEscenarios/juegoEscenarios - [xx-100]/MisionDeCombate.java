public class MisionDeCombate extends Mision
{
    private EscenarioDeCombate escenario;

    /**
     * Crea una nueva misión de combate.
     *
     * @param nombre                                 El nombre de la misión.
     * @param nivelRequerido                         El nivel requerido para participar de la misión.
     * @param escenario                              El escenario de combate donde se realizara la misión.
     * @throws java.lang.IllegalArgumentException    Cuando el escenario no es de combate.
     */
    public MisionDeCombate(String nombre, Integer nivelRequerido, Escenario escenario)
    {
        super(nombre, nivelRequerido);
        try
        {
            this.escenario = (EscenarioDeCombate) escenario;
        }
        catch(ClassCastException e)
        {
            throw new IllegalArgumentException("El escenario no es valido");
        }
    }

    /**
     * Valida si el personaje cumplio la misión o no.
     *
     * @param  p                                    El personaje a validar.
     * @return                                      true si no quedan enemigos en el escenario, false en caso contrario.
     * @throws java.lang.IllegalArgumentException   Cuando el personaje no es un personaje jugador.
     */
    public Boolean validarCumplimiento(Personaje p)
    {
        if (!(p instanceof PersonajeJugador))
        {
            throw new IllegalArgumentException();
        }
        return escenario.getEnemigos().isEmpty();
    }
}
