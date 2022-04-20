package laustrup.de_labre_lauer.repositories;

/*
    This class is made, so common global attributes can be found by this class' methods.
 */

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class CommonAttributes {

    private final static String splitRegex = "_REGEX_",
    lauBoxLocation = "/src/main/java/laustrup/de_labre_lauer/repositories/LauBox.csv";

    public static String getLauBoxLocation() {return lauBoxLocation;}
    public static String getSplitRegex() {return splitRegex;}

    public static BufferedImage getBufferedImage(String path) throws IOException {

        ImageInputStream stream = ImageIO.createImageInputStream(new File(path));
        Iterator<ImageReader> iterator = ImageIO.getImageReaders(stream);
        ImageReader reader = iterator.next();
        reader.setInput(stream);

        return reader.read(0);
    }

}

