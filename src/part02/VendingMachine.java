package part02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


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
    private ArrayList<Integer> returnedCoins;
    //private int totalStockCount;

    public VendingMachine(String owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
        this.vmStatus = Status.SERVICE_MODE;
        stock = new VendItem[maxItems];
        inputCoins = new ArrayList<Integer>();
        returnedCoins = new ArrayList<Integer>();
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
        setVmStatus(Status.SERVICE_MODE);
    }

    public String purchaseItem(int choiceId) {
        //should use decrement() method
        //also if qtyRemaining = 0 then fail state

        VendItem itemToPurchase = findItem(choiceId);
        if(this.getVmStatus() == Status.SERVICE_MODE) {
            return "This machine is in service mode.";
        }

        if(this.getAllStockQty() == 0) {
            this.setVmStatus(Status.SERVICE_MODE);
        }

        if(userMoney >= itemToPurchase.getPrice()) {
            String deliver = itemToPurchase.deliver();
            if(itemToPurchase.decrement()) {
                userMoney -= itemToPurchase.getPrice();
                //totalStockCount--;
                //this.setVmStatus(Status.SERVICE_MODE);
                // for (VendItem vendItem : stock) {
                //     if(vendItem != null && vendItem.getQty() > 0) {
                //         this.setVmStatus(Status.VENDING_MODE);
                //     }
                // }
                String res = String.format("%s\nYour change is £%.2f . \nNow dispensing.", deliver, userMoney);
                for (Integer coin : returnedCoins) {
                    //TODO Need to format the coins here
                    res += String.format(l, format, args)
                }
                System.out.println(userMoney);
                totalMoney -= userMoney;
                if(userMoney >= 2.0) {
                    returnedCoins.add(2);
                    userMoney-=2.0;
                }
                if(userMoney >= 1.0) {
                    returnedCoins.add(1);
                    userMoney-=1.0;
                }
                if(userMoney >= 0.5) {
                    returnedCoins.add(50);
                    userMoney-=0.5;
                }
                if(userMoney >= 0.2) {
                    returnedCoins.add(20);
                    userMoney-=0.2;
                }
                if(userMoney >= 0.1) {
                    returnedCoins.add(10);
                    userMoney-=0.1;
                }
                if(userMoney >= 0.05) {
                    returnedCoins.add(5);
                    userMoney-=0.05;
                }
                
                
                userMoney = 0.0;
                
                inputCoins.clear();
                if(this.getAllStockQty() == 0) {
                    this.setVmStatus(Status.SERVICE_MODE);
                    res += "\nMachine is out of stock. Switching to service mode.";
                }
                return res;
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
                    //totalStockCount += newItem.getQty();
                    return true;
                }
            }
        }
        return false;
    }

    public String[] listItems() {
        String items[] = new String[itemCount];
        for (int index = 0; index < itemCount; index++) {
            VendItem currentItem = stock[index];
            if(stock[index] != null) {
                String currentItemString = String.format("%d. %s  \n   Price: £%.2f \n   Quantity Remaining: %d\n", currentItem.getItemId(), currentItem.getName(), currentItem.getPrice(), currentItem.getQty());
                items[index] = currentItemString;
            }
            
        }
        return items;
    }

    //Used in menu to get all stock
    public VendItem[] getStock() {
        return stock;
    }

    public boolean setVmStatus(Status vStatus) {
        int totalQty = getAllStockQty();
        if(totalQty == 0 && vStatus == Status.VENDING_MODE) {
            this.vmStatus = Status.SERVICE_MODE;
            return false;
        }
        this.vmStatus = vStatus;
        return true;
    }

    private int getAllStockQty() {
        int totalQty = 0;
        for (VendItem item : stock) {
            if(item != null) {
                totalQty += item.getQty();
            }
        }
        return totalQty;
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


    // protected void saveState() throws FileNotFoundException {
    //     try {
    //         String stateDir = "vendingState.txt";
    //         PrintWriter savePw = new PrintWriter(stateDir);
    //         savePw.println(this.toString());
    //         savePw.close();
    //     } catch (FileNotFoundException e) {
    //         System.out.println(e.getStackTrace());
            
    //     }
    // }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(int maxItems) {
        if(maxItems > 0) {
            this.maxItems = maxItems;
        }
        else {
            this.maxItems = 0;
        }
        
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        if(itemCount < maxItems && itemCount >= 0) {
            this.itemCount = itemCount;
        }
        else {
            this.itemCount = 0;
        }
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        if(totalMoney > 0.0) {
            this.totalMoney = totalMoney;
        }
        else {
            this.totalMoney = 0.0;
        }
    }

    public void setUserMoney(double userMoney) {
        if(userMoney > 0.0) {
            this.userMoney = userMoney;
        }
        else {
            this.userMoney = 0.0;
        }

    }

    // public void setVmStatus(Status vmStatus) {
    //     this.vmStatus = vmStatus;
    // }

    public void setStock(VendItem[] stock) {
        this.stock = stock;
    }

    public ArrayList<Integer> getAcceptedCoins() {
        return acceptedCoins;
    }

    public String getDetails() {
        String res = String.format("%s,%d,%d,%f,%f,%s,", owner, maxItems, itemCount, totalMoney, userMoney, vmStatus);
        for (VendItem vendItem : stock) {
            
            if(vendItem != null) {
                res += vendItem.getData() + ",";
            }
            
        }
        return res;
    }

    // public int getTotalStockCount() {
    //     return totalStockCount;
    // }


    // protected void loadState(String owner, int maxItems, int itemCount, double totalMoney, double userMoney, Status vmStatus, VendItem[] stock) {
    //     setOwner(owner);
    //     setMaxItems(maxItems);
    //     setItemCount(itemCount);
    //     setTotalMoney(totalMoney);
    //     setUserMoney(userMoney);
    //     setVmStatus(vmStatus);
    //     setStock(stock);
    // }

}

//Composition relationship as this class as parent

//Add check to see if item is already there or not when adding vendItem