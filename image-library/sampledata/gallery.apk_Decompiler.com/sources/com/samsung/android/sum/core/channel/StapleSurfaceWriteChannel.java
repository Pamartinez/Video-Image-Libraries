package com.samsung.android.sum.core.channel;

import android.media.Image;
import android.media.ImageWriter;
import android.os.Build;
import android.view.Surface;
import androidx.window.embedding.c;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.SharedBufferManager;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.channel.SurfaceChannel;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.MediaType;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StapleSurfaceWriteChannel implements SurfaceWriteChannel {
    private static final String TAG = Def.tagOf((Class<?>) StapleSurfaceWriteChannel.class);
    private BufferChannel bufferChannel;
    private Thread bufferChannelThread;
    private int capacity = 62;
    private final Condition condition;
    int format;
    int height;
    private ImageWriter imageWriter;
    private final ReentrantLock lock;
    private final AtomicReference<Consumer<MediaBuffer>> onBufferReceivedByBufferChannel;
    private int processedFrames = 0;
    private final AtomicReference<SurfaceChannel.State> state;
    int width;

    public StapleSurfaceWriteChannel(BufferChannel bufferChannel2) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.condition = reentrantLock.newCondition();
        this.onBufferReceivedByBufferChannel = new AtomicReference<>();
        this.state = new AtomicReference<>(SurfaceChannel.State.OPEN);
        this.bufferChannel = bufferChannel2;
    }

    private boolean isImageValid(Image image) {
        try {
            Field declaredField = image.getClass().getSuperclass().getDeclaredField("mIsImageValid");
            declaredField.setAccessible(true);
            return ((Boolean) declaredField.get(image)).booleanValue();
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$configure$4(Consumer consumer) {
        SLog.i(TAG, "start bufferchannel-receive-thread");
        while (!this.bufferChannelThread.isInterrupted() && consumer != null) {
            try {
                BufferChannel bufferChannel2 = this.bufferChannel;
                Objects.requireNonNull(bufferChannel2);
                consumer.accept((MediaBuffer) bufferChannel2.receive());
            } catch (Exception e) {
                String str = TAG;
                SLog.i(str, "bufferchannel-receive-thread interrupted: " + e.getMessage());
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$getFormat$2(ImageWriter imageWriter2) {
        return Integer.valueOf(this.imageWriter.getFormat());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$getHeight$1(ImageWriter imageWriter2) {
        return Integer.valueOf(this.imageWriter.getHeight());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Long lambda$getUsage$3(ImageWriter imageWriter2) {
        return Long.valueOf(this.imageWriter.getUsage());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Integer lambda$getWidth$0(ImageWriter imageWriter2) {
        return Integer.valueOf(this.imageWriter.getWidth());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isClosedForReceive$7() {
        boolean z;
        if (this.state.get() == SurfaceChannel.State.CLOSED || this.state.get() == SurfaceChannel.State.CANCELED) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isClosedForSend$6() {
        boolean z;
        if (this.state.get() == SurfaceChannel.State.CLOSED || this.state.get() == SurfaceChannel.State.CANCELED) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$send$5(MediaBuffer mediaBuffer) {
        if (!mediaBuffer.getFormat().getMediaType().isMetaData()) {
            long longValue = ((Long) mediaBuffer.getExtra(Message.KEY_TIMESTAMP_US)).longValue();
            Image dequeueInputImage = this.imageWriter.dequeueInputImage();
            dequeueInputImage.setTimestamp(1000 * longValue);
            SharedBufferManager.copyFromBuffer(mediaBuffer, dequeueInputImage.getHardwareBuffer());
            this.imageWriter.queueInputImage(dequeueInputImage);
            String str = TAG;
            SLog.d(str, "timestampUs=" + longValue);
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
            ImageWriter imageWriter2 = this.imageWriter;
            if (imageWriter2 != null) {
                imageWriter2.close();
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
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.bufferChannel = null;
            this.lock.unlock();
            throw th;
        }
        this.bufferChannel = null;
        this.lock.unlock();
    }

    public void configure(SurfaceChannelConfig surfaceChannelConfig) {
        Surface surface = surfaceChannelConfig.getSurface();
        Consumer<MediaBuffer> metaDataHandler = surfaceChannelConfig.getMetaDataHandler();
        this.width = surfaceChannelConfig.getWidth();
        this.height = surfaceChannelConfig.getHeight();
        this.format = surfaceChannelConfig.getFormat();
        String str = TAG;
        SLog.d(str, "config...E");
        this.lock.lock();
        try {
            if (this.bufferChannelThread == null) {
                c cVar = new c(28, this, metaDataHandler);
                Thread thread = new Thread(cVar, str + ": bufferchannel-receive-thread");
                this.bufferChannelThread = thread;
                thread.start();
            }
            if (metaDataHandler != this.onBufferReceivedByBufferChannel.get()) {
                SLog.d(str, "update onBufferReceivedByBufferChannel");
                this.onBufferReceivedByBufferChannel.set(metaDataHandler);
            }
            this.imageWriter = ImageWriter.newInstance(surface, this.capacity);
            this.state.set(SurfaceChannel.State.OPEN);
            this.condition.signalAll();
            this.lock.unlock();
            SLog.d(str, "config...X");
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public MediaBuffer getBuffer() {
        this.lock.lock();
        try {
            if (this.imageWriter != null) {
                if (this.state.get() == SurfaceChannel.State.SUSPEND) {
                }
                MediaBuffer of2 = MediaBuffer.of(MediaType.IMAGE, this.imageWriter.dequeueInputImage());
                this.lock.unlock();
                return of2;
            }
            String str = TAG;
            SLog.w(str, "wait new image writer available...E: state(" + this.state.get() + ")");
            this.condition.await();
            SLog.w(str, "wait new image writer available...X: state(" + this.state.get() + ")");
            MediaBuffer of22 = MediaBuffer.of(MediaType.IMAGE, this.imageWriter.dequeueInputImage());
            this.lock.unlock();
            return of22;
        } catch (InterruptedException e) {
            String str2 = TAG;
            SLog.i(str2, "interrupted: " + e.getMessage());
            this.lock.unlock();
            return null;
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public int getCapacity() {
        return this.bufferChannel.getCapacity();
    }

    public int getFormat() {
        if (Build.VERSION.SDK_INT >= 33) {
            return ((Integer) Optional.ofNullable(this.imageWriter).map(new m(this, 3)).orElse(0)).intValue();
        }
        return this.format;
    }

    public int getHeight() {
        if (Build.VERSION.SDK_INT >= 33) {
            return ((Integer) Optional.ofNullable(this.imageWriter).map(new m(this, 2)).orElse(-1)).intValue();
        }
        return this.height;
    }

    public long getUsage() {
        if (Build.VERSION.SDK_INT >= 33) {
            return ((Long) Optional.ofNullable(this.imageWriter).map(new m(this, 1)).orElse(0L)).longValue();
        }
        throw new UnsupportedOperationException("Not supported in this API level");
    }

    public int getWidth() {
        if (Build.VERSION.SDK_INT >= 33) {
            return ((Integer) Optional.ofNullable(this.imageWriter).map(new m(this, 0)).orElse(-1)).intValue();
        }
        return this.width;
    }

    public boolean isClosedForReceive() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(4)).orElseGet(new n(this, 0))).booleanValue();
    }

    public boolean isClosedForSend() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(3)).orElseGet(new n(this, 1))).booleanValue();
    }

    public boolean isRequireToConfigure(int i2, int i7, int i8, long j2) {
        return isRequireToConfigure();
    }

    public void reset() {
        SLog.d(TAG, "reset....E");
        this.lock.lock();
        try {
            Thread thread = this.bufferChannelThread;
            if (thread != null) {
                thread.interrupt();
                this.bufferChannelThread.join();
            }
            ImageWriter imageWriter2 = this.imageWriter;
            if (imageWriter2 != null) {
                imageWriter2.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            this.bufferChannelThread = null;
            this.imageWriter = null;
            this.lock.unlock();
            throw th;
        }
        this.bufferChannelThread = null;
        this.imageWriter = null;
        this.lock.unlock();
        SLog.d(TAG, "reset....X");
    }

    public void setCapacity(int i2) {
        this.capacity = i2;
        if (i2 > 62) {
            String str = TAG;
            SLog.d(str, "too large capacity " + i2 + ", adjust to 62");
            this.capacity = 62;
        }
    }

    public boolean isRequireToConfigure() {
        return this.imageWriter == null || this.state.get() == SurfaceChannel.State.SUSPEND;
    }

    public MediaBuffer receive() {
        return (MediaBuffer) Optional.ofNullable(this.bufferChannel).map(new b(2)).orElseThrow(new u(4));
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0077 A[Catch:{ InterruptedException -> 0x0059, all -> 0x0056 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0084 A[SYNTHETIC, Splitter:B:28:0x0084] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void send(com.samsung.android.sum.core.buffer.MediaBuffer r8) {
        /*
            r7 = this;
            java.lang.Class<android.media.Image> r0 = android.media.Image.class
            java.lang.String r1 = "interrupted, skip this: "
            java.lang.String r2 = "# of processed frames: "
            com.samsung.android.sum.core.channel.BufferChannel r3 = r7.bufferChannel
            if (r3 == 0) goto L_0x0042
            com.samsung.android.sum.core.format.MediaFormat r3 = r8.getFormat()
            com.samsung.android.sum.core.types.MediaType r3 = r3.getMediaType()
            boolean r3 = r3.isMetaData()
            r4 = 8
            if (r3 != 0) goto L_0x0024
            int[] r3 = new int[]{r4}
            boolean r3 = r8.containFlags(r3)
            if (r3 == 0) goto L_0x0042
        L_0x0024:
            int[] r0 = new int[]{r4}
            boolean r0 = r8.containFlags(r0)
            if (r0 == 0) goto L_0x0035
            java.util.concurrent.atomic.AtomicReference<com.samsung.android.sum.core.channel.SurfaceChannel$State> r0 = r7.state
            com.samsung.android.sum.core.channel.SurfaceChannel$State r1 = com.samsung.android.sum.core.channel.SurfaceChannel.State.SUSPEND
            r0.set(r1)
        L_0x0035:
            java.lang.String r0 = TAG
            java.lang.String r1 = "send meta or eos buffer to bufferchannel"
            com.samsung.android.sum.core.SLog.d((java.lang.String) r0, (java.lang.String) r1)
            com.samsung.android.sum.core.channel.BufferChannel r7 = r7.bufferChannel
            r7.send(r8)
            return
        L_0x0042:
            java.util.concurrent.locks.ReentrantLock r3 = r7.lock
            r3.lock()
            android.media.ImageWriter r3 = r7.imageWriter     // Catch:{ InterruptedException -> 0x0059 }
            if (r3 == 0) goto L_0x005c
            java.util.concurrent.atomic.AtomicReference<com.samsung.android.sum.core.channel.SurfaceChannel$State> r3 = r7.state     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.Object r3 = r3.get()     // Catch:{ InterruptedException -> 0x0059 }
            com.samsung.android.sum.core.channel.SurfaceChannel$State r4 = com.samsung.android.sum.core.channel.SurfaceChannel.State.SUSPEND     // Catch:{ InterruptedException -> 0x0059 }
            if (r3 != r4) goto L_0x006d
            goto L_0x005c
        L_0x0056:
            r8 = move-exception
            goto L_0x0120
        L_0x0059:
            r8 = move-exception
            goto L_0x0105
        L_0x005c:
            java.lang.String r3 = TAG     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.String r4 = "wait until new writer will be available...E"
            com.samsung.android.sum.core.SLog.w((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ InterruptedException -> 0x0059 }
            java.util.concurrent.locks.Condition r4 = r7.condition     // Catch:{ InterruptedException -> 0x0059 }
            r4.await()     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.String r4 = "wait until new writer will be available...X"
            com.samsung.android.sum.core.SLog.w((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ InterruptedException -> 0x0059 }
        L_0x006d:
            java.util.concurrent.atomic.AtomicReference<com.samsung.android.sum.core.channel.SurfaceChannel$State> r3 = r7.state     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.Object r3 = r3.get()     // Catch:{ InterruptedException -> 0x0059 }
            com.samsung.android.sum.core.channel.SurfaceChannel$State r4 = com.samsung.android.sum.core.channel.SurfaceChannel.State.OPEN     // Catch:{ InterruptedException -> 0x0059 }
            if (r3 == r4) goto L_0x0084
            java.lang.String r8 = TAG     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.String r0 = "channel is already closed, ignore this"
            com.samsung.android.sum.core.SLog.d((java.lang.String) r8, (java.lang.String) r0)     // Catch:{ InterruptedException -> 0x0059 }
            java.util.concurrent.locks.ReentrantLock r7 = r7.lock
            r7.unlock()
            return
        L_0x0084:
            java.lang.Class r3 = r8.getDataClass()     // Catch:{ InterruptedException -> 0x0059 }
            boolean r3 = r0.isAssignableFrom(r3)     // Catch:{ InterruptedException -> 0x0059 }
            if (r3 == 0) goto L_0x00b3
            java.lang.String r3 = TAG     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.String r4 = "media-buffer contains image, just move it"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.String r3 = "timestampUs"
            java.lang.Object r3 = r8.getExtra(r3)     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ InterruptedException -> 0x0059 }
            long r3 = r3.longValue()     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.Object r0 = r8.getTypedData(r0)     // Catch:{ InterruptedException -> 0x0059 }
            android.media.Image r0 = (android.media.Image) r0     // Catch:{ InterruptedException -> 0x0059 }
            r5 = 1000(0x3e8, double:4.94E-321)
            long r3 = r3 * r5
            r0.setTimestamp(r3)     // Catch:{ InterruptedException -> 0x0059 }
            android.media.ImageWriter r3 = r7.imageWriter     // Catch:{ InterruptedException -> 0x0059 }
            r3.queueInputImage(r0)     // Catch:{ InterruptedException -> 0x0059 }
            goto L_0x00d9
        L_0x00b3:
            boolean r0 = r8.isEmpty()     // Catch:{ InterruptedException -> 0x0059 }
            if (r0 != 0) goto L_0x00ff
            long r3 = r8.size()     // Catch:{ InterruptedException -> 0x0059 }
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 != 0) goto L_0x00c4
            goto L_0x00ff
        L_0x00c4:
            java.lang.String r0 = TAG     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.String r3 = "media-buffer contains not image, copy it"
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r3)     // Catch:{ InterruptedException -> 0x0059 }
            java.util.List r0 = r8.asList()     // Catch:{ InterruptedException -> 0x0059 }
            ba.a r3 = new ba.a     // Catch:{ InterruptedException -> 0x0059 }
            r4 = 29
            r3.<init>(r4, r7)     // Catch:{ InterruptedException -> 0x0059 }
            r0.forEach(r3)     // Catch:{ InterruptedException -> 0x0059 }
        L_0x00d9:
            int r0 = r7.processedFrames     // Catch:{ InterruptedException -> 0x0059 }
            java.util.List r8 = r8.asList()     // Catch:{ InterruptedException -> 0x0059 }
            int r8 = r8.size()     // Catch:{ InterruptedException -> 0x0059 }
            int r0 = r0 + r8
            r7.processedFrames = r0     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.String r8 = TAG     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ InterruptedException -> 0x0059 }
            r0.<init>(r2)     // Catch:{ InterruptedException -> 0x0059 }
            int r2 = r7.processedFrames     // Catch:{ InterruptedException -> 0x0059 }
            r0.append(r2)     // Catch:{ InterruptedException -> 0x0059 }
            java.lang.String r0 = r0.toString()     // Catch:{ InterruptedException -> 0x0059 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r8, (java.lang.String) r0)     // Catch:{ InterruptedException -> 0x0059 }
            java.util.concurrent.locks.ReentrantLock r7 = r7.lock
            r7.unlock()
            return
        L_0x00ff:
            java.util.concurrent.locks.ReentrantLock r7 = r7.lock
            r7.unlock()
            return
        L_0x0105:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x0056 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0056 }
            r2.<init>(r1)     // Catch:{ all -> 0x0056 }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x0056 }
            r2.append(r8)     // Catch:{ all -> 0x0056 }
            java.lang.String r8 = r2.toString()     // Catch:{ all -> 0x0056 }
            com.samsung.android.sum.core.SLog.i((java.lang.String) r0, (java.lang.String) r8)     // Catch:{ all -> 0x0056 }
            java.util.concurrent.locks.ReentrantLock r7 = r7.lock
            r7.unlock()
            return
        L_0x0120:
            java.util.concurrent.locks.ReentrantLock r7 = r7.lock
            r7.unlock()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.channel.StapleSurfaceWriteChannel.send(com.samsung.android.sum.core.buffer.MediaBuffer):void");
    }
}
