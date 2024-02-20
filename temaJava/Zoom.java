package temaJava;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;

public class Zoom implements Interfata{
	
	//get image width and height 
	int width;    //width of the image
	int height;  //height of the image
	
	//new image dimension
	int newWidth;		//width of the image
	int newHeight;	//height of the image
	
	private int nrLinii;
	private int nrColoane;
	
	public BufferedImage image = null;
	public static File f = null;
		
	//incapsulare
	public int getNrLinii() {
		return nrLinii;
	}

	public void setNrLinii(int nrLinii) {
		if(nrLinii > 0)
			this.nrLinii = nrLinii;
	}

	public int getNrColoane() {
		return nrColoane;
	}

	public void setNrColoane(int nrColoane) {
		if(nrColoane > 0)
			this.nrColoane = nrColoane;
	}
	
	//polimorfism
	//afisare numar de linii
	public void afisare(int nrLinii)
	{
		System.out.println("Numar linii : " + this.nrLinii);
	}
	
	//afisare numar linii si numar coloane
	public void afisare(int nrLinii, int nrColoane)
	{
		System.out.println("Numar linii : " + this.nrLinii + "\n" + "Numar coloane : " + this.nrColoane);
	}
	
	//citirea unei imagini
	public BufferedImage readImage(String nume)
	{
		//read image
		 try{
			
		   //imaginea care se citeste
		    f = new File( nume + ".bmp"); //image file path
		    image = new BufferedImage(225, 225, BufferedImage.TYPE_INT_ARGB);
		    image = ImageIO.read(f);
		    
		    System.out.println("Reading complete.");
		    
		  }catch(IOException e){
		    System.out.println("Error: "+e);
		  }
		return image;
			 
	}
	
	//se retuneaza width-ul imaginii
	public int getWidth(BufferedImage image2) {
		return ((RenderedImage) image2).getWidth();    //width of the image
	}
	
	//se seteaza width-ul imaginii
	public void setWidth(int width) {
		if(width > 0)
			this.width = width;
	}
	
	//se retuneaza height-ul imaginii
	public int getHeight(BufferedImage image2) {
		return ((RenderedImage) image2).getHeight();  //height of the image
	}
	
	//se seteaza height-ul imaginii
	public void setHeight(int height) {
		if(height > 0)
			this.height = height;
	}
	
	//se retuneaza width-ul imaginii redimensionate
	public static int getnewWidth(int width) {
		return 2 * width - 1;	//width of the image
	}
	
	//se seteaza width-ul imaginii redimensionate
	public void setnewWidth(int newWidth) {
		if(newWidth > 0)
			this.newWidth = newWidth;
	}
	
	//se retuneaza height-ul imaginii redimensionate
	public static int getnewHeight(int height) {
		return 2 * height - 1;	//height of the image
	}
	
	//se seteaza height-ul imaginii redimensionate
	public void setnewHeight(int newHeight) {
		if(newHeight > 0)
			this.newHeight = newHeight;
	}
	
	//folosire varargs
	public void afis(int ...in )
	{
		for(int i : in)
			System.out.print(i + " ");
	}
	
	@Override
	public void getInformatii() {
		// TODO Auto-generated method stub
		System.out.println("Width = " + width);
		System.out.println("Height = " + height);
	}
	

}



