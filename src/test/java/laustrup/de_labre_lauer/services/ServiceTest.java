package laustrup.de_labre_lauer.services;

import laustrup.de_labre_lauer.models.LauPost;
import laustrup.de_labre_lauer.repositories.CommonAttributes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

// Check that LauBox path is correct in CommonAttributes

class ServiceTest {

    private Reader reader = new Reader();
    private Writer writer = new Writer();

    private final String splitRegex = CommonAttributes.getSplitRegex();

    @ParameterizedTest
    @CsvSource(value = {"Test|Dette er en test|Søren|2022-04-13|src/main/resources/static/images/Lauritz1.png",
                        "Anden test|Nu med_REGEX_|Gitte|2022-04-13|src/main/resources/static/images/Lauritz1.png",
                        "Tredje_REGEX_test|Endnu med_REGEX_|Sørensen|2022-04-13|src/main/resources/static/images/Lauritz1.png",
                        "Fjerde_REGEX_test|Endnu med_REGEX_i link|Tine|2022-04-13|src/main/resources/static/images/Lauritz1.png_REGEX_",
                        "Billede test|Med flere billeder|Frederik|2022-04-13|" +
                                "src/main/resources/static/images/Lauritz1.png-src/main/resources/static/images/laust.jpg"},
                        delimiter = '|')
    void createAndReadTest(String title, String content, String author, LocalDate timeStamp, String imageLocations) {
        // Arrange
        LauPost expected = new LauPost(title, content, author, timeStamp,
                            new LinkedList<>(Arrays.asList(imageLocations.split("-"))));

        // Act
        LauPost actual = writer.write(expected);

        // Assert
        if (!(expected.getTitle().contains(splitRegex)||expected.getContent().contains(splitRegex)||
                expected.getAuthor().contains(splitRegex)||expected.getImageLocationsAsString().contains(splitRegex))) {
            assertEquals(expected.getTitle(),actual.getTitle());
            assertEquals(expected.getContent(),actual.getContent());
            assertEquals(expected.getAuthor(),actual.getAuthor());
            assertEquals(expected.getTimeStamp(),actual.getTimeStamp());
            assertEquals(expected.getImageLocationsAsString(),actual.getImageLocationsAsString());
        }
        else {
            assertTrue(reader.read(expected)==null);
        }
    }

    @Test
    void readAllTest() {
        // Act
        LinkedList<LauPost> act = reader.readAll();
        for (int i = 0; i < act.size();i++) {
            Printer.printLn(act.get(i).getTitle());
        }

        // Assert
        assertTrue(reader.readAll()!=null);
    }

}