import org.json.JSONException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetitiveSwimmer extends Medlem {
    // Liste til at gemme konkurrencesvømmerens discipliner
    private List<String> discipliner;

    // Fast aktivitetsniveau for konkurrencesvømmere
    String aktivitetsNiveau = "Konkurrencesvømmer";

    // Konstruktør for en konkurrencesvømmer
    public CompetitiveSwimmer(String navn, LocalDate dateOfBirth, String betalingsStatus) {
        super(navn, dateOfBirth, "Aktiv", betalingsStatus);
        this.discipliner = new ArrayList<>();
    }

    // Bestemmer medlemmets status baseret på alder
    protected void bestemMedlemsStatus() {
        // Sætter status for junior medlemmer
        if (alder < 18) status = Medlem.medlemsStatus.AKTIV_JUNIOR;
        // Sætter status for senior medlemmer
        if (alder >= 18) status = Medlem.medlemsStatus.AKTIV_SENIOR;
        // Ekstra check for ældre senior medlemmer
        if (alder >= 60) status = Medlem.medlemsStatus.AKTIV_SENIOR;
    }

    // Metode til at oprette nyt medlem.
    public static void opretMedlem() throws JSONException {
        System.out.println("Indtast navn");
        String navn = scanner.nextLine();

        LocalDate dateOfBirth = TryCatch.indtastDato();

        System.out.println("Har de betalt for medlemsskabet, eller er det på kredit?");
        String betalingsStatus;
        while (true) {
            betalingsStatus = scanner.nextLine();
            if (betalingsStatus.equalsIgnoreCase("Kredit") || betalingsStatus.equalsIgnoreCase("Betalt")){
                break;
            }
            else {
                System.out.println("Ugyldigt input, det skal være betalt eller kredit");
            }
        }
        // Gem det nye medlem via FileHandler
        FileHandler.saveMedlem(new CompetitiveSwimmer(navn, dateOfBirth, betalingsStatus));
    }

    // Tilføjer en ny disciplin til svømmerens disciplinliste
    public void addDisciplin(String disciplin) {
        if (!discipliner.contains(disciplin)) {
            discipliner.add(disciplin);
        } else {
            System.out.println("Den valgte disciplin er allerede tilføjet.");
        }
    }

    // Fjerner en disciplin fra svømmerens disciplinliste
    public void removeDisciplin(String disciplin) {
        if (discipliner.contains(disciplin)) {
            discipliner.remove(disciplin);
        } else {
            System.out.println("Den valgte disciplin er ikke tilføjet til svømmeren.");
        }
    }

    // Get-metoder
    public List<String> getDiscipliner() {
        return discipliner;
    }

    public String getAktivitetsNiveau() {
        return aktivitetsNiveau;
    }

    // To-String
    @Override
    public String toString() {
        return super.toString() + "\t Discipliner: " + discipliner;
    }
}