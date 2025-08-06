package $03_io_and_swing_basics;

import java.io.*;

public class FileIODemo {
    public static void main(String[] args) {
        // Part 1: Writing objects to a file
        ObjectOutputStream oos = null;// Declare outside the try block
        try {
            // Initialize inside the try block
            oos = new ObjectOutputStream(new FileOutputStream("employees.dat"));

            Employee emp1 = new Employee("Sandip Shrestha", 101, 50000);
            Employee emp2 = new Employee("Smriti Thapa", 102, 55000);

            oos.writeObject(emp1);
            oos.writeObject(emp2);

            // To write them all at once
            // List<Employee> employeeList = new ArrayList<>();
            // employeeList.add(emp1);
            // employeeList.add(emp2);
            // oos.writeObject(employeeList); // Write the whole list as one object

            System.out.println("Employee objects written to employees.dat successfully.");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } finally { // The 'finally' block is crucial for closing the resources whether an exception
                    // occured or not.
            try {
                if (oos != null) {
                    oos.close();// Closing the stream
                }
            } catch (IOException e) {
                // Even the .close() method can throw an exception, so it needs a catch
                System.err.println("Error closing the output stream: " + e.getMessage());
            }
        }

        // Part 2: Reading objects from file
        System.out.println("\n--- Reading Objects back ---");
        ObjectInputStream ois = null; // Declare outside
        try {
            ois = new ObjectInputStream(new FileInputStream("employees.dat"));

            Employee empRead1 = (Employee) ois.readObject();
            Employee empRead2 = (Employee) ois.readObject();

            // To read objects all at once
            // List<Employee> readList = (List<Employee>) ois.readObject(); // Read the whole list back

            System.out.println("Read Object 1: " + empRead1);
            System.out.println("Read Object 2: " + empRead2);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing the input stream: " + e.getMessage());
            }
        }
    }
}
