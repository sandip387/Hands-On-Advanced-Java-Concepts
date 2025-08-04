package io_and_swing_basics;

import java.io.Serializable;

// This 'implements Serializable' is a "marker interface". It has no methods but it's our way of telling  the JVM that it's okay to convert objects of this class into a stream of bytes to be saved to a file.
public class Employee implements Serializable {
    private String name;
    private int id;
    private double salary;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    // A helpful toString() method to make printing the object's details easy.
    @Override
    public String toString() {
        return "Employee{name='" + name + "', id=" + id + ", salary=" + salary + "}";
    }
}
