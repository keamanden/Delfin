import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Competition {
    String competitionName;
    String competitionAddress;
    String competitionTimeAndDate;
    SvimmingDisciplin svimmingDisciplin;

    public static ArrayList<Competition> competitions = new ArrayList<>();

    public ArrayList<Member> participants = new ArrayList<>();


    public Competition(String competitionName, String competitionAddress, String competitionTimeAndDate, SvimmingDisciplin svimmingDisciplin) {
        this.competitionName = competitionName;
        this.competitionAddress = competitionAddress;
        this.competitionTimeAndDate = competitionTimeAndDate;
        this.svimmingDisciplin = svimmingDisciplin;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getCompetitionAddress() {
        return competitionAddress;
    }

    public String getCompetitionTimeAndDate() {
        return competitionTimeAndDate;
    }

    public SvimmingDisciplin getSvimmingDisciplin() {
        return svimmingDisciplin;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setCompetitionAddress(String CompetitionAddress) {
        this.competitionAddress = competitionAddress;
    }

    public void setCompetitionTimeAndDate(String CompetitionTimeAndDate) {
        this.competitionTimeAndDate = competitionTimeAndDate;
    }

    public void setSvimmingDisciplin(SvimmingDisciplin svimmingDisciplin) {
        this.svimmingDisciplin = svimmingDisciplin;
    }


    @Override
    public String toString() {
        return "Konkurrence: " + competitionName +
                "\nAdresse: " + competitionAddress +
                "\nTid og dato: " + competitionTimeAndDate +
                "\nDisciplin: " + svimmingDisciplin;
    }


    public static Competition createCompetition() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Indtast navn på konkurrencen:");
        String name = scanner.nextLine();

        System.out.println("Indtast adresse på konkurrencen:");
        String address = scanner.nextLine();

        System.out.println("Indtast tidspunkt og dato på konkurrencen (fx 01-06-2025 14:00):");
        String timeAndDate = scanner.nextLine();

        System.out.println("Vælg svømmedisciplin:");
        System.out.println("1. BUTTERFLY");
        System.out.println("2. CRAWL");
        System.out.println("3. BACKCRAWL");
        System.out.println("4. BREASTSTROKE");

        SvimmingDisciplin disciplin = null;

        try {
            int disciplineChoice = scanner.nextInt();
            scanner.nextLine(); // For at fange linjeskift

            switch (disciplineChoice) {
                case 1 -> disciplin = SvimmingDisciplin.BUTTERFLY;
                case 2 -> disciplin = SvimmingDisciplin.CRAWL;
                case 3 -> disciplin = SvimmingDisciplin.BACKCRAWL;
                case 4 -> disciplin = SvimmingDisciplin.BREASTSTROKE;
                default -> System.out.println("Ugyldigt valg af disciplin.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Fejl: Du skal indtaste et tal mellem 1 og 4.");
            scanner.nextLine(); // rydder buffer efter forkert input
        }

        if (disciplin == null) {
            System.out.println("Konkurrence kunne ikke oprettes. Manglende eller ugyldig disciplin.");
            return null;
        }

        //Her så oprette jeg competition til arraylisten, så jeg senere kan adde members til den
        Competition competition = new Competition(name, address, timeAndDate, disciplin);
        competitions.add(competition);
        System.out.println("Konkurrence oprettet og gemt.");

        return competition;

    }


    public void addParticipant(Member member) {
        if (!participants.contains(member)) {
            participants.add(member);
            // Også tilføj konkurrencen til medlemmet
            member.getCompetitions().add(this);
            System.out.println("Medlem tilføjet til konkurrence.");
        } else {
            System.out.println("");
        }

    }


    public static void showParticipants() {
        Scanner scanner = new Scanner(System.in);

        if (competitions.isEmpty()) {
            System.out.println("Ingen konkurrencer oprettet endnu.");
            return;
        }

        System.out.println("Vælg konkurrence:");
        for (int i = 0; i < competitions.size(); i++) {
            System.out.println((i + 1) + ". " + competitions.get(i).getCompetitionName());
        }

        int compIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // 🧼 Ryd new line efter nextInt()

        if (compIndex < 0 || compIndex >= competitions.size()) {
            System.out.println("Ugyldigt valg.");
            return;
        }

        Competition selectedCompetition = competitions.get(compIndex);

        if (Member.members.isEmpty()) {
            System.out.println("Ingen medlemmer oprettet endnu.");
            return;
        }

        System.out.println("Vælg medlem:");
        for (int i = 0; i < Member.members.size(); i++) {
            System.out.println((i + 1) + ". ID: " + Member.members.get(i).getIdNumber());
        }

        int memIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (memIndex < 0 || memIndex >= Member.members.size()) {
            System.out.println("Ugyldigt valg.");
            return;
        }

        Member selectedMember = Member.members.get(memIndex);

        if (!selectedMember.getCompetitionSwimmer()) {
            System.out.println("Dette medlem er ikke konkurrencesvømmer og kan ikke tilmeldes.");
            return;
        }

        selectedCompetition.addParticipant(selectedMember);
    }


    public void printParticipants() {
        if (participants.isEmpty()) {
            System.out.println("Ingen deltagere i konkurrencen " + competitionName);
            return;
        }

        System.out.println("Deltagere i konkurrencen " + competitionName + ":");
        for (Member m : participants) {
            System.out.println("ID: " + m.getIdNumber() + " - Dato: " + m.getDateOfBirth());
            System.out.println("Swimming disciplin: " + getSvimmingDisciplin() +
                    " - " + " Adresse: " + getCompetitionAddress());
        }
    }


}


