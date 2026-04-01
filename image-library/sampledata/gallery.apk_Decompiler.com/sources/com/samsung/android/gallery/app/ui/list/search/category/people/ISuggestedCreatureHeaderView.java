package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISuggestedCreatureHeaderView {
    View get();

    MediaItem[] getAllItems();

    void initView();

    void onDataChangedOnUI();

    void onListItemClick(int i2);

    void updateListColumn();
}
