import java.util.List;
import java.util.ArrayList;

/**
 * Modela el Carcaj que lleva el personaje para tranportar sus flechas.
 *  
 * @author  Maximiliano A. Eschoyez y resuelto por Ernes.
 * @version 1.0
 *
 *                                                      [PUNTAJE xx/20]
 */
public class Carcaj extends Recipiente implements Portable
{
    private final Integer PESO_PROPIO = 10;
    private Integer capacidad;
    private List<Flecha> flechas;
    private EstadoContenedor estado;

    /**
     * Constructor para un Carcaj generico chico. Su capacidad es de 5 flechas y se inicializa en estado vacio (ver
     * Arroyo y Barrica a modo de ejemplo de los estados). Recordar que tiene peso inicial.
     */
    public Carcaj()
    {
        // TODO - Implementar constructor
        super();
        super.setNombre("Carcaj chico");
        super.setPeso(PESO_PROPIO);
        
        this.capacidad = 5;
        this.flechas = new ArrayList<>();
        this.estado = new Vacio();
    }
    
    /**
     * Constructor para un Carcaj de nombre y capacidad indicados por parámetro. Se inicializa en estado vacio (ver
     * Arroyo y Barrica a modo de ejemplo de los estados) Recordar que tiene peso inicial.
     *
     * @param nombre        El nombre del carcaj.
     * @param capacidad     Cantidad máxima de flechas.
     */
    public Carcaj(String nombre, Integer capacidad)
    {
        // TODO - Implementar constructor
        super();
        super.setNombre(nombre);
        super.setPeso(PESO_PROPIO);
        
        this.capacidad = capacidad;
        this.flechas = new ArrayList<>();
        this.estado = new Vacio();
    }
    
/* ***************************************************************** Patron State ***************************************************************** */
    /**
     * Delegate methods para acceder a los métodos apropiados en base al estado del objeto.
     * 
     * Agrega una flecha al carcaj. Debe actualizarse el peso total.
     * 
     * @param  flecha                       La flecha a agregar.
     * @throws ContenedorLlenoException     En caso que el carcaj se encuentre en su capacidad máxima con el mensaje: "Carcaj lleno".
     * @throws AccionNoPermitidaException   En caso que el elemento no sea una flecha  con el mensaje: "Carcaj no admite <elemento>" 
     *                                      (<elemento> == nombre del elemento).
     */
    public void addFlecha(Elemento elemento) throws ContenedorLlenoException, AccionNoPermitidaException
    {
        // delegate method a getElemento() de la interfaz
        estado.addElemento(elemento);
    }
    
    @Override
    public void addElemento(Elemento elemento) throws ContenedorLlenoException, AccionNoPermitidaException
    {
        // delegate method a getElemento() de la interfaz
        estado.addElemento(elemento);
    }

    /**
     * Delegate methods para acceder a los métodos apropiados en base al estado del objeto.
     * 
     * Devuelve una flecha del carcaj como Elemento. Debe actualizarse el peso total.
     * 
     * @return                              Una flecha.
     * @throws ContenedorVacioException     En caso que el carcaj se encuentre vacío con el mensaje: "Carcaj vacio".
     * @throws AccionNoPermitidaException   No aplica.
     */
    public Elemento getFlecha() throws ContenedorVacioException, AccionNoPermitidaException
    {
        // delegate method a getElemento() segun estado
        return estado.getElemento();
    }
    
    @Override
    public Elemento getElemento() throws ContenedorVacioException, AccionNoPermitidaException
    {
        // delegate method a getElemento() segun estado
        return estado.getElemento();
    }

    /**
     * Se utiliza la version implementada en EstadoContenedor, que siempre lanza la excepcion AccionNoPermitidaException.
     */    
    @Override
    public Elemento getElemento(String nombre) throws ContenedorVacioException, AccionNoPermitidaException
    {
        // delegate method a getElemento() segun estado
        return estado.getElemento(nombre);
    }

