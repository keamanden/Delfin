import java.util.Arrays;
import java.util.Scanner;

public class Main {


    //Brugt til test, må gerne slettes.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        addMember(scanner);
    }

    public static void addMember(Scanner scanner) {
        Member member = new Member();
        member.addMember(scanner);
        System.out.println(member);

    }

}
