import java.util.Scanner;

public class PrefixSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of strings u wanna enter: ");
        int limit = sc.nextInt();
        String[] arr = new String[limit];
        System.out.println("Enter " + limit + " strings:");
        for (int i = 0; i < limit; i++) {
            arr[i] = sc.next();
        }
        String prefix = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int j = 0;
            while (j < prefix.length() && j < arr[i].length() && prefix.charAt(j) == arr[i].charAt(j)) {
                j++;
            }
            prefix = prefix.substring(0, j);
            if (prefix.isEmpty()) {
                prefix = "";
                break;
            }
        }
        System.out.println("The longest prefix is: " + prefix);
    }
}
