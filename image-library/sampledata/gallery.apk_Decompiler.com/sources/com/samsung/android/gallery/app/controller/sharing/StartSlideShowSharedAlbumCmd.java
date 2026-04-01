package com.samsung.android.gallery.app.controller.sharing;

import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.remote.StartSlideshowV2Cmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartSlideShowSharedAlbumCmd extends BaseCommand {
    private String getBgmName(MediaItem mediaItem) {
        String bgmName = MediaItemMde.getBgmName(mediaItem);
        if (!TextUtils.isEmpty(bgmName)) {
            return bgmName;
        }
        String spaceId = MediaItemMde.getSpaceId(mediaItem);
        return EffectTheme.getRandomTheme(spaceId.hashCode()).getRandomBgm(spaceId.hashCode());
    }

    private String getFilterName(MediaItem mediaItem) {
        String filterName = MediaItemMde.getFilterName(mediaItem);
        if (TextUtils.isEmpty(filterName)) {
            return Filter.NONE.getRawName();
        }
        return filterName;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_SHARED_SLIDE_SHOW.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            Log.she(this.TAG, "null arguments");
            return;
        }
        MediaItem mediaItem = objArr[0];
        try {
            new StartSlideshowV2Cmd().execute(eventContext, null, getFilterName(mediaItem), getBgmName(mediaItem), Boolean.TRUE);
        } catch (Exception e) {
            Log.she(this.TAG, e.getMessage());
        }
    }
}
