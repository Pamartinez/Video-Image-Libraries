package com.samsung.android.gallery.module.fileio.redact;

import A.a;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.GroupItemLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.utils.MediaFileScanner;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileEditor extends FileRedactor {
    private OnProgress mCallback;
    private boolean mCancel = false;
    private int mCount;
    private int mFail = 0;
    private FileItemInterface[] mFiles;
    private boolean mIgnoreGroup = false;
    private Function<FileItemInterface, Boolean> mIsValidItem = new Object();
    protected MediaFileScanner mMediaFileScanner;
    private Function<FileItemInterface, Boolean> mOperation;
    private ArrayList<MediaItem> mSimilarShotCandidate = new ArrayList<>();
    private int mSuccess = 0;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.function.Function<com.samsung.android.gallery.database.dbtype.FileItemInterface, java.lang.Boolean>, java.lang.Object] */
    public FileEditor(FileItemInterface[] fileItemInterfaceArr, Function<FileItemInterface, Boolean> function, OnProgress onProgress) {
        this.mFiles = fileItemInterfaceArr;
        this.mOperation = function;
        this.mCallback = onProgress;
        this.mCount = fileItemInterfaceArr.length;
    }

    private boolean finish(int i2) {
        int i7 = this.mCount;
        boolean z = true;
        if (i2 >= i7) {
            this.mCallback.onCompleted(i7, this.mSuccess, this.mFail);
        } else if (this.mCancel) {
            this.mCallback.onCanceled(i7, this.mSuccess, this.mFail);
        } else {
            z = false;
        }
        if (z) {
            Log.d(this.TAG, "Finished");
            if (this.mSimilarShotCandidate.size() > 0) {
                SimilarPhotoHelper.clearSimilarPhoto(AppResources.getAppContext(), this.mSimilarShotCandidate);
            }
            close();
        }
        return z;
    }

    private boolean isInvalidItem(int i2) {
        if (this.mIsValidItem.apply(this.mFiles[i2]).booleanValue()) {
            return false;
        }
        String str = this.TAG;
        Log.d(str, this.mFiles[i2] + "is invalid skip");
        onFileOperation(false, i2);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$close$6() {
        this.mMediaFileScanner.finishScan();
        this.mMediaFileScanner = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadGroup$3(int i2) {
        int i7 = this.mSuccess + 1;
        this.mSuccess = i7;
        this.mCallback.onProcess(this.mCount, i7 + this.mFail);
        String str = this.TAG;
        Log.d(str, "runNextFile on loadGroup " + i2 + 1);
        runNextFile(i2 + 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFileOperation$2(int i2) {
        runNextFile(i2 + 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runGroupItems$4(ArrayList arrayList, int i2, boolean z, Boolean bool, Runnable runnable) {
        boolean z3 = true;
        int i7 = i2 + 1;
        if (!z || !bool.booleanValue()) {
            z3 = false;
        }
        runGroupItems(arrayList, i7, z3, runnable);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runGroupItems$5(FileItemInterface fileItemInterface, ArrayList arrayList, int i2, boolean z, Runnable runnable, Boolean bool) {
        MediaFileScanner mediaFileScanner = this.mMediaFileScanner;
        if (mediaFileScanner != null) {
            mediaFileScanner.scan(fileItemInterface);
        }
        this.mThreadHandler.run(new h(this, arrayList, i2, z, bool, runnable));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runNextFile$1(FileItemInterface fileItemInterface, int i2, Boolean bool) {
        String str = this.TAG;
        Log.d(str, "C2pa update callback " + fileItemInterface + "   " + i2 + " / " + this.mFiles.length);
        onFileOperation(bool.booleanValue(), i2);
    }

    private boolean loadGroup(int i2) {
        if (!this.mIgnoreGroup && this.mFiles[i2].isGroupShot()) {
            ArrayList<MediaItem> groupShotList = GroupItemLoader.getGroupShotList((MediaItem) this.mFiles[i2]);
            if (groupShotList.size() > 1) {
                runGroupItems(groupShotList, 0, true, new f(this, i2, 1));
                return true;
            }
        }
        return false;
    }

    private void onFileOperation(boolean z, int i2) {
        if (z) {
            this.mSuccess++;
        } else {
            this.mFail++;
        }
        MediaFileScanner mediaFileScanner = this.mMediaFileScanner;
        if (mediaFileScanner != null) {
            mediaFileScanner.scan(this.mFiles[i2]);
        }
        this.mCallback.onProcess(this.mCount, this.mSuccess + this.mFail);
        Log.d(this.TAG, "runNextFile on FileOperation " + (i2 + 1));
        this.mThreadHandler.run(new f(this, i2, 0));
    }

    private boolean processNoneC2pa(int i2) {
        if (!PocFeatures.isEnabled(PocFeatures.C2paSecMp) || DetailsData.of(this.mFiles[i2]).hasC2pa) {
            return false;
        }
        onFileOperation(this.mOperation.apply(this.mFiles[i2]).booleanValue(), i2);
        return true;
    }

    private void runGroupItems(ArrayList<? extends FileItemInterface> arrayList, int i2, boolean z, Runnable runnable) {
        if (this.mCancel || arrayList.size() <= i2) {
            Runnable runnable2 = runnable;
            if (z) {
                this.mSuccess++;
            } else {
                this.mFail++;
            }
            runnable2.run();
            return;
        }
        FileItemInterface fileItemInterface = (FileItemInterface) arrayList.get(i2);
        this.mC2paWrapper.update(C2paWrapper.Manifest.actionEdit, fileItemInterface, this.mOperation, new g(this, fileItemInterface, arrayList, i2, z, runnable));
        if (!fileItemInterface.isSimilarShot()) {
            this.mSimilarShotCandidate.add((MediaItem) fileItemInterface);
        }
    }

    private void runNextFile(int i2) {
        a.k(i2, "runNextFile ", this.TAG);
        if (!finish(i2) && !isInvalidItem(i2) && !loadGroup(i2) && !processNoneC2pa(i2)) {
            FileItemInterface fileItemInterface = this.mFiles[i2];
            this.mC2paWrapper.update(C2paWrapper.Manifest.actionEdit, fileItemInterface, this.mOperation, new d(this, fileItemInterface, i2));
        }
    }

    public void cancel() {
        this.mCancel = true;
    }

    public void close() {
        super.close();
        if (this.mMediaFileScanner != null) {
            this.mThreadHandler.run(new e(this));
        }
    }

    public void handleFile() {
        runNextFile(0);
    }

    public FileEditor ignoreGroup() {
        this.mIgnoreGroup = true;
        return this;
    }

    public FileEditor setCheckValidFunction(Function<FileItemInterface, Boolean> function) {
        this.mIsValidItem = function;
        return this;
    }

    public FileEditor withMediaScan(BiConsumer<Integer, Integer> biConsumer) {
        this.mMediaFileScanner = new MediaFileScanner(AppResources.getAppContext(), biConsumer);
        return this;
    }
}
