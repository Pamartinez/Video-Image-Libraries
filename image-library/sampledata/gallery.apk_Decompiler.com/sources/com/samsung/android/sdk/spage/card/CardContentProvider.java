package com.samsung.android.sdk.spage.card;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.samsung.android.sdk.spage.card.event.Event;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CardContentProvider extends BroadcastReceiver {
    private static final String ACTION_AUTHORIZED = "com.samsung.android.app.spage.action.AUTHORIZED";
    private static final String ACTION_CARD_DISABLED = "com.samsung.android.app.spage.action.CARD_DISABLED";
    private static final String ACTION_CARD_ENABLED = "com.samsung.android.app.spage.action.CARD_ENABLED";
    private static final String ACTION_CARD_EVENT = "com.samsung.android.app.spage.action.CARD_EVENT";
    private static final String ACTION_CARD_INSTANT_UPDATE = "com.samsung.android.app.spage.action.CARD_INSTANT_UPDATE";
    private static final String ACTION_CARD_UPDATE = "com.samsung.android.app.spage.action.CARD_UPDATE";
    private static final String ACTION_MULTI_INSTANCE_PREFERENCE_UPDATE = "com.samsung.android.app.spage.action.MULTI_INSTANCE_PREFERENCE_UPDATE";
    private static final String EXTRA_CARD_ID = "IdNo";
    private static final String EXTRA_EVENT = "event";
    private static final String EXTRA_UPDATE_CODE = "updateCode";
    private static final String TAG = "CardContentProvider";

    public abstract void onDisabled(Context context, int[] iArr);

    public abstract void onEnabled(Context context, int[] iArr);

    public final void onReceive(Context context, Intent intent) {
        Event newEvent;
        String action = intent.getAction();
        if (ACTION_CARD_UPDATE.equals(action)) {
            onUpdate(context, CardContentManager.getInstance(), intent.getIntArrayExtra(EXTRA_CARD_ID));
        } else if (ACTION_CARD_ENABLED.equals(action)) {
            onEnabled(context, intent.getIntArrayExtra(EXTRA_CARD_ID));
        } else if (ACTION_CARD_DISABLED.equals(action)) {
            onDisabled(context, intent.getIntArrayExtra(EXTRA_CARD_ID));
        } else if (ACTION_CARD_EVENT.equals(action)) {
            Bundle extras = intent.getExtras();
            if (extras != null && (newEvent = Event.newEvent(extras)) != null) {
                onReceiveEvent(context, CardContentManager.getInstance(), intent.getIntExtra(EXTRA_CARD_ID, -1), newEvent);
            }
        } else if (ACTION_CARD_INSTANT_UPDATE.equals(action)) {
            Log.d(TAG, "onReceive Instant update");
            int intExtra = intent.getIntExtra(EXTRA_UPDATE_CODE, 0);
            if (intExtra != 0) {
                onInstantUpdate(context, CardContentManager.getInstance(), intent.getIntExtra(EXTRA_CARD_ID, -1), intExtra);
            } else {
                Log.e(TAG, "wrong update code - zero");
            }
        } else if (ACTION_MULTI_INSTANCE_PREFERENCE_UPDATE.equals(action)) {
            onPreferenceRequested(context, CardContentManager.getInstance(), intent.getIntExtra(EXTRA_CARD_ID, -1));
        }
    }

    public abstract void onUpdate(Context context, CardContentManager cardContentManager, int[] iArr);

    public void onPreferenceRequested(Context context, CardContentManager cardContentManager, int i2) {
    }

    public void onInstantUpdate(Context context, CardContentManager cardContentManager, int i2, int i7) {
    }

    public void onReceiveEvent(Context context, CardContentManager cardContentManager, int i2, Event event) {
    }
}
