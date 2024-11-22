import org.json.JSONException;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws JSONException {
        FileHandler fileHandler= new FileHandler();
        fileHandler.jsonWriter();
        fileHandler.jsonReader();
        fileHandler.saveMedlem(new Medlem("Frederik", LocalDate.of(2000, 12,30), "Aktiv", "Passiv"));


    }
}
