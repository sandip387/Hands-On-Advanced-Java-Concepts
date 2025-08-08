
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

// "Any request for the URL '/LoginServlet' should be handled by this class."
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Login Result</title></head>");
        out.println("<body>");
        out.println("<h2>Login Result</h2>");

        // Simple validation (for demo purposes)
        if ("admin".equals(username) && "password".equals(password)) {
            out.println("<p style='color:green'>✅ Welcome, " + username + "!</p>");
            out.println("<p>Login successful!</p>");
        } else {
            out.println("<p style='color:red'>❌ Invalid username or password!</p>");
            out.println("<p>Please try again.</p>");
        }

        out.println("<br><a href='login.html'>🔙 Back to Login</a>");
        out.println("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // This is good practice. It handles cases where a user might try to
        // access the servlet directly via a GET request.D
        doPost(request, response);
    }
}
