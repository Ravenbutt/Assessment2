package part02;

import java.util.ArrayList;

public class CoinTest {
    public static void main(String[] args) {
        ArrayList<Integer> acceptedCoins = new ArrayList<Integer>();
        acceptedCoins.add(1);
        acceptedCoins.add(2);
        acceptedCoins.add(5);
        acceptedCoins.add(10);
        acceptedCoins.add(20);
        acceptedCoins.add(50);
        int inputAmt = 80;
        for (Integer acceptCoin : acceptedCoins) {
            if(inputAmt >= 50) {
                
                inputAmt-=50;
                System.out.println("InputAmt:" + inputAmt + " Accepted Coin: " + 50);
            }
            else if(inputAmt >= 20) {
                
                inputAmt -= 20;
                System.out.println("InputAmt:" + inputAmt + " Accepted Coin: " + 20);
            }
            else if(inputAmt >= 10) {
                
                inputAmt -= 10;
                System.out.println("InputAmt:" + inputAmt + " Accepted Coin: " + 10);
            }
            else if(inputAmt >= 5) {
                
                inputAmt -= 5;
                System.out.println("InputAmt:" + inputAmt + " Accepted Coin: " + 5);
            }
        }
    }
}