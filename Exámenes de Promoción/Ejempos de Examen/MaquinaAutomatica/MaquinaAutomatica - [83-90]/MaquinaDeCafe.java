import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * La clase MaquinaDeCafe que implementa MaquinaDeEstados. Nótese que si bien esta ultima clase define a un estado "FALLA", este no es pedido por la consigna
 * ni existe siquiera en el enum Estado, asique tenga cuidado con la documentación de la clase MaquinaDeEstados.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 32/37
 */
public class MaquinaDeCafe implements MaquinaDeEstados
{
    protected Estado estado = Estado.APAGADO;
    protected List<Recipiente> recipientes;
    protected List<Producto>   productosDisponibles;
    protected Caja caja;
    protected Producto seleccion;
    protected int totalProductosServidos;
    
    /**
     * Constructor sin parámetros.
     */
    public MaquinaDeCafe()
    {
        this.recipientes            = new ArrayList<Recipiente>();
        this.productosDisponibles   = new ArrayList<Producto>();
        this.caja                   = new Caja();
        this.totalProductosServidos = 0;
    }
    
    // Estados de la clase:
    /**
     * Método ENCENDER:
     *  - lanza IllegalStateException si la maquina no esta APAGADA
     *  - cambia el Estado de la maquina a LISTO
     *  - establece la seleccion de producto en null
     * 
     * @throws IllegalStateException cuando se intenta operar desde un estado no valido (si la maquina no esta APAGADA)
     */
    @Override
    public void encender()
    {
        //TODO: Implementar metodo 
        if(this.getEstado() != Estado.APAGADO)
        {
            throw new IllegalStateException();
        }
        else
        {
            this.restablecer();
        } 
    }

    /**
     * Método APAGAR:
     *  - lanza IllegalStateException si la maquina esta OPERANDO
     *  - cambia el Estado de la maquina a APAGADO.
     * 
     * @throws IllegalStateException cuando se intenta operar desde un estado no valido
     */
    @Override
    public void apagar()
    {
        //TODO: Implementar metodo
        if(this.getEstado() == Estado.OPERANDO)
        {
            throw new IllegalStateException();
        }
        else
        {
            this.estado = Estado.APAGADO;
        }
    }
    
    /**
     * Método OPERAR:
     *  - Lanza IllegalStateException si la maquina no esta LISTA.
     *  - Cambia el Estado de la máquina a OPERANDO.     
     *  - Verifica si puede despachar el producto seleccionado.
     *  - Despacha el producto seleccionado.
     *  - Incrementa la cuenta de productos despachados.
     * 
     * @throws IllegalStateException    cuando se intenta operar desde un estado no valido
     */
    @Override
    public void operar()
    {
        //TODO: Implementar metodo
        if(this.getEstado() != Estado.LISTO)
        {
            throw new IllegalStateException();
        }
        else
        {
            this.estado = Estado.OPERANDO;
            if(!this.validarProducto(this.getSeleccion()))      // ¿Se puede despachar?
            {
                throw new IllegalStateException();
            }
            
            try
            {
                this.prepararProducto(this.getSeleccion());     // Preparo el producto.
                this.caja.completarVenta();                     // Completo la venta.
                this.totalProductosServidos++;                  // Actualizo el campo.
            }
            catch (ProductoException e)
            {
            }
        }
    }

    /**
     * Método RESTABLECER:
     *  - Cambia el Estado de la maquina a LISTO
     *  - Establece la seleccion de producto en null
     */
    @Override
    public void restablecer()
    {
        //TODO: Implementar metodo
        this.estado = Estado.LISTO;
        this.seleccion = null;
    }
    
    /** 
     * Método MANTENER:
     *  - lanza IllegalStateException si la maquina no esta LISTA
     *  - cambia el Estado de la maquina a MANTENIMIENTO
     */
    @Override
    public void mantener()
    {
        //TODO: Implementar metodo  
        if(this.getEstado() != Estado.LISTO)
        {
            throw new IllegalStateException();
        }
        else
        {
            this.estado = Estado.MANTENIMIENTO;
        }
    }
    
    
    // Métodos a implementar:
    /**
     * Programa el precio a un producto. Solo es posible programar el precio en modo MANTENIMIENTO
     *
     * @param producto                  El producto al cual definir el precio.
     * @param precio                    El precio del producto.
     * @throws IllegalStateException    Cuando no esta en modo MANTENIMIENTO.
     */
    public void programarPrecioDeProducto(Producto producto, int precio)
    {
        //TODO: Implementar metodo
        if(this.getEstado() == Estado.MANTENIMIENTO)
        {
            producto.setPrecio(precio);
        }
        else
        {
            throw new IllegalStateException();
        }
    }
    
    /**
     * Agrega una receta para un producto de la máquina de cafe. Solo es válido en modo mantenimiento.
     * 
     * @param producto                  El Producto a configurar en la maquina.
     * @param receta                    La Receta correspondiente al producto a agregar.
     * @throws IllegalStateException    Si la maquina no esta en modo MANTENIMIENTO.
     */
    public void programarRecetaDeProducto(Producto producto, Receta receta)
    {
        //TODO: Implementar metodo
        if(this.getEstado() == Estado.MANTENIMIENTO)
        {
            producto.setReceta(receta);
        }
        else
        {
            throw new IllegalStateException();
        }
    }
    
