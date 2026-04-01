package com.samsung.android.gallery.module.remotegallery;

import android.graphics.Bitmap;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sum.core.types.NumericEnum;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoteWebServer {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private boolean mRun;
    public IThumbnailLoader mThumbnailLoader;
    ServerSocket serverSocket;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Holder {
        static RemoteWebServer sInstance = new RemoteWebServer(0);
    }

    public /* synthetic */ RemoteWebServer(int i2) {
        this();
    }

    public static RemoteWebServer getInstance() {
        return Holder.sInstance;
    }

    public static Bitmap getQr(String str) {
        return RemoteGalleryUtil.createWebQrCode(str, String.valueOf(5858));
    }

    public static String getWebLink(String str) {
        return RemoteGalleryUtil.createWebLink(str, String.valueOf(5858));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$start$0(CountDownLatch countDownLatch) {
        try {
            this.serverSocket = new ServerSocket(5858);
            countDownLatch.countDown();
            Log.i("RemoteWebServer", "start wait client");
            try {
                this.mRun = true;
                while (this.mRun) {
                    Socket accept = this.serverSocket.accept();
                    String inetAddress = accept.getInetAddress().toString();
                    Log.i("RemoteWebServer", "Client connected : " + inetAddress + NumericEnum.SEP + accept.getPort());
                    try {
                        threadPool.execute(new WebConnectionWrap(accept, this.mThumbnailLoader));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    this.serverSocket.close();
                } catch (IOException e7) {
                    Log.w("RemoteWebServer", "socket close failed : 5858");
                    e7.printStackTrace();
                }
            } catch (IOException e8) {
                e8.printStackTrace();
            }
        } catch (IOException unused) {
            Log.w("RemoteWebServer", "socket open failed : 5858");
            countDownLatch.countDown();
        } catch (Throwable th) {
            countDownLatch.countDown();
            throw th;
        }
    }

    public boolean isRun() {
        return this.mRun;
    }

    public void setThumbnailLoader(IThumbnailLoader iThumbnailLoader) {
        this.mThumbnailLoader = iThumbnailLoader;
    }

    public void start() {
        if (!this.mRun) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Thread thread = new Thread(new c(3, this, countDownLatch));
            thread.setPriority(10);
            thread.start();
            try {
                countDownLatch.await(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.mRun = false;
    }

    private RemoteWebServer() {
        this.serverSocket = null;
    }
}
