package ra.example.controller;
import ra.example.entity.User;
import ra.example.service.UserService;
import ra.example.service.imp.UserServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "UserController", value = "/UserController")
public class UserController extends HttpServlet {
    private final UserService userService;

    public UserController() {
        userService = new UserServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action.equals("findAll")){
            findAll(request, response);
        }else if(action.equals("create")){
            request.getRequestDispatcher("/viewsUser/create.jsp").forward(request, response);
        }else if(action.equals("initUpdate")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = userService.findById(userId);
            request.setAttribute("user", user);
            request.getRequestDispatcher("/viewsUser/update.jsp").forward(request, response);
        }

    }
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/viewsUser/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if(action.equals("create")){
            User user = new User();
            user.setUsername(request.getParameter("userName"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(request.getParameter("email"));
            user.setFullName(request.getParameter("fullName"));
            user.setStatus(Boolean.parseBoolean(request.getParameter("status")));

            boolean result = userService.save(user);
            if(result){
                findAll(request, response);
            }else{
                request.getRequestDispatcher("/viewsUser/create.jsp").forward(request, response);
            }
        }

    }
}