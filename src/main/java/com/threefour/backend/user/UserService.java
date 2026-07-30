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

    public User updateUser(int id, User userDetails) {
        // Retrieve the existing user or throw an exception if not found
        User existingUser = getUserById(id);

        // Update common fields
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        existingUser.setRole(userDetails.getRole());
        existingUser.setActive(userDetails.isActive());

        // Only update password hash if a new one is provided
        if (userDetails.getPasswordHash() != null && !userDetails.getPasswordHash().isEmpty()) {
            existingUser.setPasswordHash(userDetails.getPasswordHash());
        }

        // Update subclass-specific fields based on the instances
        if (existingUser instanceof Customer && userDetails instanceof Customer) {
            ((Customer) existingUser).setAddress(((Customer) userDetails).getAddress());
        } else if (existingUser instanceof Staff && userDetails instanceof Staff) {
            ((Staff) existingUser).setEmpID(((Staff) userDetails).getEmpID());
            ((Staff) existingUser).setPosition(((Staff) userDetails).getPosition());
        }

        return userRepository.save(existingUser);
    }





}

