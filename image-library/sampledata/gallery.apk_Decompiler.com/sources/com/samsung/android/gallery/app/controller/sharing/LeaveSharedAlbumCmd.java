package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LeaveSharedAlbumCmd extends BaseCommand {
    private AnalyticsEventId mEventId;
    private MediaItem mSelectedItem;

    /* access modifiers changed from: private */
    public void requestLeaveSharedAlbum(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2 = 0;
        if (arrayList != null) {
            i2 = ((Integer) arrayList.get(0)).intValue();
        }
        if (i2 == 1) {
            new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_LEAVE_GROUP, MediaItemMde.getGroupId(this.mSelectedItem));
        }
    }

    public String getEventId() {
        return this.mEventId.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Context context = getContext();
        this.mEventId = objArr[0];
        MediaItem[] mediaItemArr = objArr[1];
        if (mediaItemArr.length == 1) {
            this.mSelectedItem = mediaItemArr[0];
            String string = context.getString(R.string.leave_shared_album_title);
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", string).appendArg("description", context.getString(R.string.leave_shared_album_description)).appendArg("option1", context.getString(R.string.leave)).appendArg("screenId", getScreenId()).appendArg("option1EventId", AnalyticsEventId.EVENT_SHARED_VIEW_LEAVE_DIALOG_OK.toString()).appendArg("cancelEventId", AnalyticsEventId.EVENT_SHARED_VIEW_LEAVE_DIALOG_CANCEL.toString()).build()).setOnDataCompleteListener(new y(19, this)).start();
        }
    }
}
