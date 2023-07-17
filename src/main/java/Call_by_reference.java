import java.sql.Array;
import java.util.Arrays;

class B {
    public void methodB(int[] arr)
    {
        for(int i=0;i<arr.length;i++){
            arr[i]=arr[i]+2;
        }
    }
}
public class Call_by_reference {
    public static void main(String args[]){
        int[] arr= {10,11,12};
        B obj = new B();
        System.out.println("Before: "+ Arrays.toString(arr));
        obj.methodB(arr);
        System.out.println("After: "+Arrays.toString(arr));

    }
}
