package com.samsung.android.gallery.app.controller.story;

import android.content.Context;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesPinCmd extends StoriesPinCmd {
    public String getAnalyticsDetail() {
        if (this.mIsPin) {
            return AnalyticsDetail.EVENT_DETAIL_STORY_PICTURE_PIN.toString();
        }
        return AnalyticsDetail.EVENT_DETAIL_STORY_PICTURE_UNPIN.toString();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_PICTURES_PIN.toString();
    }

    public void pinCompleted(boolean z) {
        int i2;
        if (z) {
            Context context = getContext();
            if (this.mIsPin) {
                i2 = R.string.story_pinned_to_top;
            } else {
                i2 = R.string.story_unpinned_from_top;
            }
            Utils.showToast(context, i2);
        }
    }
}
