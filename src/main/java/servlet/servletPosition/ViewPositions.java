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
import java.util.List;

@WebServlet("/view-positions")
public class ViewPositions extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PositionController positionController = new PositionController();
        List<Position> positions = null;
        try {
            positions = positionController.getPosition();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("positions", positions);
        getServletContext().getRequestDispatcher("/viewPositionsPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}