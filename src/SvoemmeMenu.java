import org.json.JSONException;

import java.time.LocalDate;
import java.util.Scanner;

public class SvoemmeMenu {

    public void SwimProgram() throws JSONException {
        Scanner scanner = new Scanner(System.in);
        int valg;
        while (true){
            System.out.println("Velkommen til Svømmeklubben Delfin");
            System.out.println("Tast '1' for at oprette et medlemsskab");
            System.out.println("Tast '2' for at annullere en tid");
            System.out.println("Tast '3' for at betale for din tid");
            System.out.println("Tast '4' for at logge ind som admin");
            System.out.println("Tast '0' for at afslutte");
            valg=scanner.nextInt();

            if (valg==0) break;
            switch (valg){
                case 1: {
                    System.out.println("Tast 1 for at oprette en motionist");
                    System.out.println("Tast 2 for at oprette konkurrencesvømmer");
                    valg = scanner.nextInt();
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
}
