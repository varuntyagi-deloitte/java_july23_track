import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


class TemperatureException extends Exception {
    public TemperatureException(String message) {
        super(message);
    }
}

public class TemperatureConverterSwing extends JFrame implements ActionListener {
    private JTextField fahrenheitField;
    private JTextField celsiusField;

    public TemperatureConverterSwing(String name) {
        super("Temperature Converter");

        fahrenheitField = new JTextField(10);
        fahrenheitField.addActionListener(this);

        celsiusField = new JTextField(10);
        celsiusField.addActionListener(this);

        JLabel fahrenheitLabel = new JLabel("Hi " + name + " Enter temperature in fahrenheit: ");
        fahrenheitLabel.setLabelFor(fahrenheitField);

        JLabel celsiusLabel = new JLabel("Celsius: ");
        celsiusLabel.setLabelFor(celsiusField);

        JButton convertButton = new JButton("Convert: ");
        convertButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(fahrenheitLabel);
        panel.add(fahrenheitField);
        panel.add(celsiusLabel);
        panel.add(celsiusField);
        panel.add(convertButton);

        getContentPane().add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (fahrenheitField.getText().isEmpty()) {
                throw new TemperatureException("Null value passed");
            } else {
                int fahrenheit = Integer.parseInt(fahrenheitField.getText());
                double celsius = (fahrenheit - 32) * (5.0 / 9.0);
                celsiusField.setText(String.format("%.1f", celsius));
            }
        } catch (Exception ce) {
            celsiusField.setText("");
            System.out.println(ce.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = sc.next();
        new TemperatureConverterSwing(name);
    }
}
