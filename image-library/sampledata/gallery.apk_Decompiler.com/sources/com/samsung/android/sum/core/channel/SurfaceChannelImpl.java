package com.samsung.android.sum.core.channel;

import J5.c;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.media.ImageReader;
import android.media.ImageWriter;
import android.os.ConditionVariable;
import android.os.HandlerThread;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.G;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.SharedBufferManager;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SurfaceChannelImpl implements BufferChannel, SurfaceChannel {
    private static final int HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SPN = 291;
    private static final int HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SP_M = 261;
    private static final int HAL_PIXEL_FORMAT_YCbCr_420_SP_VENUS = 2141391876;
    private static final String TAG = Def.tagOf((Class<?>) SurfaceChannelImpl.class);
    private static final Map<ColorFormat, int[]> vendorSpecificColorFormat = new HashMap<ColorFormat, int[]>() {
        {
            put(ColorFormat.NV12, new int[]{SurfaceChannelImpl.HAL_PIXEL_FORMAT_YCbCr_420_SP_VENUS});
            put(ColorFormat.NV21, new int[]{261, SurfaceChannelImpl.HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SPN});
        }
    };
    private BufferChannel bufferChannel;
    private int capacity;
    private final int channelType;
    private final Condition condition;
    private final AtomicInteger imageCapacityCount;
    private final ConditionVariable imageReadCV;
    private final ReentrantLock lock;
    private final AtomicInteger numberOfFrames;
    private final ImageReader.OnImageAvailableListener onImageAvailableListener;
    private ColorFormat pixelFormat = ColorFormat.NONE;
    private int processedFrames;
    private ImageReader reader;
    private final Supplier<MediaBuffer> receiveHandler;
    private HandlerThread receiveThread;
    private final Consumer<MediaBuffer> sendHandler;
    private ImageWriter writer;

    /* JADX WARNING: type inference failed for: r4v6, types: [java.lang.Object, android.media.ImageReader$OnImageAvailableListener] */
    public SurfaceChannelImpl(int i2, BufferChannel bufferChannel2) {
        boolean z = false;
        this.processedFrames = 0;
        this.numberOfFrames = new AtomicInteger(0);
        this.capacity = 0;
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.condition = reentrantLock.newCondition();
        this.imageReadCV = new ConditionVariable(true);
        this.imageCapacityCount = new AtomicInteger(0);
        this.channelType = i2;
        if (i2 == 2) {
            Def.require(bufferChannel2 != null ? true : z);
            this.bufferChannel = bufferChannel2;
            this.sendHandler = new e(3, this, bufferChannel2);
            Objects.requireNonNull(bufferChannel2);
            this.receiveHandler = new r(0, bufferChannel2);
            this.onImageAvailableListener = new q(this, 1);
        } else if (i2 == 3) {
            this.sendHandler = new p(this, 1);
            this.receiveHandler = new h(1);
            this.onImageAvailableListener = new Object();
        } else if (i2 == 4) {
            this.sendHandler = new p(this, 0);
            this.receiveHandler = new c(15, this);
            this.onImageAvailableListener = new q(this, 0);
        } else {
            throw new IllegalStateException("not supported type");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(MediaBuffer mediaBuffer) {
        signal();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaBuffer lambda$new$1() {
        waitUntilSignaled("receive buffer");
        return MediaBuffer.mutableOf(MediaType.IMAGE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(BufferChannel bufferChannel2, MediaBuffer mediaBuffer) {
        if (mediaBuffer.containsExtra("block-check")) {
            if (this.imageCapacityCount.get() >= this.capacity) {
                SLog.d(TAG, "wait for ImageReader close...");
                this.imageReadCV.close();
                this.imageReadCV.block(1000);
            }
            this.imageCapacityCount.incrementAndGet();
            return;
        }
        bufferChannel2.send(mediaBuffer);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaBuffer lambda$new$3() {
        throw new UnsupportedOperationException("");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$4(ImageReader imageReader) {
        throw new UnsupportedOperationException("");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onImageReceive$5(HardwareBuffer hardwareBuffer, int i2) {
        if (i2 == hardwareBuffer.getFormat()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onImageReceive$7(Image image) {
        image.close();
        this.imageCapacityCount.decrementAndGet();
        this.imageReadCV.open();
    }

    /* access modifiers changed from: private */
    public void onImageReceive(ImageReader imageReader) {
        HardwareBuffer hardwareBuffer;
        try {
            Image acquireNextImage = imageReader.acquireNextImage();
            if (acquireNextImage == null) {
                SLog.w(TAG, "null image returned from reader");
                hardwareBuffer = null;
            } else {
                hardwareBuffer = acquireNextImage.getHardwareBuffer();
            }
            if (hardwareBuffer != null) {
                if (this.pixelFormat == ColorFormat.NONE) {
                    this.pixelFormat = (ColorFormat) vendorSpecificColorFormat.entrySet().stream().filter(new g(hardwareBuffer, 1)).map(new G(0)).findFirst().orElseThrow(new h(0));
                }
                String str = TAG;
                SLog.d(str, "fmt=" + Integer.toHexString(hardwareBuffer.getFormat()) + "[" + this.pixelFormat + "], usage=" + Long.toHexString(hardwareBuffer.getUsage()));
                MediaBuffer wrap = MediaBuffer.newImageAlloc().setDataType(DataType.U8).setShape(acquireNextImage.getWidth(), acquireNextImage.getHeight()).setColorFormat(this.pixelFormat).wrap(hardwareBuffer);
                wrap.addOnReleaseListener(new androidx.window.embedding.c(29, this, acquireNextImage));
                wrap.setExtra("timestampNs", Long.valueOf(acquireNextImage.getTimestamp()));
                wrap.setExtra(Message.KEY_BLOCK_ID, Integer.valueOf(this.processedFrames));
                send(wrap);
            }
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder("received image=");
            sb2.append(acquireNextImage);
            sb2.append(", # of processed frames: ");
            int i2 = this.processedFrames + 1;
            this.processedFrames = i2;
            sb2.append(i2);
            sb2.append("timestamp: ");
            sb2.append(acquireNextImage.getTimestamp());
            SLog.d(str2, sb2.toString());
        } catch (Exception e) {
            String str3 = TAG;
            SLog.w(str3, "fail to acquire image: " + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void onImageTransit(ImageReader imageReader) {
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
        this.lock.lock();
        try {
            this.condition.signalAll();
        } finally {
            this.lock.unlock();
        }
    }

    private void waitUntilSignaled(String str) {
        this.lock.lock();
        try {
            String str2 = TAG;
            SLog.w(str2, "wait until " + str);
            this.condition.await();
            SLog.d(str2, "now " + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
        this.lock.unlock();
    }

    /* access modifiers changed from: private */
    public void writeToSurface(MediaBuffer mediaBuffer) {
        String str = TAG;
        SLog.d(str, "writeToSurface: " + mediaBuffer);
        if (this.writer == null) {
            waitUntilSignaled("writer given");
        }
        long longValue = ((Long) mediaBuffer.getExtra("timestampNs")).longValue();
        Image dequeueInputImage = this.writer.dequeueInputImage();
        dequeueInputImage.setTimestamp(longValue);
        SharedBufferManager.copyFromBuffer(mediaBuffer, dequeueInputImage.getHardwareBuffer());
        mediaBuffer.release();
        this.writer.queueInputImage(dequeueInputImage);
        StringBuilder sb2 = new StringBuilder("send image=");
        sb2.append(dequeueInputImage);
        sb2.append(", # of processed frames: ");
        int i2 = this.processedFrames + 1;
        this.processedFrames = i2;
        sb2.append(i2);
        sb2.append("[");
        sb2.append(longValue / 1000);
        sb2.append("]");
        SLog.d(str, sb2.toString());
    }

    public void cancel() {
        BufferChannel bufferChannel2 = this.bufferChannel;
        if (bufferChannel2 != null) {
            bufferChannel2.cancel();
        }
        HandlerThread handlerThread = this.receiveThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    public void close() {
        BufferChannel bufferChannel2 = this.bufferChannel;
        if (bufferChannel2 != null) {
            bufferChannel2.close();
        }
        HandlerThread handlerThread = this.receiveThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    public void configure(SurfaceChannelConfig surfaceChannelConfig) {
        throw new UnsupportedOperationException();
    }

    public int getFormat() {
        throw new UnsupportedOperationException();
    }

    public int getHeight() {
        throw new UnsupportedOperationException();
    }

    public long getUsage() {
        throw new UnsupportedOperationException();
    }

    public int getWidth() {
        throw new UnsupportedOperationException();
    }

    public boolean isClosedForReceive() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(4)).orElse(Boolean.FALSE)).booleanValue();
    }

    public boolean isClosedForSend() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(3)).orElse(Boolean.FALSE)).booleanValue();
    }

    public boolean isRequireToConfigure() {
        return false;
    }

    public boolean isRequireToConfigure(int i2, int i7, int i8, long j2) {
        return false;
    }

    public MediaBuffer receive() {
        return this.receiveHandler.get();
    }

    public void send(MediaBuffer mediaBuffer) {
        this.sendHandler.accept(mediaBuffer);
    }

    public void reset() {
    }
}
