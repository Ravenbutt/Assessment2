package part02;


/**
 * Class which implements an engineer menu for use with the VendingMachineApp
 * This class inherits from VendingMachineApp as it makes use of a lot of it's
 * functionality
 * 
 * NOTE: The password to access this menu is 'password'
 * 
 * @author Andrew Ellis
 * @version V1.1
 */
public class EngineerMenu extends VendingMachineApp {

    /**
     * Constructor for EngineerMenu
     */
    public EngineerMenu() {
        initEmpMenu();
        System.out.println("\n\t- Going back to customer menu -");
        engineerMode = false;
    }

    /**
     * Method to initialise the EngineerMenu with it's options and choices Also runs
     * authenticate() which acts as security
     */
    private void initEmpMenu() {
        String menuOptions[] = { "View All Items", "View an Item", "Add an Item", "Restock an Item",
                "View Vending Machine Details", "Set Status", "Reset the Machine", "Back" };
        Menu engMenu = new Menu("VendOS v1.0 - ENGINEER MENU", menuOptions);

        // Runs authenticate() before displaying the menu
        if (authenticate()) {
            System.out.println("\n\t- Login successful -");
            engineerMode = true;
        } else {
            System.out.println("\n\t! Login failed !");
            return;
        }

        int choice = -1;
        do {
            String extraDetails = "";
            if (vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
                extraDetails += String.format("\t  %s\n\t- Customer operations disabled -\n\n",
                        vendingMachine.getVmStatus().getStatus());
                extraDetails += "";
            }
            engMenu.setExtraDetails(extraDetails);
            choice = engMenu.getChoice();
            processChoice(choice);
        } while (choice != menuOptions.length);
    }

    /**
     * Method to authenticate the user Authenticates the user based on the input
     * password Will fail if user inputs incorrect password 3 times NOTE: Password
     * is "password"
     * 
     * @return true if correct password is entered, else false if
     *         incorrect password or ran out of attempts
     */
    private boolean authenticate() {
        int tries = 3;

        // PASSWORD IS HERE !!!
        String password = "password";

        System.out.println("\n- ENGINEER ACCESS ONLY -");
        System.out.println("++++++++++++++++++++++++\n");

        while (tries > 0) {

            // User has 3 tries to get correct password
            System.out.println("Tries remaining: " + tries);
            System.out.print("> Please enter the engineer password: ");
            String inputPassword = GetInput.getString();
            if (inputPassword.equals(password)) {
                return true;
            } else {
                tries--;
            }
        }
        return false;
    }

    /**
     * Method to process the choice sent from the menu Calls the corresponding
     * method based on user choice
     * 
     * @param choice the choice the user has made in the menu
     */
    private void processChoice(int choice) {
        switch (choice) {
            case 1:

                // Utilises parent class' listAll() method
                super.listAll();
                break;
            case 2:
                viewItemDetails();
                break;
            case 3:
                addItem();
                break;
            case 4:
                restockItem();
                break;
            case 5:
                getDetails();
                break;
            case 6:
                setStatus();
                break;
            case 7:
                vendingMachine.reset();
                System.out.println("\n\t- The machine has been reset -");
                break;
            default:
                System.out.printf("\t! '%d' is not a valid option !\n\n", choice);
                break;
        }
    }

