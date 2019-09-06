package teotw.com.mywidgets.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import teotw.com.mywidgets.widgets.tagcloud.TagsAdapter;

/**
 * @author wsm on 2018/11/9
 * 文件描述：
 */
public class TagAdapter extends TagsAdapter {
    private List<TextView> data;
    public TagAdapter(List<TextView> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(Context context, int position, ViewGroup parent) {
        return data.get(position);
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 6;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        ((TextView)view).setTextColor(themeColor);
    }
}
