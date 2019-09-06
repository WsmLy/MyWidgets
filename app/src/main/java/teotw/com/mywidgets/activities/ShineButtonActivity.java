package teotw.com.mywidgets.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import teotw.com.mywidgets.R;
import teotw.com.mywidgets.widgets.fabshine.ShineButton;

/**
 * created by samwsm at 2019-09-06 22:34
 * update by samwsm at 2019-09-06 22:34
 * updateDetail :
 */
public class ShineButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shine_button);
        final ShineButton shineButton = findViewById(R.id.shine_button);
        shineButton.init(this);
        shineButton.setBackgroundResource(R.mipmap.ic_launcher);
        shineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shineButton.showAnim();
            }
        });
    }
}
