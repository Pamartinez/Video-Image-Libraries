package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsViewDummyAdapter extends RecyclerView.Adapter<ListViewHolder> {
    final AlbumsViewHolderFactory mViewHolderFactory;

    public AlbumsViewDummyAdapter(RecyclerView recyclerView) {
        this.mViewHolderFactory = createViewHolderFactory(recyclerView, recyclerView.getContext());
    }

    public AlbumsViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
        return new AlbumsViewHolderFactory(context);
    }

    public int getItemCount() {
        throw new AssertionError("use this adapter only for create view holder");
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        throw new AssertionError("use this adapter only for create view holder");
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createViewHolder(viewGroup, i2);
    }
}
