package part01;

/**
 * A tester class for the Menu class
 * @author Andrew Ellis
 * @version V1.0
 */
public class MenuTester {
    public static String menuOptions[] = {"Say hello!", "Say goodbye!"};
    public static Menu testMenu = new Menu("A friendly menu tester", menuOptions);
    public static void main(String[] args) {
        int choice = -1;
        do {
            choice = testMenu.getChoice();
            switch (choice) {
                case 1:
                    System.out.println("\nHello there!");
                    continue;
                case 2:
                    System.out.println("\nHave a nice day, bye!");
                    break;
                default:
                    System.out.println("\nPlease input only one of the options presented.");
                    break;
            }
        } while (choice != menuOptions.length);
    }
}