package laustrup.de_labre_lauer.services;

import laustrup.de_labre_lauer.models.LauPost;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Reader reader = new Reader();
    private Writer writer = new Writer();

    @ParameterizedTest
    @CsvSource(value = {"Test_Dette er en test_Søren_2022-04-13_src/main/resources/static/images/Lauritz1.png",
                        "Anden test_Nu med|_Gitte_2022-04-13_src/main/resources/static/images/Lauritz1.png",
                        "Tredje|test_Endnu med|_Sørensen_2022-04-13_src/main/resources/static/images/Lauritz1.png",
                        "Fjerde|test_Endnu med|i link_Tine_2022-04-13_src/main/resources/static/images/Lauritz1.png|",
                        "Billede test_Med flere billeder_Frederik_2022-04-13_" +
                                "src/main/resources/static/images/Lauritz1.png-src/main/resources/static/images/laust.jpg"},
                        delimiter = '_')
    void createAndReadTest(String title, String content, String author, LocalDate timeStamp, String imageLocations) {
        // Arrange
        LauPost expected = new LauPost(title, content, author, timeStamp,
                            new LinkedList<>(Arrays.asList(imageLocations.split("-"))));

        // Act
        LauPost actual = writer.write(expected);

        // Assert
        if (!(expected.getTitle().contains("|")||expected.getContent().contains("|")||
                expected.getAuthor().contains("|")||expected.getImageLocationsAsString().contains("|"))) {
            assertEquals(expected.getTitle(),actual.getTitle());
            assertEquals(expected.getAuthor(),actual.getContent());
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