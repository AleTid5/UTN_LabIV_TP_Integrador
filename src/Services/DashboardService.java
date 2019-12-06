package Services;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DashboardService extends Service {
    public static Integer getUsersCount() {
        Integer count = 0;

        try {
            String query = "SELECT COUNT(*) count FROM users WHERE userTypeId = 3";
            ResultSet rs = execSelect(query);
            rs.next();
            count = rs.getInt("count");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static Integer getCoursesCount() {
        Integer count = 0;

        try {
            String query = "SELECT COUNT(*) count FROM courses WHERE status = 'A'";
            ResultSet rs = execSelect(query);
            rs.next();
            count = rs.getInt("count");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static Double getBestAverage() {
        Double count = 0.0;

        try {
            String query = "SELECT MAX((gradeP1 + gradeP2) / 2) bestAvg FROM studentsPerCourses;";
            ResultSet rs = execSelect(query);
            rs.next();
            count = rs.getDouble("bestAvg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
