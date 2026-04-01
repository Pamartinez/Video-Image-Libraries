package com.samsung.android.gallery.app.controller.story;

import A6.a;
import O3.y;
import R6.c;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddContentsToStoryCmd extends BaseCommand {
    private void addContentsToStoryWithPicker(EventContext eventContext, int i2) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/pick/Item").appendArg("is-pick-for-story-contents", true).appendArg("has_target_cluster", true).appendArg(IParameterKey.DATE_TAKEN, getLastItemDateTaken(eventContext.getHeaderItem())).appendArg("pick-max-item", getMaxPickItemCount(i2)).build()).setOnDataCompleteListener(new y(28, this)).start();
    }

    /* access modifiers changed from: private */
    public void addToStory(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            try {
                SimpleThreadPool.getInstance().execute(new c(eventContext.getHeaderItem(), eventContext, (MediaItem[]) arrayList.get(0), 8));
            } catch (ClassCastException e) {
                String str = this.TAG;
                Log.e(str, "fail to parsing item=" + e.getMessage());
            }
        }
    }

    private long getLastItemDateTaken(MediaItem mediaItem) {
        return MediaItemStory.getStoryTimeDurationArray(mediaItem)[1];
    }

    private int getMaxPickItemCount(int i2) {
        return PickerUtil.getStoryMaxCount() - i2;
    }

    private static int getStoryContentsCount(MediaItem mediaItem) {
        return DbCompat.storyApi().getContentsCount(mediaItem.getAlbumID());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addToStory$2(MediaItem mediaItem, EventContext eventContext, MediaItem[] mediaItemArr) {
        EventContext eventContext2 = eventContext;
        ThreadUtil.postOnUiThread(new a((Object) eventContext2, (Object) mediaItem, (Object) mediaItemArr, (Object) DbCompat.storyApi().getStoryAlbumContentsIds(MediaItemStory.getStoryId(mediaItem)), 21));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(EventContext eventContext) {
        String str;
        MediaItem headerItem = eventContext.getHeaderItem();
        if (headerItem == null) {
            Log.e(this.TAG, "fail due to no header");
            return;
        }
        int storyContentsCount = getStoryContentsCount(headerItem);
        int storyMaxCount = PickerUtil.getStoryMaxCount();
        if (storyContentsCount >= storyMaxCount) {
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                str = getContext().getResources().getQuantityString(R.plurals.cant_include_more_than_n_item_in_one_story, storyMaxCount, new Object[]{Integer.valueOf(storyMaxCount)});
            } else {
                str = getContext().getString(R.string.unable_to_add_item_to_story, new Object[]{Integer.valueOf(storyMaxCount)});
            }
            showToast(str, 1);
            return;
        }
        addContentsToStoryWithPicker(eventContext, storyContentsCount);
    }

    public String getEventId() {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_LIST_ADD_TO_STORY.toString();
        }
        return AnalyticsEventId.EVENT_MENU_STORY_ADD.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        SimpleThreadPool.getInstance().execute(new Ob.a(20, this, eventContext));
    }
}
