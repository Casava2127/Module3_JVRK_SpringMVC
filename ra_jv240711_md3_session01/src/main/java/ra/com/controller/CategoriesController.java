package ra.com.controller;

import ra.com.entity.Categories;
import ra.com.service.CategoriesService;
import ra.com.service.imp.CategoriesServiceImp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriesController", value = "/CategoriesController")
public class CategoriesController extends HttpServlet {
    //tiêm 1 instance của CategoriesService
    private CategoriesService categoriesService;

    public CategoriesController() {
        categoriesService = new CategoriesServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. Lấy tham số action trong request
        String action = request.getParameter("action");
        if (action.equals("findAll")) {
            findAll(request, response);
        } else if (action.equals("create")) {
            request.getRequestDispatcher("/views/newCategories.jsp").forward(request, response);
        } else if (action.equals("initUpdate")) {
            int catalogId = Integer.parseInt(request.getParameter("catalogId"));
            Categories catalog = categoriesService.findById(catalogId);
            request.setAttribute("catalog", catalog);
            request.getRequestDispatcher("/views/updateCategories.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            int catalogId = Integer.parseInt(request.getParameter("catalogId"));
            boolean result = categoriesService.delete(catalogId);
            if (result) {
                findAll(request, response);
            } else {
                request.getRequestDispatcher("/views/error.jsp").forward(request, response);
            }
        }
    }

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //2. Gọi sang service
        List<Categories> listCategories = categoriesService.findAll();
        //3. add listCategories vào request
        request.setAttribute("listCategories", listCategories);
        //4. Chuyển request và response sang trang categories.jsp
        request.getRequestDispatcher("/views/categories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.equals("create")) {
            //Thực hiện thêm mới
            //1. Lấy dữ liệu từ body của request --> catalog
            Categories catalog = new Categories();
            catalog.setCatalogName(request.getParameter("catalogName"));
            catalog.setDescription(request.getParameter("description"));
            catalog.setStatus(Boolean.parseBoolean(request.getParameter("status")));
            //2. Gọi sang service để thêm vào db và nhận lại kết qủa
            boolean result = categoriesService.save(catalog);
            //3. Nếu KQ là true --> gọi lại findAll
            if (result) {
                findAll(request, response);
            } else {
                request.getRequestDispatcher("/views/error.jsp").forward(request, response);
            }
        } else if (action.equals("update")) {
            Categories catalog = new Categories();
            catalog.setCatalogId(Integer.parseInt(request.getParameter("catalogId")));
            catalog.setCatalogName(request.getParameter("catalogName"));
            catalog.setDescription(request.getParameter("description"));
            catalog.setStatus(Boolean.parseBoolean(request.getParameter("status")));
            boolean result = categoriesService.update(catalog);
            if (result) {
                findAll(request, response);
            } else {
                request.getRequestDispatcher("/views/error.jsp").forward(request, response);
            }
        }
    }
}