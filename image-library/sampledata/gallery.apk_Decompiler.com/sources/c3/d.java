package C3;

import android.os.Bundle;
import com.samsung.android.gallery.app.activity.GalleryActivity;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryActivity e;

    public /* synthetic */ d(GalleryActivity galleryActivity, int i2) {
        this.d = i2;
        this.e = galleryActivity;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        GalleryActivity galleryActivity = this.e;
        switch (i2) {
            case 0:
                galleryActivity.lambda$onCreateInternal$1(obj, bundle);
                return;
            case 1:
                galleryActivity.lambda$onCreateInternal$2(obj, bundle);
                return;
            default:
                galleryActivity.lambda$onCreateInternal$3(obj, bundle);
                return;
        }
    }
}
