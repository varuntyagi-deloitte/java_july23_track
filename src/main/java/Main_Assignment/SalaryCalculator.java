package Main_Assignment;

import java.util.Properties;
import java.util.Scanner;

import static Main_Assignment.AdminModule.adminLogin;
import static Main_Assignment.EmployeeModule.employeeLogin;
import static utilities.Log4jApplication.Log;

public class SalaryCalculator {
    private static Scanner input = new Scanner(System.in);
    private static Properties properties = new Properties();
    private static String PROP_LOC = "src/main/resources/Percentage.properties";

    public static void main(String[] args) {
        Log.info("******** Welcome to ESS ********");
        while (true) {
            Log.info("1. Admin Login");
            Log.info("2. Employee Login");
            Log.info("3. Exit");
            Log.info("Enter your choice: ");
            int userChoice = input.nextInt();
            input.nextLine();

            switch (userChoice) {
                case 1:
                    Log.info("Admin Login");
                    adminLogin();
                    break;
                case 2:
                    Log.info("Employee Login");
                    employeeLogin();
                    break;
                case 3:
                    System.exit(0);
                default:
                    Log.info("Please enter a valid number");
            }
        }
    }

}
