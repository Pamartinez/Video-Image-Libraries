package com.samsung.android.gallery.module.utils;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import o4.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaFileScanner {
    private static final boolean SUPPORT_C2PA = Features.isEnabled(Features.SUPPORT_C2PA);
    /* access modifiers changed from: private */
    public final Object LOCK = new Object();
    private final BiConsumer<Integer, Integer> mCallback;
    /* access modifiers changed from: private */
    public boolean mConnected;
    /* access modifiers changed from: private */
    public boolean mDisconnectOnFinish;
    private final long mElapsed = System.currentTimeMillis();
    /* access modifiers changed from: private */
    public final HashSet<FileItemInterface> mRequestQueue = new HashSet<>();
    private final MediaScannerConnection mScannerConnection;
    /* access modifiers changed from: private */
    public final ConcurrentHashMap<String, FileItemInterface> mScanningQueue = new ConcurrentHashMap<>();
    private int mTotalRequested;
    /* access modifiers changed from: private */
    public int mTotalScanned;

    public MediaFileScanner(Context context, BiConsumer<Integer, Integer> biConsumer) {
        this.mCallback = biConsumer;
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(context, new MediaScannerConnection.MediaScannerConnectionClient() {
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$onMediaScannerConnected$0(FileItemInterface fileItemInterface) {
                MediaFileScanner.this.scanFile(fileItemInterface);
            }

            public void onMediaScannerConnected() {
                HashSet hashSet;
                MediaFileScanner.this.mConnected = true;
                synchronized (MediaFileScanner.this.LOCK) {
                    hashSet = new HashSet(MediaFileScanner.this.mRequestQueue);
                    MediaFileScanner.this.mRequestQueue.clear();
                }
                hashSet.forEach(new b(1, this));
            }

            public void onScanCompleted(String str, Uri uri) {
                MediaFileScanner.this.mScanningQueue.remove(str);
                MediaFileScanner mediaFileScanner = MediaFileScanner.this;
                mediaFileScanner.mTotalScanned = mediaFileScanner.mTotalScanned + 1;
                if (MediaFileScanner.this.isCompleted() && MediaFileScanner.this.mDisconnectOnFinish) {
                    MediaFileScanner.this.notifyComplete();
                }
            }
        });
        this.mScannerConnection = mediaScannerConnection;
        mediaScannerConnection.connect();
    }

    /* access modifiers changed from: private */
    public boolean isCompleted() {
        synchronized (this.LOCK) {
            try {
                if (!this.mRequestQueue.isEmpty()) {
                    this.mRequestQueue.forEach(new a(18, this));
                    this.mRequestQueue.clear();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return this.mScanningQueue.isEmpty();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$isCompleted$0(FileItemInterface fileItemInterface) {
        this.mScanningQueue.put(fileItemInterface.getPath(), fileItemInterface);
    }

    /* access modifiers changed from: private */
    public void notifyComplete() {
        this.mScannerConnection.disconnect();
        BiConsumer<Integer, Integer> biConsumer = this.mCallback;
        if (biConsumer != null) {
            biConsumer.accept(Integer.valueOf(this.mTotalRequested), Integer.valueOf(this.mTotalScanned));
        }
    }

    /* access modifiers changed from: private */
    public void scanFile(FileItemInterface fileItemInterface) {
        this.mTotalRequested++;
        this.mScanningQueue.put(fileItemInterface.getPath(), fileItemInterface);
        this.mScannerConnection.scanFile(fileItemInterface.getPath(), (String) null);
    }

    public void finishScan() {
        this.mDisconnectOnFinish = true;
        if (isCompleted()) {
            notifyComplete();
        }
    }

    public void scan(FileItemInterface fileItemInterface) {
        if (fileItemInterface != null && fileItemInterface.getPath() != null) {
            if (this.mConnected) {
                scanFile(fileItemInterface);
                return;
            }
            synchronized (this.LOCK) {
                this.mRequestQueue.add(fileItemInterface);
            }
        }
    }

    public static void scanFile(FileItemInterface fileItemInterface, MediaScannerConnection.OnScanCompletedListener onScanCompletedListener) {
        if (fileItemInterface != null && fileItemInterface.getPath() != null) {
            MediaScannerConnection.scanFile(AppResources.getAppContext(), new String[]{fileItemInterface.getPath()}, new String[]{fileItemInterface.getMimeType()}, onScanCompletedListener);
        }
    }
}
