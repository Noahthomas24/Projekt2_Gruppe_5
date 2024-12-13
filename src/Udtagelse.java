import java.util.*;
import java.util.stream.Collectors;

public class Udtagelse {

    // Liste til at holde resultater
    private static List<Resultat> resultater = new ArrayList<>();

    // Simpel metode til at udtage svømmere til svømmekonkurrence baseret på deres præstationer
    public static List<Resultat> udtagKonkurrenceDeltagere(String disciplin, int maxDeltagere) {
        return resultater.stream()
                .filter(r -> r.getDisciplin().equalsIgnoreCase(disciplin))
                .sorted(Comparator.comparing(Resultat::getResTid))
                .limit(maxDeltagere)
                .collect(Collectors.toList());
    }

    // Eksempel på brug af metoden
    public static void udtagDeltagereMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vælg disciplin for udtagning:");
        System.out.println("1. Brystsvømning\n2. Crawl\n3. Butterfly\n4. Rygcrawl");
        int valg = scanner.nextInt();
        scanner.nextLine(); // Ryd scannerens buffer

        String disciplin;
        switch (valg) {
            case 1 -> disciplin = "Brystsvømning";
            case 2 -> disciplin = "Crawl";
            case 3 -> disciplin = "Butterfly";
            case 4 -> disciplin = "Rygcrawl";
            default -> {
                System.out.println("Ugyldigt valg");
                return;
            }
        }

        System.out.println("Indtast det maksimale antal deltagere til konkurrencen:");
        int maxDeltagere = scanner.nextInt();

        List<Resultat> deltagere = udtagKonkurrenceDeltagere(disciplin, maxDeltagere);

        System.out.println("De udtagne deltagere er:");
        deltagere.forEach(System.out::println);
    }
}