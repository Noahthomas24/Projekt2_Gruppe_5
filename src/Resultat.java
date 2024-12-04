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

    Resultat (int brugerID, String session, String disciplin, LocalTime resTid){
        checkID(brugerID);
        this.disciplin = disciplin;
        this.resTid = resTid;
        this.session = session;
    }

    public boolean checkID(int brugerID) {
        for (Medlem m : Medlem.medlemmer) {
            if (m.brugerID == brugerID) {
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
        System.out.println("Hvad er svømmerens bruger-ID?");
        int iD = keyboard.nextInt();
        System.out.println();

        if (!checkID(iD)) {
            System.out.println("Resultat blev ikke oprettet, da ID'et ikke eksisterer.");
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

        resultater.add(new Resultat(iD, session, disciplin, resTid));
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
