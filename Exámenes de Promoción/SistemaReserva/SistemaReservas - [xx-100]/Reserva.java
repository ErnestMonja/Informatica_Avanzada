import java.time.LocalDateTime;

/**
 * Completa los métodos indicados de la clase Reserva según la documentación
 * 
 * - Implementar el método toString con formato: "Reserva de <nombre_sala> por <usuario> desde <fecha_hora_inicio> por <duracion>h".
 * - Implementar el método seSuperpone(...) para verificar si una reserva se superpone con otra en función del horario.
 * 
 * El sistema utiliza la clase LocalDateTime de la biblioteca java.time para representar fechas y horas. Esta clase permite expresar momentos como “2025-06-
 * 15T10:00” y sumar horas usando el método plusHours(int). También pueden compararse con operadores >, <, o utlizando el método isBefore(LocalDateTime).
 * 
 * No es necesario considerar zonas horarias ni diferencias geográficas. Todos los horarios se interpretan como locales.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: xx/15
 */
public class Reserva
{
    protected Sala sala;
    protected Usuario usuario;
    protected LocalDateTime inicio;
    protected int duracionHoras;

    public Reserva(Sala sala, Usuario usuario, LocalDateTime inicio, int duracionHoras)
    {
        this.sala = sala;
        this.usuario = usuario;
        this.inicio = inicio;
        this.duracionHoras = duracionHoras;
    }

    /**
     * Indica si el horario de esta Reserva se superpone con otro horario y duración pasada como parámetro.
     * 
     * Por ejemplo, si esta reserva es de 9 a 11,
     *      otroInicio 10, otrasHoras 1 -> true
     *      otroInicio 8,  otrasHoras 2 -> true
     *      otroInicio 8,  otrasHoras 1 -> false
     */
    public boolean seSuperpone(LocalDateTime otroInicio, int otrasHoras) 
    {
        //TODO Implementar       
        LocalDateTime fin = this.getInicio().plusHours​(this.getDuracionHoras());
        LocalDateTime otroFin = otroInicio.plusHours(otrasHoras);
        
        return (otroInicio.isBefore(fin)&&this.getInicio().isBefore(otroFin));
    }

    /**
     * Retorna un string que represente la Reserva con el formato:
     *  "Reserva de <nombre_sala> por <usuario> desde <hora_inicio> por <duracion> horas"
     * 
     * Ej
     *  "Reserva de Uritorco por Juan Perez <jp@perez.com> desde 17:00 por 1 horas"
     */
    @Override
    public String toString()
    {
        //TODO Implementar
        String info = "Reserva de " + this.getSala().getNombre()
                    + " por " + this.getUsuario().toString()
                    + " desde " + this.getInicio()
                    + " por " + this.getDuracionHoras() + "h";
        return info;
    }
    
    public Sala getSala()
    {
        return sala;
    }
    
    public Usuario getUsuario()
    {
        return usuario;
    }
    
    public LocalDateTime getInicio()
    {
        return inicio;
    }
    
    public int getDuracionHoras()
    {
        return duracionHoras;
    }
}