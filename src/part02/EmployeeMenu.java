package part02;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeMenu extends VendingMachineApp {
    //static Scanner input;

    String indentSpacing = "    ";

    public EmployeeMenu() {
        input = new Scanner(System.in);
        initEmpMenu();
        System.out.println(indentSpacing + "Going back to customer menu.");
        engineerMode = false;
    }

    private void initEmpMenu() {
        String menuOptions[] = { "View All Items", "View an Item", "Add an Item", "Restock an Item",
                "View Vending Machine Details", "Set Status", "Reset the Machine", "Back" };
        Menu vendMenu = new Menu("VendOS v1.0 - ENGINEER MENU", menuOptions);

        if (authenticate()) {
            System.out.println("\n- Login successful. -");
            engineerMode = true;

        } else {
            System.out.println("\n! Login failed. !");
            return;
        }

        // Condition to check if user has chose to quit and ensure last option is Quit
        // Maybe set it instead that if menuOptions[option-1].equals("Quit") for more
        // robust?
        int choice = -1;
        do {
            String extraDetails = "";
            if (vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
                extraDetails += vendingMachine.getVmStatus().getStatus();
                extraDetails += " - PURCHASING DISABLED\n";
            }

            vendMenu.setExtraDetails(extraDetails);
            choice = vendMenu.getChoice();
            processChoice(choice);
        } while (choice != menuOptions.length);

    }

    private boolean authenticate() {
        int tries = 3;
        String password = "password";
        System.out.println("\n- ENGINEER ACCESS ONLY -");
        System.out.println("++++++++++++++++++++++++\n");
        while (tries > 0) {
            System.out.println("Tries remaining: " + tries);
            System.out.print("> Please enter the engineer password: ");
            String inputPassword = input.nextLine();
            if (inputPassword.equals(password)) {
                return true;
            } else {
                tries--;
            }
        }
        return false;
    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1:
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
                System.out.println("The machine has been reset.");
                break;
                
            default:
                break;
        }
    }

    private void addItem() {
        if(vendingMachine.getItemCount() == vendingMachine.getMaxItems()) {
            System.out.println(indentSpacing + "No more items can be added.");
            return;
        }
        System.out.println("\nAdding item");
        System.out.println("+++++++++++\n");
        
        System.out.print("> Enter item name: ");
        String newName = input.nextLine();
        

        while (true) {
            System.out.print("> Enter item price: £");
            double newPrice = GetInput.checkDoubleInput();
            if(newPrice == 0.0) {
                System.out.println("\n" + indentSpacing + "! Price must be greater than £0.00. !");
                continue;
            }
            else if(newPrice == -1.0) {
                System.out.println("\n" + indentSpacing + "! Please enter a valid value. !\n");
                continue;
            }
            
            System.out.printf("\n> Item named '%s' at £%.2f. Is this correct? Y/N: ", newName, newPrice);
            boolean choice = GetInput.getYesNo();
            if(choice) {
                VendItem newItem = new VendItem(newName, newPrice);
                if (vendingMachine.addNewItem(newItem)) {
                    System.out.printf(indentSpacing + "- Item named %s at price: £%.2f has been added. -\n", newName, newPrice);
                    break;
                } else {
                    System.out.println(indentSpacing + "! Adding item failed. !");
                    break;
                }
            }
            else {
                addItem();
            }

            
            
        }

    }

    private void viewItemDetails() {
        VendItem chosenItem = super.selectItem();
        if(chosenItem == null) {
            return;
        }
        System.out.println(chosenItem.getDetails());
    }

    private void getDetails() {
        System.out.println(vendingMachine.getSystemInfo());
    }

    private void setStatus() {
        int choice = -1;
        if(!vendingMachine.setVmStatus(Status.VENDING_MODE)) {
            System.out.println("\n" + indentSpacing + "! Machine status cannot be changed from service mode while it contains no stock to vend. !");
            return;
        }
        while (choice != 0) {
            System.out.println(indentSpacing + "1. Vending Mode.");
            System.out.println(indentSpacing + "2. Service Mode.");
            System.out.print("Please select the vending machine status, enter 0 to cancel: ");

            choice = GetInput.checkIntInput();

            if(choice == -1) {
                System.err.println("\n" + indentSpacing + "! Please choose a valid number. !");
                input.next();
                continue;
            }

            if(choice-1 == 0) {
                vendingMachine.setVmStatus(Status.VENDING_MODE);
                System.out.println("\n" + indentSpacing + "- Machine set to " + vendingMachine.getVmStatus().getStatus() + " -");
                break;
            }
            else if(choice-1 == 1) {
                vendingMachine.setVmStatus(Status.SERVICE_MODE);
                System.out.println("\n" + indentSpacing + "- Machine set to " + vendingMachine.getVmStatus().getStatus() + " -");
                break;
            }
            else if(choice == 0) {
                break;
            }
            else {
                System.out.println("\n" + indentSpacing + "Please choose either 1, 2 or 0 to cancel.");
                continue;
            }
            
        }
    }

    private void restockItem() {
        System.out.println("Restocking Item");
        System.out.println("+++++++++++++++");

        VendItem chosenItem = super.selectItem();

        while (true && chosenItem != null) {
            System.out.print("\n> Please enter the new stock amount: ");
            int restockAmount = GetInput.checkIntInput();
            if(restockAmount == -1) {
                System.err.println("\n" + indentSpacing + "! Please enter a valid number. !");
                continue;
            }
            if(chosenItem.restock(restockAmount)) {
                System.out.println(
                            "\n"+indentSpacing+"Item " + chosenItem.getName() + " restocked with quantity: " + chosenItem.getQty());
                    break;
            }
            else {
                System.out.println("\n" + indentSpacing + "! Please enter a quantity from 1 to 10. !");
            }

        }

    }


}