package com.samsung.android.gallery.app.controller;

import android.app.Activity;
import android.content.Context;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimpleEventContext implements EventContext {
    private WeakReference<Context> mAppContextRef;
    private Blackboard mBlackboard;
    private WeakReference<Context> mContextRef;

    public SimpleEventContext(Blackboard blackboard) {
        Activity readActivity = BlackboardUtils.readActivity(blackboard);
        this.mContextRef = new WeakReference<>(readActivity);
        this.mAppContextRef = new WeakReference<>(readActivity.getApplicationContext());
        this.mBlackboard = blackboard;
    }

    public Activity getActivity() {
        return (Activity) this.mContextRef.get();
    }

    public Context getApplicationContext() {
        return this.mAppContextRef.get();
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public Context getContext() {
        return this.mContextRef.get();
    }
}
