/**
 * Using file handling to write, read, and update the csv file “mobileData.json”.
 * It should contain below fields.
 * • Model No. Name, Brand, Price (Add 15 records, use Scanner to insert data at run
 * time)
 * • Display data using Brand (for example, out of 15 different details, display only whose
 * brand is Nokia (Nokia should have at least 5 records)
 * • Update price specific to 1 record wrt model number (update price to X-(X*10%))
 * • Update price for all records which have brand Nokia, say if price is X, update price to
 * X+(X*10%) and then display all update records only.
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class JSONHandling {
    private static final String file_name = "C:/Users/varutyagi/July_Java_Track/src/main/java/mobileData.json";

    public static void displayRecordsForBrand(String brand_name) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(file_name)) {
            JSONArray records = (JSONArray) parser.parse(reader);
            for (Object obj : records) {
                JSONObject record = (JSONObject) obj;
                if (record.get("brand").equals(brand_name)) {
                    System.out.println(record.toJSONString());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addRecords() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of records u want to enter: ");
        int num_records = sc.nextInt();
        JSONArray records = new JSONArray();
        for (int i = 0; i < num_records; i++) {
            System.out.println("Enter record " + (i + 1));
            JSONObject record = new JSONObject();
            System.out.println("Model Number: ");
            String model_number = sc.next();
            record.put("model_number", model_number);
            System.out.println("Name: ");
            String name = sc.next();
            record.put("name", name);
            System.out.println("Brand: ");
            String brand = sc.next();
            record.put("brand", brand);
            System.out.println("Price: ");
            String price = sc.next();
            record.put("price", price);
            records.add(record);
        }
        try (FileWriter file = new FileWriter(file_name)) {
            file.write(records.toString());
            System.out.println("Records inserted successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateRecordsForModelNumber(String model,double price_update) {
        JSONParser parser = new JSONParser();
        try (FileReader file = new FileReader(file_name)) {
            JSONArray records = (JSONArray) parser.parse(file);
            for (Object obj : records) {
                JSONObject record = (JSONObject) obj;
                if (record.get("model_number").equals(model)) {
                    double price = Double.parseDouble((String) record.get("price"));
                    price = price - ((price * price_update)/100);
                    record.put("price", String.valueOf(price));
                    break;
                }
            }
            try (FileWriter file1 = new FileWriter(file_name)) {
                file1.write(records.toJSONString());
                System.out.println("Price updated successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateRecordsForBrandName(String brand,double price_update){
        JSONParser parser = new JSONParser();
        try (FileReader file = new FileReader(file_name)) {
            JSONArray records = (JSONArray) parser.parse(file);
            for (Object obj : records) {
                JSONObject record = (JSONObject) obj;
                if (record.get("brand").equals(brand)) {
                    double price = Double.parseDouble((String) record.get("price"));
                    price = price + ((price * price_update)/100);
                    record.put("price", String.valueOf(price));
                }
            }
            try (FileWriter file1 = new FileWriter(file_name)) {
                file1.write(records.toJSONString());
                System.out.println("Updated records with brand "+brand+"");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int choice = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("1.Add records:");
        System.out.println("2.Display records:");
        System.out.println("3.Modify records using model number:");
        System.out.println("4.Modify records using brand name:");
        System.out.println("5.exit");
        while((choice>=1)&&(choice<=5)){
            System.out.println("Enter the choice:");
            choice = sc.nextInt();
            if(choice == 1){
                addRecords();
            }
            else if(choice == 2){
                System.out.println("Enter the brand u wanna see records for in the database: ");
                String brand_name = sc.next();
                displayRecordsForBrand(brand_name);
            }
            else if(choice == 3){
                System.out.println("Enter the model number u wanna update records for in the database: ");
                String model_number = sc.next();
                System.out.println("Enter the discount rate of price:");
                double price = sc.nextDouble();
                updateRecordsForModelNumber(model_number,price);
            }
            else if(choice == 4){
                System.out.println("Enter the brand name u wanna update price for in the database: ");
                String brand = sc.next();
                System.out.println("Enter the discount rate of price:");
                double brand_price = sc.nextDouble();
                updateRecordsForBrandName(brand,brand_price);
            }
            else if(choice == 5){
                System.exit(0);
            }
            else{
                System.out.println("Wrong input entered");
            }
        }
    }
}
