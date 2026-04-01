package androidx.media3.common;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaLibraryInfo {
    private static final HashSet<String> registeredModules = new HashSet<>();
    private static String registeredModulesString = "media3.common";

    public static synchronized void registerModule(String str) {
        synchronized (MediaLibraryInfo.class) {
            if (registeredModules.add(str)) {
                registeredModulesString += ArcCommonLog.TAG_COMMA + str;
            }
        }
    }

    public static synchronized String registeredModules() {
        String str;
        synchronized (MediaLibraryInfo.class) {
            str = registeredModulesString;
        }
        return str;
    }
}
