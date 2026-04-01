package P4;

import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AutoAlbumSettingFragment;
import com.samsung.android.gallery.module.album.AutoAlbumSettingData;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AutoAlbumSettingFragment e;
    public final /* synthetic */ AutoAlbumSettingData f;

    public /* synthetic */ c(AutoAlbumSettingFragment autoAlbumSettingFragment, AutoAlbumSettingData autoAlbumSettingData, int i2) {
        this.d = i2;
        this.e = autoAlbumSettingFragment;
        this.f = autoAlbumSettingData;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$loadPreference$3(this.f, (SwitchPreferenceCompat) obj);
                return;
            case 1:
                this.e.lambda$loadPreference$4(this.f, (Preference) obj);
                return;
            case 2:
                this.e.lambda$loadPreference$5(this.f, (DropDownPreference) obj);
                return;
            default:
                this.e.lambda$updateCreatureCount$0(this.f, (Preference) obj);
                return;
        }
    }
}
