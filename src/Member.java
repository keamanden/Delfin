

//Member class is the main object of the program and the program revolves around the member and data related to it


import java.time.LocalDate;
import java.util.ArrayList;

public class Member {

    //Attributes for the member object.

    private int idNumber;
    private String dateOfBirth;
    private Membershiptype membershipType; //Enum from enum class
    private SvimmingDisciplin svimmingDisciplin; //Enum from enum class
    private LocalDate quotaPaid;
    private Boolean competitionSwimmer;
    private static ArrayList<TrainingTimes> trainingTimes;
    private static ArrayList<Competition> competitions;





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


    //Setters
    public void setIdNumber() {
        this.idNumber = idNumber;
    }
    public void setDateOfBirth() {
        this.dateOfBirth = dateOfBirth;
    }
    public void setMembershipType(Membershiptype membershipType) {
        this.membershipType = membershipType;
    }
    public void setSvimmingDisciplin() {
        this.svimmingDisciplin = svimmingDisciplin;
    }
    public void setQuotaPaid() {
        this.quotaPaid = quotaPaid;
    }
    public void setCompetitionSwimmer() {
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

    public static void testObj(){
        Member mem1 = new Member(10,121299,)
    }

}
