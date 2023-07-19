import java.util.Scanner;

public class SwapFirstAndLastCharacter {
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter First String: ");
        String a = sc.nextLine();
        char[] chars = a.toCharArray();
        char first = chars[0];
        char last = chars[chars.length - 1];

        chars[0] = last;
        chars[chars.length - 1] = first;
        System.out.println("The reversed string is: "+new String(chars));
    }
}
