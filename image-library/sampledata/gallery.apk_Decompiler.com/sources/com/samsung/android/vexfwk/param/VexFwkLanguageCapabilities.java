package com.samsung.android.vexfwk.param;

import com.samsung.android.vexfwk.imagetranslation.VexFwkAvailableLanguage;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0006B\u0017\b\u0016\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkLanguageCapabilities;", "Ljava/util/LinkedList;", "Lcom/samsung/android/vexfwk/imagetranslation/VexFwkAvailableLanguage;", "elements", "<init>", "(Ljava/util/LinkedList;)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkLanguageCapabilities extends LinkedList<VexFwkAvailableLanguage> {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0017J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0017¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkLanguageCapabilities$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkLanguageCapabilities;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkLanguageCapabilities> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkLanguageCapabilities fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            LinkedList linkedList = new LinkedList();
            int i2 = byteBuffer.getInt();
            for (int i7 = 0; i7 < i2; i7++) {
                byte[] bArr = new byte[byteBuffer.getInt()];
                byteBuffer.get(bArr);
                Charset charset = StandardCharsets.UTF_8;
                j.d(charset, "UTF_8");
                String str = new String(bArr, charset);
                boolean z = true;
                if (byteBuffer.get() != 1) {
                    z = false;
                }
                linkedList.add(new VexFwkAvailableLanguage(str, z));
            }
            return new VexFwkLanguageCapabilities(linkedList);
        }

        public ByteBuffer toBuffer(VexFwkLanguageCapabilities vexFwkLanguageCapabilities) {
            j.e(vexFwkLanguageCapabilities, "value");
            ArrayList<Number> arrayList = new ArrayList<>(C1196n.w0(vexFwkLanguageCapabilities, 10));
            Iterator it = vexFwkLanguageCapabilities.iterator();
            while (it.hasNext()) {
                String lang = ((VexFwkAvailableLanguage) it.next()).getLang();
                Charset charset = StandardCharsets.UTF_8;
                j.d(charset, "UTF_8");
                byte[] bytes = lang.getBytes(charset);
                j.d(bytes, "getBytes(...)");
                arrayList.add(Integer.valueOf(bytes.length + 5));
            }
            int i2 = 0;
            for (Number intValue : arrayList) {
                i2 += intValue.intValue();
            }
            ByteBuffer putInt = allocateBuffer(4 + i2).order(ByteOrder.nativeOrder()).putInt(vexFwkLanguageCapabilities.size());
            Iterator it2 = vexFwkLanguageCapabilities.iterator();
            while (it2.hasNext()) {
                VexFwkAvailableLanguage vexFwkAvailableLanguage = (VexFwkAvailableLanguage) it2.next();
                String lang2 = vexFwkAvailableLanguage.getLang();
                Charset charset2 = StandardCharsets.UTF_8;
                j.d(charset2, "UTF_8");
                byte[] bytes2 = lang2.getBytes(charset2);
                j.d(bytes2, "getBytes(...)");
                putInt.putInt(bytes2.length);
                String lang3 = vexFwkAvailableLanguage.getLang();
                j.d(charset2, "UTF_8");
                byte[] bytes3 = lang3.getBytes(charset2);
                j.d(bytes3, "getBytes(...)");
                putInt.put(bytes3);
                putInt.put(vexFwkAvailableLanguage.getDownloadRequired() ? (byte) 1 : 0);
            }
            j.d(putInt, "apply(...)");
            return putInt;
        }
    }

    public VexFwkLanguageCapabilities(LinkedList<VexFwkAvailableLanguage> linkedList) {
        j.e(linkedList, "elements");
        addAll(linkedList);
    }

    public static VexFwkLanguageCapabilities fromBuffer(ByteBuffer byteBuffer) {
        return Companion.fromBuffer(byteBuffer);
    }

    public static ByteBuffer toBuffer(VexFwkLanguageCapabilities vexFwkLanguageCapabilities) {
        return Companion.toBuffer(vexFwkLanguageCapabilities);
    }

    public /* bridge */ boolean contains(VexFwkAvailableLanguage vexFwkAvailableLanguage) {
        return super.contains(vexFwkAvailableLanguage);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(VexFwkAvailableLanguage vexFwkAvailableLanguage) {
        return super.indexOf(vexFwkAvailableLanguage);
    }

    public /* bridge */ int lastIndexOf(VexFwkAvailableLanguage vexFwkAvailableLanguage) {
        return super.lastIndexOf(vexFwkAvailableLanguage);
    }

    public final /* bridge */ VexFwkAvailableLanguage remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ VexFwkAvailableLanguage removeAt(int i2) {
        return (VexFwkAvailableLanguage) remove(i2);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof VexFwkAvailableLanguage)) {
            return false;
        }
        return contains((VexFwkAvailableLanguage) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof VexFwkAvailableLanguage)) {
            return -1;
        }
        return indexOf((VexFwkAvailableLanguage) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof VexFwkAvailableLanguage)) {
            return -1;
        }
        return lastIndexOf((VexFwkAvailableLanguage) obj);
    }

    public /* bridge */ boolean remove(VexFwkAvailableLanguage vexFwkAvailableLanguage) {
        return super.remove(vexFwkAvailableLanguage);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (!(obj instanceof VexFwkAvailableLanguage)) {
            return false;
        }
        return remove((VexFwkAvailableLanguage) obj);
    }
}
