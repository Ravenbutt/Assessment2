package part01;

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
        testGetters();
    }

    private static void createVendingMachine() {
        testingMachine = new VendingMachine("Andy's Testing Inc.", 5);
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
     * Testing method that makes use of Vending Machine instance's
     * getStock() method to display the stock in the machine
     */
    private static void displayStock() {
        for (VendItem item : testingMachine.getStock()) {
            if(item != null) {
                System.out.println("\t" + item);
            }
        }
    }

    private static void testAddingItems() {
        displayTestFrame("TESTING OF VendItem AND ADDING VendItem(s) TO VendingMachine");

        //Creating multiple (correct) VendItem instances
        testItem1 = new VendItem("Coca Cola", 1.45);
        testItem2 = new VendItem("Tayto Prawn and Cocktail", 0.70);
        testItem3 = new VendItem("Fanta Lemon", 1.35);

        //Item with quantity specified in overloaded constructor
        testItem4 = new VendItem("Cadbury's Dairy Milk", 0.70, 1);
        testItem5 = new VendItem("Galaxy Cookie Crumble", 0.80, 5);
        testItem6 = new VendItem("Lucozade", 1);

        //INTEGRATION_TEST_1
        System.out.println("--- Initial list of stock: ---");
        System.out.println("\n> EXPECTED RESULT: There should be nothing below '- ACTUAL RESULT -' line as the test hides null values, and there are no items in the machine. <");
        System.out.println("> ACTUAL RESULT: <");
        displayStock();
        
        
        System.out.println("\n--- Testing adding VendItem instances to VendingMachine ---");
        System.out.println("\n> EXPECTED RESULT: ItemID=1,2,3 & 4 should return true, indicating they've been added successfully. Adding null item should return false. <");
        System.out.println("> ACTUAL RESULT: <");
        //INTEGRATION_TEST_2
        System.out.println("\tAdding item with ID: " + testItem1.getItemId() + " - Result: " + testingMachine.addNewItem(testItem1));
        //INTEGRATION_TEST_3
        System.out.println("\tAdding item with ID: " + testItem2.getItemId() + " - Result: " + testingMachine.addNewItem(testItem2));
        //INTEGRATION_TEST_4
        System.out.println("\tAdding item with ID: " + testItem3.getItemId() + " - Result: " + testingMachine.addNewItem(testItem3));
        //INTEGRATION_TEST_5
        System.out.println("\tAdding item with ID: " + testItem5.getItemId() + " - Result: " + testingMachine.addNewItem(testItem5));
        
        //INTEGRATION_TEST_6
        //Adding item with quantity specified in constructor
        System.out.println("\tAdding item with quantity specified with ID: " + testItem4.getItemId() + " - Result: " + testingMachine.addNewItem(testItem4) + " - Item Qty: " + testingMachine.findItem(4).getQty());

        //INTEGRATION_TEST_7
        //Adding null item
        System.out.println("\tAdding null item: " + testingMachine.addNewItem(null) + "\n");

        //INTEGRATION_TEST_8
        System.out.println("--- ATTEMPTING TO ADD ITEM WHEN MaxItems HAS BEEN REACHED ---");
        System.out.println("> EXPECTED RESULT: Should retun false <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println(testingMachine.addNewItem(testItem6) + "\n");

        //INTEGRATION_TEST_9
        //INTEGRATION_TEST_10
        System.out.printf("Updated list of stock:\n");
        System.out.println("\n> EXPECTED RESULT: Five items in the machine. First three have qtyAvailable=0, ID=4 has qtyAvailable=1, ID=5 has qtyAvailable=5 <");
        System.out.println("> ACTUAL RESULT: <");
        displayStock();
        displayTestFrame("FINISHED ADDING STOCK");
    }

    private static void testListItems () {
        //INTEGRATION_TEST_11
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

        //INTEGRATION_TEST_12
        System.out.println("--- INITIAL STOCK QTY VALUES: ---");
        System.out.println("> EXPECTED RESULT: testItem1, testItem2 & testItem3 qtyAvailable=0, testItem4 qtyAvailable=1, testItem5 qtyAvailable=5 <");
        System.out.println("> ACTUAL RESULT: <");
        for (VendItem item : testingMachine.getStock()) {
            if(item != null) {
                System.out.printf("\tItem ID: %d - Quantity Available: %d\n", item.getItemId(), item.getQty());
            }

        }

        System.out.println("\n\n--- SETTING STOCK VALUES ---");
        System.out.println("> EXPECTED RESULT: testItem1.restock(5) returns true, testItem2.restock(20) & testItem3.restock(-1) returns false <");
        System.out.println("> EXPECTED RESULT: testItem1 qtyAvailable should be 5, testItem2 and testItem3 should still have 0 qtyAvailable <");
        System.out.println("> ACTUAL RESULT: <");
        //INTEGRATION_TEST_13
        System.out.println("\tResult of testItem1.restock(5): " + testItem1.restock(5));
        System.out.println("\ttestItem1 qty according to VendingMachine instance: " + testingMachine.findItem(1).getQty() + "\n");
        //INTEGRATION_TEST_14
        System.out.println("\tResult of testItem2.restock(20): " + testItem4.restock(20));
        System.out.println("\ttestItem2 qty according to VendingMachine instance: " + testingMachine.findItem(2).getQty() + "\n");
        //INTEGRATION_TEST_15
        System.out.println("\tResult of testItem3.restock(-1): " + testItem3.restock(-1));
        System.out.println("\ttestItem3 qty according to VendingMachine instance: " + testingMachine.findItem(3).getQty() + "\n");
        displayTestFrame("FINISHED RESTOCKING ITEMS");
    }

    private static void testSetStatus() {
        displayTestFrame("TESTING setStatus() TO CHANGE STATUS OF MACHINE");
        
        //INTEGRATION_TEST_16
        System.out.println("--- INITIAL STATUS: ---");
        System.out.println("> EXPECTED RESULT: Machine is in SERVICE_MODE <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("\tMachine is in: " + testingMachine.getVmStatus());

        //INTEGRATION_TEST_17
        testingMachine.setStatus(Status.VENDING_MODE);
        System.out.println("> EXPECTED RESULT: Machine is set to VENDING_MODE <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("\tMachine is in: " + testingMachine.getVmStatus());
        displayTestFrame("FINISHED CHANGING STATUS");
    }

    private static void testPurchaseNoCoins() {
        displayTestFrame("TESTING purchaseItem() BEFORE INSERTING COINS");

        //INTEGRATION_TEST_18
        System.out.println("--- INITIAL VALUE OF userMoney ---");
        System.out.println("> EXPECTED RESULT: Purchase attempt fails; returns false <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("\tResult of attempting to purchase item ID=1: " + testingMachine.purchaseItem(1));
        displayTestFrame("FINISHED TESTING purchaseItem() BEFORE INSERTING COINS");
    }

    private static void testInsertCoins() {
        displayTestFrame("TESTING insertCoin() TO ADD FUNDS TO MACHINE");

        //INTEGRATION_TEST_19
        System.out.println("--- INITIAL VALUE OF userMoney & totalMoney ---");
        System.out.println("> EXPECTED RESULT: userMoney = 0.00, totalMoney = 0.00 <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.printf("\tuserMoney = %.2f, totalMoney = %.2f\n", testingMachine.getUserMoney(), testingMachine.getTotalMoney());

        
        System.out.println("> EXPECTED RESULT: Inserting 50, 1 & 2 should return true. Inserting 3, 47 & -2 should return false. <");
        System.out.println("> ACTUAL RESULT: <");
        //INTEGRATION_TEST_20
        System.out.println("\tResult of inserting 50 (50p): " + testingMachine.insertCoin(50));
        //INTEGRATION_TEST_21
        System.out.println("\tResult of inserting 1 (£1): " + testingMachine.insertCoin(1));
        //INTEGRATION_TEST_22
        System.out.println("\tResult of inserting 2 (£2): " + testingMachine.insertCoin(2));
        //INTEGRATION_TEST_23
        System.out.println("\tResult of inserting 3 (invalid): " + testingMachine.insertCoin(3));
        //INTEGRATION_TEST_24
        System.out.println("\tResult of inserting 47 (invalid): " + testingMachine.insertCoin(47));
        //INTEGRATION_TEST_25
        System.out.println("\tResult of inserting -2 (invalid): " + testingMachine.insertCoin(-2));

        //INTEGRATION_TEST_26
        System.out.println("\n--- CHECKING userMoney & totalMoney VALUES ---");
        System.out.println("> EXPECTED RESULT:  userMoney and totalMoney should both equal 3.5 (£3.50) <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.printf("\tUser money: %.2f\n", testingMachine.getUserMoney());
        System.out.printf("\tTotal money: %.2f\n", testingMachine.getTotalMoney());

        displayTestFrame("FINISHED TESTING insertCoin()");
    }

    private static void testPurchase() {
        displayTestFrame("TESTING purchaseItem() WITH COINS INSERTED");
        
        //INTEGRATION_TEST_27
        System.out.println("--- INITIAL VALUE OF userMoney & totalMoney ---");
        System.out.println("> EXPECTED RESULT: userMoney = 3.5, totalMoney = 3.5 <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.printf("\tuserMoney = %.2f, totalMoney = %.2f\n\n", testingMachine.getUserMoney(), testingMachine.getTotalMoney());

        //INTEGRATION_TEST_28
        System.out.println("--- INITIAL VALUE OF ITEM ID=1 QTY AVAILABLE ---");
        System.out.println("EXPECTED RESULT: qtyAvailable=5");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("\titem ID=1 qtyAvailble: " + testingMachine.findItem(1).getQty() + "\n");

        //INTEGRATION_TEST_29
        System.out.println("--- ATTEMPTING TO PURCHASE VALID ITEM THAT'S IN STOCK WITH COINS INSERTED ---");
        System.out.println("> EXPECTED RESULT: Should output a String saying the purchase was successful and report back on the change given.");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("\tResult of attempting to purchase item ID=1: \n" + testingMachine.purchaseItem(1) + "\n\n");

        //INTEGRATION_TEST_30
        System.out.println("--- UPDATED VALUE OF ITEM ID=1 QTY AVAILABLE ---");
        System.out.println("EXPECTED RESULT: qtyAvailable=4");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("\titem ID=1 qtyAvailable: " + testingMachine.findItem(1).getQty() + "\n");

        //INTEGRATION_TEST_31
        System.out.println("--- UPDATED VALUE OF userMoney & totalMoney ---");
        System.out.println("> EXPECTED RESULT: userMoney = 0.0, totalMoney = 1.45 <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.printf("\tuserMoney = %.2f, totalMoney = %.2f\n", testingMachine.getUserMoney(), testingMachine.getTotalMoney());

        //INTEGRATION_TEST_32
        System.out.println("\n--- ATTEMPTING TO PURCHASE VALID ITEM THAT'S NOT STOCKED WITH COINS INSERTED ---");
        testingMachine.insertCoin(2);
        System.out.println("> EXPECTED RESULT: Should output a String saying the purchase was unsuccessful.");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("\tResult of attempting to purchase out of stock item ID=2: \n" + testingMachine.purchaseItem(2) + "\n");

        //INTEGRATION_TEST_33
        System.out.println("--- ATTEMPTING TO PURCHASE AN ITEM THAT DOESN'T EXIST WITH COINS INSERTED ---");
        System.out.println("> EXPECTED RESULT: Should output a String saying the chosen item does not exist.");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("\tResult of attempting to purchase item that doesn't exist: \n" + testingMachine.purchaseItem(10) + "\n");

        //Setting any stock to only have qty of 1 for the test
        
        //testingMachine.findItem(4).restock(1);
        

        System.out.println("--- PURCHASING THE LAST ITEM IN THE MACHINE ---");
        testingMachine.insertCoin(2);
        testingMachine.findItem(1).restock(1);
        testingMachine.findItem(5).restock(1);
        testingMachine.findItem(4).restock(1);
        
        //Purchasing one of items so that there is only one item left in the entire machine
        testingMachine.purchaseItem(1);
        //System.out.println(testingMachine.getUserMoney());

        //referencePoint1
        testingMachine.insertCoin(50);
        testingMachine.insertCoin(20);
        testingMachine.insertCoin(10);
        //System.out.println("hello!"+testingMachine.getUserMoney());
        //System.out.println("UMONEYINT: " + testingMachine.getUserMoneyInt());

        //INTEGRATION_TEST_39
        System.out.println("\nNOTE: This wasn't an original test case and was instead a precondition, but it highlighted quite a fatal flaw in my code, detailed in the testing document.");
        System.out.println(testingMachine.purchaseItem(5));
        //testingMachine.purchaseItem(5);


        testingMachine.insertCoin(1);
        testingMachine.insertCoin(50);

        //displayStock();
        testingMachine.insertCoin(1);
        testingMachine.insertCoin(50);


        //INTEGRATION_TEST_34
        System.out.println("\tINITIAL STATUS OF MACHINE: " + testingMachine.getVmStatus());

        //INTEGRATION_TEST_35
        System.out.println("> EXPECTED RESULT: Purchase should be successful, AND machine should switch to SERVICE_MODE after. <");
        System.out.println("> ACTUAL RESULT: <");
        //TODO FIX THIS
        System.out.println("\t" + testingMachine.purchaseItem(4));

        //INTEGRATION_TEST_36
        System.out.println("\n\tUPDATED STATUS OF MACHINE: " + testingMachine.getVmStatus());
        //displayStock();
        displayTestFrame("FINISHED TESTING purchaseItem() WITH COINS INSERTED");
    }

    private static void testFindItem() {
        displayTestFrame("TESTING findItem() TO FIND A VendItem INSTANCE BY IT'S ID");

        //INTEGRATION_TEST_37
        System.out.println("--- findItem() ON ITEM THAT EXISTS ---");
        System.out.println("> EXPECTED RESULT: Returns the object testItem1 toString() <");
        System.out.println("> ACTUAL RESULT: <");
        System.out.println("Result of findItem(1): " + testingMachine.findItem(1));

        //INTEGRATION_TEST_38
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

    private static void testGetters() {
        displayTestFrame("TESTING OF VendingMachine INSTANCE GETTERS");

        //INTEGRATION_TEST_39
        System.out.println("Machine owner: " + testingMachine.getOwner());
        //INTEGRATION_TEST_40
        System.out.println("Machine Status: " + testingMachine.getVmStatus());
        //INTEGRATION_TEST_41
        System.out.println("Machine Max Items: " + testingMachine.getMaxItems());
        //INTEGRATION_TEST_42
        System.out.println("Machine Item Count: " + testingMachine.getItemCount());
        //INTEGRATION_TEST_43
        System.out.println("Machine Total Money: " + testingMachine.getTotalMoney());
        //INTEGRATION_TEST_44
        System.out.println("Machine User Money: " + testingMachine.getUserMoney());
        //INTEGRATION_TEST_45 - NOTE: the testing method displayStock() uses a VendingMachine instance's getStock() getter
        System.out.println("Machine Stock List: ");
        displayStock();


        displayTestFrame("FINISHED TESTING GETTERS");
    }

}