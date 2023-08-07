package Main_Assignment;

import java.util.Properties;
import java.util.Scanner;
import static utilities.Log4jApplication.Log;
import static Main_Assignment.RowFormula.*;

public class EmployeeModule {
    private static Scanner input = new Scanner(System.in);
    private static Properties properties = new Properties();
    private static String PROP_LOC = "src/main/resources/Percentage.properties";

    // Employee Login Module for begins from here
    public static void employeeLogin() {
        Log.info("******** Employee Login ********");
        Log.info("Employee ID: ");
        String empId = input.nextLine();
        Log.info("Password: ");
        String empPass = input.nextLine();

        // After Employee Login Menu
        if (dataReadValidation(empId, 0) && dataRead(empId, 1).equals(empPass)) {
            int employeeChoice = 1;

            while (employeeChoice != 4) {
                Log.info("1. My Current CTC");
                Log.info("2. Modify Details (Sodexo & VPF)");
                Log.info("3. Calculate Salary");
                Log.info("4. Exit");
                Log.info("Enter your Choice: ");
                employeeChoice = input.nextInt();
                switch (employeeChoice) {

                    // Case 1 displays the whole details of the Employee
                    case 1 :
                        Log.info("******** Current CTC ********");
                        Log.info("Employee ID: " + empId);
                        Log.info("Name: " + dataRead(empId, 2));
                        Log.info("Salary: " + dataRead(empId, 3));
                        Log.info("Basic: " + dataRead(empId, 4));
                        Log.info("HRA: " + dataRead(empId, 5));
                        Log.info("LTA: " + dataRead(empId, 6));
                        Log.info("Sodexo: " + dataRead(empId, 7));
                        Log.info("PF: " + dataRead(empId, 8));
                        Log.info("Special Allowance: " + dataRead(empId, 10));
                        Log.info("VPF: " + dataRead(empId, 9));
                        break;

                    // Case 2 is for Modifying the Sodexo or VPF
                    case 2 :
                        modifyDetails(empId);
                        break;

                    // Case 3 for various Scenario based on which it will show the employee salary
                    case 3 :
                        scenarioOption(empId);
                        break;
                    // Case 4 to return to the previous menu
                    case 4 :
                        break;
                    default :
                        Log.info("Choice a valid option");
                }
            }

        }
        else {
            Log.info("username or password is wrong");
        }
    }

