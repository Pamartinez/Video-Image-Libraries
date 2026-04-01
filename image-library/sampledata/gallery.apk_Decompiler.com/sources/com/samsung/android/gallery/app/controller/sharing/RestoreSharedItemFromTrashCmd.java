package com.samsung.android.gallery.app.controller.sharing;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RestoreSharedItemFromTrashCmd extends BaseCommand {
    private AnalyticsEventId mEventId = AnalyticsEventId.EVENT_MENU_SHARING_RESTORE_FROM_TRASH;

    public String getEventId() {
        return this.mEventId.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr;
        AnalyticsEventId analyticsEventId;
        if (objArr == null || objArr.length <= 0) {
            mediaItemArr = null;
        } else {
            mediaItemArr = objArr[0];
        }
        if (mediaItemArr == null) {
            Log.e(this.TAG, "onExecute failed wth null item");
            return;
        }
        if (objArr.length > 1 && (analyticsEventId = objArr[1]) != null) {
            this.mEventId = analyticsEventId;
        }
        getBlackboard().postEvent(EventMessage.obtain(1057));
        getBlackboard().postEvent(EventMessage.obtain(3015));
        new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_RESTORE_FROM_TRASH, List.of(mediaItemArr));
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }
}
