
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) {

        Path path = Paths.get("C:\\Владимир\\Програмирование\\Java\\Skillbox\\1м-Вводный модуль");
        Path pathCopy = Paths.get("C:\\Владимир\\1");

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path pathNewCopy = pathCopy.resolve(path.relativize(dir));
                    Files.createDirectories(pathNewCopy);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path newFile = pathCopy.resolve(path.relativize(file));
                    Files.copy(file, newFile);
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("----------------------------------------");
            System.out.println("\t Копирование успешно завершено");
            System.out.println("----------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
