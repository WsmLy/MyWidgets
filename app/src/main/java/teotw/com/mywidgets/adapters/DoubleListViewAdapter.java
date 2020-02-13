package teotw.com.mywidgets.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import teotw.com.mywidgets.R;
import teotw.com.mywidgets.widgets.doublerecyclerview.DoubleRecyclerViewAdapter;

/**
 * Created by sam on 2020/2/13.
 */

public class DoubleListViewAdapter extends DoubleRecyclerViewAdapter<DoubleListViewAdapter.TitleHolder, DoubleListViewAdapter.ItemHolder> {
    private Context context;
    public DoubleListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TitleHolder onCreateTitleViewHolder(ViewGroup viewGroup, int i) {
        return new TitleHolder(LayoutInflater.from(context).inflate(R.layout.item_double_list_title, viewGroup, false));
    }

    @Override
    public int getTitleCount() {
        return 20;
    }

    @Override
    public void onBindTitleViewHolder() {

    }

    @Override
    public ItemHolder onCreateItemViewHolder(ViewGroup viewGroup, int i) {
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_double_list_item, viewGroup, false));
    }

    @Override
    public int getItemCount() {
        return 40;
    }

    @Override
    public void onBindItemViewHolder() {

    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ItemHolder(View itemView) {
            super(itemView);
        }
    }

    public class TitleHolder extends RecyclerView.ViewHolder {
        public TitleHolder(View itemView) {
            super(itemView);
        }
    }
}
