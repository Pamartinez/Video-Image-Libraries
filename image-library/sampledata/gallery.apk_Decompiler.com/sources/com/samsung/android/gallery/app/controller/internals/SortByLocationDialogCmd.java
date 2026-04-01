package com.samsung.android.gallery.app.controller.internals;

import K4.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortByLocationDialogCmd extends BaseCommand {
    private void reopenData() {
        MediaData mediaData = getHandler().getMediaData();
        if (mediaData != null) {
            mediaData.reopen(getHandler().getLocationKey());
        }
    }

    private void saveSortBy(int i2) {
        GalleryPreference.getInstance().saveState(PreferenceName.LOCATION_SORT_BY, i2);
    }

    /* access modifiers changed from: private */
    public void updateSortBy(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null) {
            Log.d(this.TAG, "data is null!!");
            return;
        }
        saveSortBy(((Integer) arrayList.get(0)).intValue());
        reopenData();
    }

    public String getEventId() {
        return null;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData("data://user/dialog/LocationSortBy").setOnDataCompleteListener(new a(29, this)).start();
    }
}
