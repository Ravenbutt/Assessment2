package part01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeMenu extends MenuTester {
    static Scanner input;

    public EmployeeMenu() {
        input = new Scanner(System.in);
        initEmpMenu();
        System.out.println("Going back to customer menu.");
    }

    private void initEmpMenu() {
        String menuOptions[] = {"View All Items", "View an Item", "Add an Item", "Restock an Item", "View Vending Machine Details", "Set Status", "Quit"};
        Menu vendMenu = new Menu("VendOS v1.0 - EMPLOYEE MENU", menuOptions);

        //Condition to check if user has chose to quit and ensure last option is Quit
        //Maybe set it instead that if menuOptions[option-1].equals("Quit") for more robust?
        int choice = -1;
        do {
            String extraDetails = "";
            if(vendingMachine.getVmStatus() == Status.SERVICE_MODE) {
                extraDetails += vendingMachine.getVmStatus().getStatus();
                extraDetails += " - PURCHASING DISABLED\n";
            }

            vendMenu.setExtraDetails(extraDetails);
            choice = vendMenu.getChoice();
            processChoice(choice);
        } while (choice != menuOptions.length);

    }

    private void processChoice(int choice) {
        switch (choice) {
            case 1:
                listAll();
                break;
        
            case 2:
                super.listAll();
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

            default:
                break;
        }
    }


    private void addItem() {
        System.out.println("Adding item");
        System.out.println("+++++++++++\n");
        System.out.print("Enter item name: ");
        String newName = input.nextLine();
    
        while(true) {
            System.out.print("Enter item price: £");
            try {
                double newPrice = input.nextDouble();
                input.nextLine();
                VendItem newItem = new VendItem(newName, newPrice);
                vendingMachine.addNewItem(newItem);
                System.out.printf("Item named %s at price: £%.2f has been added.\n", newName, newPrice);
                break;
            } catch (InputMismatchException e) {
                System.err.println("Please enter a valid value.\n");
                input.next();
                continue;
            }
        }

    }

    private void viewItem() {

    }

    private static void getDetails() {
        System.out.println(vendingMachine.getSystemInfo());
    }

    private void setStatus() {
        
    }

    private void restockItem() {
        
    }

}