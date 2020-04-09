package part01;


/**
 * Tester class for GetInput class
 * 
 * @author Andrew Ellis
 * @version V1.0
 */
public class GetInputTester {
    
    public static void main(String[] args) {
        testGetIntInput();
        testGetDoubleInput();
        testGetYesNo();
        testGetString();
    }
    
    /**
     * Simple method to create a frame around the different test blocks
     * Just to make it easier to differentiate each type of test
     * @param title input to be displayed
     */
    private static void displayTestFrame(String title) {
        String line = "-------------------------------------------------------------------------------------";
        int lineLength = line.length()/2+(title.length()/2);
        System.out.println("\n"+line);
        System.out.printf("%" + lineLength + "s\n", title);
        System.out.println(line+"\n");
    }

    /**
     * Tester method for GetInput.getIntInput()
     */
    private static void testGetIntInput() {
        displayTestFrame("Testing getIntInput()");

        // UNIT_TEST_20
        // UNIT_TEST_21
        System.out.print("UNIT_TEST_20 & UNIT_TEST_21 - Please enter a number: ");
        int number = GetInput.getIntInput();
        System.out.println("\nYour number was: " + number);

        // UNIT_TEST_22
        System.out.print("\nUNIT_TEST_22 - Please enter anything that isn't a number: ");
        int notNumber = GetInput.getIntInput();
        System.out.println("\nEXPECTED RESULT: If you didn't enter a number, the returned number should be -1");
        System.out.println("Actual returned number: " + notNumber);

        displayTestFrame("Finished testing getIntInput()");
    }

    /**
     * Tester method for GetInput.getDoubleInput()
     */
    private static void testGetDoubleInput() {
        displayTestFrame("Testing getDoubleInput()");

        // UNIT_TEST_23
        // UNIT_TEST_24
        System.out.print("UNIT_TEST_23 & UNIT_TEST_24 - Please enter a floating point number: ");
        double number = GetInput.getDoubleInput();
        System.out.println("\nYour number was: " + number);

        // UNIT_TEST_25
        System.out.print("\nUNIT_TEST_25 - Please enter anything that isn't a number: ");
        double notNumber = GetInput.getDoubleInput();
        System.out.println("\nEXPECTED RESULT: If you didn't enter a number, the returned number should be -1.0");
        System.out.println("Actual returned number: " + notNumber);

        displayTestFrame("Finished testing getDoubleInput()");
    }

    /**
     * Tester method for GetInput.getYesNo()
     */
    private static void testGetYesNo() {
        displayTestFrame("Testing getYesNo()");

        // UNIT_TEST_26
        System.out.print("UNIT_TEST_26 - Please enter 'y' or 'Y': ");
        boolean answerYes = GetInput.getYesNo();
        System.out.println("\nEXPECTED RESULT: Result should be true if 'y' was entered");
        System.out.println("Your actual result was: " + answerYes);

        // UNIT_TEST_27
        System.out.print("UNIT_TEST_27 - Please enter 'n' or 'N': ");
        boolean answerNo = GetInput.getYesNo();
        System.out.println("\nEXPECTED RESULT: Result should be false if 'n' was entered");
        System.out.println("Your actual result was: " + answerNo);

        // UNIT_TEST_28
        System.out.println("UNIT_TEST_28 - Please enter \"hello\"");
        boolean answerFail = GetInput.getYesNo();
        System.out.println("\nEXPECTED RESULT: Should tell the user to only enter y/n and prompt again until they do");
        System.out.println("Your actual result was: " + answerFail);

        displayTestFrame("Finished testing getYesNo()");
    }

    /**
     * Tester method for GetInput.getString()
     */
    private static void testGetString() {
        displayTestFrame("Testing getString()");

        // UNIT_TEST_29
        System.out.print("UNIT_TEST_29 - Please enter any String: ");
        String choice = GetInput.getString();
        System.out.println("\n\nYour input String was: " + choice);

        displayTestFrame("Finished testing getString()");
    }

}