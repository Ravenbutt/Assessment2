package part02;

public class MoneyBox {

    private int num5Pence;
    private int num10Pence;
    private int num20Pence;
    private int num50Pence;
    private int num1Pound;
    private int num2Pound;

    public static void main(String[] args) {
        MoneyBox myMoney = new MoneyBox();
        myMoney.addCoin(10, 20);
        System.out.println(myMoney.getNum10Pence());
        System.out.println(myMoney.addCoin(11, 10));
    }

    public MoneyBox() {
        this.num5Pence = 0;
        this.num10Pence = 0;
        this.num20Pence = 0;
        this.num50Pence = 0;
        this.num1Pound = 0;
        this.num2Pound = 0;
    }

    public int[] getCount() {
        int coinArray[] = {num5Pence,num10Pence,num20Pence,num50Pence,num1Pound,num2Pound};
        return coinArray;
    }

    public int getTotalValue() {
        int result = 0;
        result += (5*num5Pence) + (10*num10Pence) + (20*num20Pence) + (50*num50Pence) + (100*num1Pound) + (200*num2Pound);
        return result;
    }

    public String[] formatCoins() {
        String format5Pence = String.format("%d x %dp", this.num5Pence,5);
        String format10Pence = String.format("%d x %dp", this.num10Pence,10);
        String format20Pence = String.format("%d x %dp", this.num20Pence,20);
        String format50Pence = String.format("%d x %dp", this.num50Pence,50);
        String format1Pound = String.format("%d x £%d", this.num1Pound,1);
        String format2Pound = String.format("%d x £%d", this.num2Pound,2);
        String coinList[] = {format5Pence,format10Pence,format20Pence,format50Pence,format1Pound,format2Pound};
        return coinList;
    }



    //Add contains() and formatCoins() method e.g. 10x10p, 15x£1
    public void clear() {
        num5Pence = 0;
        num10Pence = 0;
        num20Pence = 0;
        num50Pence = 0;
        num1Pound = 0;
        num2Pound = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(this.getClass() != obj.getClass()) {
            return false;
        }
        MoneyBox comparableBox = (MoneyBox) obj;
        if(this.num5Pence == comparableBox.getNum5Pence() && this.num10Pence == comparableBox.getNum10Pence()
        && this.num20Pence == comparableBox.getNum20Pence() && this.num50Pence == comparableBox.getNum50Pence()
        && this.num1Pound == comparableBox.getNum1Pound() && this.num2Pound == comparableBox.getNum2Pound()) {
            return true;
        }
        return false;
    }

    public boolean contains(int coin) {
        if(coin == 2 && num2Pound>0) {
            return true;
        }
        if(coin == 1 && num1Pound>0) {
            return true;
        }
        if(coin == 50 && num50Pence>0) {
            return true;
        }
        if(coin == 20 && num20Pence>0) {
            return true;
        }
        if(coin == 10 && num10Pence>0) {
            return true;
        }
        if(coin == 5 && num5Pence>0) {
            return true;
        }
        return false;
    }

