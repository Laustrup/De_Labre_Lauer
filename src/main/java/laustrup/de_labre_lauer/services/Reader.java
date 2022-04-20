package laustrup.de_labre_lauer.services;

import laustrup.de_labre_lauer.models.Question;
import laustrup.de_labre_lauer.repositories.CommonAttributes;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

import static laustrup.de_labre_lauer.repositories.CommonAttributes.getBufferedImage;

public class Reader {

    public Question read(Question question) {

        try {
            Scanner lauBox = new Scanner(new File(CommonAttributes.getLauBoxLocation()));

            lauBox.nextLine();
            while (lauBox.hasNextLine()) {
                String[] line = lauBox.nextLine().split(CommonAttributes.getSplitRegex());
                if (line[0].equals(question.getTitle())&&line[1].equals(question.getContent())&&line[2].equals(question.getAuthor())) {
                    return createQuestion(line);
                }
            }
            lauBox.close();
        }
        catch (Exception e) {
            Printer.printException("Exception caught at reading from LauBox.csv...",e);
            return null;
        }

        Printer.printErr("Couldn't find " + question.getTitle() + " in LauBox...");
        return null;
    }

    public LinkedList<Question> readAll() {
        LinkedList<Question> posts = new LinkedList<>();

        try {
            Scanner lauBox = new Scanner(new File(CommonAttributes.getLauBoxLocation()));

            lauBox.nextLine();
            while (lauBox.hasNextLine()) {
                posts.add(createQuestion(lauBox.nextLine().split(CommonAttributes.getSplitRegex())));
            }
            if (posts.size()!=0) {return posts;}
        }
        catch (Exception e) {
            Printer.printException("Exception caught at reading all from LauBox.csv...",e);
            return null;
        }

        Printer.printErr("Couldn't find anything in LauBox...");
        return null;
    }

    private Question createQuestion(String[] line) throws Exception {
        try {
            LocalDate date = LocalDate.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new Question(line[0],line[1],line[2],date,getBufferedImage(line[4]));
        }
        catch (Exception e) {throw e;}
    }
}
