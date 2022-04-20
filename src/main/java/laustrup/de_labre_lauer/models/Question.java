package laustrup.de_labre_lauer.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Question {

    private String title,content, author;
    private BufferedImage image;
    private LocalDate timeStamp;

    // Constructor for writing to file
    public Question(String title, String content, String author, BufferedImage image) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.image = image;

        timeStamp = LocalDate.now();
    }

    // Constructor for reading from file
    public Question(String title, String content, String author, LocalDate timeStamp, BufferedImage image) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.timeStamp = timeStamp;
        this.image = image;
    }

    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getAuthor() {return author;}
    public LocalDate getTimeStamp() {return timeStamp;}
    public BufferedImage getImage() {return image;}
    public String getImageLocation() {return "src/main/resources/static/images/question_images/" +
                                        title + "-" + content + "-" + author + "-" + String.valueOf(timeStamp);}

}
