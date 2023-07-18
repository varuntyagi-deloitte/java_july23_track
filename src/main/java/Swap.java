public class Swap {
    public static void main(String[] args){
        String a = "Hi Java";
        String b ="Hello Python";
        System.out.println(a+" "+b);
        a=a+b;
        b=a.substring(0,a.length()-b.length());
        a=a.substring(b.length());
        System.out.println(a+" "+b);
    }
}
