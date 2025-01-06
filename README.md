"# paf_ws01" 
---

# **Northwind API**

## **Overview**
This project is a REST API for interacting with the `northwind` database, a classic dataset used to demonstrate relational database concepts. The API allows users to query customer data with features such as pagination and retrieval by customer ID.

---

## **Features**
- **GET `/api/customers`**: Retrieve a paginated list of customers.
- **GET `/api/customer/{id}`**: Retrieve customer details by their ID.

---

## **Setup Instructions**

### **1. Database Configuration**
1. Import the provided SQL files into MySQL:
   - **Schema file**: Sets up the `northwind` database and tables.
   - **Data file**: Populates the tables with sample data.
2. Create a new database user with access to the `northwind` database:
   ```sql
   CREATE USER 'northwind_user'@'localhost' IDENTIFIED BY 'secure_password';
   GRANT ALL PRIVILEGES ON northwind.* TO 'northwind_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

### **2. Application Configuration**
1. Update `application.properties` with the new user's credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/northwind
   spring.datasource.username=northwind_user
   spring.datasource.password=secure_password
   ```

### **3. Running the Application**
Start the Spring Boot application using:
```bash
mvn spring-boot:run
```

---

## **Key Concepts**

### **1. Spring Boot with JDBC**
- Used `JdbcTemplate` to interact with the database efficiently.
- Implemented queries with pagination (`LIMIT` and `OFFSET`) for retrieving customer data.

### **2. `SqlRowSet` for Result Mapping**
- Used `SqlRowSet` to fetch query results and map them to Java objects.
- Added a helper method (`toCustomer`) in the `Customer` model for converting `SqlRowSet` rows into `Customer` objects.

### **3. Optional Handling**
- Leveraged `Optional` in the repository to handle the absence of a customer gracefully.
- Used explicit checks in the service layer to throw `ResponseStatusException` when a customer is not found.

### **4. Layered Architecture**
- **Model**: Represents the structure of database tables (e.g., `Customer`).
- **Repository**: Handles database queries (e.g., `findAll`, `findById`).
- **Service**: Contains business logic for processing data.
- **Controller**: Exposes REST endpoints for API interaction.

### **5. RESTful API Design**
- Exposed clean, resource-based endpoints for customer operations:
  - Pagination support for fetching large datasets efficiently.
  - Detailed error handling with HTTP status codes (e.g., `404 Not Found`).

---

## **Testing the API**

### **Endpoints**
1.http://localhost:8080/api/customers?offset=0&limit=5
2. Retrieve a specific customer by ID:
http://localhost:8080/api/customer/1