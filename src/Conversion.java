import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Conversion {
    static String output;
    static BufferedImage img;

    Conversion(BufferedImage img) {
        this.img = img;
    }

    public static void createAsciiText() {

        BufferedImage tempImg = scale(img, BufferedImage.TYPE_BYTE_BINARY, img.getWidth(), img.getHeight(), 0.5, 0.5);
        int[][] arr = new int[tempImg.getWidth()][tempImg.getHeight()];
        for (int x = 0; x < tempImg.getWidth(); x++){
            for (int y = 0; y < tempImg.getHeight(); y ++){
                Color color = new Color(tempImg.getRGB(x, y));
                arr[x][y] = (color.getRed() / 255);
            }
        }

        //we have arr that filled with 1s and 0s, represents

        for (int x = 0; x <= tempImg.getWidth() - 2; x += 2) {
            for (int y = 3; y <= tempImg.getHeight() - 3; y += 3) {
                boolean[][] snippet = new boolean[][] {
                        {arr[x-2][y] == 1, arr[x][y+1] == 1},
                        {arr[x-1][y] == 1, arr[x][y+1] == 1},
                        {arr[x][y] == 1, arr[x][y+1] == 1}
                };
                output += appendAscii(snippet);
            }
            output += "\n";
        }
    }

    static String appendAscii(boolean[][] snippet) {
        //String[] tools = {"-", "/", "|", "\\"}; //U+005C --> \
        if (sni)
        return""; //nothing
    }

    static void printAsciiText(int[][] arr) {
        for (int y = 0; y < img.getHeight(); y++){
            for (int x = 0; x < img.getWidth(); x++) {
                System.out.print(arr[x][y]);
            }
            System.out.println();
        }
    }

    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, imageType);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
            g.drawRenderedImage(sbi, at);
        }
        return dbi;
    }


    static void createUnicodeText() {
        output = new String();
        for (int y = 0; y < img.getHeight(); y += 3) {
            for (int x = 0; x < img.getWidth(); x += 2) {
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
            tools = new String[]{"░", "▒", "▓"};
            dominantColor = red;
        } else if (green >= red && green >= blue) {
            tools = new String[]{"⠐", "⠕", "⠿"};
            dominantColor = green;
        } else {
            tools  = new String[]{"*", "#", "$"};
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

    static String getOutput() {
        return output;
    }

    static BufferedImage getImg() {
        return img;
    }
}
