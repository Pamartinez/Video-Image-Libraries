package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import a6.C0419b;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class OnDemandFloatingViewHolder extends RecyclerView.ViewHolder {
    private Consumer<Integer> mOnItemClickListener;

    public OnDemandFloatingViewHolder(View view) {
        super(view);
        bindView(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bind$0(View view) {
        this.mOnItemClickListener.accept(Integer.valueOf(getLayoutPosition()));
    }

    public void bind(OnDemandFloatingItem onDemandFloatingItem) {
        this.itemView.setOnClickListener(new C0419b(0, this));
    }

    public abstract void bindView(View view);

    public void recycle() {
        this.itemView.setOnClickListener((View.OnClickListener) null);
    }

    public void setOnItemClickListener(Consumer<Integer> consumer) {
        this.mOnItemClickListener = consumer;
    }
}
