package laustrup.de_labre_lauer.services;

import laustrup.de_labre_lauer.models.LauPost;
import laustrup.de_labre_lauer.repositories.LocationMap;

import java.io.FileWriter;

public class Writer {

    private Reader reader = new Reader();

    public LauPost write(LauPost post) {

        if (!(post.getTitle().contains("|")||post.getContent().contains("|")||
                post.getAuthor().contains("|")||post.getImageLocationsAsString().contains("|"))) {
            try {
                FileWriter writer = new FileWriter(LocationMap.getLauBoxLocation());
                writer.write(post.getTitle() + "|" +
                                post.getContent() + "|" +
                                post.getAuthor() + "|" +
                                post.getTimeStamp() + "|" +
                                post.getImageLocationsAsString() + "\n");
                writer.close();
            }
            catch (Exception e) {
                Printer.printException("Exception caught at writing to LauBox.csv...",e);
                return null;
            }

            return reader.read(post);
        }
        Printer.printErr("Something contained the character | ...");
        return null;
    }
}
