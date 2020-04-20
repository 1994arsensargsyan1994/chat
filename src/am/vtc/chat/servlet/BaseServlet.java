package am.vtc.chat.servlet;

import am.vtc.chat.service.UserService;
import am.vtc.chat.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {
    protected UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl();
    }
}
