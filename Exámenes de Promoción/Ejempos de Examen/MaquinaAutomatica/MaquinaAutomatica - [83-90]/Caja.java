/**
 * Implementa la facturacion de la Máquina de Cafe.
 * 
 * @author  Resuelto por Ernes.
 * @version 1.0 
 *                                              PUNTAJE: 21/22
 */
public class Caja
{
    public static final int[] BILLETES_ACEPTADOS = {2, 5, 10, 20}; 
    protected int precioACobrar;
    protected int saldoActual;
    protected int totalRecaudado;
    protected int operacionesRealizadas;
    
    /**
     * Constructor por defecto.
     */
    public Caja()
    {
        this.precioACobrar         = 0;
        this.saldoActual           = 0;
        this.totalRecaudado        = 0;
        this.operacionesRealizadas = 0;
    }
    
    /**
     * Configura el precio a cobrar en la próxima operación.
     *    
     * @param precioACobrar
     */
    public void setPrecioACobrar(int precioACobrar) 
    {
        //TODO: Implementar metodo
        if(precioACobrar <= 0)
        {
            return;
        }
        this.precioACobrar = precioACobrar;
    }
    
    /**
     * Verifica si el saldo disponible es suficiente para realizar una operación
     *
     * @return  true si el saldo es suficiente, false en caso contrario.
     */
    public boolean saldoSuficiente()
    {
        //TODO: Implementar metodo
        if(this.getSaldoActual() >= this.getPrecioACobrar())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Completa una operación de venta, actualizando las estadisticas de la caja, como el total recaudado,
     * operaciones realizadas, saldo disponible, etc.
     * 
     * @throws IllegalStateException    Cuando el saldo es insuficiente para realizar la venta.
     */
    public void completarVenta() throws IllegalStateException
    {
        //TODO: Implementar metodo
        if(this.saldoSuficiente())
        {
            this.totalRecaudado += precioACobrar;   // Actualizo el total recaudado.
            this.operacionesRealizadas++;           // Añado una operación.
            this.saldoActual -= precioACobrar;      // Le resto al saldo el precio de un cafe.
        }
        else
        {
            throw new IllegalStateException();
        }
    }
    
    /**
     * Agrega dinero para realizar una operación, actualizando el saldo disponible. Solo es posible agregar montos
     * correspondientes a BILLETES_ACEPTADOS.
     *  
     * @param  monto                        El monto a agregar.
     * @throws IllegalArgumentException     Cuando el monto no corresponde con un BILLETE_ACEPTADOS.
     */
    public void agregarSaldo(int monto)
    {
        //TODO: Implementar metodo
        for(int i = 0; i < 4; i++)
        {
            if(BILLETES_ACEPTADOS[i] == monto)
            {
                this.saldoActual += monto;
                return;
            }
        }
        throw new IllegalArgumentException();
    }
    
    /**
     * Retorna el precio a cobrar configurado.
     *
     * @return  El precio.
     */
    public int getPrecioACobrar()
    {
        return precioACobrar;
    }
    
    /**
     * Retorna el saldo disponible para realizar una compra.
     *
     * @return  El saldo.
     */
    public int getSaldoActual()
    {
        return saldoActual;
    }
    
    /**
     * Retorna el total recaudado en la caja.
     *
     * @return  El total recaudado.
     */
    public int getTotalRecaudado()
    {
        return totalRecaudado;
    }
    
    /**
     * Retorna la cantidad de operaciones que se realizaron en la caja.
     *
     * @return  Cantidad de operaciones realizadas.
     */
    public int getTotalOperacionesRealizadas()
    {
        return operacionesRealizadas; 
    }

    /**
     * Operación que se ejecuta cuando se retira el dinero cargado, resetando el saldo actual.
     */
    public void devolverSaldo()
    {
        saldoActual = 0;
    }
}