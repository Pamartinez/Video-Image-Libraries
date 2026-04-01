package com.samsung.android.gallery.module.search.languagepack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LanguagePackReceiver extends BroadcastReceiver {
    private final Runnable mCallback;
    private final IntentFilter mIntentFilter;

    public LanguagePackReceiver(Runnable runnable) {
        this.mCallback = runnable;
        IntentFilter intentFilter = new IntentFilter();
        this.mIntentFilter = intentFilter;
        intentFilter.addAction("com.samsung.android.settings.action.LANGUAGE_PACK_ADDED");
    }

    public IntentFilter getIntentFilter() {
        return this.mIntentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            Log.se("LanguagePackReceiver", "onReceive failed. intent is null.");
            return;
        }
        String action = intent.getAction();
        Log.s("LanguagePackReceiver", "onReceive action : " + action);
        if ("com.samsung.android.settings.action.LANGUAGE_PACK_ADDED".equals(action)) {
            NeuralTranslator.getInstance().clearValues();
            this.mCallback.run();
        }
    }
}
