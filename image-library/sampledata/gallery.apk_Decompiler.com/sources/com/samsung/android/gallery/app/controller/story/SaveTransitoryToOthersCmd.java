package com.samsung.android.gallery.app.controller.story;

import Ob.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveTransitoryToOthersCmd extends BaseCommand {
    private MediaItem mItem;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveToOthers$0(MediaItem mediaItem) {
        try {
            getBlackboard().post("command:///RefreshWithoutDelay", (Object) null);
            int storyId = MediaItemStory.getStoryId(mediaItem);
            DbCompat.storyApi().updateStoryType(new Integer[]{Integer.valueOf(storyId)}, MediaItemStory.getStoryType(mediaItem) - 30);
            StoryUpdateNotifier.getInstance().notifyStory(AppResources.getAppContext(), true);
            Log.d(this.TAG, "done", Integer.valueOf(storyId));
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "fail e=", e.getMessage());
        }
    }

    private void saveToOthers(MediaItem mediaItem) {
        if (mediaItem != null) {
            this.mItem = mediaItem;
            SimpleThreadPool.getInstance().execute(new a(27, this, mediaItem));
        }
    }

    public String getAnalyticsDetail() {
        MediaItem mediaItem = this.mItem;
        if (mediaItem != null) {
            return String.valueOf(MediaItemStory.getStorySaType(mediaItem));
        }
        return null;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORIES_FAVORITE_ICON_SELECT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        saveToOthers(objArr[0]);
    }
}
