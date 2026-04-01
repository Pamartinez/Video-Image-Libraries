package com.samsung.android.vexfwk.param;

import c0.C0086a;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "ORIENTATION_ROTATE_0", "ORIENTATION_ROTATE_90", "ORIENTATION_ROTATE_180", "ORIENTATION_ROTATE_270", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum VexFwkOrientation {
    ORIENTATION_ROTATE_0(0),
    ORIENTATION_ROTATE_90(90),
    ORIENTATION_ROTATE_180(MOCRLang.KHMER),
    ORIENTATION_ROTATE_270(270);
    
    public static final Companion Companion = null;
    private final int value;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkOrientation$Companion;", "", "<init>", "()V", "from", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "value", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final VexFwkOrientation from(int i2) {
            VexFwkOrientation vexFwkOrientation;
            VexFwkOrientation[] values = VexFwkOrientation.values();
            int length = values.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    vexFwkOrientation = null;
                    break;
                }
                vexFwkOrientation = values[i7];
                if (vexFwkOrientation.getValue() == i2) {
                    break;
                }
                i7++;
            }
            if (vexFwkOrientation != null) {
                return vexFwkOrientation;
            }
            throw new IllegalArgumentException(C0086a.i(i2, "Unknown orientation "));
        }

        private Companion() {
        }
    }

    static {
        VexFwkOrientation[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private VexFwkOrientation(int i2) {
        this.value = i2;
    }

    public static final VexFwkOrientation from(int i2) {
        return Companion.from(i2);
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
