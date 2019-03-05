package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import service.OrderService;
import service.ProductService;
import service.impl.ServiceManager;


public abstract class AbstractController extends HttpServlet {

	private ProductService productService;
	private OrderService orderService;

	@Override
	public final void init() throws ServletException {
		productService = ServiceManager.getInstance(getServletContext()).getProductService();
		orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
	}

	public final ProductService getProductService() {
		return productService;
	}

	public final OrderService getOrderService() {
		return orderService;
	}
}
