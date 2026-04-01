package com.samsung.android.gallery.app.ui.list.picker.timeline;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerAdapter;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineViewHolderFactory;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimelinePickerAdapter<V extends IPicturesView> extends PicturesPickerAdapter<V> {
    public TimelinePickerAdapter(V v, String str, boolean z) {
        super(v, str, (View) null, z);
    }

    public boolean checkVisualCueItem(MediaItem mediaItem) {
        return false;
    }

    public PicturesViewHolderFactory createViewHolderFactory(Context context) {
        return new TimelineViewHolderFactory(context);
    }

    public boolean isCheckingTargetCluster() {
        return ((IPicturesView) this.mView).isCheckingTargetCluster();
    }
}
