import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    static String output;

    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("Thanos.jpg"));
            createImage(img);
            FileInterface fi = new FileInterface("Thanos.txt");
            fi.writeFile(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void createImage(BufferedImage img) {
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color color = new Color(img.getRGB(x, y));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                appendColor(red, green, blue);
            }
            output += "\n";
        }
    }

    static void appendColor(int red, int green, int blue) {
        int dominantColor;
        String[] tools;
        if (red >= green && red >= blue) {
            tools = new String[]{" ░ ", " ▒ ", " ▓ "};
            dominantColor = red;
        } else if (green >= red && green >= blue) {
            tools = new String[]{" ⠐ ", " ⠕ ", " ⠿ "};
            dominantColor = green;
        } else {
            tools  = new String[]{" * ", " # ", " $ "};
            dominantColor = blue;
        }

        if (dominantColor >= 166) {
            output += tools[2];
        } else if (dominantColor >= 83) {
            output += tools[1];
        } else {
            output += tools[0];
        }
    }
}
