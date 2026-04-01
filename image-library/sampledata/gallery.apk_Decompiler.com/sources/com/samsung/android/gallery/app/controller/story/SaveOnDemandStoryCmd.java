package com.samsung.android.gallery.app.controller.story;

import A4.Q;
import R6.c;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveOnDemandStoryCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(EventContext eventContext, CharSequence charSequence, EventContext eventContext2, ArrayList arrayList) {
        onDataComplete(arrayList, eventContext.getHeaderItem(), charSequence);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSaveClick$1(MediaItem mediaItem, CharSequence charSequence) {
        try {
            StoryType storyType = StoryType.MANUAL;
            MediaItemStory.setStoryType(mediaItem, storyType.getValue());
            int storyId = MediaItemStory.getStoryId(mediaItem);
            if (mediaItem.getTitle() != null && !mediaItem.getTitle().contentEquals(charSequence)) {
                DbCompat.storyApi().renameStory(charSequence.toString(), mediaItem.getTitle(), storyId);
            }
            DbCompat.storyApi().updateStoryType(new Integer[]{Integer.valueOf(storyId)}, storyType.getValue());
            StoryUpdateNotifier.getInstance().notifyStory(AppResources.getAppContext(), true);
            getBlackboard().postEvent(EventMessage.obtain(1141));
            Log.d(this.TAG, "handleAction : SAVE_STORY", Integer.valueOf(storyId));
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "fail to SAVE_STORY", e.getMessage());
        }
        consumeExecuteListener((Object) null);
    }

    private void onDataComplete(ArrayList<Object> arrayList, MediaItem mediaItem, CharSequence charSequence) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (((Integer) arrayList.get(0)).intValue() == 1) {
                onSaveClick(mediaItem, charSequence);
            } else {
                onDiscardClick();
            }
        }
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50 && SdkConfig.atLeast(SdkConfig.GED.T)) {
            getBlackboard().postEvent(EventMessage.obtain(1093, Boolean.TRUE));
        }
    }

    private void onDiscardClick() {
        consumeExecuteListener((Object) null);
        postAnalyticsLog(AnalyticsEventId.EVENT_ONDEMAND_STORY_HIGHLIGHT_LAST_PAGE_DIALOG_DISCARD);
    }

    private void onSaveClick(MediaItem mediaItem, CharSequence charSequence) {
        SimpleThreadPool.getInstance().execute(new c(this, mediaItem, charSequence, 13));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Boolean bool = objArr[0];
        boolean booleanValue = bool.booleanValue();
        Log.d(this.TAG, "execute save on demand story", bool, eventContext.getLocationKey());
        CharSequence charSequence = objArr[1];
        if (booleanValue) {
            Context context = getContext();
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("description", context.getString(R.string.save_your_story_or_discard_it)).appendArg("option1", context.getString(R.string.save)).appendArg("option2", context.getString(R.string.crop_back_key_confirm_dialog_discard)).appendArg("hideCancel", true).build()).setOnDataCompleteListener(new Q((Object) this, (Object) eventContext, (Object) charSequence, 11)).start();
            getBlackboard().postEvent(EventMessage.obtain(1093, Boolean.FALSE));
            return;
        }
        onSaveClick(eventContext.getCurrentItem(), charSequence);
        postAnalyticsLog(AnalyticsEventId.EVENT_ONDEMAND_STORY_HIGHLIGHT_LAST_PAGE_SAVE_STORY);
    }
}
