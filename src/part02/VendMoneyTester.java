package part02;

public class VendMoneyTester {
    public static void main(String[] args) {
        VendingMachine myVend = new VendingMachine("Hello", 10);
        VendItem testItem = new VendItem("Coke Zero", 0.70,10);
        
        myVend.addNewItem(testItem);
        myVend.setVmStatus(Status.VENDING_MODE);
        myVend.insertCoin(1);
        myVend.insertCoin(2);
        
        //printArr(myVend.getInputCoins());

        System.out.println(myVend.purchaseItem(1));
        System.out.println("BROKEN VALUE: " + MoneyBox.intToDenoms(220));
    }

    private static void printArr(int[] array) {
        System.out.print("INPUT COINS ORIG: ");
        for (int item : array) {
            System.out.print(item + ", ");
        }
        System.out.println("\n");
    }
}