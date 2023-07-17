interface test{
    public void square();
}
class arithmetic implements test{
    public void square(){
        System.out.println("It's the implementation of square method");
    }
}
public class ToTestInt
{
    public static void main(String[] args){
        arithmetic obj = new arithmetic();
        obj.square();
    }
}
