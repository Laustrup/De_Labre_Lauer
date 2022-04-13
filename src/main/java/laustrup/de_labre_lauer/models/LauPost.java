package laustrup.de_labre_lauer.models;

import java.time.LocalDate;
import java.util.List;

public class LauPost {

    private String title,content, author, imageLocation;
    private LocalDate timeStamp;

    // Constructor for writing to file
    public LauPost(String title, String content, String author, String imageLocation) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.imageLocation = imageLocation;

        timeStamp = LocalDate.now();
    }

    // Constructor for reading from file
    public LauPost(String title, String content, String author, LocalDate timeStamp, String imageLocation) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.timeStamp = timeStamp;
        this.imageLocation = imageLocation;
    }

    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getAuthor() {return author;}
    public LocalDate getTimeStamp() {return timeStamp;}
    public String getImageLocation() {return imageLocation;}

}
