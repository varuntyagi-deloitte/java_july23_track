import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class KeysRageOutput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dictionary<Integer, String> pairs = new Hashtable<>();
        Dictionary<Integer, String> new_pairs = new Hashtable<>();
        System.out.println("Enter the number of pairs u want in the dictionary: ");
        int limit = sc.nextInt();
        System.out.println("Enter first key and then value for the corresponding key: ");
        for (int i = 0; i < limit; i++) {
            System.out.println("Key: ");
            int key = sc.nextInt();
            System.out.println("Value: ");
            String value = sc.next();
            pairs.put(key, value);
        }
        System.out.println("Enter the starting and ending key range u want: ");
        System.out.println("Start: ");
        int start = sc.nextInt();
        System.out.println("End: ");
        int end = sc.nextInt();
        System.out.println("Input: " + pairs);
        for (int i = start; i <= end; i++) {
            if (pairs.get(i) != null) {
                new_pairs.put(i, pairs.get(i));
            }
        }
        System.out.println("Output: " + new_pairs);
    }
}
