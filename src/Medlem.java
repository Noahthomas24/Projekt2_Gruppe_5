import org.json.JSONException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Medlem {
    protected int brugerID;
    static Scanner scanner = new Scanner(System.in);
    public static List<Medlem> medlemmer = new ArrayList<>();
    protected final String navn;
    protected final int alder;
    protected LocalDate dateOfBirth;
    protected int saldo;
    protected String betalingsStatus;
    String aktivitetsNiveau = "Motionist";

    protected enum medlemsStatus {AKTIV_JUNIOR, AKTIV_SENIOR, AKTIV_PENSIO, PASSIV}
    protected medlemsStatus status;
    protected String aktivPassiv;

    Medlem(String navn, LocalDate dateOfBirth, String aktivPassiv, String betalingsStatus) {
        brugerID = medlemmer.size()+1;
        this.navn = navn;
        alder = beregnAlder(dateOfBirth).getYears();
        this.aktivPassiv = aktivPassiv;
        this.betalingsStatus = betalingsStatus;
        this.dateOfBirth=dateOfBirth;
        bestemMedlemsStatus();
        medlemsPriser();
        betaltEllerKredit();

    }

    public static void opretMedlem() throws JSONException {
        System.out.println("Her kan du oprette et nyt medlem.");
        System.out.println("Indtast navn");
        String navn = scanner.nextLine();
        LocalDate dateOfBirth = TryCatch.indtastDato();

        System.out.println("Er det et aktivt eller passivt medlem");
        String aktivPassiv;
        while (true) {
            aktivPassiv = scanner.nextLine();
            if (aktivPassiv.equalsIgnoreCase("Aktiv") || aktivPassiv.equalsIgnoreCase("Passiv")){
                break;
            }
            else {
                System.out.println("Ugyldigt input, det skal være aktiv eller passiv");
            }
        }

        System.out.println("Har de betalt for medlemsskabet, eller er det på kredit?");
        String betalingsStatus;
        while (true) {
            betalingsStatus = scanner.nextLine();
            if (betalingsStatus.equalsIgnoreCase("Kredit") || betalingsStatus.equalsIgnoreCase("Betalt")){
                break;
            }
            else {
                System.out.println("Ugyldigt input, det skal være betalt eller kredit");
            }
        }
        FileHandler.saveMedlem(new Medlem(navn, dateOfBirth, aktivPassiv, betalingsStatus));
    }

    protected Period beregnAlder(LocalDate dateOfBirth) {
        LocalDate dagsDato = LocalDate.now();
        Period alder = Period.between(dateOfBirth, dagsDato);
        return alder;
    }
    public int getBrugerID(){
        return brugerID;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    protected void bestemMedlemsStatus() {
        if (aktivPassiv.contains("Passiv")) status = medlemsStatus.PASSIV;
        if (alder < 18) status = medlemsStatus.AKTIV_JUNIOR;
        if (alder >= 18 && alder < 60) status = medlemsStatus.AKTIV_SENIOR;
        if (alder >= 60) status = medlemsStatus.AKTIV_PENSIO;
    }

    protected int medlemsPriser(){
        if (status == medlemsStatus.AKTIV_JUNIOR) saldo = 1000;
        if (status == medlemsStatus.AKTIV_SENIOR) saldo = 1600;
        if (status == medlemsStatus.AKTIV_PENSIO) saldo = 1200;
        if (status == medlemsStatus.PASSIV) saldo = 500;
        return saldo;
    }

    protected void betaltEllerKredit(){
        if (betalingsStatus.equalsIgnoreCase("Betalt")) saldo = 0;
    }

    public String getNavn() {
        return navn;
    }

    public int getAlder() {
        return alder;
    }

    public medlemsStatus getMedlemsStatus() {
        return status;
    }

    public int getSaldo(){
        return saldo;
    }
    public int setSaldo(int saldo){
        this.saldo = saldo;
        return saldo;
    }

    public String getAktivitetsNiveau(){
        return aktivitetsNiveau;
    }

    public static boolean tjekRestance(int brugerID){
        for (Medlem medlem : getMedlemmerIRestance()){
            if (medlem.brugerID == brugerID) {
                return true;
            }
        }
        return false;
    }

    public static List<Medlem> getMedlemmerIRestance() {
        ArrayList <Medlem> list = new ArrayList<Medlem>();
        for (Medlem medlem : medlemmer){
            if (medlem.saldo == 0){ // Restance = saldo = 0
                list.add(medlem);
            }
        }
        return list;
    }

    public String toString(){
        return brugerID+" "+navn + " " + alder +" år - " +status;
    }

    public static void main(String[] args) {
        Medlem a = new Medlem("Julius", LocalDate.of(2000, 01, 29), "Aktiv", "Kredit");
        System.out.println(a + " - "+a.getSaldo()+" kr.");
    }
}