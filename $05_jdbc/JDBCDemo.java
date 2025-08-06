package $05_jdbc;

import java.sql.*;

public class JDBCDemo {
    // --- Database Connection Details ---
    // NOTE: In a real application, NEVER hardcode credentials like this. Use a
    // properties file or environment variables.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/csc409_db";
    private static final String USER = "root";
    private static final String PASS = "my-secret-pw";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // --- Establish the Connection ---
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection Successful!");

            // --- Create a Statment object ---
            stmt = conn.createStatement();

            // --- Execute SQL Queries ---
            // a) Create Table (DDL)
            System.out.println("\nCreating table 'students'...");
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(100), " +
                    "age INT)";

            stmt.executeUpdate(sqlCreateTable);
            System.out.println("Table 'students' created or already exists.");

            // b) Inset Data (DML) using PreparedStatement (Safer!)
            insertData(conn, "Alice", 20);
            insertData(conn, "Bob", 22);
            insertData(conn, "Charlie", 21);

            // c) Select DATA (DQL)
            selectData(stmt);

            // d) Update Data (DML)
            System.out.println("\nUpdating Bob's age to 23...");
            String sqlUpdate = "UPDATE students SET age = 23 WHERE name = 'Bob'";
            int rowsUpdated = stmt.executeUpdate(sqlUpdate);
            System.out.println(rowsUpdated + " row(s) updated.");
            selectData(stmt);

            // e) Delete Data (DML)
            System.out.println("\nDeleting Alice...");
            String sqlDelete = "DELETE FROM students WHERE name = 'Alice'";
            int rowsDeleted = stmt.executeUpdate(sqlDelete);
            System.out.println(rowsDeleted + " row(s) deleted.");
            selectData(stmt);

            // Demonstrate Transaction
            demonstrateTransaction(conn);
            selectData(stmt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the resources in reverse order they were opened.
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se1) {
                {
                    /* nothing we can do */}
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
        System.out.println("\nGoodbye!");
    }

    // A helper method to demonstrate PreparedStatement for safe inserts
    private static void insertData(Connection conn, String name, int age) throws SQLException {
        System.out.println("Inserting " + name + "...");
        // Use '?' as placeholders to prevent SQL injection attacks.
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("...done.");
    }

    // A helper method to display current data
    private static void selectData(Statement stmt) throws SQLException {
        System.out.println("\n--- Current Student Data ---");
        String sqlSelect = "SELECT id, name, age FROM students";
        ResultSet rs = stmt.executeQuery(sqlSelect);

        // Loop through the results
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.printf("ID: %d, Name: %s, Age: %d\n", id, name, age);
        }
        System.out.println("--------------------------");
        rs.close();
    }

    private static void demonstrateTransaction(Connection conn) throws SQLException {
        System.out.println("\n--- Demonstrating Transaction ---");
        Statement stmt = null;
        try {
            // Make the transaction Atomic.
            conn.setAutoCommit(false);

            stmt = conn.createStatement();

            System.out.println("Adding David (age 25) and Eve (age 24)...");
            stmt.executeUpdate("INSERT INTO students(name, age) VALUES ('David', 25)");
            stmt.executeUpdate("INSERT INTO students (name, age) VALUES ('Eve', 24)");

            // If all statements succeeded, commit the transaction
            System.out.println("Both statements successful. Commiting transaction.");
            conn.commit();
        } catch (SQLException e) {
            // If any statement failed, roll back the entire transaction
            System.out.println("An error occured. Rolling back transsaction.");
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Always clean up and reset auto-commit
            if (stmt != null)
                stmt.close();
            conn.setAutoCommit(true);
        }
    }
}
