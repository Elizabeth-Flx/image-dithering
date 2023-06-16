package backend;

public abstract class DitheringAlgo {

    // Image format
    //
    //  +--------->
    //  |       y
    //  |
    //  | x
    //  v

    public void processFullImage(int[][][] image, Palette palette) {
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image.length; y++) {
                processPixel(image, x, y, palette);
            }
        }
    }

    public abstract void processPixel(int[][][] image, int x, int y, Palette palette);

}
