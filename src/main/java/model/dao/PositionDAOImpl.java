package model.dao;

import model.dao.constant.EmployeesNC;
import model.entity.Position;
import model.executor.Executor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDAOImpl {

    private Executor executor;

    public PositionDAOImpl() throws SQLException {
        this.executor = new Executor();
    }

    private boolean isExist(Position position) throws SQLException {
        if (position == null || position.getId() == null)
            return false;
        executor = new Executor();
        return existById(position.getId());
    }

    private boolean existById(Long id) throws SQLException {
        return executor.execQuery(result -> result.next(), "select * from position where id = ? ", id.toString());
    }

    public Position get(Long id) throws SQLException {
        return executor.execQuery(result -> {
            if (!result.next())
                return null;
            return new Position(result.getLong(EmployeesNC.PositionTable.ID), result.getString(EmployeesNC.PositionTable.NAME));
        }, "select * from position where id=?", id.toString());
    }

    public List<Position> getAll() throws SQLException {
        return executor.execQuery("select * from position order by NAME", result -> {
            List<Position> positions = new ArrayList<>();
            while (result.next()) {
                positions.add(new Position(result.getLong(EmployeesNC.PositionTable.ID), result.getString(EmployeesNC.PositionTable.NAME)));
            }
            return positions;
        });
    }

    public void delete(Long id) throws SQLException {
        executor.execUpdate("delete from position where id=?", id.toString());
    }

    public void delete(Position position) throws SQLException {
        executor.execUpdate("delete from position where id=?", position.getId().toString());
    }

    public void update(Position position) throws SQLException {
        executor.execUpdate("update position set name=? where id =?", position.getName(), position.getId().toString());
    }

    public void insert(Position position) throws SQLException {
        executor.execUpdate("insert into position (id, name) values (POSITION_SEQUENCE.nextval,?)", position.getName());
    }

    public void save(Position position) throws SQLException {
        if (isExist(position)) {
            executor = new Executor();
            update(position);
        } else {
            executor = new Executor();
            insert(position);
        }
    }
}