    // Method for the Scenario option
    private static void scenarioOption(String empId) {
        int scenarioChoice = 1;
        float vpf;
        double sodexo;
        while (scenarioChoice!=5) {
            Log.info("Calculated Salary for various Scenario: ");
            Log.info("1: 0 VPF, 0 Sodexo");
            Log.info("2: 5% VPF, 0 Sodexo");
            Log.info("3: 5% VPF, 2200 Sodexo");
            Log.info("4: 0 VPF, 2200 Sodexo");
            Log.info("5. Go back to previous Menu");
            Log.info("Choose a specific scenario: ");
            scenarioChoice = input.nextInt();
            switch (scenarioChoice) {

                // Case 1 for the Scenario 1 where VPF = 0 and Sodexo = 0
                case 1:
                    vpf = Float.parseFloat(dataRead(empId, 9));
                    Log.info("vpf: "+vpf);
                    sodexo = Double.parseDouble(dataRead(empId, 7));
                    Log.info("Sodexo: "+sodexo);
                    if (vpf == 0 && sodexo == 0) {
                        double newSalary = Double.parseDouble(dataRead(empId, 4)) + Double.parseDouble(dataRead(empId, 5)) + Double.parseDouble(dataRead(empId, 6)) + Double.parseDouble(dataRead(empId, 10));
                        Log.info("Your Salary: " + newSalary);
                    } else {
                        //double specialAllowance = Double.parseDouble(dataRead(empId, 10)) - vpf - sodexo;
                        double specialAllowance = Double.parseDouble(dataRead(empId, 10));
                        Log.info("Special Allowance: "+specialAllowance);
                        double newSalary = Double.parseDouble(dataRead(empId, 4)) + Double.parseDouble(dataRead(empId, 5)) + Double.parseDouble(dataRead(empId, 6)) + specialAllowance;
                        Log.info("Your Salary: " + newSalary);
                    }
                    break;
                // Case 2 for the Scenario 2 where VPF = Employee choice and Sodexo = 0
                case 2:
                    vpf = Float.parseFloat(dataRead(empId, 9));
                    Log.info("Current VPF VALUE:" + vpf);
                    sodexo = Double.parseDouble(dataRead(empId, 7));
                    Log.info("Current Sodexo VALUE:" + sodexo);
                    if (vpf > 0 && sodexo == 0) {
                        double newSalary = Double.parseDouble(dataRead(empId, 4)) + Double.parseDouble(dataRead(empId, 5)) + Double.parseDouble(dataRead(empId, 6)) + Double.parseDouble(dataRead(empId, 10));
                        Log.info("Your Salary: " + newSalary);
                    } else {
                        Log.info("Please enter a desired percentage for the vpf(do not include % symbol at the end): ");
                        float newVpfPercentage = input.nextInt();
                        double newVpf = Double.parseDouble(dataRead(empId, 4)) * (newVpfPercentage / 100);
                        double specialAllowance = Double.parseDouble(dataRead(empId, 10)) + newVpf - sodexo;
                        Log.info("Special Allowance:" + specialAllowance);
                        double newSalary = Double.parseDouble(dataRead(empId, 4)) + Double.parseDouble(dataRead(empId, 5)) + Double.parseDouble(dataRead(empId, 6)) + specialAllowance;
                        Log.info("Your Salary: " + newSalary);
                    }
                    break;
                // Case 3 for the Scenario 3 where VPF = Employee Choice and Sodexo = 2200
                case 3:
                    vpf = Float.parseFloat(dataRead(empId, 9));
                    Log.info("vpf: "+vpf);
                    sodexo = Double.parseDouble(dataRead(empId, 7));
                    Log.info("Sodexo: "+sodexo);
                    if (vpf > 0 && sodexo > 0) {
                        double newSalary = Double.parseDouble(dataRead(empId, 4)) + Double.parseDouble(dataRead(empId, 5)) + Double.parseDouble(dataRead(empId, 6)) + Double.parseDouble(dataRead(empId, 10));
                        Log.info("Your Salary: " + newSalary);
                    } else {
                        Log.info("Please enter a desired percentage for the vpf(do not include % symbol at the end): ");
                        float newVpfPercentage = input.nextInt();
                        double newVpf = Double.parseDouble(dataRead(empId, 4)) * (newVpfPercentage / 100);
                        double specialAllowance = Double.parseDouble(dataRead(empId, 10)) + newVpf + 2200;
                        Log.info("Special Allowance: "+specialAllowance);
                        double newSalary = Double.parseDouble(dataRead(empId, 4)) + Double.parseDouble(dataRead(empId, 5)) + Double.parseDouble(dataRead(empId, 6)) + specialAllowance;
                        Log.info("Your Salary: " + newSalary);
                    }
                    break;

                // Case 4 for the Scenario 4 where VPF = 0 and Sodexo = 2200
                case 4:
                    vpf = Float.parseFloat(dataRead(empId, 9));
                    Log.info("vpf: "+vpf);
                    sodexo = Double.parseDouble(dataRead(empId, 7));
                    Log.info("Sodexo: "+sodexo);
                    if (vpf == 0 && sodexo > 0) {
                        double newSalary = Double.parseDouble(dataRead(empId, 4)) + Double.parseDouble(dataRead(empId, 5)) + Double.parseDouble(dataRead(empId, 6)) + Double.parseDouble(dataRead(empId, 10));
                        Log.info("Your Salary: " + newSalary);
                    } else {
                        double newVpf = Double.parseDouble(dataRead(empId, 4)) * 0;
                        double specialAllowance = Double.parseDouble(dataRead(empId, 10)) + newVpf + 2200;
                        Log.info("Special Allowance: "+specialAllowance);
                        double newSalary = Double.parseDouble(dataRead(empId, 4)) + Double.parseDouble(dataRead(empId, 5)) + Double.parseDouble(dataRead(empId, 6)) + specialAllowance;
                        Log.info("Your Salary: " + newSalary);
                    }
                    break;
                // Case 5 for Previous Menu
                case 5:
                    break;
                default:
                    Log.info("Please Enter a valid option");
            }
        }
    }

