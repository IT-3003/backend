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
