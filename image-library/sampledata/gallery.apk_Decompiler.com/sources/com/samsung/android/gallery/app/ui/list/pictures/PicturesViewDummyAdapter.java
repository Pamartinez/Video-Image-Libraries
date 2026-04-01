package com.samsung.android.gallery.app.ui.list.pictures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.abstraction.ContextThemeWrapperCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesViewDummyAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private final int mGridSize;
    private PicturesViewHolderFactory mViewHolderFactory;

    public PicturesViewDummyAdapter(RecyclerView recyclerView, int i2) {
        this.mGridSize = i2;
        Context context = recyclerView.getContext();
        this.mViewHolderFactory = createViewHolderFactory(new ContextThemeWrapperCompat(context, context.getTheme()));
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new PicturesViewHolderFactory(LayoutInflater.from(context).cloneInContext(context));
    }

    public int getItemCount() {
        throw new AssertionError("use this adapter only for create view holder");
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        throw new AssertionError("use this adapter only for create view holder");
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createListViewHolder(viewGroup, i2, this.mGridSize);
    }
}
