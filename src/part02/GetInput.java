package part02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetInput {
    private static Scanner uInput;

    public GetInput() {
        uInput = new Scanner(System.in);
    }

    public static int checkInputInt() {
        int intToTest = -1;
        try {
            intToTest = uInput.nextInt();
        } catch (InputMismatchException e) {
            uInput.nextLine();
        }
        return intToTest;
    }

    public static double checkInputDouble() {
        double doubleToTest = -1;
        try {
            doubleToTest = uInput.nextDouble();
        } catch (InputMismatchException e) {
            uInput.nextLine();
        }
        return doubleToTest;
    }
}