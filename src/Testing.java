import backend.DitheringAlgo;
import backend.ErrorDiffusion;
import backend.ImageReaderWriter;
import backend.Palette;

public class Testing {


    public static void main(String[] args) {

        int[][][] image = ImageReaderWriter.loadImageAsArray("C:\\Users\\skr3w\\OneDrive\\Pictures\\Dalle2\\DALL·E 2022-10-08 21.44.07 - a large almost empty aiport terminal in the early morning a plane is visible outside one of the windows in a vaporwave aesthetic.png");

        int[][] kernel = new int[][] {
                {0,0,7},
                {3,5,1},
        };

        Palette palette = new Palette(new int[][] {
                {255,0,0},
                {0,0,255},
                {0,255,0},
                {255,255,255},
                {0,0,0}
        });


        DitheringAlgo da = new ErrorDiffusion(kernel);

        da.processFullImage(image, palette);

        ImageReaderWriter.writeArrayToImage(image, "C:\\Users\\skr3w\\OneDrive\\Pictures\\test2.png");

    }


}
