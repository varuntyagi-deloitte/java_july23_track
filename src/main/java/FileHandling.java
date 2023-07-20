import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileHandling {
    public static final String file_name = "C:/Users/varutyagi/July_Java_Track/src/main/java/mobileData.csv";

    public static void writeMethod(List<String[]> records) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name))) {
            int cnt = 1;
            for (String[] row : records) {
                for (int i = 0; i < row.length; i++) {
                    writer.write(row[i]);
                    if (i < row.length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
                System.out.println("Record " + cnt + " inserted successfully");
                cnt++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String[]> displayRecords() {
        String line;
        List<String[]> data_read = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
            int cnt = 1;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                data_read.add(row);
                cnt++;
            }
            System.out.println("Total " + (cnt - 1) + " records read");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data_read;
    }

    public static void update_record(String value) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_name))) {
            List<String[]> read_data = displayRecords();
            for (int i = 0; i < read_data.size(); i++) {
                String[] row = read_data.get(i);
                if (row[0].equals(value)) {
                    int price = Integer.parseInt(row[3]);
                    row[3] = Integer.toString(price - (price * 10) / 100);
                    read_data.set(3, row);
                    break;
                } else if (row[2].equals(value)) {
                    int price = Integer.parseInt(row[3]);
                    row[3] = Integer.toString(price + (price * 10) / 100);
                    read_data.set(3, row);
                }
            }
            writeMethod(read_data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<String[]> data = new ArrayList<>();
        int limit;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of records u wanna insert into the file: ");
        limit = sc.nextInt();
        for (int i = 0; i < limit; i++) {
            System.out.println("Model Number:");
            String model_num = sc.next();
            System.out.println("Mobile Name: ");
            String mobile_name = sc.next();
            System.out.println("Brand Name: ");
            String brand_name = sc.next();
            System.out.println("Price: ");
            String price = sc.next();
            data.add(new String[]{model_num, mobile_name, brand_name, price});
        }
        writeMethod(data);
        System.out.println("Enter the brand name records u wanna read: ");
        String name = sc.next();
        for (String[] record : displayRecords()) {
            if (record[2].equals(name)) {
                System.out.println(Arrays.toString(record));
            }
        }
        System.out.println("Enter model number whose price is to be updated: ");
        String model = sc.next();
        update_record(model);
        System.out.println("Enter model number whose price is to be updated: ");
        String brand = sc.next();
        update_record(brand);
        for (String[] record : displayRecords()) {
            if (record[2].equals(brand)) {
                System.out.println(Arrays.toString(record));
            }
        }
    }
}
