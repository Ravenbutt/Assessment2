package part02;

public class Money {

    private int num5Pence;
    private int num10Pence;
    private int num20Pence;
    private int num50Pence;
    private int num1Pound;
    private int num2Pound;

    public static void main(String[] args) {
        Money myMoney = new Money();
        myMoney.addMoney(10, 20);
        System.out.println(myMoney.getNum10Pence());
        System.out.println(myMoney.addMoney(11, 10));
    }

    public Money() {
        num5Pence = 0;
        num10Pence = 0;
        num20Pence = 0;
        num50Pence = 0;
        num1Pound = 0;
        num2Pound = 0;
    }

    public boolean addMoney(int value) {
        if(value==2) {
            num2Pound++;
        }
        else if(value==1) {
            num1Pound++;
        }
        else if(value==50) {
            num50Pence++;
        }
        else if(value==20) {
            num20Pence++;
        }
        else if(value==10) {
            num10Pence++;
        }
        else if(value==5) {
            num5Pence++;
        }
        return false;
    }

    public boolean addMoney(int value, int count) {

        if(value==2) {
            for(int amount=0; amount<count; amount++) {
                num2Pound++;
            }
            return true;
        }
        else if(value==1) {
            for(int amount=0; amount<count; amount++) {
                num1Pound++;
            }
            return true;
        }
        else if(value==50) {
            for(int amount=0; amount<count; amount++) {
                num50Pence++;
            }
            return true;
        }
        else if(value==20) {
            for(int amount=0; amount<count; amount++) {
                num20Pence++;
            }
            return true;
        }
        else if(value==10) {
            for(int amount=0; amount<count; amount++) {
                num10Pence++;
            }
            return true;
        }
        else if(value==5) {
            for(int amount=0; amount<count; amount++) {
                num5Pence++;
            }
            return true;
        }
        return false;
    }

    public int getNum5Pence() {
        return num5Pence;
    }

    public void setNum5Pence(int num5Pence) {
        this.num5Pence = num5Pence;
    }

    public int getNum10Pence() {
        return num10Pence;
    }

    public void setNum10Pence(int num10Pence) {
        this.num10Pence = num10Pence;
    }

    public int getNum20Pence() {
        return num20Pence;
    }

    public void setNum20Pence(int num20Pence) {
        this.num20Pence = num20Pence;
    }

    public int getNum50Pence() {
        return num50Pence;
    }

    public void setNum50Pence(int num50Pence) {
        this.num50Pence = num50Pence;
    }

    public int getNum1Pound() {
        return num1Pound;
    }

    public void setNum1Pound(int num1Pound) {
        this.num1Pound = num1Pound;
    }

    public int getNum2Pound() {
        return num2Pound;
    }

    public void setNum2Pound(int num2Pound) {
        this.num2Pound = num2Pound;
    }
}