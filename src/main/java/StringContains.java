import java.util.Scanner;

public class StringContains {
    public static boolean str_contains(String search_string, String str) {
        for (int i = 0; i < str.length() - search_string.length() + 1; i++) {
            boolean result = true;
            for (int j = 0; j < search_string.length(); j++) {
                if (str.charAt(i + j) != search_string.charAt(j)) {
                    result = false;
                    break;
                }
            }
            if(result)
            {return true;}
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "Hello! I am a search example string.";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string to be searched in the above string: ");
        String search_string = sc.nextLine();
        if (search_string.length() == 0) {
            System.out.println("Search string is empty");
        } else {
            if (search_string.length() > str.length()) {
                System.out.println("Search string not present in the given string");
            } else {
                System.out.println(str_contains(search_string,str));
            }
        }
    }

}
