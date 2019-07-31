package teotw.com.mywidgets;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.setContext(getApplicationContext());
    }
}
