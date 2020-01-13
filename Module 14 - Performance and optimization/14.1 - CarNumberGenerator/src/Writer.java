import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Writer {

    public static void run() {

        Queue<String> queue = new ConcurrentLinkedQueue<>();
        queue.add(Generators.generateNumbers());

        int coreCount = Runtime.getRuntime().availableProcessors() * 2;
        long startTime = System.currentTimeMillis();
        Path targetPath = Paths.get("res\\number.txt");
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);

        try {
            executorService.submit(() -> { Generators.generateNumbers(); String str;

                while (queue.size() > 0) {
                    try {
                        if ((str = queue.poll()) != null) {
                            Files.write(targetPath, str.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            executorService.shutdown();
            executorService.awaitTermination(30, TimeUnit.SECONDS);
            System.out.println((System.currentTimeMillis() - startTime) + " Милесекунды");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
