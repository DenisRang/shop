package filters;

import model.ShoppingCart;
import utils.SessionUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

@WebFilter(filterName = "TrimResponseFilter")
public class TrimResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("ds");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ServletResponseWrapper responseWrapper = new ServletResponseWrapper(response);

        //response.get
        filterChain.doFilter(servletRequest, responseWrapper);
    }

    @Override
    public void destroy() {
        System.out.println("ds");
    }

    private class TrimResponseWrapper extends HttpServletResponseWrapper {

        public TrimResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return new ServletOutputStream() {
                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setWriteListener(WriteListener writeListener) {

                }

                @Override
                public void write(int b) throws IOException {
                    trimProxyWriter.write(b);
                }
            }
        }
    }

    private class TrimProxyWriter extends Writer {

        private final Writer wr;
        private int length;

        private TrimProxyWriter(Writer wr) {
            super();
            this.wr = wr;
            this.length = 0;
        }

        @Override
        public void write(int c) throws IOException {
            processChar((char) c);
        }

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {
            for (int i = off; i < len; i++) {
                processChar(cbuf[i]);
            }
        }

        @Override
        public void write(String s) throws IOException {
            for (int i = 0; i < s.length(); i++) {
                processChar(s.charAt(i));
            }
        }

        @Override
        public void flush() throws IOException {

        }

        @Override
        public void close() throws IOException {

        }

        private void processChar(char c) throws IOException {
            if (c != '\t' && c != '\r' && c != '\n') {
                wr.write(c);
                length++;
            }
        }

        private void complete() throws IOException {
            wr.flush();
            wr.close();
        }
    }
}






















