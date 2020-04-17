package am.vtc.chat.servlet;

import am.vtc.chat.model.User;
import am.vtc.chat.service.UserService;
import am.vtc.chat.service.UserServiceImpl;
import am.vtc.chat.util.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/register.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestValidator<User> validator = validate(req);
        try {
            if (validator.isValid()) {
                User user = validator.getEntity();
                if (false) {                               //this.userService.userExist(user.getEmail())
                    req.setAttribute("errorEmail","user already exist");
                }else {
                  //  TODO save user and to login page
             //       this.userService.save(user)
                    req.getSession().setAttribute("successfully","user successfully registered.");
                    resp.sendRedirect("/login");
                    return;
                }
            }
                req.getRequestDispatcher("WEB-INF/register.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    private static RequestValidator<User> validate(HttpServletRequest request) {
        boolean isValid = true;
        String name = request.getParameter("name");
        if (DataValidator.isNullOrBlank(name)) {
            request.setAttribute("errorName", "Name is Required");
            isValid = false;
        }
        String surname = request.getParameter("surname");
        if (DataValidator.isNullOrBlank(surname)) {
            request.setAttribute("errorSurname", "Surname is Required");
            isValid = false;
        }
        String email = request.getParameter("email");
        if (DataValidator.isNullOrBlank(email)) {
            request.setAttribute("errorEmail", "Email is Required");
            isValid = false;
        }
        String password = request.getParameter("password");
        if (DataValidator.isNullOrBlank(password)) {
            request.setAttribute("errorPassword", "Password is Required");
            isValid = false;
        } else {
            String confirmPassword = request.getParameter("confirmPassword");
            if (!password.trim().equals(confirmPassword)) {
                request.setAttribute("errorConfirmPassword", "Password does not match!");
                isValid = false;
            }
        }
        RequestValidator<User> validator = new RequestValidator<>();
        validator.setValid(isValid);
        if (isValid) {
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
        }
        return validator;

    }

}
