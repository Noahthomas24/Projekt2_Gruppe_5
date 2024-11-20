import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Main {
    public static void main(String[] args) {
        ArrayList<medlem> medlemmer = new ArrayList<medlem>();

        for (int i = 0; i<100;i++){
            medlemmer.add(new medlem(i+"",LocalDate.of(2000,12,30), i+"", i+""));

        }


        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < medlemmer.size(); i++) {
            JSONObject objItem = new JSONObject();
            objItem.put("navn", medlemmer.get(i).getNavn());
            objItem.put("alder", medlemmer.get(i).getAlder());
            objItem.put("medlemsstatus", medlemmer.get(i).getMedlemsStatus());
            objItem.put("saldo", medlemmer.get(i).getSaldo());

            // Tilføj jsonObject direkte til jsonArray
            jsonArray.add(objItem);


        }
        try(FileWriter fil =new FileWriter("Oversigt over medlemmer.json")){
            fil.write(jsonArray.toString());
            System.out.println("Fil succesfuldt overført");
            System.out.println("\n JSON Object: " + jsonArray);

        } catch(Exception e){
            System.out.println(e);
        }



    }
    }
