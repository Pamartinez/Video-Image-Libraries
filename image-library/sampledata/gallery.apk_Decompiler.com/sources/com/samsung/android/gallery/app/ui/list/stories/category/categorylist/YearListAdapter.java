package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class YearListAdapter extends RecyclerView.Adapter<YearItemViewHolder> {
    private Consumer<Integer> mClickConsumer;
    private boolean mClickable = true;
    private int mFocusedPosition;
    private final ArrayList<Integer> mItems;

    public YearListAdapter(ArrayList<Integer> arrayList) {
        this.mItems = arrayList;
    }

    private int getItemResId() {
        return R.layout.stories_category_year_list_item_layout;
    }

    private YearItemViewHolder getItemView(RecyclerView recyclerView, int i2) {
        return (YearItemViewHolder) recyclerView.findViewHolderForAdapterPosition(i2);
    }

    /* access modifiers changed from: private */
    public void onSelect(YearItemViewHolder yearItemViewHolder) {
        int absoluteAdapterPosition;
        int i2;
        if (this.mClickConsumer != null && this.mClickable && (absoluteAdapterPosition = yearItemViewHolder.getAbsoluteAdapterPosition()) != (i2 = this.mFocusedPosition)) {
            notifyItemChanged(i2, "deselect");
            this.mFocusedPosition = absoluteAdapterPosition;
            updateItemBackground(yearItemViewHolder);
            this.mClickConsumer.accept(Integer.valueOf(yearItemViewHolder.getYear()));
        }
    }

    private void updateItemBackground(YearItemViewHolder yearItemViewHolder) {
        boolean z;
        if (yearItemViewHolder != null) {
            if (yearItemViewHolder.getAbsoluteAdapterPosition() == this.mFocusedPosition) {
                z = true;
            } else {
                z = false;
            }
            yearItemViewHolder.updateTitleContainer(z);
        }
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public boolean isShowAll() {
        if (this.mFocusedPosition == 0) {
            return true;
        }
        return false;
    }

    public void resetFilter(RecyclerView recyclerView) {
        setFocus(recyclerView, 0);
    }

    public void scrollToFocusedPosition(RecyclerView recyclerView) {
        recyclerView.scrollToPosition(this.mFocusedPosition);
    }

    public void setClickable(boolean z) {
        this.mClickable = z;
    }

    public void setFocus(RecyclerView recyclerView, int i2) {
        int i7;
        int i8 = this.mFocusedPosition;
        if (i2 <= 0) {
            i7 = 0;
        } else {
            i7 = this.mItems.indexOf(Integer.valueOf(i2));
        }
        this.mFocusedPosition = i7;
        if (i8 != i7) {
            recyclerView.scrollToPosition(i7);
            Optional.ofNullable(this.mClickConsumer).ifPresent(new e(i2));
            updateItemBackground(getItemView(recyclerView, this.mFocusedPosition));
            updateItemBackground(getItemView(recyclerView, i8));
        }
    }

    public void setOnClickListener(Consumer<Integer> consumer) {
        this.mClickConsumer = consumer;
    }

    public void update(ArrayList<Integer> arrayList) {
        this.mItems.clear();
        this.mItems.addAll(arrayList);
    }

    public YearItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new YearItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemResId(), viewGroup, false));
    }

    public void onViewAttachedToWindow(YearItemViewHolder yearItemViewHolder) {
        super.onViewAttachedToWindow(yearItemViewHolder);
        updateItemBackground(yearItemViewHolder);
    }

    public void onViewRecycled(YearItemViewHolder yearItemViewHolder) {
        yearItemViewHolder.recycle();
    }

    public void onBindViewHolder(YearItemViewHolder yearItemViewHolder, int i2) {
        yearItemViewHolder.setTitle(this.mItems.get(i2).intValue());
        yearItemViewHolder.setOnClickListener(new d(1, this));
    }

    public void onBindViewHolder(YearItemViewHolder yearItemViewHolder, int i2, List<Object> list) {
        if (list.contains("deselect")) {
            yearItemViewHolder.updateTitleContainer(false);
            return;
        }
        yearItemViewHolder.setOnClickListener(new d(1, this));
        super.onBindViewHolder(yearItemViewHolder, i2, list);
    }
}
