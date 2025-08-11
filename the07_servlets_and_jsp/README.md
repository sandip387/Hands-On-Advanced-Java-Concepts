# Module 7: Java Servlets & JSP

This module introduces the foundation of web development in the Java ecosystem. We learn how to build dynamic, server-side applications that can process user input from web forms and generate custom responses.

- **Servlets** are Java classes that run on a web server to handle client requests and generate responses. They are the "brains" or the controller layer of a Java web application.
- **JSP (JavaServer Pages)** are HTML files with embedded Java code. They are primarily used for creating the "view" or the presentation layer, making it easier to design dynamic web pages than building them entirely with Java code.

---

### Code Examples in this Module

- **`login.html`**: A standard HTML form that collects a username and password from the user. It sends this data via an `HTTP POST` request to our servlet.
- **`LoginServlet.java`**: A servlet that handles the form submission. It retrieves the user's input, validates it against hardcoded values, and dynamically generates an HTML response page indicating success or failure. It uses the `@WebServlet` annotation for modern URL mapping.
- **`input.html` & `calculate.jsp`**: A pair of files demonstrating JSP. The HTML form collects financial data, and the `calculate.jsp` page receives this data, performs a calculation using embedded Java code (scriptlets and expressions), and displays the result within an HTML structure.

---

### 🚀 How to Run This Module

This module requires a **Servlet Container** like Apache Tomcat to run. The following steps detail a manual, fundamental deployment method that bypasses IDE-specific plugins.

**Prerequisites:**

1.  A working JDK installation.
2.  Apache Tomcat downloaded and unzipped on your machine.
3.  The `JAVA_HOME` environment variable must be set and point to your JDK directory.

**Step-by-Step Instructions:**

1.  **Prepare the Application:**

    - Ensure you have copied `servlet-api.jar` from your Tomcat's `lib` directory into this module's `WEB-INF/lib/` directory.

2.  **Compile the Servlet:**

    - Open a terminal and navigate into this `the07_servlets_and_jsp` directory.
    - Run the following `javac` command. This compiles the servlet and places the resulting `.class` file into the correct `WEB-INF/classes` directory.

    ```powershell
    javac -cp "WEB-INF/lib/servlet-api.jar" -d WEB-INF/classes src/LoginServlet.java
    ```

3.  **Deploy the Application:**

    - Navigate to your Tomcat installation's `webapps` directory.
    - **Copy** the entire `the07_servlets_and_jsp` folder from your project.
    - **Paste** it into the `webapps` directory.

4.  **Start the Server:**

    - Navigate to your Tomcat's `bin` directory.
    - Run `startup.bat` (on Windows) or `startup.sh` (on Mac/Linux). A new terminal window will appear and stay open; this is the running server.

5.  **Test in Browser:**
    - **Servlet Test:** Open your browser and navigate to `http://localhost:8080/the07_servlets_and_jsp/login.html`.
    - **JSP Test:** Open your browser and navigate to `http://localhost:8080/the07_servlets_and_jsp/input.html`.

---

### Kye Concepts and Common Questions

**Q1: Explain the servlet lifecycle.**
The servlet container manages the entire lifecycle of a servlet instance.

1.  **Loading & Instantiation:** The container loads the servlet class and creates a single instance of it.
2.  **Initialization (`init()`):** The container calls the `init()` method once, immediately after creating the instance. This is used for one-time setup tasks like creating database connections or loading configuration.
3.  **Servicing Requests (`service()`):** For every client request that maps to the servlet, the container calls the `service()` method. This method is the heart of the servlet; it dispatches the request to `doGet()`, `doPost()`, etc.
4.  **Destruction (`destroy()`):** When the container shuts down or reloads the application, it calls the `destroy()` method once. This is used for cleanup tasks, like closing database connections and releasing resources.

**Q2: Compare JSP with Servlets.**

- **Servlet:** Pure Java code that generates HTML output using `PrintWriter`. It's better for complex business logic, acting as a controller. It's difficult to design complex UIs with servlets.
- **JSP:** Essentially an HTML file with special tags (`<% ... %>`, `<%= ... %>`) that allow you to embed Java code. It's better for presentation and designing the view. The container automatically translates a JSP into a servlet behind the scenes.

**Q3: What's the difference between doGet() and doPost()?**

- **doGet():** Handles HTTP GET requests. Data is sent as parameters in the URL (e.g., **.../search?query=java**). GET requests are typically used for retrieving data, should be idempotent (running them multiple times has the same effect), and can be bookmarked.
- **doPost():** Handles HTTP POST requests. Data is sent in the hidden body of the HTTP request. POST requests are used for actions that change data on the server (e.g., submitting a form, creating a new user). They are not idempotent and cannot be bookmarked.

**Q4: What are "JSP Implicit Objects"?**

JSP makes several objects automatically available to you within scriptlets (<% ... %>) without you needing to declare them. The most common ones are:

- request: The `**HttpServletRequest**` object for the current request.
- response: The `**HttpServletResponse**` object for the current response.
- out: The `**PrintWriter**` object used to write output to the page (similar to response.getWriter()).
- session: The `**HttpSession**` object associated with the user.

---

### ⚠️ Common Mistakes

- **Forgetting `@WebServlet` or `web.xml` Mapping:** If you don't map a URL to your servlet, Tomcat has no idea which class to run for a given request, resulting in a `404 Not Found` error.
- **Placing `.class` Files in the Wrong Directory:** Compiled servlet classes **must** go inside the `WEB-INF/classes` directory, following their package structure. Placing them anywhere else will make them invisible to the container.
- **Session Management Issues:** Forgetting to create a session (`request.getSession()`) or storing user data in local variables (which are lost after each request) are common mistakes when trying to maintain user state.
- **Mixing Scriptlets and Logic:** It's tempting to put a lot of complex Java logic (like database calls) directly into a JSP file. This is bad practice as it mixes presentation with business logic, making the code hard to read and maintain. The best practice is the **MVC (Model-View-Controller)** pattern, where a Servlet (Controller) handles the logic and then forwards the request to a JSP (View) for display.
- **Security Vulnerabilities:** Directly printing user input back to the page without sanitizing it can lead to **Cross-Site Scripting (XSS)** vulnerabilities. While not covered in depth here, it's a critical real-world concern.
