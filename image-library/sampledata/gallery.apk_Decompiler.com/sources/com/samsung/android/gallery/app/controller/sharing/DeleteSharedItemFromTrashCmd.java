package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import S3.d;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.message.EmptyMsgMgr;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteSharedItemFromTrashCmd extends BaseCommand {
    private AnalyticsEventId mEventId = AnalyticsEventId.EVENT_MENU_SHARING_REMOVE_FROM_TRASH;
    private MediaItem[] mItems;

    /* access modifiers changed from: private */
    public void deleteFromTrash(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && arrayList.size() > 0 && ((Integer) arrayList.get(0)).intValue() == 1) {
            getBlackboard().postEvent(EventMessage.obtain(1057));
            getBlackboard().postEvent(EventMessage.obtain(3015));
            new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_DELETE_FROM_TRASH, List.of(this.mItems));
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    private String getDialogEventId(String str, boolean z) {
        if (AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_PICTURE.toString().equals(str)) {
            if (z) {
                return AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_IMAGE_OK.toString();
            }
            return AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_IMAGE_CANCEL.toString();
        } else if (AnalyticsScreenId.SCREEN_SHARED_DETAIL_VIEW_VIDEO.toString().equals(str)) {
            if (z) {
                return AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_VIDEO_OK.toString();
            }
            return AnalyticsEventId.EVENT_SHARED_DETAIL_FS_REMOVE_VIDEO_CANCEL.toString();
        } else if (z) {
            return AnalyticsEventId.EVENT_SHARED_DETAIL_REMOVE_FROM_ALBUM_DIALOG_OK.toString();
        } else {
            return AnalyticsEventId.EVENT_SHARED_DETAIL_REMOVE_FROM_ALBUM_DIALOG_CANCEL.toString();
        }
    }

    private String getTitle(Context context) {
        MediaItem[] mediaItemArr = this.mItems;
        int length = mediaItemArr.length;
        int count = (int) Stream.of(mediaItemArr).filter(new d(2)).count();
        return EmptyMsgMgr.getTitle(getContext(), length, length - count, count);
    }

    public String getEventId() {
        return this.mEventId.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        AnalyticsEventId analyticsEventId;
        this.mItems = objArr[0];
        if (objArr.length > 1 && (analyticsEventId = objArr[1]) != null) {
            this.mEventId = analyticsEventId;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", getTitle(getContext())).appendArg("option1", getContext().getString(R.string.delete)).appendArg("screenId", getScreenId()).appendArg("option1EventId", getDialogEventId(getScreenId(), true)).appendArg("cancelEventId", getDialogEventId(getScreenId(), false)).build()).setOnDataCompleteListener(new y(17, this)).start();
    }
}
