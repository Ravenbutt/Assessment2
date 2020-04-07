package part01;

/**
 * Tester class for Status enum type
 */
public class StatusTester {

    public static void main(String[] args) {
        testStatus();
        testGetString();
        testFromString();
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

    private static void testStatus() {
        displayTestFrame("Testing using Status");
        System.out.println("Result of Status.SERVICE_MODE: " + Status.SERVICE_MODE);
        System.out.println("Result of Status.VENDING_MODE: " + Status.VENDING_MODE);
        displayTestFrame("Finished testing using Status");
    }

    private static void testGetString() {
        displayTestFrame("Testing getStatusString() method");
        System.out.println("Result of Status.SERVICE_MODE.getStatusString(): " + Status.SERVICE_MODE.getStatusString());
        System.out.println("Result of Status.VENDING_MODE.getStatusString(): " + Status.VENDING_MODE.getStatusString());
        displayTestFrame("Finished testing getStatusString() method");
    }

    private static void testFromString() {
        displayTestFrame("Testing fromString() method");
        System.out.println("Result of Status.fromString(\"SERVICE_MODE\"): " + Status.fromString("SERVICE_MODE"));
        System.out.println("Result of Status.fromString(\"VENDING_MODE\"): " + Status.fromString("VENDING_MODE"));
        displayTestFrame("Finished testing fromString() method");
    }
}