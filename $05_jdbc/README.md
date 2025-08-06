# Module 5: Database Connectivity with JDBC

This module is a critical step in building real-world applications. We move beyond in-memory data and learn how to connect our Java programs to a relational database to persist data permanently. We use **JDBC (Java Database Connectivity)**, the standard API for this purpose.

---

### Prerequisites & Setup

The code in this module requires a running **MySQL database server**. The examples are configured to connect to instances running in Docker containers.

**1. Install Docker:**

- Make sure you have [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running on your machine.

**2. Run the MySQL Containers:**

- Open your terminal and run the following commands to create and start the necessary database containers.

**For the `JDBCDemo` program:**

```bash
docker run --name mysql-for-java -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -e MYSQL_DATABASE=csc409_db -d mysql:8.0

```

**For the `MovieRentalSystem` program:**

```bash
docker run --name mysql-for-mrs -p 3307:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -e MYSQL_DATABASE=mrs_db -d mysql:8.0
```

**3. Add the JDBC Driver to Your Project:**

- This project requires the `MySQL Connector/J` driver. You mush add the driver's .jar file to you IDE's classpath or "Referenced Libraries" for the code to compile and run.

---

### Code Examples in this Module

- **`JDBCDemo.java`**: A comprehensive, console-based program that demonstrates the entire lifecycle of database interaction:
  - Establishing a connection using `DriverManager`.
  - Creating a table (`CREATE`).
  - Inserting data using `PreparedStatement` to prevent SQL injection (`INSERT`).
  - Reading data using `ResultSet` (`SELECT`).
  - Updating existing records (`UPDATE`).
  - Deleting records (`DELETE`).
  - Managing atomic operations with **Transactions** (`commit`, `rollback`).
- **`MovieRentalSystem.java`**: This program combines the Swing GUI knowledge from Module 4 with the JDBC knowledge from this module. It provides a user-friendly form to add new movie records directly into a MySQL database.

---

### Key Concepts & Past Paper Q&A

**Q1: What is a `PreparedStatement` and why is it useful?**

A `PreparedStatement` is an object that represents a precompiled SQL statement. It has two major advantages over a plain `Statement`:

1.  **Security:** It is the primary defense against **SQL Injection attacks**. By using `?` placeholders and binding values with `setString()`, `setInt()`, etc., it ensures that user input is treated as literal data and not as executable SQL code.
2.  **Performance:** If you need to execute the same SQL query many times with different parameters, the `PreparedStatement` is compiled by the database only once, leading to faster execution on subsequent calls.

**Q2: What is a "Scrollable" `ResultSet`?**

By default, a `ResultSet` is a "forward-only" cursor. You can only move forward from the first row to the last using `rs.next()`. A **Scrollable `ResultSet`** is a more powerful type that allows you to move the cursor freely:

- `rs.next()`: Move forward (still available).
- `rs.previous()`: Move backward.
- `rs.first()`: Jump to the very first row.
- `rs.last()`: Jump to the last row.
- `rs.absolute(int row)`: Jump to a specific row number.

You create one by passing extra arguments to `createStatement()`, like this:
`Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);`

**Q3: What causes a `SQLException` and how is it handled?**

A `SQLException` is a "checked" exception that is thrown for any error that occurs during database interaction. Common causes include:

- Invalid database URL, username, or password.
- The database server is not running or is unreachable.
- Syntax errors in your SQL query.
- Violating a database constraint (e.g., trying to insert a duplicate primary key).

It is handled using a standard `try-catch` block. It's crucial to also have a `finally` block to ensure that database resources like the `Connection`, `Statement`, and `ResultSet` are always closed, even if an error occurs.

---

### Common Pitfalls & Things to Watch Out For

- **Forgetting the JDBC Driver:** The most common beginner error. If the appropriate JDBC driver (`.jar` file) is not added to the project's classpath, the `DriverManager` won't be able to find a suitable driver, and `DriverManager.getConnection()` will throw a `SQLException` stating "No suitable driver found".
- **Not Closing Resources:** Forgetting to close the `Connection`, `Statement`, and `ResultSet` in a `finally` block is a major resource leak that can exhaust the database's available connections and crash the application.
- **SQL Injection:** Building SQL queries by concatenating strings with user input (e.g., `String sql = "SELECT * FROM users WHERE name = '" + userName + "'";`) is extremely dangerous and opens your application to SQL injection attacks. **Always use `PreparedStatement` for queries that involve user input.**