    /**
     * Retorna una lista de ingredientes faltantes para las recetas cargadas en la máquina
     * 
     * @return Lista de ingredientes faltantes.
     */
    public List<Ingrediente> getFaltantes()
    {
        //TODO: Implementar metodo
        HashSet<Ingrediente> faltantes = new HashSet<>();
        for(Producto p : productosDisponibles)
        {
            Receta r = p.getReceta();
            for(Ingrediente i : r.getIngredientes())
            {
                int cantidadRequerida = r.getCantidadDeIngredienteRequerida(i);
                if(!hayIngredienteDisponible(i, cantidadRequerida))
                {
                    faltantes.add(i);
                }
            }
        }
        return new ArrayList<Ingrediente>(faltantes);
    }
    
    /**
     * Ejecuta la receta para el producto especificado, extrayendo de cada recipiente la cantidad necesaria de ingredientes
     * para preparar la receta.
     * 
     * @param  seleccion            el producto seleccionado 
     * @throws ProductoException    si por algun motivo no se puede extraer la cantidad requerida de cada ingrediente.
     */
    public void prepararProducto(Producto seleccion) throws ProductoException
    {
        //TODO: Implementar metodo
        Receta r = seleccion.getReceta();
        for(Ingrediente i : r.getIngredientes())
        {
            int cantidad = r.getCantidadDeIngredienteRequerida(i);
            Recipiente recipiente = this.getRecipiente(i);
            if(recipiente == null)
            {
                throw new ProductoException("");
            }
    
            if(recipiente.getCantidadIngredienteDisponible() < cantidad)
            {
                throw new ProductoException("");
            }
            try
            {
                recipiente.extraer(cantidad);
            }
            catch(CapacidadExcedidaException e)
            {
                // No hace nada
            }
        }
        
    }
    
    /**
     * Retorna el recipiente para el producto especificado
     *
     * @param ingrediente   el ingrediente requerido
     * @return              el recipiente que contiene el ingrediente requerido, o null si no existe un recipiente para ese ingrediente.
     */
    public Recipiente getRecipiente(Ingrediente ingrediente)
    {
        //TODO: Implementar metodo
        for(Recipiente r : recipientes)
        {
            if(r.getTipoIngredienteAlmacenado().equals(ingrediente))
            {
                return r;
            }
        }
        return null;
    }
    
    /**
     * Selecciona un producto para despachar. Solo es posible si la maquina esta lista para despachar producto. Configura
     * el precio del producto como precio a cobrar en la Caja, o 0 si la seleccion es null
     *
     * @param seleccion                 el producto a despachar
     * @throws IllegalStateException    si la maquina no esta lista
     */
    public void setSeleccion(Producto seleccion)
    {
        //TODO: Implementar metodo
        if(this.getEstado() != Estado.LISTO)
        {
            throw new IllegalStateException();
        }
        if(this.getSeleccion() == null)
        {
            this.caja.setPrecioACobrar(0);
        }
        this.caja.setPrecioACobrar(seleccion.getPrecio());
    }
    
    
    
    // Métodos y getters ya dados:
    /**
     * Verifica si existe suficiente cantidad de un ingrediente.
     *
     * @param ingrediente           el ingrediente requerido
     * @param cantidadRequerida     la cantidad requerida
     * @return                      true si existe suficiente ingrediente, false en caso contrario
     */
    public boolean hayIngredienteDisponible(Ingrediente ingrediente, int cantidadRequerida)
    {
        for (Recipiente r: recipientes)
        {
            if (ingrediente == r.getTipoIngredienteAlmacenado())
            {
                if (r.getCantidadIngredienteDisponible()>=cantidadRequerida)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica si es posible despachar el producto especificado.
     * 
     * @param p                     El producto a despachar
     * @throws ProductoException    Si el producto especificado es null, o no esta configurado en esta maquina, o existen
     *                              ingredientes faltantes en la receta.
     */
    public boolean validarProducto(Producto p)
    {
        if(p==null || p.getReceta()==null )
        {
            return false;
        }
        Receta r =  p.getReceta();
        for(Ingrediente ing: r.getIngredientes())
        {
            if(!hayIngredienteDisponible(ing, r.getCantidadDeIngredienteRequerida(ing)))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Agrega un recipiente del ingrediente dado
     */
    public void agregarRecipiente(Recipiente r)
    {
        recipientes.add(r);
    }
    
    /**
     * Retorna la cantidad total de productos servidos por esta máquina.
     * 
     * @return cantidad total de productos servidos
     */
    public int getTotalProductosServidos()
    {
        return totalProductosServidos;
    }
    
    /**
     * Retorna el total de dinero recaudado por esta maquina
     * 
     * @return total de dinero recaudado
     */
    public int getTotalRecaudacion()
    {
        return caja.getTotalRecaudado();
    }
    
    /**
     * Retorna el producto seleccionado
     *
     * @return el producto seleccionada en la maquina
     */
    public Producto getSeleccion()
    {
        return seleccion;
    }
    
    @Override
    public Estado getEstado()
    {
        return estado;
    }
}