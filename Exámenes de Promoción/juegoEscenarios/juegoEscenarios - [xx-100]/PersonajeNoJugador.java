public class PersonajeNoJugador extends Personaje
{
    private Mision mision;
    private Elemento recompensa;

    /**
     * Crea un nuevo personaje no jugador con nombre, vida una misión y su recompensa por cumplirla.
     *
     * @param nombre        El nombre del personaje.
     * @param vida          La cantidad de vida del personaje.
     * @param mision        La misión que asignará a un personaje jugador que la solicite.
     * @param recompensa    La recompensa por cumplir la misión.
     */
    public PersonajeNoJugador(String nombre, Integer vida, Mision mision, Elemento recompensa)
    {
        super(nombre, vida);
        this.mision = mision;
        this.recompensa = recompensa;
    }

    /**
     * Asigna una misión a un personaje jugador.
     *
     * @param personajeJugador  El personaje jugador al cual se le asignará la misión.
     */
    public void asignarMision(PersonajeJugador personajeJugador)
    {
        personajeJugador.asignarMision(mision);
    }

    /**
     * Asigna la recompensa de una misión completada a un personaje jugador.
     * 
     * Debe agrega el item a la bolsa, limpiar la misión actual y subir de nivel del personaje.
     *
     * @param  personajeJugador         El personaje jugador al cual se le asignará la recompensa.
     * @throws IllegalStateException    Si la misión no ha sido finalizada, si la bolsa del personaje está llena.
     */
    public void asignarRecompensa (PersonajeJugador personajeJugador)
    {
        if (mision == personajeJugador.getMision() && personajeJugador.isMisionFinalizada())
        {
            throw new IllegalStateException("Mision no finalizada");
        }
        try
        {
            personajeJugador.getBolsa().addElemento(recompensa);
            personajeJugador.setMision(null);
            personajeJugador.subirNivel();
        }
        catch (BolsaLlenaException e)
        {
            throw new IllegalStateException("Bolsa llena");
        }
    }
}
