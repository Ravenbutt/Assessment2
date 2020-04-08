package part01;

/**
 * VendingMachineApp class which implements a system which can perform
 * operations on a VendingMachine instance and it's various components all
 * through a console-based interface
 * 
 * @author Andrew Ellis
 * @version V1.5
 */
public class VendingMachineApp {

    protected static VendingMachine vendingMachine;

    public static void main(String[] args) {
        initVendMachine();
        initMenu();
        System.out.println("Goodbye!");
    }

    /**
     * Static method to initialise the VendingMachine instance Checks if
     * VendingState.csv contains a valid state If not, it will initialise a new
     * VendingMachine instance with several VendItems
     */
    private static void initVendMachine() {
        vendingMachine = new VendingMachine("Coca Cola", 10);
        VendItem cocaCola = new VendItem("Coca Cola Zero 550ml", 1.35, 10);
        VendItem fanta = new VendItem("Fanta Orange 550ml", 1.35, 10);
        VendItem taytoCheese = new VendItem("Tayto Cheese and Onion", 0.70, 10);
        vendingMachine.addNewItem(cocaCola);
        vendingMachine.addNewItem(fanta);
        vendingMachine.addNewItem(taytoCheese);
        vendingMachine.setStatus(Status.VENDING_MODE);
    }

    /**
     * Static method to initiate the menu with options
     */
    private static void initMenu() {
        String menuOptions[] = { "View All Items", "Insert Coins", "Purchase an Item", "Quit" };
        Menu vendMenu = new Menu("VendOS v1.0", menuOptions);
        int choice = -1;
        do {
            vendMenu.setExtraDetails(getExtraDetails());
            choice = vendMenu.getChoice();
            if (choice != menuOptions.length) {
                processChoice(choice);
            }
        } while (choice != menuOptions.length);
    }

    /**
     * Static method to process the choice input by the user in the menu Will
     * execute the corresponding code/method to each option
     * 
     * @param choice the user's choice from the menu
     */
    private static void processChoice(int choice) {

        // Condition checks if machine is in service mode and prevents the 
        // String being printed if choice is valid but not included in switch
        if (vendingMachine.getVmStatus() == Status.SERVICE_MODE && choice != 4 && choice != 1) {
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
            default:
                System.out.printf("\t! '%d' is not a valid option !\n\n", choice);
                break;
        }
    }

    /**
     * Method to list all items in the machine if there are any to list
     */
    protected static void listAll() {
        System.out.println("Item List");
        System.out.println("+++++++++++\n");
        if (vendingMachine.listItems().length == 0) {
            System.out.println("\t! There are no items to display !\n");
        }
        for (String item : vendingMachine.listItems()) {
            System.out.println(item);
        }
    }

    /**
     * Method that makes use of the VendingMachine's insertCoin() method to
     * facilitate inserting a coin through the menu Coins can be inserted repeatedly
     * until the user inputs 0 to cancel 
     * NOTE: refer to readme.txt for pound denominations; they are inserted as 1
     * and 2 for £1 and £2 respectively.
     */
    private static void insertCoins() {
        System.out.println("\nInsert a Coin");
        System.out.println("+++++++++++\n");
        System.out.println("> NOTE: This vending machine only accepts 5p, 10p, 20p, 50p, £1 and £2 denominations. <");
        ;

        int inputCoin = -1;

        while (inputCoin != 0) {
            System.out.printf("\n\t- Current inserted value: £%.2f -\n", vendingMachine.getUserMoney());

            System.out.print("\n> Please enter coin, enter 0 to finish: ");

            inputCoin = GetInput.getIntInput();
            if (inputCoin == -1) {
                System.out.println("\n\t! Please insert a valid coin. !");
                continue;
            }
            if (inputCoin == 0) {
                break;
            }
            if (!vendingMachine.insertCoin(inputCoin)) {
                if (vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
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
     * If the machine does not contain at least one of each coin, 
     * they will be notified that they may not receive all change and 
     * asked if they wish to continue
     * 
     * @return VendItem which the user has selected
     */
    protected static VendItem selectItem() {

        VendItem chosenItem = null;

        while (chosenItem == null) {

            if (vendingMachine.getTotalMoney() < 5.0) {
                System.out.print("                      ! WARNING !\n"
                        + "THIS MACHINE MAY NOT CONTAIN ENOUGH COINS TO PROVIDE CHANGE\n\n"
                        + "> Are you sure you wish to continue? Y/N: ");
                boolean choice = GetInput.getYesNo();
                if (!choice) {
                    System.out.println("\n\t- Item not purchased. -");
                    return null;
                }
            }

            System.out.println("");
            listAll();

            System.out.print("\n> Enter the number of the item you wish to select, enter 0 to cancel: ");
            int chosenId = GetInput.getIntInput();
            if (chosenId == 0) {
                break;
            }
            if (chosenId == -1) {
                System.out.println("\n\t! Please enter a valid number !");
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

        if (chosenItem == null) {
            return;
        }

        System.out.printf("\t- Selected item: %d. %s at £%.2f. -\n", chosenItem.getItemId(), chosenItem.getName(),
                chosenItem.getPrice());
        if (chosenItem.getQty() == 0) {
            System.out.println("\n\t! Sorry, this item is out of stock !");
            return;
        }
        if (vendingMachine.getUserMoney() < chosenItem.getPrice()) {
            System.out.printf("\n\t! You don't have enough funds to purchase this item !\n");
            return;
        }
        System.out.print("\n> Would you like to purchase this item? Y/N: ");

        boolean choice = GetInput.getYesNo();
        if (choice) {
            System.out.println("\n" + vendingMachine.purchaseItem(chosenItem.getItemId()));
            return;
        } else if (!choice) {
            System.out.println("\n\t- Item not purchased. -");
            return;
        }
    }

    /**
     * Method to get extra details to display on the main menu page Includes details
     * like whether the machine is in SERVICE_MODE, along with inserted coins and
     * their value
     * 
     * @return String with the extra details
     */
    private static String getExtraDetails() {
        String extraDetails = "";
        if (vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
            extraDetails += String.format("\t! %s !", vendingMachine.getVmStatus().getStatusString());

            extraDetails += "\n\t- CUSTOMER OPERATIONS DISABLED - \n\n";
        }
        extraDetails += String.format("\t- Current funds inserted: £%.2f -\n\n", vendingMachine.getUserMoney());
        return extraDetails;
    }
}
