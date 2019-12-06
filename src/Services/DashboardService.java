package Services;

import Utils.Helper;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DashboardService extends Service {
    public static Integer getUsersCount(String from, String to) {
        String between = "";

        if (from != null && to != null) {
            from = toDBString(Helper.getPretty(from, false));
            to = toDBString(Helper.getPretty(to, false));
            between = " AND register_date BETWEEN " + from + " AND " + to;
        }
        Integer count = 0;

        try {
            String query = "SELECT COUNT(*) count FROM users WHERE userTypeId = 3" + between;
            ResultSet rs = execSelect(query);
            rs.next();
            count = rs.getInt("count");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static Integer getCoursesCount(String from, String to) {
        String between = "";

        if (from != null && to != null) {
            from = toDBString(Helper.getPretty(from, false));
            to = toDBString(Helper.getPretty(to, false));
            between = " AND year BETWEEN DATE_FORMAT(" + from + ", '%Y') AND DATE_FORMAT(" + to + ", '%Y')";
        }
        Integer count = 0;

        try {
            String query = "SELECT COUNT(*) count FROM courses WHERE status = 'A'" + between;
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
            String query = "SELECT MAX((gradeP1 + gradeP2) / 2) bestAvg FROM studentsPerCourses";
            ResultSet rs = execSelect(query);
            rs.next();
            count = rs.getDouble("bestAvg");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
