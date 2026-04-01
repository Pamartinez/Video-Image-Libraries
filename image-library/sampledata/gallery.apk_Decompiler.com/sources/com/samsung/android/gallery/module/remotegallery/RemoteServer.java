package com.samsung.android.gallery.module.remotegallery;

import Fa.C0571z;
import K7.a;
import android.content.Context;
import android.database.Cursor;
import ba.C0582a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureUtils;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoteServer {
    static final Object LOCK = new Object();
    private static ConcurrentHashMap<String, String> mConnectedClient = new ConcurrentHashMap<>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private ArrayList<Integer> mAlbumIds;
    private String mPassCode;
    private boolean mRun;
    private ArrayList<MediaItem> mSelectedFiles;
    public IThumbnailLoader mThumbnailLoader;
    ServerSocket serverSocket;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Holder {
        static RemoteServer sInstance = new RemoteServer(0);
    }

    public /* synthetic */ RemoteServer(int i2) {
        this();
    }

    public static RemoteServer getInstance() {
        return Holder.sInstance;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getFileList$2(int i2, int i7, QueryParams queryParams) {
        queryParams.setGroupTypes(new GroupType[0]).setLimit(i2, i7);
        synchronized (LOCK) {
            try {
                ArrayList<Integer> arrayList = this.mAlbumIds;
                if (arrayList != null) {
                    queryParams.addAlbumIds((Collection<Integer>) arrayList);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFiles$0(MediaItem mediaItem) {
        this.mSelectedFiles.add(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$start$1(CountDownLatch countDownLatch) {
        RemoteGalleryUtil.wakeUp();
        try {
            this.serverSocket = new ServerSocket(5999);
            countDownLatch.countDown();
            Log.i("RemoteServer", "start wait client");
            try {
                this.mRun = true;
                while (this.mRun) {
                    Socket accept = this.serverSocket.accept();
                    String inetAddress = accept.getInetAddress().toString();
                    Log.i("RemoteServer", "Client connected : " + inetAddress + NumericEnum.SEP + accept.getPort());
                    if (!mConnectedClient.containsKey(inetAddress)) {
                        Context appContext = AppResources.getAppContext();
                        Utils.showToast(appContext, "Client connected : " + inetAddress);
                        mConnectedClient.put(inetAddress, inetAddress);
                    }
                    try {
                        threadPool.execute(new ConnectionWrap(accept, this.mPassCode, this.mThumbnailLoader));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    this.serverSocket.close();
                } catch (IOException e7) {
                    Log.w("RemoteServer", "socket close failed : 5999");
                    e7.printStackTrace();
                }
            } catch (IOException e8) {
                e8.printStackTrace();
            }
        } catch (IOException unused) {
            Log.w("RemoteServer", "socket open failed : 5999");
            countDownLatch.countDown();
        } catch (Throwable th) {
            countDownLatch.countDown();
            throw th;
        }
    }

    public void addFileToTop(MediaItem mediaItem) {
        if (!this.mSelectedFiles.contains(mediaItem)) {
            this.mSelectedFiles.add(0, mediaItem);
        }
    }

    public void clearSelectedData() {
        this.mSelectedFiles.clear();
        this.mAlbumIds.clear();
    }

    public String getDeepLink(String str) {
        return RemoteGalleryUtil.createDeeplink(str, this.mPassCode);
    }

    public ArrayList<MediaItem> getFileList(int i2, int i7) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        if (this.mSelectedFiles != null) {
            synchronized (LOCK) {
                try {
                    ArrayList<MediaItem> arrayList2 = this.mSelectedFiles;
                    if (arrayList2 != null && !arrayList2.isEmpty()) {
                        ArrayList<MediaItem> arrayList3 = this.mSelectedFiles;
                        arrayList.addAll(arrayList3.subList(i2, Math.min(i7 + i2, arrayList3.size())));
                        return arrayList;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        Cursor query = DbCompat.query(DbKey.ALL_PICTURES, new a(this, i2, i7, 1));
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(MediaItemLoader.load(query));
                    } while (query.moveToNext());
                    query.close();
                    return arrayList;
                }
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        Log.i("RemoteServer", "fail get file");
        if (query != null) {
            query.close();
        }
        return arrayList;
        throw th;
    }

    public boolean isRun() {
        return this.mRun;
    }

    public void setFiles(MediaItem[] mediaItemArr) {
        Arrays.stream(mediaItemArr).filter(new C0571z(4)).forEach(new C0582a(11, this));
    }

    public void setThumbnailLoader(IThumbnailLoader iThumbnailLoader) {
        this.mThumbnailLoader = iThumbnailLoader;
    }

    public String start() {
        if (this.mRun) {
            return this.mPassCode;
        }
        this.mPassCode = SecureUtils.generateRandomString(8) + "";
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(new c(2, this, countDownLatch));
        thread.setPriority(10);
        thread.start();
        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.mPassCode;
    }

    public void stop() {
        this.mRun = false;
    }

    private RemoteServer() {
        this.serverSocket = null;
        this.mAlbumIds = new ArrayList<>();
        this.mSelectedFiles = new ArrayList<>();
    }
}
