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
    private MoneyBox inputCoins;
    private MoneyBox totalCoins;


    //TODO If machine can't give change, say "cannot give change"; do this by checking if change is required, then if returned coins size = 0 then say that
    //TODO otherwise say no change required
    /**
     * Constructor for VendingMachine object - builds a new instance from the parameters
     * @param owner - The owner of the VendingMachine instance - input as String
     * @param maxItems - The max number of items the VendingMachine instance can contain - input as int
     */
    public VendingMachine(String owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
        this.vmStatus = Status.SERVICE_MODE;
        //Initialising the stock array to contain VendItem(s) up to max of maxItem
        stock = new VendItem[maxItems];
        //Coins will be stored using a MoneyBox
        inputCoins = new MoneyBox();
        totalCoins = new MoneyBox();
        //Using MoneyBox's toDouble() method to calculate totalMoney from inserted coins
        totalMoney = totalCoins.toDouble();
        //ArrayList to store the accepted coins
        acceptedCoins = new ArrayList<>(Arrays.asList(1,2,5,10,20,50));
        //Method call to store an initial amount of coins in the machine
        initTotalCoins();
    }

    /**
     * Private method to initially insert 10 of each coin into totalCoins
     */
    private void initTotalCoins() {
        for(int coinValue=0;coinValue<=50;coinValue++) {
            if(coinValue==2 || coinValue==1 || coinValue==50||coinValue==20||coinValue==10||coinValue==5) {
                totalCoins.addCoin(coinValue, 10);
                totalMoney = totalCoins.toDouble();
            }
        }
    }

    /**
     * Method to get all data pertaining to the VendingMachine instance
     * @return String - Containing the VendingMachine data
     */
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

    /**
     * Method used to reset the VendingMachine instance to it's default state
     */
    public void reset() {
        //*Reset variables here
        this.stock = new VendItem[maxItems];
        this.totalMoney = 0.0;
        this.totalCoins.clear();
        this.userMoney = 0.0;
        this.userMoneyInt = 0;
        this.inputCoins.clear();
        this.itemCount = 0;
        this.setVmStatus(Status.SERVICE_MODE);
    }

    /**
     * Private method which is used to work out the coins that are returned from a purchase
     * This also takes into account the coins that are in the machine; not returning a coin
     * if the machine doesn't contain it
     * @return MoneyBox - With the values for each coin returned
     */
    private MoneyBox chooseReturnCoins() {
        //TODO Stop the infinite loop with a break
        MoneyBox returnedCoins = new MoneyBox();
        
        while(userMoneyInt >= 200) {
            if(totalCoins.containsCoin(2)) {
                returnedCoins.addCoin(2);
                totalCoins.removeCoin(2);
                userMoneyInt-=200;
            }
            else {
                //missingCoins.add(2);
                //userMoneyInt-=200;
                inputCoins.removeCoin(2);
                break;
            }
        }
        while(userMoneyInt >= 100) {
            if(totalCoins.containsCoin(1)) {
                returnedCoins.addCoin(1);
                totalCoins.removeCoin(1);
                userMoneyInt-=100;
            }
            else {
                //missingCoins.add(1);
                //userMoneyInt-=100;
                break;
            }
        }
        while(userMoneyInt >= 50) {
            if(totalCoins.containsCoin(50)) {
                returnedCoins.addCoin(50);
                totalCoins.removeCoin(50);
                userMoneyInt-=50;
            }
            else {
                //missingCoins.add(50);
                //userMoneyInt-=50;
                break;
            }
        }
        while(userMoneyInt >= 20) {
            if(totalCoins.containsCoin(20)) {
                returnedCoins.addCoin(20);
                totalCoins.removeCoin(20);
                userMoneyInt-=20;
            }
            else {
                //missingCoins.add(20);
                //userMoneyInt-=20;
                break;
            }
        }
        while(userMoneyInt >= 10) {
            if(totalCoins.containsCoin(10)) {
                returnedCoins.addCoin(10);
                totalCoins.removeCoin(10);
                userMoneyInt-=10;
            }
            else {
                //missingCoins.add(10);
                //userMoneyInt-=10;
                break;
            }
        }
        while(userMoneyInt >= 5) {
            if(totalCoins.containsCoin(5)) {
                returnedCoins.addCoin(5);
                totalCoins.removeCoin(5);
                userMoneyInt-=5;
            }
            else {
                //missingCoins.add(5);
                //userMoneyInt-=5;
                break;
            }
        }
        //totalMoney is calculated from totalCoins
        totalMoney = totalCoins.toDouble();
        return returnedCoins;
        // if(userMoneyInt%200!=0 && userMoneyInt%100!=0 && userMoneyInt%50!=0 && userMoneyInt%20!=0 && userMoneyInt%10!=0 && userMoneyInt%5!=0) {
        //     break;
        // }
    }

    /**
     * Getter for userMoneyInt which is calculated based on inputCoins and reflects userMoney as int*100
     * @return int - The value of userMoney as an integer * 100 (in pennies)
     */
    public int getUserMoneyInt() {
        return this.userMoneyInt;
    }
    
    /**
     * Method used to facilitate the purchasing of VendItem(s) through the VendingMachine instance
     * @param choiceId - The ID number of the VendItem which the user wishes to purchase - input as int
     * @return String - To feedback to the user regarding their purchase (Whether it was successful or failed, etc)
     */
    public String purchaseItem(int choiceId) {
        /**
         * userMoneyInt calculated using MoneyBox getTotalBoxValue()
         * which calculates the total value (in pennies) of a MoneyBox
         */
        userMoneyInt = inputCoins.getTotalBoxValue();
        VendItem itemToPurchase;
        //TODO REMOVE FROM PART01
        //System.out.println(totalCoins.containsAll(acceptedCoins));

        /**
         * Calling findItem(id) method to search for the item in the stock list by the input ID
         * Will catch NullPointerException if the item is not found
         */
        try {
            itemToPurchase = findItem(choiceId);
        } catch (NullPointerException e) {
            return "This item does not exist.";
        }

        
        if(itemToPurchase == null) {
            return "This product does not exist.";
        }

        if(this.getVmStatus() == Status.SERVICE_MODE) {
            return "This machine is in service mode.";
        }

        //Used to check if the total stock quantity (sum of all qtyAvailable) is 0 and if so sets to SERVICE_MODE
        if(this.getAllStockQty() == 0) {
            this.setVmStatus(Status.SERVICE_MODE);
        }

        /**
         * Get the selected item's price and convert it into pennies
         * Used Math.round() as precision errors were causing quite a problem
         * Multiplying by 100, then rounding, then casting to int solved this
         */
        int itemPriceInt = (int)Math.round((itemToPurchase.getPrice()*100));

        if(userMoneyInt >= itemPriceInt) {
            
            String deliver = itemToPurchase.deliver();
            if(itemToPurchase.decrement()) {
                userMoneyInt -= itemPriceInt;

                userMoney = (double)userMoneyInt/100;
                String res = "";

                //Calculates the expected change by breaking down userMoneyInt
                MoneyBox expectedChange = MoneyBox.intToDenoms(userMoneyInt);
                
                //Gets the coins that the system can actually return
                MoneyBox returnedCoins = chooseReturnCoins();

                totalCoins.add(MoneyBox.intToDenoms(userMoneyInt));

                if(returnedCoins.getTotalBoxValue() > 0) {
                    res += String.format("%s\nYour change is £%.2f.\nYour change consists of: ", deliver, returnedCoins.toDouble());
                } 
                else {
                    res += String.format("%s\nYour transaction returned no change.", deliver);
                }

                res+=""+ MoneyBox.formatCoins(returnedCoins.getInsertedCoins()) + "\n";

                //Checks if system's returned coins are equal to the expected change; if not, tell the user what couldn't be returned
                if(!returnedCoins.equals(expectedChange)) {
                    res += "Sorry, we couldn't return: ";
                    res += MoneyBox.formatCoins(expectedChange.getDifference(returnedCoins).getInsertedCoins());
                }
            
                userMoney = 0.0;
                totalMoney = totalCoins.toDouble();
                inputCoins.clear();
                res += "\nNow dispensing.";
                
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
        return "\n\t! Not enough funds to purchase this item. !";
    }

    

    /**
     * Getter for status of VendingMachine
     * @return Status - Enum giving the current status of the machine
     */
    public Status getVmStatus() {
        return this.vmStatus;
    }

    /**
     * Method to facilitate inserting coins into the system
     * NOTE: Pound denominations are inserted like 1 for £1 and 2 for £2 rather than 100 and 200 respectively
     * This is detailed in the readme.txt along with my reasoning for this
     * @param amount - The amount which is to be inserted; input as integer
     * @return boolean - True if the coin could be inserted successfully else false
     */
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
            inputCoins.addCoin(amount);
            totalCoins.addCoin(amount);
            totalMoney = totalCoins.toDouble();
            //TODO REMOVE THIS FROM PART 1 TOO
            //System.out.println(totalCoins);
            return true;
        }
        return false;
    }

    /**
     * Method to sort the stock array by the VendItem's itemID number; uses bubble sort
     * Used to make the findItem() and any other searching methods more efficient
     */
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

    /**
     * Method used to facilitate adding a new VendItem to the VendingMachine
     * @param newItem - VendItem to be added into the machine
     * @return boolean - True if the VendItem was added successfully else false
     */
    public boolean addNewItem(VendItem newItem) {
        //Checks to ensure newItem isn't null
        if(newItem != null) {
            //Checks to see if the VendItem already exists based on name, or actual object reference
            for (int index = 0; index < stock.length; index++) {
                if(stock[index] == newItem || stock[index] != null && stock[index].getName().equalsIgnoreCase(newItem.getName())) {
                    return false;
                }
            }
            for (int j = 0; j < stock.length; j++) {
                if(stock[j] == null) {
                    stock[j] = newItem;
                    itemCount += 1;
                    
                    this.sortStock();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method used to list details about all items contained in the machine
     * @return String[] - Array containing a String which details each item
     */
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

    /**
     * Getter used to get the stock array of VendItem(s) 
     * @return VendItem array containing each VendItem in the machine
     */
    //Used in menu to get all stock
    public VendItem[] getStock() {
        return stock;
    }

    /**
     * 
     * @param vStatus - The new status of the VendingMachine, either VENDING_MODE or SERVICE_MODE
     * @return boolean - True if the status was changed successfully else false
     */
    public boolean setVmStatus(Status vStatus) {
        int totalQty = getAllStockQty();
        //The machine will only change to VENDING_MODE if there are more than 0 item count in the machine in total
        if(totalQty == 0 && vStatus == Status.VENDING_MODE) {
            this.vmStatus = Status.SERVICE_MODE;
            return false;
        }
        this.vmStatus = vStatus;
        return true;
    }

    /**
     * Method used to get the entire quantity of VendItem(s) contained in the machine
     * While itemCount variable stores the amount of VendItem(s), this stores the sum of all their quantity
     * @return integer - Contains the sum of the qtyAvailable of all VendItem(s) in the machine
     */
    private int getAllStockQty() {
        int totalQty = 0;
        for (VendItem item : stock) {
            if(item != null) {
                totalQty += item.getQty();
            }
        }
        return totalQty;
    }


    /**
     * Method for searching for a VendItem in the stock array based on it's itemID
     * @param itemId - ID of the VendItem to be found; input as integer
     * @return VendItem - The VendItem with the ID input if found
     * @throws NullPointerException - If VendItem with itemId could not be found
     */
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


    /**
     * Getter for userMoney
     * @return double containing userMoney
     */
    //Unnecessary?
    public double getUserMoney() {
        return userMoney;
    }

    /**
     * Getter for inputCoins
     * @return MoneyBox with values that make up the user input coins
     */
    public MoneyBox getInputCoins() {
        return inputCoins;
    }


    // @Override
    // public String toString() {
    //     String res = owner + "," + maxItems + "," + itemCount + "," + totalMoney + "," + userMoney + "," + vmStatus + ",";
    //     for (VendItem vendItem : stock) {
    //         if(vendItem != null) {
    //             res += vendItem + ",";
    //         }
            
    //     }
    //     return res;
    // }


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

    /**
     * Getter the owner of the machine
     * @return String with the owner of the machcine
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Setter to set the owner of the machine
     * @param owner - Name of the owner of the machine to be set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Getter for maxItems
     * @return integer - The maximum items the machine can contain
     */
    public int getMaxItems() {
        return maxItems;
    }

    /**
     * Setter for maxItems
     * @param maxItems integer - The new maximum items the machine can contain
     */
    //TODO make private - Maybe not cause it's setter?
    public void setMaxItems(int maxItems) {
        if(maxItems > 0) {
            this.maxItems = maxItems;
        }
        else {
            this.maxItems = 0;
        }
        
    }

    /**
     * Getter for itemCount
     * @return integer - The amount of VendItem(s) in the machine
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Setter for itemCount
     * @param itemCount integer - New item count
     */
    //TODO make private - Maybe not cause it's setter?
    public void setItemCount(int itemCount) {
        if(itemCount < maxItems && itemCount >= 0) {
            this.itemCount = itemCount;
        }
        else {
            this.itemCount = 0;
        }
    }

    /**
     * Getter for totalMoney (as a double)
     * @return double - Containing the total money the machine contains
     */
    public double getTotalMoney() {
        return totalMoney;
    }

    /**
     * Setter for totalMoney
     * @param totalMoney double - value to set totalMoney to
     */
    //TODO Not needed?
    //TODO make private - Maybe not cause it's setter?
    public void setTotalMoney(double totalMoney) {
        if(totalMoney > 0.0) {
            this.totalMoney = totalMoney;
        }
        else {
            this.totalMoney = 0.0;
        }
    }

    /**
     * Setter for userMoney
     * @param userMoney double - value to set userMoney to
     */
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

    /**
     * Setter for the stock array which stores VendItem(s)
     * @param stock array of type VendItem
     */
    //TODO make private - Maybe not cause it's setter?
    public void setStock(VendItem[] stock) {
        this.stock = stock;
    }

    /**
     * Method to get all data pertainining to the VendingMachine instance
     * Used when saving the machine state
     * @return formatted String with all the VendingMachine's details
     */
    public String getDetails() {
        String res = String.format("%s,%d,%d,%f,%f,%s,%s,%s,", owner, maxItems, itemCount, totalMoney, userMoney, this.totalCoins.toString(), this.inputCoins.toString(), vmStatus);
        for (VendItem vendItem : stock) {
            if(vendItem != null) {
                res += vendItem.getData() + ",";
            }
            
        }
        return res;
    }

    /**
     * Getter for totalCoins
     * @return MoneyBox with values that make up the total coins the machine contains
     */
    public MoneyBox getTotalCoins() {
        return totalCoins;
    }

    public ArrayList<Integer> getAcceptedCoins() {
        return acceptedCoins;
    }

    public void setUserMoneyInt(int userMoneyInt) {
        this.userMoneyInt = userMoneyInt;
    }

    public static void setAcceptedCoins(ArrayList<Integer> acceptedCoins) {
        VendingMachine.acceptedCoins = acceptedCoins;
    }

    public void setInputCoins(MoneyBox inputCoins) {
        this.inputCoins = inputCoins;
    }

    public void setTotalCoins(MoneyBox totalCoins) {
        this.totalCoins = totalCoins;
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