package part02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Class to facilitate the reading of VendingMachine data from a csv file
 * @author Andrew Ellis
 * @version V1.1
 */
public class VendingMachineRead {

    /**
     * Method to read data from a csv file and parses the data into an ArrayList
     * @return ArrayList of type String containing each comma separated value
     */
    public static ArrayList<String> parseData() {
        String csvPath = "VendingState.csv";
        String testData = null;
        Scanner sc;
        
        File csvFile = new File(csvPath);
        if(csvFile.length() == 0) {
            return null;
        }
        try {
            sc = new Scanner(csvFile);
            while(sc.hasNextLine()) {
                testData = sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringTokenizer dataTokenized = new StringTokenizer(testData, ",[]", true);
        
        ArrayList<String> dataList = new ArrayList<String>();

        while(dataTokenized.hasMoreTokens()) {
            String currentToken = dataTokenized.nextToken();
            if(currentToken.equals(",")) {
                continue;
            }
            else {
                dataList.add(currentToken.trim());
            }
        }
        return dataList;
    }

    /**
     * Method that creates and returns a new VendingMachine instance from the data
     * obtained from parseData()
     * @param listData ArrayList of type String containing the data to load
     * @return new VendingMachine to with the data which was loaded from file
     */
    public static VendingMachine loadData(ArrayList<String> listData) {
        
        /**
         * The second condition in this if statement is a check to try and ensure the integrity of the csv data
         * The first 17 values of the csv file are regarding the VendingMachine itself, and the following n values
         * are any amount of VendItem(s). Each VendItem has 4 data values each, so this is an attempt to ensure each one 
         * is 4 values long, but is not foolproof
         */
        if(listData == null || listData.subList(18, listData.size()).size() %4 != 0) {
            return null;
        }
        String name = listData.get(0);
        int maxItems = Integer.parseInt(listData.get(1));
        int currentItems = Integer.parseInt(listData.get(2));
        double totalMoney = Double.parseDouble(listData.get(3));
        double userMoney = Double.parseDouble(listData.get(4));
        MoneyBox totalCoins = new MoneyBox();
        MoneyBox inputCoins = new MoneyBox();
        totalCoins.loadFromArray(listData.subList(5, 11).toArray(new String[6]));
        inputCoins.loadFromArray(listData.subList(11, 17).toArray(new String[6]));
        Status vmStatus = null;

        if(listData.get(17).equals("VENDING_MODE")) {
            vmStatus = Status.VENDING_MODE;
        }
        else if(listData.get(17).equals("SERVICE_MODE")) {
            vmStatus = Status.SERVICE_MODE;
        }

        VendItem stock[] = new VendItem[maxItems];

        for (int j = 18; j < listData.size(); j+=4) {
            List<String> currentItemList = listData.subList(j, j+4);

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
    loadedMachine.setInputCoins(inputCoins);
    loadedMachine.setTotalCoins(totalCoins);
    loadedMachine.setUserMoney(userMoney);
    loadedMachine.setStock(stock);
    loadedMachine.setStatus(vmStatus);

    return loadedMachine;

    }
}