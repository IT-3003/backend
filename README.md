# Online Supermarket Shopping Management System (Backend)

Welcome to the backend repository of the **Online Supermarket Shopping Management System**, an enterprise-grade web application backend developed with Spring Boot, Java, and PostgreSQL.

This backend serves as the core orchestration and service engine for the platform, offering a modular, highly decoupled micro-modular architecture built around standard design patterns.

---

## Team Members & Contributors

- **Thinula Harischandra** (17478)
- **Janindu Hasaranga** (17472)
- **Ruchitha Vithana** (17450)
- **Anshumala Amarawansa** (17452)
- **Dinuvi Laknara** (17541)
- **Himandi Ranawaka** (17514)
- **Prabhavi Jayawardana** (17485)

---

## Technology Stack

* **Language & Runtime:** Java 17
* **Framework:** Spring Boot 3.4.1 (with Starters for Web, Data JPA, Validation)
* **Database Driver & Storage:** PostgreSQL / Supabase Postgres
* **Configuration Management:** `spring-dotenv` (supports seamless environment-based profile config via `.env`)
* **Payment Gateway:** Stripe Java SDK (`v28.1.0`)
* **Build System:** Apache Maven
* **Cloud Deployment:** Microsoft Azure Web App (integrated via Maven plugin)

---

## Architectural Design Patterns

This project adheres to a strict, standardized architecture designed to support a collaborative multi-developer environment:

### 1. Standard 3-Layer JPA Pattern
Each module encapsulates its responsibilities into a classic three-tier layout:
* **Controller Layer:** Exposes REST endpoints, validates HTTP requests, and formats JSON outputs.
* **Service Layer:** Houses the core business logic. Facilitates transactional flows and cross-module communications.
* **Repository Layer:** Interacts with PostgreSQL using Spring Data JPA (`JpaRepository`) for automated CRUD operations.
* **Entity Layer:** Maps Java models directly to PostgreSQL database tables.

### 2. Decoupled Flat Identity Relationships (No `@ManyToOne` / `@OneToMany` dependencies)
To ensure that each developer's module can be worked on, compiled, and deployed independently without circular dependencies or complex entity graph-loading issues:
* Tables are linked using **raw integer IDs** (e.g. `userId`, `branchId`, `itemId`) rather than full Hibernate object associations.
* Referential integrity is managed via business-logic level checks inside services.

### 3. Service-to-Service Orchestration
When one module requires data or validation from another module, it **never** queries the other module's database tables directly. Instead, it injects the target module's **Service class** (e.g., `OrderService` autowires and calls `UserService`, `BranchService`, `ItemService`, and `PromotionService`).

---

## Repository & Folder Structure

The source code under `src/main/java/com/threefour/backend` is organized into the following cohesive modules:

