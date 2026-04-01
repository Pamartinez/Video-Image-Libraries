package com.samsung.android.gallery.module.debugger;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final void accept(Object obj) {
        Log.e("LeakTracker", " activities=" + ((Blackboard) obj));
    }
}
