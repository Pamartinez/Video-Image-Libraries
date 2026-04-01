package com.samsung.android.gallery.module.trash.support;

import A.a;
import N2.j;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TrashLogger {
    final ArrayList<String> mExtras = new ArrayList<>();
    private final CountData mFailData = new CountData();
    protected int mRequestedScanSize = 0;
    private final CountData mStartData = new CountData();
    private boolean mSucceed = false;
    private final CountData mSuccessData = new CountData();
    private int mTotal = 0;

    /* renamed from: com.samsung.android.gallery.module.trash.support.TrashLogger$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.gallery.database.dbtype.StorageType[] r0 = com.samsung.android.gallery.database.dbtype.StorageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType = r0
                com.samsung.android.gallery.database.dbtype.StorageType r1 = com.samsung.android.gallery.database.dbtype.StorageType.Local     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.database.dbtype.StorageType r1 = com.samsung.android.gallery.database.dbtype.StorageType.LocalCloud     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.database.dbtype.StorageType r1 = com.samsung.android.gallery.database.dbtype.StorageType.Cloud     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.database.dbtype.StorageType r1 = com.samsung.android.gallery.database.dbtype.StorageType.PrivateItem     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.trash.support.TrashLogger.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CountData {
        final ConcurrentHashMap<MediaType, CountInfo> map = new ConcurrentHashMap<>();

        public int append(MediaType mediaType, StorageType storageType) {
            if (storageType == null) {
                return 0;
            }
            if (mediaType == MediaType.Image || mediaType == MediaType.Video) {
                return get(mediaType).append(storageType);
            }
            return 0;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.function.Function] */
        public CountInfo get(MediaType mediaType) {
            return this.map.computeIfAbsent(mediaType, new Object());
        }

        public int sum(MediaType mediaType) {
            if (mediaType == null) {
                return 0;
            }
            return get(mediaType).sum();
        }

        public String toString() {
            return get(MediaType.Image) + " " + get(MediaType.Video);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CountInfo {
        int cloud;
        int local;
        int localCloud;
        int localPrivate;
        final MediaType mediaType;

        public CountInfo(MediaType mediaType2) {
            this.mediaType = mediaType2;
        }

        public int append(StorageType storageType) {
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$database$dbtype$StorageType[storageType.ordinal()];
            if (i2 == 1) {
                int i7 = this.local + 1;
                this.local = i7;
                return i7;
            } else if (i2 == 2) {
                int i8 = this.localCloud + 1;
                this.localCloud = i8;
                return i8;
            } else if (i2 == 3) {
                int i10 = this.cloud + 1;
                this.cloud = i10;
                return i10;
            } else if (i2 != 4) {
                return 0;
            } else {
                int i11 = this.localPrivate + 1;
                this.localPrivate = i11;
                return i11;
            }
        }

        public int sum() {
            return this.local + this.cloud + this.localCloud + this.localPrivate;
        }

        public String toString() {
            MediaType mediaType2 = this.mediaType;
            if (mediaType2 == MediaType.Image) {
                StringBuilder sb2 = new StringBuilder("LI[");
                sb2.append(this.local);
                sb2.append("] CI[");
                sb2.append(this.cloud);
                sb2.append("] LCI[");
                sb2.append(this.localCloud);
                sb2.append("] LPI[");
                return C0086a.l(sb2, this.localPrivate, "]");
            } else if (mediaType2 == MediaType.Video) {
                StringBuilder sb3 = new StringBuilder("LV[");
                sb3.append(this.local);
                sb3.append("] CV[");
                sb3.append(this.cloud);
                sb3.append("] LCV[");
                sb3.append(this.localCloud);
                sb3.append("] LPV[");
                return C0086a.l(sb3, this.localPrivate, "]");
            } else {
                StringBuilder sb4 = new StringBuilder("LU[");
                sb4.append(this.local);
                sb4.append("] CU[");
                sb4.append(this.cloud);
                sb4.append("] LCU[");
                sb4.append(this.localCloud);
                sb4.append("] LPU[");
                return C0086a.l(sb4, this.localPrivate, "]");
            }
        }
    }

    private void dumpToFile() {
        try {
            Logger.writeStringsToFile(new File(AppResources.getAppContext().getFilesDir(), "dump.log").getAbsolutePath(), this.mExtras);
        } catch (Exception e) {
            a.s(e, new StringBuilder("dump to file failed e="), "TrashLogger");
        }
    }

    private String getDefault() {
        return "Req Total[" + this.mTotal + "] " + this.mStartData + " Success " + this.mSuccessData + " Fail " + this.mFailData;
    }

    public void dump(TrashLogType trashLogType, String str, boolean z) {
        DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
        deleteInstance.insertLog("[" + trashLogType + "] " + getDefault() + getDetail(z) + " [" + str + "]");
        dumpToFile();
    }

    public void failed(MediaType mediaType, StorageType storageType) {
        this.mFailData.append(mediaType, storageType);
    }

    public abstract String getDetail(boolean z);

    public int getImageFailedCount() {
        return this.mFailData.sum(MediaType.Image);
    }

    public int getImageSucceedCount() {
        return this.mSuccessData.sum(MediaType.Image);
    }

    public int getVideoFailedCount() {
        return this.mFailData.sum(MediaType.Video);
    }

    public int getVideoSucceedCount() {
        return this.mSuccessData.sum(MediaType.Video);
    }

    public boolean isSucceed() {
        return this.mSucceed;
    }

    public void preDump(TrashLogType trashLogType, int i2, int i7, int i8, boolean z, String str) {
        if (trashLogType == TrashLogType.DELETE_MULTIPLE_REQUEST) {
            DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
            StringBuilder sb2 = new StringBuilder("[");
            sb2.append(trashLogType);
            sb2.append("] Req Total [");
            sb2.append(i2);
            sb2.append("] I[");
            j.x(sb2, i7, "] V[", i8, "] A[");
            sb2.append(z);
            sb2.append("] [");
            sb2.append(str);
            sb2.append("]");
            deleteInstance.insertLog(sb2.toString());
            dumpToFile();
        }
    }

    public void scanned(int i2) {
        this.mRequestedScanSize = i2;
    }

    public void started(MediaType mediaType, StorageType storageType) {
        if (this.mStartData.append(mediaType, storageType) > 0) {
            this.mTotal++;
        }
    }

    public void succeed(MediaType mediaType, StorageType storageType) {
        if (this.mSuccessData.append(mediaType, storageType) > 0) {
            this.mSucceed = true;
        }
    }
}
