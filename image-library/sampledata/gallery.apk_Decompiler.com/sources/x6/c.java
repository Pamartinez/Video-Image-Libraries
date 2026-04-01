package x6;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ILargeImage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.LargeImageLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements SubscriberListener {
    public final /* synthetic */ LargeImageLoader d;
    public final /* synthetic */ MediaItem e;
    public final /* synthetic */ ILargeImage f;

    public /* synthetic */ c(LargeImageLoader largeImageLoader, MediaItem mediaItem, ILargeImage iLargeImage) {
        this.d = largeImageLoader;
        this.e = mediaItem;
        this.f = iLargeImage;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.lambda$requestDecodeOriginalImage$0(this.e, this.f, obj, bundle);
    }
}
