package part01;

public class VendingMachine {
    private String owner; 
    private int maxItems; //max amount of items the machine can hold
    private int itemCount; //amount of items (VendItems) currently for sale
    private double totalMoney;
    private double userMoney;
    private int userMoneyInt;
    private Status vmStatus;
    private VendItem[] stock;
    private static final int[] ACCEPTED_COINS = {2,1,50,20,10,5};

    /**
     * Constructor for VendingMachine object - builds a new instance from the parameters
     * @param owner - The owner of the VendingMachine instance - input as String
     * @param maxItems - The max number of items the VendingMachine instance can contain - input as int
     */
    public VendingMachine(String owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
        this.setStatus(Status.SERVICE_MODE);

        //Initialising the stock array to contain VendItem(s) up to max of maxItem
        stock = new VendItem[maxItems];

        //Method call to store an initial amount of totalMoney in the machine
        this.totalMoney = 50.00;
    }

    /**
     * Method to get all data pertaining to the VendingMachine instance
     * @return String containing the VendingMachine data
     */
    public String getSystemInfo() {
        String res = "\nOwner: " + owner + "\n";
        res += "Max items: " + maxItems + "\n";
        res += "Current item count: " + itemCount + "\n";
        res += String.format("Current total funds: £%.2f\n", totalMoney);
        res += String.format("Current user funds: £%.2f\n", userMoney);
        res += this.getVmStatus().getStatusString();
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
        this.stock = new VendItem[maxItems];
        this.totalMoney = 0.0;
        this.userMoney = 0.0;
        this.userMoneyInt = 0;
        this.itemCount = 0;
        this.setStatus(Status.SERVICE_MODE);
    }
    
    /**
     * Method used to facilitate the purchasing of VendItem(s) through the VendingMachine instance
     * @param choiceId - The ID number of the VendItem which the user wishes to purchase - input as int
     * @return String - To feedback to the user regarding their purchase (Whether it was successful or failed, etc)
     */
    public String purchaseItem(int choiceId) {

        VendItem itemToPurchase;

        /**
         * Calling findItem(id) method to search for the item in the stock list by the input ID
         * Will catch NullPointerException if the item is not found
         */
        try {
            itemToPurchase = findItem(choiceId);
        } catch (NullPointerException e) {
            return "\n\t- This item does not exist -";
        }
        if(this.getVmStatus() == Status.SERVICE_MODE) {
            return "\n\t- This machine is in service mode -";
        }

        //Used to check if the total stock quantity (sum of all qtyAvailable) is 0 and if so sets to SERVICE_MODE
        if(this.sumAllStockQty() == 0) {
            this.setStatus(Status.SERVICE_MODE);
        }

        /**
         * Get the selected item's price and convert it into pennies
         * Used Math.round() as precision errors were causing quite a problem
         * Multiplying by 100, then rounding to nearest whole number, then casting to int solved this
         */
        int itemPriceInt = (int)Math.round((itemToPurchase.getPrice()*100));
        int userMoneyInt = (int)Math.round((userMoney*100));

        if(userMoneyInt >= itemPriceInt) {
            String deliver = itemToPurchase.deliver();
            if(itemToPurchase.decrement()) {
                totalMoney += itemToPurchase.getPrice();
                userMoneyInt -= itemPriceInt;
                userMoney = (double)userMoneyInt/100;

                String res = "";
                
                if(userMoneyInt > 0) {
                    res += String.format("%s\nYour change is £%.2f.", deliver, userMoney);
                }
                else {
                    res += String.format("%s\nYour transaction returned no change.", deliver);
                }
                res += "\nNow dispensing.";
                if(this.sumAllStockQty() == 0) {
                    this.setStatus(Status.SERVICE_MODE);
                    res += "\n\n\t! Machine is out of stock. Switching to service mode. !";
                }
                userMoney = 0.0;
                return res;
            }
            else {
                return String.format("\n\t- None of %s left; please choose another item -", itemToPurchase.getName());
            }
        }
        return "\n\t! Not enough funds to purchase this item. !";
    }

    /**
     * Method to facilitate inserting coins into the system
     * NOTE: Pound denominations are inserted like 1 for £1 and 2 for £2 rather than 100 and 200 respectively
     * This is detailed in the readme.txt along with my reasoning behind this
     * @param amount - The amount which is to be inserted; input as integer
     * @return boolean - True if the coin could be inserted successfully else false
     */
    public boolean insertCoin(int amount) {
        double dAmount = amount;
        if(this.vmStatus == Status.SERVICE_MODE) {
            return false;
        }
        for (int acceptedCoin : ACCEPTED_COINS) {
            if(acceptedCoin == amount) {
                if(dAmount > 2) {
                    dAmount /= 100;
                }
                userMoney += dAmount;
                totalMoney += dAmount;
                return true;
            }
        }
        return false;
        
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
     * Method used to get the entire quantity of VendItem(s) contained in the machine
     * While itemCount variable stores the amount of VendItem(s), this stores the sum of all their quantity
     * @return integer containing the sum of the qtyAvailable of all VendItem(s) in the machine
     */
    private int sumAllStockQty() {
        int totalQty = 0;
        if(stock != null) {
            for (VendItem item : stock) {
                if(item != null) {
                    totalQty += item.getQty();
                }
            }
        }
        
        return totalQty;
    }

    
    /**
     * Method for searching for a VendItem in the stock array based on it's itemID
     * @param itemId integer for the ID of the VendItem to be found
     * @return VendItem with the input ID if found
     * @throws NullPointerException thrown if VendItem with itemId could not be found
     */
    public VendItem findItem(int itemId) throws NullPointerException {

        VendItem target = null;
        for(int index = 0; index < stock.length; index++) {
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
     * Method to set the status of the VendingMachine
     * NOTE: This should be getVmStatus() as per naming conventions but UML diagram specified it as getStatus()
     * @param vStatus - The new status of the VendingMachine of enum type Status, either VENDING_MODE or SERVICE_MODE
     * @return boolean returns true if the status was changed successfully else false
     */
    public boolean setStatus(Status vStatus) {
        int totalQty = sumAllStockQty();
        //The machine will only change to VENDING_MODE if there are more than 0 item count in the machine in total
        if(totalQty == 0 && vStatus == Status.VENDING_MODE) {
            this.vmStatus = Status.SERVICE_MODE;
            return false;
        }
        this.vmStatus = vStatus;
        return true;
    }

    /**
     * Getter for status of VendingMachine
     * @return Status - Enum giving the current status of the machine
     */
    public Status getVmStatus() {
        return this.vmStatus;
    }

    /**
     * Getter the owner of the machine
     * @return String with the owner of the machcine
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Getter for maxItems
     * @return integer representing the maximum items the machine can contain
     */
    public int getMaxItems() {
        return maxItems;
    }

    /**
     * Getter for itemCount
     * @return integer representing the amount of VendItem(s) in the machine
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Getter for totalMoney (as a double)
     * Only really used for testing
     * @return double representing the total money the machine contains
     */
    public double getTotalMoney() {
        return totalMoney;
    }

    /**
     * Getter for userMoney
     * @return double containing userMoney
     */
    public double getUserMoney() {
        return userMoney;
    }

    /**
     * Getter used to get the stock array of VendItem(s) 
     * @return VendItem array containing each VendItem in the machine
     */
    public VendItem[] getStock() {
        return stock;
    }
}
