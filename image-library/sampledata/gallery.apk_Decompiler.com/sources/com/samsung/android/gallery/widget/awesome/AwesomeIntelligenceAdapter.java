package com.samsung.android.gallery.widget.awesome;

import Ba.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AwesomeIntelligenceAdapter extends RecyclerView.Adapter<AwesomeItemViewHolder> {
    private static final int ITEM_LAYOUT_ID = R$layout.awesome_intelligence_dialog_item;
    private Consumer<Void> mClickListener;
    private final ArrayList<IAwesomeItem> mItems;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AwesomeItemViewHolder extends RecyclerView.ViewHolder {
        public AwesomeItemViewHolder(View view) {
            super(view);
        }

        public void update(IAwesomeItem iAwesomeItem) {
            ((ImageView) this.itemView.findViewById(R$id.icon)).setImageResource(iAwesomeItem.getType().drawableResId);
            ((TextView) this.itemView.findViewById(R$id.title)).setText(iAwesomeItem.getType().titleResId);
        }
    }

    public AwesomeIntelligenceAdapter(ArrayList<IAwesomeItem> arrayList) {
        this.mItems = arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateViewHolder$0(AwesomeItemViewHolder awesomeItemViewHolder, View view) {
        this.mItems.get(awesomeItemViewHolder.getBindingAdapterPosition()).onMenuSelect(false);
        Consumer<Void> consumer = this.mClickListener;
        if (consumer != null) {
            consumer.accept((Object) null);
        }
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public void setOnItemClickListener(Consumer<Void> consumer) {
        this.mClickListener = consumer;
    }

    public void onBindViewHolder(AwesomeItemViewHolder awesomeItemViewHolder, int i2) {
        awesomeItemViewHolder.update(this.mItems.get(i2));
    }

    public AwesomeItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        AwesomeItemViewHolder awesomeItemViewHolder = new AwesomeItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(ITEM_LAYOUT_ID, viewGroup, false));
        awesomeItemViewHolder.itemView.setOnClickListener(new g(24, this, awesomeItemViewHolder));
        return awesomeItemViewHolder;
    }
}
