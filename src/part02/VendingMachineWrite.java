package part02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Class to facilitate the export/writing of a VendingMachine instance to a csv file
 * @author Andrew Ellis
 * @version V1.1
 */
public class VendingMachineWrite {

    /**
     * Method that gets all details from a VendingMachine instance
     * Passes these details to exportData as a String
     * @param vendingMachine
     */
    public static void saveState(VendingMachine vendingMachine) {
        //Maybe say VendingMachine extends this so it can have saveState?
        String toWrite = vendingMachine.getData();
        exportData(toWrite);
    }

    /**
     * Method to actually export the VendingMachine instance data to a csv file
     * @param toWrite String containing the data regarding a VendingMachine instance
     * @return boolean true if the data was successfully written else false
     */
    public static boolean exportData(String toWrite) {
        String csvOutPath = "src/part02/VendingState.csv";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(csvOutPath);
        } catch (FileNotFoundException e) {
            return false;
        }
        writer.println(toWrite);
        writer.close();
        return true;
    }
}