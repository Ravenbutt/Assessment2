package part02;

import java.util.ArrayList;

public class VendItem implements Vendible {
    private int itemId;
    private static int nextId = 1;
    private String name;
    private double unitPrice;
    private int qtyAvailable;

    public VendItem(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.itemId = nextIdNum();
    }

    public VendItem(String name, double unitPrice, int qtyAvailable) {
        this(name,unitPrice);
        if(qtyAvailable < 0) {
            qtyAvailable = 0;
        }
        this.qtyAvailable = qtyAvailable;
    }


    private static int nextIdNum() {
        int currentNum = nextId;
        nextId++;
        //can just do return nextId++
        return currentNum;
    }

    public String getName() {
        return this.name;
    }

    public int getItemId() {
        return this.itemId; 
    }

    public double getPrice() {
        return this.unitPrice;
    }

    public int getQty() {
        return this.qtyAvailable;
    }

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

    public boolean decrement() {
        // TC5 qty > 0, decrement
        // TC6 qty = 0, decrement
        if(this.getQty() > 0) {
            this.qtyAvailable--;
            return true;
        }
        return false;
    }

    public String getDetails() {
        String res = "\nItem ID: " + itemId + "\n";
        res += "Item Name: " + name + "\n";
        res += "Quantity Available: " + qtyAvailable + "\n";
        res += String.format("Price: £%.2f", unitPrice) + "\n";
        return res;
    }

    @Override
    public String toString() {
        return "VendItem [itemId=" + itemId + ", name=" + name + ", qtyAvailable=" + qtyAvailable + ", unitPrice="
                + unitPrice + "]";
    }

    public String deliver() {
        if(this.getQty() == 0) {
            return null;
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

    public String getData() {
        String res = String.format("%d,%s,%f,%d", itemId, name, unitPrice, qtyAvailable);
        return res;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public double getUnitPrice() {
    //     return unitPrice;
    // }

    // public void setUnitPrice(double unitPrice) {
    //     this.unitPrice = unitPrice;
    // }

    // public int getQtyAvailable() {
    //     return qtyAvailable;
    // }

    // public void setQtyAvailable(int qtyAvailable) {
    //     this.qtyAvailable = qtyAvailable;
    // }

    public static VendItem loadState(int itemId, String itemName, double itemCost, int qtyAvailable) {
        VendItem loadedItem = new VendItem(itemName, itemCost, qtyAvailable);
        loadedItem.setItemId(itemId);
        return loadedItem;
    }

}