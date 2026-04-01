package com.samsung.android.gallery.app.controller.externals;

import android.content.Intent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayVideoChooserCmd extends PlayGenericVideoCmd {
    public Intent createIntent(MediaItem mediaItem) {
        Intent intent = new Intent("android.intent.action.VIEW");
        createPlayVideoIntent(intent, mediaItem, false);
        return intent;
    }

    public void handleFail() {
        showToast((int) R.string.activity_not_found, 1);
    }
}
