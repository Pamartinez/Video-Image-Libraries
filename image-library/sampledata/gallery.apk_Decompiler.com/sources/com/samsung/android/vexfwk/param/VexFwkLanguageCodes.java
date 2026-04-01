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

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0006B\u0017\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkLanguageCodes;", "Ljava/util/LinkedList;", "", "elements", "<init>", "(Ljava/util/LinkedList;)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkLanguageCodes extends LinkedList<String> {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0017¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkLanguageCodes$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageCodes;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkLanguageCodes> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkLanguageCodes fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            LinkedList linkedList = new LinkedList();
            int i2 = byteBuffer.getInt();
            for (int i7 = 0; i7 < i2; i7++) {
                byte[] bArr = new byte[byteBuffer.getInt()];
                byteBuffer.get(bArr);
                Charset charset = StandardCharsets.UTF_8;
                j.d(charset, "UTF_8");
                linkedList.add(new String(bArr, charset));
            }
            return new VexFwkLanguageCodes(linkedList);
        }

        public ByteBuffer toBuffer(VexFwkLanguageCodes vexFwkLanguageCodes) {
            j.e(vexFwkLanguageCodes, "value");
            Iterator it = vexFwkLanguageCodes.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                Charset charset = StandardCharsets.UTF_8;
                j.d(charset, "UTF_8");
                byte[] bytes = ((String) it.next()).getBytes(charset);
                j.d(bytes, "getBytes(...)");
                i2 += bytes.length + 4;
            }
            ByteBuffer putInt = allocateBuffer(4 + i2).order(ByteOrder.nativeOrder()).putInt(vexFwkLanguageCodes.size());
            Iterator it2 = vexFwkLanguageCodes.iterator();
            while (it2.hasNext()) {
                Charset charset2 = StandardCharsets.UTF_8;
                j.d(charset2, "UTF_8");
                byte[] bytes2 = ((String) it2.next()).getBytes(charset2);
                j.d(bytes2, "getBytes(...)");
                putInt.putInt(bytes2.length);
                putInt.put(bytes2);
            }
            j.d(putInt, "apply(...)");
            return putInt;
        }
    }

    public VexFwkLanguageCodes(LinkedList<String> linkedList) {
        j.e(linkedList, "elements");
        addAll(linkedList);
    }

    public static VexFwkLanguageCodes fromBuffer(ByteBuffer byteBuffer) {
        return Companion.fromBuffer(byteBuffer);
    }

    public static ByteBuffer toBuffer(VexFwkLanguageCodes vexFwkLanguageCodes) {
        return Companion.toBuffer(vexFwkLanguageCodes);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        return contains((String) obj);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof String)) {
            return -1;
        }
        return indexOf((String) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof String)) {
            return -1;
        }
        return lastIndexOf((String) obj);
    }

    public final /* bridge */ String remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ String removeAt(int i2) {
        return (String) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public /* bridge */ boolean contains(String str) {
        return super.contains(str);
    }

    public /* bridge */ int indexOf(String str) {
        return super.indexOf(str);
    }

    public /* bridge */ int lastIndexOf(String str) {
        return super.lastIndexOf(str);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        return remove((String) obj);
    }

    public /* bridge */ boolean remove(String str) {
        return super.remove(str);
    }
}
