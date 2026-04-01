package P4;

import androidx.preference.Preference;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AlbumSettingFragment;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AlbumSettingFragment e;

    public /* synthetic */ a(AlbumSettingFragment albumSettingFragment, int i2) {
        this.d = i2;
        this.e = albumSettingFragment;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        AlbumSettingFragment albumSettingFragment = this.e;
        switch (i2) {
            case 0:
                albumSettingFragment.lambda$onViewCreated$1((GalleryToolbar) obj);
                return;
            case 1:
                albumSettingFragment.lambda$onViewCreated$2((GalleryAppBarLayout) obj);
                return;
            case 2:
                albumSettingFragment.lambda$initOptionScreenshotCustom$6((Preference) obj);
                return;
            case 3:
                albumSettingFragment.lambda$initOptionCategory$3((Preference) obj);
                return;
            default:
                albumSettingFragment.lambda$initOptionCategory$4((Preference) obj);
                return;
        }
    }
}
