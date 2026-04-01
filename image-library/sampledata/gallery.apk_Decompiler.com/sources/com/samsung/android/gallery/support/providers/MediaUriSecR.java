package com.samsung.android.gallery.support.providers;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaUriSecR extends MediaUriSecQ {
    public Uri getSecMediaUri(boolean z) {
        Uri uri = MediaUriSecQ.SEC_MEDIA_TABLE_URI;
        if (z) {
            return uri;
        }
        return uri.buildUpon().appendQueryParameter("nonotify", "1").build();
    }
}
