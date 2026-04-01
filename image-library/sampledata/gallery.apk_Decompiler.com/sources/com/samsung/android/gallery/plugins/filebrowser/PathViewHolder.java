package com.samsung.android.gallery.plugins.filebrowser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PathViewHolder extends RecyclerView.ViewHolder {
    private Consumer<PathViewHolder> consumer;
    final TextView title;

    public PathViewHolder(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R$id.title);
        this.itemView.setOnClickListener(new d(3, this));
    }

    public static PathViewHolder create(ViewGroup viewGroup) {
        return new PathViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.recycler_item_path_layout, viewGroup, false));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        Consumer<PathViewHolder> consumer2 = this.consumer;
        if (consumer2 != null) {
            consumer2.accept(this);
        }
    }

    public void onBind(String str) {
        this.title.setText(str);
    }

    public PathViewHolder setOnClickListener(Consumer<PathViewHolder> consumer2) {
        this.consumer = consumer2;
        return this;
    }
}
