import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;

public class MenuUI {

    private ArrayList<Member> delayedPayment = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    static boolean running = true;


    public void displayMenu() {
        System.out.println("\uD83D\uDC2C===== MENU =====\uD83D\uDC2C");
        System.out.println("1. Tilføj medlem\uD83C\uDFCA");
        System.out.println("2. Fjern medlem❌");
        System.out.println("3. Registrer kontingentbetaling\uD83D\uDCB0");
        System.out.println("4. Tjek restancer");
        System.out.println("5. Tilføj træningstider");
        System.out.println("6. Tilføj konkurrence");
        System.out.println("7. Tilføj medlemmer til konkurrence");
        System.out.println("8. Se deltager i konkurrencer");
        System.out.println("9. Vis top 5 svømmere");
        System.out.println("10. Opdater medlemsstatus");
        System.out.println("11. Afslut");
        System.out.print("Vælg et nummer: ");

        int choice;
        while (!scanner.hasNextInt()) {
            System.out.print("Ugyldigt input. Indtast et tal: ");
            scanner.next();
        }
        choice = scanner.nextInt();

        switch (choice) {
            case 1 -> addMember();
            case 2 -> removeMember();
            case 3 -> makeQuotaPayment(Member.members);
            case 4 -> checkArrears();
            case 5 -> addTrainingTimes();
            case 6 -> addCompetition();
            case 7 -> assignToCompetition();
            case 8 -> showParticipantsInCompetition();
            case 9 -> displayTop5Svimmers();
            case 10 -> setMemberStatus(Member.members);
            case 11 -> {
                System.out.println("Afslutter programmet...");
                running = false;
            }
            default -> System.out.println("Ugyldigt valg. Prøv igen.");
        }


    }

    public void addMember() {
        Member member = new Member();
        member.addMember(member);
    }

    public void removeMember() {

    }


    public void makeQuotaPayment(ArrayList<Member> members) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Indtast ID for medlem som skal betale kontingent:");
            int id = scanner.nextInt();

            Member foundMember = null;
            for (Member member : members) {
                if (member.getIdNumber() == id) {
                    foundMember = member;
                    break;
                }
            }

