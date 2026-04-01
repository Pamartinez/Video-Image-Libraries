package com.samsung.android.gallery.module.publisher;

import A.a;
import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.SortByType;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class VirtualAlbumDataPublisher extends CursorPublisher {
    public VirtualAlbumDataPublisher(Blackboard blackboard) {
        super(blackboard);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishActionAlbumViewPicturesData$12(Cursor[] cursorArr, String str) {
        cursorArr[1] = DbCompat.query(DbKey.ALL_PICTURES_DAY, new c0(4, str));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishActionAlbumViewPicturesData$14(Cursor[] cursorArr, String str) {
        cursorArr[3] = DbCompat.query(DbKey.ALL_PICTURES_REAL_RATIO, new c0(2, str));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishActionAlbumViewPicturesData$16(Cursor[] cursorArr, String str) {
        cursorArr[5] = DbCompat.query(DbKey.ALL_PICTURES_ID, new c0(3, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishActionAlbumViewPicturesData$17(Cursor[] cursorArr, long j2, String str) {
        a.y(new StringBuilder("publishActionAlbumViewPicturesData "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray(str, cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishRepairData$10(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[3] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishRepairData$3(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[0] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishRepairData$4(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[1] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishRepairData$5(Cursor[] cursorArr, long j2) {
        a.y(new StringBuilder("publishRepairData(A) : "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray("location://virtual/album/repair/fileList", cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishRepairData$6(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[3] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishRepairData$7(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[0] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishRepairData$8(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[1] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishRepairData$9(Cursor[] cursorArr, long j2) {
        a.y(new StringBuilder("publishRepairData(T) "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray("location://virtual/album/repair/fileList", cursorArr);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishVideoData$0(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[1] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishVideoData$1(Cursor[] cursorArr, QueryParams queryParams) {
        cursorArr[0] = DbCompat.query(queryParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$publishVideoData$2(Cursor[] cursorArr, long j2) {
        a.y(new StringBuilder("publishVideoData(T) "), getCursorListInfo(cursorArr, j2), this.TAG);
        publishCursorArray("location://virtual/album/video/fileList", cursorArr);
    }

    /* access modifiers changed from: private */
    public void publishActionAlbumViewPicturesData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        String DATA_REQUEST_TO_LOCATION = CommandKey.DATA_REQUEST_TO_LOCATION(BundleWrapper.getKey(bundle));
        String string = bundle.getString("file_id", "");
        Cursor[] cursorArr = {null, null, null, null, null, null};
        new LatchBuilder("publishActionAlbumViewPicturesData").addWorker(new a0(cursorArr, string, 1)).addWorker(new a0(cursorArr, string, 2)).setCurrent(new a0(cursorArr, string, 3)).setPostExecutor((Runnable) new C0627g(this, cursorArr, currentTimeMillis, DATA_REQUEST_TO_LOCATION, 2)).start();
    }

    /* access modifiers changed from: private */
    public void publishFavoriteData(Object obj, Bundle bundle) {
        publishFavoriteFileData(bundle, -1);
    }

    /* access modifiers changed from: private */
    public void publishRecentlyData(Object obj, Bundle bundle) {
        publishRecentFileData(bundle);
    }

    /* access modifiers changed from: private */
    public void publishRepairData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        String str = (String) this.mBlackboard.read("repair-data-type", "invalid time");
        if ("all".equals(str)) {
            Log.d(this.TAG, "publishRepairData {all}");
            QueryParams storageTypes = new QueryParams().setDbKey(DbKey.TIMELINE).setMediaTypeFilter("*/*").addGroupType(GroupType.BURST).setStorageTypes(QueryParams.DbStorageType.LocalOnly);
            Cursor[] cursorArr = {null, null, null, null};
            LatchBuilder postExecutor = new LatchBuilder("publishRepairData(A)").setCurrent(new C0625e(cursorArr, 16, storageTypes)).addWorker(new C0625e(cursorArr, 17, storageTypes.clone().setDbKey(DbKey.TIMELINE_DAY))).setPostExecutor((Runnable) new g0(this, cursorArr, currentTimeMillis, 2));
            if (CursorPublisher.SUPPORT_REAL_RATIO) {
                postExecutor.addWorker(new C0625e(cursorArr, 18, storageTypes.clone().setDbKey(DbKey.TIMELINE_REALRATIO)));
            }
            postExecutor.start();
            return;
        }
        QueryParams tag = createQueryParams(bundle).setDbKey(DbKey.VIRTUAL_ALBUM_REPAIR).setGroupTypes(GroupType.BURST).setTag("repair-data-type", str);
        Cursor[] cursorArr2 = {null, null, null, null};
        LatchBuilder postExecutor2 = new LatchBuilder("publishRepairData").setCurrent(new C0625e(cursorArr2, 19, tag)).addWorker(new C0625e(cursorArr2, 12, tag.clone().setDbKey(DbKey.VIRTUAL_ALBUM_REPAIR_DAY))).setPostExecutor((Runnable) new g0(this, cursorArr2, currentTimeMillis, 0));
        if (CursorPublisher.SUPPORT_REAL_RATIO) {
            postExecutor2.addWorker(new C0625e(cursorArr2, 13, tag.clone().setDbKey(DbKey.VIRTUAL_ALBUM_REPAIR_REAL_RATIO)));
        }
        postExecutor2.start();
    }

    /* access modifiers changed from: private */
    public void publishVideoData(Object obj, Bundle bundle) {
        long currentTimeMillis = System.currentTimeMillis();
        QueryParams dbKey = createVirtualQueryParams(bundle).setDbKey(DbKey.VIRTUAL_ALBUM_VIDEO);
        if (supportTimelineInVirtualAlbum(bundle)) {
            Cursor[] cursorArr = new Cursor[2];
            new LatchBuilder("publishVideoData").addWorker(new C0625e(cursorArr, 14, dbKey.clone().setDbKey(DbKey.VIRTUAL_ALBUM_VIDEO_DAY))).setCurrent(new C0625e(cursorArr, 15, dbKey)).setPostExecutor((Runnable) new g0(this, cursorArr, currentTimeMillis, 1)).start();
            return;
        }
        Cursor query = DbCompat.query(dbKey);
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "publishVideoData " + getCursorInfo(query, currentTimeMillis));
        publishCursorArray("location://virtual/album/video/fileList", new Cursor[]{query});
    }

    private boolean supportTimelineInVirtualAlbum(Bundle bundle) {
        return SortByType.isGroupByDate(bundle.getString("id", (String) null));
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://virtual/album/video/fileList"), new f0(this, 0)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://virtual/album/favorite/fileList"), new f0(this, 1)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://virtual/album/recently/fileList"), new f0(this, 2)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://virtual/album/repair/fileList"), new f0(this, 3)));
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://virtual/album/view/fileList"), new f0(this, 4)));
    }
}
