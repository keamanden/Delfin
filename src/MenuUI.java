import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
public class MenuUI {

    private ArrayList<Member> delayedPayment = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    static boolean running = true;



    public void displayMenu(){
        System.out.println("===== MENU =====");
        System.out.println("1. Tilføj medlem");
        System.out.println("2. Fjern medlem");
        System.out.println("3. Registrer kontingentbetaling");
        System.out.println("4. Tjek restancer");
        System.out.println("5. Tilføj træningstider");
        System.out.println("6. Tilføj konkurrence");
        System.out.println("7. Vis top 5 svømmere");
        System.out.println("8. Opdater medlemsstatus");
        System.out.println("9. Afslut");
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
            case 7 -> displayTop5Svimmers();
            case 8 -> setMemberStatus(Member.members);
            case 9 -> {
                System.out.println("Afslutter programmet...");
                running = false;
            }
            default -> System.out.println("Ugyldigt valg. Prøv igen.");
        }


    }

    public void addMember(){
        Member member = new Member();
        member.addMember(member);
    }

    public void removeMember(){

    }



    public void makeQuotaPayment(ArrayList<Member> members){
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Indtast medlems-ID: ");
            int inputId = scanner.nextInt();
            scanner.nextLine(); // Fanger newline

            Member member = null;

            for (Member m : members) {
                if (m.getIdNumber() == inputId) {
                    member = m;
                    break;
                }
            }

            if (member == null) {
                System.out.println("Medlem med ID " + inputId + " blev ikke fundet.");
                return;
            }

            // Fødselsdato antages at være i format "YYYY-MM-DD"
            LocalDate birthDate = LocalDate.parse(member.getDateOfBirth());
            int age = Period.between(birthDate, LocalDate.now()).getYears();

            double amountToPay;
            if (age < 18) {
                amountToPay = 1000;
            } else if (age >= 60) {
                amountToPay = 1600 * 0.75; // 25% rabat
            } else {
                amountToPay = 1600;
            }

            System.out.println("Alder: " + age + " år");
            System.out.println("Kontingent at betale: " + amountToPay + " kr.");
            member.setQuotaPaid(LocalDate.now());
            System.out.println("Betaling registreret den: " + LocalDate.now());

        } catch (InputMismatchException e) {
            System.out.println("Fejl: Du skal indtaste et helt tal som ID.");
        } catch (DateTimeParseException e) {
            System.out.println("Fejl: Fødselsdatoen er ikke i korrekt format (YYYY-MM-DD).");
        } catch (Exception e) {
            System.out.println("Uventet fejl: " + e.getMessage());
        }


    }

    public void checkArrears(){
            //Iterator bruges der her så man kan kører vores arrayliste igennem og fjerne elementer sikkert.
            Iterator<Member> iterator = Member.members.iterator();
            LocalDate today = LocalDate.now();

            while (iterator.hasNext()) {
                Member member = iterator.next();

                // Hvis kontingentdato er mere end 1 år gammel
                if (member.getQuotaPaid().isBefore(today.minusYears(1))) {
                    delayedPayment.add(member);     // flyt til restance-liste
                    iterator.remove();              // fjern fra den normale medlemsliste
                }
            }

            // Udskriv resultatet
            System.out.println("Følgende medlemmer er i restance og er flyttet til listen 'delayedPayment':");
            for (Member m : delayedPayment) {
                System.out.println("ID: " + m.getIdNumber() + " - Sidst betalt: " + m.getQuotaPaid());
            }
        }


    public void addTrainingTimes(){

    }

    public void addCompetition(){

    }

    public void displayTop5Svimmers(){

    }

    public void setMemberStatus(ArrayList<Member> members){
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
        String currentStatus = foundMember.isActive() ? "AKTIV" : "PASSIV";
        System.out.println("Medlem med ID " + id + " er nuværende: " + currentStatus);

        // Giv mulighed for at ændre
        System.out.print("Ønsker du at ændre status? (ja/nej): ");
        String svar = scanner.nextLine().trim().toLowerCase();

        if (svar.equals("ja")) {
            foundMember.setActive(!foundMember.isActive()); // Skift status
            String newStatus = foundMember.isActive() ? "AKTIV" : "PASSIV";
            System.out.println("Status ændret. Medlem er nu: " + newStatus);
        } else {
            System.out.println("Status blev ikke ændret.");
        }
    }


    }



