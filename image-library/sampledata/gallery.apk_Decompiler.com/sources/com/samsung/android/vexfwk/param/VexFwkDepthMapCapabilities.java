package com.samsung.android.vexfwk.param;

import com.samsung.android.vexfwk.depthmap.DepthModeType;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\nB\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0003\u0010\u0007B\u0017\b\u0016\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\u0004\b\u0003\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDepthMapCapabilities;", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/depthmap/DepthModeType;", "<init>", "()V", "capabilities", "", "([I)V", "", "(Ljava/util/List;)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDepthMapCapabilities extends LinkedList<DepthModeType> {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDepthMapCapabilities$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkDepthMapCapabilities;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkDepthMapCapabilities> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkDepthMapCapabilities fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            int i2 = byteBuffer.getInt();
            if (byteBuffer.remaining() == i2 * 4) {
                VexFwkDepthMapCapabilities vexFwkDepthMapCapabilities = new VexFwkDepthMapCapabilities();
                for (int i7 = 0; i7 < i2; i7++) {
                    vexFwkDepthMapCapabilities.add(DepthModeType.Companion.fromInt(byteBuffer.getInt()));
                }
                return vexFwkDepthMapCapabilities;
            }
            throw new IllegalStateException("Check failed.");
        }

        public ByteBuffer toBuffer(VexFwkDepthMapCapabilities vexFwkDepthMapCapabilities) {
            j.e(vexFwkDepthMapCapabilities, "value");
            ByteBuffer allocateBuffer = allocateBuffer((vexFwkDepthMapCapabilities.size() + 1) * 4);
            allocateBuffer.putInt(vexFwkDepthMapCapabilities.size());
            Iterator it = vexFwkDepthMapCapabilities.iterator();
            while (it.hasNext()) {
                allocateBuffer.putInt(((DepthModeType) it.next()).getValue());
            }
            return allocateBuffer;
        }
    }

    public VexFwkDepthMapCapabilities() {
    }

    public /* bridge */ boolean contains(DepthModeType depthModeType) {
        return super.contains(depthModeType);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(DepthModeType depthModeType) {
        return super.indexOf(depthModeType);
    }

    public /* bridge */ int lastIndexOf(DepthModeType depthModeType) {
        return super.lastIndexOf(depthModeType);
    }

    public final /* bridge */ DepthModeType remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ DepthModeType removeAt(int i2) {
        return (DepthModeType) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public VexFwkDepthMapCapabilities(int[] iArr) {
        j.e(iArr, "capabilities");
        for (int fromInt : iArr) {
            add(DepthModeType.Companion.fromInt(fromInt));
        }
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof DepthModeType)) {
            return false;
        }
        return contains((DepthModeType) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof DepthModeType)) {
            return -1;
        }
        return indexOf((DepthModeType) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof DepthModeType)) {
            return -1;
        }
        return lastIndexOf((DepthModeType) obj);
    }

    public /* bridge */ boolean remove(DepthModeType depthModeType) {
        return super.remove(depthModeType);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof DepthModeType)) {
            return false;
        }
        return remove((DepthModeType) obj);
    }

    public VexFwkDepthMapCapabilities(List<? extends DepthModeType> list) {
        j.e(list, "capabilities");
        addAll(list);
    }
}
