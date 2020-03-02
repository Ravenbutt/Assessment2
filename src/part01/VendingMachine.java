package part01;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class VendingMachine {
    private String owner; 
    private int maxItems; //max amount of items the machine can hold
    private int itemCount; //amount of items (VendItems) currently for sale
    private double totalMoney;
    private double userMoney;
    private Status vmStatus;
    private VendItem[] stock;
    private int totalStockCount;

    public VendingMachine(String owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
        this.vmStatus = Status.SERVICE_MODE;
        stock = new VendItem[maxItems];
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
                return "Item purchased. Now dispensing.";
            }
            else {
                return "None of " + itemToPurchase.getName() + " left. Please choose another item.";
            }
        }
        return "Not enough funds to purchase this item.";
    }

    public void checkStock() {
        int totalStock = 0;
        for (VendItem item : stock) {
            if(item != null && item.getQty() != 0) {
                totalStock += item.getQty();
            }
        }
        if(totalStock == 0) {
            this.setStatus(Status.SERVICE_MODE);
        }
    }

    public Status getVmStatus() {
        return this.vmStatus;
    }

    public boolean insertCoin(int amount) {
        double dAmount = amount;
        if(this.vmStatus == Status.SERVICE_MODE) {
            return false;
        }
        if(dAmount == 1 || dAmount == 2 || dAmount == 5 || dAmount == 10 || dAmount == 20 || dAmount == 50) {
            if(dAmount > 2) {
                dAmount /= 100;
            }
            userMoney += dAmount;
            totalMoney += dAmount;
            return true;
        }
        return false;
    }

    public boolean addNewItem(VendItem newItem) {
        //Maybe only allow adding item when in service mode?
        if(newItem != null) {
            for (int index = 0; index < stock.length; index++) {
                if(stock[index] == newItem) {
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
        String nameArray[] = new String[stock.length];
        for (int index = 0; index < stock.length; index++) {
            System.out.println(stock[index]);
            // nameArray[index] = stock[index].getName();
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

}

//Composition relationship as this class as parent