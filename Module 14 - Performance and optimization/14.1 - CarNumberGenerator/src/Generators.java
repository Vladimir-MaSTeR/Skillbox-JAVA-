import java.util.ArrayList;

public class Generators {

    public static String generateNumbers() {

        String list = null;

        for (int i = 0; i < 10; i++) {

            char[] letters = new char[]{'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            StringBuilder carNumber = new StringBuilder();

            for (int firstCycle = 0; firstCycle < letters.length; ++firstCycle) {
                char firstLetter = letters[firstCycle];

                for (int secondCycle = 0; secondCycle < letters.length; ++secondCycle) {
                    char secondLetter = letters[secondCycle];

                    for (int thirdCycle = 0; thirdCycle < letters.length; ++thirdCycle) {
                        char thirdLetter = letters[thirdCycle];

                        for (int num = 1; num < 1000; ++num) {

                            carNumber.append(firstLetter)
                                    .append(padNumber(num, 3))
                                    .append(secondLetter).append(thirdLetter)
                                    .append(padNumber(i, 2))
                                    .append("\n");
                        }
                    }
                }
            }
            list = carNumber.toString();
        }

         return list;
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; ++i) {
            numberStr.insert(0, '0');
        }

        return numberStr.toString();
    }

}
