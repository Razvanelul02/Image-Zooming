package temaJava;

import java.awt.image.BufferedImage;

public class Consumer extends Thread {
    private ProcesareImagine procesareImagine;
    private int newHeight;
    private int newWidth;
    private BufferedImage newimg;

    public Consumer(ProcesareImagine procesareImagine, int newHeight, int newWidth, BufferedImage newimg) {
        this.procesareImagine = procesareImagine;
        this.newHeight = newHeight;
        this.newWidth = newWidth;
        this.newimg = newimg;
    }

    @Override
    public void run() {
    	try {
        int[][] valoare = procesareImagine.getValoare(); // Retrieve valoare from ProcesareImagine
        procesareImagine.processImage(valoare, newHeight, newWidth, newimg);
        
        
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
