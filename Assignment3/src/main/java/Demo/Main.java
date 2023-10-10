package Demo;

import MyClass.ClassA;
import MyClass.ClassB;
import MyClass.ClassC;
import MyContext.MyApplicationContext;
import MyContext.MyException;

public class Main {
    public static void main(String[] args) {
        try {
            MyApplicationContext ctx = new MyApplicationContext("/applicationContext.xml");
            ClassA a = (ClassA) ctx.getBean("a");
            ClassB b = (ClassB) ctx.getBean("b");
            ClassC c = (ClassC) ctx.getBean("c");

            ctx.close();

        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }
}
