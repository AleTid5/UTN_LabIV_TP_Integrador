package Services;

import Models.Subject;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SubjectService extends Service {
    private static final String table = "subjects";

    public static final ArrayList<Subject> list() {
        ArrayList<Subject> subjects = new ArrayList<>();

        try {
            String query = "SELECT * FROM %s.%s";
            query = String.format(query, database, table);
            ResultSet rs = execSelect(query);
            while (rs.next()) {
                Subject subject = new Subject(rs.getInt("id"), rs.getString("name"));
                subject.setStatus(rs.getString("status"));
                subjects.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }
}
