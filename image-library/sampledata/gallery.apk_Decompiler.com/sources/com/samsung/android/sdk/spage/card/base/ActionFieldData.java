package com.samsung.android.sdk.spage.card.base;

import android.content.Intent;
import com.samsung.android.sdk.spage.card.ConnectivityData;
import com.samsung.android.sdk.spage.card.ShareData;
import com.samsung.android.sdk.spage.card.base.ActionFieldData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ActionFieldData<T extends ActionFieldData> extends JsonFieldData<T> {
    public static final String INTENT_TYPE_ACTIVITY = "ACTIVITY";
    public static final String INTENT_TYPE_BROADCAST = "BROADCAST";
    private static final String KEY_CONNECTIVITY_DATA = "connectivityData";
    private static final String KEY_EVENT = "event";
    private static final String KEY_INTENT = "intent";
    private static final String KEY_INTENT_TYPE = "intentType";
    private static final String KEY_SHARE_DATA = "shareData";

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface Type {
    }

    public T setConnectivityData(ConnectivityData connectivityData) {
        remove(KEY_CONNECTIVITY_DATA);
        return (ActionFieldData) put(KEY_CONNECTIVITY_DATA, connectivityData.getData());
    }

    public T setEvent(String str) {
        remove(KEY_INTENT);
        remove(KEY_INTENT_TYPE);
        return (ActionFieldData) put(KEY_EVENT, str);
    }

    public T setIntent(Intent intent) {
        if (intent != null) {
            remove(KEY_EVENT);
            return (ActionFieldData) put(KEY_INTENT, intent.toUri(1));
        }
        throw new IllegalArgumentException("Intent is null");
    }

    public T setShareData(ShareData shareData) {
        remove(KEY_SHARE_DATA);
        return (ActionFieldData) put(KEY_SHARE_DATA, shareData.getData());
    }

    public T setIntent(Intent intent, String str) {
        if (intent != null) {
            remove(KEY_EVENT);
            put(KEY_INTENT_TYPE, str);
            return (ActionFieldData) put(KEY_INTENT, intent.toUri(1));
        }
        throw new IllegalArgumentException("Intent is null");
    }
}
