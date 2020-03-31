package part02;

public class TestFindItem {
    public static void main(String[] args) {
        VendingMachine venMac = new VendingMachine("Hello", 10);
        VendItem testItem = new VendItem("Cola", 2,5);
        venMac.addNewItem(testItem);
        venMac.setVmStatus(Status.VENDING_MODE);
        System.out.println(venMac.findItem(1));
        venMac.insertCoin(2);
        
        System.out.println(venMac.purchaseItem(11));
        venMac.insertCoin(2);
        venMac.insertCoin(10);
        venMac.insertCoin(20);
        System.out.println(venMac.getUserMoney());
        System.out.println(venMac.purchaseItem(1));
        //venMac.findItem(2);
    }
}