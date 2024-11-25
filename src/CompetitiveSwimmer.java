import java.util.ArrayList;
import java.util.List;

public class CompetitiveSwimmer{
    private List<String> discipliner;

    public CompetitiveSwimmer(String navn) {
        super();
        this.discipliner = new ArrayList<>();
    }

    public void tilføjDisciplin(String disciplin) {
        discipliner.add(disciplin);
    }

    @Override
    public String toString() {
        return super.toString() + ", Discipliner: " + discipliner;
    }
}
