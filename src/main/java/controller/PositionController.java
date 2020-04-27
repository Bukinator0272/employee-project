package controller;

import model.dao.PositionDAOImpl;
import model.entity.Position;

import java.sql.SQLException;
import java.util.List;

public class PositionController {

    public PositionController() {
    }

    public Position getPositionById(Long id) throws SQLException {
        PositionDAOImpl positionDAO = new PositionDAOImpl();
        Position position = positionDAO.get(id);
        return position;
    }

    public List<Position> getPosition() throws SQLException {
        PositionDAOImpl positionDAO = new PositionDAOImpl();
        return positionDAO.getAll();
    }

    public void savePosition(String name) throws SQLException {
        Position position = new Position(name);
        PositionDAOImpl positionDAO = new PositionDAOImpl();
        positionDAO.insert(position);
    }

    public void savePosition(Position position) throws SQLException {
        PositionDAOImpl positionDAO = new PositionDAOImpl();
        positionDAO.update(position);
    }

    public void removePosition(Long id) throws SQLException {
        if (id != null) {
            PositionDAOImpl positionDAO = new PositionDAOImpl();
            positionDAO.delete(id);
        }
    }
}
