package com.samsung.android.gallery.app.controller.album;

import Fb.h;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortByAlbumDialogCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public void setSortBy(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            Object[] objArr = (Object[]) arrayList.get(0);
            int sortBy = SortByType.getSortBy(((Integer) objArr[0]).intValue());
            int orderBy = SortByType.getOrderBy(((Integer) objArr[0]).intValue());
            int sortBy2 = SortByType.getSortBy(((Integer) objArr[1]).intValue());
            int orderBy2 = SortByType.getOrderBy(((Integer) objArr[1]).intValue());
            GalleryPreference.getInstance().saveState(PreferenceName.SORT_BY_ALBUM, ((Integer) objArr[1]).intValue());
            if (sortBy != sortBy2 || orderBy != orderBy2) {
                getBlackboard().postEvent(EventMessage.obtain(104));
            }
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_ALBUM_SORT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData("data://user/dialog/SortByAlbum").setOnDataCompleteListener(new h(12, this)).start();
    }
}
