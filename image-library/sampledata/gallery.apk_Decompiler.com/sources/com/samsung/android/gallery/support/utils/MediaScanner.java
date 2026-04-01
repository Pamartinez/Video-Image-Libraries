package com.samsung.android.gallery.support.utils;

import A.a;
import Ad.C0720a;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.Def;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaScanner {
    private final String HASH_TAG = ("MediaScanner@" + Integer.toHexString(hashCode()));
    /* access modifiers changed from: private */
    public boolean mBulk;
    /* access modifiers changed from: private */
    public boolean mConnected;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<String> mDeferredQueue = new CopyOnWriteArrayList<>();
    /* access modifiers changed from: private */
    public MediaScannerListener mListener;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<String> mRequestQueue = new CopyOnWriteArrayList<>();
    /* access modifiers changed from: private */
    public MediaScannerConnection mScannerConnection;
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<String> mScanningQueue = new CopyOnWriteArrayList<>();
    private int mTotalRequested;
    /* access modifiers changed from: private */
    public int mTotalScanned;
    /* access modifiers changed from: private */
    public final Runnable mTryDisconnection = new C(4, this);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FolderMediaScanner extends FileMediaScanner {
        public boolean isCompleted() {
            return true;
        }

        public void scan(MediaScannerConnection mediaScannerConnection, String[] strArr) {
            SimpleThreadPool.getInstance().execute(new C(1, strArr));
        }
    }

    private void connect() {
        String str;
        MediaScannerConnection mediaScannerConnection = this.mScannerConnection;
        if (mediaScannerConnection == null) {
            final long currentTimeMillis = System.currentTimeMillis();
            this.mScannerConnection = new MediaScannerConnection(AppResources.getAppContext(), new MediaScannerConnection.MediaScannerConnectionClient() {
                /* access modifiers changed from: private */
                public /* synthetic */ void lambda$onMediaScannerConnected$0() {
                    if (MediaScanner.this.mRequestQueue.size() > 0) {
                        MediaScanner mediaScanner = MediaScanner.this;
                        MediaScannerConnection h5 = mediaScanner.mScannerConnection;
                        MediaScanner mediaScanner2 = MediaScanner.this;
                        mediaScanner.scanFiles(h5, (Collection<String>) mediaScanner2.cloneAndClear(mediaScanner2.mRequestQueue));
                    }
                }

                public void onMediaScannerConnected() {
                    MediaScanner.this.mConnected = true;
                    Log.i("MediaScanner", "onMediaScannerConnected " + MediaScanner.this + " +" + (System.currentTimeMillis() - currentTimeMillis));
                    ThreadUtil.postOnBgThread(new C(0, this));
                }

                public void onScanCompleted(String str, Uri uri) {
                    MediaScanner.this.mScanningQueue.remove(str);
                    MediaScanner mediaScanner = MediaScanner.this;
                    mediaScanner.mTotalScanned = mediaScanner.mTotalScanned + 1;
                    if (MediaScanner.this.mScanningQueue.isEmpty()) {
                        Log.i("MediaScanner", "onScanCompleted " + MediaScanner.this + " +" + (System.currentTimeMillis() - currentTimeMillis));
                        if (MediaScanner.this.mListener != null) {
                            MediaScanner.this.mListener.onCompleted();
                        }
                        if (MediaScanner.this.mRequestQueue.size() > 0) {
                            Log.e("MediaScanner", "scan missing {" + MediaScanner.this.mRequestQueue.size() + "}");
                            MediaScanner mediaScanner2 = MediaScanner.this;
                            MediaScannerConnection h5 = mediaScanner2.mScannerConnection;
                            MediaScanner mediaScanner3 = MediaScanner.this;
                            mediaScanner2.scanFiles(h5, (Collection<String>) mediaScanner3.cloneAndClear(mediaScanner3.mRequestQueue));
                        } else if (MediaScanner.this.mDeferredQueue.size() > 0) {
                            Log.i("MediaScanner", "scan deferred {" + MediaScanner.this.mDeferredQueue.size() + "}");
                            MediaScanner mediaScanner4 = MediaScanner.this;
                            MediaScannerConnection h6 = mediaScanner4.mScannerConnection;
                            MediaScanner mediaScanner5 = MediaScanner.this;
                            mediaScanner4.scanFiles(h6, (Collection<String>) mediaScanner5.cloneAndClear(mediaScanner5.mDeferredQueue));
                        } else if (MediaScanner.this.mBulk) {
                            ThreadUtil.removeCallbackOnBgThread(MediaScanner.this.mTryDisconnection);
                            ThreadUtil.postOnBgThreadDelayed(MediaScanner.this.mTryDisconnection, Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
                        } else {
                            MediaScanner.this.disconnect();
                        }
                    }
                }
            });
            if (ThreadUtil.isMainThread()) {
                SimpleThreadPool instance = SimpleThreadPool.getInstance();
                MediaScannerConnection mediaScannerConnection2 = this.mScannerConnection;
                Objects.requireNonNull(mediaScannerConnection2);
                instance.execute(new B(mediaScannerConnection2, 0));
            } else {
                this.mScannerConnection.connect();
            }
            Log.i("MediaScanner", "connect +" + (System.currentTimeMillis() - currentTimeMillis));
            return;
        }
        if (mediaScannerConnection.isConnected()) {
            str = "{c}";
        } else {
            str = "{d}";
        }
        Log.w("MediaScanner", "connect skip ".concat(str));
    }

    /* access modifiers changed from: private */
    public void disconnect() {
        Log.i("MediaScanner", "disconnect " + this);
        this.mConnected = false;
        MediaScannerConnection mediaScannerConnection = this.mScannerConnection;
        if (mediaScannerConnection != null) {
            this.mScannerConnection = null;
            SimpleThreadPool.getInstance().execute(new B(mediaScannerConnection, 1));
        }
    }

    public static boolean isValidPath(String str) {
        if (str == null || str.startsWith("/data/sec/")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.mScanningQueue.isEmpty()) {
            disconnect();
            return;
        }
        Log.i("MediaScanner", "disconnect skip {" + this.mScanningQueue.size() + "}");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$scanFiles$1(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$scanFiles$2(long j2, MediaScannerConnection.OnScanCompletedListener onScanCompletedListener, String str, Uri uri) {
        if (onScanCompletedListener != null) {
            onScanCompletedListener.onScanCompleted(str, uri);
        }
    }

    public static void scanFile(String str, MediaScannerListener mediaScannerListener) {
        scanFiles((Collection<String>) Collections.singletonList(str), mediaScannerListener);
    }

    public static void scanFolder(String str, MediaScannerListener mediaScannerListener) {
        scanFolders(Collections.singletonList(str), mediaScannerListener);
    }

    public static void scanFolders(Collection<String> collection, MediaScannerListener mediaScannerListener) {
        new FolderMediaScanner().scan(collection, mediaScannerListener);
    }

    public ArrayList<String> cloneAndClear(Collection<String> collection) {
        ArrayList<String> arrayList = new ArrayList<>(collection);
        collection.clear();
        return arrayList;
    }

    public void scanFiles(Collection<String> collection) {
        ArrayList arrayList = (ArrayList) collection.stream().filter(new C0680s(5)).collect(Collectors.toCollection(new C0720a(1)));
        if (arrayList.size() != 0) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str.contains("/.Trash/com.sec.android.gallery3d")) {
                    this.mDeferredQueue.add(str);
                } else {
                    arrayList2.add(str);
                }
            }
            MediaScannerConnection mediaScannerConnection = this.mScannerConnection;
            if (mediaScannerConnection == null || !this.mConnected) {
                this.mRequestQueue.addAll(arrayList2);
                connect();
                return;
            }
            scanFiles(mediaScannerConnection, (Collection<String>) arrayList2);
        }
    }

    public MediaScanner setBulk(boolean z) {
        a.v("setBulk {", "}", "MediaScanner", z);
        this.mBulk = z;
        return this;
    }

    public MediaScanner setListener(MediaScannerListener mediaScannerListener) {
        this.mListener = mediaScannerListener;
        return this;
    }

    public String toString() {
        String str;
        String str2;
        MediaScannerConnection mediaScannerConnection = this.mScannerConnection;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.HASH_TAG);
        sb2.append("{");
        if (mediaScannerConnection == null) {
            str = "n";
        } else if (mediaScannerConnection.isConnected()) {
            str = "c";
        } else {
            str = "d";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mBulk) {
            str2 = "bulk";
        } else {
            str2 = "non-bulk";
        }
        sb2.append(str2);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mTotalScanned);
        sb2.append("/");
        sb2.append(this.mTotalRequested);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mScanningQueue.size());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mRequestQueue.size());
        sb2.append("}");
        return sb2.toString();
    }

    public static boolean scanFolder(Context context, String str, MediaScannerConnection.OnScanCompletedListener onScanCompletedListener) {
        return scanFiles(context, new String[]{str}, (String[]) null, onScanCompletedListener);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FileMediaScanner {
        /* access modifiers changed from: private */
        public MediaScannerConnection mScannerConnection;
        /* access modifiers changed from: private */
        public final HashSet<String> mScanningQueue = new HashSet<>();
        /* access modifiers changed from: private */
        public int mTotalScanned;

        /* access modifiers changed from: private */
        public static /* synthetic */ String[] lambda$scan$0(int i2) {
            return new String[i2];
        }

        public boolean isCompleted() {
            return this.mScanningQueue.isEmpty();
        }

        public void scan(Collection<String> collection, MediaScannerListener mediaScannerListener) {
            final long currentTimeMillis = System.currentTimeMillis();
            final String[] strArr = (String[]) collection.stream().filter(new C0680s(5)).toArray(new D(0));
            if (strArr.length == 0) {
                Log.e("FileMediaScanner", "scan skip. no valid path list");
                return;
            }
            this.mScanningQueue.addAll(Arrays.asList(strArr));
            final MediaScannerListener mediaScannerListener2 = mediaScannerListener;
            this.mScannerConnection = new MediaScannerConnection(AppResources.getAppContext(), new MediaScannerConnection.MediaScannerConnectionClient() {
                public void onMediaScannerConnected() {
                    Log.i("FileMediaScanner", "onMediaScannerConnected {" + strArr.length + "} +" + (System.currentTimeMillis() - currentTimeMillis));
                    FileMediaScanner fileMediaScanner = FileMediaScanner.this;
                    fileMediaScanner.scan(fileMediaScanner.mScannerConnection, strArr);
                }

                public void onScanCompleted(String str, Uri uri) {
                    FileMediaScanner.this.mScanningQueue.remove(str);
                    FileMediaScanner fileMediaScanner = FileMediaScanner.this;
                    fileMediaScanner.mTotalScanned = fileMediaScanner.mTotalScanned + 1;
                    if (FileMediaScanner.this.isCompleted()) {
                        Log.i("FileMediaScanner", "onScanCompleted {" + FileMediaScanner.this.mTotalScanned + "/" + strArr.length + "} +" + (System.currentTimeMillis() - currentTimeMillis));
                        MediaScannerListener mediaScannerListener = mediaScannerListener2;
                        if (mediaScannerListener != null) {
                            mediaScannerListener.onCompleted();
                        }
                        if (FileMediaScanner.this.mScannerConnection != null) {
                            FileMediaScanner.this.mScannerConnection.disconnect();
                            FileMediaScanner.this.mScannerConnection = null;
                        }
                    }
                }
            });
            if (ThreadUtil.isMainThread()) {
                SimpleThreadPool instance = SimpleThreadPool.getInstance();
                MediaScannerConnection mediaScannerConnection = this.mScannerConnection;
                Objects.requireNonNull(mediaScannerConnection);
                instance.execute(new B(mediaScannerConnection, 0));
            } else {
                this.mScannerConnection.connect();
            }
            Log.d("FileMediaScanner", "scan {" + strArr.length + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        }

        public void scan(MediaScannerConnection mediaScannerConnection, String[] strArr) {
            for (String scanFile : strArr) {
                mediaScannerConnection.scanFile(scanFile, (String) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void scanFiles(MediaScannerConnection mediaScannerConnection, Collection<String> collection) {
        this.mTotalRequested = collection.size() + this.mTotalRequested;
        this.mScanningQueue.addAll(collection);
        for (String scanFile : collection) {
            mediaScannerConnection.scanFile(scanFile, (String) null);
        }
    }

    public static void scanFiles(Collection<String> collection, MediaScannerListener mediaScannerListener) {
        new FileMediaScanner().scan(collection, mediaScannerListener);
    }

    public static boolean scanFiles(Context context, String[] strArr, String[] strArr2, MediaScannerConnection.OnScanCompletedListener onScanCompletedListener) {
        String str;
        String[] strArr3 = (strArr == null || strArr.length <= 0) ? new String[0] : (String[]) Arrays.stream(strArr).filter(new C0464a(13)).filter(new C0680s(5)).toArray(new D(1));
        if (strArr3.length == 0) {
            StringBuilder sb2 = new StringBuilder("scanFiles skip. ");
            if (strArr == null || strArr.length <= 0) {
                str = "empty";
            } else {
                str = "not supported " + FileUtils.info(strArr[0]);
            }
            sb2.append(str);
            Log.e("MediaScanner", sb2.toString());
            if (onScanCompletedListener != null) {
                onScanCompletedListener.onScanCompleted((String) null, (Uri) null);
            }
            return false;
        }
        MediaScannerConnection.scanFile(context, strArr3, strArr2, new A(onScanCompletedListener));
        return true;
    }
}
