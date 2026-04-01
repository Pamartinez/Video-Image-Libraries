package com.samsung.android.vexfwk.param;

import c0.C0086a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.nio.ByteBuffer;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "CAMERA_PREVIEW", "STILL_CAPTURE", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum VexFwkDocumentDetectionType {
    CAMERA_PREVIEW(1),
    STILL_CAPTURE(2);
    
    private static final int BUFFER_SIZE = 4;
    public static final Companion Companion = null;
    private final int value;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseBuffer;", "Lcom/samsung/android/vexfwk/param/VexFwkDocumentDetectionType;", "<init>", "()V", "BUFFER_SIZE", "", "fromBuffer", "buffer", "Ljava/nio/ByteBuffer;", "toBuffer", "value", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseBuffer<VexFwkDocumentDetectionType> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }

        public VexFwkDocumentDetectionType fromBuffer(ByteBuffer byteBuffer) {
            Object obj;
            j.e(byteBuffer, "buffer");
            if (byteBuffer.capacity() == 4) {
                int i2 = byteBuffer.getInt();
                Iterator it = VexFwkDocumentDetectionType.getEntries().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((VexFwkDocumentDetectionType) obj).getValue() == i2) {
                        break;
                    }
                }
                VexFwkDocumentDetectionType vexFwkDocumentDetectionType = (VexFwkDocumentDetectionType) obj;
                if (vexFwkDocumentDetectionType != null) {
                    return vexFwkDocumentDetectionType;
                }
                throw new IllegalArgumentException(C0086a.i(i2, "Unknown value "));
            }
            throw new IllegalStateException(C0086a.i(byteBuffer.capacity(), "buffer capacity must be 4. buffer capacity is ").toString());
        }

        public ByteBuffer toBuffer(VexFwkDocumentDetectionType vexFwkDocumentDetectionType) {
            j.e(vexFwkDocumentDetectionType, "value");
            ByteBuffer putInt = allocateBuffer(4).putInt(vexFwkDocumentDetectionType.getValue());
            j.d(putInt, "putInt(...)");
            return putInt;
        }
    }

    static {
        VexFwkDocumentDetectionType[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private VexFwkDocumentDetectionType(int i2) {
        this.value = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
