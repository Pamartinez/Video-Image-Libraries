package com.samsung.android.gallery.support.library.v0.system;

import F.b;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;
import com.samsung.android.gallery.support.library.abstraction.DvfsManagerCompat;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemBoosterCompat extends BoosterCompat {
    private static volatile SemBoosterCompat sInstance;
    private DvfsManagerCompat mBusMinLock;
    private final WeakReference<Context> mContext;
    private DvfsManagerCompat mCoreMinLock;
    private DvfsManagerCompat mCpuMinLock;
    private DvfsManagerCompat mDvfsHelperScroll;
    private DvfsManagerCompat mGpuMinLock;
    private Handler mHandler = new Handler(this.mHandlerThread.getLooper(), new b(1, this));
    private HandlerThread mHandlerThread;
    private DvfsManagerCompat mTouchBooster;
    private DvfsManagerCompat mTouchTailBooster;

    public SemBoosterCompat(Context context) {
        this.mContext = new WeakReference<>(context);
        HandlerThread handlerThread = new HandlerThread("SemBoosterCompat", 10);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
    }

    private void acquireFlickBoostInternal() {
        getTouchBooster().acquire();
    }

    private void acquireGpuBoostInternal(int i2) {
        getGpuMinLock().acquire(Math.min(i2, 1000));
    }

    private DvfsManagerCompat getBusMinLock() {
        if (this.mBusMinLock == null) {
            this.mBusMinLock = new SemDvfsManagerCompat(getContext(), "com.sec.android.gallery3d", 19, 0);
        }
        return this.mBusMinLock;
    }

    private DvfsManagerCompat getCoreMinLock() {
        if (this.mCoreMinLock == null) {
            this.mCoreMinLock = new SemDvfsManagerCompat(getContext(), "com.sec.android.gallery3d", 14, 0);
        }
        return this.mCoreMinLock;
    }

    private DvfsManagerCompat getCpuMinLock() {
        if (this.mCpuMinLock == null) {
            this.mCpuMinLock = new SemDvfsManagerCompat(getContext(), "com.sec.android.gallery3d", 12, 0);
        }
        return this.mCpuMinLock;
    }

    private DvfsManagerCompat getGpuMinLock() {
        if (this.mGpuMinLock == null) {
            this.mGpuMinLock = createGpuMinLock();
        }
        return this.mGpuMinLock;
    }

    public static SemBoosterCompat getInstance(Context context) {
        SemBoosterCompat semBoosterCompat;
        SemBoosterCompat semBoosterCompat2 = sInstance;
        if (semBoosterCompat2 != null) {
            return semBoosterCompat2;
        }
        synchronized (SemBoosterCompat.class) {
            try {
                if (sInstance == null) {
                    semBoosterCompat = new SemBoosterCompat(context);
                    sInstance = semBoosterCompat;
                } else {
                    semBoosterCompat = sInstance;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return semBoosterCompat;
    }

    private DvfsManagerCompat getTouchBooster() {
        if (this.mTouchBooster == null) {
            this.mTouchBooster = createTouchBooster();
        }
        return this.mTouchBooster;
    }

    private DvfsManagerCompat getTouchTailBooster() {
        if (this.mTouchTailBooster == null) {
            this.mTouchTailBooster = createTouchTailBooster();
        }
        return this.mTouchTailBooster;
    }

    /* access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 0) {
            acquireFlickBoostInternal();
            return false;
        } else if (i2 == 1) {
            releaseFlickBoostInternal();
            return false;
        } else if (i2 != 2) {
            return false;
        } else {
            acquireGpuBoostInternal(message.arg1);
            return false;
        }
    }

    private void releaseFlickBoostInternal() {
        getTouchBooster().release();
        getTouchTailBooster().acquire();
    }

    public void acquireFlick() {
        Handler handler = this.mHandler;
        if (handler == null) {
            Log.w("SemBoosterCompat", "fail acquireFlickBoost");
        } else {
            handler.sendEmptyMessage(0);
        }
    }

    @Deprecated
    public void acquireFull() {
        getCpuMinLock().acquire(2000);
        getCoreMinLock().acquire(2000);
        getBusMinLock().acquire(2000);
    }

    @Deprecated
    public void acquireGpu(int i2) {
        Handler handler = this.mHandler;
        if (handler == null) {
            Log.w("SemBoosterCompat", "fail acquireGpuBoost");
        } else {
            handler.sendMessage(handler.obtainMessage(2, i2, 0));
        }
    }

    public void acquireScroll() {
        if (this.mDvfsHelperScroll == null) {
            this.mDvfsHelperScroll = createScrollBoost();
        }
        this.mDvfsHelperScroll.acquire();
    }

    public DvfsManagerCompat createGpuMinLock() {
        return new SemDvfsManagerCompat(getContext(), "com.sec.android.gallery3d", 16, 0);
    }

    public DvfsManagerCompat createScrollBoost() {
        return new SemDvfsManagerCompat(getContext(), "GALLERY_SCROLL", 21, 0);
    }

    public DvfsManagerCompat createTouchBooster() {
        return new SemDvfsManagerCompat(getContext(), "GALLERY_TOUCH", 21, 0);
    }

    public DvfsManagerCompat createTouchTailBooster() {
        return new SemDvfsManagerCompat(getContext(), "GALLERY_TOUCH_TAIL", 21, 0);
    }

    public void destroy() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mHandlerThread = null;
        }
        this.mHandler = null;
        sInstance = null;
        DvfsManagerCompat dvfsManagerCompat = this.mTouchBooster;
        if (dvfsManagerCompat != null) {
            dvfsManagerCompat.release();
        }
        DvfsManagerCompat dvfsManagerCompat2 = this.mTouchTailBooster;
        if (dvfsManagerCompat2 != null) {
            dvfsManagerCompat2.release();
        }
        DvfsManagerCompat dvfsManagerCompat3 = this.mDvfsHelperScroll;
        if (dvfsManagerCompat3 != null) {
            dvfsManagerCompat3.release();
        }
    }

    public final Context getContext() {
        return this.mContext.get();
    }

    public void releaseFlick(int i2) {
        Handler handler = this.mHandler;
        if (handler == null) {
            Log.w("SemBoosterCompat", "fail releaseFlickBoost");
            return;
        }
        handler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, (long) i2);
    }

    public void releaseScroll() {
        DvfsManagerCompat dvfsManagerCompat = this.mDvfsHelperScroll;
        if (dvfsManagerCompat != null) {
            dvfsManagerCompat.release();
        }
    }
}
