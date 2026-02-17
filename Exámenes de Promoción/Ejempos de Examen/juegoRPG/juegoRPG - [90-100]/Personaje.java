public abstract class Personaje
{
    protected Mapa mapa;
    private int ataque, energia;
    private Posicion pos;
    private String nombre;

    /**
     * Inicializa un personaje. Setea el valor de ataque y de energia Por defecto si inicializa la posicion a (0,0)
     */
    Personaje(int ataque, int energia, String nombre)
    {
        this.ataque = ataque;
        this.energia = energia;
        this.nombre = nombre;
        pos = new Posicion(0,0);
    }
    
    /**
     * Setea este personaje en el Mapa. Si el nombre del personaje ya existe en este mapa se generara una excepción.
     * 
     * @param   m                       El Mapa donde se ubicara el personaje.
     * @return                          true si pudo setear el personaje en el mapa, false en caso contrario.
     * @throws NombreDuplicadoException
     */
    public boolean setMapa(Mapa m) throws NombreDuplicadoException
    {
        mapa = m;    
        if(mapa != null)
        {
            return mapa.agregarPersonaje(this);
        }
        return true;
    }
    
    /**
     * Define los cambios que se producen al cambiar de nivel.
     * 
     * Este metodo debe ser implementado por las subclases.
     */
    public abstract void subirNivel();

    /**
     * Como se mueve de una posicion a la otra.
     * 
     * Este metodo debe ser implementado por las subclases.
     */
    public abstract void mover();

    /**
     * Getter de la variable nombre.
     */    
    public String getNombre()
    {
        return this.nombre;
    }

    /**
     * @return  La posicion de este personaje.
     */
    public Posicion getPosicion()
    {
        return this.pos;
    }

    /**
     * @return  El valor de ataque de este personaje.
     */
    public int getAtaque()
    {
        return this.ataque;
    }

    /**
     * 
     * @return  La energía de este personaje.
     */
    public int getEnergia()
    {
        return this.energia;
    }
    
    /**
     * Incrementa la energía de este personaje.
     * 
     * @param energia   Cantidad de energía que será incrementada.
     */
    protected void incrementarEnergia(int energia)
    {
        this.energia += energia;
    }

    /**
     * Incrementa el ataque de un personaje
     * 
     * @param ataque    Cantidad de ataque que será incrementado.
     */
    protected void incrementarAtaque(int ataque)
    {
        this.ataque += ataque;
    }

    /**
     * Setter de la variable posición.
     * 
     * Si este personaje esta asociado a un mapa (mapa != null), chequea que la posición en el mapa este libre
     * antes de setear la posición. Si no esta ocupada, setea la posición.
     * 
     * Si el personaje no esta asociado a un mapa, setea la posición.
     * 
     * @return  true si se pudo setear la posición, false en caso contrario.
     * 
     */
    public boolean setPosicion(Posicion pos)
    {
        if(mapa != null)
        {
            if(!mapa.posicionDisponible(pos))
            {
                return false;
            }
        }
        this.pos = pos;
        return true;
    }
}