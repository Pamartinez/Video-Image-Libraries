package com.samsung.android.vexfwk.surface;

import android.hardware.HardwareBuffer;
import android.util.Log;
import android.view.Surface;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 #2\u00060\u0001j\u0002`\u0002:\u0001#B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010\r\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J%\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000f¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001a\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001a\u0010\u0004R\u0014\u0010\u001b\u001a\u00020\u00138\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u00020 8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Lcom/samsung/android/vexfwk/surface/VexFwkSurfaceWriterNative;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "<init>", "()V", "Landroid/view/Surface;", "surface", "", "width", "height", "format", "maxBufferCount", "Lme/x;", "configure", "(Landroid/view/Surface;IIII)V", "Landroid/hardware/HardwareBuffer;", "dequeueBuffer", "()Landroid/hardware/HardwareBuffer;", "hardwareBuffer", "", "timestampNs", "rotationDegree", "enqueueBuffer", "(Landroid/hardware/HardwareBuffer;JI)V", "cancelBuffer", "(Landroid/hardware/HardwareBuffer;)V", "close", "nativeHandle", "J", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "", "isClosed", "Z", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkSurfaceWriterNative implements AutoCloseable {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = "VexFwkSurfaceWriterNative";
    private boolean isClosed;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final long nativeHandle = Companion.createHandleNative();

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\t\u0010\nJ@\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH ¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u0004H ¢\u0006\u0004\b\u0015\u0010\u0016J0\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\rH ¢\u0006\u0004\b\u001a\u0010\u001bJ \u0010\u001c\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0014H ¢\u0006\u0004\b\u001c\u0010\u001dR\u001c\u0010 \u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001e8\u0002X\u0004¢\u0006\u0006\n\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcom/samsung/android/vexfwk/surface/VexFwkSurfaceWriterNative$Companion;", "", "<init>", "()V", "", "createHandleNative", "()J", "nativeHandle", "Lme/x;", "freeHandleNative", "(J)V", "Landroid/view/Surface;", "surface", "", "width", "height", "format", "maxBufferCount", "configureNative", "(JLandroid/view/Surface;IIII)V", "Landroid/hardware/HardwareBuffer;", "dequeueBufferNative", "(J)Landroid/hardware/HardwareBuffer;", "hardwareBuffer", "timestampNs", "rotationDegree", "enqueueBufferNative", "(JLandroid/hardware/HardwareBuffer;JI)V", "cancelBufferNative", "(JLandroid/hardware/HardwareBuffer;)V", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final void cancelBufferNative(long j2, HardwareBuffer hardwareBuffer) {
            VexFwkSurfaceWriterNative.cancelBufferNative(j2, hardwareBuffer);
        }

        /* access modifiers changed from: private */
        public final void configureNative(long j2, Surface surface, int i2, int i7, int i8, int i10) {
            VexFwkSurfaceWriterNative.configureNative(j2, surface, i2, i7, i8, i10);
        }

        /* access modifiers changed from: private */
        public final long createHandleNative() {
            return VexFwkSurfaceWriterNative.createHandleNative();
        }

        /* access modifiers changed from: private */
        public final HardwareBuffer dequeueBufferNative(long j2) {
            return VexFwkSurfaceWriterNative.dequeueBufferNative(j2);
        }

        /* access modifiers changed from: private */
        public final void enqueueBufferNative(long j2, HardwareBuffer hardwareBuffer, long j3, int i2) {
            VexFwkSurfaceWriterNative.enqueueBufferNative(j2, hardwareBuffer, j3, i2);
        }

        /* access modifiers changed from: private */
        public final void freeHandleNative(long j2) {
            VexFwkSurfaceWriterNative.freeHandleNative(j2);
        }

        private Companion() {
        }
    }

    /* access modifiers changed from: private */
    public static final native void cancelBufferNative(long j2, HardwareBuffer hardwareBuffer);

    /* access modifiers changed from: private */
    public static final native void configureNative(long j2, Surface surface, int i2, int i7, int i8, int i10);

    /* access modifiers changed from: private */
    public static final native long createHandleNative();

    /* access modifiers changed from: private */
    public static final native HardwareBuffer dequeueBufferNative(long j2);

    /* access modifiers changed from: private */
    public static final native void enqueueBufferNative(long j2, HardwareBuffer hardwareBuffer, long j3, int i2);

    /* access modifiers changed from: private */
    public static final native void freeHandleNative(long j2);

    public final void cancelBuffer(HardwareBuffer hardwareBuffer) {
        j.e(hardwareBuffer, "hardwareBuffer");
        Companion.cancelBufferNative(this.nativeHandle, hardwareBuffer);
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void close() {
        /*
            r7 = this;
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r7.lock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()
            int r2 = r0.getWriteHoldCount()
            r3 = 0
            if (r2 != 0) goto L_0x0012
            int r2 = r0.getReadHoldCount()
            goto L_0x0013
        L_0x0012:
            r2 = r3
        L_0x0013:
            r4 = r3
        L_0x0014:
            if (r4 >= r2) goto L_0x001c
            r1.unlock()
            int r4 = r4 + 1
            goto L_0x0014
        L_0x001c:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.lock()
            r4 = 1
            r7.isClosed = r4     // Catch:{ all -> 0x0039 }
            com.samsung.android.vexfwk.surface.VexFwkSurfaceWriterNative$Companion r4 = Companion     // Catch:{ all -> 0x0039 }
            long r5 = r7.nativeHandle     // Catch:{ all -> 0x0039 }
            r4.freeHandleNative(r5)     // Catch:{ all -> 0x0039 }
        L_0x002d:
            if (r3 >= r2) goto L_0x0035
            r1.lock()
            int r3 = r3 + 1
            goto L_0x002d
        L_0x0035:
            r0.unlock()
            return
        L_0x0039:
            r7 = move-exception
        L_0x003a:
            if (r3 >= r2) goto L_0x0042
            r1.lock()
            int r3 = r3 + 1
            goto L_0x003a
        L_0x0042:
            r0.unlock()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.surface.VexFwkSurfaceWriterNative.close():void");
    }

    public final void configure(Surface surface, int i2, int i7, int i8, int i10) {
        j.e(surface, "surface");
        Companion.configureNative(this.nativeHandle, surface, i2, i7, i8, i10);
    }

    public final HardwareBuffer dequeueBuffer() {
        HardwareBuffer hardwareBuffer;
        ReentrantReadWriteLock.ReadLock readLock = this.lock.readLock();
        readLock.lock();
        try {
            if (this.isClosed) {
                Log.w(TAG, "dequeueImage called after close");
                hardwareBuffer = null;
            } else {
                hardwareBuffer = Companion.dequeueBufferNative(this.nativeHandle);
            }
            return hardwareBuffer;
        } finally {
            readLock.unlock();
        }
    }

    public final void enqueueBuffer(HardwareBuffer hardwareBuffer, long j2, int i2) {
        j.e(hardwareBuffer, "hardwareBuffer");
        Companion.enqueueBufferNative(this.nativeHandle, hardwareBuffer, j2, i2);
    }
}
