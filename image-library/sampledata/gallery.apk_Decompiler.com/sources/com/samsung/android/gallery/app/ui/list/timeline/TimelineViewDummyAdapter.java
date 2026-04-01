package com.samsung.android.gallery.app.ui.list.timeline;

import android.content.Context;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewDummyAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelineViewDummyAdapter extends PicturesViewDummyAdapter {
    public TimelineViewDummyAdapter(RecyclerView recyclerView, int i2) {
        super(recyclerView, i2);
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new TimelineViewHolderFactory(LayoutInflater.from(context).cloneInContext(context));
    }
}
