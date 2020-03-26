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
        return doubleToTest;
    }

}