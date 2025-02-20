import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        for (int length = 1; length <= 50; length++) {
            //int len = 1;

            boolean isNegative = (random.nextInt(2) == 1);


            if (!isNegative) {
                char[] number = new char[length];

                number[0] = (char) ('1' + random.nextInt(9));

                for (int i = 1; i < length; i++) {
                    number[i] = (char) ('0' + random.nextInt(10));
                    //len++;
                }

                System.out.println(new String(number));
            } else {
                char[] number = new char[length + 1];
                int len = 1;
                number[0] = '-';
                number[1] = (char) ('1' + random.nextInt(9));

                for (int i = 2; i < length + 1; i++) {
                    number[i] = (char) ('0' + random.nextInt(10));
                    len++;
                }

                System.out.println(new String(number));
                System.out.println(len);
            }

            //System.out.println(len);
        }
    }
}


