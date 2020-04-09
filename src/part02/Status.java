package part02;


/**
 * An enumeration encompassing the different statuses a VendingMachine instance can have
 * 
 * @author Andrew Ellis
 * @version V1.0
 */
public enum Status {
    VENDING_MODE(0),
    SERVICE_MODE(1);

    private int statIntVal;
    private String[] statString = {"MODE: Vending", "MODE: Service"};

    /**
     * Constructor for Status
     * 
     * @param value integer value of the Status
     */
    private Status(int value) {
        statIntVal = value;
    }

    /**
     * Method to get the String from statString which details the status
     * 
     * @return the Status String
     */
    public String getStatus() {
        return statString[statIntVal];
    }

    /**
     * Method to return the Status enum type based on the input String
     * 
     * @param strStat Status value as a String
     * @return Status enum type
     */
    public static Status fromString(String strStat) {
        if(strStat == "VENDING_MODE") {
            return VENDING_MODE;
        }
        else if(strStat == "SERVICE_MODE") {
            return SERVICE_MODE;
        }
        return null;
    }
    
}