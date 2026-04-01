package com.samsung.android.gallery.app.ui.list.stories.highlight;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SubscriberListener {
    public final /* synthetic */ StoryToTimeline d;

    public /* synthetic */ a(StoryToTimeline storyToTimeline) {
        this.d = storyToTimeline;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.onFragmentLifecycleResumed(obj, bundle);
    }
}
