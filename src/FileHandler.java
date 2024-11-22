import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
public class FileHandler {
    ArrayList<Medlem> medlemmer = new ArrayList<>();

    public void InnitMedlemmer() throws JSONException {
        medlemmer.add(new Medlem("Mikeal Felpsr",LocalDate.of(1980, 12, 31), "Aktiv", "Kredit"));
        medlemmer.add(new Medlem("Noah Carter", LocalDate.of(2002, 10, 28), "Aktiv", "Kredit"));
        medlemmer.add(new Medlem("julius Bay", LocalDate.of(1910, 1, 21), "Aktiv", "Kredit"));
        medlemmer.add(new Medlem("Safire Storm", LocalDate.of(1983, 2, 18), "Aktiv", "Kredit"));
        medlemmer.add(new Medlem("Mr Morsing", LocalDate.of(1965, 12, 16), "Passiv", "Kredit"));
        medlemmer.add(new Medlem("Thamid Jabril", LocalDate.of(2006, 6, 5), "Aktiv", "Kredit"));
        medlemmer.add(new Medlem("Oliver Nyam", LocalDate.of(2004, 9, 3), "Passiv", "Kredit"));
        medlemmer.add(new Medlem("Signe Pedersen", LocalDate.of(1966, 2, 2), "Aktiv", "Kredit"));
        medlemmer.add(new Medlem("Karsten Kristensen", LocalDate.of(2001, 12, 10), "Aktiv", "Kredit"));
        medlemmer.add(new Medlem("Kian Corsa ", LocalDate.of(2013, 1, 3), "Passiv", "Kredit"));

        jsonHandler();
    }

    FileHandler () throws JSONException {
        InnitMedlemmer();
    }

    public void saveMedlem (Medlem medlem) throws JSONException {
        medlemmer.add(medlem);
        jsonHandler();

    }

    public void jsonHandler() throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < medlemmer.size(); i++) {
            JSONObject objItem = new JSONObject();
            objItem.put("navn", medlemmer.get(i).getNavn());
            objItem.put("dateOfBirth", medlemmer.get(i).getDateOfBirth().toString());
            objItem.put("medlemsstatus", medlemmer.get(i).getMedlemsStatus().toString());
            objItem.put("saldo", String.valueOf(medlemmer.get(i).getSaldo()));
            // Tilføj jsonObject direkte til jsonArray
            jsonArray.put(objItem);

        }
        try (
                FileWriter fil = new FileWriter("Oversigt over medlemmer.json")) {
            String formattedJson = jsonArray.toString();
            fil.write(formattedJson.replaceAll("\\{", "{\n").replaceAll("\\}", "\n}").replaceAll(",", ",\n"));
            System.out.println("Fil succesfuldt overført");

        }catch (Exception e) {
            System.out.println(e);
        }

    }

    public void jsonReader(){
        try (BufferedReader reader = new BufferedReader(new FileReader("Oversigt over medlemmer.json"))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // First, parse the content as a JSONArray
            JSONArray jsonArray = new JSONArray(jsonContent.toString());

            // Iterate through the array
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Get values from the JSON object
                String navn = jsonObject.getString("navn");
                String dateOfBirth = jsonObject.getString("dateOfBirth");
                String medlemsstatus = jsonObject.getString("medlemsstatus");
                String saldo = jsonObject.getString("saldo");

                // Print out the parsed data
                System.out.println("Navn: " + navn + ", Fødselsdato: " + dateOfBirth +
                        ", Medlemsstatus: " + medlemsstatus + ", Saldo: " + saldo);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("JSON Parsing Error: " + e.getMessage());
        }


    }
}


