package com.samsung.android.sum.core.channel;

import android.hardware.HardwareBuffer;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import androidx.window.embedding.c;
import com.samsung.android.motionphoto.utils.v2.video.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.SharedBufferManager;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.channel.SurfaceChannel;
import com.samsung.android.sum.core.functional.CountingLatch;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StapleSurfaceReadChannel implements SurfaceReadChannel {
    private static final String TAG = Def.tagOf((Class<?>) StapleSurfaceReadChannel.class);
    private BufferChannel bufferChannel;
    private int capacity;
    int format;
    int height;
    private ImageReader imageReader;
    private CountingLatch imageUseCounter;
    private final ReentrantLock lock;
    private int processedFrames;
    private HandlerThread receiveThread;
    private final AtomicReference<SurfaceChannel.State> state;
    long usage;
    int width;

    public StapleSurfaceReadChannel() {
        this((BufferChannel) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isClosedForReceive$3() {
        boolean z;
        if (this.state.get() != SurfaceChannel.State.OPEN) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isClosedForSend$2() {
        boolean z;
        if (this.state.get() != SurfaceChannel.State.OPEN) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onImageReceived$1(MediaBuffer mediaBuffer) {
        String str = TAG;
        SLog.d(str, "release image buffer: " + mediaBuffer.hashCode());
        this.imageUseCounter.down();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$sendHardwareBuffer$0(Image image) {
        image.close();
        this.imageUseCounter.down();
    }

    /* access modifiers changed from: private */
    public void onImageReceived(ImageReader imageReader2) {
        this.lock.lock();
        try {
            Image acquireNextImage = imageReader2.acquireNextImage();
            if (acquireNextImage != null) {
                MediaBuffer of2 = MediaBuffer.of(acquireNextImage);
                of2.addOnReleaseListener(new c(26, this, of2));
                of2.setExtra(Message.KEY_TIMESTAMP_US, Long.valueOf(acquireNextImage.getTimestamp() / 1000));
                of2.setExtra(Message.KEY_BLOCK_ID, Integer.valueOf(this.processedFrames));
                send(of2);
                String str = TAG;
                StringBuilder sb2 = new StringBuilder("received image=");
                sb2.append(acquireNextImage);
                sb2.append(", # of processed frames: ");
                int i2 = this.processedFrames + 1;
                this.processedFrames = i2;
                sb2.append(i2);
                sb2.append(", timestamp: ");
                sb2.append(acquireNextImage.getTimestamp());
                SLog.d(str, sb2.toString());
            } else {
                SLog.d(TAG, "received null image");
            }
        } catch (Exception e) {
            String str2 = TAG;
            SLog.w(str2, "fail to acquire image: " + e.getMessage());
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
        this.lock.unlock();
    }

    @Deprecated
    private void sendHardwareBuffer(Image image) {
        HardwareBuffer hardwareBuffer = image.getHardwareBuffer();
        if (hardwareBuffer != null) {
            MediaBuffer wrap = MediaBuffer.newAlloc(MediaType.IMAGE).setDataType(DataType.U8).setShape(image.getWidth(), image.getHeight()).setColorFormat(SharedBufferManager.colorFormatFromHalFormat(hardwareBuffer.getFormat())).wrap(hardwareBuffer);
            wrap.addOnReleaseListener(new c(27, this, image));
            wrap.setExtra(Message.KEY_TIMESTAMP_US, Long.valueOf(image.getTimestamp() / 1000));
            wrap.setExtra(Message.KEY_BLOCK_ID, Integer.valueOf(this.processedFrames));
            send(wrap);
        }
    }

    public void cancel() {
        this.lock.lock();
        try {
            this.state.set(SurfaceChannel.State.CANCELED);
            BufferChannel bufferChannel2 = this.bufferChannel;
            if (bufferChannel2 != null) {
                bufferChannel2.cancel();
            }
            ImageReader imageReader2 = this.imageReader;
            if (imageReader2 != null) {
                imageReader2.close();
            }
            CountingLatch countingLatch = this.imageUseCounter;
            if (countingLatch != null) {
                countingLatch.reset();
            }
            this.lock.unlock();
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public void close() {
        this.lock.lock();
        try {
            this.state.set(SurfaceChannel.State.CLOSED);
            reset();
            BufferChannel bufferChannel2 = this.bufferChannel;
            if (bufferChannel2 != null) {
                bufferChannel2.close();
            }
        } finally {
            this.bufferChannel = null;
            this.lock.unlock();
        }
    }

    public void configure(SurfaceChannelConfig surfaceChannelConfig) {
        boolean z;
        ImageReader imageReader2;
        this.width = surfaceChannelConfig.getWidth();
        this.height = surfaceChannelConfig.getHeight();
        this.format = surfaceChannelConfig.getFormat();
        long usage2 = surfaceChannelConfig.getUsage();
        this.usage = usage2;
        if (this.width == 0 || this.height == 0 || this.format == 0 || usage2 == 0) {
            z = false;
        } else {
            z = true;
        }
        Def.check(z);
        this.lock.lock();
        try {
            if (this.receiveThread == null) {
                HandlerThread handlerThread = new HandlerThread("surface-receive-thread");
                this.receiveThread = handlerThread;
                handlerThread.start();
            }
            String str = TAG;
            SLog.d(str, "image-reader capacity=" + this.capacity);
            long j2 = this.usage;
            if (j2 != 0) {
                imageReader2 = ImageReader.newInstance(this.width, this.height, this.format, this.capacity, j2);
            } else {
                imageReader2 = ImageReader.newInstance(this.width, this.height, this.format, this.capacity);
            }
            this.imageReader = imageReader2;
            imageReader2.setOnImageAvailableListener(new a(2, this), new Handler(this.receiveThread.getLooper()));
            this.state.set(SurfaceChannel.State.OPEN);
            this.imageUseCounter = CountingLatch.downOf();
            this.lock.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.lock.unlock();
            throw th2;
        }
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getFormat() {
        if (Build.VERSION.SDK_INT >= 33) {
            return ((Integer) Optional.ofNullable(this.imageReader).map(new b(14)).orElse(0)).intValue();
        }
        return this.format;
    }

    public int getHeight() {
        return ((Integer) Optional.ofNullable(this.imageReader).map(new b(10)).orElse(-1)).intValue();
    }

    public Surface getSurface() {
        return this.imageReader.getSurface();
    }

    public long getUsage() {
        if (Build.VERSION.SDK_INT >= 33) {
            return ((Long) Optional.ofNullable(this.imageReader).map(new b(15)).orElse(0L)).longValue();
        }
        return this.usage;
    }

    public int getWidth() {
        return ((Integer) Optional.ofNullable(this.imageReader).map(new b(13)).orElse(-1)).intValue();
    }

    public boolean isClosedForReceive() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(4)).orElseGet(new l(this, 1))).booleanValue();
    }

    public boolean isClosedForSend() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(3)).orElseGet(new l(this, 0))).booleanValue();
    }

    public boolean isRequireToConfigure() {
        if (this.imageReader == null || this.state.get() == SurfaceChannel.State.SUSPEND) {
            return true;
        }
        return false;
    }

    public void reset() {
        SLog.d(TAG, "reset...E");
        this.lock.lock();
        try {
            HandlerThread handlerThread = this.receiveThread;
            if (handlerThread != null) {
                handlerThread.interrupt();
                this.receiveThread.join();
            }
            ImageReader imageReader2 = this.imageReader;
            if (imageReader2 != null) {
                imageReader2.close();
            }
            CountingLatch countingLatch = this.imageUseCounter;
            if (countingLatch != null) {
                countingLatch.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.receiveThread = null;
            this.imageReader = null;
            this.imageUseCounter = null;
            this.lock.unlock();
            throw th;
        }
        this.receiveThread = null;
        this.imageReader = null;
        this.imageUseCounter = null;
        this.lock.unlock();
    }

    public void setCapacity(int i2) {
        this.capacity = i2;
        if (i2 > 62) {
            String str = TAG;
            SLog.d(str, "too large capacity " + i2 + ", adjust to 62");
            this.capacity = 62;
        }
    }

    public StapleSurfaceReadChannel(BufferChannel bufferChannel2) {
        this.lock = new ReentrantLock();
        this.capacity = 62;
        this.processedFrames = 0;
        this.state = new AtomicReference<>(SurfaceChannel.State.OPEN);
        this.bufferChannel = bufferChannel2;
    }

    public MediaBuffer receive() {
        return (MediaBuffer) Optional.ofNullable(this.bufferChannel).map(new b(2)).orElseThrow(new u(4));
    }

    public void send(MediaBuffer mediaBuffer) {
        this.lock.lock();
        try {
            if (mediaBuffer.containsExtra("block-check")) {
                this.imageUseCounter.await(this.capacity - 1, 1000);
                this.imageUseCounter.up();
            } else {
                this.bufferChannel.send(mediaBuffer);
            }
        } catch (Exception e) {
            String str = TAG;
            SLog.w(str, "failed to read image" + e);
            e.printStackTrace();
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
        this.lock.unlock();
    }
}
