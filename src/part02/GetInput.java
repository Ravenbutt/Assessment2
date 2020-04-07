package part02;

import java.util.Scanner;

/**
 * Class which is used to get and check user inputs for validity
 * Created in order to facilitate code reuse and have DRY code
 * @author Andrew Ellis
 * @version V1.1
 */
public class GetInput {
    private static Scanner uInput = new Scanner(System.in);;

    /**
     * Method to get user input for an integer and ensures it is a valid integer
     * NOTE: The reason it reads the scanner for a String rather than integer is because
     * integer inputs ignore whitespace, allowing the user to press enter constantly
     * @return the integer the user input, else -1 if they did not enter an integer
     */
    public static int getIntInput() {
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
     * Method to get user input for a double and ensures it is a valid double
     * NOTE: The reason it reads the scanner for a String rather than double is because
     * double inputs ignore whitespace, allowing the user to press enter constantly
     * @return the double the user input, else -1 if they did not enter an double
     */
    public static double getDoubleInput() {
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

    /**
     * Method to get user input for yes or no values input as 'Y' or 'N'
     * NOTE: '\u0000' is the default unicode for char datatypes
     * @return boolean true if user input 'Y', false if they input 'N'
     */
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
        }
        
        return false;
    }

    public static String getString() {
        String choice = uInput.nextLine();
        return choice;
    }

}