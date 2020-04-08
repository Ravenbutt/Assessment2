package part02;

/**
 * VendingMachineApp class which implements a system which can perform operations
 * on a VendingMachine instance and it's various components all through a console-based
 * interface
 * NOTE: To enter the hidden/engineer menu, input 5 on the main menu
 * @author Andrew Ellis
 * @version V1.5
 */
public class VendingMachineApp {

    protected static VendingMachine vendingMachine;
    protected static boolean engineerMode = false;

    public static void main(String[] args) {
        initVendingMachine();
        initMenu();
        System.out.println("Goodbye!");
        if(VendingMachineWrite.exportData(vendingMachine.getData())) {
            System.out.println("\n\t- State written successfully to VendingState.csv -\n");
        }
        else {
            System.out.println("\n\t- Could not save machine state -");
        }
    }

    /**
     * Static method to initialise the VendingMachine instance
     * This method checks if VendingState.csv contains a valid state
     * If not, it will initialise a new VendingMachine instance with several VendItems
     */
    private static void initVendingMachine() {
        if(VendingMachineRead.loadData(VendingMachineRead.parseData()) == null) {
            vendingMachine = new VendingMachine("Coca Cola", 10);
            VendItem cocaCola = new VendItem("Coca Cola Zero 550ml", 1.35, 10);
            VendItem fanta = new VendItem("Fanta Orange 550ml", 1.35, 10);
            VendItem taytoCheese = new VendItem("Tayto Cheese and Onion", 0.70, 10);

            vendingMachine.addNewItem(cocaCola);
            vendingMachine.addNewItem(fanta);
            vendingMachine.addNewItem(taytoCheese);
            vendingMachine.setStatus(Status.VENDING_MODE);
            System.out.println("\n\t- Machine state data could not be loaded -\n\t  New Vending Machine has been created");
        }
        else {
            vendingMachine = VendingMachineRead.loadData(VendingMachineRead.parseData());
            System.out.println("\n\t- State has been loaded from file successfully -");
        }
    }

    /**
     * Static method to initiate the menu with options
     */
    private static void initMenu() {
        String menuOptions[] = {"View All Items", "Insert Coins", "Purchase an Item", "Quit"};
        Menu vendMenu = new Menu("VendOS v1.0", menuOptions);
        int choice = -1;
        do {
            vendMenu.setExtraDetails(getExtraDetails());
            choice = vendMenu.getChoice();
            if(choice != menuOptions.length) {
                processChoice(choice);
            }
        } while (choice != menuOptions.length);
    }

    /**
     * Static method to process the choice input by the user in the menu
     * Will execute the corresponding code/method to each option
     * @param choice integer corresponding to the user's choice from the menu
     */
    private static void processChoice(int choice) {
        if(vendingMachine.getVmStatus() == Status.SERVICE_MODE && choice != 5 && choice != 4 && choice != 1) {
            System.out.println("\t! Vending machine is in service mode !\n\tOnly item viewing permitted !\n");
            return;
        }
        switch (choice) {
            case -1:
                System.out.println("\n\t! Please enter the NUMBER value of the desired option only !");
                break;
            case 1:
                listAll();
                break;
            case 2:
                insertCoins();
                break;
            case 3:
                purchaseItem();
                break;
            case 5:
                //NOTE: Entering 5 in the menu will access the hidden engineer menu
                EngineerMenu empMenu = new EngineerMenu();
                break;
            default:
                System.out.printf("\t! '%d' is not a valid option !\n\n",choice);
                break;
        }
    }

    /**
     * Method to list all items in the machine if there are any to list
     */
    protected static void listAll() {
        System.out.println("Item List");
        System.out.println("+++++++++++\n");
        if(vendingMachine.listItems().length == 0) {
            System.out.println("\t! There are no items to display !\n");
        }
        for (String item : vendingMachine.listItems()) {
            System.out.println(item);
        }
    }

