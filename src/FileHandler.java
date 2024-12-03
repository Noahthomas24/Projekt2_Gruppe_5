import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    static JSONArray jsonArray = new JSONArray();

    public void InnitMedlemmer() throws JSONException {
        Medlem.medlemmer.add(new Medlem("Mikeal Felpsr",LocalDate.of(1980, 12, 31), "Aktiv", "Kredit"));
        Medlem.medlemmer.add(new Medlem("Noah Carter", LocalDate.of(2002, 10, 28), "Aktiv", "Kredit"));
        Medlem.medlemmer.add(new Medlem("julius Bay", LocalDate.of(1910, 1, 21), "Aktiv", "Kredit"));
        Medlem.medlemmer.add(new Medlem("Safire Storm", LocalDate.of(1983, 2, 18), "Aktiv", "Kredit"));
        Medlem.medlemmer.add(new Medlem("Mr Morsing", LocalDate.of(1965, 12, 16), "Passiv", "Kredit"));
     

        jsonWriter();
    }

    FileHandler () throws JSONException {
        jsonReader();
        if (Medlem.medlemmer.isEmpty()){
            InnitMedlemmer();}
    }

    public static void saveMedlem (Medlem medlem) throws JSONException {
        Medlem.medlemmer.add(medlem);
        jsonWriter();

    }

    public static void jsonWriter() throws JSONException {
        jsonArray = new JSONArray();
        for (int i = 0; i < Medlem.medlemmer.size(); i++) {

            //Disse næste linje definere hvad et JSON object er. De omdanner alle værdierne til strings, og bliver til et objekt.
            JSONObject objItem = new JSONObject();
            objItem.put("navn", Medlem.medlemmer.get(i).getNavn());
            objItem.put("dateOfBirth", Medlem.medlemmer.get(i).getDateOfBirth().toString());
            objItem.put("medlemsstatus", Medlem.medlemmer.get(i).getMedlemsStatus().toString());
            objItem.put("saldo", String.valueOf(Medlem.medlemmer.get(i).getSaldo()));
            objItem.put("aktivitetsniveau", String.valueOf(Medlem.medlemmer.get(i).getAktivitetsNiveau()));

            // Tilføjer objektet direkte til array
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
    public static void jsonReader(){
        try (BufferedReader reader = new BufferedReader(new FileReader("Oversigt over medlemmer.json"))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonContent.toString());


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                //Dette kode gør det modsatte. Den tager objektet og omdanner det til brugbare værdier
                String navn = jsonObject.getString("navn");
                String dateOfBirthString = jsonObject.getString("dateOfBirth");
                String medlemsstatus = jsonObject.getString("medlemsstatus");
                String saldo = jsonObject.getString("saldo");
                String aktivitetsNiveau = jsonObject.getString("aktivitetsniveau");

                //det her stykke kode omdanner den ovenstående dateOfBirthString til en Localdate der kan bruges.
                LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString, DateTimeFormatter.ISO_LOCAL_DATE);

                System.out.println("Navn: " + navn + ", Fødselsdato: " + dateOfBirth +
                        ", Medlemsstatus: " + medlemsstatus + ", Saldo: " + saldo);

                Medlem medlem = new Medlem(navn, dateOfBirth, medlemsstatus, saldo);
                Medlem.medlemmer.add(medlem);

            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (org.json.JSONException e) {
            System.out.println("JSON Parsing Error: " + e.getMessage());
        }


    }
}


