public class MasevStrings {

    public static void main(String[] args) {
        String[] colors = {"Красный", "Оранжевый", "Желтый", "Зеленый", "Голубой", "Синий", "Фиолетовый"};

        String temp;

        for (int i = 0; i < colors.length / 2; i++ ) {
          temp = colors[colors.length - i - 1];
          colors[colors.length - i - 1] = colors[i];
          colors[i] = temp;
        }
        for (String s : colors) {
            System.out.println(s);
        }


//        for (int i = 0; i < colors.length; i++) {
//            System.out.print(colors[(colors.length - 1) - i] + " ");
//        }
    }
}