```
src/main/java/com/threefour/backend/
├── BackendApplication.java             # Main Application Entry Point
├── branch/                             # Supermarket Branch Management
│   ├── Branch.java                     # Entity (Table: branches)
│   ├── BranchController.java           # API endpoints under /api/branch
│   ├── BranchRepository.java           # JPA Database Access
│   └── BranchService.java              # Branch validation and lifecycle logic
├── exception/                          # Global HTTP Exception Handling
│   └── GlobalExceptionHandler.java     # Catch-all advice for REST resource exceptions
├── item/                               # Item / Product Catalogue & Stock Management
│   ├── Item.java                       # Entity (Table: items)
│   ├── ItemController.java             # API endpoints under /api/item
│   ├── ItemRepository.java             # JPA Database Access
│   └── ItemService.java                # Price verification, stock deductions, catalogue updates
├── order/                              # Order Processing & Shopping Cart Checkout
│   ├── Order.java                      # Entity (Table: orders)
│   ├── OrderItem.java                  # Entity (Table: order_items)
│   ├── OrderController.java            # API endpoints under /api/order
│   ├── OrderRepository.java            # JPA Database Access for orders
│   ├── OrderService.java               # Complex checkout orchestrator (User, Item, Promos, Branch validation)
│   └── OrderStatus.java                # Order state Enum
├── payment/                            # Payment Processing & Gateway Integrations
│   ├── Payment.java                    # Entity (Table: payments)
│   ├── PaymentController.java          # API endpoints under /api/payment & /api/payments
│   ├── PaymentRepository.java          # JPA Database Access
│   ├── PaymentService.java             # Stripe payment intent/session logger
│   └── StripeConfig.java               # Stripe API configurations & credentials loader
├── promotion/                          # Discount Coupons & Campaign Rules
│   ├── Promotion.java                  # Entity (Table: promotions)
│   ├── PromotionController.java        # API endpoints under /api/promotion
│   ├── PromotionRepository.java        # JPA coupon finder queries
│   └── PromotionService.java           # Coupon validation and subtotal deduction calculation
├── reviews/                            # Customer Reviews & Rating Engine
│   ├── Reviews.java                    # Entity (Table: reviews)
│   ├── ReviewsController.java          # API endpoints under /api/reviews
│   ├── ReviewsRepository.java          # JPA review queries
│   └── ReviewsService.java             # Review submission validations (checks User & Item existences)
└── user/                               # Identity & Access Management (User, Admin, Staff, Customer)
    ├── User.java                       # Polymorphic base entity (Table: users)
    ├── Admin.java / Customer.java      # Concrete user roles
    ├── Staff.java / Role.java          # Staff entity and role Enums
    ├── UserController.java             # API endpoints under /api/user
    ├── UserRepository.java             # JPA Database Access for profiles
    └── UserService.java                # Authentication, DTO mappings, and existence assertions
```

---

## Key Features

1. **Robust Authentication & Roles:** Separates system permissions using polymorphic hierarchies (Admin, Staff, Customer).
2. **Stripe API Checkout Sessions:** Integration with Stripe Java API to dynamically generate secure checkout links.
3. **Optimistic Stock Allocation:** Automatically validates item availability and decrements item inventories atomically on checkout.
4. **Validation Pipeline:** Standardized HTTP payload assertions using `jakarta.validation` (`@Valid`, `@Min`, `@NotBlank`, etc.) to prevent invalid data ingestion.
5. **Centralized Error Boundary:** System-wide exception handling returning standard, human-readable error messages for validation violations, missing resources, and payment failures.

---

## Getting Started

### Prerequisites
* Java Development Kit (JDK) 17 or higher
* Apache Maven (or use the included wrapper `mvnw`)
* PostgreSQL Instance (Local or Remote Cloud like Supabase)

### Setup & Local Run

1. **Clone the repository** and navigate to the project directory:
   ```bash
   git clone https://github.com/IT-3003/backend.git
   cd backend
   ```

2. **Configure Environment Variables:**
   Create a `.env` file in the root directory to store database and payment secrets (this is git-ignored):
   ```env
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/your_database_name
   SPRING_DATASOURCE_USERNAME=your_db_username
   SPRING_DATASOURCE_PASSWORD=your_db_password
   STRIPE_API_KEY=your_stripe_secret_key
   STRIPE_SUCCESS_URL=http://localhost:5173/profile
   STRIPE_CANCEL_URL=http://localhost:5173/cart
   ```

3. **Build the Application:**
   ```bash
   ./mvnw clean install
   ```

4. **Run the Application:**
   * **Option A: Using Maven Command Line**
     ```bash
     ./mvnw spring-boot:run
     ```
   * **Option B: Using IntelliJ IDEA**
     * Open the project in IntelliJ IDEA.
     * Ensure Maven dependencies are loaded.
     * Run the application by executing the `main` method in `BackendApplication.java`.
     
   The backend service will run locally at `http://localhost:8080`.

---

## Deployment

The application is configured to deploy to **Azure Web App** service via the `azure-webapp-maven-plugin`.

To deploy:
1. Ensure your Azure credentials are configured locally:
   ```bash
   az login
   ```
2. Execute the deploy command:
   ```bash
   ./mvnw azure-webapp:deploy
   ```
   *Target: App Name `it3003-springboot-backend` / Resource Group `rg-it3003-project` / Region `eastasia`.*
