package com.samsung.android.gallery.app.controller.externals;

import android.content.Intent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateMovieHighlightReelCmd extends CreateMovieManualCmd {
    public String getCreateMovieMode() {
        return "HLV";
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_HIGHLIGHT_REEL.toString();
    }

    public Intent getMovieMakerIntent(MediaItem[] mediaItemArr) {
        Intent movieMakerIntent = super.getMovieMakerIntent(mediaItemArr);
        if (this.mFromBixby) {
            movieMakerIntent.putExtra("isAppLaunchedByBixBy", true);
            movieMakerIntent.putExtra("selectedDuration", this.mDuration);
        }
        return movieMakerIntent;
    }

    public int getTitleRes() {
        return R.string.highlight_reel;
    }
}
