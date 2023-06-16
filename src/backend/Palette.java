package backend;

public class Palette {

    private int[][] colors;

    public Palette(int[][] colors) {
        this.colors = colors;
    }

    public int[] getClosestColor(int[] compareColor) {
        int[] closestColor = new int[] {0,0,0};
        double smallestDifference = Double.MAX_VALUE;

        for (int[] currentColor :  colors) {
            double currentDifference = Palette.calcColorDifference(currentColor, compareColor);
            if (currentDifference < smallestDifference) {
                closestColor = currentColor;
                smallestDifference = currentDifference;
            }
        }
        return closestColor;
    }

    public static double calcColorDifference(int[] color1, int[] color2) {
        return Math.pow((color1[0] - color2[0]) * 0.30, 2)      // red
             + Math.pow((color1[1] - color2[1]) * 0.59, 2)      // green
             + Math.pow((color1[2] - color2[2]) * 0.11, 2);     // blue
    }
}
