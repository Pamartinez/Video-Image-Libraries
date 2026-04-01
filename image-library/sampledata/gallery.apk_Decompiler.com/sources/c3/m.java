package C3;

import com.samsung.android.gallery.app.activity.GalleryPrivateActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryPrivateActivity e;

    public /* synthetic */ m(GalleryPrivateActivity galleryPrivateActivity, int i2) {
        this.d = i2;
        this.e = galleryPrivateActivity;
    }

    public final void run() {
        int i2 = this.d;
        GalleryPrivateActivity galleryPrivateActivity = this.e;
        switch (i2) {
            case 0:
                galleryPrivateActivity.lambda$onCreate$0();
                return;
            case 1:
                galleryPrivateActivity.lambda$new$6();
                return;
            case 2:
                galleryPrivateActivity.lambda$onDestroy$5();
                return;
            default:
                galleryPrivateActivity.lambda$onDestroy$4();
                return;
        }
    }
}
