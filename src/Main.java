import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        MenuUI menu = new MenuUI();

        Member member1 = new Member(2222, "02-02-2002", Membershiptype.ACTIVE, SvimmingDisciplin.BUTTERFLY,
                LocalDate.parse("2020-03-03"), true, new ArrayList<TrainingTimes>(),  new ArrayList<Competition>());
        Member.members.add(member1);



        while(MenuUI.running) {

            menu.displayMenu();





        }


        }
    }

