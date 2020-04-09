package part02;

import java.util.ArrayList;


/**
 * This class represents a 'coin pouch' or 'piggy bank' 
 * Can be used to store £2, £1, 50p, 20p, 10p and 5p coins
 * 
 * @author Andrew Ellis
 * @version V1.1
 */
public class MoneyBox {

    // These variables store the count for each coin in the box
    private int num5Pence;
    private int num10Pence;
    private int num20Pence;
    private int num50Pence;
    private int num1Pound;
    private int num2Pound;

    /**
     * Constructor for MoneyBox; initialises with count of 0 for each coin
     */
    public MoneyBox() {
        this.num2Pound = 0;
        this.num1Pound = 0;
        this.num50Pence = 0;
        this.num20Pence = 0;
        this.num10Pence = 0;
        this.num5Pence = 0;
    }

    /**
     * Method to calculate the total value, in pennies, of the MoneyBox instance
     * 
     * @return the total value of the MoneyBox in pennies
     */
    public int getTotalBoxValue() {
        int result = 0;
        result += (5 * num5Pence) + (10 * num10Pence) + (20 * num20Pence) + (50 * num50Pence) + (100 * num1Pound)
                + (200 * num2Pound);
        return result;
    }

    /**
     * Method to reset the MoneyBox instance to it's default, empty state
     */
    public void clear() {
        num2Pound = 0;
        num1Pound = 0;
        num50Pence = 0;
        num20Pence = 0;
        num10Pence = 0;
        num5Pence = 0;
    }

    /**
     * Method to check if the MoneyBox instance has at least one of each coin
     * 
     * @return true if box contains at least one of each coin, else false
     */
    public boolean containsAllCoins() {
        if (this.getNum5Pence() >= 1 && this.getNum10Pence() >= 1 && this.getNum20Pence() >= 1
                && this.getNum50Pence() >= 1 && this.getNum1Pound() >= 1 && this.getNum2Pound() >= 1) {
            return true;
        }
        return false;
    }

    /**
     * Method to check if one instance of MoneyBox is the same as another instance
     * of MoneyBox Checks based on object reference, along with checking if the coin
     * count is the same
     * 
     * @return true if either object reference is the same or coin count
     *         is the same, else false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        MoneyBox comparableBox = (MoneyBox) obj;
        if (this.num5Pence == comparableBox.getNum5Pence() && this.num10Pence == comparableBox.getNum10Pence()
                && this.num20Pence == comparableBox.getNum20Pence() && this.num50Pence == comparableBox.getNum50Pence()
                && this.num1Pound == comparableBox.getNum1Pound() && this.num2Pound == comparableBox.getNum2Pound()) {
            return true;
        }
        return false;
    }

    /**
     * Method to check if the MoneyBox instance contains at least 1 of the param
     * coin
     * 
     * @param coin to be checked to see if the MoneyBox contains at 
     *             least 1 of
     * @return true if the MoneyBox contains coin, else false
     */
    public boolean containsCoin(int coin) {
        if (coin == 2 && num2Pound > 0 || coin == 1 && num1Pound > 0 || coin == 50 && num50Pence > 0
                || coin == 20 && num20Pence > 0 || coin == 10 && num10Pence > 0 || coin == 5 && num5Pence > 0) {
            return true;
        }
        return false;
    }

