package N5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySharedAlbumWelcomeFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ FamilySharedAlbumWelcomeFragment e;

    public /* synthetic */ b(FamilySharedAlbumWelcomeFragment familySharedAlbumWelcomeFragment, int i2) {
        this.d = i2;
        this.e = familySharedAlbumWelcomeFragment;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        FamilySharedAlbumWelcomeFragment familySharedAlbumWelcomeFragment = this.e;
        switch (i2) {
            case 0:
                familySharedAlbumWelcomeFragment.lambda$createLaterButton$1(view);
                return;
            default:
                familySharedAlbumWelcomeFragment.lambda$createGetStartedButton$0(view);
                return;
        }
    }
}
