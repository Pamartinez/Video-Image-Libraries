package com.samsung.android.vexfwk.surface;

import android.view.Surface;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\nH\u0004¢\u0006\u0004\b\u0015\u0010\u000eR\u0014\u0010\u0016\u001a\u00020\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/vexfwk/surface/VexFwkSurfaceNative;", "", "Landroid/view/Surface;", "surface", "<init>", "(Landroid/view/Surface;)V", "", "width", "height", "rotationDegree", "Lme/x;", "configureSurface", "(III)V", "releaseSurface", "()V", "Ljava/nio/ByteBuffer;", "buffer", "", "timestampNs", "queueBufferWithCopy", "(Ljava/nio/ByteBuffer;IIJ)V", "finalize", "nativeHandle", "J", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkSurfaceNative {
    public static final Companion Companion = new Companion((e) null);
    private final long nativeHandle;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H ¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H ¢\u0006\u0004\b\u000b\u0010\fJ0\u0010\u0011\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH ¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0013\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H ¢\u0006\u0004\b\u0013\u0010\fJ8\u0010\u0017\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0006H ¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/vexfwk/surface/VexFwkSurfaceNative$Companion;", "", "<init>", "()V", "Landroid/view/Surface;", "surface", "", "createHandleNative", "(Landroid/view/Surface;)J", "nativeHandle", "Lme/x;", "freeHandleNative", "(J)V", "", "width", "height", "rotationDegree", "configureSurfaceNative", "(JIII)V", "releaseSurfaceNative", "Ljava/nio/ByteBuffer;", "buffer", "timestampNs", "queueBufferWithCopyNative", "(JLjava/nio/ByteBuffer;IIJ)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final void configureSurfaceNative(long j2, int i2, int i7, int i8) {
            VexFwkSurfaceNative.configureSurfaceNative(j2, i2, i7, i8);
        }

        /* access modifiers changed from: private */
        public final long createHandleNative(Surface surface) {
            return VexFwkSurfaceNative.createHandleNative(surface);
        }

        /* access modifiers changed from: private */
        public final void freeHandleNative(long j2) {
            VexFwkSurfaceNative.freeHandleNative(j2);
        }

        /* access modifiers changed from: private */
        public final void queueBufferWithCopyNative(long j2, ByteBuffer byteBuffer, int i2, int i7, long j3) {
            VexFwkSurfaceNative.queueBufferWithCopyNative(j2, byteBuffer, i2, i7, j3);
        }

        /* access modifiers changed from: private */
        public final void releaseSurfaceNative(long j2) {
            VexFwkSurfaceNative.releaseSurfaceNative(j2);
        }

        private Companion() {
        }
    }

    public VexFwkSurfaceNative(Surface surface) {
        j.e(surface, "surface");
        this.nativeHandle = Companion.createHandleNative(surface);
    }

    /* access modifiers changed from: private */
    public static final native void configureSurfaceNative(long j2, int i2, int i7, int i8);

    /* access modifiers changed from: private */
    public static final native long createHandleNative(Surface surface);

    /* access modifiers changed from: private */
    public static final native void freeHandleNative(long j2);

    /* access modifiers changed from: private */
    public static final native void queueBufferWithCopyNative(long j2, ByteBuffer byteBuffer, int i2, int i7, long j3);

    /* access modifiers changed from: private */
    public static final native void releaseSurfaceNative(long j2);

    public final void configureSurface(int i2, int i7, int i8) {
        Companion.configureSurfaceNative(this.nativeHandle, i2, i7, i8);
    }

    public final void finalize() {
        Companion.freeHandleNative(this.nativeHandle);
    }

    public final void queueBufferWithCopy(ByteBuffer byteBuffer, int i2, int i7, long j2) {
        j.e(byteBuffer, "buffer");
        Companion.queueBufferWithCopyNative(this.nativeHandle, byteBuffer, i2, i7, j2);
    }

    public final void releaseSurface() {
        Companion.releaseSurfaceNative(this.nativeHandle);
    }
}
