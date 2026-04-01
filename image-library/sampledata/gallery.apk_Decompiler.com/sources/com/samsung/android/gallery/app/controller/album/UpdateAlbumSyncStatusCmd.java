package com.samsung.android.gallery.app.controller.album;

import A.a;
import A4.C0381p;
import D7.l;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.sqlite.SQLiteFullException;
import android.os.RemoteException;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.local.LocalAlbumsApi;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.CloudData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UpdateAlbumSyncStatusCmd extends BaseCommand {
    private void addOperation(ContentProviderOperation contentProviderOperation, ArrayList<ContentProviderOperation> arrayList) {
        arrayList.add(contentProviderOperation);
        if (arrayList.size() >= 500) {
            updateInBatch(arrayList);
            arrayList.clear();
        }
    }

    private ContentProviderOperation createUpdateOp(MediaItem mediaItem) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(mediaItem.getAlbumID()));
        return new LocalAlbumsApi().getUpdateOperation(getContentValues(mediaItem), arrayList);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        com.samsung.android.gallery.support.utils.Log.e(r4.TAG, "getAllItems failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x002f */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:14:0x003a=Splitter:B:14:0x003a, B:10:0x002f=Splitter:B:10:0x002f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.samsung.android.gallery.module.data.MediaItem[] getAllItems() {
        /*
            r4 = this;
            com.samsung.android.gallery.support.blackboard.Blackboard r0 = r4.getBlackboard()
            com.samsung.android.gallery.module.dataset.MediaDataFactory r0 = com.samsung.android.gallery.module.dataset.MediaDataFactory.getInstance(r0)
            java.lang.String r1 = "location://albums"
            r2 = 1
            com.samsung.android.gallery.module.dataset.MediaData r0 = r0.open(r1, r2)
            r1 = 0
            if (r0 == 0) goto L_0x003a
            int r2 = r0.getCount()     // Catch:{ all -> 0x002d }
            if (r2 != 0) goto L_0x0019
            goto L_0x003a
        L_0x0019:
            java.util.ArrayList r2 = r0.getFullData()     // Catch:{ NullPointerException -> 0x002f }
            int r3 = r0.getCount()     // Catch:{ NullPointerException -> 0x002f }
            com.samsung.android.gallery.module.data.MediaItem[] r3 = new com.samsung.android.gallery.module.data.MediaItem[r3]     // Catch:{ NullPointerException -> 0x002f }
            java.lang.Object[] r2 = r2.toArray(r3)     // Catch:{ NullPointerException -> 0x002f }
            com.samsung.android.gallery.module.data.MediaItem[] r2 = (com.samsung.android.gallery.module.data.MediaItem[]) r2     // Catch:{ NullPointerException -> 0x002f }
            r0.close()
            return r2
        L_0x002d:
            r4 = move-exception
            goto L_0x0047
        L_0x002f:
            java.lang.String r4 = r4.TAG     // Catch:{ all -> 0x002d }
            java.lang.String r2 = "getAllItems failed"
            com.samsung.android.gallery.support.utils.Log.e(r4, r2)     // Catch:{ all -> 0x002d }
            r0.close()
            return r1
        L_0x003a:
            java.lang.String r4 = r4.TAG     // Catch:{ all -> 0x002d }
            java.lang.String r2 = "getAllItems null"
            com.samsung.android.gallery.support.utils.Log.e(r4, r2)     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x0046
            r0.close()
        L_0x0046:
            return r1
        L_0x0047:
            if (r0 == 0) goto L_0x0051
            r0.close()     // Catch:{ all -> 0x004d }
            goto L_0x0051
        L_0x004d:
            r0 = move-exception
            r4.addSuppressed(r0)
        L_0x0051:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.album.UpdateAlbumSyncStatusCmd.getAllItems():com.samsung.android.gallery.module.data.MediaItem[]");
    }

    private ContentValues getContentValues(MediaItem mediaItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("album_sync_status", Integer.valueOf(CloudData.of(mediaItem).albumSyncStatus));
        return contentValues;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onExecute$0(ThreadPool.JobContext jobContext) {
        try {
            getBlackboard().publish("album_sync_data_updating", Boolean.TRUE);
            Boolean valueOf = Boolean.valueOf(updateAlbumSyncStatus());
            getBlackboard().publish("album_sync_data_updating", Boolean.FALSE);
            return valueOf;
        } catch (Error | Exception e) {
            String str = "updateAlbumSyncStatus failed. e=" + e.getMessage();
            Log.e(this.TAG, str);
            new InternalException(str).post();
            Boolean bool = Boolean.FALSE;
            getBlackboard().publish("album_sync_data_updating", bool);
            return bool;
        } catch (Throwable th) {
            getBlackboard().publish("album_sync_data_updating", Boolean.FALSE);
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1(boolean z, Future future) {
        reloadAlbum(((Boolean) future.get()).booleanValue(), z);
    }

    private void reloadAlbum(boolean z, boolean z3) {
        if (z) {
            getBlackboard().postEvent(EventMessage.obtain(2006));
        }
    }

    private boolean updateAlbumSyncStatus() {
        boolean z = false;
        if ((!CloudStateCompat.isEnabled() || !SamsungCloudCompat.isCloudSyncOn(getContext())) && !AlbumHelper.getInstance().hasAlbumSyncStatus(getContext())) {
            Log.d(this.TAG, "updateAlbumSyncStatus skip");
            return false;
        }
        ArrayList arrayList = new ArrayList();
        MediaItem[] allItems = getAllItems();
        if (allItems != null && allItems.length > 0) {
            boolean z3 = false;
            for (MediaItem mediaItem : allItems) {
                if (mediaItem != null) {
                    if (mediaItem.isFolder()) {
                        for (MediaItem mediaItem2 : mediaItem.getAlbumsInFolder()) {
                            int albumSyncStatus = SamsungCloudCompat.getAlbumSyncStatus(getContext(), mediaItem2.getAlbumID(), mediaItem2.getTitle());
                            if (albumSyncStatus != CloudData.of(mediaItem2).albumSyncStatus) {
                                CloudData.of(mediaItem2).albumSyncStatus = albumSyncStatus;
                                addOperation(createUpdateOp(mediaItem), arrayList);
                                z3 = true;
                            }
                        }
                    } else {
                        int albumSyncStatus2 = SamsungCloudCompat.getAlbumSyncStatus(getContext(), mediaItem.getAlbumID(), mediaItem.getTitle());
                        if (albumSyncStatus2 != CloudData.of(mediaItem).albumSyncStatus) {
                            CloudData.of(mediaItem).albumSyncStatus = albumSyncStatus2;
                            addOperation(createUpdateOp(mediaItem), arrayList);
                            z3 = true;
                        }
                    }
                }
            }
            z = z3;
        }
        if (!arrayList.isEmpty()) {
            updateInBatch(arrayList);
        }
        a.v("updateAlbumSyncStatus {", "}", this.TAG, z);
        return z;
    }

    private void updateInBatch(ArrayList<ContentProviderOperation> arrayList) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            ContentProviderResult[] applyBatch = getContext().getContentResolver().applyBatch(new LocalAlbumsApi().getTableUri().getAuthority(), arrayList);
            String str = this.TAG;
            Log.d(str, "updateInBatch {" + applyBatch.length + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (OperationApplicationException | SQLiteFullException | RemoteException | NullPointerException e) {
            Log.e((CharSequence) this.TAG, "updateInBatch failed", e);
        }
    }

    public boolean includeMajorEvent() {
        return false;
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        ThreadPool.getInstance().submit(new C0381p(2, this), new l(this, objArr[0].booleanValue()));
    }
}
