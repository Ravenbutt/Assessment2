package part02;

public class VendingMachineTester {
    public static void main(String[] args) {
        VendingMachine myVen = new VendingMachine("Awndy", 40);
        VendItem myItem = new VendItem("Hello", 2);
        VendItem myItem2 = new VendItem("crisps", 3);
        VendItem myItem3 = new VendItem("test", 2);
        myVen.addNewItem(myItem);
        myVen.addNewItem(myItem2);
        myVen.addNewItem(myItem3);
        System.out.println(myVen.getDetails());
    }
}