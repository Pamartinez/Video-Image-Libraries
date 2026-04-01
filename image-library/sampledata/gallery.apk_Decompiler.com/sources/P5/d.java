package P5;

import androidx.preference.Preference;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingPicturesSettingFragment e;

    public /* synthetic */ d(SharingPicturesSettingFragment sharingPicturesSettingFragment, int i2) {
        this.d = i2;
        this.e = sharingPicturesSettingFragment;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        int i2 = this.d;
        SharingPicturesSettingFragment sharingPicturesSettingFragment = this.e;
        switch (i2) {
            case 1:
                return sharingPicturesSettingFragment.onAutoUpdateChanged(preference, obj);
            case 2:
                return sharingPicturesSettingFragment.onSuggestedContentsRuleChanged(preference, obj);
            default:
                return sharingPicturesSettingFragment.onSharingWebLinkPrefChanged(preference, obj);
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        SharingPicturesSettingFragment sharingPicturesSettingFragment = this.e;
        switch (i2) {
            case 0:
                return sharingPicturesSettingFragment.onSelectedCreatureClicked(preference);
            case 3:
                return sharingPicturesSettingFragment.onChangeSpaceNameClicked(preference);
            case 4:
                return sharingPicturesSettingFragment.onChangeSpaceCoverClicked(preference);
            default:
                return sharingPicturesSettingFragment.onSharingWebLinkClicked(preference);
        }
    }
}
