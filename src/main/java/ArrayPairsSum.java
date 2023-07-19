import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayPairsSum {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the limit of array: ");
        int limit = sc.nextInt();
        int[] arr = new int[limit];
        List<int[]> pairs = new ArrayList<>();
        //List<Integer> all_pairs = new ArrayList<>();
        System.out.println("Enter: "+limit+" numbers in the array: ");
        for(int i=0;i<limit;i++){
            arr[i]=sc.nextInt();
        }
        System.out.println("Enter the sum of the pairs: ");
        int sum = sc.nextInt();
        int cnt = 0;
        for(int i=0;i<arr.length;i++)
        {
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]+arr[j]==sum){
                    pairs.add(new int[]{arr[i], arr[j]});
                    cnt++;
                }
            }
        }
        if(cnt == 0){
            System.out.println("No pairs with sum as "+sum+" found");
        }
        else{
            System.out.println(cnt+" pairs found in the given array");
            for(int[] pair:pairs){
                System.out.print("("+pair[0]+","+pair[1]+")");
                System.out.println();
            }
        }
    }
}
