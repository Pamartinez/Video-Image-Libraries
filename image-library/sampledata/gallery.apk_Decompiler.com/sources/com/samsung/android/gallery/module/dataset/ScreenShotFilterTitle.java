package com.samsung.android.gallery.module.dataset;

import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenShotFilterTitle {
    private static final ScreenShotFilterTitle sInstance = new ScreenShotFilterTitle();
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> translation = new ConcurrentHashMap<>();

    private String getDefaultTitle(String str) {
        if (str == null || !str.startsWith("cap_")) {
            return str;
        }
        return StringCompat.capitalize(str.substring(4).replaceAll("_", " "));
    }

    public static ScreenShotFilterTitle getInstance() {
        return sInstance;
    }

    private ArrayList<String> getTranslatedStrings(ArrayList<String> arrayList) {
        return TranslationManager.getInstance().getTranslatedStringMultiple("Documents", arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ConcurrentHashMap lambda$loadTitle$0(String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ArrayList<String> keySet = ScreenShotFilterType.keySet();
        ArrayList<String> translatedStrings = getTranslatedStrings(keySet);
        for (int i2 = 0; i2 < keySet.size(); i2++) {
            String str2 = keySet.get(i2);
            if (translatedStrings.isEmpty()) {
                concurrentHashMap.put(str2, str2);
            } else {
                concurrentHashMap.put(str2, getDefaultTitle(translatedStrings.get(i2)));
            }
        }
        return concurrentHashMap;
    }

    private String loadTitle(String str, String str2) {
        String str3 = (String) this.translation.computeIfAbsent(str, new C0608m(10, this)).getOrDefault(str2, (Object) null);
        if (str3 != null) {
            return str3;
        }
        return getDefaultTitle(str2);
    }

    public String getTitle(String str) {
        return loadTitle(Locale.getDefault().getLanguage(), str);
    }
}
