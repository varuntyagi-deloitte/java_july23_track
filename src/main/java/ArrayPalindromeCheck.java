import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayPalindromeCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the limit: ");
        int limit = sc.nextInt();
        List<String> arr = new ArrayList<>();
        List<String> arr1 = new ArrayList<>();
        System.out.println("Enter " + limit + " inputs in the array: ");
        for (int i = 0; i < limit; i++) {
            String ele = sc.next();
            arr.add(ele);
        }
        for (int i = 0; i < arr.size(); i++) {
            String reverse = "";
            for (int j = arr.get(i).length() - 1; j >= 0; j--) {
                reverse = reverse + arr.get(i).charAt(j);
            }
            arr1.add(reverse.trim());
        }
        int flag = 0;
        List<String> arr3 = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).length(); j++) {
                if (arr.get(i).charAt(j) != arr1.get(i).charAt(j)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                arr3.add(arr.get(i));
            }
        }
        System.out.println("Input: " + arr);
        System.out.println("Output: " + arr3);
    }
}
