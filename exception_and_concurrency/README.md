# Module 2: Exception Handling & Concurrency

This module dives into two critical aspects of building robust Java applications: managing errors gracefully with exceptions, and handling multiple tasks at once with threads (concurrency).

---

### Code Examples in this Module

- **`VotingSystem.java` & `InvalidAgeException.java`**: A practical example of creating a custom "checked" exception to enforce a business rule (in this case, a minimum voting age).
- **`SynchronizationDemo.java` (with `PrintTable` & `MyThread`)**: This is a classic demonstration of a "race condition." It shows how the `synchronized` keyword prevents two threads from interfering with each other when using a shared resource.
- **`ThreadPriorityDemo.java` (with `PriorityThread`)**: Shows how to give "hints" to the Java scheduler that one thread might be more important than another, often affecting which one runs first.

---

### Key Concepts & Common Questions

**Q1: What's the real difference between `throw` and `throws`?**

This is a common interview question.

- `throws` is used in a method's signature (e.g., `void myMethod() throws IOException`). It's a **warning** to whatever code calls this method, saying, "Hey, be prepared, I might throw this type of exception, so you better handle it with a `try-catch` block."
- `throw` is the action keyword used _inside_ a method to actually **create and launch** a new exception object (e.g., `throw new IOException("File not found");`).

**Q2: What are the main ways to create a thread? Which one is better?**

There are two primary ways:

1.  **Extending the `Thread` class:** Your class becomes a thread itself. This is simple but less flexible, because Java does not allow extending more than one class.
2.  **Implementing the `Runnable` interface:** Your class defines a "task" that can be run by a thread. You then pass an instance of your class to a new `Thread` object. This is **generally preferred** because it's more flexible; your class can still extend another class if needed.

**Q3: Why do we even need to synchronize threads?**

When multiple threads access and modify the same shared data (like a variable or an object), you can get a "race condition." Imagine two threads trying to withdraw money from the same bank account at the exact same millisecond. Without synchronization, they might both read the balance _before_ the other has a chance to update it, potentially allowing both withdrawals to succeed even if there isn't enough money. Synchronization ensures that one operation finishes completely before the next one starts, preventing data corruption.

**Q4: When is the `finally` block mandatory?**

Technically, a `finally` block is never syntactically _mandatory_ (you can have a `try-catch` without one). However, it becomes functionally mandatory when you have **critical cleanup code** that must run no matter what—whether an exception was thrown or not. The classic use case is closing resources like files, database connections, or network sockets to prevent resource leaks.

```java
// Example: Ensuring a resource is always closed
Connection dbConnection = null;
try {
    dbConnection = connectToDatabase();
    // ... do work with the database ...
} catch (SQLException e) {
    // ... handle the error ...
} finally {
    if (dbConnection != null) {
        dbConnection.close(); // This line MUST run to prevent a leak.
    }
}
```

_Note: The modern "try-with-resources" statement automates this and is now the preferred way to handle resource cleanup._

---

### Common Pitfalls & Things to Watch Out For

- **Forgetting `InterruptedException`:** Calling `Thread.sleep()` requires you to handle a potential `InterruptedException`. It's a "checked" exception, so the compiler will force you to use a `try-catch` block.
- **Confusing `.start()` and `.run()`:** This is a classic beginner mistake.
  - `myThread.start()` tells the JVM to create a new path of execution and run the code in the `run()` method concurrently. **This is almost always what you want.**
  - `myThread.run()` simply executes the code in the `run()` method on the _current_ main thread, just like any other normal method call. No new thread is created.
- **Assuming Priorities are a Guarantee:** Setting a thread's priority to `MAX_PRIORITY` does not guarantee it will always run before a `MIN_PRIORITY` thread. It's just a strong suggestion to the OS scheduler, whose behavior can vary.
