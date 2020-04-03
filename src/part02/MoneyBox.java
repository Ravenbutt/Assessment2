package part02;

import java.util.ArrayList;

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

    public int getTotalBoxValue() {
        int result = 0;
        result += (5*num5Pence) + (10*num10Pence) + (20*num20Pence) + (50*num50Pence) + (100*num1Pound) + (200*num2Pound);
        return result;
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

    public boolean containsCoin(int coin) {
        if(coin == 0) {
            return false;
        }
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
        switch (value) {
            case 2:
                num2Pound++;
                return true;
            case 1:
                num1Pound++;
                return true;
            case 50:
                num50Pence++;
                return true;
            case 20:
                num20Pence++;
                return true;
            case 10:
                num10Pence++;
                return true;
            case 5:
                num5Pence++;
                return true;
            default:
                return false;
        }
    }

    public boolean addCoin(int value, int count) {
        switch (value) {
            case 2:
                for(int amount=0; amount<count; amount++) {
                    num2Pound++;
                }
                return true;
            case 1:
                for(int amount=0; amount<count; amount++) {
                    num1Pound++;
                }
                return true;
            case 50:
                for(int amount=0; amount<count; amount++) {
                    num50Pence++;
                }
                return true;
            case 20:
                for(int amount=0; amount<count; amount++) {
                    num20Pence++;
                }
                return true;
            case 10:
                for(int amount=0; amount<count; amount++) {
                    num10Pence++;
                }
                return true;
            case 5:
                for(int amount=0; amount<count; amount++) {
                    num5Pence++;
                }
                return true;
            default:
                return false;
        }
    }

    public boolean removeCoin(int value, int count) {

        switch (value) {
            case 2:
                for(int amount=0; amount<count; amount++) {
                    num2Pound--;
                }
                return true;
            case 1:
                for(int amount=0; amount<count; amount++) {
                    num1Pound--;
                }
                return true;
            case 50:
                for(int amount=0; amount<count; amount++) {
                    num50Pence--;
                }
                return true;
            case 20:
                for(int amount=0; amount<count; amount++) {
                    num20Pence--;
                }
                return true;
            case 10:
                for(int amount=0; amount<count; amount++) {
                    num10Pence--;
                }
                return true;
            case 5:
                for(int amount=0; amount<count; amount++) {
                    num5Pence--;
                }
                return true;
            default:
                return false;
        }
    }


    public boolean removeCoin(int value) {
        switch (value) {
            case 2:
                num2Pound++;
                return true;
            case 1:
                num1Pound++;
                return true;
            case 50:
                num50Pence++;
                return true;
            case 20:
                num20Pence++;
                return true;
            case 10:
                num10Pence++;
                return true;
            case 5:
                num5Pence++;
                return true;
            default:
                return false;
        }
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

    public ArrayList<String> getInsertedCoins() {
        ArrayList<String> coinList = new ArrayList<String>();
        if(this.getNum2Pound() > 0) {
            coinList.add(String.format("%d x £2", this.getNum2Pound()));
        }
        if(this.getNum1Pound() > 0) {
            coinList.add(String.format("%d x £1", this.getNum1Pound()));
        }
        if(this.getNum50Pence() > 0) {
            coinList.add(String.format("%d x 50p", this.getNum50Pence()));
        }
        if(this.getNum20Pence() > 0) {
            coinList.add(String.format("%d x 20p", this.getNum20Pence()));
        }
        if(this.getNum10Pence() > 0) {
            coinList.add(String.format("%d x 10p", this.getNum10Pence()));
        }
        if(this.getNum5Pence() > 0) {
            coinList.add(String.format("%d x 5p", this.getNum5Pence()));
        }
        return coinList;
    }

    public static String formatCoins(ArrayList<String> coinList) {
        String res = "";
        for (int index = 0; index < coinList.size(); index++) {
            res+=coinList.get(index);
                
            if(index == coinList.size()-1) {
                res+=".\n";
            }
            else {
                res+=", ";
            }
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

    public void subtract(MoneyBox toSubtract) {
        this.num2Pound-=toSubtract.getNum2Pound();
        this.num1Pound-=toSubtract.getNum1Pound();
        this.num50Pence-=toSubtract.getNum50Pence();
        this.num20Pence-=toSubtract.getNum20Pence();
        this.num10Pence-=toSubtract.getNum10Pence();
        this.num5Pence-=toSubtract.getNum5Pence();
    }

    public double toDouble() {
        int value = this.getTotalBoxValue();
        double dValue = (double)value/100;
        return dValue;
    }

    public static MoneyBox intToDenoms(int value) {
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

    public boolean loadFromArray(String[] fromArr) {
        
        try {
            this.num2Pound = Integer.parseInt(fromArr[0]);
            this.num1Pound = Integer.parseInt(fromArr[1]);
            this.num50Pence = Integer.parseInt(fromArr[2]);
            this.num20Pence = Integer.parseInt(fromArr[3]);
            this.num10Pence = Integer.parseInt(fromArr[4]);
            this.num5Pence = Integer.parseInt(fromArr[5]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%d,%d,%d,%d",num5Pence,num10Pence,num20Pence,num50Pence,num1Pound,num2Pound);
    }

}