package part01;

public class VendItemTester {
    public static void main(String[] args) {
        VendItem testItem1 = new VendItem("Coke Zero", 2.0);
        testItem1.restock(20);
        System.out.println(testItem1.getDetails());
        VendItem testItem2 = new VendItem("Fanta Lemon", 1.35, 10);
        
        //Testing decrement method
        System.out.println("Qty before decrement: " + testItem2.getQty());
        System.out.println("Decrement returned: " + testItem2.decrement());
        System.out.println("Qty after decrement: " + testItem2.getQty());
        System.out.println("Decrement on empty item: " + testItem1.decrement());


        System.out.println("Deliver on empty item: " + testItem1.deliver());
    }
}