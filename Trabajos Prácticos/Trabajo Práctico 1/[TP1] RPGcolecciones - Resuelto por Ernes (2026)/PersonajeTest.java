//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * La clase de Prueba PersonajeTest.
 *
 * @author  Maximiliano A. Eschoyez y resuelto por Ernes.
 * @version 1.0
 */
public class PersonajeTest extends junit.framework.TestCase
{ 
    /**
     * Constructor por defecto de la clase PersonajeTest.
     */
    public PersonajeTest()
    {
    }

    /**
     * Establece el fixture para la prueba.
     * Se invoca antes de la ejecución de cada método.
     */
    @BeforeEach
    protected void setUp()
    {
    }

    /**
     * Destruye el fixture para la prueba.
     * Se invoca después de la ejecución de cada método.
     */
    @AfterEach
    protected void tearDown()
    {
    }
    
    /**
     * Método de prueba propuesto por el TP el cual solo fue pasado de una clase
     * normal a una clase de prueba.
     */
    public void test()
    {
        Personaje mago = new Personaje("Gandalf", 100, 60);
        Bolsa bolsa = new Bolsa("Mochila", 50);
        
        mago.setBolsa(bolsa);
        mago.setElemento(new Elemento ("Espada", 10));
        mago.guardarElemento();
        System.out.println(mago.getNombre() + " " + mago.getElemento());
        
        mago.setElemento(new Elemento ("Hueso", 1));
        System.out.println(mago.getNombre() + " " + mago.getElemento());
        
        mago.guardarElemento();
        System.out.println(mago.getNombre() + " " + mago.getElemento());
        
        mago.tomarElemento("Espada");
        System.out.println(mago.getNombre() + " " + mago.getElemento());
        
        bolsa.addElemento(new Elemento ("Pluma", 1));
        bolsa.addElemento(new Elemento ("Sangre", 1));
        
        Caldero caldero = new Caldero("chico", 5);
        Receta receta = new Receta("voladora");
        receta.addIngrediente("Pluma");
        receta.addIngrediente("Sangre");
        receta.addIngrediente("Hueso");
        receta.cerrarReceta();
        System.out.println("Bolsa: " + bolsa.getElementosEnLaBolsa());
        System.out.println(caldero);
        mago.setCaldero(caldero);
        
        mago.prepararReceta(receta);
        System.out.println("Bolsa: " + bolsa.getElementosEnLaBolsa());
        System.out.println(caldero);
    }
}