# Module 8: RMI and CORBA

This module explores distributed computing in Java. Unlike web applications which communicate over HTTP, distributed object systems allow a program on one machine to directly call methods on an object living in another machine's memory, as if it were a local object.

- **RMI (Remote Method Invocation):** The pure Java-to-Java solution for distributed objects. It is relatively simple and powerful for communication between Java applications.
- **CORBA (Common Object Request Broker Architecture):** A language-independent standard. It allows objects written in different languages (like Java, C++, Python) to communicate with each other. It is more complex than RMI but offers greater interoperability.

---

### Code Examples in this Module

This module contains a complete, working RMI application that calculates factorials remotely.

- **`FactorialInterface.java`**: The remote interface. This is the "contract" that defines which methods the client can call. It must extend `java.rmi.Remote`.
- **`FactorialImpl.java`**: The server-side implementation of the interface. This class contains the actual logic for the factorial calculation and extends `UnicastRemoteObject`.
- **`FactorialServer.java`**: The server program. Its job is to create an instance of the implementation, start an RMI Registry, and bind the object to a public name so clients can find it.
- **`FactorialClient.java`**: The client program. It looks up the remote object in the server's registry and then calls its methods as if it were a normal, local Java object.

---

### 🚀 How to Run This Module

Running an RMI application requires starting multiple Java processes. The modern approach is to let the server manage its own registry.

**Prerequisites:**

1.  A working JDK installation.
2.  The `JAVA_HOME` environment variable should be set.

**Step-by-Step Instructions:**

1.  **Compile all Java files:**

    - Open a terminal and navigate to your project root (`Hands-On-Advanced-Java-Concepts`).
    - Run the `javac` command to compile all files in this module:

    ```powershell
    javac the08_rmi_and_corba/*.java
    ```

2.  **Start the Server:**

    - In the **same terminal**, run the `FactorialServer` program. It will start its own internal RMI Registry and wait.

    ```powershell
    java the08_rmi_and_corba.FactorialServer
    ```

    - You will see the message "Factorial Server is ready..." and the terminal will seem to hang. This is correct; it is now listening for client connections.

3.  **Run the Client:**
    - **Open a second, separate terminal window.**
    - Navigate to your project root in this new terminal.
    - Run the `FactorialClient` program.
    ```powershell
    java the08_rmi_and_corba.FactorialClient
    ```
    - The client will prompt you to enter a number. Type a number and press Enter. The client will display the result, and you will see a confirmation message appear in the server's terminal window.

---

### Key Concepts and Common Questions

**Q1: What is RMI? What problem does it solve? Discuss the role of the stub and skeleton.**

- **RMI (Remote Method Invocation)** is a Java API that allows an object running in one Java Virtual Machine (JVM) to invoke methods on an object running in another JVM, even if that JVM is on a different machine. It solves the problem of inter-process communication for distributed Java applications, making it seem like the remote method call is just a normal, local method call.

- **Stub:** The stub is a client-side proxy object. When the client calls a method on the remote object, it's actually calling a method on the local stub. The stub's job is to **marshal** (package up) the method arguments and send them over the network to the server.
- **Skeleton:** The skeleton is a server-side object that receives the request from the stub. Its job is to **unmarshal** (unpack) the arguments, call the actual method on the real remote object, and then marshal the return value to send it back to the stub.
  _(Note: In modern Java, the role of the skeleton is handled dynamically by the RMI runtime, so you no longer need to generate skeleton files manually.)_

**Q2: Differentiate between RMI and CORBA.**

- **RMI:** Is Java-specific. It's designed for seamless communication _between Java applications_. It is simpler to use because it leverages Java's native object model and serialization.
- **CORBA:** Is language-agnostic. It uses an **Interface Definition Language (IDL)** to define remote object contracts. This IDL can then be used to generate code for multiple languages (Java, C++, Python, etc.), allowing them to interoperate. CORBA is more complex but provides a solution for heterogeneous environments.

---

### ⚠️ Common Pitfalls and Things to Watch Out For

- **`Port already in use`: Exception:** This happens if you start the standalone `rmiregistry` command and _also_ have `LocateRegistry.createRegistry()` in your server code. The modern approach is to let the server create its own registry and not run the separate `rmiregistry` command.
- **`NotSerializableException`**: If you try to pass an object as a parameter or return value in an RMI call, that object's class must implement the `java.io.Serializable` interface. RMI uses Java's serialization mechanism to transfer objects over the network.
- **`ClassNotFoundException` on Client:** If the client cannot find the class definition for the remote interface, it means the `.class` file for the interface is not on the client's classpath. When running from the project root, this is usually not an issue.
- **Not Extending `UnicastRemoteObject` or `Remote`:** Forgetting to have the implementation extend `UnicastRemoteObject` or the interface extend `Remote` will cause errors, as the RMI system won't recognize them as valid remote objects.
- **`Firewall Issues`**: RMI can be tricky to use across different networks because it may use random ports for communication in addition to the registry port (1099). This can often be blocked by firewalls, whereas standard HTTP on port 80/443 is almost always allowed.
- Forgetting **`throws RemoteException`**: Every method declared in a remote interface must be declared to throw RemoteException. This is a checked exception, forcing the client to handle potential network errors (like the server being down or the connection dropping).
