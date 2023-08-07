package Main_Assignment;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import static Main_Assignment.RowFormula.*;
import static utilities.Log4jApplication.Log;
import static utilities.PasswordValidator.isPasswordValid;

public class AdminModule {

    private static Scanner input = new Scanner(System.in);
    private static Properties properties = new Properties();
    private static String PROP_LOC = "src/main/resources/Percentage.properties";

    // Admin Login Session method
    public static void adminLogin() {
        Log.info("******** Admin Login ********");
        Log.info("Username: ");
        String username = input.nextLine();
        Log.info("Password: ");
        String password = input.nextLine();

        //Verifies Admin Credentials
        if (username.equals("Admin") && password.equals("Admin@123")) {
            int choice = 1;
            while (choice != 3) {

                // Menu-driven for the Admin
                Log.info("1. Add Employee Details.");
                Log.info("2. Update record");
                Log.info("3. Return to Login Window.");
                Log.info("Enter your choice for admin:");
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        addEmployeeDetails();
                        break;
                    case 2:
                        updateRecord();
                        break;
                    case 3:
                        break;
                    default:
                        Log.info("Enter a valid option.");
                }
            }
        } else if ((!username.equals("Admin") && !password.equals("Admin@123"))) {
            Log.info("Username or password is invalid");
        } else if (!username.equals("Admin")) {
            Log.info("Username is Wrong");
        } else {
            Log.info("Password is wrong");
        }
    }

    // Updating Employee Record from Admin Side
    private static void updateRecord() {
        int updateEmpOption = 1;

        while (updateEmpOption != 3) {
            Log.info("***** Update Options *****");
            Log.info("1. Update CTC");
            Log.info("2. Update Basic");
            Log.info("3. Go back to previous menu");
            Log.info("Choose any one option from above: ");
            updateEmpOption = input.nextInt();
            Log.info("Enter the Employee ID: ");
            String empId = input.next();
            switch (updateEmpOption) {

                // Case 1 for updating the Employee Monthly Salary
                case 1:
                    try (InputStream op = new FileInputStream(PROP_LOC)){
                        properties.load(op);
                        if (dataReadValidation(empId, 0)) {
                            Log.info("Enter the new CTC for the Employee: ");
                            int basicPercentage = Integer.parseInt(properties.getProperty("basic").substring(0,properties.getProperty("basic").length()-1));
                            int hraPercentage= Integer.parseInt(properties.getProperty("hra").substring(0,properties.getProperty("hra").length()-1));
                            int ltaPercentage= Integer.parseInt(properties.getProperty("lta").substring(0,properties.getProperty("lta").length()-1));
                            double specialAllowance;
                            double newCtc = input.nextInt();
                            cellUpdate(empId, newCtc, 3);
                            double newBasic = (newCtc * basicPercentage) / 100;
                            cellUpdate(empId, newBasic, 4);
                            double newHRA = (newBasic * hraPercentage) / 100;
                            cellUpdate(empId, newHRA, 5);
                            double newLTA = (newCtc * ltaPercentage) / 100;
                            cellUpdate(empId, newLTA, 6);
                            double pf = Double.parseDouble(dataRead(empId,8));
                            double sodexo = Double.parseDouble(dataRead(empId,7));
                            specialAllowance = newCtc-(newHRA+newLTA+pf+sodexo+newBasic);
                            cellUpdate(empId, specialAllowance, 10);
                            Log.info("CTC BreakUp Updated.");
                        } else {
                            Log.info("Np such employee found");
                            Log.error("Employee Id doesn't exists");
                        }
                    }
                    catch(Exception e){
                        Log.info("Some Internal error occurred");
                    }
                    break;

                // Case 2 for updating the Employee Basic Percentage
                case 2:
                    Log.info("Enter the new Basic Percentage (should be less than or equal to 100)");
                    String newBasic = input.next();
                    try (InputStream op = new FileInputStream(PROP_LOC)) {
                        properties.load(op);
                        properties.setProperty("basic", newBasic);
                        try (OutputStream outputStream = new FileOutputStream(PROP_LOC)) {
                            if (newBasic.endsWith("%")) {
                                properties.store(outputStream, "Updated Configuration");
                                basicUpdate(properties.getProperty("basic"));
                                Log.info("Basic got updated");
                                hraUpdate(properties.getProperty("hra"));
                                Log.info("HRA got updated");
                                double basic = Double.parseDouble(dataRead(empId,4));
                                double Ctc = Double.parseDouble(dataRead(empId,3));
                                double hra = Double.parseDouble(dataRead(empId,5));
                                double lta = Double.parseDouble(dataRead(empId,6));
                                double pf = Double.parseDouble(dataRead(empId,8));
                                double sodexo = Double.parseDouble(dataRead(empId,7));
                                double specialAllowance = Ctc-(hra+lta+pf+sodexo+basic);
                                cellUpdate(empId, specialAllowance, 10);
                                Log.info("Special Allowance got updated");
                            } else {
                                Log.info("Value entered for Basic is invalid. Example FORMAT: XX%");
                            }
                        }
                    } catch (IOException e) {
                        Log.error("Error Occurred while updating the Basic.");
                    }
                    break;
                case 3:
                    break;
                default:
                    Log.info("Please enter a valid option.");
            }
        }
    }

    // Method for adding employee records
    private static void addEmployeeDetails() {
        Log.info("Employee Count: ");
        int empCount = input.nextInt();
        for (int i = 0; i < empCount; i++) {
            Log.info("****** Employee Details ******");
            Log.info("Enter Employee ID: ");
            String empId = input.next();
            if (dataReadValidation(empId, 0)) {
                Log.info("The specified Employee Id already Exists");
                Log.error("Error while adding Emp Id");
                break;
            }
            Log.info("Enter Employee Name");
            String empName = input.next();
            Log.info("Enter Employee Salary: ");
            double empTotalCtc = input.nextInt();
            Log.info("Enter Employee Password");
            String password = input.next();
            if(!isPasswordValid(password))
            {
                Log.info("Invalid password, should have atleast 1 lowercase,uppercase,digit,special character and the min. length should be btw(5,10)!");
            }
            else {
                //This method will automatically calculate the other fields of the Employee salary structure
                addEmployeeData(empId, password, empName, empTotalCtc, 2200, 1800);
                Log.info("Employee Data has been Added");
                Log.info("---------------------------");
            }
        }
    }

}
