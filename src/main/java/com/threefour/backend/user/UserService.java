package com.threefour.backend.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static String addnumbers(int a , int b){
        return "The sum is "+ (a+b);
    }


    public User saveUser(User user) {
        // Directly pass the incoming object to the repository to be persisted
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        // Fetches all columns for the specific primary key
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }




}

