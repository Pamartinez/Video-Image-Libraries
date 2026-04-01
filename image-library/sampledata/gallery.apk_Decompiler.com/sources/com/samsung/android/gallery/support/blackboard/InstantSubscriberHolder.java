package com.samsung.android.gallery.support.blackboard;

import K5.a;
import android.util.Pair;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InstantSubscriberHolder {
    CopyOnWriteArrayList<Pair<String, InstantSubscriberListener>> mHolder = new CopyOnWriteArrayList<>();

    public final void clear(Blackboard blackboard) {
        if (this.mHolder.size() > 0) {
            this.mHolder.forEach(new a(2, blackboard));
            this.mHolder.clear();
        }
    }

    public final InstantSubscriberListener toInstant(String str, InstantSubscriberListener instantSubscriberListener) {
        this.mHolder.add(new Pair(str, instantSubscriberListener));
        return instantSubscriberListener;
    }
}
