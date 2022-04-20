package laustrup.de_labre_lauer.services;

import laustrup.de_labre_lauer.models.Question;
import laustrup.de_labre_lauer.repositories.CommonAttributes;

import javax.imageio.ImageIO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Writer {

    private Reader reader = new Reader();

    public Question write(Question question) {

        if (!(question.getTitle().contains(CommonAttributes.getSplitRegex())||question.getContent().contains(CommonAttributes.getSplitRegex())||
                question.getAuthor().contains(CommonAttributes.getSplitRegex()))&&reader.read(question)!=null) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(CommonAttributes.getLauBoxLocation(),true));
                writer.append(question.getTitle()).append(CommonAttributes.getSplitRegex())
                        .append(question.getContent()).append(CommonAttributes.getSplitRegex())
                        .append(question.getAuthor()).append(CommonAttributes.getSplitRegex())
                        .append(String.valueOf(question.getTimeStamp())).append(CommonAttributes.getSplitRegex())
                        .append(question.getImageLocation());
                createImage(question);
                writer.close();
            }
            catch (Exception e) {
                Printer.printException("Exception caught at writing to LauBox.csv...",e);
                return null;
            }

            return reader.read(question);
        }
        Printer.printErr("Something contained " + CommonAttributes.getSplitRegex() + " ...");
        return null;
    }

    private File createImage(Question question) {
        try {
            File output = new File(question.getImageLocation());
            ImageIO.write(question.getImage(),"png",output);
            return output;
        }
        catch (Exception e) {
            Printer.printException("Exception caught at writing image...",e);
            return null;
        }
    }
}
