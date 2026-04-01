package com.samsung.android.vexfwk.param;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkParamIntegerArray;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkParamIntegerArray {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkParamIntegerArray$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<int[]> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public int[] fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            IntBuffer asIntBuffer = byteBuffer.asIntBuffer();
            int[] iArr = new int[asIntBuffer.capacity()];
            asIntBuffer.get(iArr);
            return iArr;
        }

        public ByteBuffer toBuffer(int[] iArr) {
            j.e(iArr, "value");
            ByteBuffer allocateBuffer = allocateBuffer(iArr.length * 4);
            allocateBuffer.asIntBuffer().put(iArr);
            return allocateBuffer;
        }
    }
}
