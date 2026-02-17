import java.util.List;

/**
 * Clase Villano a completar.
 * 
 * Nota: Según el proyecto, todo indica a que la última clase a completar es esta, sin embargo el aula virtual provee una muy pobre implementación 
 * de la instancia de evaluación donde se confunde de proyecto en el último inciso y procede a evaluar la clase Ballesta que no se encuentra en este
 * proyecto.
 * 
 * Sin embargo, me he tomado la libertad de realizar un implementación a esta clase. Espero que algun día usted la pueda probar y corregir de acuerdo
 * a las pruebas ejecutadas.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: xx/20
 */
public class Villano extends Personaje implements Luchador
{
    private static final int ATAQUE = 20;
    private static final int ENERGIA = 500;
    private static final int INCREMENTO_ENERGIA = 20;
    private static final int INCREMENTO_ATAQUE = 5;
    
    /**
     * Crea un villano con el nombre pasado como argumento y los niveles de energía y ataque por defecto 
     * 
     * @param nombre    Nombre del villano en algún otro personaje
     */
    public Villano(String nombre)
    {
        // Implementar
        super(ATAQUE, ENERGIA, nombre);
    }

    /**
     * Aumenta tanto la vida como el ataque del villano en el factor especificado por la clase (INCREMENTO_VIDA, INCREMENTO_ATAQUE)
     */
    @Override
    public void subirNivel()
    {
        // Implementar
        super.incrementarAtaque(INCREMENTO_ATAQUE);
        super.incrementarEnergia(INCREMENTO_ENERGIA);
    }

    /**
     * El villano no se mueve en el tablero, su posicion es fija.
     */
    @Override
    public void mover()
    {
        // Implementar
        // El villano no se mueve, ergo este método no hace nada.
    }

    /**
     * El villano ataca a todos los objetos que atacables que estan a su vista con su valor de ataque.
     * 
     * AYUDA:   Vea el método obtenerPersonajesCercanos() de la clase mapa.
     *          Tener en cuenta que ese Personaje tiene que ser del tipo Atacable.
     *                                                                      (^) -> No existe tal tipo...
     */
    @Override
    public void atacar()
    {
        // Implementar
        List<Personaje> personajesCercanos = super.mapa.obtenerPersonajesCercanos(this);
        for(Personaje p : personajesCercanos)
        {
            if(p instanceof Heroe)
            {
                Heroe h = (Heroe) p;
                h.recibirGolpe(this.ATAQUE);
            }
            if(p instanceof Villano)
            {
                Villano v = (Villano) p;
                v.recibirGolpe(this.ATAQUE);
            }
            // Como el neutro no tiene el método recibirGolpe, no puede ser atacado.
        }
    }

    /**
     * Decrementa la vida del villano por la mitad del valor recibido.
     */
    @Override
    public void recibirGolpe(int danio)
    {
        // Implementar
        super.incrementarEnergia(-danio/2);
    }    
}