import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("fatEngineer.jpg"));
            Conversion conversion = new Conversion(img);
            conversion.createAsciiText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
