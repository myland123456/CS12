/**
 * The class ImagePainter implements a GUI component that displays a GIF file.
 * @author Charlie McDowell
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
@SuppressWarnings("serial")

class ImagePainter extends JComponent {  

  /**
     Construct an empty ImagePainter.
   */
  public ImagePainter() {
  }
  /**
   * Constructs an ImagePainter for a specified GIF file.
   *
   * @param fileName the name of the file from which 
   *    to read the GIF image.
   */
    public ImagePainter(String fileName) {
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
        MediaTracker tracker = new MediaTracker(this);

        // you can call addImage more times to track several images
        // with this one MediaTracker
        tracker.addImage(image, 0);

        // you can also use waitForId(idNum) to wait for a specific image
        // this idNum is the same one passed in the addImage() call
        try { tracker.waitForAll(); }
        catch (InterruptedException e) {}
    }

  /**
   * Paint the image to fill the component.
   *
   * @param g the Graphics object to paint on.
   */
    public void paint(Graphics g) {
        if (image != null) 
            g.drawImage(image, 0, 0, this);
    }

  /**
   * Make the component just big enough to hold the image.
   *
   * @return the dimensions of the image
   */
    public Dimension getMinimumSize() {
        // size the component to just fit the image
        if (image != null) 
            return new Dimension(image.getWidth(this), image.getHeight(this));
        else
            return new Dimension(0,0);
    }

  /**
   * The preferred size is the same as the minimum size.
   *
   * @return the dimensions of the image
   */
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }

    protected Image image;
}
