
import java.io.File;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImageResizer {

    private final int THREADS_COUNT = Runtime.getRuntime().availableProcessors();

    public void resizer (Queue <File> files, String dstFolder) throws InterruptedException {

        System.out.println("Начал работу...");
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);

        for (int i = 0; i < THREADS_COUNT; i++) {
            executorService.submit(new ThreadImg(files, dstFolder));
        }

        executorService.shutdown();
        executorService.awaitTermination(40, TimeUnit.SECONDS);
        System.out.println("Работу закочил!");
        System.out.println("Время работы: " + (System.currentTimeMillis() - startTime) + " Милисекунд");

    }
}
