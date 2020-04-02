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
    private int userMoneyInt;
    private Status vmStatus;
    private VendItem[] stock;
    private static ArrayList<Integer> acceptedCoins;
    private ArrayList<Integer> inputCoins;
    private ArrayList<Integer> totalCoins;
    private ArrayList<Integer> returnedCoins;
    private ArrayList<Integer> missingCoins;
    //private int totalStockCount;

    //TODO If machine can't give change, say "cannot give change"; do this by checking if change is required, then if returned coins size = 0 then say that
    //TODO otherwise say no change required
    public VendingMachine(String owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
        this.vmStatus = Status.SERVICE_MODE;
        stock = new VendItem[maxItems];
        inputCoins = new ArrayList<Integer>();
        returnedCoins = new ArrayList<Integer>();
        totalCoins = new ArrayList<Integer>();
        acceptedCoins = new ArrayList<>(Arrays.asList(1,2,5,10,20,50));
        missingCoins = new ArrayList<Integer>();
        initTotalCoins();
    }

    private void initTotalCoins() {
        for(int index = 0; index<=10; index++) {
            for(int coinValue=0;coinValue<=50;coinValue++) {
                if(coinValue==2 || coinValue==1 || coinValue==50||coinValue==20||coinValue==10||coinValue==5) {
                    totalCoins.add(coinValue);
                }
            }
        }
    }

    public String getSystemInfo() {
        String res = "\nOwner: " + owner + "\n";
        res += "Max items: " + maxItems + "\n";
        res += "Current item count: " + itemCount + "\n";
        res += String.format("Current total funds: £%.2f\n", totalMoney);
        res += String.format("Current user funds: £%.2f\n", userMoney);
        res += this.getVmStatus().getStatus();
        res += "\n\n       ITEM LIST\n";
        res += "       +++++++++\n";
        for (String itemDetail : this.listItems()) {
            res += itemDetail + "\n";
        }
        return res;
    }

    public void reset() {
        //*Reset variables here
        stock = new VendItem[maxItems];
        totalMoney = 0.0;
        userMoney = 0.0;
        setVmStatus(Status.SERVICE_MODE);
    }

    private void chooseReturnCoins() {
        //TODO Stop the infinite loop with a break
        while(userMoneyInt >= 200) {
            if(totalCoins.contains(2)) {
                returnedCoins.add(2);
                totalCoins.remove(totalCoins.indexOf(2));
                userMoneyInt-=200;
            }
            else {
                missingCoins.add(2);
                userMoneyInt-=200;
                break;
            }
        }
        while(userMoneyInt >= 100) {
            if(totalCoins.contains(1)) {
                returnedCoins.add(1);
                totalCoins.remove(totalCoins.indexOf(1));
                userMoneyInt-=100;
            }
            else {
                missingCoins.add(1);
                userMoneyInt-=100;
                break;
            }
        }
        while(userMoneyInt >= 50) {
            if(totalCoins.contains(50)) {
                returnedCoins.add(50);
                totalCoins.remove(totalCoins.indexOf(50));
                userMoneyInt-=50;
            }
            else {
                missingCoins.add(50);
                userMoneyInt-=50;
                break;
            }
        }
        while(userMoneyInt >= 20) {
            if(totalCoins.contains(20)) {
                returnedCoins.add(20);
                totalCoins.remove(totalCoins.indexOf(20));
                userMoneyInt-=20;
            }
            else {
                missingCoins.add(20);
                userMoneyInt-=20;
                break;
            }
        }
        while(userMoneyInt >= 10) {
            if(totalCoins.contains(10)) {
                returnedCoins.add(10);
                totalCoins.remove(totalCoins.indexOf(10));
                userMoneyInt-=10;
            }
            else {
                missingCoins.add(10);
                userMoneyInt-=10;
                break;
            }
        }
        while(userMoneyInt >= 5) {
            if(totalCoins.contains(5)) {
                returnedCoins.add(5);
                totalCoins.remove(totalCoins.indexOf(5));
                userMoneyInt-=5;
            }
            else {
                missingCoins.add(5);
                userMoneyInt-=5;
                break;
            }
        }
        // if(userMoneyInt%200!=0 && userMoneyInt%100!=0 && userMoneyInt%50!=0 && userMoneyInt%20!=0 && userMoneyInt%10!=0 && userMoneyInt%5!=0) {
        //     break;
        // }
    }

    public int getUserMoneyInt() {
        return this.userMoneyInt;
    }
    
    public String purchaseItem(int choiceId) {
        //should use decrement() method
        //also if qtyRemaining = 0 then fail state

        userMoneyInt = (int)Math.round((userMoney*100));
        VendItem itemToPurchase;
        //TODO REMOVE FROM PART01
        //System.out.println(totalCoins.containsAll(acceptedCoins));

        // if(!totalCoins.containsAll(acceptedCoins)) {
        //     this.setVmStatus(Status.SERVICE_MODE);
        //     return "Machine does not have enough coins to give operate.\nSwitching to SERVICE MODE.";
        // }

        try {
            itemToPurchase = findItem(choiceId);
        } catch (NullPointerException e) {
            return "This item does not exist.";
        }

        if(itemToPurchase == null) {
            //System.out.println("helloo");
            return "This product does not exist.";
        }

        if(this.getVmStatus() == Status.SERVICE_MODE) {
            return "This machine is in service mode.";
        }

        if(this.getAllStockQty() == 0) {
            this.setVmStatus(Status.SERVICE_MODE);
        }

        if(userMoneyInt >= (int)itemToPurchase.getPrice()*100) {
            
            String deliver = itemToPurchase.deliver();
            if(itemToPurchase.decrement()) {
                //double change = userMoney-itemToPurchase.getPrice();
                userMoneyInt -= (int)(itemToPurchase.getPrice()*100);
                userMoney = (double)userMoneyInt/100;
                //totalStockCount--;
                //this.setVmStatus(Status.SERVICE_MODE);
                // for (VendItem vendItem : stock) {
                //     if(vendItem != null && vendItem.getQty() > 0) {
                //         this.setVmStatus(Status.VENDING_MODE);
                //     }
                // }
                String res = "";
                if(userMoney > 0.0) {
                    res += String.format("%s\nYour change is £%.2f.\nYour change consists of: ", deliver, userMoney);
                } 
                else {
                    res += String.format("%s\nYour transaction returned no change.", deliver);
                }
                
                    //TODO Need to format the coins here
                    //return inputCoinsStr.toString().replace("[", "").replace("]", "");

                
                totalMoney -= userMoney;
                while(userMoneyInt>0) {
                    chooseReturnCoins();
                }
                
                userMoney = 0.0;
                //System.out.println(returnedCoins);

                res += formatCoins(this.returnedCoins) + "\n";
                if(missingCoins.size()>0) {
                    res += "\nUnfortunately, the machine did not contain: " + formatCoins(missingCoins) + ", and thus could not return all change.\n";
                }
                
                res += "\nNow dispensing.";

                
                inputCoins.clear();
                returnedCoins.clear();
                missingCoins.clear();
                if(this.getAllStockQty() == 0) {
                    this.setVmStatus(Status.SERVICE_MODE);
                    res += "\n\n! Machine is out of stock. Switching to service mode. !";
                }
                return res;
            }
            else {
                return "None of " + itemToPurchase.getName() + " left. Please choose another item.";
            }
        }
        return "Not enough funds to purchase this item.";
    }


    public static String formatCoins(ArrayList<Integer> coinList) {
        String res = "";
        if(coinList.size() > 0) {

            for(int index=0; index<=coinList.size()-1; index++) {
                int coin = coinList.get(index);
                if(coin < 5) {
                    res += String.format("£%d", coin);
                }
                else if(coin > 2) {
                    res += String.format("%dp", coin); 
                }
                //System.out.println(returnedCoins.indexOf(coin));
                if(index != coinList.size()-1) {
                    res += ", ";
                }
                //!Maybe change this to just else?
                //!Also maybe just add coin to string instead of breaking up res var
                else if(coinList.indexOf(coin) == coinList.size()-1) {
                    res += "";
                }
            }
            
        }
        return res;
    }

    public Status getVmStatus() {
        return this.vmStatus;
    }

    public boolean insertCoin(int amount) {
        double dAmount = amount;
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
            totalCoins.add(amount);
            //TODO REMOVE THIS FROM PART 1 TOO
            //System.out.println(totalCoins);
            return true;
        }
        return false;
    }

    private void sortStock() {
        int swaps;
        do {
            swaps=0;
            for (int index = 0; index < stock.length-1; index++) {
                if(stock[index] != null && stock[index+1] != null) {
                    if(stock[index].getItemId() > stock[index+1].getItemId()) {
                        VendItem temp = stock[index];
                        stock[index] = stock[index+1];
                        stock[index+1] = temp;
                        swaps++;
                    }
                }
                
            }
            
        } while (swaps>0);
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
                    this.sortStock();
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

        //TODO For some reason the itemId=10 here is just ignoring that it's null?
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
        if(target == null) {
            throw new NullPointerException("Item not found.");
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
    //TODO make private - Maybe not cause it's setter?
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

    //TODO make private - Maybe not cause it's setter?
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

    //TODO make private - Maybe not cause it's setter?
    public void setTotalMoney(double totalMoney) {
        if(totalMoney > 0.0) {
            this.totalMoney = totalMoney;
        }
        else {
            this.totalMoney = 0.0;
        }
    }

    //TODO make private - Maybe not cause it's setter?
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

    //TODO make private - Maybe not cause it's setter?
    public void setStock(VendItem[] stock) {
        this.stock = stock;
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

    public ArrayList<Integer> getTotalCoins() {
        return totalCoins;
    }

    public ArrayList<Integer> getAcceptedCoins() {
        return acceptedCoins;
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