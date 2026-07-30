# Shared Database & Backend Development Guidelines

This document serves as the central guide for database connections, table mapping, and code integration across our 7 developer modules (`user`, `order`, `payment`, `promotion`, `reviews`, `branch`, `item`). 

---

## 1. How Database Connection Works
Spring Boot manages the database connection automatically using the settings in `src/main/resources/application.properties`. 

**No individual developer needs to write database connection or session code.**

### Local vs. Production Configurations
To avoid overwriting each other's credentials or editing the shared database during local development, we use `.env` files:
1. Create a `.env` file in your root folder (this file is ignored by Git):
   ```env
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/your_local_db
   SPRING_DATASOURCE_USERNAME=your_username
   SPRING_DATASOURCE_PASSWORD=your_password
   ```
2. Spring Boot reads this `.env` automatically via the `spring-dotenv` dependency, binding it to the properties file.

---

## 2. Standard 3-Layer JPA Pattern
Every developer must follow this architectural layout inside their package folder:

```
  [Controller] (Handles HTTP requests / JSON)
       │
       ▼
   [Service]   (Handles business logic)
       │
       ▼
  [Repository] (Handles database operations - JpaRepository)
       │
       ▼
   [Entity]    (Maps Java class directly to Database table)
```

1. **Entity (Model):** Annotate your class with `@Entity` and `@Table` (maps your class columns to database columns).
2. **Repository:** Create an interface extending `JpaRepository<YourEntity, Integer>` annotated with `@Repository`. *Do not write SQL queries; Spring Boot implements standard CRUD automatically.*
3. **Service:** Class annotated with `@Service` containing business logic. Inject your repository here.

---

## 3. Database Schema Naming Rules
* **Explicit Tables:** Always name tables explicitly using `@Table(name = "table_name")` in plural (e.g., `users`, `payments`). Avoid SQL keyword collisions.
* **Column Mapping:** Map database columns using `@Column(name = "column_name")` in `snake_case` matching the PostgreSQL columns.
* **ID Strategy:** Use `GenerationType.IDENTITY` for auto-incrementing primary keys:
  ```java
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  ```

---

## 4. How Primary Keys (PK) & Foreign Keys (FK) Link Up
To keep modules independent and prevent circular dependencies in Java, we link tables using **raw IDs (integers)** instead of full object maps (like `@ManyToOne`).

### Conceptual Database Layout
```
  [users Table] (User Module)
  +------------------+
  | user_id (PK)  <--+--- Primary Key: Unique identifier for a user.
  | username         |
  +------------------+
          │
          │ (Links to)
          ▼
  [orders Table] (Order Module)
  +------------------+
  | order_id (PK)    |
  | user_id (FK)  ---+--- Foreign Key: Stored as a simple int representing the user.
  | total_amount     |
  +------------------+
```

### Implementing in Java
1. **In the `user` folder:**
   ```java
   @Entity
   @Table(name = "users")
   public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "user_id")
       private int userId; 
       // ...
   }
   ```
2. **In the `order` folder:**
   ```java
   @Entity
   @Table(name = "orders")
   public class Order {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "order_id")
       private int orderId;

       @Column(name = "user_id") // Foreign Key referring to users.user_id
       private int userId; 
       // ...
   }
   ```

---

## 5. Non-Database Integration (Service to Service)
When you need data from another module, **never write database queries referencing their tables.** Instead, import and inject their Service class.

```java
package com.threefour.backend.order;

import com.threefour.backend.item.ItemService; // Import from other module
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ItemService itemService; // Inject the Item Service

    public void verifyAndCalculate(Order order) {
        // Fetch values via service method exports
        double price = itemService.getPriceById(order.getItemId());
    }
}
```

---

## 6. Action Blueprint: Exact Task List for Each Developer

Below are the exact requirements and files that each developer must build in their assigned folder:

### 👤 User Developer (`user` folder)
* **Entities:** `User.java` (Table: `users`, PK: `user_id`).
* **Repositories:** `UserRepository.java`.
* **Services:** `UserService.java`. **Must export these methods for other modules to use:**
  * `boolean existsById(int userId)` ➔ Checks if a user profile is valid.
  * `UserResponseDTO getUserById(int userId)` ➔ Returns user details (excluding raw passwords).
* **DTOs:** Create `UserRequestDTO.java` and `UserResponseDTO.java` to handle request validations and security.

### 🛒 Order Developer (`order` folder - You)
* **Entities:** `Order.java` (Table: `orders`, PK: `order_id`) and `OrderItem.java` (Table: `order_items`, PK: `order_item_id`).
* **Repositories:** `OrderRepository.java` and `OrderItemRepository.java`.
* **Services:** `OrderService.java`. **Must autowire these services to run orders:**
  * Autowire `UserService` to verify the order creator exists.
  * Autowire `BranchService` to verify the chosen store branch.
  * Autowire `ItemService` to verify pricing and deduct items from inventory.
  * Autowire `PromotionService` to validate coupon codes and apply discounts.
* **Controllers:** `OrderController.java` to handle checkout HTTP requests.

### 💳 Payment Developer (`payment` folder)
* **Entities:** `Payment.java` (Table: `payments`, PK: `payment_id`).
* **Enums:** `PaymentStatus.java` (`PENDING`, `COMPLETED`, `FAILED`).
* **Repositories:** `PaymentRepository.java`.
* **Services:** `PaymentService.java`. **Must export these methods:**
  * `Payment createPayment(double amount, String method)` ➔ Logs a new transaction.
  * `boolean isPaymentSuccessful(int paymentId)` ➔ Tells the Order module if an order is paid.

### 🏷️ Promotion Developer (`promotion` folder)
* **Entities:** `Promotion.java` / `Coupon.java` (Table: `promotions`, PK: `promotion_id`).
* **Repositories:** `PromotionRepository.java` (must support finding by code: `findByCouponCode(String code)`).
* **Services:** `PromotionService.java`. **Must export these methods:**
  * `double getDiscount(String couponCode, double orderSubtotal)` ➔ Validates a code and returns the discount amount.

### ✍️ Reviews Developer (`reviews` folder)
* **Entities:** `Review.java` (Table: `reviews`, PK: `review_id`). Stores foreign keys: `user_id` and `item_id`.
* **Repositories:** `ReviewRepository.java` (must support `List<Review> findByItemId(int itemId)`).
* **Services:** `ReviewService.java`. **Must autowire:**
  * Inject `UserService` to verify that the reviewer exists.
  * Inject `ItemService` to verify the item being reviewed exists.

### 🏢 Branch Developer (`branch` folder)
* **Entities:** `Branch.java` (Table: `branches`, PK: `branch_id`).
* **Repositories:** `BranchRepository.java`.
* **Services:** `BranchService.java`. **Must export these methods:**
  * `boolean existsById(int branchId)` ➔ Tells the Order module if a branch is valid.

### 🍔 Item Developer (`item` folder)
* **Entities:** `Item.java` / `Product.java` (Table: `items`, PK: `item_id`).
* **Repositories:** `ItemRepository.java`.
* **Services:** `ItemService.java`. **Must export these methods:**
  * `boolean existsById(int itemId)` ➔ Verifies an item exists.
  * `double getPriceById(int itemId)` ➔ Supplies the price to the Order module.
  * `boolean checkAndReduceStock(int itemId, int quantity)` ➔ Checks stock availability and decreases inventory count during checkout.
