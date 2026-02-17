 import java.lang.Integer;

/**
 * Representa una posicion determinada por  el eje x (Horizontal) y el eje y (Vertical).
 */
public class Posicion
{
    Posicion(Integer initPosx, Integer initPosy)
    {
        _posx = initPosx;
        _posy = initPosy;
    }
    
    Posicion()
    {
       _posx = 0;
       _posy = 0;
    }
    
    /**
     * Si la posición es un valor válido (positivo) cambia el valor de lo contrario, genera una excepción comprobada del tipo IllegalArgumentException
     * con el mensaje: "Posicion Ilegal".
     */
    public void setPosicion(Integer posx, Integer posy) throws IllegalArgumentException
    {
        if(posx < 0 || posy < 0)
        {
            throw new IllegalArgumentException("Posicion Ilegal");
        }
        else
        {
            _posx = posx;
            _posy = posy;
        }
    }

    /**
     * Calcula la nueva posición en base a la posición actual y la dirección de entrada.
     * 
     * Tener en cuenta que la dirección va a afectar la posición en X o en Y de la siguiente forma:
     *  Arriba:    y+1
     *  Abajo:     y-1
     *  Derecha:   x+1
     *  Izquierda: x-1
     *  
     * @param  d    Direccion a ser evaluada.
     * @return      La nueva posición tentativa (sin modificar al objeto).
     */
    public final Posicion nuevaPosicion(Direccion d)
    {
        Posicion pos = new Posicion(getX(), getY());
        switch(d)
        {
            case ARRIBA:
                pos.setPosicion(getX(), getY()+1);
                break;
            case ABAJO:
                pos.setPosicion(getX(), getY()-1);
                break;
            case DERECHA:
                pos.setPosicion(getX()+1, getY());
                break;
            case IZQUIERDA:
                pos.setPosicion(getX()-1, getY());
                break;
            default:
                break;
        }
        return pos;
    }

    public Integer getX()
    {
        return _posx;
    }

    public Integer getY()
    {
        return _posy;
    }

    private Integer _posx, _posy;
}