public class MisionDeExploracion extends Mision
{
    private EscenarioBasico escenarioAExplorar;

    /**
     * Crea una nueva misión de exploración
     *
     * @param  nombre                                 El nombre de la misión.
     * @param  nivelRequerido                         El nivel requerido para realizar la misión.
     * @param  escenarioAExplorar                     El escenario básico a explorar.
     * @throws java.lang.IllegalArgumentException     Cuando el escenario no es un escenario básico.
     */
    public MisionDeExploracion(String nombre, Integer nivelRequerido, Escenario escenarioAExplorar)
    {
        super(nombre, nivelRequerido);
        try
        {
            this.escenarioAExplorar = (EscenarioBasico) escenarioAExplorar;
        }
        catch (ClassCastException e)
        {
            throw new IllegalArgumentException("El escenario no es valido");
        }
    }

    /**
     * Valida el cumplimiento de la misión por parte de un personaje jugador.
     *
     * @param  p   El personaje jugador para validar el cumplimiento de la misión.
     * @return     true si el personaje jugador ya visito el escenario false de lo contrario.
     */
    public Boolean validarCumplimiento(Personaje p)
    {
        if(!(p instanceof PersonajeJugador))
        {
            throw new IllegalArgumentException("El personaje no es un personaje Jugador");
        }
        return escenarioAExplorar.getVisitantes().contains(p);
    }
}
