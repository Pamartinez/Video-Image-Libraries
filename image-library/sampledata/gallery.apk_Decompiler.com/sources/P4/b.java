package P4;

import androidx.preference.Preference;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AlbumSettingFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumSettingFragment e;

    public /* synthetic */ b(AlbumSettingFragment albumSettingFragment, int i2) {
        this.d = i2;
        this.e = albumSettingFragment;
    }

    public final boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        AlbumSettingFragment albumSettingFragment = this.e;
        switch (i2) {
            case 0:
                return albumSettingFragment.onChangeCoverClicked(preference);
            case 1:
                return albumSettingFragment.lambda$initOptionScreenshotCustom$5(preference);
            default:
                return albumSettingFragment.onRenameClicked(preference);
        }
    }
}
