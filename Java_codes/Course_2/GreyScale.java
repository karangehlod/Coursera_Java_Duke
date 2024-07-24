package Course_2;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

public class GreyScale {
    public static ImageResource makeGray(ImageResource inImage) {
		//blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			//set pixel's average
			pixel.setRed(average);
			pixel.setGreen(average);
			pixel.setBlue(average);
		}
		return outImage;
	}

	public static void main(String[] args) {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource grayscale = makeGray(inImage);
			String fname = inImage.getFileName();
            String newName = "gray-" + fname;
            grayscale.setFileName("./" + newName);
            grayscale.draw();
            grayscale.save();
		}
	}   
}
