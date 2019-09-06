package teotw.com.mywidgets.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import teotw.com.mywidgets.R;
import teotw.com.mywidgets.adapters.TagAdapter;
import teotw.com.mywidgets.widgets.tagcloud.TagCloudView;

public class TagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        TagCloudView tagCloudView = findViewById(R.id.tag_cloud);
        List<TextView> data = new ArrayList<>();
        for (int i = 0; i < 30; i ++) {
            final TextView textView = new TextView(this);
            textView.setText(i + "asdf");
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(TagActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            data.add(textView);
        }
        tagCloudView.setAdapter(new TagAdapter(data));
    }
}
