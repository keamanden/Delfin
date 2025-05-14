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
    public static TrainingTimes addTrainingTime() {
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Indtast medlems-ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // rydder newline

            Member foundMember = null;
            for (Member m : Member.members) {
                if (m.getIdNumber() == id) {
                    foundMember = m;
                    break;
                }
            }

            if (foundMember == null) {
                System.out.println("Ingen medlem med ID " + id + " blev fundet.");
                return addTrainingTime();
            }
            SvimmingDisciplin disciplin = foundMember.getSvimmingDisciplin();
            String fødselsdato = foundMember.getDateOfBirth();

            System.out.println("Fundet medlem:");
            System.out.println("Navn/ID: " + id);
            System.out.println("Disciplin: " + disciplin);
            System.out.println("Fødselsdato: " + fødselsdato);

            // 3. Indtast tid
            System.out.print("Indtast svømmetid i sekunder: ");
            int swimTime = scanner.nextInt();

            // 4. Opret og tilføj træningstid
            TrainingTimes nyTid = new TrainingTimes(disciplin, swimTime);

            if (foundMember.getTrainingTimes() == null) {
                foundMember.setTrainingTimes(new ArrayList<>());
            }

            foundMember.getTrainingTimes().add(nyTid);

            System.out.println("Tid tilføjet: " + disciplin + " - " + swimTime + " sekunder");

            return nyTid;
        }
    }
}