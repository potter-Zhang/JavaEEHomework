package MyContext;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MyApplicationContext {
    class BeanDefinition {
        public BeanDefinition(Class<?> beanClass, String initMethod, String destroyMethod,
                              String scope, String method, String ref) {
            this.beanClass = beanClass;
            this.setMethod = method;
            this.ref = ref;
            this.initMethod = initMethod;
            this.destroyMethod = destroyMethod;
            this.scope = scope;
        }
        public Class<?> beanClass;
        public String initMethod;
        public String destroyMethod;
        public String scope;
        public String setMethod;
        public String ref;
    }
    String xmlPath;
    HashMap<String, Object> beans;
    HashMap<String, BeanDefinition> beanDefinitions;

    public MyApplicationContext(String path) throws MyException {
        this.xmlPath = path;
        beans = new HashMap<String, Object>();
        beanDefinitions = new HashMap<String, BeanDefinition>();
        loadBeansInfoFromFile();
    }

    private void addBeanDefinition(Element bean) throws MyException {
        try {
            String id = bean.attributeValue("id");
            Class<?> beanClass = Class.forName(bean.attributeValue("class"));
            String initMethod = bean.attributeValue("init-method");
            String destroyMethod = bean.attributeValue("destroy-method");
            String scope = bean.attributeValue("scope");
            if (scope == null)
                scope = "singleton";
            if (!scope.equals("prototype") && !scope.equals("singleton")) {
                throw new MyException(MyException.ErrorCode.SCOPE_ERROR, "invalid scope");
            }
            Element prop = (Element) bean.selectSingleNode("property");
            if (id == null) {
                throw new MyException(MyException.ErrorCode.INVALID_ID, "id attribute is not found");
            }
            if (beanDefinitions.containsKey(id)) {
                throw new MyException(MyException.ErrorCode.INVALID_ID, "duplicated id");
            }
            if (prop == null) {
                beanDefinitions.put(id, new BeanDefinition(beanClass, initMethod, destroyMethod, scope, null, null));
                return;
            }
            String name = prop.attributeValue("name");
            String ref = prop.attributeValue("ref");
            if (ref == null || name == null) {
                throw new MyException(MyException.ErrorCode.PROPERTY_FORMAT_ERROR, "property missing ref or name");
            }
            beanDefinitions.put(id, new BeanDefinition(beanClass, initMethod, destroyMethod, scope, "set" + name, ref));
        } catch (ClassNotFoundException e) {
            throw new MyException(MyException.ErrorCode.CLASS_NOT_FOUND, "class not found");
        }

    }


    private void loadBeansInfoFromFile() throws MyException {
        try (InputStream input = MyApplicationContext.class.getResourceAsStream(xmlPath)) {
            if (input == null) {
                throw new MyException(MyException.ErrorCode.INPUT_STREAM_NULL, "input stream error.");
            }

            SAXReader reader = new SAXReader();
            Document xmlFile = reader.read(input);

            List<Element> beansInFile = xmlFile.selectNodes("/beans/bean");

            for (Element bean : beansInFile) {
                addBeanDefinition(bean);
            }

        } catch (IOException e) {
            throw new MyException(MyException.ErrorCode.FILE_NOT_FOUND, "file not found.");
        } catch (DocumentException e) {
            throw new MyException(MyException.ErrorCode.DOCUMENT_ERROR, "document is not a xml file.");
        }
    }


    public Object getBean(String id) throws MyException {
       try {
            if (beanDefinitions.isEmpty()) {
                loadBeansInfoFromFile();
            }

            if (beans.containsKey(id)) {
                return beans.get(id);
            }
            if (!beanDefinitions.containsKey(id)) {
                throw new MyException(MyException.ErrorCode.INVALID_ID, "id not found");
            }
            BeanDefinition bean = beanDefinitions.get(id);
           Object beanObj = bean.beanClass.newInstance();
           // init callback
           if (bean.initMethod != null) {
               Method initMethod = bean.beanClass.getMethod(bean.initMethod);
               initMethod.invoke(beanObj);
           }
            if (bean.ref == null) {
                if (bean.scope.equals("singleton"))
                    beans.put(id, beanObj);
                return beanObj;
            }
            if (!beanDefinitions.containsKey(bean.ref)) {
                throw new MyException(MyException.ErrorCode.INVALID_ID, "ref id not found");
            }
            Object refBeanobj = getBean(bean.ref);

            Method setMethod = bean.beanClass.getMethod(bean.setMethod, beanDefinitions.get(bean.ref).beanClass);
            setMethod.invoke(beanObj, refBeanobj);
            if (bean.scope.equals("singleton"))
                beans.put(id, beanObj);
            return beanObj;

        } catch (InstantiationException e) {
            throw new MyException(MyException.ErrorCode.INSTANTIATE_ERROR, "can not instantiate the class");
        } catch (IllegalAccessException | NoSuchMethodException e) {
            throw new MyException(MyException.ErrorCode.METHOD_NOT_ACCESSIBLE, "method not accessible");
        } catch (InvocationTargetException e) {
           throw new MyException(MyException.ErrorCode.METHOD_NOT_ACCESSIBLE, "method can not be invoked");
       }
    }

    public void close() throws MyException {
        try {
            for (Map.Entry<String, Object> bean : beans.entrySet()) {
                // destroy callback
                String key = bean.getKey();
                BeanDefinition beanDef = beanDefinitions.get(key);
                if (beanDef.destroyMethod != null) {
                    Method destroyMethod = beanDef.beanClass.getMethod(beanDef.destroyMethod);
                    destroyMethod.invoke(bean.getValue());
                }
            }
            beans.clear();
            beanDefinitions.clear();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new MyException(MyException.ErrorCode.METHOD_NOT_ACCESSIBLE, "destroy method not found");
        }
    }
}
