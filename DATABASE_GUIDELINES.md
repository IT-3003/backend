# Database Guidelines for Shared Development

This document outlines the guidelines for database connection, schema mapping, and coordination across the 7 developer modules (`user`, `order`, `payment`, `promotion`, `reviews`, `branch`, `item`).

---

## 1. Centralized Connection & Configuration
Spring Boot manages the database connection automatically using the settings in `src/main/resources/application.properties`. **Individual module folders do not need to implement custom JDBC connection logic.**

### Local vs. Production Configurations
To avoid overwriting each other's database configurations or accidentally modifying the production database during local testing, we use the `spring-dotenv` dependency.

1. Create a `.env` file at the root of the project (this is ignored in git):
   ```env
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/your_local_db
   SPRING_DATASOURCE_USERNAME=your_username
   SPRING_DATASOURCE_PASSWORD=your_password
   ```
2. In `src/main/resources/application.properties`, connection credentials are bound to these environment variables:
   ```properties
   spring.datasource.url=${SPRING_DATASOURCE_URL}
   spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
   spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
   ```

---

## 2. Standard 3-Layer JPA Pattern
Every module should strictly follow the standard three-layer architecture:

1. **Entity (Model) Layer:** Annotated with `@Entity` and `@Table` (e.g., `Order.java`).
2. **Repository Layer:** An interface extending `JpaRepository<EntityName, IdType>` annotated with `@Repository` (e.g., `OrderRepository.java`).
3. **Service Layer:** Business logic class annotated with `@Service` injecting repositories via dependency injection.

---

## 3. Database Schema & Table Naming Rules
Since all 7 modules will share a single database, coordinate naming to avoid conflicts:

* **Explicit Table Names:** Always use `@Table(name = "table_name")` explicitly. Do not rely on Hibernate's implicit naming. Use plural or specific names (e.g. `users` instead of `user` to avoid SQL keyword collisions).
* **Column Mapping:** Map columns explicitly using `@Column(name = "column_name")` in `snake_case` style.
* **ID Strategy:** Agree on standard identifier types (e.g., auto-incrementing integer/bigint):
  ```java
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  ```

---

## 4. Handling Inter-Module Relationships (Foreign Keys)
Because folders are split among different developers, references between modules can be handled in two ways:

* **Loose Coupling (Highly Recommended for Team Independence):**
  Instead of mapping complete entities (e.g., `@ManyToOne User user`), store only the raw ID (`private int userId`). This prevents package circular dependency issues and lets you develop your module independently.
* **Tight Coupling (JPA Relationships):**
  If you must use full JPA annotations (e.g., `@OneToMany`, `@ManyToOne`), coordinate directly with the owner of that module to ensure their entity class is ready and stable.

---

## 5. Schema Updates (`ddl-auto`)
* The configuration currently uses `spring.jpa.hibernate.ddl-auto=update`.
* **Rule:** Before pushing any code, run the application locally to ensure your new database entities are correctly mapped and do not alter/break tables created by other team members.

---

## 6. Action Checklist for Each Developer
To get the database connection up and running for your respective folder (e.g., `user`, `payment`, `promotion`), perform the following steps:

1. **Set Up Local Environment Variable:**
   * Create a file named `.env` in the root project directory (same level as `pom.xml`).
   * Add your local database connection details (refer to Section 1). This ensures you connect to your own database during development instead of clashing with others.

2. **Define Your Entity (`@Entity`):**
   * Create your model class in your folder (e.g., `com.threefour.backend.payment.Payment`).
   * Annotate it with `@Entity` and `@Table(name = "<your_table_name>")`.
   * Add fields with standard JPA annotations (`@Id`, `@GeneratedValue`, `@Column`).

3. **Create Your Repository Interface:**
   * Create an interface in your folder (e.g., `PaymentRepository`).
   * Annotate it with `@Repository` and extend `JpaRepository<YourEntity, Integer>`.
   * **Do not write SQL queries.** Standard CRUD operations (save, find, delete, etc.) are automatically handled by Spring Boot.

4. **Inject and Use in Service Layer:**
   * In your Service class, annotate it with `@Service`.
   * Inject your repository using `@Autowired` or constructor injection.
   * Call repository methods (e.g., `paymentRepository.save(payment)`) to execute database operations.

5. **Coordinate Shared Keys:**
   * If your entity references another developer's entity (e.g., `Order` referencing `User`), use the loose coupling approach by saving the ID (`userId`) as a primitive integer. Coordinate the column name mapping (e.g., `user_id`) to ensure they match up when joining tables.

---

## 7. Folder-Specific Blueprint (The 7 Modules)
Here is the breakdown of what each developer should implement in their respective package folder:

### 1. User (`com.threefour.backend.user`)
* **Entity Name:** `User`
* **Table Name:** `users` (avoid using the SQL keyword `user`)
* **Primary Key:** `user_id` (Integer / Serial)
* **Key Fields:** `username`, `email`, `password`, `role`, `phoneNumber`
* **Database Role:** This is a core lookup entity. Other modules (like `order` and `reviews`) will store `userId` as a foreign key reference.

### 2. Order (`com.threefour.backend.order`)
* **Entity Names:** `Order` and `OrderItem` (One-to-Many relationship)
* **Table Names:** `orders` and `order_items`
* **Primary Keys:** `order_id` and `order_item_id`
* **Integration References:** Stores `userId` (from User), `branchId` (from Branch), and `paymentId` (from Payment) as standard integer fields.
* **Key Fields:** `totalAmount`, `status` (Enum), `deliveryAddress`, `orderDate`

### 3. Payment (`com.threefour.backend.payment`)
* **Entity Name:** `Payment`
* **Table Name:** `payments`
* **Primary Key:** `payment_id`
* **Key Fields:** `paymentMethod` (e.g., Card, Cash), `amount`, `status` (e.g., Pending, Completed), `transactionId`
* **Database Role:** Once a payment is created, its `paymentId` is shared back with the `order` module.

### 4. Promotion (`com.threefour.backend.promotion`)
* **Entity Name:** `Promotion` or `Coupon`
* **Table Name:** `promotions` / `coupons`
* **Primary Key:** `promotion_id`
* **Key Fields:** `couponCode` (String, unique index), `discountValue`, `discountType` (e.g., percentage, flat), `expiryDate`
* **Database Role:** The `order` module checks this table using the `couponCode` to calculate discounts.

### 5. Reviews (`com.threefour.backend.reviews`)
* **Entity Name:** `Review`
* **Table Name:** `reviews`
* **Primary Key:** `review_id`
* **Key Fields:** `rating` (1–5), `comment`, `createdDate`
* **Integration References:** Stores `userId` (from User) and `itemId` (from Item) or `orderId` (from Order) as foreign keys to track what is being reviewed and by whom.

### 6. Branch (`com.threefour.backend.branch`)
* **Entity Name:** `Branch`
* **Table Name:** `branches`
* **Primary Key:** `branch_id`
* **Key Fields:** `branchName`, `location`, `contactNumber`
* **Database Role:** Used by the `order` module to assign where the order was placed or will be fulfilled.

### 7. Item (`com.threefour.backend.item`)
* **Entity Name:** `Item` or `Product`
* **Table Name:** `items`
* **Primary Key:** `item_id`
* **Key Fields:** `name`, `price`, `description`, `stockQuantity`, `category`
* **Database Role:** Referenced inside `order_items` (under the `order` module) via `itemId` to record which items are part of an order.


