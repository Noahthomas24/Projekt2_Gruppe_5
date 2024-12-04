import org.json.JSONException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetitiveSwimmer extends Medlem{
    private List<String> discipliner;
    protected enum medlemsStatus {AKTIV_JUNIOR, AKTIV_SENIOR}
    String aktivitetsNiveau = "Konkurrencesvømmer";

    public CompetitiveSwimmer(String navn, LocalDate dateOfBirth, String betalingsStatus) {
        super(navn, dateOfBirth, "Aktiv", betalingsStatus);
        this.discipliner = new ArrayList<>();
    }

    protected void bestemMedlemsStatus() {
        if (alder < 18) status = Medlem.medlemsStatus.AKTIV_JUNIOR;
        if (alder >= 18) status = Medlem.medlemsStatus.AKTIV_SENIOR;
    }

    public static void opretMedlem() throws JSONException {
        System.out.println("Nu opretter vi et medlem");
        System.out.println("Indtast navn");
        String navn = scanner.nextLine();
        LocalDate dateOfBirth = TryCatch.indtastDato();
        System.out.println("Betalt eller kredit?");
        String betalingsStatus = scanner.nextLine();
        FileHandler.saveMedlem(new CompetitiveSwimmer(navn, dateOfBirth, betalingsStatus));
    }

    public void addDisciplin(String disciplin) {
        if (!discipliner.contains(disciplin)){
            discipliner.add(disciplin);
        } else {
            System.out.println("Den valgte disciplin er allerede tilføjet.");
        }
    }

    public void removeDisciplin(String disciplin) {
        if (discipliner.contains(disciplin)){
            discipliner.remove(disciplin);
        } else {
            System.out.println("Den valgte disciplin er ikke tilføjet til svømmeren.");
        }
    }

    public List<String> getDiscipliner(){
        return discipliner;
    }

    public String getAktivitetsNiveau(){
        return aktivitetsNiveau;
    }

    @Override
    public String toString() {
        return super.toString() + "\t Discipliner: " + discipliner;
    }

    public static void main(String[] args) {
        CompetitiveSwimmer a = new CompetitiveSwimmer("Lars Testersen", LocalDate.of(2000,1,29), "BETALT");
        a.addDisciplin("Bryst");
        a.addDisciplin("Crawl");
        System.out.println(a);
        a.removeDisciplin("Crawl");
        System.out.println(a);
    }
}
