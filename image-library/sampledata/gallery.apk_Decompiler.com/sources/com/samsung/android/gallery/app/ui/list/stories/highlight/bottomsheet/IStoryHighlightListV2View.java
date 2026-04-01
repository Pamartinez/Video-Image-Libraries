package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IStoryHighlightListV2View extends IPicturesView {
    StoryHighlightListV2Adapter<IStoryHighlightListV2View> getAdapter();

    String getDataLocationKey();

    EventHandler getEventHandler();

    void invalidateSelectionToolbar();

    void onSheetSlide(float f);

    void setEventHandler(EventHandler eventHandler);
}
