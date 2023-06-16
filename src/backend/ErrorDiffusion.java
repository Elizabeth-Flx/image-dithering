package backend;

public class ErrorDiffusion extends DitheringAlgo {

    private int[][] kernel;
    public int kernelSum;

    public ErrorDiffusion(int[][] kernel) {
        this.kernel = kernel;
        int kernelSum = 0;
        for (int[] i : kernel) {
            for (int j : i) {
                kernelSum += j;
            }
        }
        this.kernelSum = kernelSum;
    }


    @Override
    public void processPixel(int[][][] image, int x, int y, Palette palette) {
        int[] currentPixel = image[x][y];
        int[] closestColor = palette.getClosestColor(currentPixel);

        int[] error = new int[] {
                currentPixel[0]-closestColor[0],
                currentPixel[1]-closestColor[1],
                currentPixel[2]-closestColor[2],
        };
        spreadError(image, x, y, error);

        image[x][y] = closestColor;
    }

    private void spreadError(int[][][] image, int x, int y, int[] error) {

        int yShift = -(int)(kernel[0].length / 2.0);

        // i and j refer to positions in the kernel
        for (int i = 0; i < kernel.length; i++) {
            for (int j = 0; j < kernel[i].length; j++) {

                int newX = x+i;
                int newY = y+j+yShift;

                // Check if new positions are in image bounds
                if (newX >= 0 && newX < image.length && newY >= 0 && newY < image[newX].length) {
                    image[newX][newY][0] += (int)((error[0] * kernel[i][j] / kernelSum) + 0.5); // Red channel
                    image[newX][newY][1] += (int)((error[1] * kernel[i][j] / kernelSum) + 0.5); // Green channel
                    image[newX][newY][2] += (int)((error[2] * kernel[i][j] / kernelSum) + 0.5); // Blue channel
                }
            }
        }
    }


    public static void main(String[] args) {

        int[][] testKernel = new int[][] {
                {0,0,1},
                {2,3,4},
        };

        int[][][] testImage = new int[][][] {
                {{},{},{},{},       {},     {},     {}},
                {{},{},{},{0,0,0},  {0,0,0},{4,4,4},{}},
                {{},{},{},{5,5,5},  {1,2,3},{7,7,7},{}},
                {{},{},{},{},       {},     {},     {}},
                {{},{},{},{},       {},     {},     {}},
        };

        ErrorDiffusion ed = new ErrorDiffusion(testKernel);
        ed.kernelSum = 2;

        ed.spreadError(testImage, 1, 4, new int[]{10,20,30});

        for (int i = 0; i < testImage.length; i++) {
            System.out.println();
            for (int j = 0; j < testImage[i].length; j++) {
                System.out.print("{");
                for (int k = 0; k < testImage[i][j].length; k++) {
                    System.out.print(testImage[i][j][k]);
                    System.out.print(",");
                }
                System.out.print("}         ");
            }
        }


    }


}
