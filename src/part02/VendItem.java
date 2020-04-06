package part02;

/**
 * 
 * @author
 * @version
 */
public class VendItem implements Vendible {
    private int itemId;
    private static int nextId = 1;
    private String name;
    private double unitPrice;
    private int qtyAvailable;

    /**
     * Constructor for VendItem instance
     * @param name String which holds the name of the VendItem instance
     * @param unitPrice double which holds the price per unit
     */
    public VendItem(String name, double unitPrice) {
        if(name == null) {
            name = "UNASSIGNED";
            unitPrice=0.0;
        }
        this.name = name;
        if(unitPrice < 0.0) {
            unitPrice = 0.0;
        }
        this.unitPrice = unitPrice;
        this.itemId = nextIdNum();
    }

    /**
     * Overloaded constructor which allows specification of quantity
     * @param name String which holds the name of the VendItem instance
     * @param unitPrice double which holds the price per unit
     * @param qtyAvailable integer which specifies the quantity of the VendItem instance
     */
    public VendItem(String name, double unitPrice, int qtyAvailable) {
        this(name,unitPrice);
        if(name == null) {
            qtyAvailable = 0;
        }
        if(qtyAvailable < 0) {
            qtyAvailable = 0;
        }
        else if(qtyAvailable > 10) {
            qtyAvailable = 0;
        }
        this.qtyAvailable = qtyAvailable;
    }

    /**
     * Static method which returns the next ID number for each VendItem
     * @return returns the nextId number after the previously allocated ID number
     */
    private static int nextIdNum() {
        return nextId++;
    }

    /**
     * Getter for VendItem name
     * @return String with the name of the VendItem instance
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for itemId
     * @return integer corresponding to the VendItem instance's ItemId
     */
    public int getItemId() {
        return this.itemId; 
    }

    /**
     * Getter for unitPrice
     * NOTE: This should be getUnitPrice() by naming conventions but UML diagram specified it as getPrice()
     * @return double value for the unit price of the VendItem
     */
    public double getPrice() {
        return this.unitPrice;
    }

    /**
     * Getter for qtyAvailable
     * NOTE: This should be getQtyAvailable() by naming conventions but UML diagram specified it as getQty()
     * @return returns the current quantity available for the VendItem instance
     */
    public int getQty() {
        return this.qtyAvailable;
    }

    /**
     * Method to restock/set the qtyAvailable of the VendItem instance
     * New value must be above 0 and less than or equal to 10
     * @param amount integer which the qtyAvailable of the VendItem should be set to
     * @return boolean - True if the item was restocked successfully, else false
     */
    public boolean restock(int amount) {
        // TC1: restock(0)
        // TC2: restock(15)
        // TC3: restock(-1)
        // TC4 restock("hi")
        //*Maybe set the stock here rather than adding to it
        if (amount > 0 && amount <= 10) {
            this.qtyAvailable = amount;
            return true;
        }
        return false;
    }

    /**
     * Method to decrement the VendItem instance by 1
     * @return boolean - True if the item was decremented successfully, else false
     */
    public boolean decrement() {
        // TC5 qty > 0, decrement
        // TC6 qty = 0, decrement
        if(this.getQty() > 0) {
            this.qtyAvailable--;
            return true;
        }
        return false;
    }

    /**
     * Method to get details about the VendItem instance
     * @return String containing the details
     */
    public String getDetails() {
        String res = "\nItem ID: " + itemId + "\n";
        res += "Item Name: " + name + "\n";
        res += "Quantity Available: " + qtyAvailable + "\n";
        res += String.format("Price: £%.2f", unitPrice) + "\n";
        return res;
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        return "VendItem [itemId=" + itemId + ", name=" + name + ", qtyAvailable=" + qtyAvailable + ", unitPrice="
                + unitPrice + "]";
    }

    /**
     * Method to output a String thanking the user for their purchase
     * Implemented from interface Vendible
     */
    @Override
    public String deliver() {
        if(this.getQty() == 0) {
            //TODO Does this get fired in the VendingMachineApp code??
            return "Sorry for running out of " + this.getName() + ".\n";
        }
        return "Thanks for purchasing " + this.getName() + ".\n";
    }

    // //Remove this
    // public ArrayList<String> getData2() {
    //     ArrayList<String> itemData = new ArrayList<String>();
    //     itemData.add(Integer.toString(itemId));
    //     itemData.add(name);
    //     itemData.add(Double.toString(unitPrice));
    //     itemData.add(Integer.toString(qtyAvailable));
    //     return itemData;
    // }

    /**
     * Method to get data pertaining to the VendItem as a String
     * @return String with data about the VendItem instance
     */
    public String getData() {
        String res = String.format("%d,%s,%f,%d", itemId, name, unitPrice, qtyAvailable);
        return res;
    }

    /**
     * Setter for itemId
     * NOTE: This method is used for loading the VendingMachine's state
     * @param itemId integer for the new itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Setter for name
     * @param name String containing the new name for the VendItem
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Static method to create a new VendItem from the input parameters, used for loading state
     * @param itemId integer whose value is the itemId for the loaded VendItem
     * @param itemName String whose value is the name for the loaded VendItem
     * @param itemCost double whose value is the unitPrice for the loaded VendItem
     * @param qtyAvailable integer whose value is the qtyAvailable for the loaded VendItem
     * @return the loaded VendItem
     */
    public static VendItem loadState(int itemId, String itemName, double itemCost, int qtyAvailable) {
        VendItem loadedItem = new VendItem(itemName, itemCost, qtyAvailable);
        loadedItem.setItemId(itemId);
        return loadedItem;
    }

}