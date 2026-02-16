import java.util.*;
import java.time.LocalDateTime;

/**
 * La clase GestorReservas administra las reservas de Salas. Posee un GestorSalas que gestiona las Salas, y un Mapa que relaciona cada Sala con una Lista de
 * Reservas.
 * 
 * Hay que implementar los siguientes métodos.
 *  - Agregar una reserva a una Sala (reservar).
 *  - Libera una reserva de una Sala (liberar).
 *  - Lista todas las Salas disponibles (disponibles).
 *  
 *  Nota: puede utilizar los métodos seSuperporne de la clase Reserva para determinar si un horario y duración se superpone con la reserva.
 *
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: xx/25
 */
public class GestorReservas
{
    private GestorSalas gestorSalas;
    protected Map<Sala, List<Reserva>> reservas;

    /**
     * Constructor.
     */
    public GestorReservas(GestorSalas gestorSalas)
    {
        this.gestorSalas = gestorSalas;
        this.reservas = new HashMap<>();
    }
    
    public List<Reserva> getReservasPorSala(String nombreSala)
    {
        Sala sala = gestorSalas.buscarSala(nombreSala);
        if(sala != null && reservas.containsKey(sala))
        {
            return reservas.get(sala);
        }
        return new ArrayList<>();
    }

    /**
     * Reserva una sala para un usuario, a la hora y con la duración indicada.
     *  
     *  - Si la sala no existe en el gestor de Salas -> Lanza Exception()
     * 
     *  - Revisa todas las reservas asociadas a la sala, y si existe alguna que se superpone -> Lanza Exception )=
     * 
     *  - Si la sala existe, y no tienen ninguna reserva superpuesta se genera una nueva Reserva y se agrega al mapa de reservas
     *    y retorna true
     * 
     * @throw  Exception    si la sala no existe, o si tiene una reserva en el horario requerido
     * @return              true si se pudo generar la reserva.
     * 
     */
    public boolean reservar(String nombreSala, Usuario usuario, LocalDateTime inicio, int duracion) throws Exception
    {
        //TODO implementar método.
        Sala salaABuscar = this.gestorSalas.buscarSala(nombreSala);
        if(salaABuscar == null)
        {
            throw new Exception("La sala no existe.");
        }
        
        List<Reserva> lista = reservas.get(salaABuscar);
        if(lista == null)                   // Capaz la lista que va con
        {
            lista = new ArrayList<>();
            reservas.put(salaABuscar, lista);
        }
        
        for(Reserva r : lista)
        {
            if(r.seSuperpone(inicio, duracion))
            {
                throw new Exception("La sala ya está reservada en ese horario.");
            }
        }
        
        Reserva reservaNueva = new Reserva(salaABuscar, usuario, inicio, duracion);
        lista.add(reservaNueva);
        return true;
    }

    /**
     * Libera la sala de la reserva agendada a la hora específicada.
     * 
     * Obtiene la Sala por su nombre del gestor de Salas, si no existe o no tiene
     * reservas asociadas, no hace nada.
     * 
     * Recorre la lista de reservas de la sala, busca si hay alguna que inicie
     * a la hora especificada y la remueve.
     */
    public void liberar(String nombreSala, LocalDateTime inicio)
    {
        //TODO implementar. 
        Sala salaABuscar = this.gestorSalas.buscarSala(nombreSala);
        if(salaABuscar == null)
        {
            return;
        }
        
        List<Reserva> listaReservas = reservas.get(salaABuscar);
        if(listaReservas != null)
        {
            Iterator<Reserva> it = listaReservas.iterator();
            while(it.hasNext())
            {
                Reserva r = it.next();
                if(r.getInicio().equals(inicio))
                {
                    it.remove();
                }
            }
        }
    }

    /**
     * Devuelve una lista de todas las salas DISPONIBLES con una capacidad mínima y en una ubicación.
     * 
     * Recorre todas las salas existentes, y de cada sala recorre la lista de reservas y determina si 
     * están o no disponibles a la hora indicada por el tiempo indicado. Si está disponible, agrega la 
     * Sala a la lista.
     * 
     * @return  la lista con todas las salas disponbiles.
     */
    public List<Sala> disponibles(LocalDateTime inicio, int duracion)
    {
        //TODO implementar método
        List<Sala> disponibles = new ArrayList<Sala>();
        List<Sala> todas = gestorSalas.getTodas();
        
        for(Sala s : todas)
        {
            List<Reserva> lista = reservas.get(s);
            boolean libre = true;
            
            if(lista != null)
            {
                for(Reserva r : lista)
                {
                    if(r.seSuperpone(inicio, duracion))
                    {
                        libre = false;
                        break;
                    }
                }
            }
            if(libre)
            {
                disponibles.add(s);
            }
        }
        return disponibles;
    }
}