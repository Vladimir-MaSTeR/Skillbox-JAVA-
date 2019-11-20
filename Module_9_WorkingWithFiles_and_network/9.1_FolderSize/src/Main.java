import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    private static final String PATH = "C:\\Владимир\\Програмирование\\Java\\Skillbox";

    public static void main(String[] args) {

        Path directory = Paths.get(PATH);

        if (Files.isDirectory(directory)) {
              ResultDto resultDto = MyFileVisitor.read(directory);

            System.out.println("Заданный адрес является папкой:");
            System.out.println("\t Всего вложенных папок  > " + (resultDto.FOLDER_NUMBER - 1));
            System.out.println("\t Всего вложенных файлов > " + resultDto.FILES_NUMBER);
            System.out.print("\t Общий размер фалов     > " + calculationSizeFile(resultDto.FILES_SIZE));

        } else {
            try {
                System.out.println(directory.toString() + " - Заданный адрес является файлом с размером =" + Files.size(directory) + " Байт");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String  calculationSizeFile (long filesSize) {
        if (filesSize / (long) Math.pow(10, 9) != 0) {
           String gB = (filesSize / (long) Math.pow(2, 30)) + " Гигабайт";
           return gB;

        } else if (filesSize / (long) Math.pow(2, 20) != 0) {
            String mB = (filesSize / (long) Math.pow(2, 20)) + " Мегабайт";
            return mB;

        } else if (filesSize / (long) Math.pow(2, 10) != 0) {
           String kB = (filesSize / (long) Math.pow(2, 10)) + " Килабайт";
            return kB;

        } else {
            String b = filesSize + " Байт";
            return b;
        }
    }


//    String path1 = ""; //путь к файлу.
//    File file1 = new File(path1);  // Создали экземпляр класса файл и в его конструктор передали путь к файлу.
//
//        System.out.println("Имя файла с расширением - " + file1.getName() + "\n");
//
//        System.out.println("Путь к файлу - " + file1.getPath());
//        System.out.println("Обсолютный путь к файлу - " + file1.getAbsolutePath() + "\n");
//
//        System.out.println("В какой папке содержится файл - " + file1.getParent() + "\n");
//
//        System.out.println("размер файла в байтах - " + file1.length() + " байт" + "\n");
//
//    След. Группа методов ЛОГИЧЕСКИЕ (try / false) по этому, для удобства используем ТЕРНАРНЫЙ ОПЕРАТОР
//        System.out.println("Существует ли файл? -" + (file1.exists() ? "Файл существует" : "Файл не существует"));
//        System.out.println("Доступен ли для записи файл? - " + (file1.canWrite() ? "Файл Доступен" : "Файл не доступен"));
//        System.out.println("Доступен ли файл для чтения? - " + (file1.canRead() ? "Файл доступен для чтения" : "Файл НЕ ДОСТУПЕН для чтения"));
//        System.out.println("Является ли файл коталогом или папкой? - " + (file1.isDirectory() ? "файл является папкой" : "файл является каталогом"));
//        System.out.println("Является ли файл файлом? - " + (file1.isFile() ? "является файлом" : "не является файлом"));
//        System.out.println("Скрыт файл или нет? - " + (file1.isHidden() ? "файл скрыт" : "файл не скрыт"));
}
