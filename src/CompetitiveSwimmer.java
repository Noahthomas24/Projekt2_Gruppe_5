import org.json.JSONException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetitiveSwimmer extends Medlem {
    // Liste til at gemme konkurrencesvømmerens discipliner
    private List<String> discipliner;
    String aktivitetsniveau;

    // Konstruktør for en konkurrencesvømmer
    public CompetitiveSwimmer(String navn, LocalDate dateOfBirth, String betalingsStatus) {
        super(navn, dateOfBirth, "Aktiv", betalingsStatus);
        this.discipliner = new ArrayList<>();
        this.aktivitetsNiveau = "Konkurrencesvømmer";
    }

    // Bestemmer medlemmets status baseret på alder
    protected void bestemMedlemsStatus() {
        if (alder < 18) status = Medlem.medlemsStatus.AKTIV_JUNIOR;
        if (alder >= 18) status = Medlem.medlemsStatus.AKTIV_SENIOR;
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
    public static void addDisciplin() {
        boolean found = false;
        System.out.println("Indtast brugerID på den ønskede kunde.");
        int brugerID = scanner.nextInt();
        scanner.nextLine();
        for (Medlem m : Medlem.medlemmer) {
            if (m.brugerID == brugerID && m instanceof CompetitiveSwimmer c){
                System.out.println("Hvilken disciplin ønsker du at tilføje til svømmeren?");
                String disciplin = scanner.nextLine();
                if (!c.discipliner.contains(disciplin)) {
                    c.discipliner.add(disciplin);
                    System.out.println("Disciplin tilføjet.");
                } else {
                    System.out.println("Den valgte disciplin er allerede tilføjet.");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Ingen svømmer med det angivne bruger-ID blev fundet.");
        }
    }

    // Fjerner en disciplin fra svømmerens disciplinliste
    public static void removeDisciplin() {
        System.out.println("Indtast bruger-ID på det medlem, du ønsker at fjerne en disciplin fra.");
        String navn = scanner.nextLine();
        for (Medlem m : Medlem.medlemmer) {
            if (m.navn == navn && m instanceof CompetitiveSwimmer c) {
                    System.out.println("Hvilken disciplin ønsker du at tilføje til svømmeren?");
                    String disciplin = scanner.nextLine();
                    c.discipliner.add(disciplin);
                } else {
                    System.out.println("Den valgte disciplin er ikke tilføjet til svømmeren.");
                }
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