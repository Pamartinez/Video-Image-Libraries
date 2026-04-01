package P5;

import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingPicturesSettingFragment e;

    public /* synthetic */ c(SharingPicturesSettingFragment sharingPicturesSettingFragment, int i2) {
        this.d = i2;
        this.e = sharingPicturesSettingFragment;
    }

    public final void run() {
        int i2 = this.d;
        SharingPicturesSettingFragment sharingPicturesSettingFragment = this.e;
        switch (i2) {
            case 0:
                sharingPicturesSettingFragment.lambda$updatePeopleCount$5();
                return;
            case 1:
                sharingPicturesSettingFragment.lambda$executeRequestErrorHandling$18();
                return;
            default:
                sharingPicturesSettingFragment.lambda$updatePeopleCount$6();
                return;
        }
    }
}
