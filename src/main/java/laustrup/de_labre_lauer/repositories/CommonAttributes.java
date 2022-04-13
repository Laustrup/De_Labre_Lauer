package laustrup.de_labre_lauer.repositories;

/*
    This class is made, so common global attributes can be found by this class' methods.
 */

public class CommonAttributes {

    private final static String splitRegex = "_REGEX_",
    lauBoxLocation = "/src/main/java/laustrup/de_labre_lauer/repositories/LauBox.csv";

    public static String getLauBoxLocation() {return lauBoxLocation;}
    public static String getSplitRegex() {return splitRegex;}
}
