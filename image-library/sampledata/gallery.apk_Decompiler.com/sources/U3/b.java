package U3;

import android.graphics.Bitmap;
import android.view.PixelCopy;
import com.samsung.android.gallery.app.controller.story.LaunchOnDemandStoryCmd;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements PixelCopy.OnPixelCopyFinishedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LaunchOnDemandStoryCmd f2442a;
    public final /* synthetic */ Bitmap b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f2443c;
    public final /* synthetic */ int d;

    public /* synthetic */ b(LaunchOnDemandStoryCmd launchOnDemandStoryCmd, Bitmap bitmap, String str, int i2) {
        this.f2442a = launchOnDemandStoryCmd;
        this.b = bitmap;
        this.f2443c = str;
        this.d = i2;
    }

    public final void onPixelCopyFinished(int i2) {
        this.f2442a.lambda$executeOnDemandFloatingView$0(this.b, this.f2443c, this.d, i2);
    }
}
