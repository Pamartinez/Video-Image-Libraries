package com.samsung.android.gallery.app.ui.list.stories.header;

import com.samsung.android.gallery.module.dataset.MediaData;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PinModelListener extends MediaData.SimpleDataChangeListener {
    public abstract void onDataChanged();

    public abstract void onPinDataDirty(ArrayList<Integer> arrayList);
}
