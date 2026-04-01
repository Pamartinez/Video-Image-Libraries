package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import U9.b;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandSuggestionItemAdapter extends RecyclerView.Adapter<OnDemandItemViewHolder> {
    private Consumer<String> mCallback;
    private final ArrayList<String> mItems = new ArrayList<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OnDemandItemViewHolder extends ListViewHolder {
        private Consumer<String> mCallback;
        private String mText = "";
        private TextView mTitleView;

        public OnDemandItemViewHolder(View view, int i2) {
            super(view, i2);
            this.itemView.setOnClickListener(new a(this, 0));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(View view) {
            Consumer<String> consumer = this.mCallback;
            if (consumer != null) {
                consumer.accept(this.mText);
            }
        }

        public void bind(String str) {
            super.bind(MediaItemBuilder.createEmpty());
            this.mText = str;
            this.mTitleView.setText(str);
            ViewUtils.setVisibleOrInvisible(this.itemView, !TextUtils.isEmpty(str));
        }

        public void bindView(View view) {
            this.mTitleView = (TextView) view.findViewById(R.id.title);
        }

        public void recycle() {
            super.recycle();
            this.mText = "";
            this.mTitleView.setText("");
        }

        public OnDemandItemViewHolder setOnItemClickListener(Consumer<String> consumer) {
            this.mCallback = consumer;
            return this;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$0(String str) {
        Consumer<String> consumer = this.mCallback;
        if (consumer != null) {
            consumer.accept(str);
        }
    }

    public String getItem(int i2) {
        if (i2 < this.mItems.size()) {
            return this.mItems.get(i2);
        }
        return "";
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public OnDemandItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new OnDemandItemViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_stories_category_on_demand_item_list_image, viewGroup, false), 0).setOnItemClickListener(new b(12, this));
    }

    public void setData(List<String> list) {
        this.mItems.clear();
        this.mItems.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(Consumer<String> consumer) {
        this.mCallback = consumer;
    }

    public void onBindViewHolder(OnDemandItemViewHolder onDemandItemViewHolder, int i2) {
        onDemandItemViewHolder.bind(getItem(i2));
    }

    public void onViewRecycled(OnDemandItemViewHolder onDemandItemViewHolder) {
        onDemandItemViewHolder.recycle();
    }
}
