package part01;

public class VendingMachineTester {
    public static void main(String[] args) {
        VendingMachine myVen = new VendingMachine("Awndy", 40);
        VendItem myItem = new VendItem("Hello", 2);
        myVen.addNewItem(myItem);
    }
}