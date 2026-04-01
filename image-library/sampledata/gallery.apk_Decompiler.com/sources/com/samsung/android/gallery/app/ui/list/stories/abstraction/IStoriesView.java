package com.samsung.android.gallery.app.ui.list.stories.abstraction;

import android.graphics.PointF;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IStoriesView extends IStoriesBaseView {
    void createPinPopupMenu(ViewGroup viewGroup, PointF pointF, MediaItem mediaItem);

    IPinView getPinView();

    boolean isDrawerSliding();

    void onListItemMenuClick(View view, PointF pointF, MediaItem mediaItem);

    boolean onOptionsItemMenuSelected(MenuItem menuItem);

    boolean supportLongClick() {
        return true;
    }

    boolean supportPinSelection();
}
