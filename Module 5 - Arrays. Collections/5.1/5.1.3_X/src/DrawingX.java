import java.lang.reflect.Array;

public class DrawingX {

   // public static String[][] xArray;
//
//    public static void addArray() {
//        xArray = new String[][] {
//                {"X", " ", " ", " ", "X"},
//                {" ", "X", " ", "X", " "},
//                {" ", " ", "X", " ", " "},
//                {" ", "X", " ", "X", " "},
//                {"X", " ", " ", " ", "X"}
//        };
//    }
//

    public static String[][] xAddArray (int cout) {
        String[][] xArray = new String[cout][cout];
       // int x = 0;

        for (int i = 0; i < xArray.length; i++) {
            for (int j = 0; j < xArray[i].length; j++) {
                if (i == j || j == xArray.length - i - 1) {
                    xArray[i][j] = "x";
                } else {
                    xArray[i][j] = " ";
                }
            }
        }
        return xArray;
    }

    public static void print() {
        String[][] xArray = xAddArray(9);

        for (int i = 0; i < xArray.length; i++) {
            System.out.println();
            for (int j = 0; j < xArray[i].length; j++) {
                System.out.print(xArray[i][j]);
            }
        }
    }


    public static void main(String[] args) {
      print();
    }
}
