package part01;

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
     * @param title String input to be displayed
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
        System.out.print("Please enter a number: ");
        int number = GetInput.getIntInput();
        System.out.println("\nYour number was: " + number);
        System.out.print("\nPlease enter anything that isn't a number: ");
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
        System.out.print("Please enter a floating point number: ");
        double number = GetInput.getDoubleInput();
        System.out.println("\nYour number was: " + number);
        System.out.print("\nPlease enter anything that isn't a number: ");
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

        System.out.print("Please enter 'y' or 'Y': ");
        boolean answerYes = GetInput.getYesNo();
        System.out.println("\nEXPECTED RESULT: Result should be true if 'y' was entered");
        System.out.println("Your actual result was: " + answerYes);

        System.out.print("Please enter 'n' or 'N': ");
        boolean answerNo = GetInput.getYesNo();
        System.out.println("\nEXPECTED RESULT: Result should be false if 'n' was entered");
        System.out.println("Your actual result was: " + answerNo);

        System.out.println("Please enter \"hello\"");
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

        System.out.print("Please enter any String: ");
        String choice = GetInput.getString();

        System.out.println("\n\nYour input String was: " + choice);

        displayTestFrame("Finished testing getString()");
    }

}