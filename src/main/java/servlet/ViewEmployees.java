package servlet;

import controller.EmployeeController;
import model.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view-employees")
public class ViewEmployees extends HttpServlet {

    private EmployeeController employeeController = new EmployeeController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeController.getAll();
        req.setAttribute("employees", employees);
        System.out.println("View Down 3");
        getServletContext().getRequestDispatcher("/ViewEmployeesPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
