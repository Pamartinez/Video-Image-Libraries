package com.samsung.android.vexfwk.ocr;

import com.samsung.android.vexfwk.param.VexFwkParamBaseBuffer;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000bB\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0003\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\bB\u0017\b\u0016\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\t¢\u0006\u0004\b\u0003\u0010\n¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrCapabilities;", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrCapabilityType;", "<init>", "()V", "capabilities", "", "([I)V", "(Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrCapabilityType;)V", "", "(Ljava/util/List;)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageOcrCapabilities extends LinkedList<VexFwkImageOcrCapabilityType> {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrCapabilities$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/ocr/VexFwkImageOcrCapabilities;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkImageOcrCapabilities> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkImageOcrCapabilities fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            int i2 = byteBuffer.getInt();
            if (byteBuffer.remaining() == i2 * 4) {
                VexFwkImageOcrCapabilities vexFwkImageOcrCapabilities = new VexFwkImageOcrCapabilities();
                for (int i7 = 0; i7 < i2; i7++) {
                    vexFwkImageOcrCapabilities.add(VexFwkImageOcrCapabilityType.Companion.fromInt(byteBuffer.getInt()));
                }
                return vexFwkImageOcrCapabilities;
            }
            throw new IllegalStateException("Check failed.");
        }

        public ByteBuffer toBuffer(VexFwkImageOcrCapabilities vexFwkImageOcrCapabilities) {
            j.e(vexFwkImageOcrCapabilities, "value");
            ByteBuffer allocateBuffer = allocateBuffer((vexFwkImageOcrCapabilities.size() + 1) * 4);
            allocateBuffer.putInt(vexFwkImageOcrCapabilities.size());
            Iterator it = vexFwkImageOcrCapabilities.iterator();
            while (it.hasNext()) {
                allocateBuffer.putInt(((VexFwkImageOcrCapabilityType) it.next()).getValue());
            }
            return allocateBuffer;
        }
    }

    public VexFwkImageOcrCapabilities() {
    }

    public /* bridge */ boolean contains(VexFwkImageOcrCapabilityType vexFwkImageOcrCapabilityType) {
        return super.contains(vexFwkImageOcrCapabilityType);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(VexFwkImageOcrCapabilityType vexFwkImageOcrCapabilityType) {
        return super.indexOf(vexFwkImageOcrCapabilityType);
    }

    public /* bridge */ int lastIndexOf(VexFwkImageOcrCapabilityType vexFwkImageOcrCapabilityType) {
        return super.lastIndexOf(vexFwkImageOcrCapabilityType);
    }

    public final /* bridge */ VexFwkImageOcrCapabilityType remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ VexFwkImageOcrCapabilityType removeAt(int i2) {
        return (VexFwkImageOcrCapabilityType) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public VexFwkImageOcrCapabilities(int[] iArr) {
        j.e(iArr, "capabilities");
        for (int fromInt : iArr) {
            add(VexFwkImageOcrCapabilityType.Companion.fromInt(fromInt));
        }
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof VexFwkImageOcrCapabilityType)) {
            return false;
        }
        return contains((VexFwkImageOcrCapabilityType) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof VexFwkImageOcrCapabilityType)) {
            return -1;
        }
        return indexOf((VexFwkImageOcrCapabilityType) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof VexFwkImageOcrCapabilityType)) {
            return -1;
        }
        return lastIndexOf((VexFwkImageOcrCapabilityType) obj);
    }

    public /* bridge */ boolean remove(VexFwkImageOcrCapabilityType vexFwkImageOcrCapabilityType) {
        return super.remove(vexFwkImageOcrCapabilityType);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof VexFwkImageOcrCapabilityType)) {
            return false;
        }
        return remove((VexFwkImageOcrCapabilityType) obj);
    }

    public VexFwkImageOcrCapabilities(VexFwkImageOcrCapabilityType vexFwkImageOcrCapabilityType) {
        j.e(vexFwkImageOcrCapabilityType, "capabilities");
        add(vexFwkImageOcrCapabilityType);
    }

    public VexFwkImageOcrCapabilities(List<? extends VexFwkImageOcrCapabilityType> list) {
        j.e(list, "capabilities");
        addAll(list);
    }
}
