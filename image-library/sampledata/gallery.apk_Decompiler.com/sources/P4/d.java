package P4;

import androidx.preference.Preference;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AutoAlbumSettingFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ AutoAlbumSettingFragment e;

    public /* synthetic */ d(AutoAlbumSettingFragment autoAlbumSettingFragment, int i2) {
        this.d = i2;
        this.e = autoAlbumSettingFragment;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        int i2 = this.d;
        AutoAlbumSettingFragment autoAlbumSettingFragment = this.e;
        switch (i2) {
            case 0:
                return autoAlbumSettingFragment.onSuggestedContentsRuleChanged(preference, obj);
            default:
                return autoAlbumSettingFragment.onAutoUpdateChanged(preference, obj);
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        return this.e.onSelectedCreatureClicked(preference);
    }
}
