package teotw.com.mywidgets.widgets.doublerecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * created by samwsm at 2020-01-01 15:43
 * update by samwsm at 2020-01-01 15:43
 * updateDetail :
 */
public abstract class DoubleRecyclerViewAdapter<T extends RecyclerView.ViewHolder, K extends RecyclerView.ViewHolder> {
    private Context context;
    private RecyclerView.Adapter titleAdapter;
    private RecyclerView.Adapter itemAdapter;

    public DoubleRecyclerViewAdapter(Context context) {
        this.context = context;
        setTitleAdapter();
        setItemAdapter();
    }

    /**
     * title相关的
     * @param viewGroup
     * @param i
     * @return
     */
    public abstract T onCreateTitleViewHolder(ViewGroup viewGroup, int i);

    public abstract int getTitleCount();

    public abstract void onBindTitleViewHolder();

    private void setTitleAdapter() {
        titleAdapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return onCreateTitleViewHolder(viewGroup, i);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                onBindTitleViewHolder();
            }

            @Override
            public int getItemCount() {
                return getTitleCount();
            }
        };
    }

    public RecyclerView.Adapter getTitleAdapter() {
        return titleAdapter;
    }

    public RecyclerView.Adapter getItemAdapter() {
        return itemAdapter;
    }

    public void notifyTitleDataSetChanged() {
        titleAdapter.notifyDataSetChanged();
    }

    public void notifyTitleItemDataSetChanged(int position) {
        titleAdapter.notifyItemChanged(position);
    }

    public void notifyTitleItemDataRemoveChanged(int position) {
        titleAdapter.notifyItemRemoved(position);
    }

    public abstract K onCreateItemViewHolder(ViewGroup viewGroup, int i);

    public abstract int getItemCount();

    public abstract void onBindItemViewHolder();

    private void setItemAdapter() {
        itemAdapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return onCreateItemViewHolder(viewGroup, i);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                onBindItemViewHolder();
            }

            @Override
            public int getItemCount() {
                return DoubleRecyclerViewAdapter.this.getItemCount();
            }
        };
    }

    public void notifyDataSetChanged() {
        titleAdapter.notifyDataSetChanged();
        itemAdapter.notifyDataSetChanged();
    }

    public void notifyItemDataSetChanged(int position) {
        titleAdapter.notifyItemChanged(position);
        itemAdapter.notifyItemChanged(position);
    }

    public void notifyItemDataRemoveChanged(int position) {
        titleAdapter.notifyItemRemoved(position);
        itemAdapter.notifyItemRemoved(position);
    }
}
