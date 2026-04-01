package com.samsung.android.sum.core.channel;

import android.hardware.HardwareBuffer;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import ba.C0582a;
import com.samsung.android.motionphoto.core.MPSurfaceReader;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.channel.SurfaceChannel;
import com.samsung.android.sum.core.functional.CountingLatch;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.Usage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetachableSurfaceReadChannel implements SurfaceReadChannel {
    private static final int HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SPN = 291;
    private static final int HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SP_M = 261;
    private static final int HAL_PIXEL_FORMAT_EXYNOS_YCrCb_420_SP_M = 285;
    private static final int HAL_PIXEL_FORMAT_YCbCr_420_SP_VENUS = 2141391876;
    private static final long MAX_TIMEOUT_TO_RESET_MS = 3000;
    private static final String TAG = Def.tagOf((Class<?>) DetachableSurfaceReadChannel.class);
    private static final Map<ColorFormat, int[]> vendorSpecificColorFormat = new HashMap<ColorFormat, int[]>() {
        {
            put(ColorFormat.NV12, new int[]{DetachableSurfaceReadChannel.HAL_PIXEL_FORMAT_YCbCr_420_SP_VENUS, 261, DetachableSurfaceReadChannel.HAL_PIXEL_FORMAT_EXYNOS_YCbCr_420_SPN});
            put(ColorFormat.NV21, new int[]{DetachableSurfaceReadChannel.HAL_PIXEL_FORMAT_EXYNOS_YCrCb_420_SP_M});
        }
    };
    private BufferChannel bufferChannel;
    private int capacity;
    private final Condition condition;
    private BufferChannelDescriptor descriptor;
    private int droppedFrames;
    private long endTimestampUs;
    private MediaBuffer eosBuffer;
    private int format;
    private int height;
    private CountingLatch imageUseCounter;
    private long lastTimestampUs;
    private final ReentrantLock lock;
    private int numFramesToProcess;
    private ColorFormat pixelFormat;
    private int processedFrames;
    private HandlerThread receiveThread;
    private final AtomicReference<SurfaceChannel.State> state;
    private final List<MediaBuffer> storedBuffers;
    private MPSurfaceReader surfaceReader;
    private final BlockingQueue<Long> timestampQueue;
    private long usage;
    private int width;

    public DetachableSurfaceReadChannel() {
        this((BufferChannel) null);
    }

    private boolean isReachedEOS() {
        int i2 = this.numFramesToProcess;
        if (i2 > 0 && i2 <= this.processedFrames + this.droppedFrames) {
            return true;
        }
        long j2 = this.endTimestampUs;
        if (j2 <= 0 || j2 > this.lastTimestampUs) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isClosedForReceive$5() {
        boolean z;
        if (this.state.get() != SurfaceChannel.State.OPEN) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isClosedForSend$4() {
        boolean z;
        if (this.state.get() != SurfaceChannel.State.OPEN) {
            z = true;
        } else {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onImageAvailable$1(HardwareBuffer hardwareBuffer, int i2) {
        if (i2 == hardwareBuffer.getFormat()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onImageAvailable$3(Long l) {
        String str = TAG;
        SLog.v(str, "buffer release: ts=" + l + ", block-id=" + this.processedFrames);
        CountingLatch countingLatch = this.imageUseCounter;
        if (countingLatch != null) {
            int down = countingLatch.down();
            SLog.v(str, "released down use count, now: " + down);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$send$0(MediaBuffer mediaBuffer) {
        if (mediaBuffer.containsExtra("block-check")) {
            this.timestampQueue.add((Long) mediaBuffer.getExtra(Message.KEY_TIMESTAMP_US));
            return;
        }
        this.processedFrames++;
        this.bufferChannel.send(mediaBuffer);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x016e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onImageAvailable(com.samsung.android.motionphoto.core.MPSurfaceReader r9) {
        /*
            r8 = this;
            java.lang.String r0 = TAG
            java.lang.String r1 = "onImageAvailable...E: "
            com.samsung.android.sum.core.SLog.d((java.lang.String) r0, (java.lang.String) r1)
            r1 = 1
            com.samsung.android.motionphoto.core.MPSurfaceReader$MPSurfaceImage r9 = r9.acquireNextImage()     // Catch:{ Exception -> 0x0157 }
            if (r9 == 0) goto L_0x014c
            android.hardware.HardwareBuffer r2 = r9.getHardwareBuffer()     // Catch:{ Exception -> 0x0072 }
            if (r2 == 0) goto L_0x014c
            android.hardware.HardwareBuffer r2 = r9.getHardwareBuffer()     // Catch:{ Exception -> 0x0072 }
            boolean r2 = r2.isClosed()     // Catch:{ Exception -> 0x0072 }
            if (r2 == 0) goto L_0x0020
            goto L_0x014c
        L_0x0020:
            android.hardware.HardwareBuffer r0 = r9.getHardwareBuffer()     // Catch:{ Exception -> 0x0072 }
            long r2 = r9.getTimestamp()     // Catch:{ Exception -> 0x0072 }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Exception -> 0x0072 }
        L_0x002c:
            java.util.concurrent.BlockingQueue<java.lang.Long> r3 = r8.timestampQueue     // Catch:{ Exception -> 0x0072 }
            boolean r3 = r3.isEmpty()     // Catch:{ Exception -> 0x0072 }
            if (r3 != 0) goto L_0x0075
            java.util.concurrent.BlockingQueue<java.lang.Long> r3 = r8.timestampQueue     // Catch:{ Exception -> 0x0072 }
            java.lang.Object r3 = r3.take()     // Catch:{ Exception -> 0x0072 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ Exception -> 0x0072 }
            long r3 = r3.longValue()     // Catch:{ Exception -> 0x0072 }
            long r5 = r9.getTimestamp()     // Catch:{ Exception -> 0x0072 }
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x0049
            goto L_0x0075
        L_0x0049:
            java.lang.String r5 = TAG     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r6.<init>()     // Catch:{ Exception -> 0x0072 }
            java.lang.String r7 = "frame is dropped on timestamp: stored="
            r6.append(r7)     // Catch:{ Exception -> 0x0072 }
            r6.append(r3)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r3 = " vs image="
            r6.append(r3)     // Catch:{ Exception -> 0x0072 }
            r6.append(r2)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r3 = r6.toString()     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.SLog.w((java.lang.String) r5, (java.lang.String) r3)     // Catch:{ Exception -> 0x0072 }
            int r3 = r8.droppedFrames     // Catch:{ Exception -> 0x0072 }
            int r3 = r3 + r1
            r8.droppedFrames = r3     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.functional.CountingLatch r3 = r8.imageUseCounter     // Catch:{ Exception -> 0x0072 }
            r3.down()     // Catch:{ Exception -> 0x0072 }
            goto L_0x002c
        L_0x0072:
            r0 = move-exception
            goto L_0x0159
        L_0x0075:
            com.samsung.android.sum.core.types.ColorFormat r3 = r8.pixelFormat     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.types.ColorFormat r4 = com.samsung.android.sum.core.types.ColorFormat.NONE     // Catch:{ Exception -> 0x0072 }
            r5 = 0
            if (r3 != r4) goto L_0x00a9
            java.util.Map<com.samsung.android.sum.core.types.ColorFormat, int[]> r3 = vendorSpecificColorFormat     // Catch:{ Exception -> 0x0072 }
            java.util.Set r3 = r3.entrySet()     // Catch:{ Exception -> 0x0072 }
            java.util.stream.Stream r3 = r3.stream()     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.channel.g r4 = new com.samsung.android.sum.core.channel.g     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r0, r5)     // Catch:{ Exception -> 0x0072 }
            java.util.stream.Stream r3 = r3.filter(r4)     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.buffer.G r4 = new com.samsung.android.sum.core.buffer.G     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0072 }
            java.util.stream.Stream r3 = r3.map(r4)     // Catch:{ Exception -> 0x0072 }
            java.util.Optional r3 = r3.findFirst()     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.channel.h r4 = new com.samsung.android.sum.core.channel.h     // Catch:{ Exception -> 0x0072 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0072 }
            java.lang.Object r3 = r3.orElseThrow(r4)     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.types.ColorFormat r3 = (com.samsung.android.sum.core.types.ColorFormat) r3     // Catch:{ Exception -> 0x0072 }
            r8.pixelFormat = r3     // Catch:{ Exception -> 0x0072 }
        L_0x00a9:
            java.lang.String r3 = TAG     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r4.<init>()     // Catch:{ Exception -> 0x0072 }
            java.lang.String r6 = "fmt="
            r4.append(r6)     // Catch:{ Exception -> 0x0072 }
            int r6 = r9.getFormat()     // Catch:{ Exception -> 0x0072 }
            java.lang.String r6 = java.lang.Integer.toHexString(r6)     // Catch:{ Exception -> 0x0072 }
            r4.append(r6)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r6 = "["
            r4.append(r6)     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.types.ColorFormat r6 = r8.pixelFormat     // Catch:{ Exception -> 0x0072 }
            r4.append(r6)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r6 = "], usage="
            r4.append(r6)     // Catch:{ Exception -> 0x0072 }
            long r6 = r0.getUsage()     // Catch:{ Exception -> 0x0072 }
            java.lang.String r0 = java.lang.Long.toHexString(r6)     // Catch:{ Exception -> 0x0072 }
            r4.append(r0)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r3, (java.lang.String) r0)     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.types.ColorFormat r0 = r8.pixelFormat     // Catch:{ Exception -> 0x0072 }
            int r4 = r9.getStride()     // Catch:{ Exception -> 0x0072 }
            int r6 = r9.getScanline()     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.buffer.Align r4 = com.samsung.android.sum.core.buffer.Align.of(r4, r6)     // Catch:{ Exception -> 0x0072 }
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r4, r9}     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.buffer.MediaBuffer r0 = com.samsung.android.sum.core.buffer.MediaBuffer.of((java.lang.Object[]) r0)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r4 = "timestampUs"
            r0.setExtra(r4, r2)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r4 = "block-id"
            int r6 = r8.processedFrames     // Catch:{ Exception -> 0x0072 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0072 }
            r0.setExtra(r4, r6)     // Catch:{ Exception -> 0x0072 }
            androidx.window.embedding.c r4 = new androidx.window.embedding.c     // Catch:{ Exception -> 0x0072 }
            r6 = 25
            r4.<init>(r6, r8, r2)     // Catch:{ Exception -> 0x0072 }
            java.lang.Runnable[] r6 = new java.lang.Runnable[r1]     // Catch:{ Exception -> 0x0072 }
            r6[r5] = r4     // Catch:{ Exception -> 0x0072 }
            r0.addOnReleaseListener(r6)     // Catch:{ Exception -> 0x0072 }
            r8.send((com.samsung.android.sum.core.buffer.MediaBuffer) r0)     // Catch:{ Exception -> 0x0072 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0072 }
            r0.<init>()     // Catch:{ Exception -> 0x0072 }
            java.lang.String r4 = "received buffer="
            r0.append(r4)     // Catch:{ Exception -> 0x0072 }
            r0.append(r9)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r4 = ", # of processed frames: "
            r0.append(r4)     // Catch:{ Exception -> 0x0072 }
            int r4 = r8.processedFrames     // Catch:{ Exception -> 0x0072 }
            r0.append(r4)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r4 = " timestamp: "
            r0.append(r4)     // Catch:{ Exception -> 0x0072 }
            r0.append(r2)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.SLog.d((java.lang.String) r3, (java.lang.String) r0)     // Catch:{ Exception -> 0x0072 }
            com.samsung.android.sum.core.buffer.MediaBuffer r0 = r8.eosBuffer     // Catch:{ Exception -> 0x0072 }
            if (r0 == 0) goto L_0x0176
            boolean r0 = r8.isReachedEOS()     // Catch:{ Exception -> 0x0072 }
            if (r0 == 0) goto L_0x0176
            r8.sendEOS()     // Catch:{ Exception -> 0x0072 }
            goto L_0x0176
        L_0x014c:
            java.lang.String r2 = "hardware buffer is already closed. ignore this"
            com.samsung.android.sum.core.SLog.w((java.lang.String) r0, (java.lang.String) r2)     // Catch:{ Exception -> 0x0072 }
            int r0 = r8.droppedFrames     // Catch:{ Exception -> 0x0072 }
            int r0 = r0 + r1
            r8.droppedFrames = r0     // Catch:{ Exception -> 0x0072 }
            return
        L_0x0157:
            r0 = move-exception
            r9 = 0
        L_0x0159:
            java.lang.String r2 = TAG
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "failed to acquire image: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.samsung.android.sum.core.SLog.w((java.lang.String) r2, (java.lang.String) r0)
            if (r9 == 0) goto L_0x0171
            r9.close()
        L_0x0171:
            int r9 = r8.droppedFrames
            int r9 = r9 + r1
            r8.droppedFrames = r9
        L_0x0176:
            java.lang.String r8 = TAG
            java.lang.String r9 = "onImageAvailable...X"
            com.samsung.android.sum.core.SLog.d((java.lang.String) r8, (java.lang.String) r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.channel.DetachableSurfaceReadChannel.onImageAvailable(com.samsung.android.motionphoto.core.MPSurfaceReader):void");
    }

    private void sendEOS() {
        this.lock.lock();
        try {
            if (this.eosBuffer != null) {
                SLog.i(TAG, String.format("sendEOS: (processedFrames=%d, numFramesToProcess=%d, droppedFrames=%d,endTimeUs=%d, lastTimestampUs=%d", new Object[]{Integer.valueOf(this.processedFrames), Integer.valueOf(this.numFramesToProcess), Integer.valueOf(this.droppedFrames), Long.valueOf(this.endTimestampUs), Long.valueOf(this.lastTimestampUs)}));
                this.bufferChannel.send(this.eosBuffer);
                this.state.set(SurfaceChannel.State.SUSPEND);
                this.eosBuffer = null;
                this.condition.signalAll();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void cancel() {
        this.lock.lock();
        try {
            this.state.set(SurfaceChannel.State.CANCELED);
            reset();
            BufferChannel bufferChannel2 = this.bufferChannel;
            if (bufferChannel2 != null) {
                bufferChannel2.cancel();
            }
        } catch (Exception e) {
            String str = TAG;
            SLog.w(str, "failed to cancel" + e);
            e.printStackTrace();
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
        this.lock.unlock();
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
            String str = TAG;
            SLog.w(str, "failed to close" + e);
            e.printStackTrace();
        } catch (Throwable th) {
            this.bufferChannel = null;
            this.lock.unlock();
            throw th;
        }
        this.bufferChannel = null;
        this.lock.unlock();
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [com.samsung.android.motionphoto.core.MPSurfaceReader$OnImageAvailableListener, java.lang.Object] */
    public void configure(SurfaceChannelConfig surfaceChannelConfig) {
        boolean z;
        int width2 = surfaceChannelConfig.getWidth();
        int height2 = surfaceChannelConfig.getHeight();
        int format2 = surfaceChannelConfig.getFormat();
        long usage2 = surfaceChannelConfig.getUsage();
        if (width2 == 0 || height2 == 0 || format2 == 0) {
            z = false;
        } else {
            z = true;
        }
        Def.check(z);
        String str = TAG;
        SLog.i(str, "configure...E");
        this.lock.lock();
        try {
            if (this.receiveThread == null) {
                this.receiveThread = new HandlerThread("surface-detach-receive-thread");
                SLog.d(str, "start surface-detach-receive-thread");
                this.receiveThread.start();
            }
            SLog.d(str, "surface-reader capacity=" + this.capacity);
            if (!(this.surfaceReader != null && this.width == width2 && this.height == height2 && this.format == format2 && this.usage == usage2)) {
                SLog.i(str, "crate new surface-reader");
                this.width = width2;
                this.height = height2;
                this.format = format2;
                this.usage = usage2;
                if (usage2 == 0) {
                    BufferChannelDescriptor bufferChannelDescriptor = this.descriptor;
                    if (bufferChannelDescriptor == null || bufferChannelDescriptor.getUsage() == 0) {
                        this.usage = Usage.HW_ENCODER.getValue();
                    } else {
                        this.usage = this.descriptor.getUsage();
                    }
                }
                MPSurfaceReader of2 = MPSurfaceReader.of(this.width, this.height, this.format, this.capacity + 1, this.usage);
                this.surfaceReader = of2;
                of2.setOnImageAvailableListener(new Object(), new Handler(this.receiveThread.getLooper()));
            }
            this.state.set(SurfaceChannel.State.OPEN);
            this.imageUseCounter = CountingLatch.downOf();
            this.processedFrames = 0;
            this.droppedFrames = 0;
            this.numFramesToProcess = 0;
            this.lastTimestampUs = 0;
            this.eosBuffer = null;
            SLog.i(str, "configure...X");
            this.lock.unlock();
        } catch (Throwable th) {
            SLog.i(TAG, "configure...X");
            this.lock.unlock();
            throw th;
        }
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getFormat() {
        return this.format;
    }

    public int getHeight() {
        return this.height;
    }

    public Surface getSurface() {
        return this.surfaceReader.getSurface();
    }

    public long getUsage() {
        return this.usage;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isClosedForReceive() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(4)).orElseGet(new e(this, 0))).booleanValue();
    }

    public boolean isClosedForSend() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(4)).orElseGet(new e(this, 1))).booleanValue();
    }

    public boolean isRequireToConfigure() {
        if (this.surfaceReader == null || this.state.get() == SurfaceChannel.State.SUSPEND) {
            return true;
        }
        return false;
    }

    public void reset() {
        String str = TAG;
        SLog.i(str, "reset...E");
        this.lock.lock();
        try {
            if (this.state.get() == SurfaceChannel.State.OPEN) {
                SLog.i(str, "wait for send eos...E");
                if (this.condition.await(3000, TimeUnit.MILLISECONDS)) {
                    SLog.i(str, "wait for send eos...X");
                } else {
                    throw new IllegalStateException("failed to send eos on time 3000");
                }
            }
            try {
                HandlerThread handlerThread = this.receiveThread;
                if (handlerThread != null) {
                    handlerThread.quit();
                }
            } catch (Exception e) {
                String str2 = TAG;
                SLog.w(str2, "fail to quit receiveThread: " + e);
            }
            CountingLatch countingLatch = this.imageUseCounter;
            if (countingLatch != null) {
                try {
                    countingLatch.await(0, 2000);
                } catch (Exception e7) {
                    String str3 = TAG;
                    SLog.w(str3, "timeout on wait for imageUseCounter: " + e7);
                }
                this.imageUseCounter.reset();
            }
            MPSurfaceReader mPSurfaceReader = this.surfaceReader;
            if (mPSurfaceReader != null) {
                mPSurfaceReader.close();
            }
            this.width = -1;
            this.height = -1;
            this.format = 0;
            this.usage = 0;
            this.timestampQueue.clear();
            this.receiveThread = null;
            this.surfaceReader = null;
            this.imageUseCounter = null;
            this.lock.unlock();
            SLog.i(TAG, "reset...X");
        } catch (Exception e8) {
            String str4 = TAG;
            SLog.w(str4, "failed to reset" + e8);
            this.width = -1;
            this.height = -1;
            this.format = 0;
            this.usage = 0;
            this.timestampQueue.clear();
            this.receiveThread = null;
            this.surfaceReader = null;
            this.imageUseCounter = null;
            this.lock.unlock();
            SLog.i(str4, "reset...X");
        } catch (Throwable th) {
            this.width = -1;
            this.height = -1;
            this.format = 0;
            this.usage = 0;
            this.timestampQueue.clear();
            this.receiveThread = null;
            this.surfaceReader = null;
            this.imageUseCounter = null;
            this.lock.unlock();
            SLog.i(TAG, "reset...X");
            throw th;
        }
    }

    public void setCapacity(int i2) {
        this.capacity = i2;
        if (i2 > 62) {
            String str = TAG;
            SLog.d(str, "too large capacity " + i2 + ", adjust to 62");
            this.capacity = 62;
        }
    }

    public DetachableSurfaceReadChannel(BufferChannel bufferChannel2) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.capacity = 16;
        this.pixelFormat = ColorFormat.NONE;
        this.processedFrames = 0;
        this.droppedFrames = 0;
        this.numFramesToProcess = 0;
        this.endTimestampUs = 0;
        this.lastTimestampUs = 0;
        this.state = new AtomicReference<>(SurfaceChannel.State.OPEN);
        this.storedBuffers = new CopyOnWriteArrayList();
        this.timestampQueue = new LinkedBlockingQueue();
        this.width = -1;
        this.height = -1;
        this.format = 0;
        this.usage = 0;
        this.condition = reentrantLock.newCondition();
        this.bufferChannel = bufferChannel2;
    }

    public MediaBuffer receive() {
        return (MediaBuffer) Optional.ofNullable(this.bufferChannel).map(new b(2)).orElseThrow(new u(4));
    }

    public void send(MediaBuffer mediaBuffer) {
        try {
            if (this.state.get() == SurfaceChannel.State.OPEN) {
                if (!this.storedBuffers.isEmpty()) {
                    SLog.i(TAG, "restore & send buffers on size=" + this.storedBuffers.size());
                    this.storedBuffers.forEach(new C0582a(27, this));
                }
                if (mediaBuffer.containFlags(8)) {
                    this.numFramesToProcess = ((Integer) mediaBuffer.getExtra(Message.KEY_WHOLE_FRAMES, 0)).intValue();
                    this.endTimestampUs = ((Long) mediaBuffer.getExtra(Message.KEY_END_TIME_US, 0L)).longValue();
                    this.eosBuffer = mediaBuffer;
                    String str = TAG;
                    SLog.d(str, "receive EOS buffer. numFramesToProcess=" + this.numFramesToProcess + ", endTimestampUs=" + this.endTimestampUs);
                    if (isReachedEOS()) {
                        sendEOS();
                    } else {
                        SLog.d(str, Def.fmtstr("delay eos until whole frames are processed: #[%d/%d], ts[%d/%d us]", Integer.valueOf(this.processedFrames), Integer.valueOf(this.numFramesToProcess), Long.valueOf(this.lastTimestampUs), Long.valueOf(this.endTimestampUs)));
                    }
                } else if (mediaBuffer.containsExtra("block-check")) {
                    this.lock.lock();
                    this.imageUseCounter.up();
                    this.imageUseCounter.await(this.capacity, 3000);
                    this.timestampQueue.add((Long) mediaBuffer.getExtra(Message.KEY_TIMESTAMP_US));
                    this.lock.unlock();
                } else {
                    this.bufferChannel.send(mediaBuffer);
                    this.lastTimestampUs = ((Long) mediaBuffer.getExtra(Message.KEY_TIMESTAMP_US, -1L)).longValue();
                    this.processedFrames++;
                }
            } else {
                this.storedBuffers.add(mediaBuffer);
                SLog.d(TAG, "state = " + this.state + ", stored buffer size=" + this.storedBuffers.size());
            }
        } catch (IllegalStateException e) {
            SLog.w(TAG, "imageUseCounter timeout. send error: " + e);
            throw e;
        } catch (Exception e7) {
            SLog.w(TAG, "failed to get image " + e7);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }
}
