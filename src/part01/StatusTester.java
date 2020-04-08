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

    /**
     * Method to test the usage of Status enums
     */
    private static void testStatus() {
        displayTestFrame("Testing using Status");

        //UNIT_TEST_14
        System.out.println("UNIT_TEST_14 - Result of Status.SERVICE_MODE: " + Status.SERVICE_MODE);

        //UNIT_TEST_15
        System.out.println("UNIT_TEST_15 - Result of Status.VENDING_MODE: " + Status.VENDING_MODE);

        displayTestFrame("Finished testing using Status");
    }

    /**
     * Method to test the getString() method
     */
    private static void testGetString() {
        displayTestFrame("Testing getStatusString() method");

        //UNIT_TEST_16
        System.out.println("UNIT_TEST_16 - Result of Status.SERVICE_MODE.getStatusString(): " + Status.SERVICE_MODE.getStatusString());
        //UNIT_TEST_17

        System.out.println("UNIT_TEST_17 - Result of Status.VENDING_MODE.getStatusString(): " + Status.VENDING_MODE.getStatusString());
        
        displayTestFrame("Finished testing getStatusString() method");
    }

    /**
     * Method to test the fromString() method
     */
    private static void testFromString() {
        displayTestFrame("Testing fromString() method");
        
        //UNIT_TEST_18
        System.out.println("UNIT_TEST_18 - Result of Status.fromString(\"SERVICE_MODE\"): " + Status.fromString("SERVICE_MODE"));
        
        //UNIT_TEST_19
        System.out.println("UNIT_TEST_19 - Result of Status.fromString(\"VENDING_MODE\"): " + Status.fromString("VENDING_MODE"));
        
        displayTestFrame("Finished testing fromString() method");
    }
}