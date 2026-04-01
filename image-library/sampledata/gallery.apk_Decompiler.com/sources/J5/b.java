package J5;

import android.graphics.Bitmap;
import android.view.PixelCopy;
import com.samsung.android.gallery.app.ui.list.search.toolbar.FloatingRecommendationLauncher;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements PixelCopy.OnPixelCopyFinishedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FloatingRecommendationLauncher f2376a;
    public final /* synthetic */ Bitmap b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f2377c;
    public final /* synthetic */ String d;

    public /* synthetic */ b(FloatingRecommendationLauncher floatingRecommendationLauncher, Bitmap bitmap, boolean z, String str) {
        this.f2376a = floatingRecommendationLauncher;
        this.b = bitmap;
        this.f2377c = z;
        this.d = str;
    }

    public final void onPixelCopyFinished(int i2) {
        this.f2376a.lambda$captureScreenAndExecuteFloatingRecommendation$0(this.b, this.f2377c, this.d, i2);
    }
}
