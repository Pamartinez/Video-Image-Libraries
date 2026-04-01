package R9;

import com.samsung.android.allshare.ERROR;
import com.samsung.android.allshare.media.ImageViewer;
import com.samsung.android.gallery.module.remote.dlna.DlnaHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ImageViewer.IImageViewerEventListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DlnaHelper f2868a;

    public /* synthetic */ a(DlnaHelper dlnaHelper) {
        this.f2868a = dlnaHelper;
    }

    public final void onDeviceChanged(ImageViewer.ImageViewerState imageViewerState, ERROR error) {
        this.f2868a.onImageViewerDeviceChanged(imageViewerState, error);
    }
}
