package servlets;

import model.ShoppingCart;
import utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/ShoppingCart")
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!SessionUtils.isCurrentShoppingCartCreated(req)) {
            Cookie cookie = SessionUtils.findShoppingCartCookie(req);
            if (cookie != null) {
                ShoppingCart shoppingCart = ShoppingCart.shoppingCartFromString(cookie.toString());
                SessionUtils.setCurrentShoppingCart(req, shoppingCart);
            }
        } else {
            String shoppingCart = SessionUtils.getCurrentShoppingCart(req).shoppingCartToString();
            SessionUtils.updateCurrentShoppingCartCookie(shoppingCart, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}