# Module 3: I/O Streams & Swing GUI Basics

This module marks a big step, moving from console-only applications to programs that can interact with the file system and display a graphical user interface (GUI). We cover reading/writing different types of data and build our first window with the Swing toolkit.

---

### Code Examples in the Module

- **`FileIODemo.java` (with `Employee.java`)**: Demonstrates how to save entire Java objects to a binary file (`.dat`) using `ObjectOutputStream` and then read them back with `ObjectInputStream`.

- **`VowelConsonantSeparator.java`**: A practical I/O example that reads user input from the console and sorts the characters into two separate human-readable text files (`.txt`).
- **`SwingLayoutDemo.java`**: Our first GUI program! This shows how to create a basic window (`JFrame`) and organize components inside it using `BorderLayout` and a nested `JPanel` with `FlowLayout`.

---

### Key Concepts & Common Questions

**Q1: What's the difference between an `ObjectInputStream` and a `BufferedReader`?**

This is about **Binary vs. Text**.

- `ObjectInputStream` is for reading **binary data** created by an `ObjectOutputStream`. Its job is to reconstruct Java objects from a stream of bytes. You can't read this data in a text editor.
- `BufferedReader` is for reading **human-readable text**. It's highly efficient for reading character data line-by-line from a file or the console.

**Q2: Compare AWT with Swing.**

- **AWT (Abstract Window Toolkit):** This is the original, older GUI toolkit. Its components are "heavyweight," meaning they rely on the underlying operating system's native UI components. This makes AWT platform-dependent and gives it a more limited set of controls.
- **Swing:** This is the modern, more advanced toolkit. Its components are "lightweight," meaning they are written entirely in Java and painted onto the screen. This makes Swing platform-independent, gives it a much richer set of components, and allows for custom "look-and-feel" themes.

**Q3: What is the task of a Layout Manager?**

A Layout Manager's job is to automatically control the **size and position** of components inside a container (like a `JFrame` or `JPanel`). Instead of setting hardcoded pixel coordinates, you add components to the container, and the layout manager arranges them according to its specific rules. This allows GUIs to resize gracefully and look correct on different screen resolutions.

**Q4: Why did we have to use `implements Serializable` in the `Employee` class?**

`Serializable` is a **marker interface**. It has no methods to implement. Its only purpose is to "mark" a class, giving the JVM permission to convert its objects into a stream of bytes. This is a security and design feature—Java forces you, the programmer, to explicitly decide which objects are safe to be serialized (saved to a file or sent over a network).

**Q5: Describe `FlowLayout` and `BorderLayout`.**

- **`FlowLayout`:** This is the simplest layout manager. It arranges components in a row, left-to-right. If there isn't enough space in one row, it "flows" to the next, much like words in a paragraph. It's the default layout for `JPanel`.
- **`BorderLayout`:** This manager divides the container into five distinct regions: `NORTH`, `SOUTH`, `EAST`, `WEST`, and `CENTER`. Each region can hold only one component (or one `JPanel` that contains multiple components). It's the default layout for `JFrame`.

**Q6: What's the difference between `GridLayout` and `GridBagLayout`?**

This is about **Simplicity vs. Power**.

- `GridLayout` is simple and rigid. It creates a grid of equally-sized cells. It's great for things like a calculator keypad but offers little flexibility.
- `GridBagLayout` is the most powerful and flexible Swing layout manager. It also uses a grid, but components can be different sizes and can span multiple rows and columns. This flexibility comes at the cost of being much more complex to configure.

**Q7: What's the deal with `SwingUtilities.invokeLater()`?**

This is a critical rule for stable Swing applications. All code that creates or modifies Swing UI components should be run on a special thread called the **Event Dispatch Thread (EDT)**. `SwingUtilities.invokeLater()` is a helper method that takes your UI code (usually in a `Runnable`) and schedules it to run safely on the EDT. This prevents graphical glitches and freezing that can occur when multiple threads try to update the UI at once.

---

### Common Pitfalls & Things to Watch Out For

- **Forgetting `implements Serializable`:** If you try to write an object to an `ObjectOutputStream` and its class doesn't implement the `Serializable` marker interface, your program will crash with a `NotSerializableException`.

- **Not Closing Streams Properly:** In manual resource management, failing to close a stream in a `finally` block is a serious error called a **resource leak**. The file or network connection remains open, consuming system resources.

- **Reading `Object` streams out of order:** When using `ObjectInputStream`, you must read objects back in the exact same order and type as they were written.

- **Forgetting `setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)`:** A very common mistake for beginners. If you don't set this on your main window, clicking the "X" button will only hide the window, but your Java program will continue running in the background, and you'll have to stop it manually.
