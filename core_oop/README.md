# Module 1: Core Java & Object-Oriented Programming

This module covers the foundational principles of the Java language and Object-Oriented Programming(OOP). The examples demonstrate core architecture, the four pillars of OOP and code organization with packages and inner classes.

---

### Code Examples

- **`OuterClass.java`**: A straightforward example of how an "inner class" works and how you create an instance of it.

- **`Shape.java` / `Circle.java` / `InterfaceDemo.java`**: This trio is a classic OOP pattern. `Shape` defines a contract (an `interface`), `Circle` is a concrete implementation of that contract, and `InterfaceDemo` is a simple `main` class that shows how to use them together.

- **`mypackage/Calculator.java` / `TestCalculator.java`**: Shows how to create hierarchical packages (`core_oop.mypackage`) and import classes across them.

---

### Key Concepts & Interview Questions

Here are some key ideas from this module, framed as common questions you might encounter.

**Q1: What is the difference between PATH and CLASSPATH in Java?**

- **`PATH` is for your Operating System.** It's a list of folders where your OS looks for executable programs like `javac.exe` and `java.exe`. If you can type `javac` in any terminal and it works, your `PATH` is set up correctly.
- **`CLASSPATH` is for the JVM.** It’s a list of folders and JAR files where Java itself looks for your compiled `.class` files. When you run `java com.myapp.MyClass`, the JVM uses the `CLASSPATH` to find the `MyClass.class` file inside the `com/myapp/` folders.

**Q2: When would I actually use an anonymous inner class?**

You'll use them most often for short, one-time "helper" tasks, especially in older GUI code like Swing or Android. They're perfect when you need to respond to an event (like a button click) and the logic is so simple that creating a whole new `.java` file for it would be overkill.

```java
// We'll see this pattern in action in the Swing/GUI modules.
// A perfect use-case: defining a simple action for a button on the fly.
button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // This logic is only used here and nowhere else.
        System.out.println("Button was clicked!");
    }
});
```

**Q3: What do the compiled `.class` files look like for nested classes?**  
If you have a class `A` with an inner class `B`, the compiler doesn't mash them together. It creates two distinct files:

1.  `A.class`
2.  `A$B.class`

The dollar sign `$` is the special character Java uses to link an inner class file to its outer class.

---

### Common Pitfalls & Things to Watch Out For

- **Instantiating Inner Classes:** It's easy to forget that non-static inner classes need an instance of the outer class to live. The syntax `outerObject.new InnerClass()` feels weird at first but is essential.
- **Package Mismatches:** This is probably the #1 command-line error. If your file is in a folder named `core_oop`, it **must** have `package core_oop;` at the very top. The folder structure and the package declaration have to match perfectly.
- **Classpath vs. Path Confusion:** If you get a `ClassNotFoundException`, the problem is almost always your Java `CLASSPATH`, not your system `PATH`. Don't start editing system-wide variables; check how you're running the `java` command first.