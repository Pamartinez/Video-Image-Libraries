package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortBySharingDialogCmd extends BaseCommand {
    private void refreshData() {
        MediaData mediaData = getHandler().getMediaData();
        if (mediaData != null) {
            mediaData.reopen(getHandler().getLocationKey());
        }
    }

    /* access modifiers changed from: private */
    public void setSortBy(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            Object[] objArr = (Object[]) arrayList.get(0);
            GalleryPreference instance = GalleryPreference.getInstance();
            int sortBy = SortByType.getSortBy(((Integer) objArr[0]).intValue());
            int orderBy = SortByType.getOrderBy(((Integer) objArr[0]).intValue());
            int sortBy2 = SortByType.getSortBy(((Integer) objArr[1]).intValue());
            int orderBy2 = SortByType.getOrderBy(((Integer) objArr[1]).intValue());
            instance.saveState(PreferenceName.SORT_BY_SHARING, ((Integer) objArr[1]).intValue());
            if (sortBy != sortBy2 || orderBy != orderBy2) {
                if (Features.isEnabled(Features.SUPPORT_SORT_SHARINGS)) {
                    new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_SORT_SHARINGS, String.valueOf(objArr[1]));
                } else {
                    refreshData();
                }
            }
        }
    }

    public String getEventId() {
        return null;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData("data://user/dialog/SortBySharing").setOnDataCompleteListener(new y(25, this)).start();
    }
}
