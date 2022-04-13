package laustrup.de_labre_lauer.services;

import laustrup.de_labre_lauer.models.LauPost;
import laustrup.de_labre_lauer.repositories.CommonAttributes;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

public class Reader {

    public LauPost read(LauPost post) {

        try {
            Scanner lauBox = new Scanner(new File(CommonAttributes.getLauBoxLocation()));

            lauBox.nextLine();
            while (lauBox.hasNextLine()) {
                String[] line = lauBox.nextLine().split(CommonAttributes.getSplitRegex());
                if (line[0].equals(post.getTitle())&&line[1].equals(post.getContent())&&line[2].equals(post.getAuthor())) {
                    return createLauPost(line);
                }
            }
            lauBox.close();
        }
        catch (Exception e) {
            Printer.printException("Exception caught at reading from LauBox.csv...",e);
            return null;
        }

        Printer.printErr("Couldn't find " + post.getTitle() + " in LauBox...");
        return null;
    }

    public LinkedList<LauPost> readAll() {
        LinkedList<LauPost> posts = new LinkedList<>();

        try {
            Scanner lauBox = new Scanner(new File(CommonAttributes.getLauBoxLocation()));

            lauBox.nextLine();
            while (lauBox.hasNextLine()) {
                posts.add(createLauPost(lauBox.nextLine().split(CommonAttributes.getSplitRegex())));
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

    private LauPost createLauPost(String[] line) throws Exception {
        try {
            LocalDate date = LocalDate.parse(line[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return new LauPost(line[0],line[1],line[2],date,line[4]);
        }
        catch (Exception e) {
            throw e;
        }
    }
}
