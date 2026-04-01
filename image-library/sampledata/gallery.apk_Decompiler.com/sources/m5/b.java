package M5;

import com.samsung.android.gallery.app.ui.list.sharings.choice.SharingAlbumChoicePresenter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingAlbumChoicePresenter e;

    public /* synthetic */ b(SharingAlbumChoicePresenter sharingAlbumChoicePresenter, int i2) {
        this.d = i2;
        this.e = sharingAlbumChoicePresenter;
    }

    public final void run() {
        int i2 = this.d;
        SharingAlbumChoicePresenter sharingAlbumChoicePresenter = this.e;
        switch (i2) {
            case 0:
                sharingAlbumChoicePresenter.lambda$createSharingAlbum$0();
                return;
            default:
                sharingAlbumChoicePresenter.lambda$addToSharedAlbum$3();
                return;
        }
    }
}
