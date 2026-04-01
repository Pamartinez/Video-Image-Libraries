package com.samsung.android.vexfwk.param;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0006B\u0017\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirections;", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirection;", "elements", "<init>", "(Ljava/util/LinkedList;)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkLanguageDirections extends LinkedList<VexFwkLanguageDirection> {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0017¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirections$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageDirections;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkLanguageDirections> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkLanguageDirections fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            LinkedList linkedList = new LinkedList();
            int i2 = byteBuffer.getInt();
            for (int i7 = 0; i7 < i2; i7++) {
                byte[] bArr = new byte[byteBuffer.getInt()];
                byteBuffer.get(bArr);
                Charset charset = StandardCharsets.UTF_8;
                j.d(charset, "UTF_8");
                String str = new String(bArr, charset);
                byte[] bArr2 = new byte[byteBuffer.getInt()];
                byteBuffer.get(bArr2);
                linkedList.add(new VexFwkLanguageDirection(str, new String(bArr2, charset)));
            }
            return new VexFwkLanguageDirections(linkedList);
        }

        public ByteBuffer toBuffer(VexFwkLanguageDirections vexFwkLanguageDirections) {
            j.e(vexFwkLanguageDirections, "value");
            Iterator it = vexFwkLanguageDirections.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                VexFwkLanguageDirection vexFwkLanguageDirection = (VexFwkLanguageDirection) it.next();
                String from = vexFwkLanguageDirection.getFrom();
                Charset charset = StandardCharsets.UTF_8;
                j.d(charset, "UTF_8");
                byte[] bytes = from.getBytes(charset);
                j.d(bytes, "getBytes(...)");
                String to = vexFwkLanguageDirection.getTo();
                j.d(charset, "UTF_8");
                byte[] bytes2 = to.getBytes(charset);
                j.d(bytes2, "getBytes(...)");
                i2 += bytes.length + 8 + bytes2.length;
            }
            ByteBuffer putInt = allocateBuffer(4 + i2).order(ByteOrder.nativeOrder()).putInt(vexFwkLanguageDirections.size());
            Iterator it2 = vexFwkLanguageDirections.iterator();
            while (it2.hasNext()) {
                VexFwkLanguageDirection vexFwkLanguageDirection2 = (VexFwkLanguageDirection) it2.next();
                String from2 = vexFwkLanguageDirection2.getFrom();
                Charset charset2 = StandardCharsets.UTF_8;
                j.d(charset2, "UTF_8");
                byte[] bytes3 = from2.getBytes(charset2);
                j.d(bytes3, "getBytes(...)");
                String to2 = vexFwkLanguageDirection2.getTo();
                j.d(charset2, "UTF_8");
                byte[] bytes4 = to2.getBytes(charset2);
                j.d(bytes4, "getBytes(...)");
                putInt.putInt(bytes3.length);
                putInt.put(bytes3);
                putInt.putInt(bytes4.length);
                putInt.put(bytes4);
            }
            j.d(putInt, "apply(...)");
            return putInt;
        }
    }

    public VexFwkLanguageDirections(LinkedList<VexFwkLanguageDirection> linkedList) {
        j.e(linkedList, "elements");
        addAll(linkedList);
    }

    public static VexFwkLanguageDirections fromBuffer(ByteBuffer byteBuffer) {
        return Companion.fromBuffer(byteBuffer);
    }

    public static ByteBuffer toBuffer(VexFwkLanguageDirections vexFwkLanguageDirections) {
        return Companion.toBuffer(vexFwkLanguageDirections);
    }

    public /* bridge */ boolean contains(VexFwkLanguageDirection vexFwkLanguageDirection) {
        return super.contains(vexFwkLanguageDirection);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(VexFwkLanguageDirection vexFwkLanguageDirection) {
        return super.indexOf(vexFwkLanguageDirection);
    }

    public /* bridge */ int lastIndexOf(VexFwkLanguageDirection vexFwkLanguageDirection) {
        return super.lastIndexOf(vexFwkLanguageDirection);
    }

    public final /* bridge */ VexFwkLanguageDirection remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ VexFwkLanguageDirection removeAt(int i2) {
        return (VexFwkLanguageDirection) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof VexFwkLanguageDirection)) {
            return false;
        }
        return contains((VexFwkLanguageDirection) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof VexFwkLanguageDirection)) {
            return -1;
        }
        return indexOf((VexFwkLanguageDirection) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof VexFwkLanguageDirection)) {
            return -1;
        }
        return lastIndexOf((VexFwkLanguageDirection) obj);
    }

    public /* bridge */ boolean remove(VexFwkLanguageDirection vexFwkLanguageDirection) {
        return super.remove(vexFwkLanguageDirection);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof VexFwkLanguageDirection)) {
            return false;
        }
        return remove((VexFwkLanguageDirection) obj);
    }
}
