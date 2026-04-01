package com.samsung.android.gallery.app.controller.story;

import M4.m;
import R6.c;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddToStoryCmd extends BaseCommand {
    private long mSelectedNum = 0;

    private void addToStory(MediaItem mediaItem, MediaItem[] mediaItemArr, Long[] lArr) {
        int length = mediaItemArr.length;
        ArrayList arrayList = new ArrayList(Arrays.asList(mediaItemArr));
        MediaItem mediaItem2 = mediaItem;
        Object[] addContentsToStory = DbCompat.storyApi().addContentsToStory(mediaItem2, arrayList, length, new m(mediaItem, 1), MediaItemStory.getStoryCoverId(mediaItem));
        if (((Boolean) addContentsToStory[0]).booleanValue()) {
            updateUserCuration(MediaItemStory.getStoryId(mediaItem2), arrayList);
            StoryUpdateNotifier.getInstance().notifyStory(getContext(), true);
        } else {
            Log.e(this.TAG, "addContentsToStory is failed");
        }
        int duplicatedCount = getDuplicatedCount((String) addContentsToStory[1], lArr);
        if (length > 0 && duplicatedCount > 0) {
            showToast(getDuplicatedString(length, duplicatedCount));
        }
    }

    private int getDuplicatedCount(String str, Long[] lArr) {
        Arrays.sort(lArr);
        int i2 = 0;
        for (String str2 : str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
            if (Arrays.binarySearch(lArr, Long.valueOf(UnsafeCast.toLong(str2))) >= 0) {
                i2++;
            }
        }
        return i2;
    }

    private String getDuplicatedString(int i2, int i7) {
        int i8;
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            int i10 = i2 - i7;
            if (i2 == 2 && i7 == 1) {
                return getContext().getString(R.string.a_item_added_to_story_a_item_was_in_there_already);
            }
            if (i10 == 1 && i7 > 1) {
                return getContext().getString(R.string.a_item_added_to_story_n_item_was_in_there_already, new Object[]{Integer.valueOf(i7)});
            }
            if (i10 >= 0 && i7 == 1) {
                return getContext().getString(R.string.n_item_added_to_story_a_item_was_in_there_already, new Object[]{Integer.valueOf(i10)});
            }
            if (i10 < 0 || i7 <= 1) {
                return "";
            }
            return getContext().getString(R.string.n_item_added_to_story_n_item_was_in_there_already, new Object[]{Integer.valueOf(i10), Integer.valueOf(i7)});
        }
        Context context = getContext();
        if (i7 > 1) {
            i8 = R.string.items_already_in_channel_not_added;
        } else {
            i8 = R.string.item_already_in_channel_not_added;
        }
        return context.getString(i8);
    }

    private static int getStoryContentsCount(MediaItem mediaItem) {
        return DbCompat.storyApi().getContentsCount(mediaItem.getAlbumID());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem mediaItem, Object[] objArr) {
        String str;
        int storyContentsCount = getStoryContentsCount(mediaItem);
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
        MediaItem[] mediaItemArr = objArr[1];
        this.mSelectedNum = (long) mediaItemArr.length;
        addToStory(mediaItem, mediaItemArr, objArr[2]);
    }

    private void updateUserCuration(int i2, ArrayList<FileItemInterface> arrayList) {
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            DbCompat.storyApi().updateUserCuration(i2, arrayList, 1);
            getBlackboard().post("command:///RefreshWithoutDelay", (Object) null);
        }
    }

    public Long getAnalyticsValue() {
        return Long.valueOf(this.mSelectedNum);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_ADD_TO_STORY.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        SimpleThreadPool.getInstance().execute(new c(this, objArr[0], objArr, 9));
    }
}
