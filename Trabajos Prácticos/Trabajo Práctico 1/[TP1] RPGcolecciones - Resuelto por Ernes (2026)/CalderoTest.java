//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * La clase de Prueba CalderoTest.
 *
 * @author  Resuelto por Ernes.
 * @version 1.0
 */
public class CalderoTest extends junit.framework.TestCase
{
    private Caldero caldero1;
    private Receta receta1;
    private Elemento elemento1;
    private Elemento elemento2;
    private Elemento elemento3;
    
    /**
     * Constructor por defecto de la claseCalderoTest
     */
    public CalderoTest()
    {
    }

    /**
     * Establece el fixture para la prueba.
     * 
     * Se invoca antes de la ejecución de cada método.
     */
    @BeforeEach
    protected void setUp()
    {
        // Creo el caldero de pruebas.
        caldero1 = new Caldero("Caldero Genial", 10);
        
        // Creo una receta y 3 elementos para la misma.
        receta1 = new Receta("Receta voladora");
        elemento1 = new Elemento("Pluma", 1);
        elemento2 = new Elemento("Hueso", 2);
        elemento3 = new Elemento("Manzana", 3);
    }

    /**
     * Destruye el fixture para la prueba.
     * 
     * Se invoca después de la ejecución de cada método.
     */
    @AfterEach
    protected void tearDown()
    {
    }
    
    /**
     * Método de prueba para Caldero según Ernes.
     */
    public void testCaldero()
    {
        // Añado los 3 ingredientes a la receta y verifico que se pueda cerrar:
        receta1.addIngrediente(elemento1.getNombre());
        receta1.addIngrediente(elemento2.getNombre());
        receta1.addIngrediente(elemento3.getNombre());
        assertTrue(receta1.cerrarReceta());
        
        // Asocio la receta al caldero y verifico que falten 3 ingredientes:
        caldero1.setReceta(receta1);
        assertFalse(caldero1.verificarIngredientes());
        assertEquals(3, caldero1.getIngredientesFaltantes().size());
        
        // Agrego un elemento a un caldero y verifico que:
        //  - Disminuya el tamaño del array que devuelve .getIngredientesFaltantes()
        //  - Todavía falten ingredientes con .verificarIngredientes() 
        caldero1.addIngrediente(elemento1);
        assertEquals(2, caldero1.getIngredientesFaltantes().size());
        assertFalse(caldero1.verificarIngredientes());
        
        // Agrego el resto de ingredientes y preparo la pocima:
        caldero1.addIngrediente(elemento2);
        caldero1.addIngrediente(elemento3);
        caldero1.prepararPocima();
        
        // Ultimas verficaciones:
        assertNull(caldero1.getReceta());
        assertEquals(0, caldero1.getIngredientesFaltantes().size());
    }
}