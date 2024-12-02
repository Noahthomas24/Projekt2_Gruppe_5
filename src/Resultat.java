import java.sql.Array;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Resultat implements Comparable<Resultat>{
    static Scanner keyboard = new Scanner(System.in);
    static List<Resultat> resultater = new ArrayList<>();
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

    public int compareTo(Resultat other){
        return this.resTid.compareTo(other.resTid);
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
            System.out.println("Indtast tid (hh:mm:ss)");
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

    public static void main(String[] args) {
        Resultat r1 = new Resultat("Ole", "Rygcrawl", LocalTime.of(0,29,12,100000000));
        Resultat r2 = new Resultat("Bo", "Rygcrawl", LocalTime.of(0,29,40,100000000));
        Resultat r3 = new Resultat("Odaae", "Rygcrawl", LocalTime.of(0,40,12,100000000));
        Resultat r4 = new Resultat("Odan", "Rygcrawl", LocalTime.of(0,29,9,100000000));

        resultater.add(new Resultat("Ole", "Rygcrawl", LocalTime.of(0,29,12,100000000)));
        resultater.add(new Resultat("Bo", "Rygcrawl", LocalTime.of(0,29,40,100000000)));
        resultater.add(new Resultat("Odaae", "Rygcrawl", LocalTime.of(0,40,12,100000000)));
        resultater.add(new Resultat("Odan", "Rygcrawl", LocalTime.of(0,29,9,100000000)));

        resultater.sort(null);
        for (Resultat r:resultater){
           System.out.println(r);
        }

    }
}
