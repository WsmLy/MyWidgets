package teotw.com.mywidgets.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import teotw.com.mywidgets.R;
import teotw.com.mywidgets.widgets.ArcProgressCircle;

public class ArcProgressActivity extends AppCompatActivity {

    private ArcProgressCircle arcProgress;

    private int aqi = 67;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_progress);

        arcProgress = findViewById(R.id.apc_aqi);
        arcProgress.setProgress(getProgress(aqi));
        arcProgress.setCenterContent(String.valueOf(aqi));
        setUnit(aqi);
    }

    /**
     * 不规则进度算法
     */
    private int getProgress(int aqi) {
        int progress = 0;
        if (aqi <= 200) {
            progress = aqi * 100 / 300;
        } else if (aqi > 200 && aqi <= 300) {
            progress = 200 * 100 / 300 + (aqi - 200) * 100 / 600;
        } else if (aqi > 300) {
            progress = 200 * 100 / 300 + 100 * 100 / 600 + (aqi - 300) * 100 / 1200;
        }
        return progress;
    }

    private void setUnit(int aqi) {
//        String unit = WeatherTodayInfo.getInstance(C.get()).defineByAqiCount(aqi);
        StringBuilder sb = new StringBuilder();
//        if (unit.equals("优") || unit.equals("良")) {
//            sb.append("空气").append(unit);
            sb.append("空气").append("优");
//        } else {
//            sb.append(unit).append("污染");
//        }
        arcProgress.setCenterUnit(sb.toString());
    }
}