            if (foundMember == null) {
                System.out.println("Medlem med ID " + id + " blev ikke fundet.");
                return;
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate birthDate = LocalDate.parse(foundMember.getDateOfBirth(), formatter);
                int age = Period.between(birthDate, LocalDate.now()).getYears();

                double amountToPay;
                if (foundMember.getMembershipType() == Membershiptype.PASSIVE ) {
                    amountToPay = 500;
                } else {
                    if (age < 18) {
                        amountToPay = 1000;
                    } else if (age >= 60) {
                        amountToPay = 1600 * 0.75;
                    } else {
                        amountToPay = 1600;
                    }
                }
                System.out.println("Alder: " + age + " år");
                System.out.println("Kontingent at betale: " + amountToPay + " kr.");
                foundMember.setQuotaPaid(LocalDate.now());
                System.out.println("Betaling registreret den: " + LocalDate.now().format(formatter));

            } catch (DateTimeParseException e) {
                System.out.println("Fejl: Fødselsdatoen er ikke i korrekt format (DD-MM-YYYY).");
            }
        }




    public void checkArrears() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            Iterator<Member> iterator = Member.members.iterator();
            LocalDate today = LocalDate.now();

            while (iterator.hasNext()) {
                Member member = iterator.next();

                if (member.getQuotaPaid().isBefore(today.minusYears(1))) {
                    delayedPayment.add(member);
                    iterator.remove();
                }
            }

            if (delayedPayment.isEmpty()) {
                System.out.println("Ingen medlemmer i restance.");
            } else {
                System.out.println("Følgende medlemmer er i restance og er flyttet til listen 'delayedPayment':");
                for (Member m : delayedPayment) {
                    String formattedDate = m.getQuotaPaid().format(formatter);
                    System.out.println("ID: " + m.getIdNumber() + " - Sidst betalt: " + formattedDate);
                }
            }

    }


    public void addTrainingTimes() {
     TrainingTimes.addTrainingTime();
    }

    public void addCompetition() {
        Competition.createCompetition();

    }



    public void assignToCompetition() {
        // Skaber en scanner til input
        Scanner scanner = new Scanner(System.in);

        // Kalder på den metode, der tildeler et medlem til en konkurrence
        Competition.showParticipants();  // Dette kalder den metode, du har defineret tidligere

        // Kan tilføje besked her, hvis nødvendigt
        System.out.println("Medlemmet blev tilmeldt konkurrencen.");
    }




    public void showParticipantsInCompetition() {
        if (Competition.competitions.isEmpty()) {
            System.out.println("Ingen konkurrencer oprettet.");
            return;
        }

        System.out.println("Vælg en konkurrence for at se deltagerne:");
        for (int i = 0; i < Competition.competitions.size(); i++) {
            System.out.println((i + 1) + ". " + Competition.competitions.get(i).getCompetitionName());
        }

        scanner.nextLine();
        String input = scanner.nextLine();

        if (input.isEmpty()) {
            System.out.println("Du indtastede ikke noget. Prøv igen.");
            return;
        }

        int choice;
        try {
            choice = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt input. Du skal indtaste et tal.");
            return;
        }

        if (choice < 0 || choice >= Competition.competitions.size()) {
            System.out.println("Ugyldigt valg.");
            return;
        }

        Competition.competitions.get(choice).printParticipants();
    }





    public static void displayTop5Svimmers() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vælg svømmedisciplin:");
        System.out.println("1. Butterfly\n2. Crawl\n3. Backcrawl\n4. Breaststroke");
        int valg = scanner.nextInt();
        SvimmingDisciplin valgtDisciplin = switch (valg) {
            case 1 -> SvimmingDisciplin.BUTTERFLY;
            case 2 -> SvimmingDisciplin.CRAWL;
            case 3 -> SvimmingDisciplin.BACKCRAWL;
            case 4 -> SvimmingDisciplin.BREASTSTROKE;
            default -> {
                System.out.println("Ugyldigt valg. ");
                yield SvimmingDisciplin.CRAWL;
            }
        };

        // Saml alle relevante tider
        List<TrainingRecord> alleTider = new ArrayList<>();

        for (Member m : Member.members) {
            if (m.getTrainingTimes() != null) {
                for (TrainingTimes t : m.getTrainingTimes()) {
                    alleTider.add(new TrainingRecord(m.getIdNumber(), t.getSvimTime(), t.getSvimmingDisciplin()));
                }
            }
        }

        // Filtrer og sorter tider KUN for den valgte disciplin
        List<TrainingRecord> filtreretTider = alleTider.stream()
                .filter(r -> r.disciplin == valgtDisciplin)
                .sorted(Comparator.comparingInt(r -> r.time))
                .collect(Collectors.toList());

        // Udskriv top 5
        System.out.println("\n--- Top 5 svømmere i disciplinen: " + valgtDisciplin + " ---");
        for (int i = 0; i < Math.min(5, filtreretTider.size()); i++) {
            TrainingRecord r = filtreretTider.get(i);
            System.out.println((i + 1) + ". ID: " + r.memberId + " - Tid: " + r.time + " sek.");
        }

        if (filtreretTider.isEmpty()) {
            System.out.println("Ingen tider fundet for denne disciplin.");
        }
    }

    // Hjælpeklasse til at holde ID + tid + disciplin sammen
    static class TrainingRecord {
        int memberId;
        int time;
        SvimmingDisciplin disciplin;

        public TrainingRecord(int memberId, int time, SvimmingDisciplin disciplin) {
            this.memberId = memberId;
            this.time = time;
            this.disciplin = disciplin;
        }
    }






    public void setMemberStatus(ArrayList<Member> members) {

        System.out.print("Indtast ID på medlemmet: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // rydder buffer

        Member foundMember = null;

        for (Member m : members) {
            if (m.getIdNumber() == id) {
                foundMember = m;
                break;
            }
        }

        if (foundMember == null) {
            System.out.println("Ingen medlem fundet med ID: " + id);
            return;
        }

        // Vis nuværende status
        System.out.println("Medlem med ID " + id + " har medlemskab: " + foundMember.getMembershipType());

        // Spørg om ny status
        System.out.println("Vælg hvilken slags medlemskab medlemmet skal have:" +
                "\n1. Aktivt" +
                "\n2. Passivt" +
                "\nIndtast \"1\" eller \"2\":");

        int userInput = scanner.nextInt();
        scanner.nextLine();

        switch (userInput) {
            case 1 -> {
                foundMember.setMembershipType(Membershiptype.ACTIVE);
                System.out.println("Medlemmet er nu AKTIVT medlem.");
            }
            case 2 -> {
                foundMember.setMembershipType(Membershiptype.PASSIVE);
                System.out.println("Medlemmet er nu PASSIVT medlem.");
            }
            default -> System.out.println("Ugyldigt valg. Ingen ændring foretaget.");
        }
    }


    }




