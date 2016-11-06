/**
* Name Victor Tamayo
*
* This prgram will read and image as a BufferedImage and converted to
* Grayscale image using the formula  0.2989*red + 0.5870*green + 0.1140*blue
*/

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Color;
import java.awt.image.WritableRaster;

public class RgbToGrey{
  private BufferedImage img;
  private Color color;
  public RgbToGrey(BufferedImage image){
    img = image;
  }

  public int getWidth(){
    return img.getWidth();
  }

  public int getHeight(){
    return img.getHeight();
  }


/**
* This method converts a color RGB image to greyscale image
* @return a greyscale image
*/
  public BufferedImage toGrayScale(){
    int w = this.getWidth();
    int h = this.getHeight();

    //create a new BufferedImage to set gray value
    BufferedImage dstImg = new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);
    //create a
    WritableRaster wr = dstImg.getRaster();

    // go through each pixel in the image
    for(int y = 0; y < h; ++y)
      for(int x = 0; x < w; ++x){
        //create a Color instance to access RGB elements
        color = new Color(img.getRGB(x,y));

        //computer grayvalue for a given color(RGB)
        double grey = (0.2989*(color.getRed()) +
          0.5870*(color.getGreen()) + 0.1140*(color.getBlue()));

        //set the gray value
        wr.setSample(x,y,0,grey);
      }
      return dstImg;
  }

  public static void main(String args[]){
    try{
        RgbToGrey greyImage = new RgbToGrey(ImageIO.read(new File("images/obama.jpg")));
        BufferedImage i = greyImage.toGrayScale();
        ImageIO.write(i,"jpg",new File("images/obamaGrey.jpg"));
    }catch(IOException e){
      e.printStackTrace();
      System.exit(1);
    }
  }
}
