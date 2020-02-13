package teotw.com.mywidgets.widgets.doublerecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * created by samwsm at 2020-01-01 15:34
 * update by samwsm at 2020-01-01 15:34
 * updateDetail :
 */
public class DoubleRecyclerView extends LinearLayout {
    private Context context;
    private RecyclerView titleView;
    private RecyclerView itemView;

    public DoubleRecyclerView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public DoubleRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public DoubleRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {

        titleView = new RecyclerView(context);
        addView(titleView);

        itemView = new RecyclerView(context);
        addView(itemView);

    }

    public void setOritation(int oritation) {
        setOritation(oritation, LinearLayoutManager.VERTICAL, LinearLayoutManager.VERTICAL);
    }

    public void setOritation(int titleOritation, int itemOritation) {
        setOritation(VERTICAL, titleOritation, itemOritation);
    }

    public void setOritation(int oritation, int titleOritation, int itemOritation) {
        setOrientation(oritation);

        titleView.setLayoutManager(new LinearLayoutManager(context, titleOritation, false));

        itemView.setLayoutManager(new LinearLayoutManager(context, itemOritation, false));
    }

    public void setTitleLayoutManager(RecyclerView.LayoutManager titleLayoutManager) {
        titleView.setLayoutManager(titleLayoutManager);
    }

    public void setItemLayoutManager(RecyclerView.LayoutManager itemLayoutManager) {
        itemView.setLayoutManager(itemLayoutManager);
    }

    public void setAdapter(DoubleRecyclerViewAdapter adapter) {
        titleView.setAdapter(adapter.getTitleAdapter());
        itemView.setAdapter(adapter.getItemAdapter());
    }
}
