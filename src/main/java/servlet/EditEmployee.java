package servlet;

import controller.EmployeeController;
import model.entity.Employee;
import model.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit-employee")
public class EditEmployee extends HttpServlet {

    private EmployeeController employeeController = new EmployeeController();
    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.removeAttribute("employees");
        Employee employee = employeeController.get(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("manager", employeeService.getManagersNameSurname(employee.getManager()));
        req.setAttribute("employee", employee);
        getServletContext().getRequestDispatcher("/EditEmployeePage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.removeAttribute("manager");
        req.removeAttribute("employee");
        Employee employee = employeeController.get(Integer.parseInt(req.getParameter("id")));
        String name = req.getParameter("name").replace(" ", "");
        if (name.equals("")) {
            name = employee.getName();
        }
        String surname = req.getParameter("surname").replace(" ", "");
        if (surname.equals("")) {
            surname = employee.getSurname();
        }
        String department = req.getParameter("department").replace(" ", "");
        if (department.equals("")) {
            department = employee.getDepartment();
        }
        String position = req.getParameter("position").replace(" ", "");
        if (position.equals("")) {
            position = employee.getPosition();
        }
        int manager;
        if (req.getParameter("manager") == null || req.getParameter("manager").equals("")) {
            manager = employee.getManager();
        } else {
            manager = Integer.parseInt(req.getParameter("manager").replace(" ", ""));
            if (manager == 0) {
                manager = employee.getManager();
            }
        }
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPosition(position);
        employee.setDepartment(department);
        employee.setManager(manager);
        employeeController.update(employee);
        resp.sendRedirect(req.getContextPath() + "/HomePage.jsp");
        //getServletContext().getRequestDispatcher("/HomePage.jsp").forward(req, resp);
    }

}
