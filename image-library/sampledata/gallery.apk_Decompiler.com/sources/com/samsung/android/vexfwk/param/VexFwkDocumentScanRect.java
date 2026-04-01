package com.samsung.android.vexfwk.param;

import c0.C0086a;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\nR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentScanRect;", "", "left", "", "top", "right", "bottom", "<init>", "(IIII)V", "getLeft", "()I", "getTop", "getRight", "getBottom", "width", "getWidth", "height", "getHeight", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocumentScanRect {
    private static final int BUFFER_SIZE = 16;
    public static final Companion Companion = new Companion((e) null);
    private final int bottom;
    private final int height;
    private final int left;
    private final int right;
    private final int top;
    private final int width;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentScanRect$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentScanRect;", "<init>", "()V", "BUFFER_SIZE", "", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkDocumentScanRect> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkDocumentScanRect fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            if (byteBuffer.capacity() == 16) {
                return new VexFwkDocumentScanRect(byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt(), byteBuffer.getInt());
            }
            throw new IllegalStateException(C0086a.i(byteBuffer.capacity(), "buffer capacity must be 16. buffer capacity is ").toString());
        }

        public ByteBuffer toBuffer(VexFwkDocumentScanRect vexFwkDocumentScanRect) {
            j.e(vexFwkDocumentScanRect, "value");
            ByteBuffer allocateBuffer = allocateBuffer(16);
            allocateBuffer.putInt(vexFwkDocumentScanRect.getLeft());
            allocateBuffer.putInt(vexFwkDocumentScanRect.getTop());
            allocateBuffer.putInt(vexFwkDocumentScanRect.getRight());
            allocateBuffer.putInt(vexFwkDocumentScanRect.getBottom());
            return allocateBuffer;
        }
    }

    public VexFwkDocumentScanRect(int i2, int i7, int i8, int i10) {
        this.left = i2;
        this.top = i7;
        this.right = i8;
        this.bottom = i10;
        this.width = i8 - i2;
        this.height = i10 - i7;
    }

    public final int getBottom() {
        return this.bottom;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getRight() {
        return this.right;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getWidth() {
        return this.width;
    }
}
