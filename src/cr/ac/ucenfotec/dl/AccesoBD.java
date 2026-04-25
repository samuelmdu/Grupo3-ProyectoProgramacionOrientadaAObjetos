package cr.ac.ucenfotec.dl;

import java.sql.*;

public class AccesoBD {

    private Connection conexion;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public AccesoBD(String direccion, String usuario, String contrasenia) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(direccion, usuario, contrasenia);
    }

    public void ejecutarStatement(String statement) throws SQLException {
        this.statement = conexion.createStatement();
        this.statement.executeUpdate(statement);
    }

    public void ejecutarStatement(String statement, String param1, int param2) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setString(1, param1);
        preparedStatement.setInt(2, param2);
        preparedStatement.executeUpdate();
    }

    public void ejecutarStatement(String statement, int id) throws SQLException {
        preparedStatement = conexion.prepareStatement(statement);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public ResultSet ejecutarQuery(String query, String correo_electronico, String contrasenia) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, correo_electronico);
        preparedStatement.setString(2, contrasenia);
        return preparedStatement.executeQuery();
    }
    // Buscar usuario moderador
    public ResultSet ejecutarQuery(String query) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
    // Imprimir objetos
    public ResultSet ejecutarQuery(String query, String correoCreador) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, correoCreador);
        return preparedStatement.executeQuery();
    }

    // Buscar objeto x id

    public ResultSet ejecutarQuery(String query, int id) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public ResultSet ejecutarQuery(String query, int id, String correoElctronico) throws SQLException {
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, correoElctronico);
        return preparedStatement.executeQuery();
    }
}
