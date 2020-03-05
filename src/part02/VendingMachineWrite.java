package part02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class VendingMachineWrite {
    public static void exportDetails(ArrayList<VendingMachine> vendList, String csvOutPath) {
        PrintWriter myPw = null;
        try {
            myPw = new PrintWriter(csvOutPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        myPw.println("TestCentre, TotalCarsTested, CarsPassed, CarsFailed, PassPerc, FailPerc");
        

        for (VendingMachine eachVend : vendList) {
            myPw.print(eachVend.getDetails());
        }
        System.out.println("Write completed successfully.");
        myPw.close();
    }
}