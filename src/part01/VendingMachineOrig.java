package part01;

public class VendingMachineOrig {
    private String owner; 
    private int maxItems; //max amount of items the machine can hold
    private int itemCount; //amount of items (VendItems) currently for sale
    private double totalMoney;
    private double userMoney;
    private Status vmStatus;
    private VendItem[] stock;

    public VendingMachineOrig(String owner, int maxItems) {
        // this.owner = owner;
        // this.maxItems = maxItems;
        // stock = new VendItem[maxItems];
        //*Changing constructor to init so that manages  initialisation, and links with reset
        init(owner, maxItems);
    }

    public String getSystemInfo() {
        return "";
    }

    public void reset() {
        //*Reset variables here
    }

    private void init(String owner, int maxItems) {
        this.owner = owner;
        this.maxItems = maxItems;
        reset();
    }

    public String purchaseItem(int choiceId) {
        return "";
    }

    public boolean insertCoin(int amount) {
        return true;
    }

    public boolean addNewItem(VendItem newItem) {
        if(newItem != null) {
            System.out.println(stock);
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
            nameArray[index] = stock[index].getName();
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

    public VendItem findItem(int itemId) {
        VendItem target = null;
		for(int index = 0; index < stock.length; index++) { //might need to change this to length-1 idk
			VendItem currItem = stock[index];
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


}

//Composition relationship as this class as parent