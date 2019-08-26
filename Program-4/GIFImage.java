import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

class GIFImage {
  
  /**
   * Load an image from a specified GIF file.
   *
   * @param fileName the name of the file from which to read the GIF image.
   * @param owner the component on which this image will be displayed
   */
  public GIFImage(String fileName, Component owner) {
      BufferedImage buff = null;
      try {
	  buff = ImageIO.read(getClass().getResourceAsStream(fileName));
      } catch (IOException e) {
	  e.printStackTrace();
	  image = null;
	  return;
      }
      image = buff;

    // the MediaTracker constructor wants an ImageObserver
    // all components implement ImageObserver so just use "this"
    MediaTracker tracker = new MediaTracker(owner);
    
    // you can call addImage more times to track several images
    // with this one MediaTracker
    tracker.addImage(image, 0/*user selected id to identify the image*/);
    
    // you can also use waitForId(idNum) to wait for a specific image
    // this idNum is the same one passed in the addImage() call
    try { tracker.waitForAll(); }
    catch (InterruptedException e) {}
  }
  public final Image image;
}
