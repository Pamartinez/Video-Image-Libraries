package H7;

import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.LogVideoTipHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoSpeedControlHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsSlideHandler;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements IntSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2351a;
    public final /* synthetic */ Object b;

    public /* synthetic */ q(int i2, Object obj) {
        this.f2351a = i2;
        this.b = obj;
    }

    public final int getAsInt() {
        int i2 = this.f2351a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                return ((LogVideoTipHandler) obj).getPopupTipTopMargin();
            case 1:
                return ((VideoSpeedControlHandler) obj).getControllerTopMargin();
            case 2:
                return ((DetailsSlideHandler) obj).calcExpandedOffset();
            default:
                return DbCompat.autoAlbumApi().getAlbumCount(((MediaItem) obj).getAlbumID(), MediaType.Video);
        }
    }
}
