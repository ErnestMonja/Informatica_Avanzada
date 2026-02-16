/**
 * La clase Taller modela las gestiones de compra y venta de autopartes. Se comienza con dinero en la billetera y se debe comprar y vender autopartes.
 * 
 * Nota: Si revisa el código de RegistroAutopartes, este tiene un constructor privado lo que imposibilita instanciarlo en esta clase. Mi solución fue usar
 * el método ya provisto getRegistro() sin embargo considero que se trata de una implementación medio pobre, no solo en este constructor, sino en los métodos
 * de la clase en general.
 * 
 * Dados los problemas listados en las clases anteriores, perdi bastante tiempo del examen conversando con los profesores y me dejo sin tiempo para plantear
 * mejor esta clase, por lo que recomendaría borrar todo lo que este escrito debajo del "TODO - implementar metodo", y arrancar este ejemplo de cero.
 *      
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 12/30
 */
public class Taller
{
    private Billetera billetera;
    private RegistroAutopartes catalogo;

    /**
     * Crea el objeto Taller, desde el cual se gestiona al resto de los objetos.
     * 
     * Debe instanciarse el RegistroAutopartes y la billetera con $100.
     * 
     * Inicialmente no hay ninguna autoparte.
     */
    public Taller()
    {
        // TODO implementar metodo
        this.billetera = new Billetera(100.0);
        this.catalogo = RegistroAutopartes.getRegistro();
    }
    
    /**
     * Se utiliza para comprar una autoparte y agregarla al catalogo.
     * 
     * La operacion se concreta si hay dinero suficiente en la billetera.
     * 
     * Notar que la billetera puede lanzar DineroInsuficienteException.
     * 
     * Si no hay dinero suficiente, se imprime el mensaje "Compra fallida".
     *  
     * @param autoparte La autoparte a comprar.
     * @param cantidad  La cantidad a comprar.
     */
    public void comprarAutoparte(Item autoparte, Integer cantidad)
    {
        // TODO implementar metodo
        try
        {
            if(this.billetera.getPesos() >= autoparte.getPrecio()*cantidad)
            {
                this.billetera.gastar(autoparte.getPrecio()*cantidad);
                for(int i = 0; i < cantidad; i++)
                {
                    this.catalogo.agregarAutoParte(autoparte);
                }
            }
        }
        catch(DineroInsuficienteException e)
        {
            System.out.println("Compra fallida");
        }
    }
    
    /**
     * Se utiliza para vender una autoparte del catalogo. El precio de venta en de un 25% más sobre el precio del item.
     * 
     * La operacion se concreta si la autoparte existe en el catálogo.
     * 
     * Se debe actualizar el dinero en la billetera.
     * 
     * Notar que la autoparte puede no existir, en ese caso se debe lanzar IllegalArgumentException e imprimir el mensaje "Venta fallida".
     *  
     * @param autoparte La autoparte a vender.
     * @param cantidad  La cantidad a vender.
     */
    public void venderAutoparte(Item autoparte, Integer cantidad)
    {
        // TODO implementar metodo
        if(this.catalogo.contains(autoparte.getId()))
        {
            for(int i = 0; i<cantidad ; i++)
            {
                this.billetera.agregarPesos(autoparte.getPrecio());
                this.catalogo.removerAutoParte(autoparte.getId());
            }
        }
        else
        {
            throw new IllegalArgumentException("Venta fallida");
        }
    }

    /**
     * Se utiliza para recargar una autoparte que lo permita al máximo.
     * 
     * La operacion se concreta si hay dinero suficiente en la billetera.
     * 
     * Notar que la billetera puede lanzar DineroInsuficienteException.
     *
     * Si no hay dinero suficiente, se imprime el mensaje "Carga fallida".
     *  
     * @param autoparte     La autoparte a comprar.
     */
    public void cargarCombustible(Recargable item)
    {
        // TODO implementar metodo
        try
        {
            if(this.billetera.getPesos() >= item.getCostoCargaCompleta())
            {
                this.billetera.gastar(item.getCostoCargaCompleta());
                //this.catalogo.agregarAutoParte(item);
            }
        }
        catch(DineroInsuficienteException e)
        {
            System.out.println("Carga fallida");
        }
    }
    
    public void comprarAutoparte(Item item)
    {
        comprarAutoparte(item, 1);
    }
    
    public void venderAutoparte(Item item)
    {
        venderAutoparte(item, 1);
    }
    
    public void vaciarCombustible(Recargable item)
    {
        item.setPorcentajeCarga(0);
    }

    public Billetera getBilletera()
    {
        return billetera;
    }

    public RegistroAutopartes getRegistroAutopartes()
    {
        return catalogo;
    }
}