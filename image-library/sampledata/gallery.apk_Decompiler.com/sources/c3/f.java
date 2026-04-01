package C3;

import com.samsung.android.gallery.app.activity.GalleryActivityHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ GalleryActivityHandler e;

    public /* synthetic */ f(GalleryActivityHandler galleryActivityHandler, int i2) {
        this.d = i2;
        this.e = galleryActivityHandler;
    }

    public final void run() {
        int i2 = this.d;
        GalleryActivityHandler galleryActivityHandler = this.e;
        switch (i2) {
            case 0:
                galleryActivityHandler.releaseCpu();
                return;
            case 1:
                galleryActivityHandler.lambda$onActivityRecreate$8();
                return;
            case 2:
                galleryActivityHandler.lambda$updateBottomTabItems$3();
                return;
            case 3:
                galleryActivityHandler.lambda$onBackPressInvokableStateChanged$9();
                return;
            default:
                galleryActivityHandler.handleMainScreenChanged();
                return;
        }
    }
}
