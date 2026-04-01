package com.samsung.android.gallery.widget.photoview;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import com.samsung.android.gallery.module.aizoom.AliveZoomFactory;
import com.samsung.android.gallery.module.aizoom.ImageScaleInterface;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AliveZoomScheduler {
    private static final AliveZoomScheduler sInstance = new AliveZoomScheduler();
    /* access modifiers changed from: private */
    public final Object LOCK = new Object();
    private volatile AliveZoomProc mAliveZoomProc;
    private final AtomicInteger mCounter = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler(ThreadUtil.createBackgroundThreadLooper("AliveZoomScheduler")) {
        /* JADX INFO: finally extract failed */
        public void handleMessage(Message message) {
            AliveZoomLoadTileTask aliveZoomLoadTileTask;
            int i2 = message.what;
            boolean z = false;
            if (i2 == 3) {
                synchronized (AliveZoomScheduler.this.LOCK) {
                    try {
                        aliveZoomLoadTileTask = (AliveZoomLoadTileTask) AliveZoomScheduler.this.mTasks.peek();
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
                if (aliveZoomLoadTileTask != null) {
                    ThreadUtil.postOnUiThread(new g(1, aliveZoomLoadTileTask, aliveZoomLoadTileTask.doInBackground(new Void[0])));
                    synchronized (AliveZoomScheduler.this.LOCK) {
                        try {
                            AliveZoomScheduler.this.mTasks.poll();
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                }
                if (AliveZoomScheduler.this.mQuickCropData.size() > 0) {
                    Log.d("AliveZoomScheduler", "handleMessage: has quickCrop request", Integer.valueOf(AliveZoomScheduler.this.mQuickCropData.size()));
                    if (!hasMessages(4)) {
                        sendEmptyMessage(4);
                        return;
                    }
                }
                synchronized (AliveZoomScheduler.this.LOCK) {
                    try {
                        if (!AliveZoomScheduler.this.mTasks.isEmpty() && !AliveZoomScheduler.this.mHandler.hasMessages(3)) {
                            sendEmptyMessage(3);
                        }
                    } catch (Throwable th3) {
                        throw th3;
                    }
                }
            } else if (i2 == 1) {
                if (message.obj instanceof DetectTask) {
                    long currentTimeMillis = System.currentTimeMillis();
                    DetectTask detectTask = (DetectTask) message.obj;
                    int detect = AliveZoomScheduler.this.getAliveZoomProc().detect(detectTask.bitmap);
                    a.A(new Object[]{Integer.valueOf(detectTask.sourceHash), Integer.valueOf(detect), Long.valueOf(currentTimeMillis)}, new StringBuilder("detect"), "AliveZoomScheduler");
                    BiConsumer<Boolean, Integer> biConsumer = detectTask.callback;
                    if (biConsumer != null) {
                        if (detect != -1) {
                            z = true;
                        }
                        biConsumer.accept(Boolean.valueOf(z), Integer.valueOf(detect));
                    }
                    if (detect == -1) {
                        AliveZoomScheduler.this.cleanUpTask(detectTask.sourceHash);
                    }
                }
            } else if (i2 == 2) {
                AliveZoomScheduler.this.cleanUpTask(message.arg1);
            } else if (i2 == 0) {
                Object obj = message.obj;
                if (obj instanceof AliveZoomProc) {
                    ((AliveZoomProc) obj).recycle();
                }
            } else if (i2 == 4) {
                QuickCropData quickCropData = (QuickCropData) AliveZoomScheduler.this.mQuickCropData.peek();
                if (quickCropData == null) {
                    Log.e("AliveZoomScheduler", "quickCrop failed");
                    return;
                }
                Bitmap zoomImage = AliveZoomScheduler.this.zoomImage(0, quickCropData.bitmap, quickCropData.scale, quickCropData.paddingRect, quickCropData.sceneType);
                if (zoomImage == null) {
                    new InternalException("quickCrop may failed. enhanced bitmap is null").post();
                }
                quickCropData.callback.accept(zoomImage);
                synchronized (AliveZoomScheduler.this.LOCK) {
                    try {
                        AliveZoomScheduler.this.mQuickCropData.poll();
                        if (!AliveZoomScheduler.this.mTasks.isEmpty() && !AliveZoomScheduler.this.mHandler.hasMessages(3)) {
                            sendEmptyMessage(3);
                        }
                    } catch (Throwable th4) {
                        throw th4;
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final ConcurrentLinkedQueue<QuickCropData> mQuickCropData = new ConcurrentLinkedQueue<>();
    /* access modifiers changed from: private */
    public final ConcurrentLinkedQueue<AliveZoomLoadTileTask> mTasks = new ConcurrentLinkedQueue<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class AliveZoomProc {
        private static final String ENGINE_TYPE = AliveZoomFactory.getEngineType();
        private final ImageScaleInterface mImageScaler;
        private final boolean mSupport;

        public AliveZoomProc() {
            boolean z;
            long currentTimeMillis = System.currentTimeMillis();
            ImageScaleInterface create = AliveZoomFactory.create();
            this.mImageScaler = create;
            if (create == null || !create.createSession(43033600, ENGINE_TYPE)) {
                z = false;
            } else {
                z = true;
            }
            this.mSupport = z;
            a.A(new Object[]{Boolean.valueOf(z), 43033600, ENGINE_TYPE, Long.valueOf(currentTimeMillis)}, new StringBuilder("createSession"), "AliveZoomScheduler");
        }

        private void boostUpGpu(int i2) {
            SeApiCompat.getBoosterCompat(AppResources.getAppContext()).acquireGpu(i2);
        }

        public int detect(Bitmap bitmap) {
            if (!this.mSupport) {
                return -1;
            }
            boostUpGpu(100);
            return this.mImageScaler.detectScene(bitmap);
        }

        public void recycle() {
            if (this.mSupport) {
                long currentTimeMillis = System.currentTimeMillis();
                this.mImageScaler.endSession();
                Log.d("AliveZoomScheduler", "endSession" + Logger.vt(currentTimeMillis));
            }
        }

        public Bitmap zoom(Bitmap bitmap, int i2, Rect rect, int i7) {
            if (AliveZoomFactory.SKIP_ALIVE_ZOOM_OUTPUT) {
                return bitmap;
            }
            if (!this.mSupport || i7 == -1) {
                return null;
            }
            boostUpGpu(StatusCodes.INPUT_MISSING);
            return this.mImageScaler.upscaleImage(bitmap, i2, rect, i7);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DetectTask {
        Bitmap bitmap;
        BiConsumer<Boolean, Integer> callback;
        int sourceHash;

        public DetectTask(int i2, Bitmap bitmap2, BiConsumer<Boolean, Integer> biConsumer) {
            this.sourceHash = i2;
            this.bitmap = bitmap2;
            this.callback = biConsumer;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class QuickCropData {
        Bitmap bitmap;
        Consumer<Bitmap> callback;
        Rect paddingRect;
        int scale;
        int sceneType;

        public QuickCropData(Bitmap bitmap2, int i2, Rect rect, int i7, Consumer<Bitmap> consumer) {
            this.scale = i2;
            this.sceneType = i7;
            this.paddingRect = rect;
            this.bitmap = bitmap2;
            this.callback = consumer;
        }
    }

    private AliveZoomScheduler() {
    }

    /* access modifiers changed from: private */
    public void cleanUpTask(int i2) {
        synchronized (this.LOCK) {
            try {
                if (this.mTasks.removeIf(new h(i2, 0))) {
                    Log.d("AliveZoomScheduler", "cleanUpTask", Integer.valueOf(i2));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public AliveZoomProc getAliveZoomProc() {
        if (this.mAliveZoomProc == null) {
            this.mAliveZoomProc = new AliveZoomProc();
        }
        return this.mAliveZoomProc;
    }

    public static AliveZoomScheduler getInstance() {
        return sInstance;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$cleanUpTask$0(int i2, AliveZoomLoadTileTask aliveZoomLoadTileTask) {
        if (aliveZoomLoadTileTask.sourceHash == i2) {
            return true;
        }
        return false;
    }

    public void close() {
        if (this.mCounter.decrementAndGet() == 0) {
            Log.d("AliveZoomScheduler", "close");
            this.mHandler.removeCallbacksAndMessages((Object) null);
            synchronized (this.LOCK) {
                this.mTasks.clear();
            }
            if (this.mAliveZoomProc != null) {
                Handler handler = this.mHandler;
                handler.sendMessage(handler.obtainMessage(0, this.mAliveZoomProc));
                this.mAliveZoomProc = null;
            }
        }
    }

    public void detectScene(int i2, Bitmap bitmap, BiConsumer<Boolean, Integer> biConsumer) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1, new DetectTask(i2, bitmap.copy(Bitmap.Config.ARGB_8888, true), biConsumer)));
    }

    public void execute(AliveZoomLoadTileTask aliveZoomLoadTileTask) {
        synchronized (this.LOCK) {
            try {
                this.mTasks.add(aliveZoomLoadTileTask);
                if (!this.mHandler.hasMessages(3)) {
                    this.mHandler.sendEmptyMessage(3);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void open() {
        if (this.mCounter.getAndIncrement() == 0) {
            Log.d("AliveZoomScheduler", "open");
        }
    }

    public void quickCropZoom(Bitmap bitmap, int i2, Rect rect, int i7, Consumer<Bitmap> consumer) {
        Log.d("AliveZoomScheduler", "quickCropZoom");
        synchronized (this.LOCK) {
            try {
                this.mQuickCropData.add(new QuickCropData(bitmap, i2, rect, i7, consumer));
                if (this.mTasks.isEmpty() && !this.mHandler.hasMessages(4)) {
                    this.mHandler.sendEmptyMessage(4);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void release(int i2) {
        Handler handler = this.mHandler;
        handler.sendMessageAtFrontOfQueue(handler.obtainMessage(2, i2, 0));
    }

    public Bitmap zoomImage(int i2, Bitmap bitmap, int i7, Rect rect, int i8) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        Bitmap zoom = getAliveZoomProc().zoom(bitmap, i7, rect, i8);
        StringBuilder sb2 = new StringBuilder("zoomImage");
        Integer valueOf = Integer.valueOf(i2);
        Integer valueOf2 = Integer.valueOf(i8);
        Integer valueOf3 = Integer.valueOf(i7);
        if (zoom != null) {
            str = zoom.getWidth() + "x" + zoom.getHeight();
        } else {
            str = "null";
        }
        a.A(new Object[]{valueOf, valueOf2, valueOf3, str, Long.valueOf(currentTimeMillis)}, sb2, "AliveZoomScheduler");
        return zoom;
    }
}
