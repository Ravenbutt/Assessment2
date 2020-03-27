package part02;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingMachineApp {

    protected static VendingMachine vendingMachine;
    protected static Scanner input;
    protected static double coinsInput[];
    protected static double total = 0;
    protected static boolean engineerMode = false;

    public static void main(String[] args) {
        //*Was thinking of doing extends VendingMachine so it could change what is printed by menu?

        initVendMachine();
        input = new Scanner(System.in);
        initMenu();
        input.close();
        System.out.println("Goodbye!");
        
    }

    private static void initVendMachine() {

        //Max of 50 coins can be input by user5
        //Change to variable adjustable by engineer

        //*On part 2, initially set to new VendingMachine("UNDEFINED", 0);
        //*Then check if those are still the values and if so produce an error
        vendingMachine = new VendingMachine("Coca Cola", 4);
        VendItem cocaCola = new VendItem("Coca Cola Zero 550ml", 1.35);
        VendItem fanta = new VendItem("Fanta Orange 550ml", 1.35);
        VendItem taytoCheese = new VendItem("Tayto Cheese and Onion", 0.70, 2);
        
        vendingMachine.addNewItem(cocaCola);
        vendingMachine.addNewItem(fanta);
        vendingMachine.addNewItem(taytoCheese);
        //!This is causing problems for some reason
        vendingMachine.setVmStatus(Status.VENDING_MODE);
    }

    private static void initMenu() {
        //set this to options and title as param for initMenu, then allow employeeMenu to just call this init menu
        String menuOptions[] = {"View All Items", "Insert Coins", "Purchase an Item", "Quit"};
        Menu vendMenu = new Menu("VendOS v1.0", menuOptions);

        //Condition to check if user has chose to quit and ensure last option is Quit
        //Maybe set it instead that if menuOptions[option-1].equals("Quit") for more robust?
        int choice = -1;
        do {
            vendMenu.setExtraDetails(getExtraDetails());
            choice = vendMenu.getChoice();
            processChoice(choice);
        } while (choice != menuOptions.length);

    }

    private static void processChoice(int choice) {
        if(vendingMachine.getVmStatus() == Status.SERVICE_MODE && choice != 5 && choice != 4 && choice != 1) {
            System.out.println("Vending machine is in service mode. \nOnly item viewing permitted.\n");
            return;
        }
        switch (choice) {
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
                EmployeeMenu empMenu = new EmployeeMenu();
                break;

            default:
                break;
        }
    }

    public static void listAll() {
        System.out.println("Item List");
        System.out.println("+++++++++++\n");
        for (String item : vendingMachine.listItems()) {
            System.out.println(item);
        }
    }

    private static void insertCoins() {
        System.out.println("Insert a Coin");
        System.out.println("+++++++++++\n");
        System.out.println("NOTE: This vending machine only accepts 5p, 10p, 20p, 50p, £1 and £2 denominations.");;

        int inputCoin = -1;

        while(inputCoin != 0) {
            System.out.printf("Current inserted value: £%.2f\n", vendingMachine.getUserMoney());

            if(vendingMachine.getInputCoins().size() > 0) {
                //!TODO Need to remove formatCoins() in this class and just use vendingMachine.formatCoins()
                System.out.println("Currently inserted coins: " + VendingMachine.formatCoins(vendingMachine.getInputCoins()));
            }

            System.out.print("\nPlease enter coin, enter 0 to finish: ");
            
            inputCoin = GetInput.checkIntInput();
            if(inputCoin == -1) {
                System.out.println("Please insert a valid coin\n");
                continue;
            }
            if(inputCoin == 0) {
                break;
            }
            if(!vendingMachine.insertCoin(inputCoin)) {
                if(vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
                    System.out.println("Machine is in service mode.\nCoin insertion is disabled.\n");
                    break;
                }
                System.out.println("Please enter only the denominations listed.");
                continue;
            }
            
        }
    }


    public static VendItem selectItem() {
        
        //int chosenId = -1;
        VendItem chosenItem = null;
        
        while(chosenItem == null) {

            if(!vendingMachine.getTotalCoins().containsAll(vendingMachine.getAcceptedCoins()) && !engineerMode) {
                System.out.println("                        !WARNING!\n" 
                + "THIS MACHINE MAY NOT CONTAIN ENOUGH COINS TO PROVIDE CHANGE\n\n"
                + "Are you sure you wish to continue? Y/N: ");
                boolean choice = GetInput.checkYesNo();
                if(!choice) {
                    System.out.println("Item not purchased.");
                    return null;
                }
            }


            listAll();

            System.out.print("\nEnter the number of the item you wish to select, enter 0 to cancel: ");
            int chosenId = GetInput.checkIntInput();
            if(chosenId == 0) {
                break;
            }
            if(chosenId == -1) {
                System.err.println("Please enter a valid number.\n");
                //input.next();
                continue;
            }

            try {
                chosenItem = vendingMachine.findItem(chosenId);
                return chosenItem;
            } catch (NullPointerException e) {
                System.err.println("Item not found.\n");
                //input.next();
                continue;
            }
        }
        return null;
        
    }

    private static void purchaseItem() {
        VendItem chosenItem = selectItem();
        if(chosenItem == null) {
            return;
        }
        System.out.printf("Selected item: %d. %s at £%.2f.\n", chosenItem.getItemId(), chosenItem.getName(), chosenItem.getPrice());
        if(chosenItem.getQty() == 0) {
            System.out.println("THIS ITEM IS OUT OF STOCK.");
            return;
        }
        // else {
        //     System.out.printf("Selected item: %d. %s at £%.2f.\n", chosenItem.getItemId(), chosenItem.getName(), chosenItem.getPrice());
        // }
        System.out.println("\nWould you like to purchase this item? Y/N: ");

        boolean choice = GetInput.checkYesNo();
        if(choice) {
            System.out.println(vendingMachine.purchaseItem(chosenItem.getItemId()));
            return;
        }
        else if(!choice) {
            System.out.println("Item not purchased.");
            return;
        }
        // while (true) {
        //     char choice = input.nextLine().charAt(0);
        //     if(Character.toUpperCase(choice) == 'Y') {
        //         System.out.println(vendingMachine.purchaseItem(chosenItem.getItemId()));
        //         break;
        //     }
        //     else if(Character.toUpperCase(choice) == 'N') {
        //         System.out.println("Item not purchased.");
        //         break;
        //     }
        //     else {
        //         System.out.println("Please enter Y for yes or N for no.");
        //         continue;
        //     }
        // }
    }

    private static String getExtraDetails() {
        String extraDetails = "";
            if(vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
                extraDetails += "> " + vendingMachine.getVmStatus().getStatus();
                extraDetails += " - CUSTOMER OPERATIONS DISABLED < \n\n";
            }

            extraDetails += String.format("Current funds inserted: £%.2f\n", vendingMachine.getUserMoney());
            
            if(VendingMachine.formatCoins(vendingMachine.getInputCoins()) != null) {
                extraDetails += VendingMachine.formatCoins(vendingMachine.getInputCoins()) + "\n";
            }
        return extraDetails;
    }

    // private static String formatCoins() {
    //     ArrayList<String> inputCoinsStr = new ArrayList<String>();
    //     if(vendingMachine.getInputCoins().size() > 0) {

    //         for(int coin : vendingMachine.getInputCoins()) {
    //             String coinStr = "";
    //             if(coin < 5) {
    //                 coinStr = String.format("£%d", coin);
    //             }
    //             else if(coin > 2) {
    //                 coinStr = String.format("%dp", coin); 
    //             }
    //             inputCoinsStr.add(coinStr);
    //         }
            
    //     }
    //     return inputCoinsStr.toString().replace("[", "").replace("]", "");
    // }


}

//Add refund method?
// TODO Add change etc for customer