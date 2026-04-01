package com.samsung.android.vexfwk.param;

import android.graphics.PointF;
import c0.C0086a;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkParamPointF;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkParamPointF {
    private static final int BUFFER_SIZE = 8;
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkParamPointF$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Landroid/graphics/PointF;", "<init>", "()V", "BUFFER_SIZE", "", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<PointF> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public PointF fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            if (byteBuffer.capacity() == 8) {
                return new PointF(byteBuffer.getFloat(), byteBuffer.getFloat());
            }
            throw new IllegalStateException(C0086a.i(byteBuffer.capacity(), "buffer capacity must be 8. buffer capacity is ").toString());
        }

        public ByteBuffer toBuffer(PointF pointF) {
            j.e(pointF, "value");
            ByteBuffer putFloat = allocateBuffer(8).putFloat(pointF.x).putFloat(pointF.y);
            j.d(putFloat, "putFloat(...)");
            return putFloat;
        }
    }
}
