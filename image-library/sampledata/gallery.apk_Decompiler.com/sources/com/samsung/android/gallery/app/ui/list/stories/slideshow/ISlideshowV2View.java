package com.samsung.android.gallery.app.ui.list.stories.slideshow;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISlideshowV2View extends IStoryHighlightView {
    boolean fromSelection();

    String getBgmName();

    String getFilterName();

    Order getSortType();

    boolean supportReordering();
}
