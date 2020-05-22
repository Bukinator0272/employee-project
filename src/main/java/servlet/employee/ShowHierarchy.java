package servlet.employee;

import controller.EmployeeController;
import model.entity.HierarchyEmployees;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/show-hierarchy")
public class ShowHierarchy extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeController employeeController = new EmployeeController();

        List<HierarchyEmployees> hierarchy = null;

        try {
            hierarchy = employeeController.showHierarchy();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("hierarchy", hierarchy);
        getServletContext().getRequestDispatcher("/show_hierarchy.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
