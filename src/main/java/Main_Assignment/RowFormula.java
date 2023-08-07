package Main_Assignment;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class RowFormula {
    private static Properties properties = new Properties();

    private static String excelFilePath = "src/main/java/Main_Assignment/EmployeeProfile.xlsx";
    private static String sheetName = "Sheet1";

    public static void addEmployeeData(String eid, String password, String empName, double totalCtc, double sodexo, double pf) {
        try (FileInputStream fos = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fos)) {
            FileInputStream fis = new FileInputStream("src/main/resources/Percentage.properties");

            properties.load(fis);

            Sheet sheet = workbook.getSheet(sheetName);

            // getLastRowNum will return the last row index.
            int lastRowIndex = sheet.getLastRowNum();
            int lastIndex = lastRowIndex + 2;

            Row newRow = sheet.createRow(lastRowIndex + 1);

            // EmpId Cell A
            Cell cell1 = newRow.createCell(0);
            cell1.setCellValue(eid);

            //Emp Password Cell B
            Cell cell2 = newRow.createCell(1);
            cell2.setCellValue(password);

            // Emp Name Cell C
            Cell cell3 = newRow.createCell(2);
            cell3.setCellValue(empName);

            // Emp Total CTC Cell D
            Cell cell4 = newRow.createCell(3);
            cell4.setCellValue(totalCtc);

            // Emp Basic Cell E
            Cell cell5 = newRow.createCell(4);
            String formula = "D" + lastIndex + "*" + properties.getProperty("basic");
            cell5.setCellFormula(formula);

            // Emp HRA Cell F
            Cell cell6 = newRow.createCell(5);
            String formula1 = "E" + lastIndex + "*" + properties.getProperty("hra");
            cell6.setCellFormula(formula1);

            // Emp LTA Cell G
            Cell cell7 = newRow.createCell(6);
            String formula2 = "D" + lastIndex + "*" + properties.getProperty("lta");
            cell7.setCellFormula(formula2);

            // Emp Sodexo Cell H
            Cell cell8 = newRow.createCell(7);
            cell8.setCellValue(sodexo);

            // Emp PF Cell J Fixed Amount 1800 Cell I
            Cell cell9 = newRow.createCell(8);
            cell9.setCellValue(pf);

            // Emp VPF Initially set to 0% Cell J
            Cell cell10 = newRow.createCell(9);
            String formula3 = "E" + lastIndex + "*" + properties.getProperty("vpf");
            cell10.setCellFormula(formula3);

            // Emp Special Allowance cell K which is D-E-F-G-H-I
            Cell cell11 = newRow.createCell(10);
            String formula4 = "D" + lastIndex + "-(E" + lastIndex + "+F" + lastIndex + "+G" + lastIndex + "+H" + lastIndex + "+I" + lastIndex + ")";
            cell11.setCellFormula(formula4);

            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            formulaEvaluator.evaluateFormulaCell(cell5);
            formulaEvaluator.evaluateFormulaCell(cell6);
            formulaEvaluator.evaluateFormulaCell(cell7);
            formulaEvaluator.evaluateFormulaCell(cell10);
            formulaEvaluator.evaluateFormulaCell(cell11);

            try (FileOutputStream fs = new FileOutputStream(excelFilePath)) {
                workbook.write(fs);
            }
        } catch (IOException e) {
            System.out.println("Error Occurred while adding the Employee Data");
            e.printStackTrace();
        }
    }

    // This method is used to update the whole Column of Basic salary formula
    public static void basicUpdate(String percentage) {
        int columnNumber = 4;
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {
            Sheet sheet = workbook.getSheet(sheetName);
            for (int rowIdx = 1; rowIdx <= sheet.getLastRowNum(); rowIdx++) {
                Row row = sheet.getRow(rowIdx);
                Cell cell = row.getCell(columnNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                int formulaIdx = rowIdx + 1;

                String formula = "D" + formulaIdx + "*" + percentage;
                cell.setCellFormula(formula);

                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateFormulaCell(cell);
            }
            try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            System.out.println("Error Occurred in Updating the Basic Percentage");
        }
    }
    public static void hraUpdate(String percentage) {
        int columnNumber = 5;
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {
            Sheet sheet = workbook.getSheet(sheetName);
            for (int rowIdx = 1; rowIdx <= sheet.getLastRowNum(); rowIdx++) {
                Row row = sheet.getRow(rowIdx);
                Cell cell = row.getCell(columnNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                int formulaIdx = rowIdx + 1;

                String formula = "E" + formulaIdx + "*" + percentage;
                cell.setCellFormula(formula);

                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
                formulaEvaluator.evaluateFormulaCell(cell);
            }
            try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            System.out.println("Error Occurred in Updating the Basic Percentage");
        }
    }

    // This method is used to update any specific cell value
    public static void  cellUpdate(String referenceCell, double newValue, int cellNumber) {
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
             FileOutputStream fos = new FileOutputStream(excelFilePath)) {

            Sheet sheet = workbook.getSheet(sheetName);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(referenceCell)) {
                        Cell targetCell = row.getCell(cell.getColumnIndex() + cellNumber);

                        if (targetCell != null) {
                            targetCell.setCellValue(newValue);
                        }
                        break;
                    }
                }
            }
            workbook.write(fos);

        } catch (IOException e) {
            System.out.println("Error occurred in Update block!!!");
        }
    }

    //    public static void vpfUpdate(String referenceCell,String percentage,int cellNumber) {
