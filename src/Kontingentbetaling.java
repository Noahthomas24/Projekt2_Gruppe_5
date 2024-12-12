import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Kontingentbetaling extends Medlem {

    Kontingentbetaling(String navn, LocalDate dateOfBirth, String aktivPassiv, String betalingsStatus) {
        super(navn, dateOfBirth, aktivPassiv, betalingsStatus);
    }

    public static class KontingentBetaling {

        private final Map<String, Integer> betalinger;

        public KontingentBetaling() {
            betalinger = new HashMap<>();

        }


        public void registrerBetaling(Medlem medlem) {
            int currentYear = LocalDate.now().getYear();
            betalinger.put(medlem.getNavn(), currentYear);
            System.out.println("Betaling registreret for medlem: " + medlem.getNavn() + " for året: " + currentYear);


            medlem.betaltEllerKredit();
        }

        public boolean harBetalt(Medlem medlem) {
            int currentYear = LocalDate.now().getYear();
            return betalinger.containsKey(medlem.getNavn()) && betalinger.get(medlem.getNavn()) == currentYear;
        }

        public void tjekBetaling(Medlem medlem) {
            if (harBetalt(medlem)) {
                System.out.println("Medlem: " + medlem.getNavn() + " har betalt kontingent for året: " + LocalDate.now().getYear());
            } else {
                System.out.println("Medlem: " + medlem.getNavn() + " har IKKE betalt kontingent for året: " + LocalDate.now().getYear());
            }
        }

        public void tjekAlleBetalinger(List<Medlem> medlemmer) {
            for (Medlem medlem : medlemmer) {
                tjekBetaling(medlem);
            }
        }

        public void visBetalinger() {
            for (String medlemNavn : betalinger.keySet()) {
                System.out.println("Medlem: " + medlemNavn + ", betalte for året: " + betalinger.get(medlemNavn));
            }
        }

        public static void main(String[] args) {


            KontingentBetaling kontingentSystem = new KontingentBetaling();
            kontingentSystem.tjekBetaling((Medlem) medlemmer);
            kontingentSystem.registrerBetaling((Medlem) medlemmer);
            kontingentSystem.visBetalinger(medlemmer);
            kontingentSystem.tjekAlleBetalinger(medlemmer);


        }

        private void visBetalinger(List<Medlem> medlemmer) {
        }
    }
}