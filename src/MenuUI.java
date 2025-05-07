import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
public class MenuUI {

    private ArrayList<Member> delayedPayment = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    boolean running = true;


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
            case 3 -> makeQuotaPayment();
            case 4 -> checkArrears();
            case 5 -> addTrainingTimes();
            case 6 -> addCompetition();
            case 7 -> displayTop5Svimmers();
            case 8 -> setMemberStatus();
            case 9 -> {
                System.out.println("Afslutter programmet...");
                running = false;
            }
            default -> System.out.println("Ugyldigt valg. Prøv igen.");
        }
        scanner.close();

    }






    public void addMember(){
        Member member = new Member();
        member.addMember(scanner);
    }

    public void removeMember(){

    }

    public void makeQuotaPayment(){

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

    public void setMemberStatus(){

    }
}
