class A{
    int result;
    public void methodA(int a){
       a=10;
    }
}
public class Call_by_value {
    public static void main(String s[])
    {
        int a=5;
        A obj = new A();
        System.out.println("Before: "+a);
        obj.methodA(a);
        System.out.println("After: "+a);

    }
}
