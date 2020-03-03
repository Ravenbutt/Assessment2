package part02;

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
        //*Maybe set the stock here rather than adding to it
        if (amount > 0 && amount <= 10) {
            this.qtyAvailable = amount;
            return true;
        }
        return false;
    }

    public boolean decrement() {
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

    @Override
    public String deliver() {
        if(this.getQty() == 0) {
            return null;
        }
        return "Thanks for purchasing " + this.getName() + "\n";
    }


}