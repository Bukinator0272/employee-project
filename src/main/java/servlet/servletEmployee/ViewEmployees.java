package servlet.servletEmployee;

import controller.EmployeeController;
import model.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/view-employees")
public class ViewEmployees extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeController employeeController = new EmployeeController();
        List<Employee> employees = null;
        try {
            employees = employeeController.getEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("employees", employees);
        getServletContext().getRequestDispatcher("/viewEmployeesPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
