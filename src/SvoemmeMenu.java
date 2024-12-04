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
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
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

            if (valg==0) break;
            switch (valg){
                case 1: {
                    System.out.println("Tast '1' for at oprette en motionist");
                    System.out.println("Tast '2' for at oprette konkurrencesvømmer");
                    valg = TryCatch.indtastTal();
                    switch (valg){
                        case 1: Medlem.opretMedlem();
                        break;
                        case 2: CompetitiveSwimmer.opretMedlem();
                        break;
                    }
                }

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

        }
    }
}
