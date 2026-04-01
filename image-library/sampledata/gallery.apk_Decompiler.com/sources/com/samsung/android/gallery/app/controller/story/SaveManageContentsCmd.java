package com.samsung.android.gallery.app.controller.story;

import A.a;
import B8.j;
import R6.c;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.StoryApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryUpdateNotifier;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveManageContentsCmd extends BaseCommand {
    private boolean isHeaderHidden(MediaItem mediaItem, MediaItem[] mediaItemArr) {
        if (mediaItem == null || !Arrays.stream(mediaItemArr).anyMatch(new j(mediaItem, 4))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isHeaderHidden$1(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2.getFileId() == mediaItem.getFileId()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(Object[] objArr, EventContext eventContext) {
        saveManageContents(eventContext, objArr[0], objArr[1]);
    }

    private void saveManageContents(EventContext eventContext, MediaItem[] mediaItemArr, MediaItem[] mediaItemArr2) {
        int i2;
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        StoryApi storyApi = DbCompat.storyApi();
        int i7 = 0;
        if (mediaItemArr == null || mediaItemArr.length <= 0) {
            i2 = 0;
            z = false;
        } else {
            storyApi.updateUserCuration(MediaItemStory.getStoryId(mediaItemArr[0]), new ArrayList(Arrays.asList(mediaItemArr)), 1);
            i2 = mediaItemArr.length;
            z = true;
        }
        if (mediaItemArr2 != null && mediaItemArr2.length > 0) {
            storyApi.updateUserCuration(MediaItemStory.getStoryId(mediaItemArr2[0]), new ArrayList(Arrays.asList(mediaItemArr2)), 0);
            int length = mediaItemArr2.length;
            if (isHeaderHidden(eventContext.getHeaderItem(), mediaItemArr2)) {
                storyApi.resetStoryCover(mediaItemArr2[0].getAlbumID());
                Log.d(this.TAG, "reset header because current header is hidden");
            }
            i7 = length;
            z = true;
        }
        if (z) {
            StoryUpdateNotifier.getInstance().notifyStory(getContext(), true);
        }
        String str = this.TAG;
        StringBuilder h5 = a.h(i2, i7, "saveManageContents {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        h5.append(z);
        h5.append("} + ");
        h5.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d(str, h5.toString());
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_MANAGE_CONTENTS_DONE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        SimpleThreadPool.getInstance().execute(new c(this, objArr, eventContext, 12));
    }
}
