import org.json.JSONException;

import java.util.Scanner;

public class SvoemmeMenu {
    static Scanner scanner = new Scanner(System.in);

    public void programMenu() throws JSONException {

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


    private static void formandMenu() throws JSONException {
        while (true){
            System.out.println("Du har nu følgende muligheder");
            System.out.println("Tast '1' for at oprette et medlem");
            System.out.println("Tast '2' for at slette et medlemskab");
            System.out.println("Tast '3' for at ændre medlemsstatus");
            System.out.println("Tast '4' for at ændre konkurrencestatus");
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
                    int brugerID = TryCatch.indtastTal();
                    FileHandler.deleteMedlem(brugerID);
                case 3:
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
                    break;
                case 4:
                    // Set motionist eller konkurrencesvømmer
                    System.out.println("Vælg brugerID");
                    Medlem valgtMedlem1 = Medlem.medlemmer.get(TryCatch.indtastTal()-1);
                    System.out.println("Tryk 1 for at sætte medlem som Konkurrencesvømmer");
                    System.out.println("Tryk 2 for at sætte medlem som Motionist");
                    int valgKonkurrenceMotion = TryCatch.indtastTal();
                    switch (valgKonkurrenceMotion) {
                        case 1:
                            valgtMedlem1.aktivitetsNiveau = "Konkurrencesvømmer";
                            valgtMedlem1.bestemMedlemsStatus();
                            System.out.println(valgtMedlem1);
                            FileHandler.jsonWriter();
                            break;
                        case 2:
                            valgtMedlem1.aktivitetsNiveau = "Motionist";
                            System.out.println(valgtMedlem1);
                            FileHandler.jsonWriter();
                            break;
                    }
                default:
                    System.out.println("Ikke en mulighed, prøv igen.");
            }
        }

    }

    private static void traenerMenu() throws JSONException {
        while (true) {
            System.out.println("Du har nu følgende muligheder");
            System.out.println("Tast '1' for at tilføje resultat");
            System.out.println("Tast '2' for at se alle resultater");
            System.out.println("Tast '3' for at se top 5");
            System.out.println("Tast '4' for at tilføje en disciplin til en svømmer");
            System.out.println("Tast '5' for at fjerne en disciplin fra en svømmer");
            System.out.println("Tast '0' for at gå til forrige side");
            int valg = TryCatch.indtastTal();
            if (valg == 0) break;

            switch (valg){
                case 1: Resultat.addResultat();
                    break;
                case 2: Resultat.visAlleResultaterForSpecifiktHold();
                    break;
                case 3: Resultat.visTop5Resultater();
                    break;
                case 4: CompetitiveSwimmer.addDisciplin();
                    break;
                case 5: CompetitiveSwimmer.removeDisciplin();
                    break;
                default:
                    System.out.println("Ikke en mulighed, prøv igen.");
            }
        }
    }

    private static void kasserMenu() {
        while (true) {
            System.out.println("Du har nu følgende muligheder");
            System.out.println("Tast '1' for at se en liste over alle medlemmer.");
            System.out.println("Tast '2' for at se en liste over medlemmer i restance.");
            System.out.println("Tast '3' for at se en enkelt brugers restance");
            System.out.println("Tast '0' for at gå til forrige side");
            int valg = TryCatch.indtastTal();
            if (valg == 0) break;

            switch (valg){
                case 1:
                    for (Medlem m : Medlem.medlemmer){
                        System.out.println(m);
                    }
                    break;
                case 2: FileHandler.getRestanceLise();
                    break;
                case 3: // step 1: tast brugerID step 2: confirm
                    System.out.println("Tast brugerID");
                    int brugerid = TryCatch.indtastTal();
                    boolean x = Medlem.tjekRestance(brugerid);// sætter parameter og kalder parameter
                    System.out.println();
                    for (Medlem m : Medlem.medlemmer){
                        if (m.brugerID == brugerid){
                            System.out.println(m);
                            System.out.println(m.saldo);
                        }
                    }
                    break;
                default:
                    System.out.println("Ikke en mulighed, prøv igen.");
            }
        }
    }
}
