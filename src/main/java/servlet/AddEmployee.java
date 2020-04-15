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

    private EmployeeController employeeController = new EmployeeController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.removeAttribute("employees");
        getServletContext().getRequestDispatcher("/AddEmployeePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("manager") == null || req.getParameter("manager").equals("")) {
            employeeController.add(
                    req.getParameter("name"),
                    req.getParameter("surname"),
                    req.getParameter("department"),
                    req.getParameter("position"),
                    0
            );
        } else {
            employeeController.add(
                    req.getParameter("name"),
                    req.getParameter("surname"),
                    req.getParameter("department"),
                    req.getParameter("position"),
                    Integer.parseInt(req.getParameter("manager"))
            );
        }
        resp.sendRedirect(req.getContextPath() + "/HomePage.jsp");
        //getServletContext().getRequestDispatcher("/HomePage.jsp").forward(req, resp);
    }

}
