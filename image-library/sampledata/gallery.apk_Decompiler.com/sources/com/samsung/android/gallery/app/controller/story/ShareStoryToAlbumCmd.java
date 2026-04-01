package com.samsung.android.gallery.app.controller.story;

import B8.j;
import Fa.C0571z;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.module.bgm.BgmExtraInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Arrays;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareStoryToAlbumCmd extends BaseCommand {
    private String mBgmName;
    private String mFilterName;
    private MediaItem[] mItems;
    private String mThemeName;

    private String getCoverInfo(MediaItem mediaItem) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("bucketId", mediaItem.getBucketID());
        jSONObject.put("title", FileUtils.getNameFromPath(mediaItem.getPath()));
        jSONObject.put("coverRectRatio", MediaItemStory.getStoryCoverRectRatio(mediaItem));
        return jSONObject.toString();
    }

    private String getStoryInfo(MediaItem mediaItem) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("storyName", mediaItem.getTitle());
        if (!TextUtils.isEmpty(this.mThemeName)) {
            jSONObject.put("themeName", this.mThemeName);
        }
        if (!TextUtils.isEmpty(this.mFilterName)) {
            jSONObject.put("filterName", this.mFilterName);
        }
        if (!TextUtils.isEmpty(this.mBgmName)) {
            jSONObject.put("bgmName", this.mBgmName);
        }
        return "story" + jSONObject;
    }

    private boolean hasCustomCover(MediaItem mediaItem) {
        if (MediaItemStory.getStoryCoverId(mediaItem) <= -1 || TextUtils.isEmpty(MediaItemStory.getStoryCoverRectRatio(mediaItem)) || !Arrays.stream(this.mItems).filter(new C0571z(4)).anyMatch(new j(mediaItem, 5))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$hasCustomCover$0(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2.getFileId() == mediaItem.getFileId()) {
            return true;
        }
        return false;
    }

    private void shareStoryToSharedAlbum(MediaItem mediaItem) {
        try {
            if (!TextUtils.isEmpty(mediaItem.getTitle()) && this.mItems.length > 0) {
                getBlackboard().publish("data://user/storyInfo", getStoryInfo(mediaItem));
                if (hasCustomCover(mediaItem)) {
                    getBlackboard().publish("data://user/coverInfo", getCoverInfo(mediaItem));
                }
                new ChooseSharedAlbumCmd().execute(getHandler(), this.mItems);
            }
        } catch (Exception e) {
            Log.e(this.TAG, e.getMessage());
        }
    }

    public String getAnalyticsDetail() {
        MediaItem mediaItem;
        if (getHandler() != null) {
            mediaItem = getHandler().getHeaderItem();
        } else {
            mediaItem = null;
        }
        if (mediaItem != null) {
            return String.valueOf(MediaItemStory.getStorySaType(mediaItem));
        }
        return null;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_ADD_TO_SHARED_ALBUM.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str;
        MediaItem[] mediaItemArr;
        MediaItem headerItem = eventContext.getHeaderItem();
        if (headerItem != null) {
            this.mThemeName = objArr[0];
            BgmExtraInfo bgmExtraInfo = objArr[1];
            if (bgmExtraInfo.isMyMusic) {
                str = null;
            } else {
                str = bgmExtraInfo.bgmName;
            }
            this.mBgmName = str;
            this.mFilterName = Filter.NONE.getRawName();
            if (eventContext.isSelectionMode()) {
                mediaItemArr = eventContext.getSelectedItems();
            } else {
                mediaItemArr = eventContext.getAllItems();
            }
            this.mItems = mediaItemArr;
            shareStoryToSharedAlbum(headerItem);
        }
    }
}
