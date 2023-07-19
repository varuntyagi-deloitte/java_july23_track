import java.util.Scanner;

public class ConsecutiveElementsInArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the limit of array: ");
        int limit = sc.nextInt();
        int[] arr = new int[limit];
        System.out.println("Enter: " + limit + " numbers in the array: ");
        for (int i = 0; i < limit; i++) {
            arr[i] = sc.nextInt();
        }
        int temp;
        for (int i = 0; i < limit; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        int flag = 0;
        for (int j = 0; j < arr.length-1; j++) {
            if (arr[j + 1] - arr[j] != 1) {
                flag = 1;
                break;
            }
        }
        if(flag==0){
            System.out.println("The elements in array are consecutive");
        }
        else{
            System.out.println("The elements in array are not consecutive");
        }
    }
}
