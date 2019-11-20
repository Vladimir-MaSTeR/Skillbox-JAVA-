
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Queue;
import javax.imageio.ImageIO;

public class ThreadImg implements Runnable {

  private Queue<File> files;
  private String dstFolder;

  public ThreadImg(Queue<File> files, String dstFolder) {
    this.files = files;
    this.dstFolder = dstFolder;
  }

  @Override
  public void run() {
    try {
      File file;
      while (files.size() > 0) {
        if ((file = files.poll()) != null) {
          BufferedImage image = ImageIO.read(file);

          int newWidth = 300;
          int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));

          BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

          int widthStep = image.getWidth() / newWidth;
          int heightStep = image.getHeight() / newHeight;

          for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
              int rgb = image.getRGB(x * widthStep, y * heightStep);
              newImage.setRGB(x, y, rgb);
            }
          }
          File newFile = new File(dstFolder + "/" + (file).getName());
          ImageIO.write(newImage, "jpg", newFile);
          System.out.println(file.getName() + " " + Thread.currentThread().getName());
          Thread.sleep(200);

        }
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
