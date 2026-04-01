package com.samsung.android.gallery.plugins.filebrowser;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileListAdapter extends RecyclerView.Adapter<FileViewHolder> {
    Consumer<FileViewHolder> clickListener;
    List<FileInfo> list = new ArrayList();
    Consumer<FileViewHolder> longClickListener;
    final Consumer<FileViewHolder> mOnClickListener = new g(this, 0);
    final Consumer<FileViewHolder> mOnLongClickListener = new g(this, 1);

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(FileViewHolder fileViewHolder) {
        Consumer<FileViewHolder> consumer = this.clickListener;
        if (consumer != null) {
            consumer.accept(fileViewHolder);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(FileViewHolder fileViewHolder) {
        Consumer<FileViewHolder> consumer = this.longClickListener;
        if (consumer != null) {
            consumer.accept(fileViewHolder);
        }
    }

    public int getItemCount() {
        return this.list.size();
    }

    public FileListAdapter setClickListener(Consumer<FileViewHolder> consumer) {
        this.clickListener = consumer;
        return this;
    }

    public FileListAdapter setLongClickListener(Consumer<FileViewHolder> consumer) {
        this.longClickListener = consumer;
        return this;
    }

    public void update(List<FileInfo> list2) {
        this.list = list2;
        notifyDataSetChanged();
    }

    public void onBindViewHolder(FileViewHolder fileViewHolder, int i2) {
        fileViewHolder.onBind(this.list.get(i2));
    }

    public FileViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return FileViewHolder.create(viewGroup).setOnItemClickListener(this.mOnClickListener).setOnItemLongClickListener(this.mOnLongClickListener);
    }

    public void onViewRecycled(FileViewHolder fileViewHolder) {
        fileViewHolder.onRecycle();
    }
}
