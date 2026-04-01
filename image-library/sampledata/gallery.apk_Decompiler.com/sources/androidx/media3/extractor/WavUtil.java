package androidx.media3.extractor;

import androidx.media3.common.util.Util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WavUtil {
    public static int getPcmEncodingForType(int i2, int i7) {
        if (i2 != 1) {
            if (i2 != 3) {
                if (i2 != 65534) {
                    return 0;
                }
            } else if (i7 == 32) {
                return 4;
            } else {
                return 0;
            }
        }
        return Util.getPcmEncoding(i7);
    }
}
