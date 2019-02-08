package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/admin",
        initParams = {
                @WebInitParam(name = "ACCESS_KEY", value = "ACCESSKEY"),
                @WebInitParam(name = "LOGIN", value = "admin"),
                @WebInitParam(name = "PASSWORD", value = "password")
        })
public class ShoppingCartServlet extends HttpServlet {
    private String accessKey;
    private String login;
    private String password;

    @Override
    public void init() throws ServletException {
        accessKey = getServletConfig().getInitParameter("ACCESS_KEY");
        login = getServletConfig().getInitParameter("LOGIN");
        password = getServletConfig().getInitParameter("PASSWORD");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<form action='/admin' method='post'>");
            out.println("<input name='login' placeholder='Login'>");
            out.println("<input name='password' placeholder='Password' type='password'>");
            out.println("<input type='submit' value='Login'>");
            out.println("</form>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getLocalAddr();
        String accessKey = req.getHeader("ACCESS_KEY");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean isLoginEmpty = login == null || login.trim().isEmpty();
        boolean isPasswordEmpty = password == null || password.trim().isEmpty();
        boolean isCredentialsCorrect = login.equals(this.login) && password.equals(this.password);

        if (accessKey != null && accessKey.equals(this.accessKey)) {
            resp.setContentType("text/html");
            resp.getWriter().println("<h5 style='color:green'>" + "OK" + "</h5>");
            System.out.println(String.format("Login via accessKey: %s", accessKey));
        }
        if (isLoginEmpty || isPasswordEmpty) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("FAILED");
            System.out.println(String.format("Login: %s\n" +
                    "Password: %s\n" +
                    "IP: %s\n" +
                    "Access key: %s", login, password, ip, accessKey));
        } else if (isCredentialsCorrect) {
            resp.setContentType("text/html");
            resp.getWriter().println("<h5 style='color:green'>" + "OK" + "</h5>");
            System.out.println(String.format("Login via login and password: %s/%s", login, password));
        }else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println(String.format("Login: %s\n" +
                    "Password: %s\n" +
                    "IP: %s\n" +
                    "Access key: %s", login, password, ip, accessKey));
        }
    }

}