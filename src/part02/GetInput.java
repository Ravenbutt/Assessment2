package part02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetInput {
    private static Scanner uInput = new Scanner(System.in);;

    public static int checkIntInput() {
        int intToTest = -1;
        try {
            intToTest = uInput.nextInt();
            uInput.nextLine();
        } catch (InputMismatchException e) {
            uInput.nextLine();
        }
        //uInput.close();
        return intToTest;
    }

    public static double checkDoubleInput() {
        double doubleToTest = -1.0;
        try {
            doubleToTest = uInput.nextDouble();
            uInput.nextLine();
        } catch (InputMismatchException e) {
            uInput.nextLine();
        }
        //uInput.close();
        return doubleToTest;
    }

    public static boolean checkYesNo() {
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