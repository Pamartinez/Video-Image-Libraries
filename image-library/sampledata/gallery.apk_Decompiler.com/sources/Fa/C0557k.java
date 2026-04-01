package Fa;

import androidx.preference.Preference;
import com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment;

/* renamed from: Fa.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0557k implements Preference.OnPreferenceClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LabsAlbumBnRFragment e;

    public /* synthetic */ C0557k(LabsAlbumBnRFragment labsAlbumBnRFragment, int i2) {
        this.d = i2;
        this.e = labsAlbumBnRFragment;
    }

    public final boolean onPreferenceClick(Preference preference) {
        int i2 = this.d;
        LabsAlbumBnRFragment labsAlbumBnRFragment = this.e;
        switch (i2) {
            case 0:
                return labsAlbumBnRFragment.onBackupAlbumDbClicked(preference);
            default:
                return labsAlbumBnRFragment.onRestoreAlbumDbClicked(preference);
        }
    }
}
