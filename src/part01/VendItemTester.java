package part01;

public class VendItemTester {

    static VendItem testItem1;
    static VendItem testItem2;
    static VendItem testItem3;
    static VendItem testItem4;
    static VendItem testItem5;

    public static void main(String[] args) {
        testCreateItem();
        testItemId();
        testRestock();
        testDecrement();
        testDeliver();
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

    private static void testCreateItem() {
        displayTestFrame("TESTING CREATION OF VendItem(s)");
        //testCase1
        System.out.println("CREATING A VALID VENDITEM");
        System.out.println("> EXPECTED RESULT: Should create a new VendItem with ID=1, name=Coke Zero, qtyAvailable=0, unitPrice=1.45 <");
        System.out.println("> ACTUAL RESULT: <");
        testItem1 = new VendItem("Coke Zero", 1.45);
        System.out.println("testItem1 toString() method: " + testItem1 + "\n");

        //testCase2
        System.out.println("CREATING A VALID VENDITEM");
        System.out.println("> EXPECTED RESULT: Should create a new VendItem with ID=2, name=Tayto Cheese and Onion, qtyAvailable=0, unitPrice=0.80 <");
        System.out.println("> ACTUAL RESULT: <");
        testItem2 = new VendItem("Tayto Cheese and Onion", 0.80);
        System.out.println("testItem2 toString() method: " + testItem2 + "\n");

        //testCase3
        System.out.println("CREATING A VALID VENDITEM WITH QUANTITY SPECIFIED");
        System.out.println("> EXPECTED RESULT: Should create a new VendItem with ID=3, name=Fanta Lemon, qtyAvailable=7, unitPrice=1.35 <");
        System.out.println("> ACTUAL RESULT: <");
        testItem3 = new VendItem("Fanta Lemon", 1.35, 7);
        System.out.println("testItem3 toString() method: " + testItem3 + "\n");

        //testCase4
        System.out.println("CREATING A VENDITEM WITH 'null' AS NAME");
        System.out.println("> EXPECTED RESULT: Should create the VendItem with ID=4, but with name as 'UNASSIGNED', qtyAvailable=0, UnitPrice=0.0. <");
        System.out.println("> ACTUAL RESULT: <");
        testItem4 = new VendItem(null, 0.50,6);
        System.out.println("testItem4 toString() method: " + testItem4 + "\n");

        //testCase5
        System.out.println("CREATING A VALID VENDITEM WITH LESS THAN 0.0 AS PRICE");
        System.out.println("> EXPECTED RESULT: Should create a new VendItem with ID=5, name=Galaxy Cookie Crumble, qtyAvailable=0, unitPrice=0.0 <");
        System.out.println("> ACTUAL RESULT: <");
        testItem5 = new VendItem("Galaxy Cookie Crumble", -0.5);
        System.out.println("testItem5 toString() method: " + testItem5 + "\n");

        displayTestFrame("FINISHED TESTING CREATION OF VendItem(s)");
    }

    private static void testItemId() {
        displayTestFrame("TESTING FOR AUTO INCREMENTING ID");
        //testCase6
        System.out.println("testItem1 EXPECTED ID=1 -- Actual: " + testItem1.getItemId());
        System.out.println("testItem2 EXPECTED ID=2 -- Actual: " + testItem2.getItemId());
        System.out.println("testItem3 EXPECTED ID=3 -- Actual: " + testItem3.getItemId());
        System.out.println("testItem4 EXPECTED ID=4 -- Actual: " + testItem4.getItemId());
        System.out.println("testItem5 EXPECTED ID=5 -- Actual: " + testItem5.getItemId());

        displayTestFrame("FINISHED TESTING FOR AUTO INCREMENTING ID");
    }

    private static void testRestock() {
        displayTestFrame("TESTING restock(int) METHOD FOR VendItem(s)");

        //testCase7
        System.out.println("TESTING RESTOCKING WITH VALID QUANTITY FOR ITEM");
        System.out.println("> EXPECTED RESULT: Returns true and updated qtyAvailable=6. <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("Result of restocking testItem1: " + testItem1.restock(6));
        System.out.println("qtyAvailable of testItem1: " + testItem1.getQty() + "\n");

        //testCase8
        System.out.println("TESTING RESTOCKING WITH INVALID(ABOVE 10) QUANTITY FOR ITEM");
        System.out.println("> EXPECTED RESULT: Returns false and testItem3 qtyAvailable should still equal 7. <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("Result of restocking testItem3: " + testItem3.restock(11));
        System.out.println("qtyAvailable of testItem3: " + testItem3.getQty() + "\n");

        //testCase9
        System.out.println("TESTING RESTOCKING WITH INVALID(BELOW 1) QUANTITY FOR ITEM");
        System.out.println("> EXPECTED RESULT: Returns false and testItem3 qtyAvailable should still equal 7. <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("Result of restocking testItem3: " + testItem3.restock(-2));
        System.out.println("qtyAvailable of testItem3: " + testItem3.getQty() + "\n");

        displayTestFrame("FINISHED TESTING restock(int) METHOD FOR VendItem(s)");
    }

    private static void testDecrement() {
        displayTestFrame("TESTING decrement() METHOD FOR VendItem(s)");

        //testCase10
        System.out.println("TESTING decrement() ON AN ITEM WITH qtyAvailable GREATER THAN 0");
        System.out.println("> EXPECTED RESULT: Returns true and testItem1's qtyAvailable should = 5. <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("Result of decrementing testItem1: " + testItem1.decrement());
        System.out.println("qtyAvailable of testItem1: " + testItem1.getQty() + "\n");

        //testCase11
        System.out.println("TESTING decrement() ON AN ITEM WITH qtyAvailable EQUAL TO 0");
        System.out.println("> EXPECTED RESULT: Returns false and testItem2's qtyAvailable still equals 0. <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("Result of decrementing testItem2: " + testItem2.decrement());
        System.out.println("qtyAvailable of testItem2: " + testItem2.getQty() + "\n");

        displayTestFrame("FINISHED TESTING decrement() METHOD FOR VendItem(s)");
    }

    private static void testDeliver() {
        displayTestFrame("TESTING deliver() METHOD FOR VendItem(s)");

        //testCase12
        System.out.println("TESTING deliver() ON AN ITEM WITH qtyAvailable GREATER THAN 0");
        System.out.println("> EXPECTED RESULT: Return a String saying 'Thanks for purchasing Coke Zero.' <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("Result of deliver() testItem1: " + testItem1.deliver());

        //testCase13
        System.out.println("TESTING deliver() ON AN ITEM WITH qtyAvailable LESS THAN 1");
        System.out.println("> EXPECTED RESULT: Return a String saying 'Sorry for being out of Tayto Cheese and Onion.' <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("Result of deliver() testItem2: " + testItem2.deliver());
    }
}