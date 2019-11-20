import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileVisitor extends SimpleFileVisitor<Path> {

    private static int folderNumber;
    private static int filesNumber;
    private static long fileSize;

    public static ResultDto read (Path directory) {

        try {
            Files.walkFileTree(directory, new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResultDto(folderNumber, filesNumber, fileSize);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        filesNumber++;
        fileSize += Files.size(file);
       return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        folderNumber++;
        return FileVisitResult.CONTINUE;
    }
}