    /**
     * Method used to add a new VendItem instance to the machine Will ask user to
     * input a name and price and construct a new VendItem instance from that data
     * IF that data is valid
     */
    private void addItem() {

        // Checks if the max items allowed has been reached
        if (vendingMachine.getItemCount() == vendingMachine.getMaxItems()) {
            System.out.println("\t! No more items can be added. !");
            return;
        }

        System.out.println("\nAdding item");
        System.out.println("+++++++++++\n");
        String newName = "";

        while (newName.isBlank() || newName == null) {
            System.out.print("> Enter item name: ");
            newName = GetInput.getString();

            // Prevents user from entering blank values for the name
            // and null values
            if (newName.isBlank()) {
                System.out.println("\n\t! Please enter a valid name !\n");
                continue;
            } else {
                break;
            }
        }

        while (true) {
            System.out.print("> Enter item price: £");
            double newPrice = GetInput.getDoubleInput();
            if (newPrice == 0.0) {
                System.out.println("\n\t! Price must be greater than £0.00. !");
                continue;
            } else if (newPrice == -1.0) {
                System.out.println("\n\t! Please enter a valid value. !\n");
                continue;
            }

            // Asks if the input details are correct
            System.out.printf("\n> Item named '%s' at £%.2f. Is this correct? Y/N: ", newName, newPrice);
            boolean choice = GetInput.getYesNo();
            if (choice) {
                VendItem newItem = new VendItem(newName, newPrice);
                if (vendingMachine.addNewItem(newItem)) {
                    System.out.printf("\t- Item named %s at price: £%.2f has been added. -\n", newName, newPrice);
                    break;
                } else {
                    System.out.println("\n\t! Adding item failed. !");
                    break;
                }
            } else {

                // Calls addItem() within itself if user says the details
                // are incorrect
                addItem();
            }
        }
    }

    /**
     * Method to view details about a particular VendItem
     */
    private void viewItemDetails() {
        VendItem chosenItem = super.selectItem();
        if (chosenItem == null) {
            return;
        }
        System.out.println(chosenItem.getDetails());
    }

    /**
     * Method to call the VendingMachine instance's getSystemInfo() method and
     * output it to the screen
     */
    private void getDetails() {
        System.out.println(vendingMachine.getSystemInfo());
    }

    /**
     * Method to set the status of the VendingMachine instance The status can only
     * be changed from SERVICE_MODE if it contains stock to vend
     */
    private void setStatus() {
        int choice = -1;

        // Checking the machine can be set to vending mode
        // It can't if it has no stock
        if (!vendingMachine.setStatus(Status.VENDING_MODE)) {
            System.out.println(
                    "\n\t! Machine status cannot be changed from service mode while it contains no stock to vend. !");
            return;
        }

        // User can input 0 to cancel
        while (choice != 0) {
            System.out.println("\t1. Vending Mode.");
            System.out.println("\t2. Service Mode.");
            System.out.print("> Please select the vending machine status, enter 0 to cancel: ");
            choice = GetInput.getIntInput();

            // choice will be -1 if user enters a non-number
            // choice - 1 allows this
            if (choice == -1) {
                System.out.println("\n\t! Please choose a valid number. !");
                continue;
            } else if (choice - 1 == 0) {
                vendingMachine.setStatus(Status.VENDING_MODE);
                System.out.println("\n\t- Machine set to " + vendingMachine.getVmStatus().getStatus() + " -");
                break;
            } else if (choice - 1 == 1) {
                vendingMachine.setStatus(Status.SERVICE_MODE);
                System.out.println("\n\t- Machine set to " + vendingMachine.getVmStatus().getStatus() + " -");
                break;
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("\n\tPlease choose either 1, 2 or 0 to cancel.");
                continue;
            }

        }
    }

    /**
     * Method utilising a VendItem instances restock() method which facilitates
     * restocking an item through the engineer menu
     */
    private void restockItem() {
        System.out.println("Restocking Item");
        System.out.println("+++++++++++++++");

        // Utilises parent class' selectItem() method
        VendItem chosenItem = super.selectItem();
        while (true && chosenItem != null) {
            System.out.print("\n> Please enter the new stock amount: ");

            // User inputs new stock amount
            int restockAmount = GetInput.getIntInput();

            // GetInput.getIntInput() will return -1 if user doesn't enter
            // a number
            if (restockAmount == -1) {
                System.out.println("\n\t! Please enter a valid number. !");
                continue;
            }

            if (chosenItem.restock(restockAmount)) {
                System.out.printf("\n\t- Item '%s' restocked with quantity: %d\n\n", chosenItem.getName(),
                        chosenItem.getQty());
                break;
            } else {
                System.out.println("\n\t! Please enter a quantity from 1 to 10. !");
            }
        }
    }
}