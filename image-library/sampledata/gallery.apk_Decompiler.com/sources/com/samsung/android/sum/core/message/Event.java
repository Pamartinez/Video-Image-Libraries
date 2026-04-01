package com.samsung.android.sum.core.message;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Event extends Message {
    private static final String TAG = Def.tagOf((Class<?>) Event.class);

    public Event(int i2) {
        super(1000, i2);
    }

    public static Event of(int i2) {
        return new Event(i2);
    }

    public Event(Message message) {
        super(message);
    }

    public static Event of(int i2, String str) {
        return (Event) new Event(i2).put("message", str);
    }

    public static Event of(int i2, Exception exc) {
        return (Event) new Event(i2).setException(exc);
    }

    public static Event of(int i2, String str, Object obj) {
        return (Event) new Event(i2).put(str, obj);
    }

    public static Event of(int i2, Map<String, Object> map) {
        return (Event) new Event(i2).put(map);
    }

    public static Event of(Response response) {
        Event event = new Event((Message) response);
        if (response.getBufferList() != null && !response.getBufferList().isEmpty()) {
            SLog.d(TAG, "response contains buffer-list, set it into event");
            event.put("buffer-list", response.getBufferList());
        }
        return event;
    }

    public static Event of(Message message) {
        return new Event(message);
    }
}
