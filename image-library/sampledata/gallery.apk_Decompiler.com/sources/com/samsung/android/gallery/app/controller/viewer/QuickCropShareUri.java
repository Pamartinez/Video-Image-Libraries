package com.samsung.android.gallery.app.controller.viewer;

import com.samsung.android.gallery.support.utils.UriBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickCropShareUri {
    private final String uri;

    public QuickCropShareUri(boolean z, String str, String str2) {
        this.uri = new UriBuilder("viewer_quick_crop_share/").appendArg("KEY_IS_SHARE", Boolean.toString(z)).appendArg("KEY_TARGET_COMPONENT_PACKAGE", str).appendArg("KEY_TARGET_COMPONENT_ACTIVITY", str2).build();
    }

    public String getUri() {
        return this.uri;
    }
}
