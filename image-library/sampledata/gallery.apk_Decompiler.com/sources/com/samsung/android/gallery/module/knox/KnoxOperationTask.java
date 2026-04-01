package com.samsung.android.gallery.module.knox;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.DbCompatGroup;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.LocalDatabase;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import h3.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import n4.C0491c;
import q9.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KnoxOperationTask {
    private static final int MOVE_TO_KNOX_MAX_COUNT;
    private static final boolean SUPPORT_UNLIMITED_MOVE_COUNT = Features.isEnabled(Features.SUPPORT_UNLIMITED_MOVE_TO_SECURE_FOLDER);
    private final Blackboard mBlackboard;
    private final KnoxOperationCallback mCallback;
    private int mCloudIndex = 0;
    private int mCloudOnlyCount;
    private final int mContainerId;
    private int mLocalCloudImageCount = 0;
    private int mLocalCloudVideoCount = 0;
    private final List<MediaItem> mMediaItemList;
    private final KnoxUtil.MoveType mMoveType;
    private final ArrayList<String> mRequestItemList = new ArrayList<>();
    private int mTotalCount = 0;

    /* renamed from: com.samsung.android.gallery.module.knox.KnoxOperationTask$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType[] r0 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType = r0
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType r1 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.FAIL_CLOUD_CONTENTS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType r1 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.FAIL_EMPTY_DATA     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType r1 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.FAIL_EXCEEDED_RANGE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType r1 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.FAIL_WRONG_CONTAINER_ID     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.knox.KnoxOperationTask$MessageType r1 = com.samsung.android.gallery.module.knox.KnoxOperationTask.MessageType.DUMMY_LOCAL_CLOUD_CONTENTS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.knox.KnoxOperationTask.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MessageType {
        PROGRESS_CONTENTS,
        FAIL_CLOUD_CONTENTS,
        FAIL_EMPTY_DATA,
        FAIL_EXCEEDED_RANGE,
        FAIL_WRONG_CONTAINER_ID,
        DUMMY_LOCAL_CLOUD_CONTENTS,
        NONE
    }

    static {
        int i2;
        if (SdkConfig.atLeast(SdkConfig.GED.O)) {
            i2 = 1000;
        } else {
            i2 = 500;
        }
        MOVE_TO_KNOX_MAX_COUNT = i2;
    }

    public KnoxOperationTask(Blackboard blackboard, List<MediaItem> list, int i2, KnoxUtil.MoveType moveType, KnoxOperationCallback knoxOperationCallback) {
        this.mBlackboard = blackboard;
        this.mMediaItemList = list;
        this.mContainerId = i2;
        this.mMoveType = moveType;
        this.mCallback = knoxOperationCallback;
    }

    private void addFilePath(MediaItem mediaItem) {
        Optional.ofNullable(mediaItem.getPath()).filter(new b(4, this)).ifPresent(new C0491c(13, this, mediaItem));
    }

    private void addLogData() {
        String str = "[knox][" + this.mMoveType + "][" + this.mContainerId + "][t=" + this.mTotalCount + ",l=" + (this.mTotalCount - this.mCloudOnlyCount) + ",c=" + this.mCloudOnlyCount + ",lci=" + this.mLocalCloudImageCount + ",lcv=" + this.mLocalCloudVideoCount + "]";
        Log.d("KnoxOperationTask", str);
        DebugLogger.getDeleteInstance().lambda$apply$0("MoveToKnox", str);
    }

    private Cursor getGroupMediaCursor(MediaItem mediaItem) {
        int i2;
        if (mediaItem == null || mediaItem.getGroupMediaId() == 0) {
            return null;
        }
        if (mediaItem.getBucketID() > 0) {
            i2 = mediaItem.getBucketID();
        } else {
            i2 = FileUtils.getBucketId(FileUtils.getDirectoryFromPath(mediaItem.getPath(), false));
        }
        return DbCompatGroup.getGroupCursor(mediaItem, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$addFilePath$4(String str) {
        return !this.mRequestItemList.contains(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addFilePath$5(MediaItem mediaItem, String str) {
        if (mediaItem.isCloudOnly()) {
            StringBuilder sb2 = new StringBuilder("CLOUD_ONLY/");
            int i2 = this.mCloudIndex + 1;
            this.mCloudIndex = i2;
            sb2.append(i2);
            str = sb2.toString();
        }
        this.mRequestItemList.add(str);
        if (mediaItem.isCloudOnly()) {
            this.mCloudOnlyCount++;
        } else if (mediaItem.getStorageType() == StorageType.LocalCloud) {
            if (mediaItem.isImage()) {
                this.mLocalCloudImageCount++;
            } else {
                this.mLocalCloudVideoCount++;
            }
        }
        this.mTotalCount++;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$extractItemPath$0(com.samsung.android.gallery.module.data.MediaItem r5) {
        /*
            r4 = this;
            long r0 = r5.getGroupMediaId()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto L_0x003a
            android.database.Cursor r0 = r4.getGroupMediaCursor(r5)
            if (r0 == 0) goto L_0x0026
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x0024 }
            if (r1 == 0) goto L_0x0026
        L_0x0016:
            com.samsung.android.gallery.module.data.MediaItem r5 = com.samsung.android.gallery.module.data.MediaItemBuilder.createSimple(r0)     // Catch:{ all -> 0x0024 }
            r4.addFilePath(r5)     // Catch:{ all -> 0x0024 }
            boolean r5 = r0.moveToNext()     // Catch:{ all -> 0x0024 }
            if (r5 != 0) goto L_0x0016
            goto L_0x0029
        L_0x0024:
            r4 = move-exception
            goto L_0x002f
        L_0x0026:
            r4.addFilePath(r5)     // Catch:{ all -> 0x0024 }
        L_0x0029:
            if (r0 == 0) goto L_0x002e
            r0.close()
        L_0x002e:
            return
        L_0x002f:
            if (r0 == 0) goto L_0x0039
            r0.close()     // Catch:{ all -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r5 = move-exception
            r4.addSuppressed(r5)
        L_0x0039:
            throw r4
        L_0x003a:
            r4.addFilePath(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.knox.KnoxOperationTask.lambda$extractItemPath$0(com.samsung.android.gallery.module.data.MediaItem):void");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$extractItemPath$1(MediaItem mediaItem) {
        Optional.ofNullable(mediaItem).ifPresent(new q9.b(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$operate$3(Context context) {
        extractItemPath();
        ThreadUtil.runOnUiThread(new a(this, context, 1));
    }

    /* access modifiers changed from: private */
    /* renamed from: onPostExecute */
    public void lambda$operate$2(Context context) {
        this.mCallback.dismissProgressDialog();
        MessageType prepareResultType = getPrepareResultType();
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$knox$KnoxOperationTask$MessageType[prepareResultType.ordinal()];
        if (i2 == 1 || i2 == 2 || i2 == 3 || i2 == 4) {
            Utils.showToast(AppResources.getAppContext(), KnoxOperatorString.getMessage(prepareResultType, this.mMoveType, this.mCloudOnlyCount, MOVE_TO_KNOX_MAX_COUNT));
        } else if (i2 != 5) {
            operateInternal(context);
        } else {
            this.mCallback.showCloudSyncConfirmDialog(this.mLocalCloudImageCount, this.mLocalCloudVideoCount, new q9.b(this, 0));
        }
        Log.d("KnoxOperationTask", "moveToKnox prepare result : " + prepareResultType);
    }

    /* access modifiers changed from: private */
    public void operateInternal(Context context) {
        Uri uri;
        try {
            if (this.mRequestItemList.size() > 500) {
                ShareList.setSharePathList((ArrayList) this.mRequestItemList.clone());
                if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
                    uri = LocalDatabase.URI;
                } else {
                    uri = LocalDatabase.URI_LEGACY;
                }
                SeApiCompat.moveFilesForApp(context, uri.buildUpon().appendEncodedPath("unlimited_move_list").build(), this.mRequestItemList.size(), this.mContainerId);
                Log.d("KnoxOperationTask", "moveToKnox api called(uri)");
            } else {
                ArrayList<String> arrayList = this.mRequestItemList;
                SeApiCompat.moveFilesForApp(context, arrayList, arrayList, this.mContainerId);
                Log.d("KnoxOperationTask", "moveToKnox api called(array)");
            }
            this.mBlackboard.post("command://event/DataDirty", (Object) null);
            addLogData();
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("operate failed. e="), "KnoxOperationTask");
        }
    }

    public void extractItemPath() {
        if (this.mContainerId != -100) {
            this.mMediaItemList.forEach(new q9.b(this, 1));
        }
    }

    public MessageType getPrepareResultType() {
        if (this.mContainerId == -100) {
            return MessageType.FAIL_WRONG_CONTAINER_ID;
        }
        if (isExceededRange(this.mRequestItemList.size())) {
            return MessageType.FAIL_EXCEEDED_RANGE;
        }
        if (this.mRequestItemList.isEmpty()) {
            return MessageType.FAIL_EMPTY_DATA;
        }
        if (this.mCloudOnlyCount == this.mTotalCount) {
            return MessageType.FAIL_CLOUD_CONTENTS;
        }
        if (this.mLocalCloudImageCount + this.mLocalCloudVideoCount > 0) {
            return MessageType.DUMMY_LOCAL_CLOUD_CONTENTS;
        }
        return MessageType.NONE;
    }

    public boolean isExceededRange(int i2) {
        if (SUPPORT_UNLIMITED_MOVE_COUNT || i2 <= MOVE_TO_KNOX_MAX_COUNT) {
            return false;
        }
        return true;
    }

    public void operate(Context context) {
        if (this.mMediaItemList.size() > 300) {
            this.mCallback.showProgressDialog();
        }
        SimpleThreadPool.getInstance().execute(new a(this, context, 0));
    }
}
