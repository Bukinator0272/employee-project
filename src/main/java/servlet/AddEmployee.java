package servlet;

import controller.EmployeeController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-employee")
public class AddEmployee extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/AddEmployeePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeController employeeController = new EmployeeController();
        employeeController.add(
                req.getParameter("name"),
                req.getParameter("surname"),
                req.getParameter("department"),
                req.getParameter("position"),
                req.getParameter("projectName"),
                Integer.parseInt(req.getParameter("cabinetNumber"))
        );
        getServletContext().getRequestDispatcher("/HomePage.jsp").forward(req, resp);
    }

}
