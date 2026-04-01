package androidx.media3.exoplayer.drm;

import android.util.Pair;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WidevineUtil {
    private static long getDurationRemainingSec(Map<String, String> map, String str) {
        if (map == null) {
            return -9223372036854775807L;
        }
        try {
            String str2 = map.get(str);
            if (str2 != null) {
                return Long.parseLong(str2);
            }
            return -9223372036854775807L;
        } catch (NumberFormatException unused) {
            return -9223372036854775807L;
        }
    }

    public static Pair<Long, Long> getLicenseDurationRemainingSec(DrmSession drmSession) {
        Map<String, String> queryKeyStatus = drmSession.queryKeyStatus();
        if (queryKeyStatus == null) {
            return null;
        }
        return new Pair<>(Long.valueOf(getDurationRemainingSec(queryKeyStatus, "LicenseDurationRemaining")), Long.valueOf(getDurationRemainingSec(queryKeyStatus, "PlaybackDurationRemaining")));
    }
}
