package androidx.core.os;

import android.content.res.Configuration;
import android.os.LocaleList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ConfigurationCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static LocaleList getLocales(Configuration configuration) {
            return configuration.getLocales();
        }
    }

    public static LocaleListCompat getLocales(Configuration configuration) {
        return LocaleListCompat.wrap(Api24Impl.getLocales(configuration));
    }
}
