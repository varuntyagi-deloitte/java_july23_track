/**
 Traverse through this json to find “Frozen”
 {
 "disney": {
 "history": {
 "founder": "Walt Disney",
 "currentCeo": "Bob Iger",
 "founded":{
 "city":"Los Angeles",
 "state":"California",
 "month":"October",
 "day":"16",
 "year":"1923"
 }
 },
 "movies": [
 "Toy Story",
 "Frozen",
 "Star Wars",
 "Lion King",
 "Spirited Away",
 "Alladin"
 ],
 "metaData": {
 "createdBy": null,
 "lastModifiedBy": null,
 "createdDate": "2022-02-21T16:01:27.000Z",
 "lastModifiedDate": "2022-12-16T16:01:27.000Z"
 }
 }
 }
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Scanner;

public class Frozen {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the movie to be found from the dataset: ");
        String find = sc.nextLine();
        String file_name = "C:/Users/varutyagi/July_Java_Track/src/main/java/Frozen.json";
        try(FileReader file = new FileReader(file_name)){
            JSONParser parse = new JSONParser();
            Object obj = parse.parse(file);
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray movies = (JSONArray) jsonObj.get("disney.movies");
            System.out.println(movies.toJSONString());
            for(Object movie:movies){
                if(movie.equals(find)){
                    System.out.println(find+" movie found");
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
