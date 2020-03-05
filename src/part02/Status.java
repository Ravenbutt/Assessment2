package part02;

public enum Status {
    VENDING_MODE(0),
    SERVICE_MODE(1);

    private int statIntVal;
    private String[] statString = {"MODE: Vending", "MODE: Service"};

    private Status(int value) {
        statIntVal = value;
    }

    public String getStatus() {
        return statString[statIntVal];
    }

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