package App.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Service {
    private static String user = "root"; // Cambiar de ser necesario;
    private static String password = "Ale123ale"; // Cambiar de ser necesario;
    private static String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false", "localhost", 3306, "veterinaria");
    private static Connection connection = null;
    protected static String database = "Tidele_Alejandro_TP_Integrador";

    static { // Se invoca automaticamente llamando a cualquier metodo estático.
        try {
            if (Service.connection == null) { // Singleton de conexión
                Class.forName("com.mysql.jdbc.Driver");
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

    protected static final Integer execInsert(String query, Boolean x) throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    protected static final ResultSet execSelect(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    /*
    public static final List<Pet> findAll(String table) {
		List<Pet> list = new ArrayList<Pet>();

		try {
			String query = String.format("SELECT * FROM veterinaria.%s", table);
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next())
				list.add(new Pet(rs.getInt("id"), rs.getString("Nombre"), rs.getInt("Edad"), rs.getString("Sexo")));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}

		return list;
	}

	public static final void insert(String table, List<String> columns, List<String> values) {
		try {
			if (columns.size() != values.size())
				throw new Exception("No coinciden las columnas con los valores");

			String query = String.format("INSERT INTO veterinaria.%s (", table);

			Integer i = 0;
			for (String column : columns)
				query += column + (++i == columns.size() ? ") VALUES (" : ", ");

			i = 0;
			for (String value : values)
				query += value + (++i == values.size() ? ");" : ", ");

			Statement st = connection.createStatement();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
	}

	public static final void delete(String table, int petId) {
		try {
			String query = String.format("DELETE FROM veterinaria.%s WHERE id = %d", table, petId);
			Statement st = connection.createStatement();
			st.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}
	}
    */
}
