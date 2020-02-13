package teotw.com.mywidgets.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import teotw.com.mywidgets.R;
import teotw.com.mywidgets.adapters.DoubleListViewAdapter;
import teotw.com.mywidgets.widgets.doublerecyclerview.DoubleRecyclerView;

public class DoubleRecyclerActivity extends AppCompatActivity {
    private DoubleRecyclerView doubleRecyclerView;
    private DoubleListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_recycler);
        doubleRecyclerView = findViewById(R.id.double_list);
        doubleRecyclerView.setTitleLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        doubleRecyclerView.setItemLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new DoubleListViewAdapter(this);
        doubleRecyclerView.setAdapter(adapter);
    }
}
