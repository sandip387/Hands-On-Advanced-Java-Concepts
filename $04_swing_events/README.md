# Module 4: Swing Components & Event Handling

This module brings our graphical user interfaces to life. We move beyond static layouts to create interactive applications that respond to user actions like button clicks, menu selections, mouse movements, and key presses. This is the core of any GUI application.

---

### Code Examples in this Module

- **`StudentForm.java`**: A comprehensive form that uses multiple Swing components (`JTextField`, `JRadioButton`, `JCheckBox`, `JButton`). It demonstrates handling `ActionEvent` from buttons to gather user input and display it.
- **`MenuDemo.java`**: Shows how to build a standard desktop menu bar (`JMenuBar`) with menus (`JMenu`) and items (`JMenuItem`). It also covers how to add professional touches like keyboard **Mnemonics** (Alt+Key) and **Accelerators** (Ctrl+Key shortcuts).
- **`EventDemo.java`**: Dives deeper into event handling by implementing `MouseListener` and `KeyListener`. This example shows how to trigger different actions on a mouse press vs. a mouse release and how to listen for global key presses like the `Escape` key.

---

### Key Concepts & Common Questions

**Q1: What is the task of a Listener interface?**

Listener interfaces are the foundation of Java's event model. They act as a contract, defining one or more methods that a class must implement in order to "listen" for and respond to specific events. This follows the Observer design pattern: a component (the "source," like a button) allows other objects (the "listeners") to register with it. When an event occurs, the source notifies all its registered listeners by calling the appropriate method on them.

**Q2: What's the difference between an `ActionListener`, `MouseListener`, and `KeyListener`?**

They are all "listener" interfaces, but they listen for different types of user interaction.

- `ActionListener` is for high-level "action" events. This is the most common one, used for button clicks, menu item selections, or pressing Enter in a text field. It has only one method: `actionPerformed()`.
- `MouseListener` is for low-level mouse events. It lets you respond to specific mouse actions like pressing a button down, releasing it, clicking, or the cursor entering/exiting a component's area. It has five methods.
- `KeyListener` is for low-level keyboard events. It lets you respond to pressing and releasing specific keys on the keyboard. It has three methods.

**Q3: What is an "Adapter Class" and why is it useful?**

When a listener interface has multiple methods (like `MouseListener`'s five methods), you are forced to implement all of them, even if you only care about one (e.g., `mousePressed`). This clutters your code with empty methods.

An **Adapter Class** (like `MouseAdapter` or `KeyAdapter`) is a helper class that provides pre-built empty implementations for all the methods in a listener interface. You can then **extend the adapter class** and only override the one or two methods you actually need. It's a much cleaner approach.

**Q4: What's the difference between a Mnemonic and an Accelerator?**

This is about local vs. global shortcuts.

- **Mnemonic (`setMnemonic`):** A local shortcut (`Alt + Key`) that helps you navigate a UI component that is _already visible_, like opening a menu (`Alt+F` for "<u>F</u>ile").
- **Accelerator (`setAccelerator`):** A global shortcut (`Ctrl + Key`) that triggers an action from anywhere in the application, regardless of what is visible. It's for power-user efficiency (e.g., `Ctrl+S` to save).

**Q5: Why do we use `Double.parseDouble(String)` instead of just casting `(double)myString`?**

In Java, casting `(double)` only works between compatible numeric types (like `int` to `double`). A `String` (a sequence of characters) and a `double` (a 64-bit number format) are completely different and incompatible data types. You **must** use a parsing method like `Double.parseDouble()` to tell Java how to interpret the characters in the string and convert them into a true number.

---

### Common Pitfalls & Things to Watch Out For

- **Forgetting to Register a Listener:** A classic mistake is creating a listener method but forgetting to actually attach it to a component with `myButton.addActionListener(this)`. If you don't register it, the event will never be handled.
- **Forgetting `setFocusable(true)` for `KeyListener`:** For a component to receive keyboard events, it must be "focusable." For components like `JFrame`, you often need to explicitly call `setFocusable(true)` before the `KeyListener` will work.
- **Not Handling `NumberFormatException`:** When converting user input from a `JTextField` to a number using `Integer.parseInt()` or `Double.parseDouble()`, you must wrap the code in a `try-catch` block. If the user enters non-numeric text or leaves the field blank, a `NumberFormatException` will be thrown, crashing your event handler if not caught.
- **Using `+` to Build Strings in a Loop:** When assembling a string from many pieces (like in our form's submit logic), using the `+` operator is very inefficient because it creates many temporary `String` objects. Using a `StringBuilder` is the correct and high-performance way to do this.
- **Confusing `ActionEvent` vs. `ItemEvent`:**
  - `ActionEvent` is for definitive actions like a button click.
  - `ItemEvent` is for changes in state, like selecting or deselecting a `JCheckBox` or choosing a new item from a `JComboBox`. Using the wrong listener means your code won't respond to the correct user interaction.
