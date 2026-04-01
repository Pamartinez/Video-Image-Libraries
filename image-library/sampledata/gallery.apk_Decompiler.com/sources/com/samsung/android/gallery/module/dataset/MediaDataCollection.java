package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.StaleDataException;
import android.graphics.Bitmap;
import android.os.Bundle;
import c0.C0086a;
import com.samsung.android.gallery.module.concurrent.RwLock;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BufferedArray;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MediaDataCollection extends MediaDataRef {
    protected BufferedArray<MediaItem> mArrayData = new BufferedArray<>(0);
    private BufferedArray.IndexArray mCursorIndex;
    protected Cursor[] mCursors;
    protected final RwLock mLock = new RwLock();

    public MediaDataCollection(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$load$0(int i2, int i7, BufferedArray bufferedArray, Cursor cursor) {
        int min = Math.min(i2, 2000);
        while (i7 < min) {
            bufferedArray.set(i7, loadMediaItem(cursor, i7));
            i7++;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaItem lambda$read$3(Integer num) {
        return loadMediaItem(this.mCursors[0], getCursorPosition(num.intValue()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$readAsync$4(MediaData.OnDataReadListener onDataReadListener, int i2) {
        onDataReadListener.onDataReadCompleted(read(i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$swap$1(BufferedArray bufferedArray, Cursor[] cursorArr, int i2, Cursor[] cursorArr2) {
        if (this.mLock.acquireWriteLock()) {
            this.mArrayData = bufferedArray;
            this.mCursors = cursorArr;
            this.mCursorIndex = null;
            if (i2 != 0) {
                notifyChanged();
            }
            this.mLock.releaseWriteLock();
        }
        closeCursors(cursorArr2);
    }

    private void swap(Cursor[] cursorArr) {
        Throwable th;
        MediaDataCollection mediaDataCollection;
        Throwable th2;
        int loadAndCompare;
        String str;
        try {
            if (this.mLock.acquireWriteLock()) {
                long currentTimeMillis = System.currentTimeMillis();
                Cursor[] cursorArr2 = this.mCursors;
                Cursor cursor = cursorArr[0];
                int count = cursor.getCount();
                BufferedArray bufferedArray = new BufferedArray(count);
                if (this.mCursors == null) {
                    try {
                        loadAndCompare = load(bufferedArray, cursor);
                    } catch (StaleDataException | IllegalStateException | NullPointerException e) {
                        th2 = e;
                        mediaDataCollection = this;
                        try {
                            StringCompat stringCompat = mediaDataCollection.TAG;
                            Log.e(stringCompat, "swap > fail by destroy e=" + th2.getMessage());
                            mediaDataCollection.mLock.releaseWriteLock();
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        mediaDataCollection = this;
                        mediaDataCollection.mLock.releaseWriteLock();
                        throw th;
                    }
                } else {
                    loadAndCompare = loadAndCompare(bufferedArray, cursor);
                }
                int i2 = loadAndCompare;
                StringCompat stringCompat2 = this.TAG;
                StringBuilder sb2 = new StringBuilder("swap ");
                if (i2 == 0) {
                    str = "skip ";
                } else {
                    str = "";
                }
                sb2.append(str);
                sb2.append('{');
                sb2.append(count);
                sb2.append("} +");
                sb2.append(System.currentTimeMillis() - currentTimeMillis);
                Log.d(stringCompat2, sb2.toString());
                mediaDataCollection = this;
                try {
                    mediaDataCollection.runOnUiThread(new C0610o(mediaDataCollection, bufferedArray, cursorArr, i2, cursorArr2));
                    if (cursorArr2 == null) {
                        mediaDataCollection.preloadThumbnail(bufferedArray);
                    }
                } catch (StaleDataException | IllegalStateException | NullPointerException e7) {
                    e = e7;
                    th2 = e;
                    StringCompat stringCompat3 = mediaDataCollection.TAG;
                    Log.e(stringCompat3, "swap > fail by destroy e=" + th2.getMessage());
                    mediaDataCollection.mLock.releaseWriteLock();
                }
            } else {
                mediaDataCollection = this;
            }
        } catch (StaleDataException | IllegalStateException | NullPointerException e8) {
            e = e8;
            mediaDataCollection = this;
            th2 = e;
            StringCompat stringCompat32 = mediaDataCollection.TAG;
            Log.e(stringCompat32, "swap > fail by destroy e=" + th2.getMessage());
            mediaDataCollection.mLock.releaseWriteLock();
        } catch (Throwable th5) {
            th = th5;
            mediaDataCollection = this;
            th = th;
            mediaDataCollection.mLock.releaseWriteLock();
            throw th;
        }
        mediaDataCollection.mLock.releaseWriteLock();
    }

    public int compare(BufferedArray<MediaItem> bufferedArray, BufferedArray<MediaItem> bufferedArray2) {
        if (bufferedArray == null || bufferedArray2 == null) {
            return -1;
        }
        int size = bufferedArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!equalsItem(bufferedArray.get(i2), bufferedArray2.get(i2))) {
                return -1;
            }
        }
        return 0;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo(DataKey.DATA_CURSOR(this.mLocationKey), new C0609n(this, 0)).setTriggerPreloadedData());
        arrayList.add(new SubscriberInfo("command://event/DataChanged", new C0609n(this, 1)));
        arrayList.add(new SubscriberInfo("command://event/DataDirty", new C0609n(this, 2)));
    }

    public abstract boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2);

    public int getCount() {
        return this.mArrayData.size();
    }

    public int getCursorPosition(int i2) {
        BufferedArray.IndexArray indexArray = this.mCursorIndex;
        if (indexArray == null) {
            return i2;
        }
        return ((Integer) indexArray.get(i2)).intValue();
    }

    public abstract int getPresetCount();

    public abstract ThumbKind getPresetThumbnailKind();

    public int getRealCount() {
        return getCount();
    }

    public boolean isDataAvailable() {
        if (this.mCursors != null) {
            return true;
        }
        return false;
    }

    public boolean isItemValid(MediaItem mediaItem) {
        if (mediaItem == null || !FileUtils.exists(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    public int load(BufferedArray<MediaItem> bufferedArray, Cursor cursor) {
        int presetCount = getPresetCount();
        int size = bufferedArray.size();
        int min = Math.min(size, presetCount);
        for (int i2 = 0; i2 < min; i2++) {
            bufferedArray.set(i2, loadMediaItem(cursor, i2));
        }
        if (size > presetCount) {
            SimpleThreadPool.getInstance().execute(new C0611p(this, size, presetCount, (BufferedArray) bufferedArray, cursor));
        }
        return min;
    }

    public int loadAndCompare(BufferedArray<MediaItem> bufferedArray, Cursor cursor) {
        int size = bufferedArray.size();
        int min = Math.min(size, 2000);
        for (int i2 = 0; i2 < min; i2++) {
            bufferedArray.set(i2, loadMediaItem(cursor, i2));
        }
        if (min < size) {
            return min - size;
        }
        int size2 = size - this.mArrayData.size();
        if (size2 == 0) {
            return compare(bufferedArray, this.mArrayData);
        }
        return size2;
    }

    public MediaItem loadMediaItem(Cursor cursor, int i2) {
        if (this.mLock.acquireReadLock("loadMediaItem")) {
            try {
                return MediaItemLoader.loadMediaItem(cursor, i2);
            } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException e) {
                e.printStackTrace();
            } finally {
                this.mLock.releaseReadLock("loadMediaItem");
            }
        }
        Log.e(this.TAG, "loadMediaItem lock failed");
        return null;
    }

    public void onDataCursorChanged(Object obj, Bundle bundle) {
        Cursor[] cursorArr = (Cursor[]) obj;
        if (isInvalidCursors(cursorArr)) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "onDataCursorChanged failed. invalid cursors" + this);
            return;
        }
        try {
            StringCompat stringCompat2 = this.TAG;
            Log.i(stringCompat2, "onDataCursorChanged {" + this.mLocationKey + ',' + cursorArr.length + ',' + getCursorCount(cursorArr[0]) + '}');
            swap(cursorArr);
        } catch (Exception e) {
            StringCompat stringCompat3 = this.TAG;
            Log.e(stringCompat3, "onDataCursorChanged failed {" + this.mLocationKey + "} e=" + e.getMessage());
            if (!isDataAvailable()) {
                Log.w(this.TAG, "ignore");
                return;
            }
            throw e;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mArrayData = new BufferedArray<>(0);
        this.mCursorIndex = null;
        closeCursors(this.mCursors);
        this.mCursors = null;
    }

    public void preloadThumbnail(BufferedArray<MediaItem> bufferedArray) {
        ThumbKind presetThumbnailKind = getPresetThumbnailKind();
        int min = Math.min(bufferedArray.size(), getPresetCount());
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        C0612q qVar = new C0612q(0);
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "preloadThumbnail {" + presetThumbnailKind + ',' + min + '}');
        for (int i2 = 0; i2 < min; i2++) {
            instance.loadThumbnail(bufferedArray.get(i2), presetThumbnailKind, qVar);
        }
    }

    public MediaItem read(int i2) {
        if (i2 < 0 || i2 >= this.mArrayData.size()) {
            return null;
        }
        return this.mArrayData.computeIfAbsent(i2, new C0608m(0, this));
    }

    public void readAsync(int i2, MediaData.OnDataReadListener onDataReadListener, BooleanSupplier booleanSupplier) {
        MediaItem mediaItem = this.mArrayData.get(i2);
        if (mediaItem != null) {
            onDataReadListener.onDataReadCompleted(mediaItem);
        } else {
            ThreadUtil.postOnBgThread(new C0613s(this, onDataReadListener, i2, 0));
        }
    }

    public MediaItem readCache(int i2) {
        return this.mArrayData.get(i2);
    }

    public void removeItemAt(int i2) {
        if (i2 < 0 || i2 >= getCount()) {
            StringCompat stringCompat = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "removeItemAt failed {", "/");
            o2.append(getCount());
            o2.append('}');
            Log.e(stringCompat, o2.toString());
            return;
        }
        if (this.mCursorIndex == null) {
            this.mCursorIndex = new BufferedArray.IndexArray(getCount());
        }
        this.mCursorIndex.removeAt(i2);
        this.mArrayData.removeAt(i2);
        runOnUiThread(new r(0, this), 300);
    }

    public void requestData(String str) {
        if (BlackboardUtils.publishDataRequestForce(this.mBlackboard, str)) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("requestData {");
            if (str.equals(this.mLocationReference)) {
                str = ArgumentsUtil.removeArgs(str);
            }
            sb2.append(str);
            sb2.append("}");
            Log.i(stringCompat, sb2.toString());
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.e(stringCompat2, "requestData skip duplicated {" + ArgumentsUtil.removeArgs(str) + "}");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$preloadThumbnail$5(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
    }
}
