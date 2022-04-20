package laustrup.de_labre_lauer.controllers;

import laustrup.de_labre_lauer.models.LauPost;
import laustrup.de_labre_lauer.services.Reader;
import laustrup.de_labre_lauer.services.Writer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PostConstroller {

    private Reader reader = new Reader();
    private Writer writer = new Writer();

    @GetMapping(value = "/laupost", consumes = "application/json")
    public ResponseEntity<LauPost> getPost(@RequestBody LauPost post) {
        return new ResponseEntity<>(reader.read(post), HttpStatus.OK);
    }

    @GetMapping("/lauposts")
    public ResponseEntity<List<LauPost>> getPosts() {
        return new ResponseEntity<>(reader.readAll(),HttpStatus.OK);
    }

    @PostMapping(value = "/laupost", consumes = "application/json")
    public ResponseEntity<LauPost> createPost(@RequestBody LauPost post) {
        return new ResponseEntity<>(writer.write(post),HttpStatus.CREATED);
    }

}
