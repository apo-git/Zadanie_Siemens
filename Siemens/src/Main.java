import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        for (int length = 1; length <= 50; length++) {
            boolean isNegative = random.nextInt(2) == 1;

            String number = generateRandomString(length, isNegative, random);

            System.out.println(number);
        }
    }

    public static String generateRandomString(int length, boolean isNegative, Random random) {
        int extraCharacterIfNegative;

        if (isNegative) {
            extraCharacterIfNegative = 1;
        } else {
            extraCharacterIfNegative = 0;
        }

        char[] number = new char[length + extraCharacterIfNegative];

        if (isNegative) number[0] = '-';

        number[extraCharacterIfNegative] = (char) ('1' + random.nextInt(9));

        for (int i = extraCharacterIfNegative + 1; i < number.length; i++) {
            number[i] = (char) ('0' + random.nextInt(10));
        }

        return new String(number);
    }
}


