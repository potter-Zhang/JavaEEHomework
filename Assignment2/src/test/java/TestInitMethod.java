import edu.whu.MyClass;
import edu.whu.SimpleClass;
import edu.whu.initMethod;
import org.example.Main;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestInitMethod {
    @Test
    public void testNonExistClass() {
        assertThrows(RuntimeException.class, () -> {
            Main.InstantiateBootstrapClass("class not exists");
        });
    }

    @Test
    public void testInitMethod1() {
        MyClass myClass = (MyClass) Main.InstantiateBootstrapClass("edu.whu.MyClass");
        assertEquals(1, myClass.getFlag());
    }

    @Test
    public void testInitMethod2() {
        SimpleClass simpleClass = (SimpleClass) Main.InstantiateBootstrapClass("edu.whu.SimpleClass");
        assertEquals(3, simpleClass.getFlag());
    }






}