    /**
     * Method to add/increment a single coin by 1
     * 
     * @param coinToAdd - coin which is to be added if it is an accepted coin
     * @return true if the coin was added successfully, else false
     */
    public boolean addCoin(int coinToAdd) {
        switch (coinToAdd) {
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

    /**
     * Overloaded method for addCoin() to add a coin, also asking how many coins
     * should be added
     * 
     * @param coinToAdd coin which is to be added if it is an accepted coin
     * @param count     how many of coinToAdd should be added
     * @return true if the coins were added successfully, else false
     */
    public boolean addCoin(int coinToAdd, int count) {
        switch (coinToAdd) {
            case 2:
                for (int amount = 0; amount < count; amount++) {
                    num2Pound++;
                }
                return true;
            case 1:
                for (int amount = 0; amount < count; amount++) {
                    num1Pound++;
                }
                return true;
            case 50:
                for (int amount = 0; amount < count; amount++) {
                    num50Pence++;
                }
                return true;
            case 20:
                for (int amount = 0; amount < count; amount++) {
                    num20Pence++;
                }
                return true;
            case 10:
                for (int amount = 0; amount < count; amount++) {
                    num10Pence++;
                }
                return true;
            case 5:
                for (int amount = 0; amount < count; amount++) {
                    num5Pence++;
                }
                return true;
            default:
                return false;
        }
    }

    /**
     * Method to remove/decrement a single coin by 1
     * 
     * @param coinToRemove coin which is to be removed if it is an
     *                     accepted coin
     * @return true if the coin was removed successfully, else false
     */
    public boolean removeCoin(int coinToRemove) {
        switch (coinToRemove) {
            case 2:
                if(num2Pound > 0) {
                    num2Pound--;
                    return true;
                }
            case 1:
                if(num1Pound > 0) {
                    num1Pound--;
                    return true;
                }
            case 50:
                if(num50Pence > 0) {
                    num50Pence--;
                    return true;
                }
            case 20:
                if(num20Pence > 0) {
                    num20Pence--;
                    return true;
                }
            case 10:
                if(num10Pence > 0) {
                    num10Pence--;
                    return true;
                }
            case 5:
                if(num5Pence > 0) {
                    num5Pence--;
                    return true;
                }
            default:
                return false;
        }
    }

    /**
     * Method to check if the MoneyBox contains none of any coin
     * 
     * @return true if MoneyBox doesn't contain any coins, else false
     */
    public boolean isEmpty() {
        if (this.getNum5Pence() == 0 && this.getNum10Pence() == 0 && this.getNum20Pence() == 0
                && this.getNum50Pence() == 0 && this.getNum1Pound() == 0 && this.getNum2Pound() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Method to return a new MoneyBox containing the difference between two
     * MoneyBox instance's values
     * 
     * @param secondBox MoneyBox which is to be compared
     * @return MoneyBox with the difference between the original two
     */
    public MoneyBox getDifference(MoneyBox secondBox) {

        // Gets the difference between first MoneyBox and secondE
        int num2PoundDifference = this.getNum2Pound() - secondBox.getNum2Pound();
        int num1PoundDifference = this.getNum1Pound() - secondBox.getNum1Pound();
        int num50PenceDiff = this.getNum50Pence() - secondBox.getNum50Pence();
        int num20PenceDiff = this.getNum20Pence() - secondBox.getNum20Pence();
        int num10PenceDiff = this.getNum10Pence() - secondBox.getNum10Pence();
        int num5PenceDiff = this.getNum5Pence() - secondBox.getNum5Pence();

        // newBox coin counts will all equal 0 if secondBox
        // greater than this object
        MoneyBox newBox = new MoneyBox();

        newBox.addCoin(2, num2PoundDifference);
        newBox.addCoin(1, num1PoundDifference);
        newBox.addCoin(50, num50PenceDiff);
        newBox.addCoin(20, num20PenceDiff);
        newBox.addCoin(10, num10PenceDiff);
        newBox.addCoin(5, num5PenceDiff);
        return newBox;
    }

    /**
     * Method to get ONLY the currently inserted coins from the MoneyBox, and format
     * the coins like 'COUNT x COIN', so a MoneyBox with num1Pound=11
     * would format like '11 x £1'
     * 
     * @return ArrayList<String> containing the formatted inserted coins
     */
    public ArrayList<String> getInsertedCoins() {
        ArrayList<String> coinList = new ArrayList<String>();
        if (this.getNum2Pound() > 0) {
            coinList.add(String.format("%d x £2", this.getNum2Pound()));
        }
        if (this.getNum1Pound() > 0) {
            coinList.add(String.format("%d x £1", this.getNum1Pound()));
        }
        if (this.getNum50Pence() > 0) {
            coinList.add(String.format("%d x 50p", this.getNum50Pence()));
        }
        if (this.getNum20Pence() > 0) {
            coinList.add(String.format("%d x 20p", this.getNum20Pence()));
        }
        if (this.getNum10Pence() > 0) {
            coinList.add(String.format("%d x 10p", this.getNum10Pence()));
        }
        if (this.getNum5Pence() > 0) {
            coinList.add(String.format("%d x 5p", this.getNum5Pence()));
        }
        return coinList;
    }

    /**
     * Method to format an ArrayList containing coin Strings so they can be
     * formatted nicely for output by adding ',' and '.' where necessary
     * 
     * @param coinList ArrayList<String> containing each formatted coin
     * @return String with the formatted coins
     */
    public static String formatCoins(ArrayList<String> coinList) {
        String res = "";
        for (int index = 0; index < coinList.size(); index++) {
            res += coinList.get(index);

            if (index == coinList.size() - 1) {
                res += ".";
            } else {
                res += ", ";
            }
        }
        return res;
    }

    /**
     * Method to add the coin counts of two MoneyBox instances together
     * 
     * @param toAdd MoneyBox whose coin counts should be added to this instance's
     *              own counts
     */
    public void add(MoneyBox toAdd) {
        if(toAdd == null){
            return;
        }
        this.num2Pound += toAdd.getNum2Pound();
        this.num1Pound += toAdd.getNum1Pound();
        this.num50Pence += toAdd.getNum50Pence();
        this.num20Pence += toAdd.getNum20Pence();
        this.num10Pence += toAdd.getNum10Pence();
        this.num5Pence += toAdd.getNum5Pence();
    }

    /**
     * Method to subtract the coin counts of two MoneyBox instances
     * 
     * @param toSub MoneyBox whose coin counts should be subtracted from this
     *              instance's own counts
     */
    public void subtract(MoneyBox toSub) {
        if (toSub == null) {
            return;
        }
        this.num2Pound -= toSub.getNum2Pound();
        this.num1Pound -= toSub.getNum1Pound();
        this.num50Pence -= toSub.getNum50Pence();
        this.num20Pence -= toSub.getNum20Pence();
        this.num10Pence -= toSub.getNum10Pence();
        this.num5Pence -= toSub.getNum5Pence();
    }

    /**
     * Method to get the value, in pounds and pence, of a MoneyBox instance as a
     * double
     * 
     * @return the value of the MoneyBox instance in pounds and pence
     */
    public double toDouble() {
        int value = this.getTotalBoxValue();
        double dValue = (double) value / 100;
        return dValue;
    }

    /**
     * Static method that breaks down an integer of pennies into coins that make it
     * up, returning a new MoneyBox with those coin counts E.g. intToDenoms(120)
     * would return a new MoneyBox with 1 x £1 and 1 x 20p
     * 
     * @param value integer to be broken down into it's respective coins
     * @return MoneyBox with the count of coins that make up param value
     */
    public static MoneyBox intToDenoms(int value) {
        MoneyBox brokenValue = new MoneyBox();
        if (value <= 0) {
            return brokenValue;
        }
        while (value >= 200) {
            brokenValue.addCoin(2);
            value -= 200;
        }
        while (value >= 100) {
            brokenValue.addCoin(1);
            value -= 100;
        }
        while (value >= 50) {
            brokenValue.addCoin(50);
            value -= 50;
        }
        while (value >= 20) {
            brokenValue.addCoin(20);
            value -= 20;
        }
        while (value >= 10) {
            brokenValue.addCoin(10);
            value -= 10;
        }
        while (value >= 5) {
            brokenValue.addCoin(5);
            value -= 5;
        }
        return brokenValue;
    }

    /**
     * Method to load MoneyBox coin counts from an array
     * 
     * @param fromArr array to load coin counts from
     * @return true if values were loaded successfully else false
     */
    public boolean loadFromArray(String[] fromArr) {
        if (fromArr.length != 6) {
            return false;
        }
        try {
            this.num2Pound  = Integer.parseInt(fromArr[0]);
            this.num1Pound  = Integer.parseInt(fromArr[1]);
            this.num50Pence = Integer.parseInt(fromArr[2]);
            this.num20Pence = Integer.parseInt(fromArr[3]);
            this.num10Pence = Integer.parseInt(fromArr[4]);
            this.num5Pence  = Integer.parseInt(fromArr[5]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns a String representation of the object
     */
    @Override
    public String toString() {
        return String.format("%d,%d,%d,%d,%d,%d", num2Pound, num1Pound, num50Pence, num20Pence, num10Pence, num5Pence);
    }

    /**
     * Getter for num5Pence
     * 
     * @return value of num5Pence
     */
    public int getNum5Pence() {
        return num5Pence;
    }

    /**
     * Getter for num10Pence
     * 
     * @return value of num10Pence
     */
    public int getNum10Pence() {
        return num10Pence;
    }

    /**
     * Getter for num20Pence
     * 
     * @return value of num20Pence
     */
    public int getNum20Pence() {
        return num20Pence;
    }

    /**
     * Getter for num50Pence
     * 
     * @return value of num50Pence
     */
    public int getNum50Pence() {
        return num50Pence;
    }

    /**
     * Getter for num1Pound
     * 
     * @return value of num1Pound
     */
    public int getNum1Pound() {
        return num1Pound;
    }

    /**
     * Getter for num2Pound
     * 
     * @return value of num2Pound
     */
    public int getNum2Pound() {
        return num2Pound;
    }
}