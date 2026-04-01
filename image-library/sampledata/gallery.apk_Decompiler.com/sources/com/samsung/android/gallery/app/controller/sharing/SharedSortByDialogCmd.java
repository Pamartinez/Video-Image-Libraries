package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedSortByDialogCmd extends BaseCommand {
    private void refreshAlbum() {
        MediaData mediaData = getHandler().getMediaData();
        if (mediaData != null) {
            mediaData.reopen(getHandler().getLocationKey());
        }
    }

    /* access modifiers changed from: private */
    public void setSortBy(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            Object[] objArr = (Object[]) arrayList.get(0);
            GalleryPreference.getInstance().saveSharedSortBy(MediaItemMde.getSpaceId(eventContext.getHeaderItem()), ((Integer) objArr[1]).intValue());
            if (((Integer) objArr[0]).intValue() != ((Integer) objArr[1]).intValue()) {
                refreshAlbum();
            }
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_SHARED_VIEW_DIALOG_SORT_BY.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        getBlackboard().publish("data://albums/current", eventContext.getHeaderItem());
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData("data://user/dialog/SharedSortBy").setOnDataCompleteListener(new y(23, this)).start();
    }
}
