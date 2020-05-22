package servlet.department;

import controller.DepartmentController;
import model.entity.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit-department")
public class EditDepartment extends HttpServlet {

    Department department;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DepartmentController departmentController = new DepartmentController();
        try {
            department = departmentController.getDepartmentById(Long.valueOf(req.getParameter("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("department", department);
        getServletContext().getRequestDispatcher("/edit_department.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        department.setName(newName);
        DepartmentController departmentController = new DepartmentController();
        try {
            departmentController.saveDepartment(department);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/view-departments");
    }
}
