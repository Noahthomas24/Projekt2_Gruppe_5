import org.json.JSONException;

public class Main {
    public static void main(String[] args) throws JSONException {
        FileHandler fileHandler= new FileHandler();
        fileHandler.jsonWriter();
        SvoemmeMenu s = new SvoemmeMenu();
        s.FormandMenu();
        Medlem.medlemmer.get(4).setSaldo(-132);
        fileHandler.getRestanceLise();

    }
}
