package servlet.servletPosition;

import controller.PositionController;
import model.entity.Position;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit-position")
public class EditPosition extends HttpServlet {

    private Position position;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionController positionController = new PositionController();
        try {
            position = positionController.getPositionById(Long.valueOf(req.getParameter("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("position", position);
        getServletContext().getRequestDispatcher("/editPositionPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newName = req.getParameter("name");
        position.setName(newName);
        PositionController positionController = new PositionController();
        try {
            positionController.savePosition(position);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/view-positions");
    }
}
