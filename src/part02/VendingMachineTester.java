package part02;

public class VendingMachineTester {

    private static VendingMachine testingMachine;
    private static VendItem testItem1;
    private static VendItem testItem2;
    private static VendItem testItem3;
    private static VendItem testItem4;
    private static VendItem testItem5;
    private static VendItem testItem6;
    
    //TODO use padding instead of characters to make it not look awful?

    public static void main(String[] args) {
        createVendingMachine();
        testAddingItems();
        testListItems();
        testRestocking();
        testSetStatus();
        testPurchaseNoCoins();
        testInsertCoins();
        testPurchase();
        testFindItem();
    }

    private static void createVendingMachine() {
        testingMachine = new VendingMachine("Testing Machine", 5);
    }

    private static void displayTestFrame(String title) {
        String line = "-------------------------------------------------------------------------------------";
        int lineLength = line.length()/2+(title.length()/2);
        System.out.println("\n"+line);
        System.out.printf("%" + lineLength + "s\n", title);
        System.out.println(line+"\n");
    }

    private static void displayStock() {

        for (VendItem item : testingMachine.getStock()) {
            if(item != null) {
                System.out.println("    " + item);
            }

        }
    }
    
    private static void testAddingItems() {
        displayTestFrame("TESTING OF VendItem AND ADDING VendItem(s) TO VendingMachine");
        //Creating a VendingMachine instance
        

        //Creating multiple (correct) VendItem instances
        testItem1 = new VendItem("Coca Cola", 1.45);
        testItem2 = new VendItem("Tayto Prawn and Cocktail", 0.70);
        testItem3 = new VendItem("Fanta Lemon", 1.35);
        //Item with quantity specified in overloaded constructor
        testItem4 = new VendItem("Cadbury's Dairy Milk", 0.70, 1);
        testItem5 = new VendItem("Galaxy Cookie Crumble", 0.80, 5);
        testItem6 = new VendItem("Lucozade", 1);

        
        System.out.printf("Initial list of stock: \n");
        System.out.println("\n> EXPECTED RESULT: There should be nothing below '- ACTUAL RESULT -' line as the test hides null values, and there are no items in the machine. <");
        System.out.println("> ACTUAL RESULT: <");
        displayStock();
        
        System.out.println("\nTesting adding VendItem instances to VendingMachine:");
        System.out.println("\n> EXPECTED RESULT: ItemID=1,2,3 & 4 should return true, indicating they've been added successfully. Adding null item should return false. <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    Adding item with ID: " + testItem1.getItemId() + " - Result: " + testingMachine.addNewItem(testItem1));
        System.out.println("    Adding item with ID: " + testItem2.getItemId() + " - Result: " + testingMachine.addNewItem(testItem2));
        System.out.println("    Adding item with ID: " + testItem3.getItemId() + " - Result: " + testingMachine.addNewItem(testItem3));
        System.out.println("    Adding item with ID: " + testItem5.getItemId() + " - Result: " + testingMachine.addNewItem(testItem5));
        
        //Adding item with quantity specified in constructor
        System.out.println("    Adding item with quantity specified with ID: " + testItem4.getItemId() + " - Result: " + testingMachine.addNewItem(testItem4));

        //Adding null item
        System.out.println("    Adding null item: " + testingMachine.addNewItem(null) + "\n");

        System.out.println("ATTEMPTING TO ADD ITEM WHEN MaxItems HAS BEEN REACHED");
        System.out.println("> EXPECTED RESULT: Should retun false <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println(testingMachine.addNewItem(testItem6) + "\n");

        System.out.printf("Updated list of stock:\n");
        System.out.println("\n> EXPECTED RESULT: Four items in the machine. First three have qtyAvailable=0, item four has qtyAvailable=1. <");
        System.out.println("> ACTUAL RESULT: <");
        displayStock();
        displayTestFrame("FINISHED ADDING STOCK");
    }

    private static void testListItems () {
        displayTestFrame("TESTING listItems()");
        System.out.println("> EXPECTED RESULT: Should output all stock in a readable format <");
        System.out.println("> ACTUAL RESULT: <");
        for (String itemString : testingMachine.listItems()) {
            System.out.println(itemString);
        }
        displayTestFrame("FINISHED TESTING listItems()");
    }

    private static void testRestocking() {
        displayTestFrame("TESTING restock() OF VendItem, SHOWING THAT VALUES ARE UPDATED IN VendingMachine");
        System.out.println("INITIAL STOCK QTY VALUES:");
        System.out.println("> EXPECTED RESULT: testItem1, testItem2 & testItem3 qtyAvailable=0, testItem4 qtyAvailable=1 <");
        System.out.println("> ACTUAL RESULT: <");
        for (VendItem item : testingMachine.getStock()) {
            if(item != null) {
                System.out.printf("    Item ID: %d - Stock Available: %d\n", item.getItemId(), item.getQty());
            }

        }

        System.out.println("\n\n> SETTING STOCK VALUES <");
        System.out.println("> EXPECTED RESULT: testItem1.restock(5) returns true, testItem2.restock(20) & testItem3.restock(-1) returns false <");
        System.out.println("> EXPECTED RESULT: testItem1 qtyAvailable should be 5, testItem2 and testItem3 should still have 0 qtyAvailable <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    Result of testItem1.restock(5): " + testItem1.restock(5));
        System.out.println("    testItem1 qty according to VendingMachine instance: " + testingMachine.findItem(1).getQty() + "\n");
        System.out.println("    Result of testItem2.restock(20): " + testItem2.restock(20));
        System.out.println("    testItem2 qty according to VendingMachine instance: " + testingMachine.findItem(2).getQty() + "\n");
        System.out.println("    Result of testItem3.restock(-1): " + testItem3.restock(-1));
        System.out.println("    testItem3 qty according to VendingMachine instance: " + testingMachine.findItem(3).getQty() + "\n");
        displayTestFrame("FINISHED RESTOCKING ITEMS");
    }

    private static void testSetStatus() {
        displayTestFrame("TESTING setVmStatus() TO CHANGE STATUS OF MACHINE");
        
        System.out.println("INITIAL STATUS:");
        System.out.println("> EXPECTED RESULT: Machine is in SERVICE_MODE <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    Machine is in: " + testingMachine.getVmStatus());


        testingMachine.setVmStatus(Status.VENDING_MODE);
        System.out.println("> EXPECTED RESULT: Machine is set to VENDING_MODE <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    Machine is in: " + testingMachine.getVmStatus());
        displayTestFrame("FINISHED CHANGING STATUS");
    }

    private static void testPurchaseNoCoins() {
        displayTestFrame("TESTING purchaseItem() BEFORE INSERTING COINS");
        System.out.println("INITIAL VALUE OF userMoney");
        System.out.println("> EXPECTED RESULT: Purchase attempt fails; returns false <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    Result of attempting to purchase item ID=1: " + testingMachine.purchaseItem(1));
        displayTestFrame("FINISHED TESTING purchaseItem() BEFORE INSERTING COINS");
    }

    private static void testInsertCoins() {
        displayTestFrame("TESTING insertCoin() TO ADD FUNDS TO MACHINE");

        System.out.println("INITIAL VALUE OF userMoney & totalMoney");
        System.out.println("> EXPECTED RESULT: userMoney = £0.00, totalMoney = £0.00 <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.printf("    userMoney = %.2f, totalMoney = %.2f\n", testingMachine.getUserMoney(), testingMachine.getTotalMoney());

        System.out.println("> EXPECTED RESULT: Inserting 50, 1 & 2 should return true. Inserting 3, 47 & -2 should return false. <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    Result of inserting 50 (50p): " + testingMachine.insertCoin(50));
        System.out.println("    Result of inserting 1 (£1): " + testingMachine.insertCoin(1));
        System.out.println("    Result of inserting 2 (£2): " + testingMachine.insertCoin(2));
        System.out.println("    Result of inserting 3 (invalid): " + testingMachine.insertCoin(3));
        System.out.println("    Result of inserting 47 (invalid): " + testingMachine.insertCoin(47));
        System.out.println("    Result of inserting -2 (invalid): " + testingMachine.insertCoin(-2));

        System.out.println("\nCHECKING userMoney & totalMoney VALUES");
        System.out.println("> EXPECTED RESULT:  userMoney and totalMoney should both equal 3.5 (£3.50) <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.printf("     User money: %.2f\n", testingMachine.getUserMoney());
        System.out.printf("     Total money: %.2f\n", testingMachine.getTotalMoney());

        displayTestFrame("FINISHED TESTING insertCoin()");
    }

    private static void testPurchase() {
        displayTestFrame("TESTING purchaseItem() WITH COINS INSERTED");
        
        System.out.println("INITIAL VALUE OF userMoney & totalMoney");
        System.out.println("> EXPECTED RESULT: userMoney = 3.5, totalMoney = 3.5 <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.printf("    userMoney = %.2f, totalMoney = %.2f\n", testingMachine.getUserMoney(), testingMachine.getTotalMoney());

        System.out.println("INITIAL VALUE OF ITEM ID=1 QTY AVAILABLE");
        System.out.println("EXPECTED RESULT: qtyAvailable=5");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    item ID=1 qtyAvailble: " + testingMachine.findItem(1).getQty() + "\n");

        System.out.println("ATTEMPTING TO PURCHASE VALID ITEM THAT'S IN STOCK WITH COINS INSERTED");
        System.out.println("> EXPECTED RESULT: Should output a String saying the purchase was successful and report back on the change given.");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    Result of attempting to purchase item ID=1: \n" + testingMachine.purchaseItem(1) + "\n");

        System.out.println("UPDATED VALUE OF ITEM ID=1 QTY AVAILABLE");
        System.out.println("EXPECTED RESULT: qtyAvailable=4");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    item ID=1 qtyAvailable: " + testingMachine.findItem(1).getQty() + "\n");

        System.out.println("INITIAL VALUE OF userMoney & totalMoney");
        System.out.println("> EXPECTED RESULT: userMoney = 0.0, totalMoney = 1.45 <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.printf("    userMoney = %.2f, totalMoney = %.2f\n", testingMachine.getUserMoney(), testingMachine.getTotalMoney());


        System.out.println("\nATTEMPTING TO PURCHASE VALID ITEM THAT'S NOT STOCKED WITH COINS INSERTED");
        testingMachine.insertCoin(2);
        
        System.out.println("> EXPECTED RESULT: Should output a String saying the purchase was unsuccessful.");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    Result of attempting to purchase out of stock item ID=2: \n" + testingMachine.purchaseItem(2) + "\n");


        System.out.println("ATTEMPTING TO PURCHASE AN ITEM THAT DOESN'T EXIST WITH COINS INSERTED");
        System.out.println("> EXPECTED RESULT: Should output a String saying the chosen item does not exist.");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("    Result of attempting to purchase item that doesn't exist: \n" + testingMachine.purchaseItem(10) + "\n");

        //Setting any stock to only have qty of 1 for the test
        testingMachine.findItem(1).restock(1);
        testingMachine.findItem(4).restock(1);
        testingMachine.insertCoin(2);

        System.out.println("--- PURCHASING THE LAST ITEM IN THE MACHINE ---");

        
        //Purchasing one of items so that there is only one item left in the entire machine
        testingMachine.purchaseItem(1);
        testingMachine.insertCoin(50);
        testingMachine.insertCoin(20);
        testingMachine.insertCoin(10);
        testingMachine.purchaseItem(5);


        testingMachine.insertCoin(1);
        testingMachine.insertCoin(50);

        System.out.println("        INITIAL STATUS OF MACHINE: " + testingMachine.getVmStatus());
        System.out.println("> EXPECTED RESULT: Purchase should be successful, AND machine should switch to SERVICE_MODE after. <");
        System.out.println("> ACTUAL RESULT: <");
        //TODO FIX THIS
        System.out.println("    " + testingMachine.purchaseItem(4));

        System.out.println("        UPDATED STATUS OF MACHINE: " + testingMachine.getVmStatus());

        displayTestFrame("FINISHED TESTING purchaseItem() WITH COINS INSERTED");
    }

    private static void testFindItem() {
        displayTestFrame("TESTING findItem() TO FIND A VendItem INSTANCE BY IT'S ID");

        System.out.println("--- findItem() ON ITEM THAT EXISTS ---");
        System.out.println("> EXPECTED RESULT: Returns the object testItem1 toString() <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("Result of findItem(1): " + testingMachine.findItem(1));

        System.out.println("--- findItem() ON ITEM THAT DOES NOT EXIST ---");
        System.out.println("> EXPECTED RESULT: Should throw a NullPointerException with detail message stating item not found. Should print stack trace. <");
        System.out.println("> ACTUAL RESULT: <");
        try {
            System.out.println(testingMachine.findItem(59));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println("\n!! NOTE: ABOVE IS A CAUGHT EXCEPTION THAT HAS BEEN PRINTED FOR TEST PURPOSES !!");

        displayTestFrame("FINISHED TESTING findItem()");
    }

    private static void testCase2() {
        

        


        
        System.out.println("----   VENDING MACHINE INFO   ----");
        System.out.println(testingMachine.getSystemInfo());
        System.out.println("---- END VENDING MACHINE INFO ----\n");



        //Outputting the details of each item
        System.out.println("----       LISTING STOCK       ----");
        for (VendItem item : testingMachine.getStock()) {
            if(item != null) {
                System.out.print(item.getDetails());
            }
        }
        System.out.println("----     END LISTING STOCK     ----\n");



        System.out.println("----   ITEM PURCHASE BEFORE COIN INPUT   ----");
        System.out.println("Result of attempting to purchase item ID=1: " + testingMachine.purchaseItem(1));
        System.out.println("---- END ITEM PURCHASE BEFORE COIN INPUT ----\n");

        
        System.out.println("----   COIN INPUT   ----");
        System.out.println("Result of inserting 50 (50p): " + testingMachine.insertCoin(50));
        System.out.println("Result of inserting 1 (£1): " + testingMachine.insertCoin(1));
        System.out.println("Result of inserting 2 (£2): " + testingMachine.insertCoin(2));
        System.out.println("Result of inserting 3 (£3, invalid): " + testingMachine.insertCoin(3));
        System.out.println("Result of inserting 47 (invalid): " + testingMachine.insertCoin(47));


        System.out.println("User money: " + testingMachine.getUserMoney());
        System.out.println("Total money: " + testingMachine.getTotalMoney());

        System.out.println("---- END COIN INPUT ----\n");


        System.out.println("----   ITEM PURCHASE   ----");
        System.out.println("userMoney before purchase: " + testingMachine.getUserMoney());
        System.out.println("totalMoney before purchase: " + testingMachine.getTotalMoney() + "\n");
        System.out.println("> PURCHASING ITEM ID=1: <\n" + testingMachine.purchaseItem(1));
        System.out.println("\nuserMoney after purchase: " + testingMachine.getUserMoney());
        System.out.println("totalMoney after purchase: " + testingMachine.getTotalMoney() + "\n");


        System.out.println("> PURCHASING ITEM THAT DOESN'T EXIST <");
        System.out.println("Attempting to purchase item ID=8: " + testingMachine.purchaseItem(8));
        System.out.println("Attempting to purchase item ID=20: " + testingMachine.purchaseItem(20) + "\n");


        System.out.println("> PURCHASING ITEM WITH QTY=0 <");
        testingMachine.insertCoin(2);
        testingMachine.insertCoin(1);
        System.out.println("Attempting to purchase item qty=0: " + testingMachine.purchaseItem(2) + "\n");

        System.out.println("> PURCHASING LAST ITEM <");
        System.out.println("TEST >>> Machine is in: " + testingMachine.getVmStatus() + " <<< TEST\n");
        testItem1.restock(1);
        testItem4.restock(1);
        testItem5.restock(1);
        System.out.println(testingMachine.purchaseItem(1));
        testingMachine.insertCoin(2);
        System.out.println(testingMachine.purchaseItem(4));
        System.out.println("TEST >>> Machine is in: " + testingMachine.getVmStatus() + " <<< TEST\n");
        System.out.println("---- END ITEM PURCHASE ----\n");


        System.out.println("----   FINDING A VENDITEM   ----\n> NOTE: THROWS NULLPOINTEREXCEPTION IF ITEM DOESN'T EXIST <");
        System.out.println("Item that exists: " + testingMachine.findItem(1));
        try {
            System.out.println(testingMachine.findItem(59));
        } catch (NullPointerException e) {
            System.out.println("Item not found.");
        }

        System.out.println("---- END FINDING A VENDITEM ----\n");

        //System.out.println(testingMachine.getDetails());
    }

    private static void testCase3() {

    }

    private static void testCase4() {

    }

    private static void testCase5() {

    }

    private static void testCase6() {

    }

    private static void testCase7() {

    }

    private static void testCase8() {

    }

    private static void testCase9() {

    }

    private static void testCase10() {

    }

    private static void testCase11() {

    }

    private static void testCase12() {

    }

    private static void testCase13() {

    }

    




}