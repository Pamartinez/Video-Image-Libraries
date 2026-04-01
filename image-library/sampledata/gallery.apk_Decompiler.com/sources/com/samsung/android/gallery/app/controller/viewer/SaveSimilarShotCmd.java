package com.samsung.android.gallery.app.controller.viewer;

import android.content.Context;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveSimilarShotCmd extends SaveGroupShotCmd {
    public String getEventId() {
        return null;
    }

    public String getShotName(Context context) {
        return context.getResources().getString(R.string.similar_photo);
    }

    public boolean isContainVideo() {
        return false;
    }
}
