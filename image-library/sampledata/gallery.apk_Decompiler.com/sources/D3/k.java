package D3;

import com.samsung.android.gallery.app.controller.sharing.request.RequestCreateStory;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.sdk.mobileservice.social.share.result.ContentDownloadResult;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ AtomicInteger e;

    public /* synthetic */ k(AtomicInteger atomicInteger, int i2) {
        this.d = i2;
        this.e = atomicInteger;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        AtomicInteger atomicInteger = this.e;
        switch (i2) {
            case 0:
                ((QueryParams) obj).setParentAlbumId(atomicInteger.get());
                return;
            case 1:
                RequestCreateStory.lambda$getTotalCropInfoKey$5(atomicInteger, (ContentDownloadResult.DownloadedContent) obj);
                return;
            case 2:
                AlbumsDragAndDropDelegate.lambda$getTotalItemCount$12(atomicInteger, (MediaItem) obj);
                return;
            default:
                atomicInteger.addAndGet(((MediaItem) obj).getCount());
                return;
        }
    }
}
