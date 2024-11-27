import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Resultat {
    static Scanner keyboard = new Scanner(System.in);
    static ArrayList<Resultat> resultater = new ArrayList<>();
    String navn;
    String disciplin;
    LocalTime resTid;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("mm:ss:SS");

    Resultat (String navn, String disciplin, LocalTime resTid){
        this.navn = navn;
        this.disciplin = disciplin;
        this.resTid = resTid;
    }

    public String toString(){
        return navn+": "+resTid.format(format);
    }

    public int compareTo(Resultat r){
        return resTid.compareTo(r.resTid);
    }

    public void addResultat(){
        System.out.println("Hvad er svømmerens navn?");
        String navn = keyboard.nextLine();
        System.out.println();

        System.out.println("Hvilken disciplin?");
        String disciplin = keyboard.nextLine();
        System.out.println();

        LocalTime resTid = verificerTid();

        resultater.add(new Resultat(navn, disciplin, resTid));
    }

    public LocalTime verificerTid(){
        LocalTime tid=null;
        boolean korrektTid = false;

        while (!korrektTid){
            System.out.println("Indtast tid (mm:ss)");
            String indtastetTid = keyboard.nextLine();

            try {
                tid = LocalTime.parse(indtastetTid, DateTimeFormatter.ofPattern("mm:ss"));
                korrektTid = true;
            } catch (RuntimeException e) {
                System.out.println("Ugyldig tid, prøv igen");
            }
        }
        return tid;
    }

    public Duration konverterTid(){
        LocalTime startTid = LocalTime.of(0,0);
        LocalTime slutTid = verificerTid();

        Duration resultat = Duration.between(startTid, slutTid);
        return resultat;
    }

    public static void main(String[] args) {
        Resultat r1 = new Resultat("Ole", "Rygcrawl", LocalTime.of(0,29,12,100000000));
        Resultat r2 = new Resultat("Bo", "Rygcrawl", LocalTime.of(0,29,40,100000000));
        Resultat r3 = new Resultat("Odaae", "Rygcrawl", LocalTime.of(0,40,12,100000000));
        Resultat r4 = new Resultat("Odan", "Rygcrawl", LocalTime.of(0,29,9,100000000));

        resultater.add(new Resultat("Ole", "Rygcrawl", LocalTime.of(0,29,12,100000000)));
        resultater.add(new Resultat("Bo", "Rygcrawl", LocalTime.of(0,29,40,100000000)));
        resultater.add(new Resultat("Odaae", "Rygcrawl", LocalTime.of(0,40,12,100000000)));
        resultater.add(new Resultat("Odan", "Rygcrawl", LocalTime.of(0,29,9,100000000)));

        r1.konverterTid();
        System.out.println(r1.konverterTid());


    }
}
