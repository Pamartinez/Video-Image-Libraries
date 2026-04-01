package O5;

import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingPicturesFragment e;

    public /* synthetic */ d(SharingPicturesFragment sharingPicturesFragment, int i2) {
        this.d = i2;
        this.e = sharingPicturesFragment;
    }

    public final void run() {
        int i2 = this.d;
        SharingPicturesFragment sharingPicturesFragment = this.e;
        switch (i2) {
            case 0:
                sharingPicturesFragment.lambda$onPostResume$7();
                return;
            case 1:
                sharingPicturesFragment.loadFamilyAutoAlbumId();
                return;
            case 2:
                sharingPicturesFragment.lambda$exit$9();
                return;
            default:
                sharingPicturesFragment.removeSharedAlbumNotifications();
                return;
        }
    }
}
