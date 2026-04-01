package com.samsung.android.gallery.app.controller.album;

import Fb.h;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SortByDialogCmd extends BaseCommand {
    MediaItem mAlbumItem;

    private boolean isSortByChanged(Object[] objArr) {
        if (objArr.length > 1) {
            Integer num = objArr[0];
            if (!(num instanceof Integer) || !(objArr[1] instanceof Integer) || num.intValue() == objArr[1].intValue()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private void refreshAlbum() {
        MediaData mediaData = getHandler().getMediaData();
        if (mediaData != null) {
            mediaData.reopen(getHandler().getLocationKey());
        }
    }

    private void saveSortBy(MediaItem mediaItem, Object[] objArr) {
        GalleryPreference.getInstance().saveSortBy(String.valueOf(mediaItem.getAlbumID()), objArr[1].intValue());
    }

    /* access modifiers changed from: private */
    public void setSortBy(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null) {
            Log.d(this.TAG, "data is null!!");
            return;
        }
        MediaItem mediaItem = this.mAlbumItem;
        MediaItem mediaItem2 = (MediaItem) getBlackboard().pop("data://sorted/target", null);
        if (mediaItem != mediaItem2 || mediaItem == null) {
            Log.w((CharSequence) this.TAG, "album mismatched", mediaItem, mediaItem2);
            return;
        }
        Object[] objArr = (Object[]) arrayList.get(0);
        Log.d(this.TAG, "setSortBy", Integer.valueOf(mediaItem.getAlbumID()), Arrays.toString(objArr));
        if (isSortByChanged(objArr)) {
            saveSortBy(mediaItem, objArr);
            refreshAlbum();
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_SPLIT_SORT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mAlbumItem = objArr[0];
        getBlackboard().publish("data://sorted/target", this.mAlbumItem);
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData("data://user/dialog/SortBy").setOnDataCompleteListener(new h(13, this)).start();
    }
}
