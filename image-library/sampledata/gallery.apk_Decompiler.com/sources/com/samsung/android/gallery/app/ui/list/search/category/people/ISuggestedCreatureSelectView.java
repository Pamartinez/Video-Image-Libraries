package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.view.MenuItem;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISuggestedCreatureSelectView extends ICreatureSelectView {
    MediaItem[] getHeaderItems();

    int getHeaderListColumnSize();

    boolean onMenuItemSelected(MenuItem menuItem);

    void setLocationKey(String str);

    void updateSetAsRelationMenu();
}
