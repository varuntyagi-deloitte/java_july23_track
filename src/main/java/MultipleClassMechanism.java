class BaseClass {
    public void method(){
        System.out.println("It's a method of BaseClass");
    }
}

class Z extends BaseClass {
    public void method(){
        System.out.println("It's a method of class Z");
    }
}

class X extends BaseClass {
    public void method(){
        System.out.println("It's a method of class X");
    }
}

class Y extends BaseClass {
    public void method(){
        System.out.println("It's a method of class Y");
    }
}

public class MultipleClassMechanism {
    public static void main(String[] args) {
        X obj = new X();
        Y obj1 = new Y();
        Z obj2 = new Z();
        obj.method();
        obj1.method();
        obj2.method();
    }
}
