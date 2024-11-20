import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class medlem {
    protected final String navn;
    protected final int alder;
    protected int saldo;
    protected String betalingsStatus;
    static ArrayList<medlem> restanceListe = new ArrayList<>();
    protected enum medlemsStatus {AKTIV_JUNIOR, AKTIV_SENIOR, AKTIV_PENSIO, PASSIV}
    protected medlemsStatus status;
    protected String aktivPassiv;

    medlem(String navn, LocalDate dateOfBirth, String aktivPassiv, String betalingsStatus) {
        this.navn = navn;
        alder = beregnAlder(dateOfBirth).getYears();
        this.aktivPassiv = aktivPassiv;
        this.betalingsStatus = betalingsStatus;
        bestemMedlemsStatus();
        medlemsPriser();
        betaltEllerKredit();
    }

    protected Period beregnAlder(LocalDate dateOfBirth) {
        LocalDate dagsDato = LocalDate.now();
        Period alder = Period.between(dateOfBirth, dagsDato);
        return alder;
    }

    protected void bestemMedlemsStatus() {
        if (aktivPassiv.contains("Passiv")) status = medlemsStatus.PASSIV;
        if (alder < 18) status = medlemsStatus.AKTIV_JUNIOR;
        if (alder > 18 && alder < 60) status = medlemsStatus.AKTIV_SENIOR;
        if (alder > 60) status = medlemsStatus.AKTIV_PENSIO;
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
        if (betalingsStatus.equalsIgnoreCase("kredit")) restanceListe.add(this);
    }
    public String getBetalingsStatus() {
        if (betalingsStatus.equalsIgnoreCase("betalt")){
            return navn+" har ingen udestående betalinger. Saldoen ligger på: "+saldo;
        }else return navn + " mangler at betale: "+saldo;
    }
    public List getRestanceListe(){
        return restanceListe;
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
        medlem a = new medlem("Julius", LocalDate.of(2000, 01, 29), "Aktiv", "Kredit");
        medlem a1 = new medlem("Gulius", LocalDate.of(2000, 01, 29), "Aktiv", "Kredit");
        medlem a2 = new medlem("Pulius", LocalDate.of(2000, 01, 29), "Aktiv", "betalt");

        System.out.println(a + " - "+a.getSaldo()+" kr.");
        System.out.println(restanceListe);

    }
}