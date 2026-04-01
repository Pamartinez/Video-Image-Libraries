package com.samsung.android.vexfwk.image;

import android.hardware.HardwareBuffer;
import c0.C0086a;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/image/VexFwkImageFormat;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "UNKNOWN", "NV12", "NV21", "RGB_888", "RGBA_8888", "YUV_420_SP_VENUS", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum VexFwkImageFormat {
    UNKNOWN(0),
    NV12(1),
    NV21(2),
    RGB_888(3),
    RGBA_8888(4),
    YUV_420_SP_VENUS(5);
    
    public static final Companion Companion = null;
    private static final int HARDWARE_BUFFER_FORMAT_YCBCR_420_SP_VENUS = 2141391876;
    private final int value;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0007J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/image/VexFwkImageFormat$Companion;", "", "<init>", "()V", "HARDWARE_BUFFER_FORMAT_YCBCR_420_SP_VENUS", "", "from", "Lcom/samsung/android/vexfwk/image/VexFwkImageFormat;", "value", "hardwareBuffer", "Landroid/hardware/HardwareBuffer;", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final VexFwkImageFormat from(int i2) {
            VexFwkImageFormat vexFwkImageFormat;
            VexFwkImageFormat[] values = VexFwkImageFormat.values();
            int length = values.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    vexFwkImageFormat = null;
                    break;
                }
                vexFwkImageFormat = values[i7];
                if (vexFwkImageFormat.getValue() == i2) {
                    break;
                }
                i7++;
            }
            if (vexFwkImageFormat != null) {
                return vexFwkImageFormat;
            }
            throw new IllegalArgumentException(C0086a.i(i2, "Unknown image format "));
        }

        private Companion() {
        }

        public final VexFwkImageFormat from(HardwareBuffer hardwareBuffer) {
            j.e(hardwareBuffer, "hardwareBuffer");
            int format = hardwareBuffer.getFormat();
            if (format == 1) {
                return VexFwkImageFormat.RGBA_8888;
            }
            if (format == 35) {
                return VexFwkImageFormat.NV12;
            }
            if (format == VexFwkImageFormat.HARDWARE_BUFFER_FORMAT_YCBCR_420_SP_VENUS) {
                return VexFwkImageFormat.YUV_420_SP_VENUS;
            }
            throw new IllegalArgumentException(C0086a.i(hardwareBuffer.getFormat(), "Unsupported hardware buffer format "));
        }
    }

    static {
        VexFwkImageFormat[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private VexFwkImageFormat(int i2) {
        this.value = i2;
    }

    public static final VexFwkImageFormat from(int i2) {
        return Companion.from(i2);
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }

    public static final VexFwkImageFormat from(HardwareBuffer hardwareBuffer) {
        return Companion.from(hardwareBuffer);
    }
}
