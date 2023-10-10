import MyClass.ClassB;
import MyContext.MyApplicationContext;
import MyContext.MyException;
import org.junit.Test;

import static org.junit.Assert.assertThrows;


public class TestSet {
    @Test
    public void dependencyInjectionTest() {
        try {
            MyApplicationContext ctx = new MyApplicationContext("/tests/test0.xml");
            ctx.getBean("a");
            TestClassB tcb = (TestClassB) ctx.getBean("b");
            assert (TestClassB.counter == 2);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void fileNotFoundTest() {
        try {
            MyApplicationContext ctx = new MyApplicationContext("");
            assert (false);
        } catch (MyException e) {
            assert (e.getErrorCode() == MyException.ErrorCode.DOCUMENT_ERROR);
        }
    }

    @Test
    public void initMethodTest() {
        try {
            MyApplicationContext ctx = new MyApplicationContext("/tests/test1.xml");
            TestClassB tcb = (TestClassB) ctx.getBean("b");
            assert (TestClassB.counter == 2);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
