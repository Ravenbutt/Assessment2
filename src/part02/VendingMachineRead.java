package part02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class VendingMachineRead {

    

    public static void main(String[] args) {
        loadData(parseData());
    }


    //Getting data from csv, turning it into ArrayList
    public static ArrayList<String> parseData() {
        String csvPath = "VendingState.csv";
        String testData = null;
        Scanner sc;
        
        File csvFile = new File(csvPath);
        try {
            sc = new Scanner(csvFile);
            while(sc.hasNextLine()) {
                testData = sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        StringTokenizer stringTok = new StringTokenizer(testData, ",[]", true);
        
        ArrayList<String> testDataList = new ArrayList<String>();

        while(stringTok.hasMoreTokens()) {
            String currentToken = stringTok.nextToken();
            if(currentToken.equals(",")) {
                continue;
            }
            else {
                testDataList.add(currentToken.trim());
            }
            
        }

        return testDataList;
    }


    //Creating VendingMachine from data
    public static VendingMachine loadData(ArrayList<String> testDataList) {
        ArrayList<String> readList = testDataList;
        String name = readList.get(0);
        int maxItems = Integer.parseInt(readList.get(1));
        int currentItems = Integer.parseInt(readList.get(2));
        double totalMoney = Double.parseDouble(readList.get(3));
        double userMoney = Double.parseDouble(readList.get(4));
        Status vmStatus = null;

        if(readList.get(5).equals("VENDING_MODE")) {
            vmStatus = Status.VENDING_MODE;
        }
        else if(readList.get(5).equals("SERVICE_MODE")) {
            vmStatus = Status.SERVICE_MODE;
        }

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

    //Saves state of input VendingMachine and writes to CSV
    public static void saveState(VendingMachine vendingMachine) {
        //Maybe say VendingMachine extends this so it can have saveState?
        String toWrite = vendingMachine.getDetails();
        exportData(toWrite);
    }

    public static boolean exportData(String toWrite) {
        String csvOutPath = "VendingState.csv";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(csvOutPath);
        } catch (FileNotFoundException e) {
            System.err.println(e.getStackTrace());
        }
        writer.println(toWrite);
        System.out.printf("State written successfully to %s\n", csvOutPath);
        writer.close();
        return true;
    }

}