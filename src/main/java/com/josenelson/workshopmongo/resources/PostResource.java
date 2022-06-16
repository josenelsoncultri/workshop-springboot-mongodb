package com.josenelson.workshopmongo.resources;

import com.josenelson.workshopmongo.domain.Post;
import com.josenelson.workshopmongo.resources.util.URL;
import com.josenelson.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RequestMapping(value="/posts")
@RestController
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value="/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value="/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitle(text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value="/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue="") String text,
            @RequestParam(value="minDate", defaultValue="") String minDate,
            @RequestParam(value="maxDate", defaultValue="") String maxDate
        ) {

        text = URL.decodeParam(text);

        Instant min = URL.convertDate(minDate, new Date(0).toInstant());
        Instant max = URL.convertDate(maxDate, new Date().toInstant());
        List<Post> list = postService.fullSearch(text, min, max);

        return ResponseEntity.ok().body(list);
    }
}
