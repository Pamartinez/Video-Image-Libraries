package com.samsung.android.gallery.module.pipconverter;

import B8.d;
import I4.b;
import L9.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.net.Uri;
import android.util.Log;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import com.samsung.android.sdk.sgpl.pip.core.EncodeVideo;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PipConverter {
    private final Context mAppContext;
    private final Point mBackgroundSize = new Point();
    /* access modifiers changed from: private */
    public EncodeVideo mEncodeVideo;
    /* access modifiers changed from: private */
    public Consumer<Integer> mProgressListener;
    private final String mResultFilePath;
    private Uri mVideoFileUri;
    private final PointF mVideoPos = new PointF();
    private final Point mVideoSize = new Point();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ConverterThread extends Thread {
        private final WeakReference<EncodeVideo> mEncodeVideo;

        public ConverterThread(EncodeVideo encodeVideo) {
            this.mEncodeVideo = new WeakReference<>(encodeVideo);
        }

        public void run() {
            try {
                if (this.mEncodeVideo.get() != null) {
                    this.mEncodeVideo.get().encode();
                }
            } catch (IOException e) {
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = getUncaughtExceptionHandler();
                Objects.requireNonNull(uncaughtExceptionHandler);
                uncaughtExceptionHandler.uncaughtException(this, e);
            }
        }
    }

    public PipConverter(Context context, String str, String str2) {
        this.mAppContext = context.getApplicationContext();
        this.mResultFilePath = str;
        if (str2 != null) {
            this.mVideoFileUri = Uri.fromFile(new File(str2));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$start$1(Thread thread, Throwable th) {
        Optional.ofNullable(this.mProgressListener).ifPresent(new b(18));
        C0212a.y(th, new StringBuilder("Exception : "), "PipConverter");
    }

    private void setTrimValues(String str, ArrayList<PlaybackOption> arrayList) {
        long j2;
        long videoDuration = (long) (MediaHelper.getVideoDuration(str) / 1000);
        Log.d("PipConverter", "original video duration : " + videoDuration + " second");
        if (videoDuration <= 0) {
            Log.d("PipConverter", "video duration : " + videoDuration);
        } else if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            arrayList.forEach(new d(arrayList2, 7));
            this.mEncodeVideo.setTrims(arrayList2);
        } else {
            EncodeVideo encodeVideo = this.mEncodeVideo;
            if (videoDuration >= 10) {
                j2 = 10000000;
            } else {
                j2 = 1000000 * videoDuration;
            }
            encodeVideo.setTrims(Collections.singletonList(Encode.TrimInfo.create(0, j2)));
        }
    }

    public float[] calculateRelatedPos(float f, float f5, int i2, int i7, int i8, int i10) {
        boolean z;
        boolean z3;
        int i11;
        float f8 = (float) i8;
        float f10 = (float) i2;
        float f11 = f8 / f10;
        float f12 = (float) i10;
        float f13 = (float) i7;
        float f14 = f12 / f13;
        float f15 = f8 * 0.5f;
        float f16 = ((f10 * 0.5f) + f) - f15;
        float f17 = f12 * 0.5f;
        float f18 = ((f13 * 0.5f) + f5) - f17;
        if (f16 < 0.0f) {
            z = true;
        } else {
            z = false;
        }
        if (f18 > 0.0f) {
            z3 = true;
        } else {
            z3 = false;
        }
        float abs = (Math.abs(f16) * f11) / f15;
        int i12 = -1;
        if (z) {
            i11 = -1;
        } else {
            i11 = 1;
        }
        float f19 = abs * ((float) i11);
        float abs2 = (Math.abs(f18) * f14) / f17;
        if (!z3) {
            i12 = 1;
        }
        return new float[]{f19, abs2 * ((float) i12)};
    }

    public boolean prepareEncode(String str, Bitmap bitmap, ArrayList<PlaybackOption> arrayList) {
        EncodeVideo encodeVideo = new EncodeVideo();
        this.mEncodeVideo = encodeVideo;
        encodeVideo.setLogoBitmap(bitmap);
        this.mEncodeVideo.setEncodeProgressListener(new Encode.EncodeProgressListener() {
            /* JADX WARNING: type inference failed for: r0v3, types: [java.util.function.Consumer, java.lang.Object] */
            public void onCompleted() {
                PipConverter.this.mEncodeVideo.stop();
                PipConverter.this.mEncodeVideo = null;
                Optional.ofNullable(PipConverter.this.mProgressListener).ifPresent(new Object());
            }

            public void onProgressChanged(int i2) {
                Optional.ofNullable(PipConverter.this.mProgressListener).ifPresent(new b(i2));
            }
        });
        this.mBackgroundSize.set(bitmap.getWidth(), bitmap.getHeight());
        setTrimValues(str, arrayList);
        return true;
    }

    public void setProgressListener(Consumer<Integer> consumer) {
        this.mProgressListener = consumer;
    }

    public void setVideoPosition(int i2, int i7) {
        this.mVideoPos.set((float) i2, (float) i7);
    }

    public void setVideoSize(int i2, int i7) {
        this.mVideoSize.set(i2, i7);
    }

    public void start() {
        PipConverter pipConverter;
        try {
            PointF pointF = this.mVideoPos;
            float f = pointF.x;
            float f5 = pointF.y;
            Point point = this.mVideoSize;
            int i2 = point.x;
            int i7 = point.y;
            Point point2 = this.mBackgroundSize;
            pipConverter = this;
            try {
                float[] calculateRelatedPos = pipConverter.calculateRelatedPos(f, f5, i2, i7, point2.x, point2.y);
                EncodeVideo encodeVideo = pipConverter.mEncodeVideo;
                float f8 = calculateRelatedPos[0];
                float f10 = calculateRelatedPos[1];
                Point point3 = pipConverter.mVideoSize;
                encodeVideo.setVideoLayoutInfo(f8, f10, point3.x, point3.y);
                pipConverter.mEncodeVideo.initialize(pipConverter.mResultFilePath, pipConverter.mAppContext, pipConverter.mVideoFileUri);
                Log.d("PipConverter", "start [ bg : " + pipConverter.mBackgroundSize.toString() + ", video : " + pipConverter.mVideoSize.toString() + ", related : " + Arrays.toString(calculateRelatedPos) + "] ");
            } catch (IOException e) {
                e = e;
            }
        } catch (IOException e7) {
            e = e7;
            pipConverter = this;
            e.printStackTrace();
            ConverterThread converterThread = new ConverterThread(pipConverter.mEncodeVideo);
            converterThread.setUncaughtExceptionHandler(new a(pipConverter));
            converterThread.start();
        }
        ConverterThread converterThread2 = new ConverterThread(pipConverter.mEncodeVideo);
        converterThread2.setUncaughtExceptionHandler(new a(pipConverter));
        converterThread2.start();
    }

    public void stop() {
        this.mProgressListener = null;
        EncodeVideo encodeVideo = this.mEncodeVideo;
        if (encodeVideo != null) {
            encodeVideo.stop();
        }
    }
}
