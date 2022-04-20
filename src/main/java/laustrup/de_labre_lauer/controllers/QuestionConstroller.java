package laustrup.de_labre_lauer.controllers;

import laustrup.de_labre_lauer.models.Question;
import laustrup.de_labre_lauer.services.Reader;
import laustrup.de_labre_lauer.services.Writer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class QuestionConstroller {

    private Reader reader = new Reader();
    private Writer writer = new Writer();

    @GetMapping(value = "/question", consumes = "application/json")
    public ResponseEntity<Question> getQuestion(@RequestBody Question post) {
        return new ResponseEntity<>(reader.read(post), HttpStatus.OK);
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestions() {return new ResponseEntity<>(reader.readAll(),HttpStatus.OK);}

    @PostMapping(value = "/question", consumes = "application/json")
    public ResponseEntity<Question> createQuestion(@RequestBody Question post) {
        return new ResponseEntity<>(writer.write(post),HttpStatus.CREATED);
    }

}
