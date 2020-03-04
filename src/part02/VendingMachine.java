package part02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

public class VendingMachine {
    private String owner; 
    private int maxItems; //max amount of items the machine can hold
    private int itemCount; //amount of items (VendItems) currently for sale
    private double totalMoney;
    private double userMoney;
    private Status vmStatus;
    private VendItem[] stock;
    private static ArrayList<Integer> acceptedCoins;
    private ArrayList<Integer> inputCoins;

    public VendingMachine(String owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
        this.vmStatus = Status.SERVICE_MODE;
        stock = new VendItem[maxItems];
        inputCoins = new ArrayList<Integer>();
    }

    public String getSystemInfo() {
        String res = "Owner: " + owner + "\n";
        res += "Max items: " + maxItems + "\n";
        res += "Current item count: " + itemCount + "\n";
        res += String.format("Current total funds: £%.2f\n", totalMoney);
        res += String.format("Current user funds: £%.2f\n", userMoney);
        res += this.getVmStatus().getStatus();
        return res;
    }

    public void reset() {
        //*Reset variables here
        stock = new VendItem[maxItems];
        totalMoney = 0.0;
        userMoney = 0.0;
        setStatus(Status.SERVICE_MODE);
    }

    public String purchaseItem(int choiceId) {
        //should use decrement() method
        //also if qtyRemaining = 0 then fail state

        VendItem itemToPurchase = findItem(choiceId);
        if(this.getVmStatus() == Status.SERVICE_MODE) {
            return "This machine is in service mode.";
        }
        if(userMoney >= itemToPurchase.getPrice()) {
            if(itemToPurchase.decrement()) {
                userMoney -= itemToPurchase.getPrice();
                itemToPurchase.deliver();
                this.setStatus(Status.SERVICE_MODE);
                for (VendItem vendItem : stock) {
                    if(vendItem != null && vendItem.getQty() > 0) {
                        this.setStatus(Status.VENDING_MODE);
                    }
                }
                return "Item purchased. Now dispensing.";
            }
            else {
                return "None of " + itemToPurchase.getName() + " left. Please choose another item.";
            }
        }
        return "Not enough funds to purchase this item.";
    }

    public Status getVmStatus() {
        return this.vmStatus;
    }

    public boolean insertCoin(int amount) {
        double dAmount = amount;
        acceptedCoins = new ArrayList<>(Arrays.asList(1,2,5,10,20,50));
        if(this.vmStatus == Status.SERVICE_MODE) {
            return false;
        }
        if(acceptedCoins.contains(amount)) {
            if(dAmount > 2) {
                dAmount /= 100;
            }
            userMoney += dAmount;
            totalMoney += dAmount;
            inputCoins.add(amount);
            return true;
        }
        return false;
    }

    public boolean addNewItem(VendItem newItem) {
        //Maybe only allow adding item when in service mode?
        if(newItem != null) {
            for (int index = 0; index < stock.length; index++) {
                if(stock[index] == newItem || stock[index] != null && stock[index].getName().equalsIgnoreCase(newItem.getName())) {
                    return false;
                }
            }
            for (int j = 0; j < stock.length; j++) {
                if(stock[j] == null) {
                    stock[j] = newItem;
                    itemCount += 1;
                    return true;
                }
            }
        }
        return false;
    }

    public String[] listItems() {
        String nameArray[] = new String[itemCount];
        for (int index = 0; index < itemCount; index++) {
            if(stock[index] != null) {
                String currentItem = stock[index].getItemId() + ". " 
                + stock[index].getName() + " - Quantity Remaining: " + stock[index].getQty();
                nameArray[index] = currentItem;
            }
            
        }
        return nameArray;
    }

    //Used in menu to get all stock
    public VendItem[] getStock() {
        return stock;
    }

    public void setStatus(Status vStatus) {
        this.vmStatus = vStatus;
    }



    public VendItem findItem(int itemId) throws NullPointerException {
        VendItem target = null;
        for(int index = 0; index < stock.length; index++) { //might need to change this to length-1 idk
            VendItem currItem = stock[index];
            if(currItem == null) {
                throw new NullPointerException("Item not found.");
            }
            if (currItem.getItemId() == itemId) {
                target = currItem;
                break;
            }
        }
		
		return target;
    }

    public boolean removeItem(int itemId) {
        VendItem item = findItem(itemId);
        if(item != null) {
            for (int i = 0; i < stock.length; i++) {
                
            }
        }
        return true;
    }

    public double getUserMoney() {
        return userMoney;
    }

    public ArrayList<Integer> getInputCoins() {
        return inputCoins;
    }

    @Override
    public String toString() {
        String res = owner + "," + maxItems + "," + itemCount + "," + totalMoney + "," + userMoney + "," + vmStatus + ",";
        for (VendItem vendItem : stock) {
            if(vendItem != null) {
                res += vendItem + ",";
            }
            
        }
        return res;
    }


    protected void saveState() throws FileNotFoundException {
        try {
            String stateDir = "vendingState.txt";
            PrintWriter savePw = new PrintWriter(stateDir);
            savePw.println(this.toString());
            savePw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
            
        }
    }

    protected static VendingMachine loadState() {

        try {
            File stateDir = new File("vendingState.txt");
            Scanner mySc = new Scanner(stateDir);

            while (mySc.hasNextLine()) {
                System.out.println(mySc.nextLine());
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        return null;
    }

}

//Composition relationship as this class as parent