package com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IStoryHighlightView extends IMvpBaseView {
    EventHandler getEventHandler();

    ArrayList<MediaItem> getExportItems();

    MediaItem getHeaderItem() {
        return null;
    }

    MediaData getMediaData();

    Options getOptions();

    MvpBasePresenter getPresenter();

    MediaData getStoriesData() {
        return null;
    }

    void handlePostEvent(Event event, Object... objArr);

    boolean isBackPressed();

    void onDataChangedOnUi();

    Object requestData(DataRequest dataRequest, Object... objArr);
}
