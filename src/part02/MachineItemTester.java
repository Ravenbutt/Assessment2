package part02;

public class MachineItemTester {
    public static void main(String[] args) {
        VendingMachine testVendingMachine = new VendingMachine("Andrew Ellis", 20);
        VendItem cocaCola = new VendItem("Coca Cola 550ml", 1.35);
        VendItem fantaOrg = new VendItem("Fanta Orange 550ml", 1.35);
        VendItem taytoChsOni = new VendItem("Tayto Cheese & Onion", 0.80);
        VendItem galaxyChoc = new VendItem("Galaxy", 0.90);
        VendItem dairyMilk = new VendItem("Dairy Milk", 0.85);

        System.out.println("Printing all items before adding any VendItems:");
        for (String item : testVendingMachine.listItems()) {
            System.out.println(item);
        }

        testVendingMachine.addNewItem(cocaCola);
        testVendingMachine.addNewItem(fantaOrg);
        testVendingMachine.addNewItem(taytoChsOni);
        testVendingMachine.addNewItem(galaxyChoc);
        testVendingMachine.addNewItem(dairyMilk);

        System.out.println("After adding VendItems:");

        for (String item : testVendingMachine.listItems()) {
            System.out.println(item);
        }

        System.out.println("\nSetting qty of certain items");
        cocaCola.restock(10);
        fantaOrg.restock(-1);
        taytoChsOni.restock(1);
        galaxyChoc.restock(27);

        for (String item : testVendingMachine.listItems()) {
            System.out.println(item);
        }

    }
}