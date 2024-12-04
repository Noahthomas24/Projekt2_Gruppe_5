import org.json.JSONException;

import java.util.Scanner;

public class SvoemmeMenu {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws JSONException {
        while (true){
            System.out.println("Velkommen til Svømmeklubben Delfinen!");
            System.out.println("Vælg bruger:");
            System.out.println("Tast '1' for formand");
            System.out.println("Tast '2' for træner");
            System.out.println("Tast '3' for kasser");
            System.out.println("Tast '0' for at afslutte programmet");
            System.out.println("Indtast valg:");
            int valg = TryCatch.indtastTal();
            if (valg == 0) break;

            switch (valg){
                case 1: formandMenu();
                    break;
                case 2: traenerMenu();
                    break;
                case 3: kasserMenu();
                    break;
                default:
                    System.out.println("Ikke en mulighed, prøv igen.");
            }
        }
    }
    public void setAktivPassiv() throws JSONException {
        System.out.println("Vælg brugerID");
        Medlem valgtMedlem = Medlem.medlemmer.get(TryCatch.indtastTal() - 1);
        System.out.println("Tryk 1 for at sætte medlem som aktiv");
        System.out.println("Tryk 2 for at sætte medlem som passiv");
        int valgAktivPassiv = TryCatch.indtastTal();
        switch (valgAktivPassiv) {
            case 1:
                valgtMedlem.aktivPassiv = "aktiv";
                valgtMedlem.bestemMedlemsStatus();
                System.out.println(valgtMedlem);
                FileHandler.jsonWriter();
                break;
            case 2:
                valgtMedlem.status = Medlem.medlemsStatus.PASSIV;
                System.out.println(valgtMedlem);
                FileHandler.jsonWriter();
                break;
        }
    }

    public static void formandMenu() throws JSONException {
        while (true){
            System.out.println("Du har nu følgende muligheder");
            System.out.println("Tast '1' for at oprette et medlem");
            System.out.println("Tast '2' for at ændre medlemsstatus");
            System.out.println("Tast '3' for at slette et medlemskab");
            System.out.println("Tast '0' for at gå til forrige side");
            int valg= TryCatch.indtastTal();
            if (valg == 0) break;

            switch (valg){
                case 1:
                    System.out.println("Tast '1' for at oprette en motionist");
                    System.out.println("Tast '2' for at oprette konkurrencesvømmer");
                    valg = TryCatch.indtastTal();
                    switch (valg){
                        case 1: Medlem.opretMedlem();
                        break;
                        case 2: CompetitiveSwimmer.opretMedlem();
                        break;
                    }
                    break;
                case 2:
                    System.out.println("Vælg brugerID");
                    Medlem valgtMedlem = Medlem.medlemmer.get(TryCatch.indtastTal()-1);
                    System.out.println("Tryk 1 for at sætte medlem som aktiv");
                    System.out.println("Tryk 2 for at sætte medlem som passiv");
                    int valgAktivPassiv = TryCatch.indtastTal();
                    switch (valgAktivPassiv) {
                        case 1:
                            valgtMedlem.aktivPassiv = "aktiv";
                            valgtMedlem.bestemMedlemsStatus();
                            System.out.println(valgtMedlem);
                            FileHandler.jsonWriter();
                            break;
                        case 2:
                            valgtMedlem.status = Medlem.medlemsStatus.PASSIV;
                            System.out.println(valgtMedlem);
                            FileHandler.jsonWriter();
                            break;
                    }
                    // Medlem.aendrMedlem
                    break;
                case 3:
                    // Medlem.sletMedlem
                    break;
                default:
                    System.out.println("Ikke en mulighed, prøv igen.");
            }
        }

    }

    public static void traenerMenu() {
        while (true) {
            System.out.println("Du har nu følgende muligheder");
            System.out.println("Tast '1' for at ");
            System.out.println("Tast '2' for at ");
            System.out.println("Tast '3' for at ");
            System.out.println("Tast '0' for at gå til forrige side");
            int valg = TryCatch.indtastTal();
            if (valg == 0) break;

            switch (valg){
                case 1: // Liste over Holdmedlemmer
                    break;
                case 2: // Se resultater
                    break;
                case 3: // 5 bedste resultater
                    break;
                default:
                    System.out.println("Ikke en mulighed, prøv igen.");
            }
        }
    }

    public static void kasserMenu() {
        while (true) {
            System.out.println("Du har nu følgende muligheder");
            System.out.println("Tast '1' for at ");
            System.out.println("Tast '2' for at ");
            System.out.println("Tast '3' for at ");
            System.out.println("Tast '0' for at gå til forrige side");
            int valg = TryCatch.indtastTal();
            if (valg == 0) break;

            switch (valg){
                case 1: //
                    break;
                case 2: //
                    break;
                default:
                    System.out.println("Ikke en mulighed, prøv igen.");
            }
        }
    }
}
