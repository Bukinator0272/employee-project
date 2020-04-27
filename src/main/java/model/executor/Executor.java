package model.executor;

import model.connection.PoolConnectionBuilder;

import java.sql.*;

public class Executor {

    private final Connection connection;

    public Executor() throws SQLException {
        this.connection = new PoolConnectionBuilder().getConnection();
    }

    public void execUpdate(String update) throws SQLException {
        try (
                Statement stmt = connection.createStatement()) {
            stmt.execute(update);
        } finally {
            connection.close();
        }
    }

    public void execUpdate(String query, String... args) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            setArgs(pstmt, args);
            pstmt.executeUpdate();
            connection.setAutoCommit(false);
            connection.commit();
            connection.setAutoCommit(true);
        } finally {
            connection.close();
        }
    }

    public <T> T execUpdate(ResultHandler<T> handler, String query, String... args) throws SQLException {
        T value;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            setArgs(statement, args);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys(); //////////////////////////////////////////
            value = handler.handle(result);
            result.close();
        } finally {
            connection.close();
        }
        return value;
    }

    private void setArgs(PreparedStatement statement, String[] args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            statement.setString(i + 1, args[i]);
        }
    }

    public <T> T execQuery(String query, ResultHandler<T> handler) throws SQLException {
        T value;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
            ResultSet result = stmt.getResultSet();
            value = handler.handle(result);
            result.close();
        } finally {
            connection.close();
        }
        return value;
    }

    public <T> T execQuery(ResultHandler<T> handler, String query, String... args) throws SQLException {
        T value;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setArgs(stmt, args);
            stmt.execute();
            ResultSet result = stmt.getResultSet();
            value = handler.handle(result);
            result.close();
            connection.setAutoCommit(false);
            connection.commit();
            connection.setAutoCommit(true);
        } finally {
            connection.close();
        }
        return value;
    }
}