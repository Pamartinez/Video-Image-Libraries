package com.samsung.android.gallery.app.activity.external;

import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.module.thumbnail.logic.RemoteThumbnailLoader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements RemoteThumbnailLoader {
    public final byte[] load(String str, long j2) {
        return new RemoteClient(str).getThumb(j2);
    }
}
