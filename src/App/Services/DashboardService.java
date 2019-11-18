package App.Services;

import java.util.ArrayList;

public class DashboardService extends Service {
    public static final ArrayList<Double> getData(Integer docket) {
        ArrayList<Double> dashboardData = new ArrayList<>();
        Double n1 = 10.0;
        Double n2 = 25.0;
        Double n3 = 5.4;

        dashboardData.add(n1);
        dashboardData.add(n2);
        dashboardData.add(n3);

        return dashboardData;
    }
}
