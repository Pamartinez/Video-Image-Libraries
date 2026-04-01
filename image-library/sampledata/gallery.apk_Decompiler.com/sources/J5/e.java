package J5;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderView;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsInvitationViewHolder;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Bitmap e;

    public /* synthetic */ e(Bitmap bitmap, int i2) {
        this.d = i2;
        this.e = bitmap;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Bitmap bitmap = this.e;
        switch (i2) {
            case 0:
                ((FilterResultsEntity) obj).setThumb(bitmap);
                return;
            case 1:
                ((Consumer) obj).accept(bitmap);
                return;
            case 2:
                MxAlbumsHeaderView.lambda$setThumbnailOnHostIcon$5(bitmap, (ImageView) obj);
                return;
            default:
                SharingsInvitationViewHolder.lambda$setThumbnailOnHostIcon$3(bitmap, (ImageView) obj);
                return;
        }
    }
}
