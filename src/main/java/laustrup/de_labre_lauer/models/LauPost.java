package laustrup.de_labre_lauer.models;

import java.time.LocalDate;
import java.util.List;

public class LauPost {

    private String title,content, author;
    private LocalDate timeStamp;
    private List<String> imageLocations;

    // Constructor for writing to file
    public LauPost(String title, String content, String author, List<String> imageLocations) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.imageLocations = imageLocations;

        timeStamp = LocalDate.now();
    }

    // Constructor for reading from file
    public LauPost(String title, String content, String author, LocalDate timeStamp, List<String> imageLocations) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.timeStamp = timeStamp;
        this.imageLocations = imageLocations;
    }

    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getAuthor() {return author;}
    public LocalDate getTimeStamp() {return timeStamp;}
    public List<String> getImageLocations() {return imageLocations;}
    public String getImageLocationsAsString() {
        String locations = new String();

        for (int i = 0; i < imageLocations.size();i++) {
            locations += imageLocations.get(i);
        }

        return locations;
    }
}
