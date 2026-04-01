package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ILastPageView {
    String getEditedTitle();

    IStoryHighlightView getParent();

    void hide(boolean z);

    void updateDataAndUi();
}
