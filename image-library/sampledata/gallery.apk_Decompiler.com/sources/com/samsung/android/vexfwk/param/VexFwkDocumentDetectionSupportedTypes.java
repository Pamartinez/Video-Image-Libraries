package com.samsung.android.vexfwk.param;

import com.samsung.android.vexfwk.extensions.CollectionsKt;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ld.C1145a;
import ne.C1194l;
import ne.C1196n;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionSupportedTypes;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocumentDetectionSupportedTypes {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionSupportedTypes$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType;", "<init>", "()V", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<List<? extends VexFwkDocumentDetectionType>> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final VexFwkDocumentDetectionType fromBuffer$lambda$1(IntBuffer intBuffer, int i2) {
            int i7 = intBuffer.get();
            for (VexFwkDocumentDetectionType vexFwkDocumentDetectionType : VexFwkDocumentDetectionType.getEntries()) {
                if (vexFwkDocumentDetectionType.getValue() == i7) {
                    return vexFwkDocumentDetectionType;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }

        private Companion() {
        }

        public List<VexFwkDocumentDetectionType> fromBuffer(ByteBuffer byteBuffer) {
            j.e(byteBuffer, "buffer");
            IntBuffer asIntBuffer = byteBuffer.asIntBuffer();
            return CollectionsKt.repeatMap(asIntBuffer.capacity(), new C1145a(asIntBuffer, 1));
        }

        public ByteBuffer toBuffer(List<? extends VexFwkDocumentDetectionType> list) {
            j.e(list, "value");
            ByteBuffer allocateBuffer = allocateBuffer(list.size() * 4);
            IntBuffer asIntBuffer = allocateBuffer.asIntBuffer();
            Iterable<VexFwkDocumentDetectionType> iterable = list;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            for (VexFwkDocumentDetectionType value : iterable) {
                arrayList.add(Integer.valueOf(value.getValue()));
            }
            asIntBuffer.put(C1194l.j1(arrayList));
            return allocateBuffer;
        }
    }
}
