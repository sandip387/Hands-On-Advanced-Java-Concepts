package $05_jdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MovieRentalSystem extends JFrame implements ActionListener {
    private JTextField idField, titleField, genreField, languageField, lengthField;
    private JButton submitButton, clearButton;
    private Connection conn;

    public MovieRentalSystem() {
        setTitle("Movie Rental System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Create GUI Components
        add(new JLabel("Movie ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Genre:"));
        genreField = new JTextField();
        add(genreField);

        add(new JLabel("Language:"));
        languageField = new JTextField();
        add(languageField);

        add(new JLabel("Length (minutes):"));
        lengthField = new JTextField();
        add(lengthField);

        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        add(submitButton);
        add(clearButton);

        // Register Action Listeners
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        initializeDatabase();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeDatabase() {
        final String DB_URL = "jdbc:mysql://localhost:3307/mrs_db";
        final String USER = "root";
        final String PASS = "my-secret-pw";

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Database connection successful!");

            Statement stmt = conn.createStatement();

            String createTableSQL = "CREATE TABLE IF NOT EXISTS Movie (" +
                    "id INT PRIMARY KEY, " +
                    "title VARCHAR(100), " +
                    "genre VARCHAR(50), " +
                    "language VARCHAR(30), " +
                    "length INT)";

            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'Movie' is ready.");
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed! See console for details.",
                    "Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit if we can't connect to the DB
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            insertMovie();
        } else if (e.getSource() == clearButton) {
            clearFields();
        }
    }

    private void insertMovie() {
        String insertSQL = "INSERT INTO Movie (id, title, genre, language, length) VALUES (?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, Integer.parseInt(idField.getText()));
            pstmt.setString(2, titleField.getText());
            pstmt.setString(3, genreField.getText());
            pstmt.setString(4, languageField.getText());
            pstmt.setInt(5, Integer.parseInt(lengthField.getText()));

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Movie added successfully!");
                clearFields();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "SQL Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input: Please ensure ID and Length are valid numbers.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        genreField.setText("");
        languageField.setText("");
        lengthField.setText("");
        idField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieRentalSystem::new); // '::' is the Method Reference (super-concise shorthand for
                                                            // a lamba expression that does nothing but call a single
                                                            // existing method.) Here, it is read as "a reference to the
                                                            // new (constructor) of the MovieRentalSystem class."
    }
}
