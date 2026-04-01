package com.samsung.android.gallery.module.dataset.tables;

import Z8.a;
import Z8.b;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.StaleDataException;
import android.os.Handler;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.DefaultRecord;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DefaultTable<T extends DefaultRecord> implements IMediaDataTable<T>, Closeable {
    private final Object LOCK;
    protected final String TAG;
    protected int mCount;
    protected Cursor mCursor;
    private Handler mCursorHandler;
    public ArrayList<T> mDataList;
    protected int mFakeLoadingCount;
    private volatile int mLastUsedIndex;
    private OnLoadDoneListener mNotifier;
    protected int mRealCount;
    protected boolean mSupportFakeLoading;
    private boolean mVideoTouched;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnLoadDoneListener {
        void onLoadDone();
    }

    public DefaultTable(Cursor cursor) {
        this.TAG = tag();
        this.LOCK = new Object();
        this.mNotifier = null;
        this.mFakeLoadingCount = getFakeLoadingCount();
        onConstruct(cursor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$readDataCursor$0(boolean z, long j2, ThreadPool.JobContext jobContext) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            int min = Math.min(getMaxPreloadCount(), this.mRealCount);
            if (!z) {
                loadRealData(this.mCount, min);
            }
            String str = this.TAG;
            Log.p(str, "data loading async {" + this.mCount + "~" + min + ",total=" + this.mRealCount + ",delay=" + (currentTimeMillis - j2) + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            this.mCount = this.mRealCount;
            notifyChanged();
            return null;
        } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException unused) {
            return null;
        } catch (NullPointerException e) {
            String message = e.getMessage();
            if (message == null || !message.contains("CursorWindow.get")) {
                Cursor cursor = this.mCursor;
                if (cursor == null || cursor.isClosed()) {
                    Log.e((CharSequence) this.TAG, "data loading failed(4). table closed", (Throwable) e);
                    return null;
                }
                throw e;
            }
            Log.e((CharSequence) this.TAG, "data loading failed(3). exception during full loading phase", (Throwable) e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$readDataCursor$1(boolean z, long j2) {
        ThreadPool.getInstance().submit(new b(this, z, j2));
    }

    private void loadRealData(int i2, int i7) {
        MediaItem createMediaItem;
        int i8 = i2;
        while (i8 < i7) {
            try {
                if (this.mDataList.get(i8) == null && (createMediaItem = createMediaItem(this.mCursor, i8)) != null) {
                    setToDataList(i8, createMediaItem);
                    onDataLoaded(i8, createMediaItem);
                }
                i8++;
            } catch (IndexOutOfBoundsException e) {
                String str = this.TAG;
                Log.e(str, "mRealCount = " + this.mRealCount + ",arr size = " + this.mDataList.size() + " ,s =" + i2 + " ,e = " + i7);
                Log.e(this.TAG, e.getMessage());
                return;
            }
        }
    }

    private void notifyChanged() {
        OnLoadDoneListener onLoadDoneListener = this.mNotifier;
        if (onLoadDoneListener != null) {
            onLoadDoneListener.onLoadDone();
        }
    }

    private boolean readDataCursor(boolean z) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.mSupportFakeLoading || this.mRealCount <= (i2 = this.mFakeLoadingCount)) {
            int i7 = this.mRealCount;
            this.mCount = i7;
            if (!z) {
                try {
                    loadRealData(0, i7);
                } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException e) {
                    Log.e((CharSequence) this.TAG, "data loading failed(5). exception during full loading phase", e);
                    return false;
                }
            }
            String str = this.TAG;
            Log.d(str, "data loading {0~" + this.mCount + "} +", Boolean.valueOf(z), Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            return true;
        }
        if (!z) {
            try {
                loadRealData(0, i2);
            } catch (CursorIndexOutOfBoundsException | StaleDataException | ArrayIndexOutOfBoundsException | IllegalStateException e7) {
                Log.e((CharSequence) this.TAG, "data loading failed(1). exception happen during full loading phase", e7);
                return false;
            }
        }
        this.mCount = this.mFakeLoadingCount;
        this.mCursorHandler.post(new a(this, z, currentTimeMillis));
        String str2 = this.TAG;
        Log.d(str2, "data loading partial {0~" + this.mFakeLoadingCount + ",total=" + this.mRealCount + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        return false;
    }

    public void close() {
        this.mCursorHandler.removeCallbacksAndMessages((Object) null);
        closeCursor();
    }

    public void closeCursor() {
        Utils.closeSilently(this.mCursor);
    }

    public abstract MediaItem createMediaItem(Cursor cursor, int i2);

    public abstract T createRecordInstance(MediaItem mediaItem);

    public MediaItem get(int i2) {
        try {
            this.mLastUsedIndex = i2;
            DefaultRecord defaultRecord = (DefaultRecord) this.mDataList.get(i2);
            if (defaultRecord == null) {
                return null;
            }
            return defaultRecord.get();
        } catch (IndexOutOfBoundsException e) {
            String str = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "get failed {", "} e=");
            o2.append(e.getMessage());
            Log.e(str, o2.toString());
            return null;
        }
    }

    public abstract int getFakeLoadingCount();

    public int getLastUsedIndex() {
        return this.mLastUsedIndex;
    }

    public int getLoadedCount() {
        return this.mCount;
    }

    public int getMaxPreloadCount() {
        return 10000;
    }

    public int getRealCount() {
        return this.mRealCount;
    }

    public boolean init(boolean z) {
        return readDataCursor(z);
    }

    public boolean isFullLoaded() {
        if (this.mRealCount == this.mCount) {
            return true;
        }
        return false;
    }

    public boolean isVideoTouched() {
        return this.mVideoTouched;
    }

    public void load(int i2) {
        loadRealData(i2, i2 + 1);
    }

    public MediaItem loadAndGet(int i2) {
        try {
            load(i2);
            return get(i2);
        } catch (Exception e) {
            A.a.s(e, C0086a.o(i2, "loadAndGet failed {", "} e="), this.TAG);
            return null;
        }
    }

    public void onConstruct(Cursor cursor) {
        boolean z;
        synchronized (this.LOCK) {
            this.mCursor = cursor;
            this.mRealCount = cursor.getCount();
            this.mDataList = new ArrayList<>(Collections.nCopies(this.mRealCount, (Object) null));
            if (this.mNotifier != null) {
                z = true;
            } else {
                z = false;
            }
            this.mSupportFakeLoading = z;
            this.mCursorHandler = ThreadUtil.getBackgroundThreadHandler();
        }
    }

    public void setToDataList(int i2, MediaItem mediaItem) {
        if (this.mDataList.isEmpty()) {
            this.mDataList.add(createRecordInstance(mediaItem));
        } else {
            this.mDataList.set(i2, createRecordInstance(mediaItem));
        }
    }

    public void setVideoTouched(boolean z) {
        this.mVideoTouched = z;
    }

    public abstract String tag();

    public String toString() {
        String str;
        String simpleName = getClass().getSimpleName();
        Cursor cursor = this.mCursor;
        if (cursor == null || cursor.isClosed()) {
            str = "{C}";
        } else {
            str = "";
        }
        return simpleName.concat(str);
    }

    public boolean init() {
        return readDataCursor(false);
    }

    public DefaultTable(Cursor cursor, OnLoadDoneListener onLoadDoneListener) {
        this.TAG = tag();
        this.LOCK = new Object();
        this.mNotifier = onLoadDoneListener;
        this.mFakeLoadingCount = getFakeLoadingCount();
        onConstruct(cursor);
    }

    public DefaultTable(Cursor cursor, OnLoadDoneListener onLoadDoneListener, int i2) {
        this.TAG = tag();
        this.LOCK = new Object();
        this.mNotifier = onLoadDoneListener;
        this.mFakeLoadingCount = i2 == 0 ? getFakeLoadingCount() : i2;
        onConstruct(cursor);
    }

    public void onDataLoaded(int i2, MediaItem mediaItem) {
    }
}
