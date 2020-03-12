package part02;

import java.util.ArrayList;
import java.util.Random;

public class FizzBuzz {
    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<Integer>(); 
        for (int i = 1; i < 50; i++) {
            nums.add(i);
        }
        for (int i = 0; i < nums.size(); i++) {
            if(nums.get(i) % 3 == 0 && nums.get(i) % 5 == 0) {
                System.out.println("FizzBuzz");
            }
            else if(nums.get(i) % 3 == 0) {
                System.out.println("Fizz");
            }
            else if(nums.get(i) % 5 == 0) {
                System.out.println("Buzz");
            }
            else {
                System.out.println(nums.get(i));
            }
        }
    }

    public static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }
}