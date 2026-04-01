package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import U5.b;
import a6.i;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandFloatingItemAdapter extends RecyclerView.Adapter<OnDemandFloatingViewHolder> {
    private List<OnDemandFloatingItem> mItems = new ArrayList();
    private FloatingItemClickListener mOnItemClickListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FloatingItemClickListener {
    }

    private OnDemandFloatingItem getItem(int i2) {
        if (this.mItems.isEmpty()) {
            return null;
        }
        return this.mItems.get(i2);
    }

    /* access modifiers changed from: private */
    public void lambda$onCreateViewHolder$0(Integer num, FloatingItemClickListener floatingItemClickListener) {
        ((i) floatingItemClickListener).f2476a.lambda$setData$1(num.intValue(), getItem(num.intValue()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$1(Integer num) {
        Optional.ofNullable(this.mOnItemClickListener).ifPresent(new b(12, this, num));
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public boolean isEmpty() {
        return this.mItems.isEmpty();
    }

    public void setData(List<?> list) {
        this.mItems = list;
    }

    public void setOnItemClickListener(FloatingItemClickListener floatingItemClickListener) {
        this.mOnItemClickListener = floatingItemClickListener;
    }

    public void onBindViewHolder(OnDemandFloatingViewHolder onDemandFloatingViewHolder, int i2) {
        OnDemandFloatingItem item = getItem(i2);
        if (item != null) {
            onDemandFloatingViewHolder.bind(item);
        }
    }

    public OnDemandFloatingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        OnDemandFloatingViewHolder createViewHolder = OnDemandFloatingViewHolderFactory.createViewHolder(viewGroup, i2);
        createViewHolder.setOnItemClickListener(new U9.b(22, this));
        return createViewHolder;
    }

    public void onViewRecycled(OnDemandFloatingViewHolder onDemandFloatingViewHolder) {
        onDemandFloatingViewHolder.recycle();
        super.onViewRecycled(onDemandFloatingViewHolder);
    }
}
