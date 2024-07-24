package Course_2;

import java.io.File;

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

public class BatchInversion {
    public static ImageResource makeInversion(ImageResource inImage) {
        // blank image
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            // set pixel's to 255- inPixel.getRGB()
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }

    // demo
    public static void main(String[] args) {
        DirectoryResource directory = new DirectoryResource();
        for (File file : directory.selectedFiles()) {
            ImageResource image = new ImageResource(file);
            ImageResource invertedImage = makeInversion(image);
            String fname = image.getFileName();
            String newName = "inverted-" + fname;
            invertedImage.setFileName("./" + newName);
            invertedImage.draw();
            invertedImage.save();
        }
    }
}
