import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TryCatch {

    // Verificerer at man indtaster et tal
    public static int indtastTal(){
        while (true){
            String input = Medlem.scanner.nextLine();
            // datatypes
            try {
                return Integer.parseInt(input); // konverterer string til int
            } catch (NumberFormatException e){
                System.out.println("Ugyldigt valg, prøv igen");
            }
        }
    }

    // Verificerer at man indtaster datoer i korrekt format, og de ikke er i fremtiden
    public static LocalDate indtastDato(){
        LocalDate dato = null;
        boolean korrektDato = false;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (!korrektDato){
            System.out.println("Indtast dato (yyyy-mm-dd)");
            String indtastetDato = Medlem.scanner.nextLine();
            try {
                dato = LocalDate.parse(indtastetDato, formatter);

                if(dato.isAfter(LocalDate.now())){
                    System.out.println("Datoen kan ikke være i fremtiden, prøv igen");
                } else {
                    korrektDato = true;
                }
                } catch (RuntimeException e) {
                System.out.println("Ugyldigt input, prøv igen.");
            }
        }
        return dato;
    }

    // Verificerer at man indtaster en gyldig tid til resultater
    public static LocalTime verificerTid() {
        LocalTime tid = null;
        boolean korrektTid = false;

        while (!korrektTid) {
            System.out.println("Indtast tid (hh:mm:ss)");
            String indtastetTid = Medlem.scanner.nextLine();

            try {
                tid = LocalTime.parse(indtastetTid, DateTimeFormatter.ofPattern("HH:mm:ss"));
                korrektTid = true;
            } catch (RuntimeException e) {
                System.out.println("Ugyldig tid, prøv igen");
            }
        }
        return tid;
    }
}

