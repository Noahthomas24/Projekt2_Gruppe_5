import org.json.JSONException;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Resultat implements Comparable<Resultat>{
    static Scanner keyboard = new Scanner(System.in);
    static List<Resultat> resultater = new ArrayList<>();
    int brugerID;
    String disciplin;
    LocalTime resTid;
    static LocalDate dato;
    Medlem medlem;
    static String session; //Træning eller stævne
    public DateTimeFormatter format = DateTimeFormatter.ofPattern("mm:ss:SS");

    Resultat (int brugerID, LocalDate dato, String session, String disciplin, LocalTime resTid){
        checkID(brugerID);
        this.brugerID = brugerID;
        this.disciplin = disciplin;
        this.resTid = resTid;
        this.session = session;
        this.dato = dato;
    }

    public static boolean checkID(int brugerID) {
        for (Medlem m : Medlem.medlemmer) {
            if (m.getBrugerID()==brugerID){
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
        return medlem.navn+" - "+getDisciplin()+" - " + getSession() + " - " +resTid.format(format);
    }

    public int compareTo(Resultat other){
        return this.resTid.compareTo(other.resTid);
    }

    public static void addResultat() throws JSONException {
        System.out.println("Hvad er svømmerens bruger-ID?");
        int iD = keyboard.nextInt();
        System.out.println();

        if (!checkID(iD)) {
            System.out.println("Resultat blev ikke oprettet, da ID'et ikke eksisterer.");
            return;
        }

        dato = TryCatch.indtastDato();

        System.out.println("Hvilken disciplin?");
        String disciplin = keyboard.nextLine();
        System.out.println();

        System.out.println("Tast 1 for træningstid, tast 2 for stævne");
        int sessionsForm = Medlem.scanner.nextInt();
        switch (sessionsForm){
            case 1: session = "Træningstid";
                break;
            case 2:
                System.out.println("Hvilket stævne?");
                session = Medlem.scanner.nextLine();
                break;
        }


        LocalTime resTid = verificerTid();
        FileHandler.saveResult(new Resultat(iD, dato, session, disciplin, resTid));
    }

    public static LocalTime verificerTid(){
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

    // Sorterer resultaterne efter laveste tid. Bliver kaldt ved oprettelse af FileHandler objekt.
    public static void sorterEfterTider(){
        Collections.sort(resultater, new Comparator<Resultat>() {
            @Override
            public int compare(Resultat o1, Resultat o2) {
                return o1.getResTid().compareTo(o2.getResTid());
            }
        });
    }

    // Forkorter resultatliste til liste for junior eller senior
    public static List<Resultat> visResultaterForSpecifiktHold(){
        List<Resultat> holdResultater = new ArrayList<>();

        System.out.println("Tast 1 for at se resultater for JUNIOR");
        System.out.println("Tast 2 for at se resultater for SENIOR");
        int valg = TryCatch.indtastTal();
        switch (valg){
            case 1:
                for (Resultat r : resultater){
                    if (r.getDisciplin().contains("JUNIOR")) holdResultater.add(r);
                }
                break;

            case 2:
                for (Resultat r : resultater){
                    if (r.getDisciplin().contains("SENIOR")) holdResultater.add(r);
                }
                break;
        }

        return holdResultater;
    }

    // Forkorter resultatliste til den specifikke disciplin
    private static List<Resultat> visResultaterForSpecifikDisciplin(){
        List<Resultat> disciplinResultater = visResultaterForSpecifiktHold();

        System.out.println("Hvilken disciplin ønsker du at se resultater for?");
        System.out.println("Tast 1 for 'Brystsvømning'");
        System.out.println("Tast 2 for 'Crawl'");
        System.out.println("Tast 3 for 'Butterfly'");
        System.out.println("Tast 4 for 'Rygcrawl'");
        int valgtDisciplin = TryCatch.indtastTal();
        switch (valgtDisciplin){
            case 1:
                for (Resultat r : resultater){
                    if (r.getDisciplin().equalsIgnoreCase("Brystsvømning")) disciplinResultater.add(r);
                }
                break;

            case 2:
                for (Resultat r : resultater){
                    if (r.getDisciplin().equalsIgnoreCase("Crawl")) disciplinResultater.add(r);
                }
                break;

            case 3:
                for (Resultat r : resultater){
                    if (r.getDisciplin().equalsIgnoreCase("Butterfly")) disciplinResultater.add(r);
                }
                break;

            case 4:
                for (Resultat r : resultater){
                    if (r.getDisciplin().equalsIgnoreCase("Rygcrawl")) disciplinResultater.add(r);
                }
                break;
        }

        return disciplinResultater;
    }

    // Viser de fem bedste resultater for det givne hold indenfor den givne disciplin
    public static void visTop5Resultater(){
        List<Resultat> topFem = new ArrayList<>();
        List<Resultat> specifikDisciplin = visResultaterForSpecifikDisciplin();

        for (Resultat r : specifikDisciplin){
            if (!topFem.contains(r.getBrugerID())){
                topFem.add(r);
            }

            if (topFem.size() == 5) { break; }
        }

        for (Resultat r : topFem){
            System.out.println(r);
        }
    }

    public String getDisciplin(){ return disciplin;}

    public LocalTime getResTid(){ return resTid;}

    public String getSession(){ return session; }

    public LocalDate getDato() { return dato; }

    public int getBrugerID() { return brugerID; }


}
