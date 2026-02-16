public class Arroyo extends Elemento
{
    public Arroyo()
    {
        super.setNombre("Arroyo de agua fresca");
        super.setPeso(1000);
        super.setTipo(TipoElemento.FIJO);
    }
    
    public Elemento getElemento()
    {
        return new Agua();
    }
}