package part01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTester {

    protected static VendingMachine vendingMachine;
    static Scanner input;
    static double coinsInput[];
    static double total = 0;
    

    public static void main(String[] args) {
        //*Was thinking of doing extends VendingMachine so it could change what is printed by menu?

        initVendMachine();
        input = new Scanner(System.in);
        initMenu();
        input.close();
        System.out.println("Goodbye!");
        
    }

    private static void initVendMachine() {

        //Max of 50 coins can be input by user
        //Change to variable adjustable by engineer
        coinsInput = new double[50];

        //*On part 2, initially set to new VendingMachine("UNDEFINED", 0);
        //*Then check if those are still the values and if so produce an error
        vendingMachine = new VendingMachine("Coca Cola", 10);
        VendItem cocaCola = new VendItem("Coca Cola Zero 550ml", 1.35);
        VendItem fanta = new VendItem("Fanta Orange 550ml", 1.35);
        VendItem taytoCheese = new VendItem("Tayto Cheese and Onion", 0.70, 2);
        vendingMachine.setStatus(Status.VENDING_MODE);
        vendingMachine.addNewItem(cocaCola);
        vendingMachine.addNewItem(fanta);
        vendingMachine.addNewItem(taytoCheese);
    }

    private static void initMenu() {
        String menuOptions[] = {"View All Items", "Insert Coins", "Select Item", "Quit"};
        Menu vendMenu = new Menu("VendOS v1.0", menuOptions);

        //Condition to check if user has chose to quit and ensure last option is Quit
        //Maybe set it instead that if menuOptions[option-1].equals("Quit") for more robust?
        int choice = -1;
        do {
            String extraDetails = "";
            vendingMachine.checkStock();
            if(vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
                extraDetails += vendingMachine.getVmStatus().getStatus();
                extraDetails += " - PURCHASING DISABLED\n";
            }
            extraDetails += String.format("Current funds inserted: £%.2f", vendingMachine.getUserMoney());
            vendMenu.setExtraDetails(extraDetails);
            choice = vendMenu.getChoice();
            processChoice(choice);
        } while (choice != menuOptions.length);

    }

    private static void processChoice(int choice) {

        switch (choice) {
            case 1:
                listAll();
                break;

            case 2:
                insertCoins();
                break;

            case 3:
                selectItem();
                break;

            case 5:
                EmployeeMenu empMenu = new EmployeeMenu();
                break;

            default:
                break;
        }
    }


    public static void listAll() {
        VendItem allItems[] = vendingMachine.getStock();
        for (VendItem vendItem : allItems) {
            if(vendItem != null) {
                System.out.println(vendItem.getItemId() + ". " + "Item: " + vendItem.getName() + " Quantity Remaining: " + vendItem.getQty() + "");
            }
        }
    }

    private static void insertCoins() {
        System.out.println("Insert a Coin");
        System.out.println("+++++++++++\n");
        System.out.println("NOTE: This vending machine only accepts 5p, 10p, 20p, 50p, £1 and £2 denominations");;

        int inputCoin = -1;


        while(inputCoin != 0) {
            System.out.printf("Current inserted value: £%.2f\n", vendingMachine.getUserMoney());
            System.out.print("Please enter coin, enter 0 to finish: ");
            try {
                inputCoin = input.nextInt();
                if(inputCoin == 0) {
                    break;
                }
                if(vendingMachine.insertCoin(inputCoin) == false) {
                    if(vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
                        System.out.println("Machine is in service mode.");
                        break;
                    }
                    System.out.println("Please enter only the denominations listed.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Please insert a valid coin.");
                input.next();
                continue;
            }
        }
    }


    private static void selectItem() {
        
        int chosenId = -1;
        VendItem chosenItem = null;
        
        while(chosenItem == null) {
            listAll();
            System.out.print("\nEnter the number of the item you wish to select: ");
            try {
                chosenId = input.nextInt();
                input.nextLine();
                chosenItem = vendingMachine.findItem(chosenId);
                break;
            } catch (InputMismatchException e) {
                System.err.println("Please enter a valid number.");
                input.next();
                continue;
            }
            catch (NullPointerException e) {
                System.err.println("Item not found.");
                continue;
            }
        }

        if(chosenItem.getQty() == 0) {
            System.out.printf("Selected item: %d. %s at £%.2f.\n", chosenItem.getItemId(), chosenItem.getName(), chosenItem.getPrice());
            System.out.println("THIS ITEM IS OUT OF STOCK.");
        }
        else {
            System.out.printf("Selected item: %d. %s at £%.2f.\n", chosenItem.getItemId(), chosenItem.getName(), chosenItem.getPrice());
            purchaseItem(chosenId);
        }
        
        
        
    }

    private static void purchaseItem(int chosenId) {
        System.out.println("\nWould you like to purchase this item? Y/N: ");

        while (true) {
            char choice = input.nextLine().charAt(0);
            if(Character.toUpperCase(choice) == 'Y') {
                System.out.println(vendingMachine.purchaseItem(chosenId));
                break;
            }
            else if(Character.toUpperCase(choice) == 'N') {
                System.out.println("Item not purchased.");
                break;
            }
            else {
                System.out.println("Please enter Y for yes or N for no.");
                continue;
            }
        }
    }
}

//Add refund method?