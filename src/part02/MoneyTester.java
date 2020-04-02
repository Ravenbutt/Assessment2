package part02;

public class MoneyTester {
    public static void main(String[] args) {
        Money moneyTest = new Money();
        moneyTest.addMoney(10, 20);
        System.out.println(moneyTest.getNum10Pence());
    }
}