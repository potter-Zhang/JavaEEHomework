package MyClass;

public class ClassB {
    ClassC classC;
    public ClassB() {
        System.out.println("Class B");
    }

    public void init() {
        System.out.println("Class B init");
    }

    public void setClassC(ClassC classC) {
        this.classC = classC;
    }

}
