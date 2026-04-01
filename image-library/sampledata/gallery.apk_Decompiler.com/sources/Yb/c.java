package Yb;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.widget.simpleslideshow.transform.PageTransform;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ View e;
    public final /* synthetic */ float f;

    public /* synthetic */ c(View view, float f5, int i2) {
        this.d = i2;
        this.e = view;
        this.f = f5;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((PageTransform) obj).transformPage(this.e, this.f);
                return;
            default:
                ((ViewPager2.PageTransformer) obj).transformPage(this.e, this.f);
                return;
        }
    }
}
