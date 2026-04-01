package a6;

import android.graphics.Bitmap;
import android.view.PixelCopy;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.ProcessingViewManager;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements PixelCopy.OnPixelCopyFinishedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ long f2477a;
    public final /* synthetic */ ImageView b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Bitmap f2478c;
    public final /* synthetic */ Runnable d;

    public /* synthetic */ l(long j2, ImageView imageView, Bitmap bitmap, Runnable runnable) {
        this.f2477a = j2;
        this.b = imageView;
        this.f2478c = bitmap;
        this.d = runnable;
    }

    public final void onPixelCopyFinished(int i2) {
        ProcessingViewManager.lambda$copyBlurredBackgroundView$4(this.f2477a, this.b, this.f2478c, this.d, i2);
    }
}
