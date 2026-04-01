package com.samsung.scsp.common;

import android.content.SharedPreferences;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Preferences {
    final SharedPreferences sharedPreferences;

    public Preferences(String str) {
        this.sharedPreferences = ContextFactory.getBaseContext().getSharedPreferences(str, 0);
    }

    public void clear() {
        this.sharedPreferences.edit().clear().apply();
    }
}