    /**
     * Method that makes use of the VendingMachine's insertCoin() method
     * to facilitate inserting a coin through the menu
     * Coins can be inserted repeatedly until the user inputs 0 to cancel
     * NOTE: Pound denominations are inserted like 1 for £1 and 2 for £2 rather than 100 and 200 respectively
     * This is detailed in the readme.txt along with my reasoning behind this
     */
    private static void insertCoins() {
        System.out.println("\nInsert a Coin");
        System.out.println("+++++++++++\n");
        System.out.println("> NOTE: This vending machine only accepts 5p, 10p, 20p, 50p, £1 and £2 denominations. <");;

        int inputCoin = -1;
        while(inputCoin != 0) {
            System.out.printf("\n\t- Current inserted value: £%.2f -\n", vendingMachine.getUserMoney());
            if(vendingMachine.getInputCoins().getTotalBoxValue() > 0) {
                System.out.printf("\t- Currently inserted coins: %s -\n", MoneyBox.formatCoins(vendingMachine.getInputCoins().getInsertedCoins()));
            }
            System.out.print("\n> Please enter coin, enter 0 to finish: ");
            inputCoin = GetInput.getIntInput();
            if(inputCoin == -1) {
                System.out.println("\t! Please insert a valid coin. !\n");
                continue;
            }
            if(inputCoin == 0) {
                break;
            }
            if(!vendingMachine.insertCoin(inputCoin)) {
                if(vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
                    System.out.println("\t! Machine is in service mode. !\n\t  Coin insertion is disabled.\n");
                    break;
                }
                System.out.println("\t! Please enter only the denominations listed. !");
                continue;
            }
            
        }
    }

    /**
     * Method used to select a VendItem from the list of stock
     * If the machine does not contain at least one of each coin, they will be notified 
     * that they may not receive all change and asked if they wish to continue
     * @return VendItem which the user has selected
     */
    protected static VendItem selectItem() {
        VendItem chosenItem = null;
        while(chosenItem == null) {
            if(!vendingMachine.getTotalCoins().containsAllCoins() && !engineerMode) {
                System.out.print("                      ! WARNING !\n" 
                + "THIS MACHINE MAY NOT CONTAIN ENOUGH COINS TO PROVIDE CHANGE\n\n"
                + "> Are you sure you wish to continue? Y/N: ");
                boolean choice = GetInput.getYesNo();
                if(!choice) {
                    System.out.println("\n\t- Item not purchased. -");
                    return null;
                }
            }
            System.out.println("");
            listAll();
            System.out.print("\n> Enter the number of the item you wish to select, enter 0 to cancel: ");
            int chosenId = GetInput.getIntInput();
            if(chosenId == 0) {
                break;
            }
            if(chosenId == -1) {
                System.out.println("\t! Please enter a valid number !\n");
                continue;
            }
            try {
                chosenItem = vendingMachine.findItem(chosenId);
                return chosenItem;
            } catch (NullPointerException e) {
                System.out.println("\n\t! Item not found !\n");
                continue;
            }
        }
        return null;
        
    }

    /**
     * Method which makes use of the VendingMachine's purchaseItem() method to
     * facilitate the purchasing of a VendItem using the menu
     */
    protected static void purchaseItem() {
        VendItem chosenItem = selectItem();
        if(chosenItem == null) {
            return;
        }
        System.out.printf("\t- Selected item: %d. %s at £%.2f. -\n", chosenItem.getItemId(), chosenItem.getName(), chosenItem.getPrice());
        if(chosenItem.getQty() == 0) {
            System.out.println("\n\t! Sorry, this item is out of stock !");
            return;
        }
        if(vendingMachine.getUserMoney() < chosenItem.getPrice()) {
            System.out.printf("\n\t! You don't have enough funds to purchase this item !\n");
            return;
        }
        System.out.print("\n> Would you like to purchase this item? Y/N: ");
        boolean choice = GetInput.getYesNo();
        if(choice) {
            System.out.println("\n"+vendingMachine.purchaseItem(chosenItem.getItemId()));
            return;
        }
        else if(!choice) {
            System.out.println("\n\t- Item not purchased. -");
            return;
        }
    }

    /**
     * Method to get extra details to display on the main menu page
     * Includes details like whether the machine is in SERVICE_MODE, 
     * along with inserted coins and their value
     * @return String with the extra details
     */
    private static String getExtraDetails() {
        String extraDetails = "";
            if(vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
                extraDetails += String.format("\t! %s !", vendingMachine.getVmStatus().getStatusString());
                
                extraDetails += "\n\t- CUSTOMER OPERATIONS DISABLED - \n\n";
            }
            extraDetails += String.format("\t- Current funds inserted: £%.2f -\n", vendingMachine.getUserMoney());
            if(vendingMachine.getInputCoins().getTotalBoxValue() > 0) {
                extraDetails += String.format("\t  Currently inserted coins: %s\n\n", MoneyBox.formatCoins(vendingMachine.getInputCoins().getInsertedCoins()));
            }
            else {
                extraDetails += "\n";
            }
        return extraDetails;
    }
}
