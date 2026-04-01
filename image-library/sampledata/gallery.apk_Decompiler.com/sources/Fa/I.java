package Fa;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.samsung.android.gallery.app.provider.ShareProvider;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class I implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Context e;

    public /* synthetic */ I(Context context, int i2) {
        this.d = i2;
        this.e = context;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Context context = this.e;
        switch (i2) {
            case 0:
                Utils.showThemeToast(context, ((Integer) obj) + " images and videos are scanned");
                return;
            case 1:
                context.grantUriPermission(ShareProvider.SHARE_SHEET_PACKAGE, (Uri) obj, 3);
                return;
            case 2:
                ((ImageView) obj).setImageDrawable(context.getDrawable(R.drawable.gallery_ic_thumbnail_trash));
                return;
            case 3:
                SamsungCloudCompat.setFavorite(context, ((MediaItem) obj).getCloudServerId(), ((MediaItem) obj).getCloudServerPath(), true);
                return;
            case 4:
                ItemProperty.lambda$reset$1(context, (String) obj);
                return;
            default:
                context.grantUriPermission("com.samsung.android.spay", (Uri) obj, 1);
                return;
        }
    }
}
