package com.samsung.android.gallery.app.ui.list.search.keyword;

import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IKeywordResultView extends ISearchPicturesView {
    void setContainerVisibility(boolean z);

    void setPendingLayoutChange();

    void storyDataLoaded(String str);

    boolean supportMenu();
}
