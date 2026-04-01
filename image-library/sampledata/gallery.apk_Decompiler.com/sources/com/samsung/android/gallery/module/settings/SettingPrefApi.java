package com.samsung.android.gallery.module.settings;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SettingPrefApi {
    String getKey();

    boolean isEnabled() {
        return false;
    }

    boolean setAndNotifyIfChanged(Context context, boolean z);

    boolean support(Context context) {
        return true;
    }

    void restoreChanged() {
    }

    void setEnabled(boolean z) {
    }

    void notifyChanged(Context context, boolean z) {
    }
}
