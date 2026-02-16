El proyecto implementa una sistema de una maquina de cafe.
- La maquina posee Recipientes que contienen los Ingredientes necesarios para preparar diferentes tipos de Producto.
- La maquina permite configurarla para despachar diferentes productos. 
- Cada producto tiene asociada una Receta.
- La maquina cuenta con una Caja, donde ingresa el dinero para comprarel producto.

1. Implementar los métodos de la clase Receta según la documentación.

    public void agregarIngrediente(Ingrediente ing, Integer cantidad)
    public List<Ingrediente> getIngredientes()
    public int getCantidadDeIngredienteRequerida (Ingrediente ingrediente)
    public int volumenTotalReceta()
    public String toString()
    
(evaluamos sobreescritura de metodo toString, manejo de mapas, iterar un mapa) (15%)



2. Implementar la clase Silo, que extiende de Recipiente, e implementa los siguientes constructores:

    public Silo (Ingrediente ingredienteAlmacenado, int capacidadMaxima)
    public Silo (Ingrediente ingredienteAlmacenado)
    public abstract Ingrediente getTipoIngredienteAlmacenado()
    public abstract void agregar (int cantidadAgregada) throws CapacidadExcedidaException
    public abstract void extraer (int cantidadAExtraer) throws CapacidadExcedidaException
    
(evaluamos herencia, clase abstracta, constructores, excepciones verificadas) (16%)
      


3. Implementar la clase Caja

    public void setPrecioACobrar(int precioProducto) 
    public boolean saldoSuficiente()
    public void completarVenta()
    public void agregarSaldo (int valor)

(evaluamos constructores por defecto, operaciones basicas, excepciones) (22%)



4. Implementar los métodos de la interfaz MaquinaDeEstados en la clase MaquinaDeCafe e
Implementar los métodos faltantes de la clase MaquinaDeCafe, segun la documentación

 - MaquinaDeEstados:
    . operar:
        - lanza IllegalStateException si 
            - la maquina no esta LISTA
            - no hay saldo suficiente en la caja
            - no se puede despachar el producto seleccionado     
        - cambia el Estado de la maquina a OPERANDO
        - prepara el producto seleccionado
        - actualiza contadores
        - cierra la venta en la caja
    . encender:
        - lanza IllegalStateException si la maquina no esta APAGADA
        - cambia el Estado de la maquina a LISTO
        - establece la seleccion de producto en null
    . restablecer:
        - lanza IllegalStateException si la maquina esta APAGADA
        - cambia el Estado de la maquina a LISTO
        - establece la seleccion de producto en null
    . apagar:
        - lanza IllegalStateException si la maquina esta OPERANDO
        - cambia el Estado de la maquina a APAGADO.
    . mantener:
        - lanza IllegalStateException si la maquina no esta LISTA
        - cambia el Estado de la maquina a MANTENIMIENTO

 - MaquinaDeCafe:
    public Recipiente getRecipiente (Ingrediente ingrediente)
    public void setSeleccion(Producto seleccion) 
    public void programarRecetaDeProducto (Producto p, Receta r)
    public void programarPrecioDeProducto (Producto p, int precio)
    public List<Ingrediente> getFaltantes()
    public void prepararProducto(Producto seleccion) 
    
(evaluamos uso de enums, manejo de colecciones, herencia, interfaces, excepciones, aplicación de un caso de uso, etc) (37%)


Resuelto por Ernes. (83/90)