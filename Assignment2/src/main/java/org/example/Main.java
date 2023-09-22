package org.example;

import edu.whu.MyClass;
import edu.whu.initMethod;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String bootstrapClassName = GetBootstrapClassName();
        MyClass myClass = (MyClass) InstantiateBootstrapClass(bootstrapClassName);
    }

    public static String GetBootstrapClassName() {
        Properties props = new Properties();

        try (InputStream input = Properties.class.getResourceAsStream("/myapp.properties")) {
            if (input == null)
                throw new IOException("file not found");
            props.load(input);
            return props.getProperty("bootstrapClass");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static Object InstantiateBootstrapClass(String bootstrapClassName) {

        try {

            Class<?> userClass = Class.forName(bootstrapClassName);
            Object user = userClass.newInstance();
            for (Method method : userClass.getMethods()) {
                if (method.isAnnotationPresent(initMethod.class)) {
                    method.invoke(user);
                }
            }
            return user;
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}