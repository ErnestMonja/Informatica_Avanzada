public class Pocima extends Elemento
{
    public Pocima(String nombre, Integer peso)
    {
        super.setNombre("Pocima de " + nombre);
        super.setPeso(peso);
        super.setTipo(TipoElemento.LIQUIDO);
    }
}