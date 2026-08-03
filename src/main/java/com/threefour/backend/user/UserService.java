package com.threefour.backend.user;

import org.springframework.stereotype.Service;

import com.threefour.backend.order.OrderRepository;
import com.threefour.backend.order.OrderItemRepository;
import com.threefour.backend.payment.PaymentRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final OrderItemRepository orderItemRepository;

    public UserService(UserRepository userRepository, OrderRepository orderRepository,
                       PaymentRepository paymentRepository, OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
        this.orderItemRepository = orderItemRepository;
    }

    // Convert User Entity to UserResponse DTO
    public UserResponse convertToResponseDTO(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole());
        response.setActive(user.isActive());

        if (user instanceof Customer) {
            response.setType("customer");
            response.setAddress(((Customer) user).getAddress());
        } else if (user instanceof Staff) {
            response.setType("staff");
            response.setEmpID(((Staff) user).getEmpID());
            response.setPosition(((Staff) user).getPosition());
        } else if (user instanceof Admin) {
            response.setType("admin");
        }
        return response;
    }

    // Save a new User
    public User createUser(UserRequest request) {
        User user;

        // Polymorphically instantiate based on type
        if ("customer".equalsIgnoreCase(request.getType())) {
            Customer customer = new Customer();
            customer.setAddress(request.getAddress());
            user = customer;
        } else if ("staff".equalsIgnoreCase(request.getType())) {
            Staff staff = new Staff();
            staff.setEmpID(request.getEmpID());
            staff.setPosition(request.getPosition());
            user = staff;
        } else {
            user = new Admin();
        }

        // Map basic fields
        user.setId(request.getId() == 0 ? (int)(Math.random() * 900000 + 100000) : request.getId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        user.setActive(request.isActive());

        // Hash password before saving (e.g. using BCrypt, or plain text here for now)
        if (request.getPassword() != null) {
            user.setPasswordHash(request.getPassword());
        }

        return userRepository.save(user);
    }

    // Update existing User
    public User updateUser(int id, UserRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update common fields
        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());
        existingUser.setPhoneNumber(request.getPhoneNumber());
        existingUser.setRole(request.getRole());
        existingUser.setActive(request.isActive());

        // Update password only if a new one is sent
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            existingUser.setPasswordHash(request.getPassword());
        }

        // Update subclass specific fields
        if (existingUser instanceof Customer) {
            ((Customer) existingUser).setAddress(request.getAddress());
        } else if (existingUser instanceof Staff) {
            ((Staff) existingUser).setEmpID(request.getEmpID());
            ((Staff) existingUser).setPosition(request.getPosition());
        }

        return userRepository.save(existingUser);
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        orderRepository.clearPaymentReferencesByUserId(id);
        paymentRepository.deleteByUser_Id(id);
        orderItemRepository.deleteByOrder_User_Id(id);
        orderRepository.deleteByUser_Id(id);
        userRepository.deleteById(id);
    }

    public java.util.List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    public UserResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials: User not found"));

        // Simple plain-text password comparison
        if (user.getPasswordHash() == null || !user.getPasswordHash().equals(password)) {
            throw new RuntimeException("Invalid credentials: Passwords do not match");
        }

        return convertToResponseDTO(user);
    }
}