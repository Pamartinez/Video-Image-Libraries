package com.samsung.android.gallery.app.ui.list.sharings.choice;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsViewHolderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingAlbumChoiceDummyAdapter extends AlbumsViewDummyAdapter {
    public SharingAlbumChoiceDummyAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public AlbumsViewHolderFactory createViewHolderFactory(RecyclerView recyclerView, Context context) {
        return new SharingChoiceViewHolderFactory(context);
    }
}
