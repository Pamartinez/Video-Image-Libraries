package com.samsung.android.gallery.app.ui.list.trash.container;

import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ITrashContainerView extends IMvpBaseView {
    GalleryAppBarLayout getAppbarLayout();

    FloatingToolbarLayout getFloatingToolbarLayout();

    int getTabLayoutHeight();

    GalleryToolbar getToolbar();

    void onSelectionModeChanged(boolean z);

    void startShrinkAnimation();
}
