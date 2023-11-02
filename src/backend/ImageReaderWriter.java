package backend;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class ImageReaderWriter {

    public static int[][][] loadImageAsArray(String imagePath) {

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));

            int width = image.getWidth();
            int height = image.getHeight();

            int[][][] imageAsArray = new int[height][width][3];

            for (int x = 0; x < height; x++) {
                for (int y = 0; y < width; y++) {
                    int rgb = image.getRGB(y, x);

                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    imageAsArray[x][y] = new int[] {red, green, blue};
                }
            }
            return imageAsArray;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeArrayToImage(int[][][] image, String outputFilePath) {

        int height = image.length;
        int width = image[0].length;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                int[] rgb = image[x][y];
                int color = (rgb[0] << 16) | (rgb[1] << 8) | rgb[2];
                bufferedImage.setRGB(y, x, color);
            }
        }

        try {
            File outputFile = new File(outputFilePath);
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
