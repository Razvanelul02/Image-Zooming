package testJava;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import temaJava.Zoom;
import temaJava.ProcesareImagine;
import temaJava.Producer;
import temaJava.Consumer;

public class Main extends Zoom {
		
		public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Zoom z = new Zoom();
		BufferedImage image;
		
		//get image width and height 
		int width;    //width of the image
		int height;  //height of the image
		
		//new image dimension
		int newWidth;		//width of the image
		int newHeight;	//height of the image
		
		z.setNrLinii(200);
		z.afisare(z.getNrLinii());
		
		//citirea de la tastatura a numelui pozei ce se doreste sa se mareasa si numele pozei rezultate  
		
	 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Introduceti numele pozei ce doriti sa o mariti:");
		String numeFisierIn = br.readLine();
		System.out.println("Introduceti un nume pentru poza marita:");
		String numeFisierOut = br.readLine();
		
		br.close();
		
		System.out.println("Numele pozei " + numeFisierIn);
		System.out.println("Numele pozei marite " + numeFisierOut);
		
		image = z.readImage(numeFisierIn);
		
		//get image width and height 
		System.out.println("Width " +z.getWidth(image));
		height = z.getWidth(image);
		System.out.println("Height " +z.getHeight(image));
		width = z.getWidth(image);
		
		//folosire varargs
		System.out.println("Varargs");
		z.afis(height);
		System.out.println();
		z.afis(height,width);
		System.out.println();
		
		
		
		//new image dimension
		newWidth = getnewWidth(width);
		newHeight = getnewHeight(height);
		System.out.println("newWidth " +  newWidth);
		System.out.println("newHeight " + newHeight);
		
		int[][] valoare = ProcesareImagine.getPixelValues(image, height, width);
		
		BufferedImage newimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

		BufferedImage processedImage = ProcesareImagine.processImage(valoare, newHeight, newWidth, newimg);

		File newf = null;
        int[][] inter = new int[newHeight][newWidth];

		ProcesareImagine procesareImagine = new ProcesareImagine(newimg, inter, newf, numeFisierOut);
		
		 Producer producer = new Producer(procesareImagine, image, height, width);
	     Consumer consumer = new Consumer(procesareImagine, newHeight, newWidth, newimg);

	        producer.start();
	        consumer.start();

	        // Wait for both threads to finish
	        try {
	            producer.join();
	            consumer.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		/*
		//cream o matrice ce este o copie intermediara o imaginii finale
		int[][] inter = new int [newHeight][newWidth];
		
		//create buffered image object img
		BufferedImage newimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
		//file object
		File newf = null;
		
		ProcesareImagine prImage = new ProcesareImagine(newimg, inter, newf, numeFisierOut);
		
		prImage.imagineMarita(width, height, newWidth, newHeight);
		
		//matricea valoare contine valoarea fiecarui pixel
				int[][] valoare = new int [height][width];
			
		//am luat valorile lui alpha,read,green si blue pentru fiecare pixel al imaginii 
		//am retinut aceste valori in vectorul valoare
	    for(int i = 0; i < height; i++) {
	    	for(int j = 0; j < width; j++) {
	           
	    		Color c = new Color(image.getRGB(i, j));           
	    		int p = (c.getAlpha()<<24) | (c.getRed()<<16) | (c.getGreen()<<8) | c.getBlue(); //pixel
	    		valoare[i][j] = p;
	    	}
	    }

	    int l = -1;
		int c = -1;
		//initializam poza cea noua
		for(int i = 0; i < newHeight; i = i + 2) {
			l++;
			c = -1;
			for(int j = 0; j < newWidth; j = j + 2) { 
				c++;
				newimg.setRGB(i, j, valoare[l][c]);         
			}
		}
	        
		int newValoare = 0;
	      
		//cream o matrice ce este o copie intermediara o imaginii finale
		for(int i = 0; i < newHeight; i++) {
			for(int j = 0; j < newWidth; j++) {
	          
				Color c2 = new Color(newimg.getRGB(i, j));             
				int p = (c2.getAlpha()<<24) | (c2.getRed()<<16) | (c2.getGreen()<<8) | c2.getBlue(); //pixel
				inter[i][j] = p;
				newimg.setRGB(i, j, p);
			}
		}
	      
		//lucram intai pe linii 
		for(int i = 0; i < newHeight; i = i + 2) {
			for(int j = 0; j < newWidth-2; j = j + 2) { 
				newValoare = (int)((inter[i][j] + inter[i][j+2])/2);
				newimg.setRGB(i, j + 1, newValoare);               
			}
		}
	        
		//apoi lucram pe coloane
		for(int j = 0; j < newWidth; j = j + 2) {
			for(int i = 0; i < newHeight-2; i = i + 2) {
				newValoare = (int)((inter[i][j] + inter[i+2][j])/2);
				newimg.setRGB(i + 1, j, newValoare);
			}
		}  
	 */
		//write image
		try{
			newf = new File( numeFisierOut + ".png");
			ImageIO.write(newimg, "png", newf);
	       
	       
			System.out.println("Writing complete.");
			}catch(IOException e){
				System.out.println("Error: " + e);
			}

		}
		

}
