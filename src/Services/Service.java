package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Service {
    private static String user = "root"; // Cambiar de ser necesario;
    private static String password = "Ale123ale"; // Cambiar de ser necesario;
    protected static String database = "Tidele_Alejandro_TP_Integrador";
    private static String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false&useUnicode=true", "localhost", 3306, database);
    private static Connection connection = null;

    static { // Se invoca automaticamente llamando a cualquier metodo estático.
        try {
            if (Service.connection == null) { // Singleton de conexión
                Class.forName("com.mysql.cj.jdbc.Driver");
                Service.connection = DriverManager.getConnection(url, user, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    protected static final String toDBString(String string) {
        return "'" + string + "'";
    }

    protected static final String toDBMD5(String string) {
        return "MD5('" + string + "')";
    }

    protected static final String createInsert(String table, String columns, String template) {
        return "INSERT INTO " + database + "." + table + " (" + columns + ") VALUES(" + template + ");";
    }

    protected static final void execInsert(String query) throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(query);
    }

    protected static final Integer execInsert(String query, Integer columnIndex) throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        return rs.getInt(columnIndex);
    }

    protected static final ResultSet execSelect(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    protected static final void execUpdate(String query) throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(query);
    }
}
