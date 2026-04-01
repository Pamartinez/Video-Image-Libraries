package com.samsung.android.gallery.app.controller.sharing;

import O3.y;
import Ob.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.module.service.message.EmptyMsgMgr;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmptySharedItemFromTrashCmd extends BaseCommand {
    private AnalyticsEventId mEventId = AnalyticsEventId.EVENT_MENU_SHARING_RESTORE_FROM_TRASH;

    /* access modifiers changed from: private */
    public void emptyTrash(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && arrayList.size() > 0 && ((Integer) arrayList.get(0)).intValue() == 1) {
            getBlackboard().postEvent(EventMessage.obtain(1057));
            getBlackboard().postEvent(EventMessage.obtain(3015));
            new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_EMPTY_FROM_TRASH);
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    private void emptyWithUserConfirm(EventContext eventContext, int i2) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", EmptyMsgMgr.getTitle(getContext(), i2, 0, 0)).appendArg("option1", getContext().getString(R.string.delete)).appendArg("screenId", getScreenId()).appendArg("option1EventId", getEventIdOfEmpty()).appendArg("cancelEventId", getEventIdOfCancel()).build()).setOnDataCompleteListener(new y(18, this)).start();
    }

    private String getEventIdOfCancel() {
        return AnalyticsEventId.EVENT_RECYCLE_BIN_EMPTY_ALL_TRASH_CANCEL.toString();
    }

    private String getEventIdOfEmpty() {
        return AnalyticsEventId.EVENT_RECYCLE_BIN_EMPTY_ALL_TRASH_OK.toString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(EventContext eventContext) {
        int[] familySharedTrashCount = new MdeDatabase().getFamilySharedTrashCount();
        emptyWithUserConfirm(eventContext, familySharedTrashCount[0] + familySharedTrashCount[1]);
    }

    public String getEventId() {
        return this.mEventId.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        AnalyticsEventId analyticsEventId;
        int i2 = 0;
        if (objArr != null && objArr.length > 0) {
            i2 = objArr[0].intValue();
        }
        if (!(objArr == null || objArr.length <= 1 || (analyticsEventId = objArr[1]) == null)) {
            this.mEventId = analyticsEventId;
        }
        if (i2 == 0) {
            SimpleThreadPool.getInstance().execute(new a(11, this, eventContext));
        } else {
            emptyWithUserConfirm(eventContext, i2);
        }
    }
}
