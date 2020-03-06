package part02;

import java.util.ArrayList;
import java.util.List;

public class ReadWriteTester {

    // public static VendingMachine readData() {
    //     ArrayList<String> readList = VendingMachineRead.parseData();
    //     String name = readList.get(0);
    //     int maxItems = Integer.parseInt(readList.get(1));
    //     int currentItems = Integer.parseInt(readList.get(2));
    //     double totalMoney = Double.parseDouble(readList.get(3));
    //     double userMoney = Double.parseDouble(readList.get(4));
    //     Status vmStatus = Status.fromString(readList.get(5));
    //     VendItem stock[] = new VendItem[maxItems];
    //     List<String> vendingMachineData = readList.subList(0, 6);
    //     System.out.println(vendingMachineData);
    
    //         for (int j = 6; j < readList.size(); j+=4) {
    //             List<String> currentItemList = readList.subList(j, j+4);
    //             //String itemName = currentItemList.sub
    //             System.out.println(readList.subList(j, j+4));
    //             int itemId = Integer.parseInt(currentItemList.get(0));
    //             String itemName = currentItemList.get(1);
    //             double itemCost = Double.parseDouble(currentItemList.get(2));
    //             int qtyAvailable = Integer.parseInt(currentItemList.get(3));
    //             VendItem loadedItem = VendItem.loadState(itemId, itemName, itemCost, qtyAvailable);
    //             System.out.println(loadedItem);
    //             for (VendItem item : stock) {
    //                 if(item == null) {
    //                     item = loadedItem;
    //                     continue;
    //                 }
    //                 else {
    //                     continue;
    //                 }
    //             }
    //         }

    //     VendingMachine loadedMachine = new VendingMachine(name, maxItems);
    //     loadedMachine.setItemCount(currentItems);
    //     loadedMachine.setTotalMoney(totalMoney);
    //     loadedMachine.setUserMoney(userMoney);
    //     loadedMachine.setVmStatus(vmStatus);
    //     loadedMachine.setStock(stock);

    //     return loadedMachine;


    public static void main(String[] args) {

        //readData();
        //System.out.println(readData());

        //VendingMachine.loadState(name, maxItems, currentItems, totalMoney, userMoney, vmStatus, stock);

    }

}