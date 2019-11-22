
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main
{
    private final static String SRC_FOLDER = "src/images";
    private final static String DST_FOLDER = "src/newImages";

    public static void main(String[] args)
    {
        Path srcPath = Paths.get(SRC_FOLDER);
        Path dstPath = Paths.get(DST_FOLDER);

        File srcDirectory = new File(srcPath.toUri());

        try {
            File[] file = srcDirectory.listFiles();

            ImageResizer imageResizer = new ImageResizer();
            assert file != null;
            Queue<File> queue = new ConcurrentLinkedQueue<>(Arrays.asList(file));
            imageResizer.resizer(queue, dstPath.toString());

        } catch (NullPointerException | InterruptedException e) {
            System.out.println("Файл не найден");
        }


    }
}
