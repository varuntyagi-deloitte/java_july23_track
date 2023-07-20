import java.util.Scanner;

class CustomException extends Exception{
    public CustomException(String message){
        super(message);
    }
}

public class AgeCustomException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter age: ");
        try{
        int age = sc.nextInt();
        if (age < 18) {
            throw new CustomException("Candidate is not eligible to cast a vote");
        } else {
            System.out.println("Candidate whose age is " + age + " is eligible to vote");
        }}
        catch(CustomException e){
            System.out.println("Exception raised:"+e.getMessage());
        }
    }
}
