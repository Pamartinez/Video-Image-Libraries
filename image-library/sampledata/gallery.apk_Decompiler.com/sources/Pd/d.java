package Pd;

import android.system.OsConstants;
import java.io.ByteArrayInputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends ByteArrayInputStream {
    public final long a(int i2, long j2) {
        if (i2 == OsConstants.SEEK_SET) {
            reset();
            skip(j2);
        } else if (i2 == OsConstants.SEEK_END) {
            reset();
            skip(((long) this.count) + j2);
        } else if (i2 == OsConstants.SEEK_CUR && j2 != 0) {
            reset();
            skip(j2 + ((long) this.pos));
        }
        return (long) this.pos;
    }
}