    /*
     * Implementar los metodos de las clases privadas que sean necesarios para cada estado.
     * 
     * No olvidar contemplar las situaciones que producen el cambio a otro estado.
     * 
     * Ver lo implementado en Arroyo y Barrica. Considerar las pautas en Botella.
     */    
    /**
     * La clase VACIO indica el estado del Carcaj sin flechas. Debe cambiar al estado CONFLECHAS al agregarse una flecha.
     */
    private class Vacio extends EstadoContenedor
    {
        // TODO - Implementar la clase privada
        public void addElemento(Elemento elemento) throws ContenedorLlenoException, AccionNoPermitidaException
        {
            if(elemento instanceof Flecha)
            {
                Flecha f = (Flecha) elemento;
                flechas.add(f);
                addPeso(f.getPeso());
                estado = new ConFlechas();
            }
            else
            {
                throw new AccionNoPermitidaException("Carcaj no admite " + elemento.getNombre());
            }
        }
        
        public Elemento getElemento() throws ContenedorVacioException, AccionNoPermitidaException
        {
            throw new ContenedorVacioException("Carcaj vacio");
        }
        
        @Override
        public String toString()
        {
            return " sin flechas (vacio)";
        }
    }

    /**
     * La clase CONFLECHAS indica el estado del Carcaj con flechas. Debe cambiar al estado VACIO al quitarse la última flecha.
     * Debe cambiar al estado LLENO al agregar una flecha que complete la capacidad máxima.
     */
    private class ConFlechas extends EstadoContenedor
    {              
        // TODO - Implementar la clase privada
        public void addElemento(Elemento elemento) throws ContenedorLlenoException, AccionNoPermitidaException
        {
            if(elemento instanceof Flecha)
            {
                Flecha f = (Flecha) elemento;
                flechas.add(f);
                addPeso(f.getPeso());
                if(getCantidadFlechas().equals(getCapacidad()))
                {
                    estado = new Lleno();
                }
            }
            else
            {
                throw new AccionNoPermitidaException("Carcaj no admite " + elemento.getNombre());
            }
        }
        
        public Elemento getElemento() throws ContenedorVacioException, AccionNoPermitidaException
        {
            // Saco la ultima flecha
            Flecha f = flechas.remove(getCantidadFlechas() - 1);
            addPeso(-f.getPeso());
            if(getCantidadFlechas().equals(0))
            {
                estado = new Vacio();
            }
            return f;
        }
        
        @Override
        public String toString()
        {
            return " con " + getCantidadFlechas() + " flechas";
        }
    }

    /**
     * La clase LLENO indica el estado del Carcaj con flechas. Debe cambiar al estado CONFLECHAS al quitarse una flecha.
     */
    private class Lleno extends EstadoContenedor
    {
        // TODO - Implementar la clase privada
        public void addElemento(Elemento elemento) throws ContenedorLlenoException, AccionNoPermitidaException
        {
            throw new ContenedorLlenoException("Carcaj lleno");
        }
        
        public Elemento getElemento() throws ContenedorVacioException, AccionNoPermitidaException
        {
            // Saco la ultima flecha
            Flecha f = flechas.remove(getCantidadFlechas() - 1);
            addPeso(-f.getPeso());
            estado = new ConFlechas();
            return f;
        }
        
        @Override
        public String toString()
        {
            return " con " + getCantidadFlechas() + " flechas (lleno)";
        }
    }
/* *************************************************************** FIN Patron State **************************************************************** */

    /**
     * Modifica el peso del carcaj. Puede sumar o restar peso.
     *
     * @param peso  El peso a modificar.
     */
    public void addPeso (Integer peso)
    {
        setPeso(getPeso() + peso);
    }

    /**
     * Devuelve informacion sobre el carcaj de la forma:
     *          "<nombre> <estado>"
     * donde <nombre> es el nombre del carcaj y <estado> es el toString de cada estado.
     */
    @Override
    public String toString()
    {
        // TODO - Implementar metodo
        String info = super.getNombre() + this.getEstado().toString();
        return info;
    }

    public Integer getCantidadFlechas()
    {
        return flechas.size();
    }

    public Integer getCapacidad()
    {
        return capacidad;
    }

    public EstadoContenedor getEstado()
    {
        return estado;
    }
}