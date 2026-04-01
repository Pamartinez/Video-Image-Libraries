package androidx.appcompat.app;

import androidx.core.os.LocaleListCompat;
import java.util.LinkedHashSet;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class LocaleOverlayHelper {
    private static LocaleListCompat combineLocales(LocaleListCompat localeListCompat, LocaleListCompat localeListCompat2) {
        Locale locale;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i2 = 0;
        while (true) {
            if (i2 >= localeListCompat2.size() + localeListCompat.size()) {
                return LocaleListCompat.create((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]));
            }
            if (i2 < localeListCompat.size()) {
                locale = localeListCompat.get(i2);
            } else {
                locale = localeListCompat2.get(i2 - localeListCompat.size());
            }
            if (locale != null) {
                linkedHashSet.add(locale);
            }
            i2++;
        }
    }

    public static LocaleListCompat combineLocalesIfOverlayExists(LocaleListCompat localeListCompat, LocaleListCompat localeListCompat2) {
        if (localeListCompat == null || localeListCompat.isEmpty()) {
            return LocaleListCompat.getEmptyLocaleList();
        }
        return combineLocales(localeListCompat, localeListCompat2);
    }
}
