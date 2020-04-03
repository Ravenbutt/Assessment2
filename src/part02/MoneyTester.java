package part02;

public class MoneyTester {
    public static void main(String[] args) {
        MoneyBox moneyTest = new MoneyBox();
        moneyTest.addCoin(10, 20);
        System.out.println(moneyTest.getNum10Pence());
        System.out.println(moneyTest.formatCoins());
        for (String itemString : moneyTest.formatCoins()) {
            System.out.println(itemString);
        }
        MoneyBox moneyTestEquals = new MoneyBox();
        moneyTestEquals.addCoin(10,19);
        System.out.println(moneyTest.equals(moneyTestEquals));
    }
}