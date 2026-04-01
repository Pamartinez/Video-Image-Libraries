package eb;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.animations.SimpleShrinkView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Bitmap e;
    public final /* synthetic */ Bitmap f;
    public final /* synthetic */ int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ int f3264h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ ImageView f3265i;

    public /* synthetic */ d(int i2, Bitmap bitmap, Bitmap bitmap2, int i7, int i8, ImageView imageView) {
        this.d = i2;
        this.e = bitmap;
        this.f = bitmap2;
        this.g = i7;
        this.f3264h = i8;
        this.f3265i = imageView;
    }

    public final void accept(Object obj) {
        SimpleShrinkView.lambda$prepareTopView$0(this.d, this.e, this.f, this.g, this.f3264h, this.f3265i, (ViewGroup.MarginLayoutParams) obj);
    }
}
