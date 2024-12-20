import org.json.JSONException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Medlem {
    protected int brugerID;
    protected static int noOfAccounts = 1;
    static Scanner scanner = new Scanner(System.in);
    public static List<Medlem> medlemmer = new ArrayList<>();
    protected final String navn;
    protected final int alder;
    protected LocalDate dateOfBirth;
    protected int saldo;
    protected String betalingsStatus;
    String aktivitetsNiveau;
    protected List<String> discipliner = new ArrayList<>();

    protected enum medlemsStatus {AKTIV_JUNIOR, AKTIV_SENIOR, AKTIV_PENSIO, PASSIV}
    protected medlemsStatus status;
    protected String aktivPassiv;

    Medlem(String navn, LocalDate dateOfBirth, String aktivPassiv, String betalingsStatus) {
        brugerID = noOfAccounts;
        noOfAccounts ++;
        this.navn = navn;
        alder = beregnAlder(dateOfBirth).getYears();
        this.aktivPassiv = aktivPassiv;
        this.betalingsStatus = betalingsStatus;
        this.dateOfBirth=dateOfBirth;
        aktivitetsNiveau = "Motionist";
        bestemMedlemsStatus();
        medlemsPriser();
        betaltEllerKredit();

    }

    //Metode til at oprette medlemmer og tilføje dem til Array
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

    // Metode til at slette medlemmer
    public static void deleteMedlem(int brugerID) {
        for (Medlem m : medlemmer){
            if (m.brugerID == brugerID){
                medlemmer.remove(m);
            }
        }
    }

    // Beregn alder baseret på fødselsdato
    protected Period beregnAlder(LocalDate dateOfBirth) {
        LocalDate dagsDato = LocalDate.now();
        Period alder = Period.between(dateOfBirth, dagsDato);
        return alder;
    }

    // Bestem medlemsstatus baseret på alder og aktiv/passiv
    protected void bestemMedlemsStatus() {
        if (aktivPassiv.contains("Passiv")) status = medlemsStatus.PASSIV;
        if (alder < 18) status = medlemsStatus.AKTIV_JUNIOR;
        if (alder >= 18 && alder < 60) status = medlemsStatus.AKTIV_SENIOR;
        if (alder >= 60) status = medlemsStatus.AKTIV_PENSIO;
    }

    // Beregner abonnementsprisen
    protected int medlemsPriser(){
        if (status == medlemsStatus.AKTIV_JUNIOR) saldo = 1000;
        if (status == medlemsStatus.AKTIV_SENIOR) saldo = 1600;
        if (status == medlemsStatus.AKTIV_PENSIO) saldo = 1200;
        if (status == medlemsStatus.PASSIV) saldo = 500;
        return saldo;
    }

    // Opdaterer saldo til 0, hvis abonnement er betalt
    protected void betaltEllerKredit(){
        if (betalingsStatus.equalsIgnoreCase("Betalt")) saldo = 0;
    }

    // Metoder til at finde medlemmer i restance
    public static void getRestanceListe() {
        System.out.println("Følgende medlemmer er i gæld:");
        for (Medlem m : medlemmer) {
            if (m.saldo > 0) System.out.println(m + " Saldo: " + m.saldo);
        }
    }

    // Get-metoder
    public static List<Medlem> getMedlemmerIRestance() {
        List<Medlem> list = new ArrayList<>();
        for (Medlem medlem : medlemmer) {
            if (medlem.saldo > 0) {
                list.add(medlem);
            }
        }
        return list;
    }

    public static boolean tjekRestance(int brugerID){
        for (Medlem medlem : getMedlemmerIRestance()){
            if (medlem.brugerID == brugerID) {
                return true;
            }
        }
        return false;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
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

    public int getSaldo() {
        return saldo;
    }

    public int setSaldo(int saldo) {
        this.saldo = saldo;
        return saldo;
    }

    public String getAktivitetsNiveau() {
        return aktivitetsNiveau;
    }

    // toString metode
    public String toString() {
        return "BrugerID: " + brugerID + " " + navn + " " + alder + " år - " + status;
    }
}