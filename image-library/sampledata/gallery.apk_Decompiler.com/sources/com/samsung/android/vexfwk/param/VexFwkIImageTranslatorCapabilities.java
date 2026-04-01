package com.samsung.android.vexfwk.param;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\bB\u0017\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkIImageTranslatorCapabilities;", "Ljava/util/LinkedList;", "", "elements", "<init>", "(Ljava/util/LinkedList;)V", "", "([I)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkIImageTranslatorCapabilities extends LinkedList<Integer> {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkIImageTranslatorCapabilities$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkIImageTranslatorCapabilities;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkIImageTranslatorCapabilities> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkIImageTranslatorCapabilities fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            LinkedList linkedList = new LinkedList();
            int i2 = byteBuffer.getInt();
            if (byteBuffer.remaining() == i2 * 4) {
                for (int i7 = 0; i7 < i2; i7++) {
                    linkedList.add(Integer.valueOf(byteBuffer.getInt()));
                }
                return new VexFwkIImageTranslatorCapabilities((LinkedList<Integer>) linkedList);
            }
            throw new IllegalStateException("Check failed.");
        }

        public ByteBuffer toBuffer(VexFwkIImageTranslatorCapabilities vexFwkIImageTranslatorCapabilities) {
            j.e(vexFwkIImageTranslatorCapabilities, "value");
            ByteBuffer allocateBuffer = allocateBuffer((vexFwkIImageTranslatorCapabilities.size() + 1) * 4);
            allocateBuffer.putInt(vexFwkIImageTranslatorCapabilities.size());
            Iterator it = vexFwkIImageTranslatorCapabilities.iterator();
            while (it.hasNext()) {
                allocateBuffer.putInt(((Number) it.next()).intValue());
            }
            return allocateBuffer;
        }
    }

    public VexFwkIImageTranslatorCapabilities(LinkedList<Integer> linkedList) {
        j.e(linkedList, "elements");
        addAll(linkedList);
    }

    public /* bridge */ boolean contains(Integer num) {
        return super.contains(num);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(Integer num) {
        return super.indexOf(num);
    }

    public /* bridge */ int lastIndexOf(Integer num) {
        return super.lastIndexOf(num);
    }

    public final /* bridge */ Integer remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ Integer removeAt(int i2) {
        return (Integer) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof Integer)) {
            return false;
        }
        return contains((Integer) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        return indexOf((Integer) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        return lastIndexOf((Integer) obj);
    }

    public /* bridge */ boolean remove(Integer num) {
        return super.remove(num);
    }

    public VexFwkIImageTranslatorCapabilities(int[] iArr) {
        j.e(iArr, "elements");
        for (int valueOf : iArr) {
            add(Integer.valueOf(valueOf));
        }
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj == null ? true : obj instanceof Integer)) {
            return false;
        }
        return remove((Integer) obj);
    }
}
