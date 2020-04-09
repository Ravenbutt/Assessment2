package part02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Class to facilitate the reading of VendingMachine data from a csv file
 * 
 * @author Andrew Ellis
 * @version V1.1
 */
public class VendingMachineRead {

    /**
     * Method to read data from a csv file and parses the data into an ArrayList
     * 
     * @return ArrayList of type String containing each comma separated value
     */
    public static ArrayList<String> parseData() {
        String csvPath = "VendingState.csv";
        String readData = null;
        Scanner sc;

        File csvFile = new File(csvPath);

        // Checks if file contains nothing
        if (csvFile.length() == 0) {
            return null;
        }

        try {
            sc = new Scanner(csvFile);
            while (sc.hasNextLine()) {
                readData = sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Splits the data String read from file on comma and square brackets
        // Final parameter tells Tokenizer to keep the delimiters
        StringTokenizer dataTokenized = new StringTokenizer(readData, ",[]", true);

        ArrayList<String> dataList = new ArrayList<String>();

        while (dataTokenized.hasMoreTokens()) {
            String currentToken = dataTokenized.nextToken();
            if (currentToken.equals(",")) {
                continue;
            } else {
                dataList.add(currentToken.trim());
            }
        }
        return dataList;
    }

    /**
     * Method that creates and returns a new VendingMachine instance from the data
     * obtained from parseData()
     * 
     * @param listData ArrayList of type String containing the data to load
     * @return new VendingMachine to with the data which was loaded from file
     */
    public static VendingMachine loadData(ArrayList<String> listData) {

        
        // Tries to validate csv data; first 17 values in listData are VendingMachine 
        // related, every 4 after that is stock related - just tries to make sure no data 
        // has been cut off but is not fool proof
        if (listData == null || listData.subList(18, listData.size()).size() % 4 != 0) {
            return null;
        }

        // First 5 values in data list are name, maxItems, currentItems,
        // totalMoney and userMoney
        String name = listData.get(0);
        int maxItems = Integer.parseInt(listData.get(1));
        int currentItems = Integer.parseInt(listData.get(2));
        double totalMoney = Double.parseDouble(listData.get(3));
        double userMoney = Double.parseDouble(listData.get(4));

        // Using MoneyBoxes loadFromArray() method to load the total and
        // input coins
        MoneyBox totalCoins = new MoneyBox();
        MoneyBox inputCoins = new MoneyBox();
        totalCoins.loadFromArray(listData.subList(5, 11).toArray(new String[6]));
        inputCoins.loadFromArray(listData.subList(11, 17).toArray(new String[6]));

        Status vmStatus = null;

        // Using Status' fromString() method to try get the status; if it 
        // doesn't work for some reason, then will just set service mode
        if(Status.fromString(listData.get(17)) != null) {
            vmStatus = Status.fromString(listData.get(17));
        }
        else {
            vmStatus = Status.SERVICE_MODE;
        }

        // Every 4 elements in listData from 18 until end is regarding a
        // VendItem, so 18 would be itemId, 19 name, 20 itemCost, 21 qtyAvailable 
        VendItem stock[] = new VendItem[maxItems];
        for (int j = 18; j < listData.size(); j += 4) {

            // Created a sublist of all the state data to make it simpler
            List<String> currentItemList = listData.subList(j, j + 4);
            int itemId = Integer.parseInt(currentItemList.get(0));
            String itemName = currentItemList.get(1);
            double itemCost = Double.parseDouble(currentItemList.get(2));
            int qtyAvailable = Integer.parseInt(currentItemList.get(3));

            VendItem loadedItem = VendItem.loadState(itemId, itemName, itemCost, qtyAvailable);

            // Setting the stock array of VendingMachine instance
            // to contain the loaded item
            for (int i = 0; i < currentItems; i++) {
                if (stock[i] == null) {
                    stock[i] = loadedItem;
                    break;
                }
            }
        }

        // Finally instantiating the new VendingMachine instance and setting
        // the loaded data
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