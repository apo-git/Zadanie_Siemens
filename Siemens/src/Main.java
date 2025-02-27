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

    // prepared for final part
    public static String addOrSubstractString(String number1, String number2) {
        int negative1 = 0;
        int negative2 = 0;

        if (number1.startsWith("-")) {
            negative1 = 1;
        }

        if (number2.startsWith("-")) {
            negative2 = 1;
        }

        String absolute1;
        if (negative1 == 1) absolute1 = number1.substring(1);
        else absolute1 = number1;

        String absolute2;
        if (negative2 == 1) absolute2 = number2.substring(1);
        else absolute2 = number2;

        if (number1.charAt(0) == '-' && number2.charAt(0) == '-') {
            return "- add+";
        } else if (number1.charAt(0) != '-' && number2.charAt(0) != '-') {
            return "+ add+";
        } else {
            int subHelp = compare(absolute1, absolute2);

            if (subHelp == 0) {
                return "0";
            } else if (subHelp > 0) {
                return "sub(a,b)"; // repair this part of code
                // need implementation
            } else {
                return "sub(b,a)"; // repair this part of code
                // need implementation
            }
        }
    }

    public static int compare(String absolute1, String absolute2) {
        int lenOfNum1 = absolute1.length();
        int lenOfNum2 = absolute2.length();
        int subHelp = 0;

        if (lenOfNum1 != lenOfNum2) {
            subHelp = lenOfNum1 - lenOfNum2;
        } else {
            for (int i = 0; i < lenOfNum1; i++) {
                int digit1 = absolute1.charAt(i) - '0';
                int digit2 = absolute2.charAt(i) - '0';
                if (digit1 > digit2) {
                    subHelp = 1;
                    break;
                }
                if (digit1 < digit2) {
                    subHelp = -1;
                    break;
                }
            }
        }

        return subHelp;
    }
}


