package com.samsung.android.gallery.app.controller.story;

import Ad.C0720a;
import O3.o;
import R6.c;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.ondemand.StoryGenApi;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveFromStoryCmd extends BaseCommand {
    private boolean mIsFromConfirmDialog;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1(MediaItem mediaItem, MediaItem[] mediaItemArr) {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            getBlackboard().post("command:///RefreshWithoutDelay", (Object) null);
        }
        StoryHelper.removeFromStory(getContext(), mediaItem, mediaItemArr);
        if (SdkConfig.atLeast(SdkConfig.GED.V)) {
            StoryGenApi.create().setBlockListAtTrip((ArrayList<Integer>) null, (ArrayList) Arrays.stream(mediaItemArr).map(new o(21)).collect(Collectors.toCollection(new C0720a(1))));
        }
    }

    public Long getAnalyticsValue() {
        MediaItem[] selectedItems;
        EventContext handler = getHandler();
        if (handler == null || (selectedItems = handler.getSelectedItems()) == null) {
            return 0L;
        }
        return Long.valueOf((long) selectedItems.length);
    }

    public String getEventId() {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_LIST_REMOVE_DONE.toString();
        }
        return AnalyticsEventId.EVENT_MENU_STORY_REMOVE_FROM_STORY.toString();
    }

    public boolean isAnalyticsEnabled() {
        return !this.mIsFromConfirmDialog;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems.length >= 1) {
            SimpleThreadPool.getInstance().execute(new c(this, objArr[0], selectedItems, 10));
            getBlackboard().postEvent(EventMessage.obtain(1057));
            getBlackboard().postEvent(EventMessage.obtain(1003));
            if (objArr.length > 1 && objArr[1] != null) {
                getBlackboard().postEvent(objArr[1]);
            }
            if (objArr.length > 2) {
                this.mIsFromConfirmDialog = objArr[2].booleanValue();
            }
        }
    }
}
