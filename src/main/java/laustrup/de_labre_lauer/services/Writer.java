package laustrup.de_labre_lauer.services;

import laustrup.de_labre_lauer.models.LauPost;
import laustrup.de_labre_lauer.repositories.CommonAttributes;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Writer {

    private Reader reader = new Reader();

    public LauPost write(LauPost post) {

        if (!(post.getTitle().contains(CommonAttributes.getSplitRegex())||post.getContent().contains(CommonAttributes.getSplitRegex())||
                post.getAuthor().contains(CommonAttributes.getSplitRegex())||post.getImageLocationsAsString().contains(CommonAttributes.getSplitRegex()))
                &&reader.read(post)!=null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(CommonAttributes.getLauBoxLocation(),true));
                writer.append(post.getTitle()).append(CommonAttributes.getSplitRegex())
                        .append(post.getContent()).append(CommonAttributes.getSplitRegex())
                        .append(post.getAuthor()).append(CommonAttributes.getSplitRegex())
                        .append(String.valueOf(post.getTimeStamp())).append(CommonAttributes.getSplitRegex())
                        .append(imageLocations(post));
                writer.close();
            }
            catch (Exception e) {
                Printer.printException("Exception caught at writing to LauBox.csv...",e);
                return null;
            }

            return reader.read(post);
        }
        Printer.printErr("Something contained " + CommonAttributes.getSplitRegex() + " ...");
        return null;
    }
    private String imageLocations(LauPost post) {
        String locations = new String();

        for (int i = 0; i < post.getImageLocations().size();i++) {
            if (i == post.getImageLocations().size()-1) {
                locations += post.getImageLocations().get(i) + "\n";
                break;
            }
            locations += post.getImageLocations().get(i) + CommonAttributes.getSplitRegex();
        }

        return locations;
    }
}
