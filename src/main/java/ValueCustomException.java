import java.util.Scanner;

class CustomValueException extends Exception{
    public CustomValueException(String message){
        super(message);
    }
}
public class ValueCustomException {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Guess the price of Tesla in rupees: ");
        int price = sc.nextInt();
        System.out.println("Guess tax in america: ");
        int tax1 = sc.nextInt();
        System.out.println("Guess tax in india: ");
        int tax2 = sc.nextInt();
        int output = 0;
        output = price+((price*tax1)/100);
        output = output + ((output*tax2)/100);
        try{
            if(output < 2*price){
                System.out.println("Output: "+output);
            }
            else{
                throw new CustomValueException("The actual price is more than double your guess");
            }
        }
        catch(Exception e){
            System.out.println("Exception raised: "+e.getMessage());
        }
    }
}
