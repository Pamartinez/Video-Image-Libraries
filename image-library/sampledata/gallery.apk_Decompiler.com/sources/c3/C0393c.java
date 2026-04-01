package C3;

import com.samsung.android.gallery.app.activity.GalleryActivity;

/* renamed from: C3.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0393c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryActivity e;

    public /* synthetic */ C0393c(GalleryActivity galleryActivity, int i2) {
        this.d = i2;
        this.e = galleryActivity;
    }

    public final void run() {
        int i2 = this.d;
        GalleryActivity galleryActivity = this.e;
        switch (i2) {
            case 0:
                galleryActivity.lambda$onCreate$0();
                return;
            case 1:
                galleryActivity.lambda$new$6();
                return;
            default:
                galleryActivity.lambda$onCreateInternal$4();
                return;
        }
    }
}
