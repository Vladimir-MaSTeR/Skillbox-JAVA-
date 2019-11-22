import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;

public class DateBerth {

    public static void birthdayDate() {
        var count = 0;
        LocalDate birthday = LocalDate.of(1987, 4, 26);
        LocalDate today = LocalDate.now();

        DateTimeFormatter printformat = DateTimeFormatter.ofPattern("dd.MM.yyyy - E", new Locale("ru"));

        while (today.isAfter(birthday)) {
            System.out.println(count + " - " + printformat.format(birthday));
            LocalDate datePlus = birthday.plusYears(1);
            birthday = datePlus;
            count++;
        }
    }

    public static void main(String[] args) {
        birthdayDate();
    }
}
