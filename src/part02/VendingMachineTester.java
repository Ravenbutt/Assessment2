package part02;

public class VendingMachineTester {
    public static void main(String[] args) {
        VendingMachine testingMachine = new VendingMachine("Testing Machine", 10);


        //Adding initial correct case items
        VendItem testItem1 = new VendItem("Coca Cola", 1.45);
        VendItem testItem2 = new VendItem("Tayto Prawn and Cocktail", 0.70);
        VendItem testItem3 = new VendItem("Fanta Lemon", 1.35);

        //Item with quantity specified in overloaded constructor
        VendItem testItem4 = new VendItem("Cadbury's Dairy Milk", 0.70, 1);

        testingMachine.addNewItem(testItem1);
        testingMachine.addNewItem(testItem2);
        testingMachine.addNewItem(testItem3);
        //Adding item with quantity specified
        testingMachine.addNewItem(testItem4);




        System.out.println("----     RESTOCKING VENDITEM    ----");
        //Restocking an item; it should appear as stocked in the stock list even after adding

        System.out.println("Result of testItem1.restock(2): " + testItem1.restock(2));
        System.out.println("Qty of testItem1 according to testingMachine: " + testingMachine.findItem(1).getQty() + "\n");

        //Attempting to restock more than 10 of VendItem will return false
        System.out.println("Result of testItem2.restock(20): " + testItem2.restock(20));
        System.out.println("testItem2 qty: " + testItem2.getQty() + "\n");

        //Attempting to restock less than 1 VendItem will return false
        System.out.println("Result of testItem2.restock(-1): " + testItem2.restock(-1));
        System.out.println("testItem2 qty: " + testItem2.getQty());

        System.out.println("----   END RESTOCKING VENDITEM  ----\n");


        
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


        System.out.println("----        SETTING STATUS        ----");
        System.out.println("Machine is in: " + testingMachine.getVmStatus());
        testingMachine.setVmStatus(Status.VENDING_MODE);
        System.out.println("Machine is in: " + testingMachine.getVmStatus());
        System.out.println("----      END SETTING STATUS      ----\n");


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
}