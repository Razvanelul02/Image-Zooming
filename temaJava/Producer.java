package temaJava;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Producer extends Thread {
    private ProcesareImagine procesareImagine;
    private BufferedImage image;
    private int height;
    private int width;

    public Producer(ProcesareImagine procesareImagine, BufferedImage image, int height, int width) {
        this.procesareImagine = procesareImagine;
        this.image = image;
        this.height = height;
        this.width = width;
    }

    @Override
    public void run() {
    	 try {
    	int[][] valoare = ProcesareImagine.getPixelValues(image, height, width);
        procesareImagine.setValoare(valoare);
        
       
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}