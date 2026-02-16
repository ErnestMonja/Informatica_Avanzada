import java.time.LocalDateTime;

/**
 * Implemente la Clase ReservaRapida tal que:
 *  - Extienda de Reserva.
 *  - Implemente la interfaz Cancelable.
 *  - El método cancelar() muestre un mensaje simple por consola
 *  - Reservar por defecto 1 hora (ReservaRapida es una Reserva con una duración de 1hr).
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: xx/20
 */
public class ReservaRapida extends Reserva implements Cancelable
{
    private static final int DURACION_HORA_RAPIDA = 1;

    /**
     * Constructor de objectos para la clase ReservaRapida.
     */
    public ReservaRapida(Sala sala, Usuario usuario, LocalDateTime inicio)
    {
        super(sala, usuario, inicio, DURACION_HORA_RAPIDA);
    }

    /**
     * ¿Cancela como?
     */
    public void cancelar()
    {
        System.out.println("Reserva rápida cancelada: " + super.toString());
    }
    
    public int getDuracionHoraRapida()
    {
        return DURACION_HORA_RAPIDA;
    }
    
}