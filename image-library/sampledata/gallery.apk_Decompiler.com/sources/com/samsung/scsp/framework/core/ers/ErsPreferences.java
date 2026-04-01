package com.samsung.scsp.framework.core.ers;

import com.samsung.scsp.common.PreferenceItem;
import com.samsung.scsp.common.Preferences;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ErsPreferences extends Preferences {
    private static final String name = "scsp.ers.preferences";
    public final PreferenceItem<String> defaultUrl;
    public final PreferenceItem<Long> expiredTime;
    public final PreferenceItem<String> playUrl;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final ErsPreferences preferences = new ErsPreferences();

        private LazyHolder() {
        }
    }

    public static ErsPreferences get() {
        return LazyHolder.preferences;
    }

    private ErsPreferences() {
        super(name);
        this.defaultUrl = new PreferenceItem<>(this, "defaultUrl", "https://api.samsungcloud.com");
        this.playUrl = new PreferenceItem<>(this, "playUrl", "https://play.samsungcloud.com");
        this.expiredTime = new PreferenceItem<>(this, "expiredTime", 7200000L);
    }
}
