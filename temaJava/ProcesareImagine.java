package temaJava;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ProcesareImagine extends Zoom {
    private BufferedImage newimg;
    private int[][] inter;
    private File newf;
    private String numeFisierOut;

    private int[][] valoare;
    private Buffer buffer;

    public ProcesareImagine(BufferedImage newimg, int[][] inter, File newf, String numeFisierOut) {
        this.newimg = newimg;
        this.inter = inter;
        this.newf = newf;
        this.numeFisierOut = numeFisierOut;
        this.buffer = new Buffer();
    }

    public void setValoare(int[][] valoare) {
        buffer.setValoare(valoare);
    }

    public int[][] getValoare() {
        return buffer.getValoare();
    }
	
	public static int[][] getPixelValues(BufferedImage image, int height, int width) {
        int[][] valoare = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(image.getRGB(i, j));
                int p = (c.getRed() << 16) | (c.getGreen() << 8) | c.getBlue();
                valoare[i][j] = p;
            }
        }

        return valoare;
    }

    public static BufferedImage processImage(int[][] valoare, int newHeight, int newWidth, BufferedImage newimg) {
        int[][] inter = new int[newHeight][newWidth];

        int l = -1;
        int c = -1;

        for (int i = 0; i < newHeight; i = i + 2) {
            l++;
            c = -1;
            for (int j = 0; j < newWidth; j = j + 2) {
                c++;
                newimg.setRGB(i, j, valoare[l][c]);
            }
        }

        int newValoare = 0;

        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                Color c2 = new Color(newimg.getRGB(i, j));
                int p = (c2.getAlpha() << 24) | (c2.getRed() << 16) | (c2.getGreen() << 8) | c2.getBlue();
                inter[i][j] = p;
                newimg.setRGB(i, j, p);
            }
        }

        for (int i = 0; i < newHeight; i = i + 2) {
            for (int j = 0; j < newWidth - 2; j = j + 2) {
                newValoare = (int) ((inter[i][j] + inter[i][j + 2]) / 2);
                newimg.setRGB(i, j + 1, newValoare);
            }
        }

        for (int j = 0; j < newWidth; j = j + 2) {
            for (int i = 0; i < newHeight - 2; i = i + 2) {
                newValoare = (int) ((inter[i][j] + inter[i + 2][j]) / 2);
                newimg.setRGB(i + 1, j, newValoare);
            }
        }

        return newimg;
    }
}
