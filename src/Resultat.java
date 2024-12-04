import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Resultat implements Comparable<Resultat>{
    static Scanner keyboard = new Scanner(System.in);
    static List<Resultat> resultater = new ArrayList<>();
    String navn;
    String disciplin;
    LocalTime resTid;
    Medlem medlem;
    String session; //Træning eller stævne
    public DateTimeFormatter format = DateTimeFormatter.ofPattern("mm:ss:SS");

    Resultat (String navn, String session, String disciplin, LocalTime resTid){
        checkNavn(navn);
        this.disciplin = disciplin;
        this.resTid = resTid;
        this.session = session;
    }

    public boolean checkNavn(String navn) {
        for (Medlem m : Medlem.medlemmer) {
            if (navn.equalsIgnoreCase(m.getNavn())) {
                this.medlem = m;
                return true;
            }
        }
        System.out.println("Vedkommende er ikke medlem hos os.");
        return false;
    }

    public String toString(){
        if (medlem == null) {
            return "Ugyldigt resultat (medlem ikke fundet)";
        }
        return medlem.navn+": "+resTid.format(format);
    }

    public int compareTo(Resultat other){
        return this.resTid.compareTo(other.resTid);
    }

    public void addResultat(){
        System.out.println("Hvad er svømmerens navn?");
        String navn = keyboard.nextLine();
        System.out.println();

        if (!checkNavn(navn)) {
            System.out.println("Resultat blev ikke oprettet, da navnet ikke blev fundet.");
            return;
        }


        System.out.println("Hvilken disciplin?");
        String disciplin = keyboard.nextLine();
        System.out.println();

        System.out.println("Tast 1 for træningstid, tast 2 for stævne");
        int sessionsForm = Medlem.scanner.nextInt();
        switch (sessionsForm){
            case 1: session = "Træningstid";
                break;
            case 2: session = "Stævne";
                break;
        }


        LocalTime resTid = verificerTid();

        resultater.add(new Resultat(navn, session, disciplin, resTid));
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

    public String getDisciplin(){ return disciplin;}

    public LocalTime getResTid(){ return resTid;}

    public String getSession(){ return session; }
}
