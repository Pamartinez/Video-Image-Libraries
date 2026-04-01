package androidx.media3.common;

import androidx.media3.common.util.Assertions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FrameInfo {
    public final Format format;
    public final long offsetToAddUs;

    public FrameInfo(Format format2, long j2) {
        boolean z;
        boolean z3;
        boolean z7 = false;
        if (format2.colorInfo != null) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "format colorInfo must be set");
        if (format2.width > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        Assertions.checkArgument(z3, "format width must be positive, but is: " + format2.width);
        Assertions.checkArgument(format2.height > 0 ? true : z7, "format height must be positive, but is: " + format2.height);
        this.format = format2;
        this.offsetToAddUs = j2;
    }
}
