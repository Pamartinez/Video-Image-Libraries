package com.samsung.android.sum.core.channel;

import android.media.Image;
import android.media.ImageReader;
import android.media.ImageWriter;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import c0.C0086a;
import com.samsung.android.motionphoto.utils.v2.video.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.types.MediaType;
import java.util.Optional;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StapleSurfaceRWChannel implements SurfaceChannel {
    private static final String TAG = Def.tagOf((Class<?>) StapleSurfaceRWChannel.class);
    private static int maxImages = Math.min(62, 62);
    private int capacity;
    int format;
    int height;
    private int processedFrames;
    private ImageReader reader;
    private HandlerThread receiveThread;
    long usage;
    private final Condition waitCondition;
    private final ReentrantLock waitLock;
    int width;
    private ImageWriter writer;

    public StapleSurfaceRWChannel() {
        this((BufferChannel) null);
    }

    /* access modifiers changed from: private */
    public void onImageReceived(ImageReader imageReader) {
        if (this.writer == null) {
            waitUntilSignaled("writer is given");
        }
        Image acquireNextImage = imageReader.acquireNextImage();
        String str = TAG;
        StringBuilder sb2 = new StringBuilder("received image=");
        sb2.append(acquireNextImage);
        sb2.append(", # of processed frames: ");
        int i2 = this.processedFrames + 1;
        this.processedFrames = i2;
        sb2.append(i2);
        SLog.d(str, sb2.toString());
        this.writer.queueInputImage(acquireNextImage);
        signal();
    }

    private void signal() {
        this.waitLock.lock();
        try {
            this.waitCondition.signalAll();
        } finally {
            this.waitLock.unlock();
        }
    }

    private void waitUntilSignaled(String str) {
        this.waitLock.lock();
        try {
            String str2 = TAG;
            SLog.w(str2, "wait until " + str);
            this.waitCondition.await();
            SLog.d(str2, "now " + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.waitLock.unlock();
            throw th;
        }
        this.waitLock.unlock();
    }

    public void configure(SurfaceChannelConfig surfaceChannelConfig) {
        boolean z;
        this.width = surfaceChannelConfig.getWidth();
        this.height = surfaceChannelConfig.getHeight();
        this.format = surfaceChannelConfig.getFormat();
        this.usage = surfaceChannelConfig.getUsage();
        Surface surface = surfaceChannelConfig.getSurface();
        if (this.width == 0 || this.height == 0 || this.format == 0 || surface == null) {
            z = false;
        } else {
            z = true;
        }
        Def.check(z);
        HandlerThread handlerThread = new HandlerThread("surface-receive-thread");
        this.receiveThread = handlerThread;
        handlerThread.start();
        ImageReader newInstance = ImageReader.newInstance(this.width, this.height, this.format, this.capacity);
        this.reader = newInstance;
        newInstance.setOnImageAvailableListener(new a(1, this), new Handler(this.receiveThread.getLooper()));
        this.writer = ImageWriter.newInstance(surface, this.capacity);
        signal();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getFormat() {
        if (Build.VERSION.SDK_INT >= 33) {
            return ((Integer) Optional.ofNullable(this.reader).map(new b(11)).orElse(0)).intValue();
        }
        return this.format;
    }

    public int getHeight() {
        return ((Integer) Optional.ofNullable(this.reader).map(new b(10)).orElse(-1)).intValue();
    }

    public long getUsage() {
        if (Build.VERSION.SDK_INT >= 33) {
            return ((Long) Optional.ofNullable(this.reader).map(new b(12)).orElse(0L)).longValue();
        }
        return this.usage;
    }

    public int getWidth() {
        return ((Integer) Optional.ofNullable(this.reader).map(new b(13)).orElse(-1)).intValue();
    }

    public boolean isClosedForReceive() {
        return false;
    }

    public boolean isClosedForSend() {
        return false;
    }

    public boolean isRequireToConfigure(int i2, int i7, int i8, long j2) {
        return Build.VERSION.SDK_INT >= 33 ? (!isRequireToConfigure() && this.writer.getWidth() == i2 && this.writer.getHeight() == i7 && this.writer.getFormat() == i8 && this.writer.getUsage() == j2) ? false : true : (!isRequireToConfigure() && this.width == i2 && this.height == i7 && this.format == i8) ? false : true;
    }

    public void setCapacity(int i2) {
        this.capacity = i2;
        if (i2 > maxImages) {
            String str = TAG;
            StringBuilder o2 = C0086a.o(i2, "too large capacity, ", "adjust to ");
            o2.append(maxImages);
            SLog.d(str, o2.toString());
            this.capacity = maxImages;
        }
    }

    public StapleSurfaceRWChannel(BufferChannel bufferChannel) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.waitLock = reentrantLock;
        this.waitCondition = reentrantLock.newCondition();
        this.capacity = maxImages;
        this.processedFrames = 0;
    }

    public MediaBuffer receive() {
        waitUntilSignaled("receive buffer");
        return MediaBuffer.mutableOf(MediaType.IMAGE);
    }

    public void send(MediaBuffer mediaBuffer) {
        signal();
    }

    public boolean isRequireToConfigure() {
        return this.writer == null || this.reader == null;
    }

    public void cancel() {
    }

    public void close() {
    }

    public void reset() {
    }
}
