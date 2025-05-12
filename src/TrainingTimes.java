import java.util.ArrayList;
import java.util.Scanner;

public class TrainingTimes {
    SvimmingDisciplin svimmingDisciplin;
    int svimTime;

    public TrainingTimes(SvimmingDisciplin svimmingDisciplin, int svimTime) {
        this.svimmingDisciplin = svimmingDisciplin;
        this.svimTime = svimTime;
    }

    public SvimmingDisciplin getSvimmingDisciplin() {
        return svimmingDisciplin;
    }

    public int getSvimTime() {
        return svimTime;
    }

    public void setSwimTime(int svimTime) {
        this.svimTime = svimTime;
    }

    public void setSvimmingDisciplin(SvimmingDisciplin svimmingDisciplin) {
        this.svimmingDisciplin = svimmingDisciplin;
    }

    @Override
    public String toString() {
        return svimmingDisciplin + " - " + svimTime + " sekunder";
    }

    // Statisk metode til at oprette et nyt træningsresultat
    public static TrainingTimes createTrainingTime() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vælg svømmedisciplin:");
        System.out.println("1. Butterfly\n2. Crawl\n3. Backcrawl\n4. Breaststroke");
        int disciplinValg = scanner.nextInt();
        SvimmingDisciplin disciplin = switch (disciplinValg) {
            case 1 -> SvimmingDisciplin.BUTTERFLY;
            case 2 -> SvimmingDisciplin.CRAWL;
            case 3 -> SvimmingDisciplin.BACKCRAWL;
            case 4 -> SvimmingDisciplin.BREASTSTROKE;
            default -> {
                System.out.println("Ugyldigt valg. Standardvælger Crawl.");
                yield SvimmingDisciplin.CRAWL;
            }
        };

        System.out.print("Indtast svømmetid i sekunder: ");
        int swimTime = scanner.nextInt();

        return new TrainingTimes(disciplin, swimTime);
    }
}