//        int columnNumber = 9;
//        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {
//            Sheet sheet = workbook.getSheet(sheetName);
//            for (int rowIdx = 1; rowIdx <= sheet.getLastRowNum(); rowIdx++) {
//                Row row = sheet.getRow(rowIdx);
//                Cell cell = row.getCell(columnNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                int formulaIdx = rowIdx + 1;
//
//                String formula = "D" + formulaIdx + "*" + percentage;
//                cell.setCellFormula(formula);
//
//                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
//                formulaEvaluator.evaluateFormulaCell(cell);
//            }
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(referenceCell)) {
//                        Cell targetCell = row.getCell(cell.getColumnIndex() + cellNumber);
//                        Cell basicCell = row.getCell(cell.getColumnIndex() + 4);
//
//                        if (targetCell != null) {
//                            String formula = "E" + basicCell + "*" + percentage;
//                            targetCell.setCellFormula(formula);
//                        }
//                        break;
//                    }
//                }
//            }
//            try (FileOutputStream fos = new FileOutputStream(excelFilePath)) {
//                workbook.write(fos);
//            }
//        } catch (IOException e) {
//            System.out.println("Error Occurred in Updating the Basic Percentage");
//        }
//    }
    // This method is used to update the formula for a specific cell.
    public static void cellFormulaUpdate(String referenceCell, String percentage, int cellNumber) {
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
             FileOutputStream fos = new FileOutputStream(excelFilePath)) {

            Sheet sheet = workbook.getSheet(sheetName);

            for (Row row : sheet) {
                for (Cell cell : row) {
                    if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(referenceCell)) {
                        Cell targetCell = row.getCell(cell.getColumnIndex() + cellNumber);
                        Cell basicCell = row.getCell(cell.getColumnIndex() + 4);

                        if (targetCell != null) {
                            String formula = "E" + basicCell + "*" + percentage;
                            targetCell.setCellFormula(formula);
                        }
                        break;
                    }
                }
            }
            workbook.write(fos);

        } catch (IOException e) {
            System.out.println("Error occurred in Update block!!!");
        }
    }

    public static Boolean dataReadValidation(String empInput, int cellNumber) {
        int flag = 0;
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {
            Sheet sheet = workbook.getSheet(sheetName);

            for (Row row : sheet) {
                Cell cell = row.getCell(cellNumber);

                if (cell != null && cell.toString().equals(empInput)) {
                    flag += 1;
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error found in Data Reading");
        }
        return flag == 1;
    }

    public static String dataRead(String reference, int cellNumber){
        String targetValue = "";

        try(Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))){
            Sheet sheet = workbook.getSheet(sheetName);

            for (Row row: sheet){
                for (Cell cell: row){
                    if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(reference)){
                        Cell targetCell = row.getCell(cell.getColumnIndex() + cellNumber);
                        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                        CellValue cellValue = evaluator.evaluate(targetCell);

                        if (cellValue != null){
                            if (cellValue.getCellType() == CellType.STRING){
                                targetValue = cellValue.getStringValue();
                            } else if (cellValue.getCellType() == CellType.NUMERIC) {
                                double intTargetValue = cellValue.getNumberValue();
                                targetValue = Double.toString(intTargetValue);
//                            } else if (targetCell.getCellType() == CellType.BOOLEAN) {
//                                boolean booleanTarget = targetCell.getBooleanCellValue();
//                                targetValue = Boolean.toString(booleanTarget);
//                            } else if (targetCell.getCellType() == CellType.FORMULA) {
//                                targetValue = targetCell.getCellFormula();
                            }
                        }
                        break;
                    }
                }
            }
        } catch (IOException e){
            System.out.println("Error in the Data Reading");
        }
        return targetValue;
    }
}
