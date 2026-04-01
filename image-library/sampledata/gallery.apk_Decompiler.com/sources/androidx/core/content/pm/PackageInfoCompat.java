package androidx.core.content.pm;

import android.content.pm.PackageInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PackageInfoCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static long getLongVersionCode(PackageInfo packageInfo) {
            return packageInfo.getLongVersionCode();
        }
    }

    public static long getLongVersionCode(PackageInfo packageInfo) {
        return Api28Impl.getLongVersionCode(packageInfo);
    }
}