    public boolean addCoin(int value) {
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

    public boolean addCoin(int value, int count) {

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

    public boolean removeCoin(int value, int count) {

        if(value==2) {
            for(int amount=0; amount<count; amount++) {
                num2Pound--;
            }
            return true;
        }
        else if(value==1) {
            for(int amount=0; amount<count; amount++) {
                num1Pound--;
            }
            return true;
        }
        else if(value==50) {
            for(int amount=0; amount<count; amount++) {
                num50Pence--;
            }
            return true;
        }
        else if(value==20) {
            for(int amount=0; amount<count; amount++) {
                num20Pence--;
            }
            return true;
        }
        else if(value==10) {
            for(int amount=0; amount<count; amount++) {
                num10Pence--;
            }
            return true;
        }
        else if(value==5) {
            for(int amount=0; amount<count; amount++) {
                num5Pence--;
            }
            return true;
        }
        return false;
    }


    public boolean removeCoin(int value) {
        if(value==2) {
            num2Pound--;
        }
        else if(value==1) {
            num1Pound--;
        }
        else if(value==50) {
            num50Pence--;
        }
        else if(value==20) {
            num20Pence--;
        }
        else if(value==10) {
            num10Pence--;
        }
        else if(value==5) {
            num5Pence--;
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

    @Override
    public String toString() {
        return String.format("%d, %d, %d, %d, %d, %d",num5Pence,num10Pence,num20Pence,num50Pence,num1Pound,num2Pound);
    }

    public boolean isEmpty() {
        if(this.getNum5Pence()==0 && this.getNum10Pence()==0 && this.getNum20Pence()==0 
        && this.getNum50Pence()==0 && this.getNum1Pound()==0 && this.getNum2Pound()==0) {
            return true;
        }
        return false;
    }

    public MoneyBox getDifference(MoneyBox secondBox) {
        int num2PoundDifference = this.getNum2Pound()-secondBox.getNum2Pound();
        int num1PoundDifference = this.getNum1Pound()-secondBox.getNum1Pound();
        int num50PenceDiff = this.getNum50Pence()-secondBox.getNum50Pence();
        int num20PenceDiff = this.getNum20Pence()-secondBox.getNum20Pence();
        int num10PenceDiff = this.getNum10Pence()-secondBox.getNum10Pence();
        int num5PenceDiff = this.getNum5Pence()-secondBox.getNum5Pence();
        MoneyBox newBox = new MoneyBox();
        newBox.addCoin(2, num2PoundDifference);
        newBox.addCoin(1, num1PoundDifference);
        newBox.addCoin(50, num50PenceDiff);
        newBox.addCoin(20, num20PenceDiff);
        newBox.addCoin(10, num10PenceDiff);
        newBox.addCoin(5, num5PenceDiff);
        return newBox;
    }

    public String containsCoins() {
        String res = "";
        if(this.getNum2Pound() > 0) {
            res+= String.format("%d x £2, ", this.getNum2Pound());
        }
        if(this.getNum1Pound() > 0) {
            res+= String.format("%d x £1, ", this.getNum1Pound());
        }
        if(this.getNum50Pence() > 0) {
            res+= String.format("%d x 50p, ", this.getNum50Pence());
        }
        if(this.getNum20Pence() > 0) {
            res+= String.format("%d x 20p, ", this.getNum20Pence());
        }
        if(this.getNum10Pence() > 0) {
            res+= String.format("%d x 10p, ", this.getNum10Pence());
        }
        if(this.getNum5Pence() > 0) {
            res+= String.format("%d x 5p.", this.getNum5Pence());
        }
        return res;
    }

    public void add(MoneyBox toAdd) {
        this.num2Pound+=toAdd.getNum2Pound();
        this.num1Pound+=toAdd.getNum1Pound();
        this.num50Pence+=toAdd.getNum50Pence();
        this.num20Pence+=toAdd.getNum20Pence();
        this.num10Pence+=toAdd.getNum10Pence();
        this.num5Pence+=toAdd.getNum5Pence();
    }

    public double toDouble() {
        int value = this.getTotalValue();
        double dValue = (double)value/100;
        return dValue;
    }

    public static MoneyBox breakDownValue(int value) {
        MoneyBox brokenValue = new MoneyBox();
        while(value >= 200) {
            brokenValue.addCoin(2);
            value-=200;
        }
        while(value >= 100) {
            brokenValue.addCoin(1);
            value-=100;
        }
        while(value >= 50) {
            brokenValue.addCoin(50);
            value-=50;
        }
        while(value >= 20) {
            brokenValue.addCoin(20);
            value-=20;
        }
        while(value >= 10) {
            brokenValue.addCoin(10);
            value-=10;
        }
        while(value >= 5) {
            brokenValue.addCoin(5);
            value-=5;
        }
        return brokenValue;
    }
}