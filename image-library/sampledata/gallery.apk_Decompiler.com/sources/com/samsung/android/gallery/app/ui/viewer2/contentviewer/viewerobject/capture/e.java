package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ long d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ String f;
    public final /* synthetic */ String g;

    public /* synthetic */ e(long j2, String str, boolean z, String str2) {
        this.d = j2;
        this.e = z;
        this.f = str;
        this.g = str2;
    }

    public final void accept(Object obj) {
        ((UriBuilder) obj).appendArg("quickCropId", this.d).appendArg("quickCropShare", this.e).appendArg((String) MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME, this.f).appendArg("className", this.g);
    }
}
