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


    public User saveThinula(User user) {
        // Directly pass the incoming object to the repository to be persisted
        return userRepository.save(user);
    }
}

