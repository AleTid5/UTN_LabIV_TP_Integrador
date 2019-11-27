package Services;

import Models.Location;
import Models.Province;

import java.sql.ResultSet;
import java.util.ArrayList;

public class GeoLocationService extends Service {
    public static final ArrayList<Province> getProvinces() {
        ArrayList<Province> provinces = new ArrayList<>();

        try {
            String query = "SELECT * FROM %s.%s";
            query = String.format(query, database, "provinces");
            ResultSet rs = execSelect(query);
            while (rs.next()) {
                provinces.add(new Province(rs.getInt("id"), rs.getString("name"), rs.getString("status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return provinces;
    }

    public static final ArrayList<Location> getLocations() {
        ArrayList<Location> locations = new ArrayList<>();

        try {
            String query = "SELECT * FROM %s.%s";
            query = String.format(query, database, "locations");
            ResultSet rs = execSelect(query);
            while (rs.next()) {
                locations.add(new Location(rs.getInt("id"), rs.getString("name"), rs.getString("status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return locations;
    }
}
