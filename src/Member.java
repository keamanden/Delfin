

//Member class is the main object of the program and the program revolves around the member and data related to it


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;



public class Member {

    //Attributes for the member object.

    private int idNumber;
    public String dateOfBirth;
    private Membershiptype membershipType; //Enum from enum class
    private SvimmingDisciplin svimmingDisciplin; //Enum from enum class
    private LocalDate quotaPaid;
    private Boolean competitionSwimmer;
    private boolean isActive;
    private static ArrayList<TrainingTimes> trainingTimes;
    private static ArrayList<Competition> competitions;


    public static ArrayList<Member> juniorSvimmers = new ArrayList<>();
    public static ArrayList<Member> seniorSvimmers = new ArrayList<>();
    public static ArrayList<Member> members = new ArrayList<>();




    public void addMember(Member member) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("Indtast medlemsnummer på nyt medlem som skal være fire cifre og må ikke starte med 0");
        setIdNumber(scanner.nextInt());

        scanner.nextLine();

        System.out.println("Indtast fødselsdato på nyt medle i form DD-MM-YYYY");
        setDateOfBirth(scanner.nextLine());

        //Selecting membership type

        System.out.println("vælg Medlemskab:" +" indtast \"1\"" + " eller " + "\"2\"" +
                "\n 1. Aktivt" +
                "\n 2. Passivt");

        int userInput = scanner.nextInt();

        switch (userInput) {

            case 1: setMembershipType(Membershiptype.ACTIVE);  break;

            case 2: setMembershipType(Membershiptype.PASSIVE) ; break;

        }

        //Selecting swimming disciplin

        System.out.println("vælg hvilken svømmedisciplin som medlemmet svømmer:" + " indtast \"1\"" + " eller " + "\"2\"" + " eller " + "\"3\"" + " eller " + "\"4\"" +
                "\n 1. Butterfly" +
                "\n 2. Crawl" +
                "\n 3. Backcrawl" +
                "\n 4. Breaststroke" );

        userInput = scanner.nextInt();

        switch (userInput) {
            case 1: setSvimmingDisciplin(SvimmingDisciplin.BUTTERFLY) ; break;

            case 2: setSvimmingDisciplin(SvimmingDisciplin.CRAWL); break;

            case 3: setSvimmingDisciplin(SvimmingDisciplin.BACKCRAWL); break;

            case 4: setSvimmingDisciplin(SvimmingDisciplin.BREASTSTROKE); break;
        }

        setQuotaPaid(LocalDate.now());

        System.out.println("Er det nye medlem konkurrencesvømmer? indtast \"J\" for Ja eller \"N\" for nej");

        if (scanner.next().equalsIgnoreCase("J")) {
            setCompetitionSwimmer(true);
        } else {
            setCompetitionSwimmer(false);
        }
        scanner.nextLine();


        members.add(member);
        if(member.competitionSwimmer) {
            addToJuniorOrSeniorArrayList(member);
        }

    }


    public void addToJuniorOrSeniorArrayList(Member member) {

        addToJuniorArrayList(member);
        addToSeniorArrayList(member);

    }


    public void addToJuniorArrayList(Member member) {

        String yearFormat = "dd-MM-yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(yearFormat);

        LocalDate today = LocalDate.now();
        if(Period.between(LocalDate.parse(member.dateOfBirth, formatter), today).getYears() < 18 ) {
            juniorSvimmers.add(member);       }
    }

    public void addToSeniorArrayList(Member member) {

        String yearFormat = "dd-MM-yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(yearFormat);
        LocalDate today = LocalDate.now();
        if(Period.between(LocalDate.parse(member.dateOfBirth, formatter), today).getYears() >= 18 ) {
            seniorSvimmers.add(member);
        }
    }





    //Constructor for member
    public Member(int idNumber, String dateOfBirth, Membershiptype membershipType, SvimmingDisciplin svimmingDisciplin,
                  LocalDate quotaPaid, Boolean competitionSwimmer, ArrayList<TrainingTimes> trainingTimes, ArrayList<Competition> competitions)
    {

        this.idNumber = idNumber;
        this.dateOfBirth = dateOfBirth;
        this.membershipType = membershipType;
        this.svimmingDisciplin = svimmingDisciplin;
        this.quotaPaid = quotaPaid;
        this.competitionSwimmer = competitionSwimmer;
        this.trainingTimes = trainingTimes;
        this.competitions = competitions;


    }

    public Member() {

    }


    //Setters

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setQuotaPaid(LocalDate date) {
        this.quotaPaid = date;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setMembershipType(Membershiptype membershipType) {
        this.membershipType = membershipType;
    }
    public void setSvimmingDisciplin(SvimmingDisciplin svimmingDisciplin) {
        this.svimmingDisciplin = svimmingDisciplin;
    }
    public void setQuotaPaid() {
        this.quotaPaid = quotaPaid;
    }
    public void setCompetitionSwimmer(Boolean competitionSwimmer) {
        this.competitionSwimmer = competitionSwimmer;
    }
    public void setTrainingTimes(ArrayList<TrainingTimes> trainingTimes) {
        this.trainingTimes = trainingTimes;
    }
    public void setCompetitions(ArrayList<Competition> competitions) {
        this.competitions = competitions;
    }


    //Getters
    public int getIdNumber() {
        return idNumber;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public Membershiptype getMembershipType() {
        return membershipType;
    }
    public SvimmingDisciplin getSvimmingDisciplin() {
        return svimmingDisciplin;
    }
    public LocalDate getQuotaPaid() {
        return quotaPaid;
    }
    public Boolean getCompetitionSwimmer() {
        return competitionSwimmer;
    }
    public ArrayList<TrainingTimes> getTrainingTimes() {
        return trainingTimes;
    }
    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }


//    public static void testObj(){
//        Member mem1 = new Member(10,121299,)
//    }

    //Ovverride function for competitive and non competitve svimmers

    public void displayMember() {
        System.out.println(toString());

        if (competitionSwimmer && !competitions.isEmpty()) {
            for (Competition competition : competitions) {
                System.out.println(competition.toString());
            }

            if (!trainingTimes.isEmpty()) {
                for (TrainingTimes trainingTime : trainingTimes) {
                    System.out.println(trainingTime.toString());
                }
            }
        }
    }

    @Override
    public String toString() {

        String comp = competitionSwimmer ? "ja" : "nej";

            return

             "Id nummer på medlem" + this.idNumber +
                    "\nFødslelsdato for medlem: " + this.dateOfBirth +
                    "\nMedlemstype for medlem: " + this.membershipType +
                    "\nSvømmediscipling for medlem: " + this.svimmingDisciplin +
                    "\nDato for kontigent betaling: " + this.quotaPaid +
                    "\nmedlemmet er konkurrencesvømmer: " + comp +
                    "\nmedlemmet har følgende træningstider: " + this.trainingTimes +
                    "\nmedlemmet har følgende konkurrencetider: " + this.competitions;


    }

}
