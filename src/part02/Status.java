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
    
}