import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetitiveSwimmer extends Medlem{
    private List<String> discipliner;
    protected enum medlemsStatus {AKTIV_JUNIOR, AKTIV_SENIOR}

    public CompetitiveSwimmer(String navn, LocalDate dateOfBirth, String betalingsStatus) {
        super(navn, dateOfBirth, "Aktiv", betalingsStatus);
        this.discipliner = new ArrayList<>();
    }

    protected void bestemMedlemsStatus() {
        if (alder < 18) status = Medlem.medlemsStatus.AKTIV_JUNIOR;
        if (alder >= 18) status = Medlem.medlemsStatus.AKTIV_SENIOR;
    }

    public void tilføjDisciplin(String disciplin) {
        if (!discipliner.contains(disciplin)){
            discipliner.add(disciplin);
        } else {
            System.out.println("Den valgte disciplin er allerede tilføjet.");
        }
    }

    public void fjernDisciplin(String disciplin) {
        if (discipliner.contains(disciplin)){
            discipliner.remove(disciplin);
        } else {
            System.out.println("Den valgte disciplin er ikke tilføjet til svømmeren.");
        }
    }

    public List<String> getDiscipliner(){
        return discipliner;
    }

    @Override
    public String toString() {
        return super.toString() + "\t Discipliner: " + discipliner;
    }

    public static void main(String[] args) {
        CompetitiveSwimmer a = new CompetitiveSwimmer("Lars Testersen", LocalDate.of(2000,1,29), "BETALT");
        a.tilføjDisciplin("Bryst");
        a.tilføjDisciplin("Crawl");
        System.out.println(a);
        a.fjernDisciplin("Crawl");
        System.out.println(a);
    }
}
