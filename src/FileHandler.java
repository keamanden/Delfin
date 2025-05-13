import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String FILE_NAME = "members.txt";

    // Metode til at skrive medlemmer til fil
    public static void writeToFile(List<Member> members) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Member member : members) {
                writer.write(memberToString(member));
                writer.newLine();
            }
            System.out.println("Medlemsdata er blevet gemt til filen.");
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }

    // Metode til at læse medlemmer fra fil
    public static List<Member> readFromFile() {
        List<Member> members = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Member member = stringToMember(line);
                if (member != null) {
                    members.add(member);
                }
            }
            System.out.println("Medlemsdata er blevet læst fra filen.");
        } catch (IOException e) {
            System.out.println("Fejl ved læsning fra fil: " + e.getMessage());
        }
        return members;
    }

    // Hjælpemetode til at konvertere et medlem til en streng
    private static String memberToString(Member member) {
        return member.getIdNumber() + "," +
                member.getDateOfBirth() + "," +
                member.getMembershipType() + "," +
                member.getSvimmingDisciplin() + "," +
                member.getQuotaPaid() + "," +
                member.getCompetitionSwimmer();
    }

    // Hjælpemetode til at konvertere en streng til et medlem
    private static Member stringToMember(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            System.out.println("Ugyldigt dataformat: " + line);
            return null;
        }
        try {
            int idNumber = Integer.parseInt(parts[0]);
            String dateOfBirth = parts[1];
            Membershiptype membershipType = Membershiptype.valueOf(parts[2]);
            SvimmingDisciplin svimmingDisciplin = SvimmingDisciplin.valueOf(parts[3]);
            LocalDate quotaPaid = LocalDate.parse(parts[4]);
            boolean competitionSwimmer = Boolean.parseBoolean(parts[5]);

            Member member = new Member();
            member.setIdNumber(idNumber);
            member.setDateOfBirth(dateOfBirth);
            member.setMembershipType(membershipType);
            member.setSvimmingDisciplin(svimmingDisciplin);
            member.setQuotaPaid(quotaPaid);
            member.setCompetitionSwimmer(competitionSwimmer);

            return member;
        } catch (Exception e) {
            System.out.println("Fejl ved konvertering af data: " + e.getMessage());
            return null;
        }
    }
}

