import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        String sum = "0";

        // we generate 50 random numbers which are either positive or negative
        for (int length = 1; length <= 50; length++) {
            boolean isNegative = random.nextInt(2) == 1;

            String number = generateRandomNumbers(length, isNegative, random);
            sum = addOrSubtractString(sum, number);
        }

        int length = sum.length();

        if (length < 10) System.out.println(sum);
        else System.out.println(sum.substring(0, 10));
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

    // decision-making if both numbers are '+' or '-', or one is '+' and second '-' (using correct method according to that)
    public static String addOrSubtractString(String number1, String number2) {
        int negative1 = 0;
        int negative2 = 0;

        if (number1.startsWith("-")) {
            negative1 = 1;
        }

        if (number2.startsWith("-")) {
            negative2 = 1;
        }

        // if number is negative we don't want to work w. '-' sign
        String absolute1;
        if (negative1 == 1) absolute1 = number1.substring(1);
        else absolute1 = number1;

        String absolute2;
        if (negative2 == 1) absolute2 = number2.substring(1);
        else absolute2 = number2;

        // if both numbers are negative we can add them together and at the end return '-' appended to new num
        if (number1.charAt(0) == '-' && number2.charAt(0) == '-') {
            return '-' + addSameValue(absolute1, absolute2);
            // if both numbers are positive we will just add them together and at the end return new num
        } else if (number1.charAt(0) != '-' && number2.charAt(0) != '-') {
            return addSameValue(absolute1, absolute2);
        } else {
            // otherwise we know only one number is negative so we need another method to use for that
            // first of all we need to find out which number is bigger of the comparing numbers
            int subHelp = compare(absolute1, absolute2);

            // if subHelp is equal to zero, it is easy! return 0 'same number - same number always equals to O'
            if (subHelp == 0) {
                return "0";
            } else if (subHelp > 0) {
                // if subHelp is greater than zero, we know that first number is bigger (if first number "absolute1" which is bigger is negative, then final number is going to be negative, otherwise positive)
                if (negative1 == 1) return '-' + subtractDifferentValues(absolute1, absolute2);
                else return subtractDifferentValues(absolute1, absolute2);
            } else {
                // if subHelp is lower than zero, we know that second number is bigger (if first number "absolute2" which is bigger is negative, then final number is going to be negative, otherwise positive)
                if (negative2 == 1) return '-' + subtractDifferentValues(absolute2, absolute1);
                else return subtractDifferentValues(absolute2, absolute1);
            }
        }
    }

    // returning neg or pos number (or 0), neg if second number is bigger, pos if first number is bigger, if they equal return 0
    public static int compare(String absolute1, String absolute2) {
        int lenOfNum1 = absolute1.length();
        int lenOfNum2 = absolute2.length();
        int subHelp = 0;

        if (lenOfNum1 != lenOfNum2) {
            subHelp = lenOfNum1 - lenOfNum2;
            // if subHelp is < 0 second number is bigger, if subHelp > 0 first number is bigger (for same lengths we check here)
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
            // if both numbers have same lengths we decide according to first letter which is bigger of one of those numbers, still if subHelp is < 0 second number is bigger, if subHelp > 0 first number is bigger
        }
        // if they are both same return 0, which we set as default value

        return subHelp;
    }

    // When both numbers are either positive or negative we use this method to compute their value
    public static String addSameValue(String absolute1, String absolute2) {
        // max possible positions of numbers set here
        int position1 = absolute1.length() - 1;
        int position2 = absolute2.length() - 1;
        // set lengths of numbers
        int len1 = absolute1.length();
        int len2 = absolute2.length();
        // if sum is bigger than 9, we need to store that extra cipher
        int shift = 0;
        // if we sum for example two numbers like 111 and 9999, we will need to set size of char array to length of bigger one of those two numbers + 1
        int size = Math.max(len1, len2) + 1;
        char[] result = new char[size];
        // indexing final number
        int positionFinal = size - 1;

        // while not all conditions are met continue while loop (if there is still number at position of first or second number, or there is extra cipher to add
        while (position1 >= 0 || position2 >= 0 || shift != 0) {
            int x = 0;
            int y = 0;

            // when we count all indexes of position1 we want to set x to 0
            if (position1 >= 0) x = absolute1.charAt(position1) - '0';
            // when we count all indexes of position2 we want to set y to 0
            if (position2 >= 0) y = absolute2.charAt(position2) - '0';
            int totalSum = x + y + shift;
            shift = totalSum / 10;
            int firstSign = totalSum % 10;
            result[positionFinal] = (char) ('0' + firstSign);
            positionFinal--;
            position1--;
            position2--;
        }

        // if number is longer of the longest from (num1, num2) then positionFinal is -1 so we need to set it to 0, otherwise length of number is only length of the longest from (num1, num2), so at 0 pos is 0 set, that's why we move
        positionFinal++;

        return new String(result, positionFinal, size - positionFinal);
    }

    // if one of the numbers is negative and second one is positive we will use this method to subtract smaller number from bigger
    static String subtractDifferentValues(String absolute1, String absolute2) {
        // max possible positions of numbers set here
        int position1 = absolute1.length() - 1;
        int position2 = absolute2.length() - 1;
        // we will use shift to store number
        int shift = 0;
        // maxsize cannot get bigger than length of absolute1
        int size = absolute1.length();
        // set result as array of chars, which will store evaluation of two numbers
        char[] result = new char[size];
        int positionFinal = size - 1;

        // while there are still indexes (of number "absolute1" which is always bigger (that's why we need to only check this one) and equal sign is there because we still can have one more borrowed sign
        while (position1 >= 0) {
            // value at position1 stored in x
            int x = absolute1.charAt(position1) - '0';
            int y = 0;
            // when we count all indexes of position2 we want to set y to 0 otherwise value at position2
            if (position2 >= 0) y = absolute2.charAt(position2) - '0';
            // subtracting borrowed number
            x = x - shift;

            // computing if we need to borrow number
            if (x < y) {
                x = x + 10;
                shift = 1;
            } else {
                shift = 0;
            }

            // computing numbers at positionFinal index
            result[positionFinal] = (char) ('0' + (x - y));
            positionFinal--;
            position1--;
            position2--;
        }

        // we need to get rid of zeroes before number starts
        int countZero = 0;

        while (result[countZero] == '0') {
            countZero++;
        }

        return new String(result, countZero, size - countZero);
    }
}