    // Method for Modifying the Sodexo  and VPF
    private static void modifyDetails(String empId) {
        int modifyChoice = 1;
        while (modifyChoice != 3) {
            Log.info("**** Modify Details ****");
            Log.info("1. Modify Sodexo.");
            Log.info("2. Modify VPF");
            Log.info("3. Return to previous menu");
            Log.info("Enter the choice regarding modification details:");
            modifyChoice = input.nextInt();
            switch (modifyChoice) {

                // Case 1 for Modifying the Sodexo
                case 1 :
                    double checkSodexo = Double.parseDouble(dataRead(empId, 7));
                    if (checkSodexo == 2200) {
                        Log.info("Do you want to Opt out for Sodexo? (Y/N)");
                        String sodexoAns = input.next();
                        if (sodexoAns.equals("Y") || sodexoAns.equals("y")) {
                            cellUpdate(empId, 0, 7);
                            double specialAllowance = Double.parseDouble(dataRead(empId,10));
                            cellUpdate(empId, (specialAllowance+2200), 10);
                            Log.info("Sodexo opted Out. Same updated in the employee sheet.!");
                        } else if (sodexoAns.equals("N") || sodexoAns.equals("n")) {
                            Log.info("No changes were made in your salary.");
                        } else {
                            Log.info("Please enter a valid answer i.e., it should be in Y or N");
                        }
                    } else if (checkSodexo == 0) {
                        Log.info("Do you want to Opt in for Sodexo? (Y/N)");
                        String sodexoAns = input.next();
                        if (sodexoAns.equals("Y") || sodexoAns.equals("y")) {
                            cellUpdate(empId, 2200, 7);
                            double specialAllowance = Double.parseDouble(dataRead(empId,10));
                            cellUpdate(empId, (specialAllowance-2200), 10);
                            Log.info("Sodexo opted In. Same updated in the employee sheet.!");
                        } else if (sodexoAns.equals("N") || sodexoAns.equals("n")) {
                            Log.info("No changes were made in your salary.");
                        } else {
                            Log.info("Please enter a valid answer i.e., it should be in Y or N");
                        }
                    }
                    break;

                // Case 2 for the VPF
                case 2 :
                    Log.info("Would you like to update your VPF? (Y/N)");
                    String vpfChoice = input.next();
                    if (vpfChoice.equals("Y") || vpfChoice.equals("y")) {
                        Log.info("How much percentage would like the VPF?(with % symbol)");
                        String vpfPer = input.next();
                        if(vpfPer.endsWith("%")) {
                            cellFormulaUpdate(empId, vpfPer, 9);
                            Log.info("VPF percentage updated successfully!");
//                            double Ctc = Double.parseDouble(dataRead(empId,3));
//                            double hra = Double.parseDouble(dataRead(empId,5));
//                            double lta = Double.parseDouble(dataRead(empId,6));
//                            double pf = Double.parseDouble(dataRead(empId,8));
//                            double sodexo = Double.parseDouble(dataRead(empId,7));
//                            double specialAllowance = Ctc-(hra+lta+pf+sodexo);
//                            cellUpdate(empId, specialAllowance, 10);
//                            Log.info("Special Allowance got updated");
                        }
                        else{
                            Log.info("Value entered for Vpf is invalid. Example FORMAT: XX%");
                        }
                    } else if (vpfChoice.equals("N") || vpfChoice.equals("n")) {
                        Log.info("No changes were made in your salary.");
                    } else {
                        Log.info("Please enter a valid answer i.e., it should be in Y or N");
                    }
                    break;

                // Case 3 for the previous Menu
                case 3 :
                    break;
                default :
                    Log.info("Enter a valid option!!!");
            }
        }
    }

}
