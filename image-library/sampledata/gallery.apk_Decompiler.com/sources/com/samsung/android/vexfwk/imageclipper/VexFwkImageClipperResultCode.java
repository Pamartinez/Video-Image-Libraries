package com.samsung.android.vexfwk.imageclipper;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\b\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/vexfwk/imageclipper/VexFwkImageClipperResultCode;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "OK", "ERR_UNKNOWN", "ERR_INVALID_PARAM", "ERR_UNSUPPORTED", "ERR_NO_MEMORY", "ERR_BAD_STATE", "ERR_USER_CANCEL", "ERR_EXPIRED", "ERR_USER_PAUSE", "ERR_BUFFER_OVERFLOW", "ERR_BUFFER_UNDERFLOW", "ERR_NO_DISKSPACE", "ERR_COMPONENT_NOT_EXIST", "ERR_GLOBAL_DATA_NOT_EXIST", "ERR_NO_OBJECT", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum VexFwkImageClipperResultCode {
    OK(0),
    ERR_UNKNOWN(1),
    ERR_INVALID_PARAM(2),
    ERR_UNSUPPORTED(3),
    ERR_NO_MEMORY(4),
    ERR_BAD_STATE(5),
    ERR_USER_CANCEL(6),
    ERR_EXPIRED(7),
    ERR_USER_PAUSE(8),
    ERR_BUFFER_OVERFLOW(9),
    ERR_BUFFER_UNDERFLOW(10),
    ERR_NO_DISKSPACE(11),
    ERR_COMPONENT_NOT_EXIST(12),
    ERR_GLOBAL_DATA_NOT_EXIST(13),
    ERR_NO_OBJECT(14);
    
    public static final Companion Companion = null;
    private final int value;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/imageclipper/VexFwkImageClipperResultCode$Companion;", "", "<init>", "()V", "fromInt", "Lcom/samsung/android/vexfwk/imageclipper/VexFwkImageClipperResultCode;", "value", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final VexFwkImageClipperResultCode fromInt(int i2) {
            for (VexFwkImageClipperResultCode vexFwkImageClipperResultCode : VexFwkImageClipperResultCode.values()) {
                if (vexFwkImageClipperResultCode.getValue() == i2) {
                    return vexFwkImageClipperResultCode;
                }
            }
            throw new NoSuchElementException("Array contains no element matching the predicate.");
        }

        private Companion() {
        }
    }

    static {
        VexFwkImageClipperResultCode[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private VexFwkImageClipperResultCode(int i2) {
        this.value = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
