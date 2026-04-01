package com.samsung.android.vexfwk.param;

import com.samsung.android.vexfwk.imagetranslation.VexFwkLanguagePackInfo;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0006B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkRequiredLanguages;", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/imagetranslation/VexFwkLanguagePackInfo;", "elements", "<init>", "(Ljava/util/LinkedList;)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkRequiredLanguages extends LinkedList<VexFwkLanguagePackInfo> {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0017¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkRequiredLanguages$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkRequiredLanguages;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkRequiredLanguages> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkRequiredLanguages fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            LinkedList linkedList = new LinkedList();
            int i2 = byteBuffer.getInt();
            for (int i7 = 0; i7 < i2; i7++) {
                byte[] bArr = new byte[byteBuffer.getInt()];
                byteBuffer.get(bArr);
                Charset charset = StandardCharsets.UTF_8;
                j.d(charset, "UTF_8");
                linkedList.add(new VexFwkLanguagePackInfo(new String(bArr, charset)));
            }
            return new VexFwkRequiredLanguages(linkedList);
        }

        public ByteBuffer toBuffer(VexFwkRequiredLanguages vexFwkRequiredLanguages) {
            j.e(vexFwkRequiredLanguages, "value");
            Iterator it = vexFwkRequiredLanguages.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                String languagePackCode = ((VexFwkLanguagePackInfo) it.next()).getLanguagePackCode();
                Charset charset = StandardCharsets.UTF_8;
                j.d(charset, "UTF_8");
                byte[] bytes = languagePackCode.getBytes(charset);
                j.d(bytes, "getBytes(...)");
                i2 += bytes.length + 4;
            }
            ByteBuffer putInt = allocateBuffer(4 + i2).order(ByteOrder.nativeOrder()).putInt(vexFwkRequiredLanguages.size());
            Iterator it2 = vexFwkRequiredLanguages.iterator();
            while (it2.hasNext()) {
                String languagePackCode2 = ((VexFwkLanguagePackInfo) it2.next()).getLanguagePackCode();
                Charset charset2 = StandardCharsets.UTF_8;
                j.d(charset2, "UTF_8");
                byte[] bytes2 = languagePackCode2.getBytes(charset2);
                j.d(bytes2, "getBytes(...)");
                putInt.putInt(bytes2.length);
                putInt.put(bytes2);
            }
            j.d(putInt, "apply(...)");
            return putInt;
        }
    }

    public VexFwkRequiredLanguages(LinkedList<VexFwkLanguagePackInfo> linkedList) {
        j.e(linkedList, "elements");
        addAll(linkedList);
    }

    public static VexFwkRequiredLanguages fromBuffer(ByteBuffer byteBuffer) {
        return Companion.fromBuffer(byteBuffer);
    }

    public static ByteBuffer toBuffer(VexFwkRequiredLanguages vexFwkRequiredLanguages) {
        return Companion.toBuffer(vexFwkRequiredLanguages);
    }

    public /* bridge */ boolean contains(VexFwkLanguagePackInfo vexFwkLanguagePackInfo) {
        return super.contains(vexFwkLanguagePackInfo);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(VexFwkLanguagePackInfo vexFwkLanguagePackInfo) {
        return super.indexOf(vexFwkLanguagePackInfo);
    }

    public /* bridge */ int lastIndexOf(VexFwkLanguagePackInfo vexFwkLanguagePackInfo) {
        return super.lastIndexOf(vexFwkLanguagePackInfo);
    }

    public final /* bridge */ VexFwkLanguagePackInfo remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ VexFwkLanguagePackInfo removeAt(int i2) {
        return (VexFwkLanguagePackInfo) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof VexFwkLanguagePackInfo)) {
            return false;
        }
        return contains((VexFwkLanguagePackInfo) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof VexFwkLanguagePackInfo)) {
            return -1;
        }
        return indexOf((VexFwkLanguagePackInfo) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof VexFwkLanguagePackInfo)) {
            return -1;
        }
        return lastIndexOf((VexFwkLanguagePackInfo) obj);
    }

    public /* bridge */ boolean remove(VexFwkLanguagePackInfo vexFwkLanguagePackInfo) {
        return super.remove(vexFwkLanguagePackInfo);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof VexFwkLanguagePackInfo)) {
            return false;
        }
        return remove((VexFwkLanguagePackInfo) obj);
    }
}
