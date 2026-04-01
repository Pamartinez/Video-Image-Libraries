package P5;

import androidx.preference.Preference;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;
import com.samsung.android.gallery.settings.widget.ExSumSwitchPreference;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingPicturesSettingFragment e;

    public /* synthetic */ a(SharingPicturesSettingFragment sharingPicturesSettingFragment, int i2) {
        this.d = i2;
        this.e = sharingPicturesSettingFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SharingPicturesSettingFragment sharingPicturesSettingFragment = this.e;
        switch (i2) {
            case 0:
                sharingPicturesSettingFragment.lambda$onViewCreated$2((GalleryToolbar) obj);
                return;
            case 1:
                sharingPicturesSettingFragment.lambda$updatePeopleCount$4((Preference) obj);
                return;
            case 2:
                sharingPicturesSettingFragment.lambda$updatePreference$7((ExSumSwitchPreference) obj);
                return;
            case 3:
                sharingPicturesSettingFragment.lambda$updatePreference$8(obj);
                return;
            case 4:
                sharingPicturesSettingFragment.lambda$executeRequestErrorHandling$17((ExSumSwitchPreference) obj);
                return;
            case 5:
                sharingPicturesSettingFragment.loadArgument((String) obj);
                return;
            case 6:
                sharingPicturesSettingFragment.lambda$initSharingAlbumOption$9((Preference) obj);
                return;
            default:
                sharingPicturesSettingFragment.lambda$initSharingAlbumOption$10((Preference) obj);
                return;
        }
    }
}
