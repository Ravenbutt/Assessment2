package part01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetInput {
    private static Scanner uInput = new Scanner(System.in);;

    public static int checkIntInput() {
        int intToTest = -1;
        String input = uInput.nextLine();
        try {
            intToTest = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
        //uInput.close();
        return intToTest;
    }

    /**
     * Reason it reads a String rather than double as double ignores whitespace and you can press enter constantly.
     * @return
     */
    public static double checkDoubleInput() {
        double doubleToTest = -1.0;
        String input = uInput.nextLine();
        try {
            doubleToTest = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1.0;
        }
        //uInput.close();
        return doubleToTest;
    }

    public static boolean getYesNo() {
        char choice = '\u0000';
        while(Character.toUpperCase(choice) != 'N') {
            try {
                choice = uInput.nextLine().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please enter Y for yes or N for no.");
            }
            
            switch (Character.toUpperCase(choice)) {
                case 'Y':
                    return true;
                case 'N':
                    return false;
                default:
                    System.out.println("Please enter Y for yes or N for no.");
                    break;
            }

            
            // else {
            //     System.out.println("Please enter Y for yes or N for no.");
            //     continue;
            // }
        }
        
        return false;
    }

}