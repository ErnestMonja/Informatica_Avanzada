import java.util.List;

/**
 * Clase Heroe a completar. Se le avisa al lector de esta clase, que para ahorrarse sanidad mental, siempre que se hable de Vida en verdad se refiere
 * a las variables que esten relacionadas con la energÍa. Seguramente (al igual que la pobre calidad de los otros 3 ejemplos del lev) se debe a un
 * error de diseño de este examen.
 * 
 * Sin embargo se logro el puntaje completo en esta clase, por lo que esta afirmación presupuesta por yo mismo es efectivamente cierta.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 40/40
 */
public class Heroe extends Personaje implements Luchador
{
    private static final int ATAQUE = 20;
    private static final int ENERGIA = 100;
    private static final int INCREMENTO_ENERGIA = 10;
    private static final int INCREMENTO_ATAQUE = 5;
    private Faccion faccion;

    /**
     * Crea una nueva instancia de Heroe, con el nivel de ataque y energia por defecto (para Heroe)
     * 
     * @param nombre  Nombre del Heroe.
     * @param faccion Faccion a la cual pertenece.
     */
    Heroe(String nombre, Faccion faccion)
    {
        //COMPLETAR
        super(ATAQUE, ENERGIA, nombre);
        this.faccion = faccion;
    }

    /**
     * El heroe sólo puede atacar a objetivos cercanos (ver método obtenerPersonajesCercanos clase Mapa)
     * 
     * Debe ser un villano o bien un Heroe de una facción diferente a la del heroe.
     * 
     * Los danios se hacen mediante el metodo RecibirGolpe con el nivel de ataque propio.
     */
    @Override
    public void atacar()
    {
        //COMPLETAR
        List<Personaje> personajesCercanos = super.mapa.obtenerPersonajesCercanos(this);
        for(Personaje p : personajesCercanos)
        {
            if(p instanceof Villano)
            {
                Villano v = (Villano) p;
                v.recibirGolpe(this.ATAQUE);
            }
            if(p instanceof Heroe)
            {
                Heroe h = (Heroe) p;
                if(h.getFaccion() != this.getFaccion())
                {
                    h.recibirGolpe(this.ATAQUE);
                }
            }
        }
    }

    /**
     * Disminuye la vida del Heroe por el valor del danio.
     */
    @Override
    public void recibirGolpe(int danio)
    {
        //COMPLETAR
        super.incrementarEnergia(-danio);
    }

    /**
     * Aumenta su vida de acuerdo a la variable INCREMENTO_VIDA y su ataque de acuerdo a la variable INCREMENTO_ATAQUE
     */
    @Override
    public void subirNivel()
    {
        //COMPLETAR
        super.incrementarAtaque(INCREMENTO_ATAQUE);
        super.incrementarEnergia(INCREMENTO_ENERGIA);
    }

    /**
     * Mueve UNA VEZ a la primera posición disponible en el siguiente orden:
     *  1. ARRIBA
     *  2. ABAJO
     *  3. DERECHA
     *  4. IZQUIERDA
     * Si ningún movimiento esta disponible el heroe se queda quieto.
     * 
     * AYUDAS:
     * - Las direcciones se pueden recorrer como una lista en ese orden utilizando Direccion.values().
     * - Metodo realizarMovimiento (moverPersonajeEnDireccion()) de Mapa para comprobar que el movimiento sea legal.
     */
    @Override
    public void mover()
    {
        //COMPLETAR
        for(Direccion d : Direccion.values())
        {
            if(super.mapa.moverPersonajeEnDireccion(this, d))
            {
                super.getPosicion().nuevaPosicion(d);
            }
        }
    }

    /**
     * Devuelve la faccion del Heroe.
     * 
     * @return
     */
    public Faccion getFaccion()
    {
        // COMPLETAR
        return this.faccion;
    }
}