package com.samsung.android.gallery.app.ui.list.stories.category;

import android.os.Bundle;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.AppResources;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements SubscriberListener {
    public final void onNotify(Object obj, Bundle bundle) {
        StoryUpdateNotifier.getInstance().notifyStory(AppResources.getAppContext(), true);
    }
}
