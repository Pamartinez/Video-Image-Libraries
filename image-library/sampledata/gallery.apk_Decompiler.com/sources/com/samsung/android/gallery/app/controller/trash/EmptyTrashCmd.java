package com.samsung.android.gallery.app.controller.trash;

import A.a;
import A4.C0381p;
import V3.C0410a;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.service.message.EmptyMsgMgr;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmptyTrashCmd extends BaseCommand {
    private EventContext mEventContext;
    private boolean mIsEmptyAll = false;
    private MediaItem[] mItems = null;
    private String mLocationKey;
    private int mTrashCount;

    private int getAllItemCount() {
        MediaData open;
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !LocationKey.isPrivateTrash(this.mLocationKey)) {
            return TrashProviderFactory.getInstance(getContext()).getTrashTotalCount();
        }
        try {
            open = MediaDataFactory.getInstance(getBlackboard()).open(this.mLocationKey, true);
            int count = open.getCount();
            open.close();
            return count;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getAllItemCount failed. e="), this.TAG);
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private String getEventIdOfCancel() {
        if (this.mIsEmptyAll) {
            return AnalyticsEventId.EVENT_RECYCLE_BIN_EMPTY_ALL_TRASH_CANCEL.toString();
        }
        return AnalyticsEventId.EVENT_RECYCLE_BIN_EMPTY_TRASH_CANCEL.toString();
    }

    private String getEventIdOfEmpty() {
        if (this.mIsEmptyAll) {
            return AnalyticsEventId.EVENT_RECYCLE_BIN_EMPTY_ALL_TRASH_OK.toString();
        }
        return AnalyticsEventId.EVENT_RECYCLE_BIN_EMPTY_TRASH_OK.toString();
    }

    private int[] getItemCounts() {
        int[] iArr = new int[2];
        for (MediaItem mediaItem : this.mItems) {
            if (mediaItem != null) {
                if (mediaItem.isVideo()) {
                    iArr[1] = iArr[1] + 1;
                } else {
                    iArr[0] = iArr[0] + 1;
                }
            }
        }
        return iArr;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$onExecute$0(boolean z, ThreadPool.JobContext jobContext) {
        this.mTrashCount = getAllItemCount();
        operate(z);
        return null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a A[Catch:{ ClassCastException -> 0x0014 }] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001e A[Catch:{ ClassCastException -> 0x0014 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDataCompleted(com.samsung.android.gallery.app.controller.EventContext r2, java.util.ArrayList<java.lang.Object> r3) {
        /*
            r1 = this;
            if (r3 == 0) goto L_0x0016
            int r2 = r3.size()     // Catch:{ ClassCastException -> 0x0014 }
            if (r2 <= 0) goto L_0x0016
            r2 = 0
            java.lang.Object r2 = r3.get(r2)     // Catch:{ ClassCastException -> 0x0014 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ ClassCastException -> 0x0014 }
            int r2 = r2.intValue()     // Catch:{ ClassCastException -> 0x0014 }
            goto L_0x0017
        L_0x0014:
            r2 = move-exception
            goto L_0x0026
        L_0x0016:
            r2 = -1
        L_0x0017:
            r3 = 1
            if (r2 != r3) goto L_0x001e
            r1.operateCompleteDelete()     // Catch:{ ClassCastException -> 0x0014 }
            return
        L_0x001e:
            java.lang.String r2 = r1.TAG     // Catch:{ ClassCastException -> 0x0014 }
            java.lang.String r3 = "cancel or unexpected option selected."
            com.samsung.android.gallery.support.utils.Log.e(r2, r3)     // Catch:{ ClassCastException -> 0x0014 }
            return
        L_0x0026:
            java.lang.String r1 = r1.TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "unexpected result delivered."
            r3.<init>(r0)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            com.samsung.android.gallery.support.utils.Log.e(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.trash.EmptyTrashCmd.onDataCompleted(com.samsung.android.gallery.app.controller.EventContext, java.util.ArrayList):void");
    }

    private void operate(boolean z) {
        MediaItem[] mediaItemArr;
        boolean z3 = this.mIsEmptyAll;
        if (z3 && this.mTrashCount == 0) {
            Log.e(this.TAG, "unable to operate. no item in trash");
        } else if (!z3 && ((mediaItemArr = this.mItems) == null || mediaItemArr.length == 0)) {
            Log.e(this.TAG, "unable to operate. no item selected.");
        } else if (z) {
            operateCompleteDelete();
        } else {
            ThreadPool.getInstance().submit(new C0381p(4, this));
        }
    }

    private void operateCompleteDelete() {
        getBlackboard().publish("data://user/selected", this.mItems);
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.EmptyService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("is_empty_all", this.mIsEmptyAll);
        intent.putExtra("location_key", this.mLocationKey);
        startService(intent);
        getBlackboard().postEvent(EventMessage.obtain(1057));
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    /* access modifiers changed from: private */
    public Object showEmptyDialog(ThreadPool.JobContext jobContext) {
        int i2;
        String str;
        if (this.mIsEmptyAll) {
            i2 = this.mTrashCount;
            str = EmptyMsgMgr.getTitle(getContext(), i2, 0, 0);
            String str2 = this.TAG;
            Log.d(str2, "Empty All [" + i2 + "]");
        } else {
            int[] itemCounts = getItemCounts();
            int i7 = itemCounts[0] + itemCounts[1];
            String title = EmptyMsgMgr.getTitle(getContext(), i7, itemCounts[0], itemCounts[1]);
            String str3 = this.TAG;
            Log.d(str3, "Empty [" + itemCounts[0] + "][" + itemCounts[1] + "] selected");
            i2 = i7;
            str = title;
        }
        if (i2 == 0) {
            Log.e(this.TAG, "unable to operate. no item selected. exclude null");
            return null;
        }
        DataCollectionDelegate.getInitInstance(this.mEventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", str).appendArg("option1", getContext().getString(R.string.delete)).appendArg("screenId", getScreenId()).appendArg("option1EventId", getEventIdOfEmpty()).appendArg("cancelEventId", getEventIdOfCancel()).build()).setOnDataCompleteListener(new U3.a(8, this)).start();
        return null;
    }

    public String getEventId() {
        if (this.mIsEmptyAll) {
            return AnalyticsEventId.EVENT_RECYCLE_BIN_MENU_EMPTY_ALL.toString();
        }
        return AnalyticsEventId.EVENT_RECYCLE_BIN_MENU_EMPTY.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mLocationKey = eventContext.getLocationKey();
        this.mEventContext = eventContext;
        this.mIsEmptyAll = objArr[1].booleanValue();
        boolean booleanValue = objArr[2].booleanValue();
        if (this.mIsEmptyAll) {
            ThreadPool.getInstance().submit(new C0410a(this, booleanValue));
            return;
        }
        this.mItems = objArr[0];
        operate(booleanValue);
    }
}
