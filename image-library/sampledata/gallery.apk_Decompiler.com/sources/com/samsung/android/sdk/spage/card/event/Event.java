package com.samsung.android.sdk.spage.card.event;

import android.os.Bundle;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Event {
    public static final String DEFAULT_EVENT_TYPE = "default";
    private static final String EXTRA_EVENT = "event";
    private static final String EXTRA_EVENT_TYPE = "eventType";
    private String mEventName;
    private String mEventType;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface EventName {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface EventType {
    }

    public Event(String str, Bundle bundle) {
        initialize0(str, bundle);
    }

    private void initialize0(String str, Bundle bundle) {
        this.mEventType = str;
        this.mEventName = bundle.getString(EXTRA_EVENT);
        initialize(bundle);
    }

    public static Event newEvent(Bundle bundle) {
        String string = bundle.getString(EXTRA_EVENT_TYPE);
        if (TextUtils.isEmpty(string)) {
            string = DEFAULT_EVENT_TYPE;
        }
        string.getClass();
        char c5 = 65535;
        switch (string.hashCode()) {
            case 366526597:
                if (string.equals(SearchTextEvent.TYPE)) {
                    c5 = 0;
                    break;
                }
                break;
            case 530697857:
                if (string.equals(ItemSelectionEvent.TYPE)) {
                    c5 = 1;
                    break;
                }
                break;
            case 1544803905:
                if (string.equals(DEFAULT_EVENT_TYPE)) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return new SearchTextEvent(string, bundle);
            case 1:
                return new ItemSelectionEvent(string, bundle);
            case 2:
                return new Event(string, bundle);
            default:
                return null;
        }
    }

    public String getEventName() {
        return this.mEventName;
    }

    public String getEventType() {
        return this.mEventType;
    }

    public void initialize(Bundle bundle) {
    }
}
