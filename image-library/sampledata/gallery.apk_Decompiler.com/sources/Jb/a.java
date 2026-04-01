package Jb;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.PinchImageView;
import com.samsung.android.gallery.widget.PinchMatrix;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.v3.DataItem;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ DataItem e;
    public final /* synthetic */ PinchImageView f;
    public final /* synthetic */ MediaItem g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ GridInfo f2831h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ PinchMatrix[] f2832i;

    public /* synthetic */ a(DataItem dataItem, PinchImageView pinchImageView, MediaItem mediaItem, GridInfo gridInfo, PinchMatrix[] pinchMatrixArr, int i2) {
        this.d = i2;
        this.e = dataItem;
        this.f = pinchImageView;
        this.g = mediaItem;
        this.f2831h = gridInfo;
        this.f2832i = pinchMatrixArr;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.lambda$requestBitmap$1(this.f, this.g, this.f2831h, this.f2832i, (Bitmap) obj);
                return;
            default:
                this.e.lambda$requestBitmap$2(this.f, this.g, this.f2831h, this.f2832i, (Bitmap) obj);
                return;
        }
    }
}
