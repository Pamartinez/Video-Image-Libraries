package com.samsung.android.gallery.app.service.abstraction;

import A.a;
import android.content.Intent;
import android.database.Cursor;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.service.abstraction.ProgressJob;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.module.trash.helper.TrashHelper;
import com.samsung.android.gallery.module.trash.helper.TrashProgressListener;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MyFilesApi;
import com.samsung.android.gallery.support.utils.PocFeatures;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseTrashService extends AbsProgressService implements TrashProgressListener {
    protected int LATE_DELAY = 1000;
    protected Queue<ProgressJob> mAppendQueue = new ConcurrentLinkedQueue();
    protected boolean mEmptyState = false;
    protected boolean mIsAbnormalFinished = false;

    public BaseTrashService(String str, String str2) {
        super(str, str2);
    }

    public void forceRefreshData() {
        Blackboard.postBroadcastEventGlobal(EventMessage.obtain(1029, 1, 0, (Object) null));
    }

    public String getLocationKey() {
        return "location://trash";
    }

    public MediaItem[] getSelectedItemList(boolean z) {
        MediaData open;
        if (!z) {
            return (MediaItem[]) this.mBlackboard.pop("data://user/selected");
        }
        boolean z3 = true;
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM && LocationKey.isPrivateTrash(this.mLocationKey)) {
            try {
                open = MediaDataFactory.getInstance(this.mBlackboard).open(this.mLocationKey, true);
                MediaItem[] mediaItemArr = (MediaItem[]) open.getAllData().toArray(new MediaItem[0]);
                open.close();
                return mediaItemArr;
            } catch (Exception e) {
                a.s(e, new StringBuilder("getSelectedItemList failed. e="), this.TAG);
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (SamsungCloudCompat.isSyncOn(this) && !Features.isEnabled(Features.IS_UPSM)) {
            z3 = false;
        }
        Cursor trashCursor = TrashProviderFactory.getInstance(AppResources.getAppContext()).getTrashCursor(z3);
        if (trashCursor != null) {
            try {
                if (trashCursor.moveToFirst()) {
                    ArrayList arrayList = new ArrayList();
                    do {
                        arrayList.add(getTrashHelper().getTrashItem(trashCursor));
                    } while (trashCursor.moveToNext());
                    MediaItem[] mediaItemArr2 = (MediaItem[]) arrayList.toArray(new MediaItem[0]);
                    trashCursor.close();
                    return mediaItemArr2;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (trashCursor != null) {
            trashCursor.close();
        }
        Log.w(this.TAG, "unable to get all item, trash is empty");
        return new MediaItem[0];
        throw th;
        throw th;
    }

    public abstract TrashHelper getTrashHelper();

    public abstract TrashLogType getTrashLogType();

    public int getTrashTotalCount() {
        MediaData open;
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !LocationKey.isPrivateTrash(this.mLocationKey)) {
            return TrashProviderFactory.getInstance(AppResources.getAppContext()).getTrashTotalCount();
        }
        try {
            open = MediaDataFactory.getInstance(this.mBlackboard).open(this.mLocationKey, true);
            int count = open.getCount();
            open.close();
            return count;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getTrashTotalCount failed. e="), this.TAG);
            return 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void interruptService() {
        super.interruptService();
        getTrashHelper().setInterrupted();
    }

    public void onCancelInternal() {
        getTrashHelper().setInterrupted();
    }

    public void onCleanInternal() {
        this.mDialogHelper.dismissDialog();
        showResult();
        forceRefreshData();
        super.onCleanInternal();
    }

    public void onComplete() {
        getTrashHelper().dump(getTrashLogType(), getLocationKey());
        startDeleteAnimation();
    }

    public void onEndInternal() {
        getTrashHelper().done();
        setChangeCollectOff();
        if (supportUpdateTrashState()) {
            MyFilesApi.updateTrashState(this.mEmptyState);
        }
        super.onEndInternal();
    }

    public boolean onInterruptInternal(Intent intent) {
        getTrashHelper().setInterrupted();
        return true;
    }

    public abstract void showResult();

    public void startDeleteAnimation() {
        int i2;
        Blackboard blackboard = this.mBlackboard;
        if (isInterrupted()) {
            i2 = 1058;
        } else {
            i2 = 1059;
        }
        blackboard.postEvent(EventMessage.obtain(i2));
    }

    public abstract boolean supportUpdateTrashState();

    public void onProgress(int i2) {
    }
}
