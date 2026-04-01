package com.samsung.scsp.common;

import java.util.Set;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4215a;

    public /* synthetic */ f(int i2) {
        this.f4215a = i2;
    }

    public final void accept(Object obj, Object obj2) {
        PreferenceItem preferenceItem = (PreferenceItem) obj;
        switch (this.f4215a) {
            case 0:
                preferenceItem.preferences.sharedPreferences.edit().putBoolean(preferenceItem.name, ((Boolean) obj2).booleanValue()).apply();
                return;
            case 1:
                preferenceItem.preferences.sharedPreferences.edit().putFloat(preferenceItem.name, ((Float) obj2).floatValue()).apply();
                return;
            case 2:
                preferenceItem.preferences.sharedPreferences.edit().putInt(preferenceItem.name, ((Integer) obj2).intValue()).apply();
                return;
            case 3:
                preferenceItem.preferences.sharedPreferences.edit().putLong(preferenceItem.name, ((Long) obj2).longValue()).apply();
                return;
            case 4:
                preferenceItem.preferences.sharedPreferences.edit().putString(preferenceItem.name, (String) obj2).apply();
                return;
            default:
                preferenceItem.preferences.sharedPreferences.edit().putStringSet(preferenceItem.name, (Set) obj2).apply();
                return;
        }
    }
}
