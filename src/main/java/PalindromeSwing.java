import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class PalindromeSwing extends JFrame implements ActionListener {

    private JTextField inputNumber;
    private JTextField result;
    public PalindromeSwing(String name){
        super("Palindrome Checker");

        inputNumber = new JTextField(10);
        inputNumber.addActionListener(this);

        result = new JTextField(10);
        result.addActionListener(this);

        JLabel inputLabel = new JLabel("Hi "+name+", Enter the number:");
        inputLabel.setLabelFor(inputNumber);

        JLabel outputLabel = new JLabel("Hi "+name+", The result is:");
        outputLabel.setLabelFor(result);

        JButton Check = new JButton("Check: ");
        Check.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(inputLabel);
        panel.add(inputNumber);
        panel.add(outputLabel);
        panel.add(result);
        panel.add(Check);

        getContentPane().add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 100);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            int number = Integer.parseInt(inputNumber.getText());
            if (number>0) {
                int copy_number = number,rem,new_number = 0;
                while(copy_number>0){
                    rem = copy_number%10;
                    new_number = (new_number*10)+rem;
                    copy_number = copy_number/10;
                }
                if(number==new_number){
                    result.setText("Palindrome");
                }
                else{
                    result.setText("Not Palindrome");
                }
            } else {
                throw new TemperatureException("Please enter valid number");
            }
        } catch (Exception ce) {
            result.setText("");
            System.out.println(ce.getMessage());
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.next();
        new PalindromeSwing(name);
    }
}
