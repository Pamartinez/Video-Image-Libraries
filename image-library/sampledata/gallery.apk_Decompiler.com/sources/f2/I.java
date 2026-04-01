package F2;

import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class I implements Iterable {
    public final String toString() {
        StringBuilder sb2 = new StringBuilder("[");
        boolean z = true;
        for (Object append : this) {
            if (!z) {
                sb2.append(ArcCommonLog.TAG_COMMA);
            }
            sb2.append(append);
            z = false;
        }
        sb2.append(']');
        return sb2.toString();
    }
}
