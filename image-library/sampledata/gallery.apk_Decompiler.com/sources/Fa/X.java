package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.SharingNotificationFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class X implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SharingNotificationFragment e;

    public /* synthetic */ X(SharingNotificationFragment sharingNotificationFragment, int i2) {
        this.d = i2;
        this.e = sharingNotificationFragment;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        int i2 = this.d;
        SharingNotificationFragment sharingNotificationFragment = this.e;
        switch (i2) {
            case 0:
                return sharingNotificationFragment.lambda$loadPreference$3(preference, obj);
            case 1:
                return sharingNotificationFragment.lambda$loadPreference$4(preference, obj);
            case 2:
                return sharingNotificationFragment.lambda$loadPreference$5(preference, obj);
            default:
                return sharingNotificationFragment.lambda$loadMainSwitchPreference$0(preference, obj);
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        return this.e.lambda$loadMainSwitchPreference$1(preference);
    }
}
