import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        // we generate 50 random numbers which are either positive or negative
        for (int length = 1; length <= 50; length++) {
            boolean isNegative = random.nextInt(2) == 1;

            String number = generateRandomNumbers(length, isNegative, random);

            System.out.println(number);
        }
    }

    public static String generateRandomNumbers(int length, boolean isNegative, Random random) {
        int extraCharacterIfNegative;

        // check for negative number
        if (isNegative) {
            extraCharacterIfNegative = 1;
        } else {
            extraCharacterIfNegative = 0;
        }

        // setting array of chars to proper length
        char[] number = new char[length + extraCharacterIfNegative];

        // first character is set to '–' if (num) is negative
        if (isNegative) number[0] = '-';

        // ASCII transformation to chars (1 to 9)
        number[extraCharacterIfNegative] = (char) ('1' + random.nextInt(9));

        // generating proper number of characters in this for loop
        for (int i = extraCharacterIfNegative + 1; i < number.length; i++) {
            // ASCII transformation to chars (0 to 9)
            number[i] = (char) ('0' + random.nextInt(10));
        }

        // returning generated string
        return new String(number);
    }
}


