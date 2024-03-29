package com.josenelson.workshopmongo.services;

import com.josenelson.workshopmongo.domain.User;
import com.josenelson.workshopmongo.dto.UserDTO;
import com.josenelson.workshopmongo.repository.UserRepository;
import com.josenelson.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User user) {
        return userRepository.insert(user);
    }

    public void delete(String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj) {
        User user = findById(obj.getId());
        updateData(user, obj);
        return userRepository.save(user);
    }

    public void updateData(User user, User obj) {
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
