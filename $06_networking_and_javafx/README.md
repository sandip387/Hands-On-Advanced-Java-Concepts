# Module 6: Networking and JavaFX

This module covers two essential areas of Advanced Java: network programming using sockets and building modern graphical user interfaces (GUIs) with JavaFX. This marks a significant move towards creating applications that can communicate over the internet and offer a polished user experience.

---

### Code Examples in this Module

- **`FileServer.java` & `FileClient.java`**: A client-server application demonstrating **TCP (Transmission Control Protocol)**. The server makes a file available, and the client requests it by name.
- **`UDPGreatestServer.java` & `UDPGreatestClient.java`**: A client-server application demonstrating **UDP (User Datagram Protocol)**. The client sends two numbers, and the server returns the greater of the two.
- **`JavaFXFormDemo.java`**: A complete, modern GUI form built with JavaFX. It showcases a superior layout (`GridPane`), a variety of UI controls (`TextField`, `RadioButton`, `CheckBox`, `TextArea`), and modern event handling with lambda expressions.

---

### Key Concepts & Common Questions

**Q1: What's the fundamental difference between TCP and UDP?**

This is a core networking concept and a frequent exam question.

- **TCP (Transmission Control Protocol)** is **connection-oriented** and **reliable**. It establishes a dedicated connection (like a phone call) before data is sent. It guarantees that all data packets will arrive in the correct order and without errors. It's used for applications where data integrity is critical, like web browsing (HTTP), file transfers (FTP), and email.
- **UDP (User Datagram Protocol)** is **connectionless** and **unreliable**. It simply sends packets (datagrams) to a destination without establishing a connection first (like sending a postcard). There is no guarantee of delivery, order, or error-checking. It's much faster and has less overhead, making it ideal for applications where speed is more important than perfect reliability, like online gaming, video streaming, and DNS lookups.

**Q2: Write the steps for writing client and server programs using TCP. (From 2080, Model Set II Papers)**

- **Server Side:**
  1.  Create a `ServerSocket` object, binding it to a specific port number (`ServerSocket ss = new ServerSocket(port);`).
  2.  Put the server in a loop to listen for connections.
  3.  Call the `accept()` method on the `ServerSocket`. This is a blocking call that waits until a client connects, then returns a `Socket` object for communication.
  4.  Get input and output streams (`InputStream`, `OutputStream`) from the client `Socket` to send and receive data.
  5.  Process the client's request and send a response.
  6.  Close the streams and the client `Socket`.
- **Client Side:**
  1.  Create a `Socket` object, specifying the server's IP address and port number (`Socket s = new Socket("localhost", port);`).
  2.  Get input and output streams from the `Socket`.
  3.  Send data to the server and receive the response.
  4.  Close the streams and the `Socket`.

**Q3: Compare JavaFX with Swing.**

This is the most common question about Java's GUI toolkits.

- **Swing** is the older, mature GUI toolkit. It has a vast number of third-party components and is well-documented. Its components are "lightweight" (written in pure Java).
- **JavaFX** is the modern successor to Swing. It offers a more contemporary look and feel, built-in support for modern features like CSS for styling, FXML for separating UI design from application logic (similar to HTML), and better integration with graphics and media. Its event handling with properties and lambda expressions is also considered cleaner.

**Q4: In JavaFX, what is the relationship between a `Stage`, a `Scene`, and a Layout Pane like `GridPane`?**

This describes the basic structure of any JavaFX application.

- **`Stage`:** The top-level window of the application (e.g., the main window with minimize, maximize, and close buttons). You are given the primary `Stage` by the JavaFX runtime.
- **`Scene`:** The content _inside_ the `Stage`. A `Stage` can only show one `Scene` at a time. It contains the "scene graph," which is the tree of all UI elements.
- **Layout Pane (`GridPane`, `VBox`, etc.):** The root node of the scene graph. It is a container that manages the size and position of all the other controls (like `Button`s and `TextField`s) you add to it. The `Scene` is created with this root layout pane.

**Q5: How does a JavaFX `Hyperlink` control format text that functions as a button?**

- A `Hyperlink` control in JavaFX is designed to look and feel like a standard web hyperlink. It appears as simple text, typically blue and underlined. However, functionally, it behaves like a `Button`. It fires an `ActionEvent` when clicked, so you can attach a listener to it using `.setOnAction()` to execute code, just as you would with a `Button`. It's ideal for navigation or triggering actions without the visual weight of a full button.

---

### Common Pitfalls & Things to Watch Out For

- **Forgetting VM Arguments for JavaFX:** When running a JavaFX application from the command line or a standard IDE, you **must** provide the VM arguments to point to the JavaFX SDK and specify which modules to load. The error `JavaFX runtime components are missing` is almost always caused by this.
  `--module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml`
- **Not Closing Sockets:** Just like file streams and database connections, sockets are system resources. Forgetting to close a `Socket` or `ServerSocket` in a `finally` block (or by using a try-with-resources statement) will lead to a resource leak.
- **Blocking the UI Thread:** In both Swing and JavaFX, there is a single UI thread (the Application Thread) that handles all rendering and event processing. If you run a long-running task (like a complex calculation or a slow network request) directly in an event handler, the entire application will freeze. The correct approach is to run such tasks on a separate background thread.
- **TCP `accept()` is a Blocking Call:** In `FileServer.java`, the `serverSocket.accept()` line will pause the program's execution indefinitely until a client connects. In a real-world server, you would immediately hand off the returned `clientSocket` to a new `Thread` so the server can instantly go back to waiting for the next client.
- **Mixing TCP/UDP Classes:** A common error is trying to use a `DatagramPacket` with a `Socket` or an `InputStream` with a `DatagramSocket`. Remember:
  - **TCP (streams):** `ServerSocket`, `Socket`, `InputStream`, `OutputStream`.
  - **UDP (packets):** `DatagramSocket`, `DatagramPacket`, byte arrays.
- **UDP Packet Size Limits:** While UDP is fast, it's designed for small packets of data (typically under 64KB). You cannot use it to send large files directly without breaking them into many small, numbered packets and reassembling them on the other side (a complex task that TCP handles for you automatically).
