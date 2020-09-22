import java.util.Scanner;

public class BotCore {
    public static String input() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static void output(Object message) {
        System.out.println(message);
    }


}
