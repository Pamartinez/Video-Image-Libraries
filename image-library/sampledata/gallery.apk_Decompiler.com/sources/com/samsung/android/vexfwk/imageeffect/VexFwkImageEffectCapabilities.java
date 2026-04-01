package com.samsung.android.vexfwk.imageeffect;

import com.samsung.android.vexfwk.param.VexFwkParamBaseBuffer;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\nB\t\b\u0016¢\u0006\u0004\b\u0003\u0010\u0004B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0003\u0010\u0007B\u0017\b\u0016\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\b¢\u0006\u0004\b\u0003\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/imageeffect/VexFwkImageEffectCapabilities;", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;", "<init>", "()V", "capabilities", "", "([I)V", "", "(Ljava/util/List;)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageEffectCapabilities extends LinkedList<ImageEffectType> {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/imageeffect/VexFwkImageEffectCapabilities$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/imageeffect/VexFwkImageEffectCapabilities;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkImageEffectCapabilities> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkImageEffectCapabilities fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            int i2 = byteBuffer.getInt();
            if (byteBuffer.remaining() == i2 * 4) {
                VexFwkImageEffectCapabilities vexFwkImageEffectCapabilities = new VexFwkImageEffectCapabilities();
                for (int i7 = 0; i7 < i2; i7++) {
                    vexFwkImageEffectCapabilities.add(ImageEffectType.Companion.fromInt(byteBuffer.getInt()));
                }
                return vexFwkImageEffectCapabilities;
            }
            throw new IllegalStateException("Check failed.");
        }

        public ByteBuffer toBuffer(VexFwkImageEffectCapabilities vexFwkImageEffectCapabilities) {
            j.e(vexFwkImageEffectCapabilities, "value");
            ByteBuffer allocateBuffer = allocateBuffer((vexFwkImageEffectCapabilities.size() + 1) * 4);
            allocateBuffer.putInt(vexFwkImageEffectCapabilities.size());
            Iterator it = vexFwkImageEffectCapabilities.iterator();
            while (it.hasNext()) {
                allocateBuffer.putInt(((ImageEffectType) it.next()).getValue());
            }
            return allocateBuffer;
        }
    }

    public VexFwkImageEffectCapabilities() {
    }

    public /* bridge */ boolean contains(ImageEffectType imageEffectType) {
        return super.contains(imageEffectType);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(ImageEffectType imageEffectType) {
        return super.indexOf(imageEffectType);
    }

    public /* bridge */ int lastIndexOf(ImageEffectType imageEffectType) {
        return super.lastIndexOf(imageEffectType);
    }

    public final /* bridge */ ImageEffectType remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ ImageEffectType removeAt(int i2) {
        return (ImageEffectType) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public VexFwkImageEffectCapabilities(int[] iArr) {
        j.e(iArr, "capabilities");
        for (int fromInt : iArr) {
            add(ImageEffectType.Companion.fromInt(fromInt));
        }
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof ImageEffectType)) {
            return false;
        }
        return contains((ImageEffectType) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof ImageEffectType)) {
            return -1;
        }
        return indexOf((ImageEffectType) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof ImageEffectType)) {
            return -1;
        }
        return lastIndexOf((ImageEffectType) obj);
    }

    public /* bridge */ boolean remove(ImageEffectType imageEffectType) {
        return super.remove(imageEffectType);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof ImageEffectType)) {
            return false;
        }
        return remove((ImageEffectType) obj);
    }

    public VexFwkImageEffectCapabilities(List<? extends ImageEffectType> list) {
        j.e(list, "capabilities");
        addAll(list);
    }
}
