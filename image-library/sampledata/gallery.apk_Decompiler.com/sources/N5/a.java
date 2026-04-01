package N5;

import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySharedAlbumWelcomeFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ FamilySharedAlbumWelcomeFragment e;

    public /* synthetic */ a(FamilySharedAlbumWelcomeFragment familySharedAlbumWelcomeFragment, int i2) {
        this.d = i2;
        this.e = familySharedAlbumWelcomeFragment;
    }

    public final void run() {
        int i2 = this.d;
        FamilySharedAlbumWelcomeFragment familySharedAlbumWelcomeFragment = this.e;
        switch (i2) {
            case 0:
                familySharedAlbumWelcomeFragment.lambda$onHandleEvent$5();
                return;
            default:
                familySharedAlbumWelcomeFragment.lambda$onGetStartedButtonClicked$3();
                return;
        }
    }
}
