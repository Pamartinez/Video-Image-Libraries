package com.samsung.android.gallery.app.activity.external;

import android.net.Uri;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Runnable {
    public final /* synthetic */ ShortcutViewNavigatorController d;
    public final /* synthetic */ Uri e;
    public final /* synthetic */ MediaItem[] f;
    public final /* synthetic */ CountDownLatch g;

    public /* synthetic */ r(ShortcutViewNavigatorController shortcutViewNavigatorController, Uri uri, MediaItem[] mediaItemArr, CountDownLatch countDownLatch) {
        this.d = shortcutViewNavigatorController;
        this.e = uri;
        this.f = mediaItemArr;
        this.g = countDownLatch;
    }

    public final void run() {
        this.d.lambda$loadShortcutItem$0(this.e, this.f, this.g);
    }
}
