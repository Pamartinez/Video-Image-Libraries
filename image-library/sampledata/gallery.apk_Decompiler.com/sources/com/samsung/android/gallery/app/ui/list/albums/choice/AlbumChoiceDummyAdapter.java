package com.samsung.android.gallery.app.ui.list.albums.choice;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumChoiceDummyAdapter extends AlbumsViewDummyAdapter {
    private boolean mEnableBgRipple = true;

    public AlbumChoiceDummyAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    private boolean isGridView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof GridLayoutManager) || ((GridLayoutManager) layoutManager).getSpanCount() <= 1) {
            return false;
        }
        return true;
    }

    public AlbumsChoiceViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
        return new AlbumsChoiceViewHolderFactory(context, isGridView(recyclerView));
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        ListViewHolder onCreateViewHolder = super.onCreateViewHolder(viewGroup, i2);
        if (!this.mEnableBgRipple) {
            onCreateViewHolder.getRootView().setBackground((Drawable) null);
        }
        return onCreateViewHolder;
    }
}
