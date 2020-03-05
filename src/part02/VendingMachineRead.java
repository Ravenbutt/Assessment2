package part02;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class VendingMachineRead {
    public static ArrayList<String> parseData() {
        String testData = "Awndy,40,3,0.0,0.0,SERVICE_MODE,1,Hello,2.0,0,2,crisps,3.0,0,3,test,2.0,0,";
        StringTokenizer stringTok = new StringTokenizer(testData.trim(), ",[] ", true);
        
        ArrayList<String> testDataList = new ArrayList<String>();

        while(stringTok.hasMoreTokens()) {
            String currentToken = stringTok.nextToken();
            if(currentToken.trim().equals(",")) {
                continue;
            }
            else {
                testDataList.add(currentToken.trim());
            }
            
        }

        return testDataList;
    }

    public static VendingMachine loadData() {
        ArrayList<String> readList = parseData();
        String name = readList.get(0);
        int maxItems = Integer.parseInt(readList.get(1));
        int currentItems = Integer.parseInt(readList.get(2));
        double totalMoney = Double.parseDouble(readList.get(3));
        double userMoney = Double.parseDouble(readList.get(4));
        Status vmStatus = Status.fromString(readList.get(5));
        VendItem stock[] = new VendItem[maxItems];
        List<String> vendingMachineData = readList.subList(0, 6);

        for (int j = 6; j < readList.size(); j+=4) {
            List<String> currentItemList = readList.subList(j, j+4);

            int itemId = Integer.parseInt(currentItemList.get(0));
            String itemName = currentItemList.get(1);
            double itemCost = Double.parseDouble(currentItemList.get(2));
            int qtyAvailable = Integer.parseInt(currentItemList.get(3));
            VendItem loadedItem = VendItem.loadState(itemId, itemName, itemCost, qtyAvailable);

            for (int i = 0; i < currentItems; i++) {
                if(stock[i] == null) {
                    stock[i] = loadedItem;
                    break;
                }
            }
        }

    VendingMachine loadedMachine = new VendingMachine(name, maxItems);
    loadedMachine.setItemCount(currentItems);
    loadedMachine.setTotalMoney(totalMoney);
    loadedMachine.setUserMoney(userMoney);
    loadedMachine.setVmStatus(vmStatus);
    loadedMachine.setStock(stock);

    return loadedMachine;
    }

    public boolean saveState(VendingMachine vendingMachine) {
        //Maybe say VendingMachine extends this so it can have saveState?
        return true;
    }

}