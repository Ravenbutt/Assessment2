package part01;

public class VendItemTester {
    public static void main(String[] args) {
        VendItem myItem = new VendItem("Coke Zero", 2.0);
        myItem.restock(20);
        System.out.println(myItem.getDetails());

    }
}