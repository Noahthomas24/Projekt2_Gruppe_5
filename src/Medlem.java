import java.time.LocalDate;
import java.time.Period;

public class Medlem {
    protected final String navn;
    protected final int alder;
    protected LocalDate dateOfBirth;
    protected int saldo;
    protected String betalingsStatus;

    protected enum medlemsStatus {AKTIV_JUNIOR, AKTIV_SENIOR, AKTIV_PENSIO, PASSIV}
    protected medlemsStatus status;
    protected String aktivPassiv;

    Medlem(String navn, LocalDate dateOfBirth, String aktivPassiv, String betalingsStatus) {
        this.navn = navn;
        alder = beregnAlder(dateOfBirth).getYears();
        this.aktivPassiv = aktivPassiv;
        this.betalingsStatus = betalingsStatus;
        this.dateOfBirth=dateOfBirth;
        bestemMedlemsStatus();
        medlemsPriser();
        betaltEllerKredit();
    }

    protected Period beregnAlder(LocalDate dateOfBirth) {
        LocalDate dagsDato = LocalDate.now();
        Period alder = Period.between(dateOfBirth, dagsDato);
        return alder;
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

    public String toString(){
        return navn + " " + alder +" år - " +status;
    }

    public static void main(String[] args) {
        Medlem a = new Medlem("Julius", LocalDate.of(2000, 01, 29), "Aktiv", "Kredit");
        System.out.println(a + " - "+a.getSaldo()+" kr.");
    }
}