package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameSharedAlbumCmd extends BaseCommand {
    private AnalyticsEventId mEventId;
    private MediaItem mItem;

    private String getDataRequestKey() {
        if (MdeGroupHelper.isSAFamilyGroup(this.mItem)) {
            return "data://user/dialog/SharedFamilyAlbumRename";
        }
        return "data://user/dialog/SharedAlbumRename";
    }

    /* access modifiers changed from: private */
    public void renameSharedAlbum(EventContext eventContext, ArrayList<Object> arrayList) {
        String str;
        if (arrayList != null && (str = (String) arrayList.get(0)) != null && !str.isEmpty()) {
            new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_UPDATE_SPACE, MediaItemMde.getSpaceId(this.mItem), str, null);
        }
    }

    public String getEventId() {
        return this.mEventId.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        if (objArr == null || objArr.length == 0) {
            Log.she(this.TAG, "RenameSharedAlbumCmd null arguments");
            return;
        }
        this.mEventId = objArr[0];
        if (objArr.length > 1) {
            this.mItem = objArr[1][0];
        } else if (!eventContext.isSelectionMode()) {
            this.mItem = eventContext.getHeaderItem();
        } else if (eventContext.getSelectedItems().length > 0) {
            this.mItem = eventContext.getSelectedItems()[0];
        }
        if (this.mItem != null) {
            UriBuilder uriBuilder = new UriBuilder(getDataRequestKey());
            if (this.mItem.getTitle() != null) {
                str = this.mItem.getTitle();
            } else {
                str = getContext().getString(R.string.shared_album);
            }
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(uriBuilder.appendArg("name", str).appendArg("screenId", eventContext.getScreenId()).build()).setOnDataCompleteListener(new y(21, this)).start();
        }
    }
}
