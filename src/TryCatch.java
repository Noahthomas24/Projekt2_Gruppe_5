import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TryCatch {

    public static int indtastTal(){
        while (true){
            String input = Medlem.scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("Ugyldigt valg, prøv igen");
            }
        }
    }

    public static LocalDate indtastDato(){
        LocalDate dato = null;
        boolean korrektDato = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (!korrektDato){
            System.out.println("Indtast dato (yyyy-mm-dd)");
            String indtastetDato = Medlem.scanner.nextLine();
            try {
                dato = LocalDate.parse(indtastetDato, formatter);
                korrektDato = true;
            } catch (RuntimeException e) {
                System.out.println("Ugyldigt input, prøv igen.");
            }
        }
        return dato;
    }

}

