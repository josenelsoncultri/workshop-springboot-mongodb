package com.josenelson.workshopmongo.services;

import com.josenelson.workshopmongo.domain.Post;
import com.josenelson.workshopmongo.domain.User;
import com.josenelson.workshopmongo.dto.UserDTO;
import com.josenelson.workshopmongo.repository.PostRepository;
import com.josenelson.workshopmongo.repository.UserRepository;
import com.josenelson.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return postRepository.findByTitleContainingIgnoreCase(text);
    }
}
