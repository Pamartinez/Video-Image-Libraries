package com.samsung.android.gallery.app.controller.trash;

import A.a;
import A4.C0381p;
import I5.e;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ShowLowStorageCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RestoreTrashCmd extends BaseCommand {
    private boolean mIsRestoreAll = false;

    private void checkRoamingState(Context context) {
        PreferenceCache preferenceCache = PreferenceCache.RoamingTip;
        if (preferenceCache.getBoolean()) {
            Log.d(this.TAG, "roaming message is already shown.");
        } else if (NetworkUtils.isNetworkRoaming(context)) {
            showToast((int) R.string.restoring_while_roaming, 1);
            preferenceCache.setBoolean(true);
        }
    }

    private int getAllItemCounts() {
        return TrashProviderFactory.getInstance(getContext()).getTrashTotalCount();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onExecute$0(Object[] objArr, ThreadPool.JobContext jobContext) {
        return Boolean.valueOf(restorePrivateTrash(objArr[0], this.mIsRestoreAll));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$onExecute$1(ThreadPool.JobContext jobContext) {
        if (getAllItemCounts() == 0) {
            Log.e(this.TAG, "unable to operate. no item in trash");
            return null;
        }
        operateRestore((MediaItem[]) null);
        return null;
    }

    private void operateRestore(MediaItem[] mediaItemArr) {
        getBlackboard().publish("data://user/selected", mediaItemArr);
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.RestoreService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("is_restore_all", this.mIsRestoreAll);
        startService(intent);
        getBlackboard().postEvent(EventMessage.obtain(1057));
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    private boolean restorePrivateTrash(MediaItem[] mediaItemArr, boolean z) {
        Cursor queryTrashData;
        long currentTimeMillis = System.currentTimeMillis();
        if (mediaItemArr == null && z) {
            ArrayList arrayList = new ArrayList();
            try {
                queryTrashData = PrivateDatabase.getInstance().queryTrashData();
                if (queryTrashData != null) {
                    if (queryTrashData.moveToFirst()) {
                        do {
                            arrayList.add(MediaItemLoader.load(queryTrashData));
                        } while (queryTrashData.moveToNext());
                    }
                }
                if (queryTrashData != null) {
                    queryTrashData.close();
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("restore private trash query failed. e="), this.TAG);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            if (!arrayList.isEmpty()) {
                mediaItemArr = (MediaItem[]) arrayList.toArray(new MediaItem[0]);
            }
        }
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "restore private trash skip. no item in trash");
            return false;
        }
        PrivateDatabase.getInstance().untrash(mediaItemArr);
        getBlackboard().postEvent(EventMessage.obtain(1003));
        String str = this.TAG;
        a.A(new Object[]{Integer.valueOf(mediaItemArr.length), Long.valueOf(currentTimeMillis)}, new StringBuilder("restore private trash"), str);
        return true;
        throw th;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_RECYCLE_BIN_MENU_RESTORE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mIsRestoreAll = objArr[1].booleanValue();
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM && LocationKey.isPrivateTrash(eventContext.getLocationKey())) {
            ThreadPool.getInstance().submit(new e(4, this, objArr));
        } else if (isLowStorageWithTrash()) {
            new ShowLowStorageCmd().execute(eventContext, new Object[0]);
        } else {
            checkRoamingState(getContext());
            if (this.mIsRestoreAll) {
                ThreadPool.getInstance().submit(new C0381p(5, this));
                return;
            }
            MediaItem[] mediaItemArr = objArr[0];
            if (mediaItemArr == null || mediaItemArr.length == 0) {
                Log.e(this.TAG, "unable to operate. no item selected.");
            } else {
                operateRestore(mediaItemArr);
            }
        }
    }
}
